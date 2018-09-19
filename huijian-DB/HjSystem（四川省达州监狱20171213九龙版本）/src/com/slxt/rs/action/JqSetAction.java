package com.slxt.rs.action;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.slxt.rs.model.JlHjJqWeek;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.SysHjServer;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.JqSetService;


public class JqSetAction extends DispatchAction{
	private  JqSetService js;
	public void setJs(JqSetService js) {
		this.js = js;
	}
    //查询监区
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String hql="from JlJq order by jy";
		    List<JlJq> list=js.searchAll(hql);
		    request.setAttribute("jlJqList", list);
		    return mapping.findForward("jqSetMain");
	}
	//删除监区
	public ActionForward deleteJq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
			Calendar c = Calendar.getInstance();   
			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
			c  =  Calendar.getInstance(Locale.CHINESE);   
			String loginTime = simpleDateTimeFormat.format(c.getTime());
			SysLog sl=new SysLog();
			sl.setLogTime(loginTime);
			sl.setUserName(user.getUserName());
			sl.setUserNo(user.getUserNo());
			sl.setUserIp(request.getRemoteAddr());
			sl.setModel("监区设置");
			String jqId=request.getParameter("jqId");
			JlJq jlJq=(JlJq)js.findById(JlJq.class,Integer.parseInt(jqId));
			sl.setType("严重");
			sl.setInfo("删除监区（监区编号："+jlJq.getJqNo()+" 监区名称："+jlJq.getJqName()+")");
			sl.setOp("删除监区");
			js.save(sl);
			StringBuffer str=new StringBuffer(" delete from JlHjJqWeek jjq where jjq.jqNo=?");
			Object[] obj={jlJq.getJqNo()};
			js.deleteByHql(str.toString(), obj);
			StringBuffer str1=new StringBuffer(" delete from SysUserJq jjq where jjq.jqNo=?");
			js.deleteByHql(str1.toString(), obj);
			js.delete(JlJq.class, Integer.parseInt(jqId));
			return mapping.findForward("delSuceess");
	}
	//增加监区
	public ActionForward checkJy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String hql="from SysHjServer";
		    List<SysHjServer> list=js.searchAll(hql);
		    request.setAttribute("sysQqServerList", list);
		    return mapping.findForward("addJq");
	}
	//增加保存监区
	public ActionForward addSaveJq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String jqNo = java.net.URLDecoder.decode((String)request.getParameter("jqNo"),"UTF-8") ;
		    String jqName = java.net.URLDecoder.decode((String)request.getParameter("jqName"),"UTF-8") ;
		    String jyId = java.net.URLDecoder.decode((String)request.getParameter("jyId"),"UTF-8");
		    String isTs=request.getParameter("isTs");
		    StringBuffer str=new StringBuffer("from JlJq jl where jl.jqNo='");
		    str.append(jqNo+"'");
		    List list=js.searchAll(str.toString());
		    if(list.size()>0){
		    	JSONArray json=JSONArray.fromObject(null);
		    	response.getWriter().println(json.toString());
		    }else{
		    	JlJq jl=new JlJq();
		    	jl.setJqNo(jqNo);
		    	if(jqName!=""&&jqName!=null){
		    		jl.setJqName(jqName);
		    	}
		    	Calendar c = Calendar.getInstance();   
		    	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		    	c  =  Calendar.getInstance(Locale.CHINESE);   
		    	String loginTime = simpleDateTimeFormat.format(c.getTime());
		    	SysLog sl=new SysLog();
		    	sl.setType("正常");
		    	sl.setLogTime(loginTime);
		    	sl.setUserName(user.getUserName());
		    	sl.setUserNo(user.getUserNo());
		    	sl.setUserIp(request.getRemoteAddr());
		    	sl.setModel("监区设置");
		    	sl.setInfo("增加监区（监区编号："+jqNo+"  监区名称："+jqName+")");
		    	sl.setOp("增加监区");
		    	js.save(sl);
				jl.setJy(jyId);
				jl.setIsTs(Integer.parseInt(isTs));
				js.save(jl);
				JSONArray json=JSONArray.fromObject(jl);
				response.getWriter().println(json.toString());
		    }
		    return null;
	}
	//修改监区
	public ActionForward updateJq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
			   return mapping.findForward("sessionFailure");
		    }
		    String jqId=request.getParameter("jqId");
		    JlJq jlJq=(JlJq)js.findById(JlJq.class,Integer.parseInt(jqId));
		    StringBuffer str=new StringBuffer(" from SysHjServer sqs where sqs.serverName!='");
		    str.append(jlJq.getJy()+"'");
		    List<SysHjServer> list=js.searchAll(str.toString());
		    request.setAttribute("jlJq", jlJq);
		    request.setAttribute("sysQqServerList", list);
		    return mapping.findForward("updateJq");
	}
	//修改保存监区
	public ActionForward updateSaveJq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String jqId=request.getParameter("jqId");
			String jqName = java.net.URLDecoder.decode((String)request.getParameter("jqName"),"UTF-8") ;
			String jyMc = java.net.URLDecoder.decode((String)request.getParameter("jyMc"),"UTF-8");
			String isTs=request.getParameter("isTs");
			JlJq jlJq=(JlJq)js.findById(JlJq.class, Integer.parseInt(jqId));
			StringBuffer str1=new StringBuffer("");
			StringBuffer str=new StringBuffer("update JlJq jl set ");
			if(jqName!=""){
				str.append("jl.jqName='"+jqName+"',");
			}else{
				str.append("jl.jqName='',");
			}
			str.append("jl.jy='"+jyMc+"',");
			str.append("jl.isTs="+Integer.parseInt(isTs));
			Calendar c = Calendar.getInstance();   
			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
			c  =  Calendar.getInstance(Locale.CHINESE);   
			String loginTime = simpleDateTimeFormat.format(c.getTime());
			if((jqName.trim()!="" && jlJq.getJqName()!=null && !jqName.trim().equals(jlJq.getJqName()))  || jlJq.getIsTs()!=Integer.parseInt(isTs)){
				SysLog sl=new SysLog();
				sl.setType("正常");
				sl.setLogTime(loginTime);
				sl.setUserName(user.getUserName());
				sl.setUserNo(user.getUserNo());
				sl.setUserIp(request.getRemoteAddr());
				sl.setModel("监区设置");
				str1.append("编号为"+jlJq.getJqNo()+"的监区");
				if(jqName.trim()!="" && !jlJq.getJqName().equals(jqName.trim())){
					str1.append("  监区名称"+jlJq.getJqName()+"修改为"+jqName);
				}
				if(jlJq.getIsTs()!=Integer.parseInt(isTs)){
					if(Integer.parseInt(isTs)==0){
						str1.append("  该监区设置为普通监区");
					}else{
						str1.append("  该监区设置为特殊监区");
					}
				}
				sl.setInfo(str1.toString());
				sl.setOp("修改监区");
				js.save(sl);
			}
			str.append(" where jl.webId=?");
			Object[] obj={Integer.parseInt(jqId)};
			js.updates(str.toString(), obj);
			JSONArray json=JSONArray.fromObject(null);
			response.getWriter().println(json.toString());
			return null;
	}
	//查询监区的亲情星期设置
	public ActionForward checkJqWeek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
			   return mapping.findForward("sessionFailure");
		    }
		    String jqId=request.getParameter("jqId");
		    JlJq jlJq=(JlJq)js.findById(JlJq.class, Integer.parseInt(jqId));
		    StringBuffer str=new StringBuffer("from JlHjJqWeek jjq where jjq.jqNo='");
		    str.append(jlJq.getJqNo()+"'");
		    List<JlHjJqWeek> jlHjJqWeek=js.searchAll(str.toString());
		    request.setAttribute("jlJq", jlJq);
		    request.setAttribute("jlJqWeekTimeList", jlHjJqWeek);
		    return mapping.findForward("jqWeekMain");
	}
	//增加监区的亲情电话使用时间
	public ActionForward addjqWeek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
		   	String webId=request.getParameter("webId");
		   	JlJq jlJq=(JlJq)js.findById(JlJq.class, Integer.parseInt(webId));
		   	request.setAttribute("jlJq", jlJq);
		   	return mapping.findForward("addjqWeek");
	}
	//增加保存监区亲情电话使用时间
	public ActionForward addSaveJqWeek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
		    String jqNo = java.net.URLDecoder.decode((String)request.getParameter("jqNo"),"UTF-8") ;
		    String jy = java.net.URLDecoder.decode((String)request.getParameter("jy"),"UTF-8");
		    String webId=request.getParameter("webId");
		    String selectedWeek=request.getParameter("selectedWeek");
		    String hql="from JlHjJqWeek where jqNo='"+jqNo+"' and jqWeek="+Integer.parseInt(selectedWeek) ;
		    List list=js.searchAll(hql);
		    if(list.size()>0){
		    	JSONArray json=JSONArray.fromObject(null);
		    	response.getWriter().println(json.toString());
		    }else{
		    	JlHjJqWeek jwt= new JlHjJqWeek();
		    	jwt.setJqNo(jqNo);
		    	jwt.setJqWeek(Integer.parseInt(selectedWeek));
		    	jwt.setJy(jy);
		    	js.save(jwt);
		    	Calendar c = Calendar.getInstance();   
		    	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		    	c  =  Calendar.getInstance(Locale.CHINESE);   
		    	String loginTime = simpleDateTimeFormat.format(c.getTime());
		    	String weekDay="";
		    	switch (Integer.parseInt(selectedWeek)) {
					case 0:
						weekDay="星期天";
						break;
					case 1:
						weekDay="星期一";
						break;
					case 2:
						weekDay="星期二";
						break;
					case 3:
						weekDay="星期三";
						break;
					case 4:
						weekDay="星期四";
						break;
					case 5:
						weekDay="星期五";
						break;
					case 6:
						weekDay="星期六";
						break;
				}
		    	SysLog sl=new SysLog();
		    	sl.setType("正常");
		    	sl.setLogTime(loginTime);
		    	sl.setUserName(user.getUserName());
		    	sl.setUserNo(user.getUserNo());
		    	sl.setUserIp(request.getRemoteAddr());
		    	sl.setModel("监区设置");
		    	sl.setInfo("增加监区会见时间（监区编号："+jqNo+"  星期："+weekDay+")");
		    	sl.setOp("增加监区会见时间");
		    	js.save(sl);
				JSONArray json=JSONArray.fromObject(Integer.parseInt(webId));
			    response.getWriter().println(json.toString());
		    }
		    return null;
	}
	//修改监区的亲情电话使用时间
	public ActionForward updateJqWeek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
		    String timeIndex=request.getParameter("timeIndex");
		    JlHjJqWeek jlHjJqWeek=(JlHjJqWeek)js.findById(JlHjJqWeek.class, Integer.parseInt(timeIndex));
		    request.setAttribute("jlJqWeekTime", jlHjJqWeek);
		    String hql="from JlJq where jqNo='"+jlHjJqWeek.getJqNo()+"'";
		    List list=js.searchAll(hql);
		    if(list.size()>0){
		    	JlJq jlJq=(JlJq)list.get(0);
		    	request.setAttribute("jlJq", jlJq);
		    }
		    return mapping.findForward("updateJqWeek");
	}
	//保存修改监区的亲情电话使用时间
	public ActionForward updateSaveJqWeek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
		    String webId=request.getParameter("webId");
		    String selectedWeek=request.getParameter("selectedWeek");
		    String timeIndex=request.getParameter("timeIndex");
		    JlHjJqWeek jlJqWeekTime=(JlHjJqWeek)js.findById(JlHjJqWeek.class, Integer.parseInt(timeIndex));
		    String hql="from JlHjJqWeek where jqNo='"+jlJqWeekTime.getJqNo()+"' and jqWeek="+Integer.parseInt(selectedWeek);
			List list=js.searchAll(hql);
			if(list.size()>0){
				JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
			}else{
				StringBuffer str=new StringBuffer("update JlHjJqWeek set ");
				str.append(" jqWeek='"+selectedWeek+"'");
				str.append(" where webId=?");
				Object[] obj={Integer.parseInt(timeIndex)};
				js.updates(str.toString(), obj);
				if((jlJqWeekTime.getJqWeek()!=null && jlJqWeekTime.getJqWeek().intValue()!=Integer.parseInt(selectedWeek))){
					Calendar c = Calendar.getInstance();   
					SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
					c  =  Calendar.getInstance(Locale.CHINESE);   
					String loginTime = simpleDateTimeFormat.format(c.getTime());
					String weekDay="";
					String yweekDay="";
					switch (Integer.parseInt(selectedWeek)){
					case 0:
						weekDay="星期天";
						break;
					case 1:
						weekDay="星期一";
						break;
					case 2:
						weekDay="星期二";
						break;
					case 3:
						weekDay="星期三";
						break;
					case 4:
						weekDay="星期四";
						break;
					case 5:
						weekDay="星期五";
						break;
					case 6:
						weekDay="星期六";
						break;
					}
					switch (jlJqWeekTime.getJqWeek()){
					case 0:
						yweekDay="星期天";
						break;
					case 1:
						yweekDay="星期一";
						break;
					case 2:
						yweekDay="星期二";
						break;
					case 3:
						yweekDay="星期三";
						break;
					case 4:
						yweekDay="星期四";
						break;
					case 5:
						yweekDay="星期五";
						break;
					case 6:
						yweekDay="星期六";
						break;
					}
					StringBuffer str2=new StringBuffer("监区编号为"+jlJqWeekTime.getJqWeek()+"会见星期由"+yweekDay+"修改为"+weekDay);
					SysLog sl=new SysLog();
					sl.setType("正常");
					sl.setLogTime(loginTime);
					sl.setUserName(user.getUserName());
					sl.setUserNo(user.getUserNo());
					sl.setUserIp(request.getRemoteAddr());
					sl.setModel("监区设置");
					sl.setInfo(str2.toString());
					sl.setOp("修改监区会见时间");
					js.save(sl);
				}
				JSONArray json=JSONArray.fromObject(Integer.parseInt(webId));
				response.getWriter().println(json.toString());
			}
			return null;
	}
	//删除监区的亲情电话使用时间
	public ActionForward delJqWeek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
		    String timeIndex=request.getParameter("timeIndex");
		    JlHjJqWeek jlJqWeekTime=(JlHjJqWeek)js.findById(JlHjJqWeek.class, Integer.parseInt(timeIndex));
		    js.delete(JlHjJqWeek.class, Integer.parseInt(timeIndex));
		    Calendar c = Calendar.getInstance();   
	    	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
	    	c  =  Calendar.getInstance(Locale.CHINESE);   
	    	String loginTime = simpleDateTimeFormat.format(c.getTime());
	    	String weekDay="";
	    	switch (jlJqWeekTime.getJqWeek()) {
				case 0:
					weekDay="星期天";
					break;
				case 1:
					weekDay="星期一";
					break;
				case 2:
					weekDay="星期二";
					break;
				case 3:
					weekDay="星期三";
					break;
				case 4:
					weekDay="星期四";
					break;
				case 5:
					weekDay="星期五";
					break;
				case 6:
					weekDay="星期六";
					break;
			}
		    SysLog sl=new SysLog();
	    	sl.setType("严重");
	    	sl.setLogTime(loginTime);
	    	sl.setUserName(user.getUserName());
	    	sl.setUserNo(user.getUserNo());
	    	sl.setUserIp(request.getRemoteAddr());
	    	sl.setModel("监区设置");
	    	sl.setInfo("删除监区会见时间（监区编号："+jlJqWeekTime.getJqNo()+"  星期："+weekDay+")");
	    	sl.setOp("删除监区会见时间");
	    	js.save(sl);
		    String jqNo=request.getParameter("jqNo");
		    String hql="from JlJq where jqNo='"+jqNo+"'";
		    List list=js.searchAll(hql);
		    if(list.size()>0){
		    	JlJq jlJq=(JlJq)list.get(0);
		    	request.setAttribute("jlJq", jlJq);
		    }
		    return mapping.findForward("delSuccessJqWeek");
	}
}

package com.slxt.rs.action;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.slxt.rs.model.JlJb;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.GradeMessageService;

public class GradeMessageAction extends DispatchAction{
    private GradeMessageService gm;

	public void setGm(GradeMessageService gm) {
		this.gm = gm;
	}
	//查询级别
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   	SysUser user=(SysUser)request.getSession().getAttribute("users");
		   	if(user==null){
		   		return mapping.findForward("sessionFailure");
		   	}
		   	String hql="from JlJb";
		   	List<JlJb> list=gm.searchAll(hql);
		   	request.setAttribute("jlJbList", list);
		   	return mapping.findForward("jlJbMain");
	}
	//增加级别
	public ActionForward addJlJb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hql="from JlJb";
		   	List<JlJb> list=gm.searchAll(hql);
		   	request.setAttribute("jlJbList", list);
	        return mapping.findForward("addJlJb");
	}
	//增加保存级别
	public ActionForward addSavejlJb(ActionMapping mapping, ActionForm form,
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
			sl.setType("正常");
			sl.setLogTime(loginTime);
			sl.setUserName(user.getUserName());
			sl.setUserNo(user.getUserNo());
			sl.setUserIp(request.getRemoteAddr());
		   	sl.setModel("罪犯级别");
		   	String jbNo = java.net.URLDecoder.decode((String)request.getParameter("jbNo"),"UTF-8") ;
		   	String jbName = java.net.URLDecoder.decode((String)request.getParameter("jbName"),"UTF-8") ;
//		   	String qqCount =request.getParameter("qqCount");
//		   	String qqTime =request.getParameter("qqTime");
		   	String hjCount =request.getParameter("hjCount");
		   	String hjTime =request.getParameter("hjTime");
		   	String recordOverTime =request.getParameter("recordOverTime");
		   	StringBuffer str =new StringBuffer("from JlJb jl where jl.jbNo='");
		   	str.append(jbNo+"'");
		   	List list1=gm.searchAll(str.toString());
		   	if(list1.size()>0 || (Integer.parseInt(hjTime)<=0 || Integer.parseInt(hjCount)<=-1 || Integer.parseInt(recordOverTime)<=0)){
		   		List flag=new ArrayList();
		   		if(list1.size()>0){
		   			flag.add(0);
		   			JSONArray json=JSONArray.fromObject(flag);
		   			response.getWriter().println(json.toString());
		   		}else{
		   			flag.add(1);
		   			JSONArray json=JSONArray.fromObject(flag);
		   			response.getWriter().println(json.toString());
		   		}
		   	}else{
		   		StringBuffer str1=new StringBuffer("");
		   		JlJb jlJb=new JlJb();
		   		jlJb.setJbNo(jbNo);
		   		str1.append("增加级别 （级别编号："+jbNo);
		   		if(jbName!="" && jbName!=null){
		   			jlJb.setJbName(jbName);
		   			str1.append(" 级别名称:"+jbName);
		   		}
		   		jlJb.setQqCount(0);
		   		if(Integer.parseInt(hjCount)==-1){
		   			str1.append(" 每月会见次数为无限制");
		   		}else{
		   			str1.append(" 每月会见次数："+Integer.parseInt(hjCount)+"次");
		   		}
		   		jlJb.setQqTime(0);
		   		if(Integer.parseInt(hjTime)==-1){
		   			str1.append(" 每次会见时长为无限制");
		   		}else{
		   			str1.append(" 每次会见时长："+Integer.parseInt(hjTime)+"分钟");
		   		}
		   		if(Integer.parseInt(recordOverTime)==-1){
		   			str1.append(" 复听录音超时时间为无限制");
		   		}else{
		   			str1.append(" 复听录音超时时间："+Integer.parseInt(recordOverTime)+"天");
		   		}
		   		str1.append("）");
		   		jlJb.setHjCount(Integer.parseInt(hjCount));
		   		jlJb.setHjTime(Integer.parseInt(hjTime));
		   		jlJb.setRecordOverTime(Integer.parseInt(recordOverTime));
		   		jlJb.setAutoDown(0);
		   		jlJb.setDownTime(1);
		   		jlJb.setHjQy(0);
		   		gm.save(jlJb);
		   		sl.setInfo(str1.toString());
		   		sl.setOp("增加罪犯级别");
			   	gm.save(sl);
			   	JSONArray json=JSONArray.fromObject(null);
			   	response.getWriter().println(json);
		   	}
		   	return null;
	}
	//删除级别
	public ActionForward delejlJb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    Calendar c = Calendar.getInstance();   
		    SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		    c  =  Calendar.getInstance(Locale.CHINESE);   
		    String loginTime = simpleDateTimeFormat.format(c.getTime());
		    SysLog sl=new SysLog();
		    sl.setUserName(user.getUserName());
		    sl.setUserNo(user.getUserNo());
		    sl.setUserIp(request.getRemoteAddr());
		    sl.setLogTime(loginTime);
		    sl.setModel("罪犯级别");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String jlJbId=request.getParameter("jlJbId");
		    JlJb jlJb=(JlJb)gm.findById(JlJb.class, Integer.parseInt(jlJbId));
		    sl.setType("严重");
		    sl.setInfo("删除级别（级别编号："+jlJb.getJbNo()+" 级别名称："+jlJb.getJbName()+"）");
		    sl.setOp("删除罪犯级别");
		    gm.delete(JlJb.class, Integer.parseInt(jlJbId));
		    gm.save(sl);
		    return mapping.findForward("delSuccess");
	}
	//修改级别
	public ActionForward checkjlJb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String jlJbId=request.getParameter("jlJbId");
		    JlJb jlJb=(JlJb)gm.findById(JlJb.class, Integer.parseInt(jlJbId));
		    request.setAttribute("jlJb",jlJb);
		    String hql="from JlJb where jbNo!="+jlJb.getJbNo();
		    List<JlJb> list=gm.searchAll(hql);
		    request.setAttribute("listJlJb",list);
		    return mapping.findForward("updateJlJb");
	}
	//修改保存级别
	public ActionForward updateSavejlJb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId = request.getParameter("webId") ;
			String jbName = java.net.URLDecoder.decode((String)request.getParameter("jbName"),"UTF-8") ;
//			String qqCount =request.getParameter("qqCount");
//			String qqTime =request.getParameter("qqTime");
			String hjCount =request.getParameter("hjCount");
			String hjTime =request.getParameter("hjTime");
			String recordOverTime =request.getParameter("recordOverTime");
			if(Integer.parseInt(hjTime)<=0 || Integer.parseInt(hjCount)<=-1 || Integer.parseInt(recordOverTime)<=0){
				JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
			}else{
				JlJb jlJb=(JlJb)gm.findById(JlJb.class, Integer.parseInt(webId));
				StringBuffer str1=new StringBuffer();
				StringBuffer str=new StringBuffer("update JlJb jl set ");
//				str.append("jl.qqCount="+Integer.parseInt(qqCount)+",");
//				str.append("jl.qqTime="+Integer.parseInt(qqTime)+",");
				str.append("jl.hjCount="+Integer.parseInt(hjCount)+",");
				str.append("jl.recordOverTime="+Integer.parseInt(recordOverTime)+",");
				if(jbName!=""){
					str.append("jl.jbName='"+jbName+"',");
				}else{
					str.append("jl.jbName='',");
				}
				str.append("jl.hjTime="+Integer.parseInt(hjTime));
				str.append(" where jl.webId=?");
				Object[] obj={Integer.parseInt(webId)};
				if(jlJb.getHjCount()!=Integer.parseInt(hjCount) || jlJb.getHjTime()!=Integer.parseInt(hjTime) || (jbName!=null && !jlJb.getJbName().equals(jbName.trim()))){
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
					sl.setModel("罪犯级别");
					str1.append("级别编号为"+jlJb.getJbNo()+"的");
					if(jlJb.getHjCount()!=Integer.parseInt(hjCount)){
						str1.append(" 每月会见次数由原来"+jlJb.getHjCount()+"次修改为"+Integer.parseInt(hjCount)+"次");
					}
					if(jlJb.getHjTime()!=Integer.parseInt(hjTime)){
						str1.append(" 每月会见时长由原来"+jlJb.getHjTime()+"分钟修改为"+Integer.parseInt(hjTime)+"分钟");
					}
					if(jlJb.getRecordOverTime()!=Integer.parseInt(recordOverTime)){
						str1.append(" 复听录音超时时间由原来"+jlJb.getRecordOverTime()+"天修改为"+Integer.parseInt(recordOverTime)+"天");
					}
					if(jbName!=null && !jlJb.getJbName().equals(jbName.trim())){
						str1.append(" 级别名称由原来"+jlJb.getJbName()+"修改为"+jbName);
					}
					sl.setInfo(str1.toString());
					sl.setOp("修改罪犯级别");
					gm.save(sl);
				}
				gm.updates(str.toString(),obj);
				JSONArray json=JSONArray.fromObject(obj);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	//复位亲情电话次数
	public ActionForward fwQqCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
		    }
		    int i=gm.fw("reset_hj_count_by_server");
		    List<Integer> list=new ArrayList<Integer>();
		    list.add(i);
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
		    sl.setModel("罪犯级别");
		    sl.setInfo("复位会见次数");
		    sl.setOp("复位会见次数");
		    gm.save(sl);
		    JSONArray json=JSONArray.fromObject(list);
		    response.getWriter().println(json.toString());
		    return null;
	}
	//强制复位亲情电话次数
	public ActionForward qzfwQqCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
		    }
			gm.saveProcess("qzreset_qq_count");
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
			sl.setModel("罪犯级别");
			sl.setInfo("强制复位会见次数");
			sl.setOp("强制复位会见次数");
			gm.save(sl);
			return mapping.findForward("qzfwQqCountSuccess");
	}
	//批量增加级别次数
	public ActionForward plAddQqCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		    String hql="from JlJb";
		    List<JlJb> list=gm.searchAll(hql);
		    request.setAttribute("jlJbList", list);
		    return mapping.findForward("plAddQqCount");
	}
	//批量增加保存级别次数
	public ActionForward plAddSaveQqCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String jljb=request.getParameter("jljB");
			String addQqcount=request.getParameter("addQqcount");
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
			sl.setModel("级别管理");
			sl.setInfo("级别编号为"+jljb+"的所有犯人会见次数都增加"+addQqcount+"次");
			sl.setOp("批量增加会见次数");
			gm.save(sl);
			String hql="update JlFr set hjLeft=qqLeft+"+Integer.parseInt(addQqcount)+" where jbNo=?";
			Object[] obj={jljb};
			gm.updates(hql, obj);
			return mapping.findForward("plAddSuccess");
	}
}

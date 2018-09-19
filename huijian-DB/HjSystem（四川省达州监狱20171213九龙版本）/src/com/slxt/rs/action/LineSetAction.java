package com.slxt.rs.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.slxt.rs.model.SysHjLine;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.LineSetService;
import com.slxt.rs.vo.LineVo;
public class LineSetAction extends DispatchAction{
	private LineSetService ls;

	public void setLs(LineSetService ls) {
		this.ls = ls;
	}
	//查询线路设置
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String sql="select line.Line_No,line.Server_Name,line.Line,line.dkq,line.ZW,line.webId,card.CardNo,line.State from (select line.Line_No,server.Server_Name,line.Line,line.dkq,line.ZW,line.webId,line.State from SYS_HJ_LINE line,SYS_HJ_SERVER server where line.JY=server.Server_Name) as line left join SYS_HJ_LINE_CARD card on line.Line_No=card.Line_No and line.Server_Name=card.JY";
		    List list=ls.searchAllBySql(sql);
		    Iterator it=list.iterator();
		    List<LineVo> list1=new ArrayList();
		    while(it.hasNext()){
		    	Object[] obj=(Object[])it.next();
		    	LineVo lv=new LineVo();
		    	lv.setLineNo(obj[0].toString());
		    	lv.setJy(obj[1].toString());
		    	if(obj[2]!=null && obj[2].toString()!=""){
		    		lv.setLine(obj[2].toString());
		    	}
		    	if(obj[3]!=null && obj[3].toString()!=""){
		    		lv.setDkq(obj[3].toString());
		    	}
		    	if(obj[4]!=null && obj[4].toString()!=""){
		    		lv.setZw(obj[4].toString());
		    	}
		    	lv.setWebId(obj[5].toString());
		    	if(obj[6]!=null && obj[6].toString()!=""){
		    		lv.setCardNo(obj[6].toString());
		    	}
		    	lv.setState(obj[7].toString());
		    	list1.add(lv);
		    }
		    request.setAttribute("sysQqLineList", list1);
		    return mapping.findForward("lineSetMain");
	}
	//修改线路设置
	public ActionForward checkLine(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		  	String lineId=request.getParameter("lineId");
		  	SysHjLine sysQqLine=(SysHjLine)ls.findById(SysHjLine.class, Integer.parseInt(lineId));
		  	StringBuffer str=new StringBuffer(" from SysHjServer sqs where sqs.serverName!='");
		  	str.append(sysQqLine.getJy()+"'");
		  	List<SysHjLine> list=ls.searchAll(str.toString());
		  	request.setAttribute("sysQqLine", sysQqLine);
		  	request.setAttribute("sysQqServerList", list);
		  	return mapping.findForward("updateLineSet");
	}
	//修改保存线路设置
	public ActionForward updateSaveLine(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String lineId=request.getParameter("lineId");
			SysHjLine sysHjLine=(SysHjLine)ls.findById(SysHjLine.class, Integer.parseInt(lineId));
			String jy = java.net.URLDecoder.decode((String)request.getParameter("jy"),"UTF-8") ;
			String zw = java.net.URLDecoder.decode((String)request.getParameter("zw"),"UTF-8") ;
			String dkq = java.net.URLDecoder.decode((String)request.getParameter("dkq"),"UTF-8") ;
			String state=request.getParameter("state");
			StringBuffer str=new StringBuffer("update SysHjLine sql set ");
			if(dkq.trim()!=""){
				str.append("sql.dkq='"+dkq+"',");
			}else{
				str.append("sql.dkq='',");
			}
			str.append("sql.state="+Integer.parseInt(state)+",");
			str.append("sql.jy='"+jy+"',");
			if(zw.trim()!=""){
				str.append("sql.zw='"+zw+"'");
			}else{
				str.append("sql.zw=''");
			}
			if((sysHjLine.getZw()!=null && !sysHjLine.getZw().equals(zw)) || (sysHjLine.getDkq()!=null && !sysHjLine.getDkq().equals(dkq.toString()))){
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
				sl.setModel("线路设置");
				StringBuffer str1=new StringBuffer("线路逻辑号为"+sysHjLine.getLineNo());
				if(sysHjLine.getZw()!=null && !sysHjLine.getZw().equals(zw)){
					str1.append(" 座位名称"+sysHjLine.getZw()+"修改为"+zw);
				}
				if(sysHjLine.getDkq()!=null && !sysHjLine.getDkq().equals(dkq.toString())){
					str1.append(" 读卡器IP"+sysHjLine.getDkq()+"修改为"+dkq);
				}
			    sl.setInfo(str1.toString());
				sl.setOp("修改线路设置");
				ls.save(sl);
			}
			str.append(" where sql.webId=?");
			Object[] obj={Integer.parseInt(lineId)};
			ls.updates(str.toString(), obj);
			JSONArray json=JSONArray.fromObject(null);
			response.getWriter().println(json.toString());
			return null;
		}
	public  ActionForward lineCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String lineNo=request.getParameter("lineNo");
			String jy=request.getParameter("jy");
			String sql="select CardNo,jy from SYS_HJ_LINE_CARD where Line_No="+lineNo+" and jy='"+jy+"'";
			List list= ls.searchAllBySql(sql);
			if(list.size()>0){
				Object[] obj=(Object[])list.get(0);
				request.setAttribute("cardNo", obj[0].toString());
				request.setAttribute("op", 1);
			}else{
				request.setAttribute("op", 0);
			}
			request.setAttribute("lineNo", lineNo);
			request.setAttribute("jy", jy);
			return mapping.findForward("updateCardNo");
	}
	public  ActionForward updateSaveCardNo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String lineNo=request.getParameter("lineNo");
			String jy=request.getParameter("jy");
			String op=request.getParameter("op");
			String cardNo=request.getParameter("cardNo");
			if(op.equals("0")){
				String sql="insert into SYS_HJ_LINE_CARD(Line_No,JY,CardNo) values("+lineNo+",'"+jy+"','"+cardNo+"')";
				ls.executeUpdate(sql);
			}else{
				String sql="update SYS_HJ_LINE_CARD set CardNo='"+cardNo+"' where Line_No="+lineNo+" and JY='"+jy+"'";
				ls.executeUpdate(sql);
			}
			JSONArray json=JSONArray.fromObject(null);
			response.getWriter().println(json.toString());
			return null;
	}
}

package com.slxt.rs.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.slxt.rs.form.DiaryMessageForm;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysMenu;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.DiaryMessageService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.Page;

public class DiaryMessageAction extends DispatchAction{
    private DiaryMessageService dms;
	public void setDms(DiaryMessageService dms) {
		this.dms = dms;
	}
	//查询日志
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			Page page=new Page();
			page.setPageNo(1);
			String logBegin="";
			String logEnd="";
			DiaryMessageForm dmf=(DiaryMessageForm)form;
			String callTimeStart3 = request.getParameter("callTimeStart3");
			String callTimeEnd3 = request.getParameter("callTimeEnd3");
			String userIp3 = request.getParameter("userIp3");
			String userName3 = request.getParameter("userName3");
			String userNo3 = request.getParameter("userNo3");
			String info3 = request.getParameter("info3");
			String op3 = request.getParameter("op3");
			String model2 = request.getParameter("model2");
			String type2 = request.getParameter("type2");
			String pageNo3=request.getParameter("pageNo3");
			if(type2!=null && !type2.equals("null")){
				dmf.setType(type2);
			}
			if(model2!=null  && !model2.equals("null")){
				dmf.setModel(model2);
			}
			if(pageNo3!=null && !pageNo3.equals("-1")){
				page.setPageNo(Integer.parseInt(pageNo3));
			}
			if(callTimeStart3!=null && !callTimeStart3.trim().equals("")){
				dmf.setCallTimeStart(callTimeStart3);
			}
			if(callTimeEnd3!=null && !callTimeEnd3.trim().equals("")){
				dmf.setCallTimeEnd(callTimeEnd3);
			}
			if(op3!=null && !op3.trim().equals("")){
				dmf.setOp(op3);
			}
			if(info3!=null && !info3.trim().equals("")){
				dmf.setInfo(info3);
			}
			if(userIp3!=null && !userIp3.trim().equals("")){
				dmf.setUserIp(userIp3);
			}
			if(userNo3!=null && !userNo3.trim().equals("")){
				dmf.setUserNo(userNo3);
			}
			if(userName3!=null && !userName3.trim().equals("")){
				dmf.setUserName(userName3);
			}
			if(dmf.getCallTimeStart()!=null || dmf.getCallTimeEnd()!=null || dmf.getModel()!=null || dmf.getInfo()!=null || dmf.getOp()!=null || dmf.getType()!=null || dmf.getUserIp()!=null || dmf.getUserName()!=null || dmf.getUserNo()!=null || (pageNo3!=null && !pageNo3.equals("-1"))){
				StringBuffer str=new StringBuffer(" from SysLog sl where 1=1");
				if(dmf.getCallTimeStart()!=null && dmf.getCallTimeStart()!=""){
					str.append(" and sl.logTime>='"+dmf.getCallTimeStart()+"'");
					logBegin=dmf.getCallTimeStart();
				}
				if(dmf.getCallTimeEnd()!=null && dmf.getCallTimeEnd()!=""){
					str.append(" and sl.logTime<='"+dmf.getCallTimeEnd()+"'");
					logEnd=dmf.getCallTimeEnd();
				}
				if(dmf.getModel()!=null && !dmf.getModel().equals("null")){
					str.append(" and sl.model ='"+dmf.getModel()+"'");
				}
				if(dmf.getInfo()!=null && !dmf.getInfo().trim().equals("")){
					str.append(" and sl.info like '%"+dmf.getInfo()+"%'");
				}
				if(dmf.getOp()!=null && !dmf.getOp().trim().equals("")){
					str.append(" and sl.op like '%"+dmf.getOp()+"%'");
				}
				if(dmf.getType()!=null && !dmf.getType().equals("null")){
					str.append(" and sl.type ='"+dmf.getType()+"'");
				}
				if(dmf.getUserIp()!=null && !dmf.getUserIp().trim().equals("")){
					str.append(" and sl.userIp ='"+dmf.getUserIp()+"'");
				}
				if(dmf.getUserNo()!=null && !dmf.getUserNo().trim().equals("")){
					str.append(" and sl.userNo ='"+dmf.getUserNo()+"'");
				}
				if(dmf.getUserName()!=null && !dmf.getUserName().trim().equals("")){
					str.append("and sl.userName like '%"+dmf.getUserName()+"%'");
				}
				str.append(" order By sl.logTime DESC");
				Object[] obj={};
				Map map=dms.searchToPage(str.toString(), page.getPageNo(), 20, obj);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				page.setPageSize(20);
				List<SysLog> list=(List)map.get(Constant.RESULTLIST);
				Iterator it=list.iterator();
				while(it.hasNext()){
					SysLog sysLog=(SysLog)it.next();
					page.getList().add(sysLog);
				}
			}else{
				Date date=new Date(System.currentTimeMillis());
			    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			    String now=df1.format(date);
			    StringBuffer str=new StringBuffer(now.substring(0,10));
			    str.append(" 00:00:00");
				logBegin=str.toString();
				StringBuffer str1=new StringBuffer(now.substring(0,10));
				str1.append(" 23:59:59");
				logEnd=str1.toString();
			}
			String hql="from SysMenu";
			List<SysMenu> sysMenuList=dms.searchAll(hql);
			request.setAttribute("page", page);
			request.setAttribute("sysMenuList", sysMenuList);
			request.setAttribute("logEnd", logEnd);
			request.setAttribute("logBegin", logBegin);
			return mapping.findForward("diaryMessageMain");
	}
	//分页查询
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			Page page=new Page();
			page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			String logBegin="";
			String logEnd="";
			DiaryMessageForm dmf=(DiaryMessageForm)form;
			if(dmf.getCallTimeStart()!=null || dmf.getCallTimeEnd()!=null || dmf.getModel()!=null || dmf.getInfo()!=null || dmf.getOp()!=null || dmf.getType()!=null || dmf.getUserIp()!=null || dmf.getUserName()!=null || dmf.getUserNo()!=null){
				StringBuffer str=new StringBuffer(" from SysLog sl where 1=1");
				if(dmf.getCallTimeStart()!=null && dmf.getCallTimeStart()!=""){
					str.append(" and sl.logTime>='"+dmf.getCallTimeStart()+"'");
					logBegin=dmf.getCallTimeStart();
				}
				if(dmf.getCallTimeEnd()!=null && dmf.getCallTimeEnd()!=""){
					str.append(" and sl.logTime<='"+dmf.getCallTimeEnd()+"'");
					logEnd=dmf.getCallTimeEnd();
				}
				if(dmf.getModel()!=null && !dmf.getModel().equals("null")){
					str.append(" and sl.model ='"+dmf.getModel()+"'");
				}
				if(dmf.getInfo()!=null && !dmf.getInfo().trim().equals("")){
					str.append(" and sl.info like '%"+dmf.getInfo()+"%'");
				}
				if(dmf.getOp()!=null && !dmf.getOp().trim().equals("")){
					str.append(" and sl.op like '%"+dmf.getOp()+"%'");
				}
				if(dmf.getType()!=null && !dmf.getType().equals("null")){
					str.append(" and sl.type ='"+dmf.getType()+"'");
				}
				if(dmf.getUserIp()!=null && !dmf.getUserIp().trim().equals("")){
					str.append(" and sl.userIp ='"+dmf.getUserIp()+"'");
				}
				if(dmf.getUserNo()!=null && !dmf.getUserNo().trim().equals("")){
					str.append(" and sl.userNo ='"+dmf.getUserNo()+"'");
				}
				if(dmf.getUserName()!=null && !dmf.getUserName().trim().equals("")){
					str.append("and sl.userName like '%"+dmf.getUserName()+"%'");
				}
				str.append(" order By sl.logTime DESC");
				Object[] obj={};
				Map map=dms.searchToPage(str.toString(), page.getPageNo(), 20, obj);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				page.setPageSize(20);
				List<SysLog> list=(List)map.get(Constant.RESULTLIST);
				Iterator it=list.iterator();
				while(it.hasNext()){
					SysLog sysLog=(SysLog)it.next();
					page.getList().add(sysLog);
				}
			}else{
				Date date=new Date(System.currentTimeMillis());
			    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			    String now=df1.format(date);
			    StringBuffer str=new StringBuffer(now.substring(0,10));
			    str.append(" 00:00:00");
				logBegin=str.toString();
				StringBuffer str1=new StringBuffer(now.substring(0,10));
				str1.append(" 23:59:59");
				logEnd=str1.toString();
			}
			String hql="from SysMenu";
			List<SysMenu> sysMenuList=dms.searchAll(hql);
			request.setAttribute("page", page);
			request.setAttribute("sysMenuList", sysMenuList);
			request.setAttribute("logEnd", logEnd);
			request.setAttribute("logBegin", logBegin);
			return mapping.findForward("diaryMessageMain");
	}
	//查看日志详细信息
	public ActionForward diaryXiangxi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String logId=request.getParameter("logId");
			StringBuffer str=new StringBuffer("from SysLog sl where sl.logId=");
			str.append(Long.parseLong(logId));
			List<SysLog> list=dms.searchAll(str.toString());
			SysLog sysLog=(SysLog)list.get(0);
			request.setAttribute("sysLog", sysLog);
			String op3 = java.net.URLDecoder.decode((String)request.getParameter("op3"),"UTF-8");
			String info3 = java.net.URLDecoder.decode((String)request.getParameter("info3"),"UTF-8");
			String userNo3 = java.net.URLDecoder.decode((String)request.getParameter("userNo3"),"UTF-8");
			String userName3 = java.net.URLDecoder.decode((String)request.getParameter("userName3"),"UTF-8");
			String userIp3 = java.net.URLDecoder.decode((String)request.getParameter("userIp3"),"UTF-8");
			String callTimeEnd3 = request.getParameter("callTimeEnd3");
			String callTimeStart3 =request.getParameter("callTimeStart3");
			String pageNo3=request.getParameter("pageNo3");
			String type2=request.getParameter("type2");
			String model2=request.getParameter("model2");
			request.setAttribute("op3", op3);
			request.setAttribute("pageNo3", pageNo3);
			request.setAttribute("info3", info3);
			request.setAttribute("userNo3", userNo3);
			request.setAttribute("userName3", userName3);
			request.setAttribute("userIp3", userIp3);
			request.setAttribute("callTimeEnd3", callTimeEnd3);
			request.setAttribute("callTimeStart3", callTimeStart3);
			request.setAttribute("type2", type2);
			request.setAttribute("model2", model2);
			return mapping.findForward("diaryXiangxiMain");
	} 
	
	
}

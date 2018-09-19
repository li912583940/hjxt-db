package com.slxt.rs.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.slxt.rs.form.CardMessageForm;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.JlYjABDoor;
import com.slxt.rs.model.SysHjServer;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.ABDoorMessageService;
import com.slxt.rs.svc.CardMessageService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.CardMessageVO;
import com.slxt.rs.vo.Page;

public class ABDoorMessagAction extends DispatchAction{
	private ABDoorMessageService abd;

	public void setAbd(ABDoorMessageService abd) {
		this.abd = abd;
	}

	//查询陪同警察表
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String hql="from JlYjABDoor";
		    List<JlYjABDoor> list=abd.searchAll(hql);
		    request.setAttribute("JlYjABDoorList", list);
		    return mapping.findForward("jlYjABDoorMain");
	}
	//修改陪同警察信息
	public ActionForward updateYjABDoor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String webId=request.getParameter("webId");
		    JlYjABDoor jlYjABDoor=(JlYjABDoor)abd.findById(JlYjABDoor.class, Integer.parseInt(webId));
		    request.setAttribute("jlYjABDoor", jlYjABDoor);
		    return mapping.findForward("updateJlYjABDoor");
	}
	//修改保存陪同警察信息
	public ActionForward saveYjABDoor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		  	String webId=request.getParameter("webId");
//		  	JlYjABDoor jlYjABDoor=(JlYjABDoor)abd.findById(JlYjABDoor.class,Integer.parseInt(webId));
//		  	String port=request.getParameter("port");
//		  	String audioPort=request.getParameter("audioPort");
		  	String yjName = java.net.URLDecoder.decode((String)request.getParameter("yjName"),"UTF-8") ;
//		  	String recordURL = java.net.URLDecoder.decode((String)request.getParameter("recordURL"),"UTF-8") ;
//		  	StringBuffer str3=new StringBuffer("from JlHjIcCard ic where ic.icCardNo='");
//		    str3.append(icCardNo+"'");
//		    List list=abd.searchAll(str3.toString());
//		    if(list.size()>0){
//		    	JSONArray json=JSONArray.fromObject(null);
//		    	response.getWriter().println(json.toString());
//		    }else{
		  	
	    	StringBuffer str=new StringBuffer("update JlYjABDoor set yjName='");
		  	str.append(yjName+"'");

		  	str.append(" where webId=?");
		  	Object[] obj={Integer.parseInt(webId)};
		  	abd.updates(str.toString(), obj);
		  	
		  	JSONArray json=JSONArray.fromObject(1);
		  	response.getWriter().println(json.toString());
//		    }
		  	
		  	return null;
	}
	//增加陪同警察
	public ActionForward addYjABDoor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    return mapping.findForward("addYjABDoor");
	}
	//增加保存警察
	public ActionForward addSaveYjABDoor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String yjNo = java.net.URLDecoder.decode((String)request.getParameter("yjNo"),"UTF-8") ;
		    String yjName = java.net.URLDecoder.decode((String)request.getParameter("yjName"),"UTF-8") ;
		    
		    StringBuffer str=new StringBuffer("from JlYjABDoor jl where jl.yjNo='");
		    str.append(yjNo+"'");
		    List list=abd.searchAll(str.toString());
		    if(list.size()>0){
		    	JSONArray json=JSONArray.fromObject(null);
		    	response.getWriter().println(json.toString());
		    }else{
		    	JlYjABDoor jl=new JlYjABDoor();
		    	jl.setYjNo(yjNo);
		    	if(yjName!=""&&yjName!=null){
		    		jl.setYjName(yjName);
		    	}

				abd.save(jl);
				JSONArray json=JSONArray.fromObject(jl);
				response.getWriter().println(json.toString());
		    }
		    return null;
	}
	//删除警察
	public ActionForward deleteYjABDoor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
			String webId=request.getParameter("webId");

			abd.delete(JlYjABDoor.class, Integer.parseInt(webId));
			return mapping.findForward("delSuceess");
	}
}

package com.slxt.rs.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.slxt.rs.form.UserForm;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.model.SysUserGroup;
import com.slxt.rs.svc.UserService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.Page;
import com.slxt.rs.vo.UsGroup;
public class UserAction extends DispatchAction{
	private UserService us;
	public void setUs(UserService us) {
		this.us = us;
	}
  //查询所有用户
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    Page page=new Page();
		    page.setPageNo(1);
		    Object[] Object={};
		    if(user.getIsSuper()==1){
		    	UserForm userForm=(UserForm)form;
		    	if(userForm.getUserNo()!=null && userForm.getUserName()!=null && userForm.getDePtName()!=null && userForm.getUserGroup()!=null){
		    		StringBuffer str=new StringBuffer("select su.webId,su.user_No,su.user_Name,su.user_Pwd,su.user_Depart,su.group_No,sug.group_Name,su.Is_Sp,su.Is_Bj from sys_user su,sys_user_group sug where su.group_No=sug.group_No and su.is_Super !=1");
			    	if(userForm.getUserNo()!=null && !userForm.getUserNo().trim().equals("")){
			    		str.append(" and su.user_No='"+userForm.getUserNo()+"'");
			    	}
			    	if(userForm.getUserName()!=null && !userForm.getUserName().trim().equals("")){
			    		str.append(" and su.user_Name like '%"+userForm.getUserName()+"%'");
			    	}
			    	if(userForm.getDePtName()!=null && !userForm.getDePtName().equals("null")){
			    		str.append(" and su.user_Depart like '%"+userForm.getDePtName()+"%'");
			    	}
			    	if(userForm.getUserGroup()!=null && !userForm.getUserGroup().equals("null")){
			    		str.append(" and su.group_No='"+userForm.getUserGroup()+"'");
			    	}
			    	str.append(" order by su.user_Depart,su.group_No,su.user_No");
		    		Map map=us.searchToPageBySql(str.toString(), page.getPageNo(), 20, Object);
			    	page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
			    	page.setPageSize(20);
			    	List list=(List)map.get(Constant.RESULTLIST);
			    	Iterator it=list.iterator();
			    	while(it.hasNext()){
			    		Object[] obj=(Object[])it.next();
			    		UsGroup ug=new UsGroup();
			    		ug.setUser_Id(Integer.parseInt(obj[0].toString()));
			    		ug.setUser_No(obj[1].toString());
			    		if(obj[2]!=null && obj[2]!=""){
			    			ug.setUser_Name(obj[2].toString());
			    		}
			    		ug.setUser_Pwd(obj[3].toString());
			    		if(obj[4]!=null && obj[4]!=""){
			    			ug.setUser_Depart(obj[4].toString());
			    		}
			    		ug.setGroup_No(obj[5].toString());
			    		ug.setGroup_Name(obj[6].toString());
			    		ug.setIsSp(obj[7].toString());
			    		ug.setIsBj(obj[8].toString());
			    		page.getList().add(ug);
			    	}
		    	}
		    	String hql="from SysUserGroup";
		    	List<SysUserGroup> list=us.searchAll(hql);
		    	request.setAttribute("sysUserGroupList", list);
		    	String hql1="select distinct User_Depart from SYS_USER where User_Depart!=''";
			   	List list1=us.searchAllBySql(hql1);
			   	request.setAttribute("deptList", list1);
		    }else{
		    	UserForm userForm=(UserForm)form;
		    	if(userForm.getUserNo()!=null && userForm.getUserName()!=null && userForm.getDePtName()!=null && userForm.getUserGroup()!=null){
		    		StringBuffer str=new StringBuffer("select su.webId,su.user_No,su.user_Name,su.user_Pwd,su.user_Depart,su.group_No,sug.group_Name,su.Is_Sp,su.Is_Bj from sys_user su,sys_user_group sug where su.group_No=sug.group_No and su.is_Super!=1 and su.group_No !='");
			    	str.append(user.getGroupNo()+"'");
			    	if(userForm.getUserNo()!=null && !userForm.getUserNo().trim().equals("")){
			    		str.append(" and su.user_No='"+userForm.getUserNo()+"'");
			    	}
			    	if(userForm.getUserName()!=null && !userForm.getUserName().trim().equals("")){
			    		str.append(" and su.user_Name like '%"+userForm.getUserName()+"%'");
			    	}
			    	if(userForm.getDePtName()!=null && !userForm.getDePtName().equals("null")){
			    		str.append(" and su.user_Depart like '%"+userForm.getDePtName()+"%'");
			    	}
			    	if(userForm.getUserGroup()!=null && !userForm.getUserGroup().equals("null")){
			    		str.append(" and su.group_No='"+userForm.getUserGroup()+"'");
			    	}
			    	str.append(" order by su.user_Depart,su.group_No,su.user_No");
			    	Map map=us.searchToPageBySql(str.toString(), page.getPageNo(), 20, Object);
			    	page.setPageSize(20);
			    	page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
			    	List list=(List)map.get(Constant.RESULTLIST);
			    	Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						UsGroup ug=new UsGroup();
						ug.setUser_Id(Integer.parseInt(obj[0].toString()));
						ug.setUser_No(obj[1].toString());
						if(obj[2]!=null && obj[2]!=""){
							ug.setUser_Name(obj[2].toString());
						}
						ug.setUser_Pwd(obj[3].toString());
						if(obj[4]!=null && obj[4]!=""){
							ug.setUser_Depart(obj[4].toString());
						}
						ug.setGroup_No(obj[5].toString());
						ug.setGroup_Name(obj[6].toString());
						ug.setIsSp(obj[7].toString());
						ug.setIsBj(obj[8].toString());
						page.getList().add(ug);
					 }
			    }
		    	String hql="from SysUserGroup where isAdmin=0";
		    	List<SysUserGroup> list=us.searchAll(hql);
		    	request.setAttribute("sysUserGroupList", list);
		    	String hql1="select distinct User_Depart from SYS_USER where User_Depart!='' and Group_No!='Admin'";
			   	List list1=us.searchAllBySql(hql1);
			   	request.setAttribute("deptList", list1);
		    }
		    request.setAttribute("page", page);
		    return mapping.findForward("showUser");
	}
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    Page page=new Page();
		    page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		    Object[] Object={};
		    if(user.getIsSuper()==1){
		    	UserForm userForm=(UserForm)form;
		    	if(userForm.getUserNo()!=null && userForm.getUserName()!=null && userForm.getDePtName()!=null && userForm.getUserGroup()!=null){
		    		StringBuffer str=new StringBuffer("select su.webId,su.user_No,su.user_Name,su.user_Pwd,su.user_Depart,su.group_No,sug.group_Name,su.Is_Sp,su.Is_Bj from sys_user su,sys_user_group sug where su.group_No=sug.group_No and su.is_Super !=1");
			    	if(userForm.getUserNo()!=null && !userForm.getUserNo().trim().equals("")){
			    		str.append(" and su.user_No='"+userForm.getUserNo()+"'");
			    	}
			    	if(userForm.getUserName()!=null && !userForm.getUserName().trim().equals("")){
			    		str.append(" and su.user_Name like '%"+userForm.getUserName()+"%'");
			    	}
			    	if(userForm.getDePtName()!=null && !userForm.getDePtName().equals("null")){
			    		str.append(" and su.user_Depart like '%"+userForm.getDePtName()+"%'");
			    	}
			    	if(userForm.getUserGroup()!=null && !userForm.getUserGroup().equals("null")){
			    		str.append(" and su.group_No='"+userForm.getUserGroup()+"'");
			    	}
			    	str.append(" order by su.user_Depart,su.group_No,su.user_No");
		    		Map map=us.searchToPageBySql(str.toString(), page.getPageNo(), 20, Object);
			    	page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
			    	page.setPageSize(20);
			    	List list=(List)map.get(Constant.RESULTLIST);
			    	Iterator it=list.iterator();
			    	while(it.hasNext()){
			    		Object[] obj=(Object[])it.next();
			    		UsGroup ug=new UsGroup();
			    		ug.setUser_Id(Integer.parseInt(obj[0].toString()));
			    		ug.setUser_No(obj[1].toString());
			    		if(obj[2]!=null && obj[2]!=""){
			    			ug.setUser_Name(obj[2].toString());
			    		}
			    		ug.setUser_Pwd(obj[3].toString());
			    		if(obj[4]!=null && obj[4]!=""){
			    			ug.setUser_Depart(obj[4].toString());
			    		}
			    		ug.setGroup_No(obj[5].toString());
			    		ug.setGroup_Name(obj[6].toString());
			    		ug.setIsSp(obj[7].toString());
			    		ug.setIsBj(obj[8].toString());
			    		page.getList().add(ug);
			    	}
		    	}
		    	String hql="from SysUserGroup";
		    	List<SysUserGroup> list=us.searchAll(hql);
		    	request.setAttribute("sysUserGroupList", list);
		    	String hql1="select distinct User_Depart from SYS_USER where User_Depart!=''";
			   	List list1=us.searchAllBySql(hql1);
			   	request.setAttribute("deptList", list1);
		    }else{
		    	UserForm userForm=(UserForm)form;
		    	if(userForm.getUserNo()!=null && userForm.getUserName()!=null && userForm.getDePtName()!=null && userForm.getUserGroup()!=null){
		    		StringBuffer str=new StringBuffer("select su.webId,su.user_No,su.user_Name,su.user_Pwd,su.user_Depart,su.group_No,sug.group_Name,su.Is_Sp,su.Is_Bj from sys_user su,sys_user_group sug where su.group_No=sug.group_No and su.is_Super!=1 and su.group_No !='");
			    	str.append(user.getGroupNo()+"'");
			    	if(userForm.getUserNo()!=null && !userForm.getUserNo().trim().equals("")){
			    		str.append(" and su.user_No='"+userForm.getUserNo()+"'");
			    	}
			    	if(userForm.getUserName()!=null && !userForm.getUserName().trim().equals("")){
			    		str.append(" and su.user_Name like '%"+userForm.getUserName()+"%'");
			    	}
			    	if(userForm.getDePtName()!=null && !userForm.getDePtName().equals("null")){
			    		str.append(" and su.user_Depart like '%"+userForm.getDePtName()+"%'");
			    	}
			    	if(userForm.getUserGroup()!=null && !userForm.getUserGroup().equals("null")){
			    		str.append(" and su.group_No='"+userForm.getUserGroup()+"'");
			    	}
			    	str.append(" order by su.user_Depart,su.group_No,su.user_No");
			    	Map map=us.searchToPageBySql(str.toString(), page.getPageNo(), 20, Object);
			    	page.setPageSize(20);
			    	page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
			    	List list=(List)map.get(Constant.RESULTLIST);
			    	Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						UsGroup ug=new UsGroup();
						ug.setUser_Id(Integer.parseInt(obj[0].toString()));
						ug.setUser_No(obj[1].toString());
						if(obj[2]!=null && obj[2]!=""){
							ug.setUser_Name(obj[2].toString());
						}
						ug.setUser_Pwd(obj[3].toString());
						if(obj[4]!=null && obj[4]!=""){
							ug.setUser_Depart(obj[4].toString());
						}
						ug.setGroup_No(obj[5].toString());
						ug.setGroup_Name(obj[6].toString());
						ug.setIsSp(obj[7].toString());
						ug.setIsBj(obj[8].toString());
						page.getList().add(ug);
					 }
			    }
		    	String hql="from SysUserGroup where isAdmin=0";
		    	List<SysUserGroup> list=us.searchAll(hql);
		    	request.setAttribute("sysUserGroupList", list);
		    	String hql1="select distinct User_Depart from SYS_USER where User_Depart!='' and Group_No!='Admin'";
			   	List list1=us.searchAllBySql(hql1);
			   	request.setAttribute("deptList", list1);
		    }
		    request.setAttribute("page", page);
		    return mapping.findForward("showUser");
	}
	//删除用户
	public ActionForward delUser(ActionMapping mapping, ActionForm form,
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
		    sl.setModel("用户管理");
		    sl.setUserName(user.getUserName());
		    sl.setUserNo(user.getUserNo());
		    sl.setUserIp(request.getRemoteAddr());
		    String userId=request.getParameter("userId");
		    SysUser sysUser=(SysUser)us.findById(SysUser.class, Integer.parseInt(userId));
		    sl.setType("严重");
			sl.setInfo("删除用户（用户编号："+sysUser.getUserNo()+" 用户姓名："+sysUser.getUserName()+"）");
			sl.setOp("删除用户");
			us.save(sl);
		    us.delete(SysUser.class, Integer.parseInt(userId));
		    return mapping.findForward("delUserSuccess");
		    
	}
	//查询用户组
	public ActionForward checkUserGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	List<SysUserGroup> usGroup=new ArrayList();
		 	if(user.getIsSuper()==1){
		 		String sql="from SysUserGroup";
		 		usGroup=us.searchAll(sql);
		 	}else{
		 		StringBuffer str=new StringBuffer("from SysUserGroup sug where sug.groupNo !='");
		 		str.append(user.getGroupNo()+"'");
		 		usGroup=us.searchAll(str.toString());
		 	}
		 	request.setAttribute("usGroup", usGroup);
		 	return mapping.findForward("addUser");
	}
	//增加用户
	public ActionForward saveUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String userNo = java.net.URLDecoder.decode((String)request.getParameter("userNo"),"UTF-8") ;
		 	StringBuffer hql=new StringBuffer("from SysUser su where su.userNo='");
		 	hql.append(userNo+"'");
		 	List list1=us.searchAll(hql.toString());
		 	if(list1.size()>0){
		 		JSONArray json=JSONArray.fromObject(null);
		 		response.getWriter().println(json);
		 	}else{
		 		Calendar c = Calendar.getInstance();   
		 		SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 		c  =  Calendar.getInstance(Locale.CHINESE);   
		 		String loginTime = simpleDateTimeFormat.format(c.getTime());
		 		SysLog sl=new SysLog();
		 		sl.setType("正常");
		 		sl.setLogTime(loginTime);
		 		sl.setModel("用户管理");
		 		sl.setUserName(user.getUserName());
		 		sl.setUserNo(user.getUserNo());
		 		sl.setUserIp(request.getRemoteAddr());
		 		String usGroup = java.net.URLDecoder.decode((String)request.getParameter("usGroup"),"UTF-8") ;
		 		StringBuffer str= new StringBuffer("from SysUserGroup sug where sug.webId=");
		 		str.append(Integer.parseInt(usGroup));
		 		List<SysUserGroup> list=us.searchAll(str.toString());
		 		SysUserGroup sug=(SysUserGroup)list.get(0);
		 		SysUser su=new SysUser();
		 		String userDepart =java.net.URLDecoder.decode(request.getParameter("userDepart"),"UTF-8");
		 		su.setUserDepart(userDepart);
		 		String userName=java.net.URLDecoder.decode(request.getParameter("userName"),"UTF-8");
		 		StringBuffer str1=new StringBuffer("");
		 		su.setUserNo(userNo);
		 		str1.append("增加用户 （ 登录帐号："+userNo);
		 		if(userName.trim()!=null &&userName.trim().length()>0){
		 			su.setUserName(userName);
					str1.append(" 用户姓名："+userName);
		 		}
		 		su.setUserPwd("123456");
		 		su.setGroupNo(sug.getGroupNo());
		 		str1.append(" 所属用户组编号："+sug.getGroupNo()+"）");
		 		su.setIsSuper(0);
		 		su.setIsSp(0);
		 		su.setIsBj(0);
		 		us.save(su);
		 		sl.setInfo(str1.toString());
		 		sl.setOp("增加用户");
		 		us.save(sl);
		 		JSONArray json=JSONArray.fromObject(list);
		 		response.getWriter().println(json);
		 	}
		 	return null;
	}
	//修改用户
	public ActionForward sreachUserGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String groupNo = java.net.URLDecoder.decode((String)request.getParameter("groupNo"),"UTF-8") ;
			String userId = request.getParameter("userId"); 
			StringBuffer str=new StringBuffer("select su.webId,su.userNo,su.userName,su.userPwd,su.userDepart,su.groupNo,sug.groupName,su.isSp,su.isBj from SysUser su,SysUserGroup sug where su.groupNo=sug.groupNo and su.webId=");
			str.append(Integer.parseInt(userId));
			List suList=us.searchAll(str.toString());
			Object[] obj=(Object[])suList.get(0);
			UsGroup ug=new UsGroup();
			ug.setUser_Id(Integer.parseInt(obj[0].toString()));
			ug.setUser_No(obj[1].toString());
			if(obj[2]!=null && obj[2]!=""){
				ug.setUser_Name(obj[2].toString());
			}
			ug.setUser_Pwd(obj[3].toString());
			if(obj[4]!=null && obj[4]!=""){
				ug.setUser_Depart(obj[4].toString());
			}
			ug.setGroup_No(obj[5].toString());
			ug.setGroup_Name(obj[6].toString());
			ug.setIsSp(obj[7].toString());
			ug.setIsBj(obj[8].toString());
			List<SysUserGroup> sugList=new ArrayList();
			if(user.getIsSuper()==1){
				StringBuffer hql= new StringBuffer("from SysUserGroup sug where sug.groupNo !='");
				hql.append(groupNo+"'");
				sugList= us.searchAll(hql.toString());
			}else{
				StringBuffer hql= new StringBuffer("from SysUserGroup sug where sug.groupNo !='");
				hql.append(groupNo+"'");
				hql.append(" and sug.groupNo !='"+user.getGroupNo()+"'");
				sugList= us.searchAll(hql.toString());
			}
			request.setAttribute("ug", ug);
			request.setAttribute("sugList", sugList);
			return mapping.findForward("updateUser");
	}
	//修改保存用户
	public ActionForward updataSaveUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String userNo = java.net.URLDecoder.decode((String)request.getParameter("userNo"),"UTF-8") ;
			String arr = java.net.URLDecoder.decode((String)request.getParameter("usGroup"),"UTF-8") ;
			String userId=request.getParameter("userId");
			StringBuffer str=new StringBuffer("update SysUser su set");
			String userName=java.net.URLDecoder.decode(request.getParameter("userName"),"UTF-8");
			String userDepart=java.net.URLDecoder.decode(request.getParameter("userDepart"),"UTF-8");
			String isSp=request.getParameter("isSp");
			String isBj=request.getParameter("isBj");
			SysUser sysUser=(SysUser)us.findById(SysUser.class, Integer.parseInt(userId));
			StringBuffer str1=new StringBuffer("");
			if(userName!=null && userName.length()>0){
				str.append(" su.userName='"+userName+"',");
			}else{
				str.append(" su.userName='',");
			}
			if(userDepart!=null && userDepart.length()>0){
				str.append(" su.userDepart='"+userDepart+"',");
			}else{
				str.append(" su.userDepart='',");
			}
			str.append(" su.userNo='"+userNo+"',");
			str.append(" su.isSp='"+isSp+"',");
			str.append(" su.isBj='"+isBj+"',");
			str.append(" su.groupNo='"+arr+"' where su.webId =?");
			Object[] obj={Integer.parseInt(userId)};
			if((sysUser.getUserName()!=null && !sysUser.getUserName().equals(userName.trim())) || !sysUser.getGroupNo().equals(arr)){
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
				sl.setModel("用户管理");
				str1.append("用户编号为"+sysUser.getUserNo());
				if(!sysUser.getUserName().equals(userName.trim())){
					str1.append(" 用户姓名"+sysUser.getUserName()+"修改为"+userName);
				}
				if(!sysUser.getGroupNo().equals(arr)){
					str1.append(" 所属用户组编号"+sysUser.getGroupNo()+"修改为"+arr);
				}
				if(!sysUser.getIsSp().equals(isSp)){
					str1.append(" 审批家属状态"+sysUser.getIsSp()+"修改为"+isSp);
				}
				if(!sysUser.getIsBj().equals(isBj)){
					str1.append(" 帮教状态"+sysUser.getIsBj()+"修改为"+isBj);
				}
				sl.setInfo(str1.toString());
				sl.setOp("修改用户");
				us.save(sl);
			}
			us.updates(str.toString(), obj);
			JSONArray json=JSONArray.fromObject(null);
			response.getWriter().println(json);
			return null;
	}
	//重置密码
	public ActionForward resetPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
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
			sl.setModel("用户管理");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String userId=request.getParameter("userId");
			StringBuffer str=new StringBuffer("update SysUser su set su.userPwd='");
			str.append(123456+"' where su.webId=?");
			Object[] obj={Integer.parseInt(userId)};
			SysUser sysUser=(SysUser)us.findById(SysUser.class, Integer.parseInt(userId));
			sl.setInfo("用户登录帐号为"+sysUser.getUserNo()+"的密码被重置");
			sl.setOp("重置密码");
			us.save(sl);
			us.updates(str.toString(), obj);
			JSONArray json=JSONArray.fromObject(null);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().println(json.toString());
			return null;
	}
	
      
}

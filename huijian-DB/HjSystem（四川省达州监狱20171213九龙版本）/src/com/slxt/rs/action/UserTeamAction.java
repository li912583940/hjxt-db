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

import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysMenu;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.model.SysUserGroup;
import com.slxt.rs.model.SysUserJq;
import com.slxt.rs.model.SysUserMenu;
import com.slxt.rs.svc.UserTeamService;
public class UserTeamAction extends DispatchAction{
    private UserTeamService ut;
    public void setUt(UserTeamService ut) {
		this.ut = ut;
	}
	//查询用户组
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    List<SysUserGroup> list=new ArrayList();
		    if(user.getIsSuper()==1){
		    	String sql="select su.webId,su.group_No,su.group_Name,su.is_Admin from sys_user_group su";
		    	List list1=ut.searchAllBySql(sql);
		    	Iterator it=list1.iterator();
		    	while(it.hasNext()){
		    		Object[] ob=(Object[])it.next();
		    		SysUserGroup sug=new SysUserGroup();
		    		sug.setWebId(Integer.parseInt(ob[0].toString()));
		    		sug.setGroupNo(ob[1].toString());
		    		sug.setGroupName(ob[2].toString());
		    		sug.setIsAdmin(Integer.parseInt(ob[3].toString()));
		    		list.add(sug);
				  }
			}else{
				StringBuffer sql=new StringBuffer("select su.webId,su.group_No,su.group_Name,su.is_Admin from sys_user_group su where su.group_No !='");
				sql.append(user.getGroupNo()+"'");
				List list1=ut.searchAllBySql(sql.toString());
				Iterator it=list1.iterator();
				while(it.hasNext()){
					Object[] ob=(Object[])it.next();
					SysUserGroup sug=new SysUserGroup();
					sug.setWebId(Integer.parseInt(ob[0].toString()));
					sug.setGroupNo(ob[1].toString());
					sug.setGroupName(ob[2].toString());
					sug.setIsAdmin(Integer.parseInt(ob[3].toString()));
					list.add(sug);
				}
		   }
		   request.setAttribute("sysUserGroupList", list);
		   return mapping.findForward("success");
	}
	//查询用户组的用户
	public ActionForward searchUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
			   return mapping.findForward("sessionFailure");
		    }
		    String groupNo=java.net.URLDecoder.decode((String)request.getParameter("groupNo"),"UTF-8");
		    StringBuffer str=new StringBuffer("select su.webId,su.user_No,su.user_Name,su.user_Pwd,su.user_Depart,su.group_No from sys_user su where su.group_No='");
		    str.append(groupNo+"'");
		    List list1=ut.searchAllBySql(str.toString());
		    List<SysUser> list=new ArrayList();
			Iterator it=list1.iterator();
			while(it.hasNext()){
				  Object[] ob=(Object[])it.next();
				  SysUser sug=new SysUser();
				  sug.setWebId(Integer.parseInt(ob[0].toString()));
				  sug.setUserNo(ob[1].toString());
				  if(ob[2]!=null && ob[2]!=""){
					  sug.setUserName(ob[2].toString());
				  }
				  sug.setUserPwd(ob[3].toString());
				  if(ob[4]!=null && ob[4]!=""){
					  sug.setUserDepart(ob[4].toString());
				  }
				  sug.setGroupNo(ob[5].toString());
				  list.add(sug);
			}
		    request.setAttribute("groupUser", list);
		    return mapping.findForward("showgroupUser");
		
	}
	//删除用户组
	public ActionForward delUserTeam(ActionMapping mapping, ActionForm form,
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
		    sl.setModel("用户组管理");
		    String groupNo = java.net.URLDecoder.decode((String)request.getParameter("groupNo"),"UTF-8") ;
		    String groupId = java.net.URLDecoder.decode((String)request.getParameter("groupId"),"UTF-8") ;
		    SysUserGroup sysUserGroup=(SysUserGroup)ut.findById(SysUserGroup.class, Integer.parseInt(groupId));
		    StringBuffer str=new StringBuffer("from SysUser su where su.groupNo ='");
		    str.append(groupNo+"'");
		    String hql1="delete from SysUserMenu su where su.groupNo =?";
		    Object[] obj={groupNo};
		    String hql2="delete from SysUser su where su.groupNo =?";
		    String hql3="delete from SysUserJq sug where sug.groupNo =?";
		    ut.deleteByHql(hql1, obj);
		    ut.deleteByHql(hql2, obj);
		    ut.deleteByHql(hql3, obj);
		    sl.setType("严重");
		    sl.setInfo("删除用户组（用户组编号："+sysUserGroup.getGroupNo()+" 用户组名称："+sysUserGroup.getGroupName()+"）");
		    sl.setOp("删除用户组");
		    ut.save(sl);
		    ut.delete(SysUserGroup.class, Integer.parseInt(groupId));
		    return mapping.findForward("delSuccess");
	}
	//查询用户组可以拥有的权限
	public ActionForward sreachMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    StringBuffer str=new StringBuffer("from SysMenu sm where sm.menuAdmin !=");
		    str.append(1);
		    List<SysMenu> list=ut.searchAll(str.toString());
		    request.setAttribute("listMenu", list);
		 	String sql="select Dept_Name from Dept";
		   	List list1=ut.searchAllBySql(sql);
		   	request.setAttribute("deptList", list1);
		    return mapping.findForward("addUserGroup");
	}
	//增加用户组
	public ActionForward addUserTeam(ActionMapping mapping, ActionForm form,
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
			sl.setModel("用户组管理");
			String groupNo = java.net.URLDecoder.decode((String)request.getParameter("groupNo"),"UTF-8") ;
			String groupName = java.net.URLDecoder.decode((String)request.getParameter("groupName"),"UTF-8") ;
			String deptName = java.net.URLDecoder.decode((String)request.getParameter("deptName"),"UTF-8") ;
			Integer isSpDept =Integer.parseInt(request.getParameter("isSpDept").trim());
			Integer spQxJb = Integer.parseInt(request.getParameter("spQxJb").trim());
			
			StringBuffer str=new StringBuffer("from SysUserGroup su where ");
			str.append("su.groupNo='"+groupNo.trim()+"'");
			str.append(" or su.groupName='"+groupName+"'");
			List list=ut.searchAll(str.toString());
			if(list.size()>0){
				JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json);
			}else{
				SysUserGroup sug=new SysUserGroup();
				sug.setGroupName(groupName.trim());
				sug.setGroupNo(groupNo.trim());
				sug.setIsAdmin(0);
				if(!deptName.equals("null")){
					sug.setUserDepart(deptName);
				}
				sug.setIsSpDept(isSpDept);
				sug.setSpQxJb(spQxJb);
				ut.save(sug);
				String str1="";
				if(isSpDept==1){
					str1="是";
				}else{
					str1="否";
				}
				sl.setInfo("增加用户组（ 用户组编号："+groupNo+" 用户组名称："+groupName+" 是否为审批部门："+str1+" 审批部门级别："+spQxJb+"）");
				sl.setOp("增加用户组");
				ut.save(sl);
				JSONArray json=JSONArray.fromObject(sug);
				response.getWriter().println(json);
			}
			return null;
	}
	//修改用户组
	public ActionForward updateUserGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String groupNo = java.net.URLDecoder.decode((String)request.getParameter("groupNo"),"UTF-8") ;
		    String groupId = java.net.URLDecoder.decode((String)request.getParameter("groupId"),"UTF-8") ;
		    StringBuffer hql=new StringBuffer("from SysUserGroup sug where sug.webId=");
		    hql.append(Integer.parseInt(groupId));
		    List<SysUserGroup> listGroup=ut.searchAll(hql.toString());
		    SysUserGroup sug=(SysUserGroup)listGroup.get(0);
		    request.setAttribute("sug", sug);
		    String sql="select Dept_Name from Dept";
		   	List list1=ut.searchAllBySql(sql);
		   	request.setAttribute("deptList", list1);
		    return mapping.findForward("updateUserGroup");
	}
	//保存修改后的用户组
	public ActionForward savaUpdateUserGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String groupNo = java.net.URLDecoder.decode((String)request.getParameter("groupNo"),"UTF-8") ;
			String groupName = java.net.URLDecoder.decode((String)request.getParameter("groupName"),"UTF-8") ;
			 String deptName = java.net.URLDecoder.decode((String)request.getParameter("deptName"),"UTF-8") ;
			String groupId=request.getParameter("groupId");
			Integer isSpDept =Integer.parseInt(request.getParameter("isSpDept").trim());
			Integer spQxJb = Integer.parseInt(request.getParameter("spQxJb").trim());
			SysUserGroup sysUserGroup=(SysUserGroup)ut.findById(SysUserGroup.class, Integer.parseInt(groupId));
			StringBuffer str1=new StringBuffer("");
			StringBuffer str=new StringBuffer("update SysUserGroup sug set sug.groupNo='");
			str.append(groupNo+"',");
			str.append("userDepart='"+deptName+"',");
			str.append("sug.groupName='");
			str.append(groupName+"',");
			str.append("sug.isSpDept="+isSpDept+",");
			str.append("sug.spQxJb="+spQxJb+"");
			if(!sysUserGroup.getGroupName().equals(groupName.trim())){
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
				sl.setModel("用户组管理");
				String str2="";
				if(sysUserGroup.getIsSpDept()==1){
					str2="是";
				}else{
					str2="否";
				}
				String str3 ="";
				if(isSpDept==1){
					str3="是";
				}else{
					str3="否";
				}
				str1.append("编号为"+groupNo+"的用户组的用户组名由"+sysUserGroup.getGroupName()+"修改为"+groupName+", 是否为审批部门由"+str2+"修改为"+str3+", 审批部门权限由"+sysUserGroup.getSpQxJb()+"修改为"+spQxJb);
				sl.setInfo(str1.toString());
				sl.setOp("修改用户组");
				ut.save(sl);
		     }
		     str.append(" where sug.webId=?");
		     Object[] obj={Integer.parseInt(groupId)};
		     ut.updates(str.toString(), obj);
		     JSONArray json=JSONArray.fromObject(null);
		     response.getWriter().println(json);     
		     return null;
	}
	//查询用户组坐席组权限和访问权限
	public ActionForward listSlicGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
			   return mapping.findForward("sessionFailure");
		    }
		    String groupNo = java.net.URLDecoder.decode((String)request.getParameter("groupNo"),"UTF-8") ;
		    String groupId=request.getParameter("groupId");
		    SysUserGroup sysUserGroup=(SysUserGroup)ut.findById(SysUserGroup.class, Integer.parseInt(groupId));
//		    StringBuffer str=new StringBuffer(" select ssg.slicGroupName,ssg.slicGroupNo from SysPowerLine spl,Sys_User_Group sug, SysSlicGroup ssg where ssg.slicGroupNo=spl.slicGroupNo and spl.groupNo=sug.group_No and sug.group_No='");
//		    str.append(groupNo+"'");
		    StringBuffer str=new StringBuffer("from SysUserJq sus where sus.groupNo='");
		    str.append(groupNo+"'");
		    List list=ut.searchAll(str.toString());
		    List<JlJq> limt=new ArrayList();
		    List<JlJq> unlimt=new ArrayList();
		    if(list.size()<=0){
		    	String sql="select ssg.jqName,ssg.jqNo,ssg.jy from JlJq ssg";
		    	List list1=ut.searchAll(sql);
		    	Iterator it=list1.iterator();
		    	while(it.hasNext()){
		    		Object[] obj=(Object[] )it.next();
		    		JlJq gl=new JlJq();
			    	if(obj[0]!=null && obj[0]!=""){
			    		gl.setJqName(obj[0].toString());
			    	}
			    	gl.setJqNo(obj[1].toString());
			    	gl.setJy(obj[2].toString());
			    	unlimt.add(gl);
		    	}
		    }else{
		    	 StringBuffer str3=new StringBuffer(" select ssg.jqName,ssg.jqNo,ssg.jy from SysUserJq spl,SysUserGroup sug, JlJq ssg where ssg.jqNo=spl.jqNo and spl.groupNo=sug.groupNo and sug.groupNo='");
				 str3.append(groupNo+"'");
				 List list2=ut.searchAll(str3.toString());
			     Iterator it=list2.iterator();
			     while(it.hasNext()){
			    	Object[] obj=(Object[] )it.next();
			    	JlJq gl=new JlJq();
			    	if(obj[0]!=null && obj[0]!=""){
			    		gl.setJqName(obj[0].toString());
			    	}
			    	gl.setJqNo(obj[1].toString());
			    	gl.setJy(obj[2].toString());
			    	limt.add(gl);
			    }
			    StringBuffer str1=new StringBuffer();
			    for(int i=0;i<limt.size();i++){
			    	JlJq gl=(JlJq)limt.get(i);
			    	if(i==0){
			    		str1.append(" ssg.jqNo!='");
			    		str1.append(gl.getJqNo()+"'");
			    	}else{
			    		str1.append(" and ssg.jqNo!='");
			    		str1.append(gl.getJqNo()+"'");
			    	}
			    }
			    StringBuffer str2=new StringBuffer("select ssg.jqName,ssg.jqNo,ssg.jy from JlJq ssg where");
			    str2.append(str1.toString());
			    List list1=ut.searchAll(str2.toString());
			  	Iterator it1=list1.iterator();
		    	while(it1.hasNext()){
		    		Object[] obj=(Object[] )it1.next();
		    		JlJq gl=new JlJq();
			    	if(obj[0]!=null && obj[0]!=""){
			    		gl.setJqName(obj[0].toString());
			    	}
			    	gl.setJqNo(obj[1].toString());
			    	gl.setJy(obj[2].toString());
			    	unlimt.add(gl);
		    	}
		    }
		    StringBuffer hql1=new StringBuffer("select sm.webId,sm.menuNo,sm.menuName,sm.menuAdmin from SysUserMenu spg,SysMenu sm where spg.menuNo=sm.menuNo and spg.groupNo='");
		    hql1.append(groupNo+"'");
		    List list1=ut.searchAll(hql1.toString());
		    List<SysMenu> menu=new ArrayList();
		    List<SysMenu> unmenu=new ArrayList();
		    if(list1.size()>0){
		    	for(int i=0;i<list1.size();i++){
		    		Object ob[] = (Object[]) list1.get(i) ;
		    		SysMenu sm= new SysMenu();
		    		sm.setWebId(Integer.parseInt(ob[0].toString()));
		    		sm.setMenuNo(ob[1].toString());
		    		sm.setMenuName(ob[2].toString());
		    		sm.setMenuAdmin(Integer.parseInt(ob[3].toString()));
		    		menu.add(sm);
			    }
			    StringBuffer hql2=new StringBuffer("from SysMenu sm where sm.menuAdmin !=1");
			    for(int i=0;i<menu.size();i++){
			    	SysMenu  spm=(SysMenu)menu.get(i);
			    	hql2.append(" and sm.menuNo !='");
			    	hql2.append(spm.getMenuNo()+"'");
			    }
			    unmenu=ut.searchAll(hql2.toString());
		    }else{
		    	StringBuffer hql3=new StringBuffer("from SysMenu sm where sm.menuAdmin !=1");
		    	unmenu=ut.searchAll(hql3.toString());
		    }
		    request.setAttribute("sysUserGroup",sysUserGroup);
		    request.setAttribute("sysSlicGroupList",limt);
		    request.setAttribute("unsysSlicGroupList",unlimt);
		    request.setAttribute("menuList",menu);
		    request.setAttribute("unmenuList",unmenu);
		    return mapping.findForward("selectSlicGroupPage");
	}
	//保存修改后的用户组坐席组权限和访问权限
	public ActionForward updateSaveLimt(ActionMapping mapping, ActionForm form,
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
		 	sl.setModel("用户组管理");
		 	sl.setUserName(user.getUserName());
		 	sl.setUserNo(user.getUserNo());
		 	sl.setUserIp(request.getRemoteAddr());
		 	sl.setLogTime(loginTime);
		 	String groupNo = java.net.URLDecoder.decode((String)request.getParameter("groupNo"),"UTF-8");
		 	String slicGroupNo = java.net.URLDecoder.decode((String)request.getParameter("slicGroupList"),"UTF-8");
		 	String menuNo = java.net.URLDecoder.decode((String)request.getParameter("menu"),"UTF-8");
		 	String hql="delete from SysUserMenu spm where spm.groupNo=?";
		 	Object[] obj={groupNo};
		 	ut.deleteByHql(hql, obj);
		 	String[] menu=menuNo.split(",");
		 	for(int i=0;i<menu.length;i++){
		 		SysUserMenu spm= new SysUserMenu();
		 		spm.setGroupNo(groupNo);
		 		spm.setMenuNo(menu[i]);
		 		ut.save(spm);
		 	}
		 	sl.setInfo("用户组编号为"+groupNo+"的功能权限和监区权限被修改");
		 	sl.setOp("修改用户组权限");
		 	ut.save(sl);
		 	String hql1="delete from SysUserJq spl where spl.groupNo=?";
		 	ut.deleteByHql(hql1, obj);
		 	String[] slicGroup=slicGroupNo.split(",");
		 	for(int i=0;i<slicGroup.length;i=i+2){
		 		SysUserJq spl=new SysUserJq();
		 		spl.setGroupNo(groupNo);
		 		spl.setJy(slicGroup[i]);
		 		spl.setJqNo(slicGroup[i+1]);
		 		ut.save(spl);
		 	}
		 	JSONArray json=JSONArray.fromObject(null);
		 	response.getWriter().println(json.toString());
		 	return null;
	}
	
	
}

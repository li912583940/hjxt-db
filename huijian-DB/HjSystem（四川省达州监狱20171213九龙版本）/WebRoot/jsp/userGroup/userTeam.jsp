<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>main</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
      	<script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/userTeam.js" type="text/javascript" ></script>
		 <script   language="javascript">   
			document.onkeydown   =   function()   
			{       
			if(event.keyCode   ==   8)   
			{   
			if(event.srcElement.tagName.toLowerCase()   !=   "input"   
			&&   event.srcElement.tagName.toLowerCase()   !=   "textarea")   
			event.returnValue   =   false;   
			}   
			} 
			
	 </script>   
		<style type="text/css">
		   a{text-decoration: NONE}
		</style>
  </head>
  <body>
   	<div id="user_info"><span>登录姓名：${users.userName} 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：用户组管理 </span></div>
       <form action="" method="post">
        <div id="content">
		     	<table  border="0" cellspacing="0" cellpadding="0">
		     		<tr>
		     			<th nowrap="nowrap">用户组编号</th>
		     			<th nowrap="nowrap">用户组名称</th>
		     			<th nowrap="nowrap">管理员组标识</th>
		     			<th nowrap="nowrap">查看用户组成员</th>
		     			<th nowrap="nowrap">操作</th>
		     		</tr>
		     		<tbody id="info">
		     		  <logic:notEmpty name="sysUserGroupList">
		     	   		<logic:iterate id="ut" name="sysUserGroupList">
		     			<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
		     			   <td width="19%" nowrap="nowrap">${ut.groupNo}</td>
		     			   <td width="19%" nowrap="nowrap">${ut.groupName }</td>
		     			   <logic:equal value="1" name="ut" property="isAdmin">
		     			         <td width="19%" nowrap="nowrap">是</td>
		     			   </logic:equal>
		                   <logic:notEqual  value="1" name="ut" property="isAdmin" >
		                          <td width="19%" nowrap="nowrap"></td>
		                   </logic:notEqual>
		     			   <td width="19%" nowrap="nowrap"><a href="javascript:void(0)" onclick="checkGroupUser();return false;">查看组成员</a></td>
		     			   <td nowrap="nowrap" width="19%" nowrap="nowrap">
		     			       <a href="javascript:void(0)" onclick="updateUserTeam();return false;">修改&nbsp;&nbsp;&nbsp;</a>
		     			       <a href="javascript:void(0)" onclick="delUserTeam();return false;">删除 &nbsp;&nbsp;&nbsp;</a>
		     			       <input type="hidden" id="isAdmin" name="isAdmin" value="${ut.isAdmin}" />
		     			       <input type="hidden" id="groupNo" name="groupNo" value="${ut.groupNo}" />
		     			       <input type="hidden" id="groupId" name="groupId" value="${ut.webId}" />
		     			       <a href="javascript:void(0)" onclick="setSlicGroup();return false;">设置权限 &nbsp;&nbsp;&nbsp;</a>
		     			   </td>
		     			</tr>
		     		</logic:iterate>
		     		</logic:notEmpty>
		     		<logic:empty name="sysUserGroupList">
		     				<tr>
		     					<td colspan="5"><font color="red">没有相关记录</font></td>
		     				</tr>
		     		</logic:empty>
		     		</tbody>
		     	</table>
     	  </div>
     	  <a href="javascript:void(0)" onclick="sreachMenu();return false;" style="position:absolute;margin-top:20px;margin-left:130px;height:25px; line-height:25px;"><img src="images/zengjia.gif"></img></a>
       </form>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<script src="<%=basePath %>js/user.js" type="text/javascript" ></script>
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
  
  <body onkeydown="return enterSubmit(this,event);">
  	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：用户管理 </span></div>
       <html:form action="user.do?method=search" method="post">
        <div id="content">
		<table  border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>用户编号：<html:text property="userNo" styleId="userNo"></html:text></td>
			<td>用户姓名：<html:text property="userName" styleId="userName"></html:text></td>
			<td nowrap="nowrap">部门：
    				<html:select property="dePtName" style="width:130px">
    					<html:option value="null">全部</html:option>
    					<logic:iterate id="jl" name="deptList">
    						<html:option value="${jl}">${jl}</html:option>
    					</logic:iterate>
    				</html:select>
		     </td>
			<td>用户组：
				<html:select property="userGroup" style="width:130px">
    					<html:option value="null">全部</html:option>
    					<logic:iterate id="jl" name="sysUserGroupList">
    						<html:option value="${jl.groupNo}">${jl.groupName}</html:option>
    					</logic:iterate>
    				</html:select>
			</td>
			<td colspan="3"><a href="javascript:void(0)" onclick="checkUser();return false;"><img src="images/chaxun.gif"></img></a></td>
		</tr>
		<tr>
     		<th width="20%" nowrap="nowrap">用户登录帐号</th>
     		<th width="20%" nowrap="nowrap">用户姓名</th>
     		<th width="20%" nowrap="nowrap">用户所在部门</th>
     		<th width="20%" nowrap="nowrap">用户所在用户组</th>
     		<th width="6%" nowrap="nowrap">审批家属</th>
     		<th width="6%" nowrap="nowrap">查看帮教</th>
     		<th width="8%" nowrap="nowrap">操作</th>
     	</tr>
     	<tbody id="info">
     	<logic:empty name="page" property="list">
     		<tr>
     		  <td colspan="7"><font color="red">没有相关信息</font></td>
     		</tr>
     	</logic:empty>
     	<logic:notEmpty name="page" property="list">
     	   <logic:iterate id="ug" name="page" property="list">
   				<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
   					<td nowrap="nowrap">${ug.user_No }</td>
   					<td nowrap="nowrap">${ug.user_Name }</td>
   					<td nowrap="nowrap">${ug.user_Depart }</td>
   					<td nowrap="nowrap">${ug.group_Name }</td>
   					<td nowrap="nowrap">
							<c:if test="${ug.isSp==1}"><font color="red">是</font></c:if>
					     	<c:if test="${ug.isSp==0}">否</c:if>
					</td>
					<td nowrap="nowrap">
							<c:if test="${ug.isBj==1}"><font color="red">是</font></c:if>
					     	<c:if test="${ug.isBj==0}">否</c:if>
					</td>
   					<td nowrap="nowrap"><a href="javascript:void(0)" onclick="sreachUserGroup();return false;">修改&nbsp;&nbsp;&nbsp;</a><a href="javascript:void(0)" onclick="delUser();return false;">删除 </a><input type="hidden" name="userId" value="${ug.user_Id }" /><input type="hidden" name="groupNo" value="${ug.group_No }" />&nbsp;&nbsp;<a href="javascript:void(0)" onclick="resetPwd();return false;">重置密码&nbsp;</a></td>
   				</tr>
   				
            </logic:iterate>
            <tr>
   				<td colspan="7"><page:page pageNo="${page.pageNo}" pageSize="${page.pageSize}" recordCount="${page.recordCount}" url="user.do?method=search1"></page:page></td>
   			</tr>
     	</logic:notEmpty>
     	</tbody>
     </table>
     </div>
     <a href="javascript:void(0)" onclick="checkUserGroup();return false;" style="position:absolute;margin-top:20px;margin-left:130px;height:25px; line-height:25px;"><img src="images/zengjia.gif"></img></a>
     </html:form>
  </body>
</html>

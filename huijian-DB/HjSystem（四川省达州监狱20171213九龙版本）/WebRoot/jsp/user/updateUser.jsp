<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
	</head>
  <body>
   	<div id="user_info"><span>h登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：用户管理 </span></div>
     <form action="user.do?method=search" method="post">
	   	<table  style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
	   	         <tr>
	   	         	<td width="30%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户帐号：</td>
	   	         	<td width="30%"><input type="text" id="userNo" name="userNo" value="${ug.user_No}" disabled="disabled" /></td>
	   	         </tr>
	   	          <tr>
	   	         	<td width="30%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户姓名：</td>
	   	         	<td width="30%"><input type="text" id="userName" name="userName" value="${ug.user_Name}" /></td>
	   	         </tr>
	   	          <tr>
	   	         	<td>&nbsp;&nbsp;&nbsp;&nbsp;用户所属部门：</td>
	   	         	<td><input type="text" id="userDepart" name="userDepart" value="${ug.user_Depart}" /></td>
	   	         </tr>
	   	          <tr>
	   	         	<td>用户所属用户组：</td>
	   	         	<td>
	   	         	    <select id="userGroup" style="width:130px;">
	   	         	            <option value="${ug.group_No}" selected="selected">${ug.group_Name}</option>
	   	         	            <logic:notEmpty name="sugList">
	   	         	            <logic:iterate id="sug" name="sugList">
	   	         	                  <option value="${sug.groupNo}">${sug.groupName}</option>
	   	         	            </logic:iterate>
	   	         	            </logic:notEmpty>
	   	         	    </select>
	   	         	</td>
	   	         </tr>
	   	         <tr>
		      	 		<td>是否可以审批家属：&nbsp;&nbsp;&nbsp;&nbsp;</td>
		      	 		<td>
		      	 			<select id="isSp" style="width:130px">
		      	 					    <c:if test="${ug.isSp==1}">
			      	 						<option value="1" selected="selected">是</option>
			      	 						<option value="0">否</option>
			      	 					</c:if>
			      	 					<c:if test="${ug.isSp==0}">
			      	 						<option value="0" selected="selected">否</option>
			      	 						<option value="1">是</option>
			      	 				   </c:if>
			      	 				   
	      	 				</select>
		      	 		</td>
		      	 	</tr>
		      	 <tr>
		      	 		<td>是否可以查看帮教信息：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		      	 		<td>
		      	 			<select id="isBj" style="width:130px">
		      	 					    <c:if test="${ug.isBj==1}">
			      	 						<option value="1" selected="selected">是</option>
			      	 						<option value="0">否</option>
			      	 					</c:if>
			      	 					<c:if test="${ug.isBj==0}">
			      	 						<option value="0" selected="selected">否</option>
			      	 						<option value="1">是</option>
			      	 				   </c:if>
			      	 				   
	      	 				</select>
		      	 		</td>
		      	 </tr>
	   	         <tr>
	   	         	<td><a href="javascript:void(0);" onclick="saveUpdateUser();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="userId" name="userId" value="${ug.user_Id}" /></td>
	   	         	<td><a href="user.do?method=search"><img src="images/fanhui.gif"></img></a></td>
	   	         </tr>
	 
	  </table>
	 </form>  	
  </body>
</html>

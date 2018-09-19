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
		<script src="<%=basePath %>js/updateUserPwd.js" type="text/javascript" ></script>
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
				var basePath='<%=basePath %>';
		</script>   
  </head>
   <body>
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：修改密码 </span></div>
	     <form action="" method="post">
			   	<table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
			   		<tr>
			   			<td width="30%">用户名：</td>
			   			<td width="30%"><input type="text" id="username" name="username" value="${users.userNo }" disabled="disabled" /></td>
			   		</tr>
			   		<tr>
			   			<td>原始密码：</td>
			   			<td><input type="password" id="password" name="password" /><span id="info" style="display:none;"><font color="red">原始密码不正确</font></span></td>
			   		</tr>
			   		<tr>
			   			<td>新密码：</td>
			   			<td><input type="password" id="password1" name="password1" /></td>
			   		</tr>
			   		<tr>
			   			<td>确认新密码：</td>
			   			<td><input type="password" id="password2" name="password2" /></td>
			   		</tr>
			   		<tr>
			   			<td><a href="javascript:void(0)" onclick="save();return false;"><img src="<%=basePath %>images/baocun.gif"></img></a><input type="hidden" id="loginPassword" name="loginPassword" value="${users.userPwd}" /><input type="hidden" id="userId" name="userId" value="${users.webId}" /></td>
			   			<td><a href="javascript:void(0)" onclick="chongzhi();return false;"><img src="<%=basePath %>images/chongzhi.gif"></img></a></td>
			   		</tr>
			   	</table>
    	</form>
  </body>
</html>

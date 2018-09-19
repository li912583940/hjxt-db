<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
      	<script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/load.js" type="text/javascript" ></script>
		<title>会见系统</title>
		<link href="<%=basePath %>css/layout.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		 table td{ border:0;}
			a{
				cursor:hand;
			}
		</style>
		<script type="text/javascript" >
				var basePath='<%=basePath%>';
		</script>
  </head>
  <body id="login" onload="document.all.username.focus();" onkeydown="return enterSubmit(this,event);">
    <div id="l">
	      <form action=""  method="post">
				<table width="285" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td ><input type="text" id="username" name="username"  class="btn"/></td>
					    
					  </tr>
					  <tr>
					    <td><input type="password" id="password" name="password"  class="btn"/></td>
					    <td rowspan="1" ><a onclick="login();"><img src="<%=basePath %>images/login_button.jpg"></img></a></td>
					   </tr>
					   
					    <tr>
				      		<td colspan="1">首次登录请安装：  <a href="<%=basePath %>ocx/AllOcx.rar">控件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;    </td>
				      </tr>
					   <tr>
				      		<td colspan="1"><div id="info" style="display:none"><font color="red">帐号或密码有误请重新输入</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </div> </td>
				      </tr>
		        </table>         
	    </form> 
	 </div>
 </body>
</html>

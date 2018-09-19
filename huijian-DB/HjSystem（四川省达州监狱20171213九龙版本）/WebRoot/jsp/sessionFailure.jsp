<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
				function   goToLogin(){
				   window.open("<%=basePath%>jsp/login.jsp");
				   top.window.opener=null; 
				   top.window.open("","_self"); 
				   top.window.close();
				}
		</script>   
  </head>
  <body>
   
       <form action="userTeam.do?method=search" method="post">
            <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:30%; margin:0 auto; margin-top:50px;">
		     		<tr><td><font color="red">由于当前用户长时间没有操作，系统已自动退出</font></td></tr>
		     		<tr><td><a href="javascript:void(0)" onclick="goToLogin();return false;">跳转至登录页面</a></td></tr>
		    </table>

       </form>
  </body>
</html>

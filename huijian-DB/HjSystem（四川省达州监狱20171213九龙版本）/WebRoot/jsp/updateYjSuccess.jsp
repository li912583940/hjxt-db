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
		</script>   
  </head>
   <body>
   	<div id="user_info"><span>姓名：${jlYj.yjName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：修改密码 </span></div>
	     <form action="<%=basePath %>jsp/updateLoginUserYJ.jsp" method="post">
			   	<table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:30%; margin:0 auto; margin-top:10px;">
			   		<tr>
			   			<td><font color="red">修改成功</font></td>
			   		</tr>
			   		<tr>
			   			<td><a href="<%=basePath %>jsp/updateLoginUserYJ.jsp"><img src="images/fanhui.gif"></img></a></td>
			   		</tr>
			   	</table>
    	</form>
  </body>
</html>

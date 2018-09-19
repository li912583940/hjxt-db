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
       <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title>main</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
  </head>
   <body onkeydown="return enterSubmit(this,event);">
   	<div id="user_info"><span>&nbsp;登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div style="margin-left:35%;margin-top: 8%;"><img src="<%=basePath %>images/jh.jpg" height="300px"/></div>
	<!--  
	<div style="margin-left:10%;margin-top: 2%;"><font size="20" color="#50d9ae">欢迎使用VoxPMCC监狱会见监控系统</font></div>
       --> 	 	
  </body>
</html>

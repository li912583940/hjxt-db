<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>top</title>
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
<script src="<%=basePath %>js/updateUserPwd.js" type="text/javascript" ></script>
	<style type="text/css">
			a{
			cursor:hand;
			}
	</style>
</head>

<body>
<div id="header">
<div id="top">网站名称</div>
<h1>logo</h1>
<p><a href="downLoadDriver.jsp" target="main"><font color="#FFFFFF">下载驱动</font></a><!--<a target="main">帮助</a>--><a href="updateLoginUser.jsp" target="main"><font color="#FFFFFF">修改密码</font></a><a onclick="goHomeUser();"><font color="#FFFFFF">退出系统</font></a><input type="hidden" id="lujin" value="<%=basePath %>" /></p>
</div>
</body>
</html>

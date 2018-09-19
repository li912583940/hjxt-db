<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
		<script src="<%=basePath %>js/jlJb.js" type="text/javascript" ></script>
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
  	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：罪犯级别 </span></div>
       <form action="gradeMessage.do?method=search" method="post">
			     	<table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
			     		<tr>
			     			<td width="30%">级别编号：</td>
			     			<td width="30%"><input type="text" id="jbNo"  name="jbNo" value="${jlJb.jbNo }" disabled="disabled" /></td>
			     		</tr>
			     		<tr>
			     			<td>级别名称：</td>
			     			<td><input type="text" id="jbName"  name="jbName" value="${jlJb.jbName }" /></td>
			     		</tr>
			     		<tr>
			     			<td>每月会见次数：</td>
			     			<td><input type="text" id="hjCount"  name="hjCount" value="${jlJb.hjCount}" /></td>
			     		</tr>
			     		<tr>
			     			<td>每次会见时长（分钟）：</td>
			     			<td><input type="text" id="hjTime"  name="hjTime" value="${jlJb.hjTime }" /></td>
			     		</tr>
			     		<tr>
			     			<td>复听录音超时时间（天）：</td>
			     			<td><input type="text" id="recordOverTime"  name="recordOverTime" value="${jlJb.recordOverTime }" /></td>
			     		</tr>

			     		<tr>
			     			<td><a href="javascript:void(0);" onclick="updateSaveJlJb();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="webId" name="webId" value="${jlJb.webId }" /></td>
			     			<td><a href="gradeMessage.do?method=search"><img src="images/fanhui.gif"></img></a></td>
			     		</tr>
			     	</table>
		</form>
  </body>
</html>

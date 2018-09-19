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
		<script src="<%=basePath %>js/yjMessage.js" type="text/javascript" ></script>
		<script type="text/javascript">
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
	  <div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：警察信息</span></div>
	       <form action="yjMessage.do?method=search" method="post">
			     	<table  style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
			     		<tr>
			     			<td width="30%">警察编号：</td>
			     			<td width="30%"><input type="text" id="yjNum1" name="yjNum1" value="${jlYj.yjNum }"/></td>
			     		</tr>
		
			     		<tr>
			     			<td><a href="javascript:void(0);" onclick="saveUpdateYj1();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="webId" name="webId" value="${jlYj.webId}" /></td>
			     			<td><a id="returnBack" href="yjMessage.do?method=search&deptName2=${deptName2}&yjNum2=${yjNum2}&jyName2=${jyName2}&pageNo2=${pageNo2}"><img src="images/fanhui.gif"></img></a></td>
			     		</tr>
			     	</table>
		
			</form>
  </body>
</html>

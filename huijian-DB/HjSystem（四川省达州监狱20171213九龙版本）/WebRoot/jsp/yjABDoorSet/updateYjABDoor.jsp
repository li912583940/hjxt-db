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
		<script src="<%=basePath %>js/yjABDoorSet.js" type="text/javascript" ></script>
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
		<script  language="javascript" for="WM1711" event="cardEvent()">
		 		//document.getElementById("yjCard").value=document.getElementById("WM1711").FunGetEveData();
		 		var idcard=document.getElementById("WM1711").FunGetEveData();
		 		idcard1=idcard.substring(6,8);
		 		idcard2=idcard.substring(8,10);
		 		idcard3=idcard.substring(10,12);
		 		idcard4=idcard.substring(12,14);
		 		idcard=idcard1+idcard2+idcard3+idcard4;
				var ten=parseInt(idcard,16);
		 		document.getElementById("icCardNo").value=ten;
		 		
		 </script>
       
  </head>
  <body>
	  <div id="user_info"><span>登录姓名：${users.userName} 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：陪同警察管理</span></div>
	       <form action="yjABDoorMessage.do?method=search" method="post">
			     	<table  style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:50%; margin:0 auto; margin-top:10px;">
			     			<tr>
			     			<td width="25%">警号：</td>
			     			<td width="25%"><input type="text" id="yjNo" name="yjNo" value="${jlYjABDoor.yjNo}" disabled="disabled" /></td>
			     		</tr>
			     		<tr>
			     			<td>姓名：</td>
			     			<td><input type="text" id="yjName" name="yjName" value="${jlYjABDoor.yjName}" /></td>
			     		</tr>
			     		
			     		<tr>
			     			<td><a href="javascript:void(0);" onclick="saveYjABDoor();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="webId" name="webId" value="${jlYjABDoor.webId}" /></td>
			     			<td><a href="yjABDoorMessage.do?method=search"><img src="images/fanhui.gif"></img></a></td>
			     		</tr>
			     	</table>
			</form>
			
  </body>
</html>

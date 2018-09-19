<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
        <script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/materialMessage.js" type="text/javascript" ></script>
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
   	<div id="user_info"><span>登录姓名：${users.userNo } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：罪犯信息 </span></div>
       <form action="materialMessage.do?method=search" method="post">
		     	<table   style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px;">
			     	 <tr>
		   	         	<td width="10%" style="font-size:20px;font-family:微软雅黑;font-weight:bold;">罪犯信息：</td>
		   	         	<td width="40%"><textarea rows="20" cols="85" id="itemVocText" name="itemVocText">${cheCiErrorMessage}</textarea></td>
		   	         	<td width="10%" style="font-size:20px;font-family:微软雅黑;font-weight:bold;">亲属信息：</td>
	   	         		<td width="40%"><textarea rows="20" cols="85" id="itemVocText" name="itemVocText">${ztzErrorMessage}</textarea></td>
		   	         </tr>
	   	         		<c:if test="${flag==true && flag1==true}"> <tr><td colspan="2"><a href="javascript:void(0)" onclick="daoRuZsDB();return false;"><img src="<%=basePath%>images/baocun.gif" /></a></td><td colspan="2"><a href="materialMessage.do?method=search"><img src="<%=basePath%>images/fanhui.gif" /></a></td> </tr></c:if>
	   	         		<c:if test="${flag==false || flag1==false}"><tr><td colspan="4"><a href="materialMessage.do?method=search"><img src="<%=basePath%>images/fanhui.gif" /></a></td> </tr></c:if>
		     	</table>
        </form>
        <div id="load" style="display:none;position:absolute;position:absolute;z-index:2;margin-left:45%"></div> 
  </body>
</html>

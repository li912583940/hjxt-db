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
		<script src="<%=basePath %>js/lineSet.js" type="text/javascript" ></script>
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
			var basePath='<%=basePath%>';     
		</script>
       
  </head>
  <body>
	  <div id="user_info"><span>登录姓名：${users.userName} 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：线路设置</span></div>
	       <form action="lineSet.do?method=search" method="post">
			     	<table  style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
			     		<tr>
			     			<td width="30%">线路逻辑号：</td>
			     			<td width="30%">
			     				<input type="text" id="lineNo" name="lineNo" value="${lineNo }" disabled="disabled"/>
			     			</td>
			     		</tr>
			     		<tr>
			     			<td width="30%">服务器名称：</td>
			     			<td width="30%">
			     				<input type="text" id="jy" name="jy" value="${jy }" disabled="disabled"/>
			     			</td>
			     		</tr>
			     		<tr>
			     			<td>IC卡号：</td>
			     			<td><input type="text" id="cardNo" name="cardNo" value="${cardNo}"/></td>
			     		</tr>
			     		
			     		<tr>
			     			<td><a href="javascript:void(0);" onclick="updateSaveCardNo();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="op" name="op" value="${op}" /></td>
			     			<td><a href="lineSet.do?method=search"><img src="images/fanhui.gif"></img></a></td>
			     		</tr>
			     	</table>
			 </form>
  </body>
</html>

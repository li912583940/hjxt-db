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
		<script src="<%=basePath %>js/jqSet.js" type="text/javascript" ></script>
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
	  <div id="lm_info"><span>当前位置：监区设置</span></div>
	       <form action="jqSet.do?method=search" method="post">
			     	<table  style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
			     		<tr>
			     			<td width="30%">监区编号：</td>
			     			<td width="30%"><input type="text" id="jqNo" name="jqNo" value="${jlJq.jqNo }" disabled="disabled" /></td>
			     		</tr>
			     		<tr>
			     			<td>监区名称：</td>
			     			<td><input type="text" id="jqName" name="jqName" value="${jlJq.jqName }" /></td>
			     		</tr>
			     		<tr>
			     			<td>服务器名称：</td>
			     			<td>
			     				<select id="jyMc" style="width:130px;">
			     					<option value="${jlJq.jy}" selected="selected">${jlJq.jy}</option>
			     					<logic:notEmpty name="sysQqServerList">
			     						<logic:iterate id="sqs" name="sysQqServerList">
			     								<option value="${sqs.serverName}">${sqs.serverName}</option>
			     						</logic:iterate>
			     					</logic:notEmpty>
			     				</select>
			     			</td>
			     		</tr>
			     		<tr>
			     			<td>特殊监区：</td>
			     			<td>
			     				<c:if test="${jlJq.isTs==0}">
			     					<input type="radio" name="isTs" value="0" checked="checked"/>不是 <input type="radio" name="isTs" value="1"/>是
			     				</c:if>
			     				<c:if test="${jlJq.isTs==1}">
			     					<input type="radio" name="isTs" value="0"/>不是 <input type="radio" name="isTs" value="1" checked="checked"/>是
			     				</c:if>
			     			</td>
			     		</tr>
			     		<tr>
			     			<td><a href="javascript:void(0);" onclick="saveUpdateJq();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="jqId" name="jqId" value="${jlJq.webId}"/></td>
			     			<td><a href="jqSet.do?method=search"><img src="images/fanhui.gif"></img></a></td>
			     		</tr>
			     	</table>
		
			</form>
  </body>
</html>

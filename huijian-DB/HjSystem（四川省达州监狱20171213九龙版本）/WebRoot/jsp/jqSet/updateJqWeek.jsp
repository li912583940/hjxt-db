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
        <script src="<%=basePath %>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>    
      	<script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/jqSet.js" type="text/javascript" ></script>
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
		<style type="text/css">
			a{
			cursor:hand;
			}
		
		</style>
  </head>
   <body>
   	<div id="user_info1"><span>&quot;登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info1"><span>当前位置：监区设置 </span></div>
    <form action="jqSet.do?method=search" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td width="30%">服务器名称：</td>
      	 	    <td width="30%"><input type="text" id="jy" name="jy" value="${jlJq.jy}" disabled="disabled"/></td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%">监区名称：</td>
      	 	    <td width="30%"><input type="text" id="jqName" name="jqName" value="${jlJq.jqName}" disabled="disabled"/></td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%">监区编号：</td>
      	 		<td width="30%"><input type="text" id="jqNo" name="jqNo" value="${jlJqWeekTime.jqNo}" disabled="disabled"/></td>
      	 	</tr>
      	     <tr>
			     			<td>星期：</td>
			     			<td><select id="week" style="width:130px">
			     			    <c:if test="${jlJqWeekTime.jqWeek==0}">
			     			            <option value="1">星期一</option>
			     						<option value="2">星期二</option>
			     						<option value="3">星期三</option>
			     						<option value="4">星期四</option>
			     						<option value="5">星期五</option>
			     						<option value="6">星期六</option>
			     						<option value="0" selected="selected">星期天</option>
			     			    </c:if>
			     			    <c:if test="${jlJqWeekTime.jqWeek==1}">
			     			    		<option value="1" selected="selected">星期一</option>
			     						<option value="2">星期二</option>
			     						<option value="3">星期三</option>
			     						<option value="4">星期四</option>
			     						<option value="5">星期五</option>
			     						<option value="6">星期六</option>
			     						<option value="0">星期天</option>
			     			    </c:if>
			     			    <c:if test="${jlJqWeekTime.jqWeek==2}">
			     			    		<option value="1">星期一</option>
			     						<option value="2" selected="selected">星期二</option>
			     						<option value="3">星期三</option>
			     						<option value="4">星期四</option>
			     						<option value="5">星期五</option>
			     						<option value="6">星期六</option>
			     						<option value="0">星期天</option>
			     			    </c:if>
			     			    <c:if test="${jlJqWeekTime.jqWeek==3}">
			     			    		<option value="1">星期一</option>
			     						<option value="2">星期二</option>
			     						<option value="3" selected="selected">星期三</option>
			     						<option value="4">星期四</option>
			     						<option value="5">星期五</option>
			     						<option value="6">星期六</option>
			     						<option value="0">星期天</option>
			     			    </c:if>
			     			    <c:if test="${jlJqWeekTime.jqWeek==4}">
			     			    		<option value="1">星期一</option>
			     						<option value="2">星期二</option>
			     						<option value="3">星期三</option>
			     						<option value="4" selected="selected">星期四</option>
			     						<option value="5">星期五</option>
			     						<option value="6">星期六</option>
			     						<option value="0">星期天</option>
			     			    </c:if>
			     			    <c:if test="${jlJqWeekTime.jqWeek==5}">
			     			    		<option value="1">星期一</option>
			     						<option value="2">星期二</option>
			     						<option value="3">星期三</option>
			     						<option value="4">星期四</option>
			     						<option value="5" selected="selected">星期五</option>
			     						<option value="6">星期六</option>
			     						<option value="0">星期天</option>
			     			    </c:if>
			     			    <c:if test="${jlJqWeekTime.jqWeek==6}">
			     			    		<option value="1">星期一</option>
			     						<option value="2">星期二</option>
			     						<option value="3">星期三</option>
			     						<option value="4">星期四</option>
			     						<option value="5">星期五</option>
			     						<option value="6" selected="selected">星期六</option>
			     						<option value="0">星期天</option>
			     			    
			     			    </c:if>
			     			</select>
			     		</td>
			 </tr>
      	 	<tr>
			     <td><a href="javascript:void(0);" onclick="updateSaveJqWeek();return false;"><img src="<%=basePath %>images/baocun.gif"></img></a><input type="hidden" id="webId" value="${jlJq.webId}"/><input type="hidden" id="timeIndex" value="${jlJqWeekTime.webId}"/></td>
			     <td><a href="jqSet.do?method=checkJqWeek&jqId=${jlJq.webId}"><img src="<%=basePath %>images/fanhui.gif"></img></a></td>
			</tr>
      	 </table>
    </form>
  </body>
</html>

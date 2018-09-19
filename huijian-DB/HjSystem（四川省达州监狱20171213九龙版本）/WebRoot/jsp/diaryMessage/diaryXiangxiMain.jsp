<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
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
        <script src="<%=basePath %>js/DateTimeCalendar.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/diaryMessage.js" type="text/javascript" ></script>
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
  </head>
  <body>
	  <div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：操作日志 </span></div>
	       <form action="diaryMessage.do?method=search" method="post">
	        		<table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
	        			<tr>
	        			  <td width="30%">时间：</td>
	        			  <td width="30%"><input type="text" value="${sysLog.logTime}" /></td>
	        		    </tr>
	        		    <tr>
	        			  <td>用户编号：</td>
	        			  <td><input type="text" value="${sysLog.userNo}" /></td>
	        			</tr>
	        			<tr>
	        			  <td>用户姓名：</td>
	        			  <td><input type="text" value="${sysLog.userName}" /></td>
	        		    </tr>
	        		    <tr>
	        			   <td>来源IP：</td>
	        			   <td><input type="text" value="${sysLog.userIp}" /></td>
	        			</tr>
	        			<tr>
	        			   <td>级别：</td>
	        			   <td><input type="text" value="${sysLog.type}" /></td>
	        			</tr> 
	        			<tr>
	        			     <td>模块：</td>
	        			     <td><input type="text" value="${sysLog.model}" /></td>
	        			</tr>
	        			<tr> 
	        				 <td>操作：</td>
	        				 <td><input type="text" value="${sysLog.op}" /></td>
	        			</tr>
	        			<tr> 
	        			    <td>内容：</td>
	        			    <td><textarea style="width:130px">${sysLog.info}</textarea></td>
	        			</tr>
	        		</table>
	           <a href="diaryMessage.do?method=search&op3=${op3}&info3=${info3}&userNo3=${userNo3}&userName3=${userName3}&userIp3=${userIp3}&callTimeEnd3=${callTimeEnd3}&callTimeStart3=${callTimeStart3}&pageNo3=${pageNo3}&type2=${type2}&model2=${model2}" style="position:absolute;margin-top:20px;margin-left:840px;height:25px; line-height:25px;"><img src="images/fanhui.gif"></img></a>
	        </form>
  </body>
</html>

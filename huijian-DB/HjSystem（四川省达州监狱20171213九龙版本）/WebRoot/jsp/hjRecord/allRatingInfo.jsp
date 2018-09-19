<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String http=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>main</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>js/hjRecord.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/DateTimeCalendar.js" type="text/javascript"></script>
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
			   a{text-decoration: NONE}
			</style>   
  </head>
  <body>
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：会见通话记录 </span></div>
       <html:form action="hjRecord.do?method=search" method="post">
        	<div id="content2">
		     	<table>
		     	<tr>
		     	    <th height="25px">用户编号</th>
		     	    <th height="25px">用户姓名</th>
		     	    <th height="25px">评级内容</th>
		     	    <th height="25px">评级时间</th>
		     	 </tr>
		     	    <logic:notEmpty name="jlHjRecRatingInfoList">
		     	       <logic:iterate id="jl" name="jlHjRecRatingInfoList">
			     	    	<tr>
			     	    		<td height="25px">${jl.userNo }</td>
			     	    		<td height="25px">${jl.userName }</td>
			     	    		<td height="25px">${jl.writeTxt }</td>
			     	    		<td height="25px"><fmt:formatDate type="time" value="${jl.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			     	    		
			     	    	</tr>
		     	    	</logic:iterate>
		     	    </logic:notEmpty>
		     	    <logic:empty name="jlHjRecRatingInfoList">
			     	    	<tr>
			     	    		<td colspan="4"><font color="red">没有相关记录</font></td>
			     	    	</tr>
		     	    </logic:empty>
	  			 </table>
	  	 	</div>
	  	 	<a href="hjRecord.do?method=search&callTimeStart2=${callTimeStart2}&callTimeEnd2=${callTimeEnd2}&frName2=${frName2}&frNo3=${frNo3}&zw2=${zw2}&type2=${type2}&jy2=${jy2}&jq2=${jq2}&qsName2=${qsName2}&pageNo2=${pageNo2}&recRatingState2=${recRatingState2}&recAssessmentState2=${recAssessmentState2}&recordOverTime2=${recordOverTime2}" style="position:absolute;margin-top:20px;margin-left:900px;height:25px; line-height:25px;"><img src="images/fanhui.gif"></img></a>
	  	 </html:form>	
  </body>
</html>

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
		<script src="<%=basePath %>js/hjRecord.js" type="text/javascript" ></script>
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
	  <div id="user_info"><span>登录姓名：${users.userName} 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：会见通话记录</span></div>
	       <form action="lineSet.do?method=search" method="post">
			     	<table  style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
			     		<tr>
			     			<td width="30%">呼叫ID：</td>
			     			<td width="30%">
			     				<input type="text" id="callId" name="callId" value="${jlHjRec.callId }" disabled="disabled"/>
			     				
			     			</td>
			     		</tr>
			     		<tr>
      		    			<td>当前用户编号：</td>
                			<td><input type="text" name="userNo" id="userNo" value="${userNo}" disabled="disabled" /></td>     		
      					</tr>
      					<tr>
      		    			<td>当前用户姓名：</td>
                			<td><input type="text" name="userNo" id="userName" value="${userName}" disabled="disabled" /></td>     		
      					</tr>
			     		<tr>
			     			<td><font color="red">录音评级状态：</font></td>
			     			<td>
			     				<select id="state" style="width:130px">
			     					<c:if test="${jlHjRec.recRatingState==0}">
			     						<option value="0" selected="selected">未评</option>
			     						<option value="1">异常</option>
			     						<option value="2">正常</option>
			     					</c:if>
			     					<c:if test="${jlHjRec.recRatingState==1}">
			     						<option value="1" selected="selected">异常</option>
			     						<option value="0">未评</option>
			     						<option value="2">正常</option>
			     					</c:if>
			     					<c:if test="${jlHjRec.recRatingState==2}">
			     						<option value="2" selected="selected">正常</option>
			     						<option value="0">未评</option>
			     						<option value="1">异常</option>
			     					</c:if>
			     				</select>
			     			</td>
			     			
			     		</tr>
			     		<tr>
                			<td><font color="red">录入评级内容：</font></td>
                			<td><textarea name="writeTxt" id="writeTxt" style="width:400px;height:200px"></textarea></td>       		
      						</tr>
			     		<tr>
			     			<td><a href="javascript:void(0);" onclick="updateSaveRatingState();return false;"><img src="images/baocun.gif"></img></a><input type="hidden"  id="webId" name="webId" value="${jlHjRec.webId}"/> </td>
			     			<td><a id="returnBack" href="hjRecord.do?method=search&callTimeStart2=${callTimeStart2}&callTimeEnd2=${callTimeEnd2}&frName2=${frName2}&frNo3=${frNo3}&zw2=${zw2}&type2=${type2}&jy2=${jy2}&jq2=${jq2}&qsName2=${qsName2}&pageNo2=${pageNo2}&recRatingState2=${recRatingState2}&recAssessmentState2=${recAssessmentState2}&recordOverTime2=${recordOverTime2}"><img src="images/fanhui.gif"></img></a></td>
			     		</tr>
			     	</table>
			 </form>
  </body>
</html>

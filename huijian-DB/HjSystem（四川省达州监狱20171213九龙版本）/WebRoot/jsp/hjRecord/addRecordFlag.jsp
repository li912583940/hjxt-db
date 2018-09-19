<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
   	<div id="user_info"><span>登录姓名：${users.userName }   登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：会见通话记录 </span></div>
        	 <form action="hjRecord.do?method=search" method="post">
		     	<table  style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
      		<tr>
               <td width="30%">呼叫ID：</td>
               <td width="30%"><input type="text" name="callId" id="callId" value="${callId}" disabled="disabled" /></td>        		
      		</tr>
      		<tr>
      		    <td>用户编号：</td>
                <td><input type="text" name="userNo" id="userNo" value="${userNo}" disabled="disabled" /></td>     		
      		</tr>
      		<tr>
                <td>摘要内容：</td>
                <td><textarea name="writeTxt" id="writeTxt" style="width:400px;height:200px"></textarea></td>       		
      		</tr>
      		<tr>
      			<td><a href="javascript:void(0);" onclick="addsaveRecordFlag();return false;"><img src="images/baocun.gif"></img></a></td>
      			<td><a id="returnBack" href="hjRecord.do?method=search&callTimeStart2=${callTimeStart2}&callTimeEnd2=${callTimeEnd2}&frName2=${frName2}&frNo3=${frNo3}&zw2=${zw2}&type2=${type2}&jy2=${jy2}&jq2=${jq2}&qsName2=${qsName2}&pageNo2=${pageNo2}&recRatingState2=${recRatingState2}&recAssessmentState2=${recAssessmentState2}&recordOverTime2=${recordOverTime2}"><img src="images/fanhui.gif"></img></a></td>
      		</tr>
      </table>
      </form>
  </body>
</html>

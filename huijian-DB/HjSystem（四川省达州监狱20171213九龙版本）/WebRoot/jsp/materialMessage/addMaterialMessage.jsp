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
		<script  language="javascript" for="WM1711" event="cardEvent()">
			var idcard=document.getElementById("WM1711").FunGetEveData();
			//从第6个字符开始，第14个字符结束，截取M1卡卡号，再转成10进制
			idcard1=idcard.substring(6,8);
	 		idcard2=idcard.substring(8,10);
	 		idcard3=idcard.substring(10,12);
	 		idcard4=idcard.substring(12,14);
	 		idcard=idcard4+idcard3+idcard2+idcard1;
			var ten=parseInt(idcard,16);
			document.getElementById("frCard").value=ten;
		 </script>   
  </head>
  <!--  <body onload="openPort1();" onunload="colsePort1();"> 
   <body >-->
   <body>
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：罪犯管理 </span></div>
    <form action="materialMessage.do?method=search" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td width="30%">罪犯编号：</td>
      	 		<td width="30%"><input type="text" id="frNo1" name="frNo1" value="${frNo}" /></td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>罪犯姓名：</td>
      	 		<td><input type="text" id="frName1" name="frName1" /></td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>罪犯IC卡号：</td>
      	 		<td><input type="text" id="frCard" name="frCard" /></td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>服务器名称：</td>
      	 		<td>
      	 			<select id="jy1" style="width:130px" onchange="checkJq1()">
      	 				<option value="null">全部</option>
      	 				<logic:notEmpty name="sysQqServerList">
	      	 				<logic:iterate id="sqs" name="sysQqServerList">
	      	 					<option value="${sqs.serverName }">${sqs.serverName }</option>
	      	 				</logic:iterate>
      	 				</logic:notEmpty>
      	 			</select>
      	 		</td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>所属监区：</td>
      	 		<td>
      	 			<select id="jq1" style="width:130px" disabled="disabled">
      	 				<option value="null">全部</option>
      	 			</select>
      	 		</td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>分管等级：</td>
      	 		<td>
      	 			<select id="jb1" style="width:130px">
      	 				<option value="null">全部</option>
      	 				<logic:iterate id="jljb" name="jlJbList">
      	 					<option value="${jljb.jbNo}">${jljb.jbName}</option>
      	 				</logic:iterate>
      	 			</select>
      	 		</td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>会见级别：</td>
      	 		<td>
      	 			<select id="hjjb" style="width:130px">
      	 					<option value="1" >正常</option>
      	 					<option value="-1">禁止</option>
      	 			</select>
      	 		</td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>国籍：</td>
      	 		<td><input type="text" id="frGj" name="frGj" class="inputstyle keywords" value="中国"  onfocus='if(this.value=="中国"){this.value="";}; ' onblur='if(this.value==""){this.value="中国";};'></td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>入监时间：</td>
      	 		<td><input type="text" id="infoRjsj" name="infoRjsj" /></td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>罪名：</td>
      	 		<td><input type="text" id="infoZm" name="infoZm" /></td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>刑期：</td>
      	 		<td><input type="text" id="infoXq" name="infoXq" /></td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>出生日期：</td>
      	 		<td><input type="text" id="infoCsrq" name="infoCsrq" /></td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>住址：</td>
      	 		<td><input type="text" id="infoHome" name="infoHome" /></td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>是否重点监控：</td>
      	 		<td>
      	 			<select id="monitorFlag" style="width:130px;">
      	 				<option value="0" selected="selected">否</option>
      	 				<option value="1">是</option>
      	 			</select>
      	 		</td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>是否重点罪犯：</td>
      	 		<td>
      	 			<!-- <select id="stateZdzf" style="width:130px;" onchange="changeZdzfType()"> -->
      	 			<select id="stateZdzf" style="width:130px;">
      	 				<option value="0" selected="selected">否</option>
      	 				<option value="1">是</option>
      	 			</select>
      	 		</td>
      	 	</tr>
      	 	<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
      	 		<td>备注：</td>
      	 		<!-- <td><input type="text" id="zdzfType" name="zdzfType" disabled="disabled"/></td> -->
      	 		<td><input type="text" id="zdzfType" name="zdzfType"/></td>
      	 	</tr>
      	 	<tr>
      	 		<td><a href="javascript:void(0);" onclick="addSaveFr();return false;"><img src="images/baocun.gif"></img></a></td>
      	 		<td><a id="returnBack" href="materialMessage.do?method=search&frNo3=${frNo3}&frName2=${frName2}&jy2=${jy2}&jq2=${jq2}&jbNo2=${jbNo2}&pageNo2=${pageNo2}&stateZdzf2=${stateZdzf2}&state2=${state2}&frCard2=${frCard2}"><img src="images/fanhui.gif"></img></a></td>
      	 	</tr>
      	 </table>
      	 </form>
      	<!--  <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
      	 <object id="WM02R" classid="CLSID:54A61884-0949-41BE-8FB9-DB55BCCE486C" codebase="<%=basePath %>ocx/WM02R.CAB#version=1,0,0,0" style="display: none"></object>
      	 <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
    	  <object id="WM1711" name="WM1711" codebase="<%=basePath%>/ocx/WM171.CAB#version=1,0,0,0"  classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object> -->
  </body>
</html>

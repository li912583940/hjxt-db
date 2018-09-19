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
		<script src="<%=basePath %>js/yjMessage.js" type="text/javascript" ></script>
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
		<!--  <script  language="javascript" for="reID" event="ReadEvent(cardid);">
			 		document.getElementById("yjCard").value=cardid;
			 	
		 </script>-->
		 <script  language="javascript" for="WM1711" event="cardEvent()">
		 		//document.getElementById("yjCard").value=document.getElementById("WM1711").FunGetEveData();
		 		var idcard=document.getElementById("WM1711").FunGetEveData();
		 		idcard1=idcard.substring(6,8);
		 		idcard2=idcard.substring(8,10);
		 		idcard3=idcard.substring(10,12);
		 		idcard4=idcard.substring(12,14);
		 		idcard=idcard1+idcard2+idcard3+idcard4;
				var ten=parseInt(idcard,16);
		 		document.getElementById("yjCard").value=ten;
		 		
		 </script>
		 
  </head>
  <body onload="openPort();" onunload="colsePort();">
	  <div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：警察信息</span></div>
	       <form action="yjMessage.do?method=search" method="post">
			     	<table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
			     		<tr>
			     			<td width="30%">警察编号：</td>
			     			<td width="30%"><input type="text" id="yjNum1" name="yjNum1" /></td>
			     		</tr>
			     		<tr>
			     			<td>警察名称：</td>
			     			<td><input type="text" id="yjName1" name="yjName1" /></td>
			     		</tr>
			     		<tr>
			     			<td>警察IC卡：</td>
			     			<td><input type="text" id="yjCard" name="yjCard" /></td>
			     		</tr>
			     		<tr>
			     			<td >部门：</td>
			     			<td >
		     				<select id="deptName" >
		     					<logic:iterate id="jl" name="deptList">
		     						<option value="${jl}">${jl}</option>
		     					</logic:iterate>
		     				</select>
		     				</td>
			     		</tr>
			     		<!--  <tr>
			     			<td>服务器名称：</td>
			     			<td><select id="jyId" style="width:130px;" onchange="checkJq1()" />
			     				<logic:notEmpty name="list1">
			     						<option value="null">全部</option>
				     					<logic:iterate id="sqs" name="list1">
				     						<option value="${sqs.serverName}">${sqs.serverName}</option>
				     					</logic:iterate>
			     					</logic:notEmpty>
			     			</select></td>
			     		</tr>
			     		<tr>
			     			<td>所属监区：</td>
			     			<td><select id="jqId" style="width:130px;" disabled="disabled">
			     			             <option value="null">全部</option>
			     			   </select>
			     			 </td>
			     		</tr>-->
			     		<tr>
			     			<td><a href="javascript:void(0);" onclick="addSaveYj();return false;"><img src="images/baocun.gif"></img></td>
			     			<td><a href="yjMessage.do?method=search""><img src="images/fanhui.gif"></img></a></td>
			     		</tr>
			     	</table>
			     	</form>
			     <!--  <object classid="clsid:0185EF68-468E-4380-BA78-8C713E37969A" id="reID" width="0" height="0"></object>-->
      	   <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
    	  <object id="WM1711" name="WM1711" codebase="<%=basePath%>/ocx/WM171.CAB#version=1,0,0,0"  classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object>
  </body>
</html>

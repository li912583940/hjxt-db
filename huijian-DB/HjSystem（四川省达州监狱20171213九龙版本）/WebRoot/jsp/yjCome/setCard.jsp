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
      	<script src="<%=basePath %>js/hjNotice.js" type="text/javascript" ></script>
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
		<!-- <script  language="javascript" for="WM02R" event="cardevent()">
				document.getElementById("frCard").value=document.getElementById("WM02R").GetCardNum();
		 </script> -->
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
		 		//document.getElementById("qsCard").value=document.getElementById("WM1711").FunGetEveData();
		 </script>    
  </head>
   <body onload="openPort();" onunload="colsePort();">
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：罪犯签到 </span></div>
    <form action="yjCome.do?method=search1" method="post">
		      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
		      	 	<tr>
		      	 		<td width="30%">罪犯编号：</td>
		      	 		<td width="30%"><input type="text" id="frNo1" name="frNo1" value="${jlFr.frNo }" disabled="disabled" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>罪犯姓名：</td>
		      	 		<td><input type="text" id="frName1" name="frName1" value="${jlFr.frName}" disabled="disabled"/></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>罪犯IC卡号：</td>
		      	 		<td><input type="text" id="frCard" name="frCard" value="${jlFr.frCard}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td><a href="javascript:void(0);" onclick="updateSaveFr();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="frId" name="frId" value="${jlFr.webId }" /><input type="hidden" id="djId" name="djId" value="${jlHjDj.hjid }" /></td>
		      	 		<td><a id="returnBack" href="yjCome.do?method=search1"><img src="images/fanhui.gif"></img></a></td>
		      	 	</tr>
		      	 </table>
     </form>
     <!-- <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
      	 <object id="WM02R" classid="CLSID:54A61884-0949-41BE-8FB9-DB55BCCE486C" codebase="<%=basePath %>ocx/WM02R.CAB#version=1,0,0,0" style="display: none"></object> -->
      	 
      	 <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
    	  <object id="WM1711" name="WM1711" codebase="<%=basePath%>/ocx/WM171.CAB#version=1,0,0,0"  classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object>
  </body>
</html>

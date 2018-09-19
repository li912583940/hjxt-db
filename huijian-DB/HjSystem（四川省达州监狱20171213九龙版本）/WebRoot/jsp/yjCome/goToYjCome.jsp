<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Flexigrid</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>FlexiGrid/css/flexigrid.css"/>
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.2.6.js"></script>
		<script src="<%=basePath %>js/hjNotice.js" type="text/javascript" ></script>		
		<script  type="text/javascript"> 
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
 
		<script  language="javascript" for="WM1711" event="cardEvent()">
				
		 		//document.getElementById("yjCard").value=document.getElementById("WM1711").FunGetEveData();
		 		var idcard=document.getElementById("WM1711").FunGetEveData();
				//从第6个字符开始，第14个字符结束，截取M1卡卡号，再转成10进制
				idcard1=idcard.substring(6,8);
		 		idcard2=idcard.substring(8,10);
		 		idcard3=idcard.substring(10,12);
		 		idcard4=idcard.substring(12,14);
		 		idcard=idcard1+idcard2+idcard3+idcard4;
				var ten=parseInt(idcard,16);
		 		document.getElementById("yjCardNo").value=ten;
		 		//alert("返回值是:"+idcard);
		 		$.ajax({
		 		      type:"POST",
		 		      url:"yjCome.do",
		 		      data:"method=getYjInfo&yjCardNo="+ten,
		 		      dataType:"json",
		 		      success:callback28   
		 		});
				function callback28(data){
					if(data.length>0){
						document.getElementById("yjNum").value=data[0].yjNum;
						document.getElementById("yjName").value=data[0].yjName;
						fpZw();
					}else{
						alert("请注意：狱警卡信息不存在，请在“狱警信息”菜单中修改狱警信息后再重试，或者在“警察警号”处直接输入警号后，点击保存按钮！");
					}
				}		
		 </script>
	</head>
	<body onload="openPort();" onunload="colsePort();">
		<div id="user_info2"><span>登录姓名：${users.userName }    登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：座位分配 </span></div>
		<form action="yjCome.do?method=search2" method="post">
	 	 	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:80%; margin:0 auto; margin-top:10px;">
					<tr>
						<td colspan="2">罪犯姓名：<input type="text" id="frName" value="${jlHjDj.frName }"/></td>
					</tr>
					<tr>
						<td colspan="2">警察卡号：<input type="text" id="yjCardNo" value="${jlYj.yjCard }"/></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;警察警号：<input type="text" id="yjNum" value="${jlYj.yjNum }"//><font color="red">☆温馨提示：直接输入警号，点击保存也可以分配座位！</font></td>
					</tr>
					<tr>
						<td colspan="2">警察姓名：<input type="text" id="yjName" value="${jlYj.yjName }"/></td>
					</tr>
					<tr>
		      	 		<td><a href="javascript:void(0);" onclick="fpZw();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="hjid" name="hjid" value="${jlHjDj.hjid }" /></td>
		      	 		<td><a href="yjCome.do?method=search"><img src="images/fanhui.gif"></img></a></td>
		      	 	</tr>
					
			</table>
	 </form>
	 <!--  <object classid="clsid:0185EF68-468E-4380-BA78-8C713E37969A" id="reID" width="0" height="0"></object>-->
	 <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
     <object id="WM1711" name="WM1711" codebase="<%=basePath%>/ocx/WM171.CAB#version=1,0,0,0"  classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object>
     <!-- <div style="display: none"><object id="rd" classid="clsid:D2622F14-FC06-4B6A-8CE2-91FA6BD5E998" codebase="<%=basePath %>ocx/ReaderOcx.ocx#version=1,0,0,1"></object></div> -->
    </body>
</html>

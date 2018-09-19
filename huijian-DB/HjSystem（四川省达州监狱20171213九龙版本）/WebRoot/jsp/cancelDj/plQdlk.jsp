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
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/jqueryUI/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/jqueryUI/themes/icon.css" />
		<script src="<%=basePath %>js/jqueryUI/jquery-1.7.2.min.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/jqueryUI/jquery.easyui.min.js"type="text/javascript"> </script>
		<script src="<%=basePath %>js/jqueryUI/locale/easyui.lang-zh_CN.js"type="text/javascript"> </script>
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
			 	function readCard(){
					//var lSnr; //本用于取序列号，但在javascript只是当成dc_card函数的一个临时变量
			 		//st = rd.dc_init(100, 115200);
			 		//if(st <= 0) //返回值小于等于0表示失败
			 		//{
			 		//    alert("初始化失败!");
			 		//	return false;
			 		//}
			 		//st = rd.dc_card(0, lSnr);
			 		 //返回值小于0表示失败
			 		//if(st != 0){
			 		//    alert("读卡失败!");
			 		//    rd.dc_exit();
			 		//	return false;
			 		//}
			 		
			 		//var cardNo=rd.get_bstrRBuffer_asc.substring(6,8)+rd.get_bstrRBuffer_asc.substring(4,6)+rd.get_bstrRBuffer_asc.substring(2,4);
			 		//cardNo=parseInt(cardNo,16);
			 		//关闭端口
			 		//st = rd.dc_exit();
			 		//if(st != 0) {
			 		 //   alert("dc_exit error!");
			 		//    return false;
			 		//}
			 		var cardNo=rd.ReadCardID();
			 		//cardNo=cardNo.substring(6,8)+cardNo.substring(4,6)+cardNo.substring(2,4);
			 		//cardNo=parseInt(cardNo,16);
			 		
			 		document.getElementById("yjCardNo").value=cardNo;
			 		$.ajax({
			 		      type:"POST",
			 		      url:"cancelDj.do",
			 		      data:"method=getYjInfo&yjCardNo="+cardNo,
			 		      dataType:"json",
			 		      success:callback28   
			 		});

				}
				function callback28(data){
					if(data.length>0){
						document.getElementById("yjNum").value=data[0].yjNum;
						document.getElementById("yjName").value=data[0].yjName;
						savePlqdlk();
					}
				}
		</script>
	</head>
	<body bgcolor="#DDF2FF">
		<div id="user_info2"><span>登录姓名：${users.userName }    登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：返回签到 </span></div>
		<form action="cancelDj.do?method=search2" method="post">
	 	 	 <table style="border-style:solid; border-color:#2A65A1; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
					<logic:iterate id="fr" name="jlHjRec">
						<tr>
							<td colspan="2">罪犯姓名：<input type="text" name="frName1" value="${fr.frName }"/><input type="hidden" name="hjid" value="${fr.webId }" /></td>
						</tr>
					</logic:iterate>
					<tr>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;警察卡号：<input type="text" id="yjCardNo"/><input type="button" value="读卡" onclick="readCard()"/></td>
					</tr>
					<tr>
						<td colspan="2">警察警号：<input type="text" id="yjNum"/></td>
					</tr>
					<tr>
						<td colspan="2">警察姓名：<input type="text" id="yjName"/></td>
					</tr>
					<tr>
		      	 		<td><a href="javascript:void(0);" onclick="savePlqdlk();return false;"><img src="images/baocun.gif"></img></a></td>
		      	 		<td><a href="cancelDj.do?method=search2"><img src="images/fanhui.gif"></img></a></td>
		      	 	</tr>
					
			</table>
	 </form>
	 <div id="info2"></div>
	 <div style="display: none"><object id="rd" classid="clsid:D2622F14-FC06-4B6A-8CE2-91FA6BD5E998" codebase="<%=basePath %>ocx/ReaderOcx.ocx#version=1,0,0,1"></object></div>
    </body>
</html>

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
	</head>
	<body>
		<div id="user_info2"><span>登录姓名：${users.userName }    登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：座位分配 </span></div>
		<form action="yjCome.do?method=search" method="post">
	 	 	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
					<!--  
					<tr>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;警察卡号：<input type="text" id="yjCardNo" disabled="disabled"/><input type="button" value="读卡" onclick="readCard()"/></td>
					</tr>-->
					<tr>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;罪犯姓名：<input type="text"  value="${jlHjDj.frName }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td colspan="2">服务器名称：<select id="jy" style="width:130px" onchange="checkLine();">
														<option value="null">全部</option>
														<logic:iterate id="fwq" name="sysQqServerList">
															<c:if test="${fwq.serverName=='Server1'}"><option value="${fwq.serverName}" selected="selected">${fwq.serverName}</option></c:if>
															<c:if test="${fwq.serverName!='Server1'}"><option value="${fwq.serverName}">${fwq.serverName}</option></c:if>
														</logic:iterate>
													</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;座位名称：<select id="zwNo" style="width:130px">
														<option value="null">全部</option>
														<logic:iterate id="zw" name="sysHjLineList">
															<option value="${zw.lineNo}">${zw.zw}</option>
														</logic:iterate>
													</select>
												</td>
					</tr>
					<tr>
		      	 		<td><a href="javascript:void(0);" onclick="rgfpZw1();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="hjid" name="hjid" value="${jlHjDj.hjid }" /><input type="hidden" id="hjType" name="hjType" value="${jlHjDj.hjType}" /></td>
		      	 		<td><a href="yjCome.do?method=search1"><img src="images/fanhui.gif"></img></a></td>
		      	 	</tr>
					
			</table>
	 </form>
	 <div style="display: none"><object id="rd" classid="clsid:638B238E-EB84-4933-B3C8-854B86140668"></object></div>
    </body>
</html>

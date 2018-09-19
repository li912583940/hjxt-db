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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Flexigrid</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>FlexiGrid/css/flexigrid.css"/>
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.2.6.js"></script>
		<script src="<%=basePath %>js/hjNotice.js" type="text/javascript" ></script>
		<script  type="text/javascript"> 
			 window.setInterval(refreshMeetInfo1, 15000); 
			// window.setInterval(refreshMeetInfo2, 1000); 
			 document.onkeydown   =   function()   
				{       
					if(event.keyCode   ==   8){   
						if(event.srcElement.tagName.toLowerCase()   !=   "input"   
						&&   event.srcElement.tagName.toLowerCase()   !=   "textarea")   
						event.returnValue   =   false;   
					}
				}
				
			 	var basePath='<%=basePath%>';
		</script>
		<script  language="javascript" for="WM02R" event="cardevent()">
				$.ajax({
				      type:"POST",
				      url:'<%=basePath%>'+"yjCome.do",
				      data:"method=fpZw2&cardNum="+document.getElementById("WM02R").GetCardNum(),
				      dataType:"json",
				      success:callback13   
				});
		 </script>
		 <style type="text/css">
		 	 a{text-decoration: NONE}
	 		.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		</style>   
	</head>
	<!--  <body bgcolor="#DDF2FF" onkeydown="return enterSubmit(this,event);" onload="openPort();" onunload="colsePort();">-->
	<body onkeydown="return enterSubmit(this,event);">
		<div id="user_info2"><span>登录姓名：${users.userName }    登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：座位分配 </span></div>
		<form action="yjCome.do?method=search" method="post">
	 	 	<div id="content2">
	 	 			<table>
						<tr>
							<th width="15%"  nowrap="nowrap">操作</th>
							<th width="6%"  nowrap="nowrap">监区</th>
							<th width="6%"  nowrap="nowrap">分管等级</th>
						    <th width="6%"  nowrap="nowrap">罪犯姓名</th>
						    <th width="4%"  nowrap="nowrap">重点犯</th>
							<th width="6%" nowrap="nowrap">会见类型</th>
							<th width="10%" nowrap="nowrap">会见说明</th>
							<th width="4%"  nowrap="nowrap">座位</th>
							<th width="6%"  nowrap="nowrap">罪犯编号</th>
							<th width="16%" nowrap="nowrap" colspan="2">亲属</th>
							<th width="4%"  nowrap="nowrap">会见时长</th>
							<th width="10%"  nowrap="nowrap">登记时间</th>
							<th width="4%"  nowrap="nowrap">授权状态</th>
							<th width="6%"  nowrap="nowrap">操作</th>
							
					</tr>
					<tbody id="hjdjTable">
					<logic:notEmpty name="list1">
						<logic:iterate id="jl" name="list1" indexId="index">
							<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
								<td nowrap="nowrap"><a href="javascript:void(0)" onclick="yjcome1();return false;">自动分配</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="delQd1();return false;">取消分配</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="rgfp1();return false;">人工分配</a><input type="hidden" name="hjid" value="${jl.hjid}"/></td>
								<!-- <td nowrap="nowrap"><a href="javascript:void(0)" onclick="yjcome1();return false;">自动分配</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="delQd1();return false;">取消分配</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="rgfp1();return false;">人工分配</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="setCard();return false;">设置卡号</a><input type="hidden" name="hjid" value="${jl.hjid}"/></td> -->
								<td nowrap="nowrap">${jl.jqName}<c:if test="${index==0}"><input type="hidden" id="isNoPrint" name="isNoPrint" value="0" /></c:if><input type="hidden" name="webId" value="${jl.hjid}" /><input type="hidden" name="fpFlag" value="${jl.fpFlag}" /><input type="hidden" id="cardNum" name="cardNum"/></td>
								<td nowrap="nowrap">${jl.jbName}</td>
								<td nowrap="nowrap"><font color="green">${jl.frName}</font></td>
								<td nowrap="nowrap">
									<c:if test="${jl.stateZdzf=='否'}">否</c:if>
									<c:if test="${jl.stateZdzf!='否'}"><font color="red">是</font></c:if>
								</td>
								<td nowrap="nowrap">
									<c:if test="${jl.hjType==1}">电话会见</c:if>
									<c:if test="${jl.hjType==2}"><font color="red">面对面会见</font></c:if>
									<c:if test="${jl.hjType==3}"><font color="red">视频会见</font></c:if>
									<c:if test="${jl.hjType==4}"><font color="red">帮教</font></c:if>
									<c:if test="${jl.hjType==5}"><font color="red">提审</font></c:if>
								</td>
								<td nowrap="nowrap" >${jl.hjInfo}</td>
								<td nowrap="nowrap">
									<c:if test="${jl.fpFlag==0}"><font color="red">未分配</font></c:if>
									<c:if test="${jl.fpFlag!=0}">${jl.zw}</c:if>
								</td>
							
								<td nowrap="nowrap">${jl.frNo}</td>
								<td nowrap="nowrap" colspan="2">${jl.qsInfo1}</td>
								<td nowrap="nowrap">${jl.hjTime}分钟</td>
								<td nowrap="nowrap">${jl.djTime}</td>
								<td nowrap="nowrap">
									<c:if test="${jl.shState==1}">已授权</c:if>
									<c:if test="${jl.shState==0}"><font color="red">未授权</font></c:if>
								</td>
								<td nowrap="nowrap"><a href="javascript:void(0);" onclick="updateShState();return false;">授权</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="delShState();return false;">取消授权</a></td>
								
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="list1">
							<tr><td colspan="15"><font color="red">没有相关信息</font><input type="hidden" id="isNoPrint" name="isNoPrint" value="0" /><input type="hidden" id="cardNum" name="cardNum"/></td></tr>
					</logic:empty>
					</tbody>
			</table>
	 	 </div>
	 </form>
	  <!--<object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
     <object id="WM02R" classid="CLSID:54A61884-0949-41BE-8FB9-DB55BCCE486C" codebase="<%=basePath %>ocx/WM02R.CAB#version=1,0,0,0" style="display: none"></object> -->
    </body>
</html>

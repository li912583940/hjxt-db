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
		<script src="<%=basePath %>js/wpdj.js" type="text/javascript" ></script>
		<script  type="text/javascript"> 
			 window.setInterval(refreshMeetInfo,15000); 
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
		<div id="lm_info2"><span>当前位置：物品登记 </span></div>
		<form action="wpdj.do?method=search" method="post">
	 	 	<div id="content2">
	 	 			<table>
						<tr>
							<th width="5%" nowrap="nowrap">操作</th>
							<th width="9%" nowrap="nowrap">监区</th>
							<th width="9%" nowrap="nowrap">罪犯编号</th>
							<th width="9%" nowrap="nowrap">罪犯姓名</th>
							<th width="9%" nowrap="nowrap">座位</th>
							<th width="9%" nowrap="nowrap">状态</th>
							<th width="9%" nowrap="nowrap">会见类型</th>
							<th width="10%" nowrap="nowrap">会见说明</th>
							<th width="10%" nowrap="nowrap">登记时间</th>
							<th width="13%" nowrap="nowrap">亲属</th>
							<th width="4%" nowrap="nowrap">物品</th>
						
						</tr>
					<tbody id="hjdjTable">
					<logic:notEmpty name="list1">
						<logic:iterate id="jl" name="list1" indexId="index">
							<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
								<td nowrap="nowrap"><a href="javascript:void(0)" onclick="wpdj(${jl.hjid});return false;">登记物品</a></td>
								<td nowrap="nowrap">${jl.jqName}<c:if test="${index==0}"><input type="hidden" id="isNoPrint" name="isNoPrint" value="1" /></c:if><input type="hidden" name="webId" value="${jl.hjid}" /><input type="hidden" name="fpFlag" value="${jl.fpFlag}" /></td>
								<td nowrap="nowrap">${jl.frNo}</td>
								<td nowrap="nowrap">${jl.frName}</td>
								<td nowrap="nowrap">
									<c:if test="${jl.fpFlag==0}"><font color="red">未分配</font></c:if>
									<c:if test="${jl.fpFlag!=0}">${jl.zw}</c:if>
								</td>
								<td nowrap="nowrap">
									<c:if test="${jl.fpTzfrFlag==0}"><font color="red">未通知</font></c:if>
									<c:if test="${jl.fpTzfrFlag==1}">已通知</c:if>
								</td>
								<td nowrap="nowrap">
									<c:if test="${jl.hjType==1}">考察</c:if>
									<c:if test="${jl.hjType==2}">宽管</c:if>
									<c:if test="${jl.hjType==3}">普管</c:if>
									<c:if test="${jl.hjType==4}">帮教</c:if>
									<c:if test="${jl.hjType==5}">提审</c:if>
								</td>
								<td nowrap="nowrap">${jl.hjInfo }</td>
								<td nowrap="nowrap">${jl.djTime}</td>
								<td nowrap="nowrap">${jl.qsInfo1}</td>
								<td nowrap="nowrap">
									<c:if test="${jl.infoWp==1}"><font color="red">有</font></c:if>
									<c:if test="${jl.infoWp!=1}">无</c:if>
								</td>
							</tr>
						</logic:iterate>
						
					</logic:notEmpty>
					<logic:empty name="list1">
							<tr><td colspan="11"><font color="red">没有相关信息</font><input type="hidden" id="isNoPrint" name="isNoPrint" value="0" /></td></tr>
					</logic:empty>
					</tbody>
			</table>
	 	 </div>
	 </form>
    </body>
</html>

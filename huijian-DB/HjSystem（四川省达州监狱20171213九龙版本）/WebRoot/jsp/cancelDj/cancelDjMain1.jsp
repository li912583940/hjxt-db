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
		<script src="<%=basePath %>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
		<div id="lm_info2"><span>当前位置：返回签到 </span></div>
		<html:form action="cancelDj.do?method=search2" method="post">
	 	 	<div id="content2">
	 	 			<table>
	 	 				<tr>
	 	 					<td colspan="3" width="32%" nowrap="nowrap">开始时间：<html:text  property="beginTime"  styleId="callTimeStart" value="${todayBegin}" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
	 	 					<td colspan="3" width="32%" nowrap="nowrap">结束时间：<html:text  property="endTime"    styleId="callTimeEnd" value="${todayEnd}" onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" ></html:text></td>
	 	 					<td colspan="3" width="32%" nowrap="nowrap">罪犯编号：<html:text  property="frNo"  styleId="frNo" ></html:text></td>
	 	 				</tr>
	 	 				<tr>
	 	 					<td colspan="3" width="32%" nowrap="nowrap">罪犯姓名：<html:text  property="frName"  styleId="frName"></html:text></td>
	 	 					<td colspan="3" width="32%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;监区：<html:select property="jq" style="width:130px">
	 	 													<html:option value="null">全部</html:option>
	 	 													<logic:notEmpty name="jlJqList">
	 	 														<logic:iterate id="jq" name="jlJqList">
	 	 															<html:option value="${jq.jqNo}">${jq.jqName}</html:option>
	 	 														</logic:iterate>
	 	 													</logic:notEmpty>
	 	 													</html:select>
	 	 										</td>
	 	 					<td colspan="3" width="32%" nowrap="nowrap"><html:button value="查  询" property="cx" onclick="checkDj1();"/>&nbsp;&nbsp;<html:button value="批量签到" property="cx" onclick="plqdlk();"/></td>
	 	 				</tr>
						<tr>
								<th nowrap="nowrap" height="25px" width="12%">开始时间</th>
								<th nowrap="nowrap" height="25px" width="12%">结束时间</th>
								<th nowrap="nowrap" height="25px" width="8%">通话时长</th>
								<th nowrap="nowrap" height="25px" width="10%">座位</th>
								<th nowrap="nowrap" height="25px" width="11%">监区名称</th>
								<th nowrap="nowrap" height="25px" width="11%">罪犯编号</th>
								<th nowrap="nowrap" height="25px" width="10%">罪犯姓名</th>
								<th nowrap="nowrap" height="25px" width="12%">会见人信息</th>
								<th nowrap="nowrap" height="25px" width="10%">操作</th>
						</tr>
					<tbody id="hjdjTable">
					<logic:notEmpty name="page" property="list">
						<logic:iterate id="rs" name="page" property="list" indexId="index">
							<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
								 <td nowrap="nowrap" height="25px">${rs.callTimeStart }</td>
					   	 		 <td nowrap="nowrap" height="25px">${rs.callTimeEnd }</td>
					  		 	 <td nowrap="nowrap" height="25px">${rs. callTimeLen}</td>
					   	 		 <td nowrap="nowrap" height="25px">${rs. zw}</td>
					   	 		 <td nowrap="nowrap" height="25px">${rs.jqName}</td>
					   	 		 <td nowrap="nowrap" height="25px">${rs.frNo}</td>
					   	 		 <td nowrap="nowrap" height="25px">${rs.frName}</td>
					   	 		 <td nowrap="nowrap" height="25px">${rs.qsInfo}</td>
					   	         <td nowrap="nowrap" height="25px"><input type="checkbox" name="selectId" onclick="selectRecord1(${rs.webId})" value="${rs.webId}"/></td>
							</tr>
						</logic:iterate>
						<tr>
							<td colspan="9">
								<div id="info2"><page:page pageNo="${page.pageNo}" recordCount="${page.recordCount}" url="cancelDj.do?method=search3" pageSize="${page.pageSize}"/></div>
							</td>
						</tr>
					</logic:notEmpty>
					<logic:empty name="page" property="list">
							<tr><td colspan="9"><font color="red">没有相关信息</font></td></tr>
					</logic:empty>
					</tbody>
			</table>
			<input type="hidden" id="rowIndex" />;
	 	 </div>
	 </html:form>
    </body>
</html>

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
			 	
			 	 function enterSubmit(src,e){
			         if(window.event)
				        keyPressed = window.event.keyCode; // IE
			         else
			            keyPressed = e.which; // Firefox
			         if(keyPressed==13){ 
			        	 checkDj();                     
			             return false;
			        }
				}
		</script>
	</head>
	<body>
		<div id="user_info2"><span>登录姓名：${users.userName }    登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：登记查询 </span></div>
		<html:form action="cancelDj.do?method=search" method="post">
	 	 	<div id="content2">
	 	 			<table>
	 	 				<tr>
	 	 					<td colspan="4" width="24%" nowrap="nowrap">开始时间：<html:text  property="beginTime"  styleId="callTimeStart" value="${todayBegin}" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
	 	 					<td colspan="4" width="24%" nowrap="nowrap">结束时间：<html:text  property="endTime"    styleId="callTimeEnd" value="${todayEnd}" onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" ></html:text></td>
	 	 					<td colspan="4" width="24%" nowrap="nowrap">会见状态：<html:select property="state" style="width:130px">
	 	 													<html:option value="null">全部</html:option>
	 	 													<html:option value="0">未会见</html:option>
	 	 													<html:option value="1">已会见</html:option>
	 	 													<html:option value="2">取消会见</html:option>
	 	 												</html:select></td>
	 	 					  <td colspan="3" width="24%" nowrap="nowrap">会见类型：<html:select property="hjType" style="width:130px">
	 	 													<html:option value="null">全部</html:option>
	 	 													<html:option value="1">电话会见</html:option>
	 	 													<html:option value="2">面对面会见</html:option>
	 	 													<html:option value="3">视频会见</html:option>
	 	 													</html:select>
	 	 													
	 	 					</td>
	 	 				
	 	 				</tr>
	 	 					<tr><td colspan="4" width="24%" nowrap="nowrap">罪犯编号：<html:text  property="frNo"  styleId="frNo"  onkeydown="return enterSubmit(this,event);"></html:text></td> 
	 	 					<td colspan="4" width="24%" nowrap="nowrap">罪犯姓名：<html:text  property="frName"  styleId="frName"  onkeydown="return enterSubmit(this,event);"></html:text></td>
	 	 					<td colspan="4" width="24%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;监区：<html:select property="jq" style="width:130px">
	 	 													<html:option value="null">全部</html:option>
	 	 													<logic:notEmpty name="jlJqList">
	 	 														<logic:iterate id="jq" name="jlJqList">
	 	 															<html:option value="${jq.jqNo}">${jq.jqName}</html:option>
	 	 														</logic:iterate>
	 	 													</logic:notEmpty>
	 	 													</html:select>
	 	 										&nbsp;&nbsp;&nbsp;<html:button value="查询" property="cx" onclick="checkDj();"/></td>
	 	 				</tr>
						<tr>
							<th width="10%" nowrap="nowrap">监区</th>
							<th width="10%" nowrap="nowrap">罪犯编号</th>
							<th width="10%" nowrap="nowrap">会见编号</th>
							<th width="10%" nowrap="nowrap">学员姓名</th>
							<th width="20%" nowrap="nowrap">亲属</th>
							<!--<th width="8%" nowrap="nowrap">物品</th>-->
							<th width="8%" nowrap="nowrap">会见时长</th>
							<th width="8%" nowrap="nowrap">会见类型</th>
							<!--<th width="8%" nowrap="nowrap">会见说明</th>-->
							<th width="14%" nowrap="nowrap">登记时间</th>
							<th width="2%" nowrap="nowrap">登记人</th>
							<th width="8%" nowrap="nowrap">取消原因</th>
						</tr>
					<tbody id="hjdjTable">
					<logic:notEmpty name="page" property="list">
						<logic:iterate id="jl" name="page" property="list" indexId="index">
							<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
								<td nowrap="nowrap">${jl.jqName }<c:if test="${index==0}"><input type="hidden" id="isNoPrint" name="isNoPrint" value="1" /></c:if><input type="hidden"  name="webId" value="${jl.hjid}" /><input type="hidden" name="fpFlag" value="${jl.fpFlag}" /></td>
								<td nowrap="nowrap">${jl.frNo }</td>
								<td nowrap="nowrap">${jl.hjIndex }</td>
								<td nowrap="nowrap">${jl.frName }</td>
								<td nowrap="nowrap">${jl.qsInfo1 }</td>
								<!--<td nowrap="nowrap">${jl.infoWp}</td>-->
								<td nowrap="nowrap">${jl.hjTime }分钟</td>
								<td nowrap="nowrap">
									<c:if test="${jl.hjType==1}">电话会见</c:if>
									<c:if test="${jl.hjType==2}"><font color="red">面对面会见</font></c:if>
									<c:if test="${jl.hjType==3}"><font color="red">视频会见</font></c:if>
									<c:if test="${jl.hjType==4}"><font color="red">帮教</font></c:if>
									<c:if test="${jl.hjType==5}"><font color="red">提审</font></c:if>
								</td>
								<!--<td nowrap="nowrap">${jl.hjInfo}</td>-->
								<td nowrap="nowrap">${jl.djTime }</td>
								<td nowrap="nowrap">${jl.djUser}</td>
								<td nowrap="nowrap">${jl.cancelInfo }</td>
							</tr>
						</logic:iterate>
						<tr>
							<td colspan="12">
								<div id="info2"><page:page pageNo="${page.pageNo}" recordCount="${page.recordCount}" url="cancelDj.do?method=search1" pageSize="${page.pageSize}"/></div>
							</td>
						</tr>
					</logic:notEmpty>
					<logic:empty name="page" property="list">
							<tr><td colspan="12"><font color="red">没有相关信息</font></td></tr>
					</logic:empty>
					</tbody>
			</table>
	 	 </div>
	 </html:form>
    </body>
</html>

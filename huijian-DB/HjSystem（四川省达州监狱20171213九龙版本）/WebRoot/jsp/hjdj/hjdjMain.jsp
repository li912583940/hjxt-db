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
		<script src="<%=basePath %>js/hjdj.js" type="text/javascript" ></script>
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
		<div id="lm_info2"><span>当前位置：会见登记 </span></div>
		<form action="hjdj.do?method=search" method="post">
	 	 	<div id="content2">
	 	 			<table>
						<tr>
							<th width="6%" nowrap="nowrap">监区</th>
							<th width="6%" nowrap="nowrap">会见编号</th>
							<th width="6%" nowrap="nowrap">罪犯编号</th>
							<!-- <th width="8%" nowrap="nowrap">罪犯档案号</th> -->
							<th width="6%" nowrap="nowrap">罪犯姓名</th>
							<th width="8%" nowrap="nowrap">亲属</th>
							<th width="6%" nowrap="nowrap">会见时长</th>
							<th width="7%" nowrap="nowrap">登记时间</th>
							<th width="4%" nowrap="nowrap">登记人</th>
							<th width="4%" nowrap="nowrap">座位</th>
							<!--  <th width="8%" nowrap="nowrap">通知状态</th>-->
							<th width="4%" nowrap="nowrap">审批状态</th>
							<th width="4%" nowrap="nowrap">会见类型</th>
							<th width="10%" nowrap="nowrap">会见说明</th>
						</tr>
					<tbody id="hjdjTable">
					<logic:notEmpty name="page" property="list">
						<logic:iterate id="jl" name="page" property="list" indexId="index">
							<tr onclick="selectRow();">
								<td nowrap="nowrap">${jl.jqName }<c:if test="${index==0}"><input type="hidden" id="isNoPrint" name="isNoPrint" value="1" /></c:if><input type="hidden"  name="webId" value="${jl.hjid}" /><input type="hidden" name="fpFlag" value="${jl.fpFlag}" /><input type="hidden" id="webId1" name="webId1" value="${jl.frNo }" /></td>
								<td nowrap="nowrap">${jl.hjIndex }</td>
								<td nowrap="nowrap">${jl.frNo }</td>
								<!-- <td nowrap="nowrap"><font color="red">${jl.frDah }</font></td> -->
								<td nowrap="nowrap">${jl.frName }</td>
								<td>${jl.qsInfo1 }</td>
								<td nowrap="nowrap">${jl.hjTime }分钟</td>
								<td nowrap="nowrap">${jl.djTime }</td>
								<td nowrap="nowrap">${jl.djUser}</td>
								<td nowrap="nowrap">
									<c:if test="${jl.fpFlag==0}"><font color="red">未分配</font></c:if>
									<c:if test="${jl.fpFlag!=0}">${jl.zw}</c:if>
								</td>
							<!-- 	<td nowrap="nowrap">
									<c:if test="${jl.sfyzState==2}">已发送</c:if>
									<c:if test="${jl.sfyzState!=2}"><font color="red">未发送</font></c:if>
								</td> -->
								
								<td nowrap="nowrap">
									<c:if test="${jl.state=='待审批'}"><font color="red">待审批</font></c:if>
									<c:if test="${jl.state!='待审批'}">审批通过</c:if>
								</td>
								<td nowrap="nowrap">
									<c:if test="${jl.hjType==1}">电话会见</c:if>
									<c:if test="${jl.hjType==2}"><font color="red">面对面会见</font></c:if>
									<c:if test="${jl.hjType==3}"><font color="red">视频会见</font></c:if>
									<c:if test="${jl.hjType==4}"><font color="red">帮教</font></c:if>
									<c:if test="${jl.hjType==5}"><font color="red">提审</font></c:if>
								</td>
								<td>${jl.hjInfo }</td>
							</tr>
						</logic:iterate>
						<tr>
							<td colspan="12">
								<div id="info2"><page:page pageNo="${page.pageNo}" recordCount="${page.recordCount}" url="hjdj.do?method=search1" pageSize="${page.pageSize}"/></div>
							</td>
						</tr>
					</logic:notEmpty>
					<logic:empty name="page" property="list">
							<tr><td colspan="12"><font color="red">没有相关信息</font><input type="hidden" id="isNoPrint" name="isNoPrint" value="0" /></td></tr>
					</logic:empty>
					</tbody>
			</table>
	 	 </div>
	 </form>
 	 <!-- <div style="position:absolute;margin-top:20px;margin-left:70px;height:25px; line-height:25px;"><input type="button" onclick="goHjdj();" value="登   记"/><c:if test="${hJClient.paramData3==1}">&nbsp;&nbsp;<input type="button" onclick="document.all.WebBrowser.ExecWB(8,1)" value="打印设置"/>&nbsp;&nbsp;<input type="button" onclick="printZjz();" value="打印准见证"/></c:if>&nbsp;&nbsp;<input type="button" onclick="deleteDj();" value="取消登记"/>&nbsp;&nbsp;<input type="button" onclick="updateHjDj();" value="修改会见登记"/>&nbsp;&nbsp;<input type="button" value="发起停止办理会见通知" onclick="TZHandle();" /><input type="hidden" id="selectRow" name="selectRow" value="-1"/></div> -->
     <div style="position:absolute;margin-top:20px;margin-left:70px;height:25px; line-height:25px;"><input type="button" onclick="goHjdj();" value="登   记"/><c:if test="${hJClient.paramData3==1}">&nbsp;&nbsp;<input type="button" onclick="document.all.WebBrowser.ExecWB(8,1)" value="打印设置"/>&nbsp;&nbsp;<input type="button" onclick="printZjz();" value="打印准见证"/></c:if>&nbsp;&nbsp;<input type="button" onclick="deleteDj();" value="取消登记"/>&nbsp;&nbsp;<input type="button" onclick="updateHjDj();" value="修改会见登记"/><input type="hidden" id="selectRow" name="selectRow" value="-1"/></div>
     <div style="position:absolute;margin-top:60px;margin-left:70px;height:25px; line-height:25px;font-size:12px;">
	        	<c:if test="${hJClient3.paramData2==0}">未开启</c:if>
	        	<c:if test="${hJClient3.paramData2==1}"><font color="red">已开启</font></c:if>联动门禁&nbsp;&nbsp;&nbsp;&nbsp;<a href="hjdj.do?method=search&paramData2=${hJClient3.paramData2}">切换</a>
	 <input type="hidden" id="paramhjType" name="paramhjType" value="${hJClient3.paramData2}"/>
	 </div>
     <div id="info" style="display:none"></div>	
     <object id="WebBrowser" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"
                viewastext>
            </object>
    </body>
</html>

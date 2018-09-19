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
			 window.setInterval(refreshMeetInfo3, 30000); 
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
			function changeColor(obj) {
				var f = obj.checked;
				var chkColor = "#ff00ff"; //选中后颜色
				var noColor = "#fff";  //取消选中后的颜色
				if(f)
				obj.parentElement.parentElement.style.backgroundColor = chkColor;
				else
				obj.parentElement.parentElement.style.backgroundColor = noColor;
			}
			 					 
		</script>
		 <style type="text/css">
		 	 a{text-decoration: NONE}
	 		.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		</style>   
	</head>
	<body onkeydown="return enterSubmit(this,event);">
		<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：座位分配 </span></div>
		<html:form action="yjCome.do?method=search" method="post">
	 	 	<div id="content2">
	 	 			<table>
	 	 				<tr>
	 	 					<td colspan="3" nowrap="nowrap">罪犯姓名：<html:text property="frName" styleId="frName"/></td>
	 	 					<td colspan="3" nowrap="nowrap">罪犯编号：<html:text property="frNo" styleId="frNo"/></td>
	 	 					<td colspan="3" nowrap="nowrap">监区：<html:select property="jq" style="width:130px">
	 	 													<html:option value="null">全部</html:option>
	 	 													<logic:notEmpty name="jlJqList">
	 	 														<logic:iterate id="jq" name="jlJqList">
	 	 															<html:option value="${jq.jqNo}">${jq.jqName}</html:option>
	 	 														</logic:iterate>
	 	 													</logic:notEmpty>
	 	 													</html:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="查       询"  onclick="yjComeSearch();"/><!-- &nbsp;&nbsp;<input type="button" value="批量签到" onclick="plQd1();" style="color:red;"/> --></td>
	 	 				</tr>
						<tr>
							<th width="10%"  nowrap="nowrap">操作</th>
							<th width="10%"  nowrap="nowrap">监区</th>
						    <th width="10%"  nowrap="nowrap">罪犯姓名</th>
							<th width="8%" nowrap="nowrap">会见类型</th>
							<th width="14%" nowrap="nowrap">会见说明</th>
							<th width="8%"  nowrap="nowrap">座位</th>
							<th width="12%"  nowrap="nowrap">罪犯编号</th>
							<th width="16%" nowrap="nowrap">亲属</th>
							<th width="8%"  nowrap="nowrap">选中</th>
					</tr>
					<tbody id="hjdjTable">
					<logic:notEmpty name="list1">
						<logic:iterate id="jl" name="list1" indexId="index">
							<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
								<td nowrap="nowrap"><a href="javascript:void(0)" onclick="yjcome();return false;"><font color="Fuchsia">警察签到</font></a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="delQd();return false;">取消签到</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="rgfp();return false;"><font color="Olive">人工分配</font></a><input type="hidden" name="hjid" value="${jl.hjid}"/></td>
								<td nowrap="nowrap">${jl.jqName}<c:if test="${index==0}"><input type="hidden" id="isNoPrint" name="isNoPrint" value="1" /></c:if><input type="hidden" name="webId" value="${jl.hjid}" /><input type="hidden" name="fpFlag" value="${jl.fpFlag}" /></td>
								<td nowrap="nowrap">${jl.frName}</td>
								<td nowrap="nowrap" name="hjType">
									<c:if test="${jl.hjType==1}">考察</c:if>
									<c:if test="${jl.hjType==2}"><font color="red">宽管</font></c:if>
									<c:if test="${jl.hjType==3}"><font color="red">普管</font></c:if>
									<c:if test="${jl.hjType==4}"><font color="red">帮教</font></c:if>
									<c:if test="${jl.hjType==5}"><font color="red">提审</font></c:if>
								</td>
								<td nowrap="nowrap">${jl.hjInfo}</td>
								<td nowrap="nowrap">
									<c:if test="${jl.fpFlag==0}"><font color="red">未分配</font></c:if>
									<c:if test="${jl.fpFlag!=0}">${jl.zw}</c:if>
								</td>
								<td nowrap="nowrap">${jl.frNo}</td>
								<td nowrap="nowrap">${jl.qsInfo1}</td>
								<!--<td nowrap="nowrap">
									<c:if test="${jl.infoWp==1}"><font color="red">有</font></c:if>
									<c:if test="${jl.infoWp!=1}"><font>无</font></c:if>
								</td>  -->
								<td nowrap="nowrap">
									<c:if test="${jl.fpFlag==0}"><input type="checkbox" name="selectId" onclick="selectRecord(${jl.hjid})" value="${jl.hjid}"/></c:if>
									<c:if test="${jl.fpFlag==1}"><input type="checkbox" name="selectId" disabled="disabled" value="${jl.hjid}" /></c:if>
									<!--<c:if test="${jl.hjType==2}"><input type="checkbox" name="selectId" disabled="disabled" value="${jl.hjid}" /></c:if>-->
								</td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="list1">
							<tr><td colspan="9"><font color="red">没有相关信息</font><input type="hidden" id="isNoPrint" name="isNoPrint" value="0" /></td></tr>
					</logic:empty>
					</tbody>
			</table>
			<input type="hidden" id="rowIndex" />;
	 	 </div>
	 </html:form>
    </body>
</html>

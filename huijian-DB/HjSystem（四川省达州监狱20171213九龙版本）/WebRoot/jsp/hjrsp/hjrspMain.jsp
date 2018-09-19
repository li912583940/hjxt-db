<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<script src="<%=basePath %>js/hjrsp.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
			var basePath='<%=basePath%>';
	  </script>
	 <style type="text/css">
		.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		 a{text-decoration: NONE}
		</style>   
  </head>
  <body>
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：会见网络审批 </span></div>
       <html:form action="hjrsp.do?method=search" method="post" >
        	<div id="content2">
		     	<table  border="0" cellspacing="0" cellpadding="0">
		     		<tr>		
		     			<td colspan="2" height="25px" nowrap="nowrap" width="28%">开始时间：<html:text  property="callTimeStart"  styleId="callTimeStart" value="${todayBegin}" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
		     	    	<td colspan="2" height="25px" nowrap="nowrap" width="28%">结束时间：<html:text  property="callTimeEnd"   styleId="callTimeEnd" value="${todayEnd}" onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
		     			<td colspan="2" height="25px" nowrap="nowrap" width="28%">审批状态：<html:select property="spState"><html:option value="null">全部</html:option><html:option value="1">待审批</html:option><html:option value="2">审批通过</html:option><html:option value="3">审批不通过</html:option></html:select></td>
		     				
		     			<td colspan="2" height="25px" nowrap="nowrap" width="16%">罪犯姓名：<html:text  property="frName"  styleId="frName" ></html:text>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="chaxun();return false;"><img src="images/chaxun.gif"></img></a></td>
		     	    	
		     		</tr>
		     		<tr>
		     			<th width="14%" height="25px" nowrap="nowrap">所属监区<input type="hidden" id="groupNo" name="groupNo" value="${users.groupNo }" /> <input type="hidden" id="userNo" name="userNo" value="${users.userNo}" /> <input type="hidden" id="userName" name="userName" value="${users.userName}" /></th>
				     	<th width="14%" height="25px" nowrap="nowrap">罪犯姓名</th>
			     		<th width="14%" height="25px" nowrap="nowrap">审批申请人</th>
			     		<th width="14%" height="25px" nowrap="nowrap">申请时间</th>
			     		<th width="14%" height="25px" nowrap="nowrap">审批部门</th>			     		
			     		<th width="14%" height="25px" nowrap="nowrap">审批人</th>
			     		<th width="14%" height="25px" nowrap="nowrap">审批状态</th>
			     		<th width="16%" height="25px" nowrap="nowrap">操作</th>
			  
		     		</tr>
		     		  <logic:notEmpty name="page" property="list">
		     				<logic:iterate id="fr" name="page" property="list">
		     					<tr>
		     					<td width="14%" height="25px" nowrap="nowrap">${fr.jqName}  <input type="hidden" id="spId" name="spId" value="${fr.spId}" /><input type="hidden" id="spGroupNo" name="spGroupNo" value="${fr.spGroupNo}" /><input type="hidden" id="spState1" name="spState1" value="${fr.spState}" /><input type="hidden" id="spUser" name="spUser" value="${fr.spUser}" /></td>
		     					<td width="14%" height="25px" nowrap="nowrap">${fr.frName}</td>
		     					<td width="14%" height="25px" nowrap="nowrap">${fr.spTjUser}[${fr.spTjUserName}]</td>
		     					<td width="14%" height="25px" nowrap="nowrap">${fr.spTjTime}</td>
		     					<td width="14%" height="25px" nowrap="nowrap">${fr.spGroupName}</td>
		     					<td width="14%" height="25px" nowrap="nowrap">${fr.spUserName}</td>
		     					<td width="14%" height="25px" nowrap="nowrap">
			     					<c:if test="${fr.spState==1}"><font color="red">待审批</font></c:if>
			     					<c:if test="${fr.spState==2}">通过</c:if>
			     					<c:if test="${fr.spState==3}">不通过</c:if>
		     					</td>
		     					
		     					<td width="16%" height="25px" nowrap="nowrap">
		     						<a href="javascript:void(0)" onclick="seeHjSp(${fr.spId});return false;">查看详情</a>&nbsp;&nbsp;
		     						<c:if test="${fr.spState==1}">
		     							<a href="javascript:void(0)" onclick="updateHjSp(${fr.spId});return false;"><font color="green">我要审批</font></a>
		     						</c:if>
		     						<c:if test="${fr.spState==2}">
		     							审批完毕
		     						</c:if> 
		     						<c:if test="${fr.spState==3}">
		     							审批完毕
		     						</c:if>   
		     						
		     					</td>
		     					
		     					
		     				</tr>
		     				</logic:iterate>
		     				
		     				<tr>
		     					<td colspan="8" height="25px">:<page:page pageNo="${page.pageNo}" pageSize="${page.pageSize}" recordCount="${page.recordCount}" url="hjrsp.do?method=search1"></page:page></td>
		     				</tr>
		     		</logic:notEmpty>
		     		<logic:empty name="page" property="list">
		     				<tr>
		     					<td colspan="8" height="25px"><font color="red">没有相关信息</font></td>
		     				</tr>
		     		</logic:empty>
		     	</table>
		     </div>
		 </html:form>
  </body>
</html>

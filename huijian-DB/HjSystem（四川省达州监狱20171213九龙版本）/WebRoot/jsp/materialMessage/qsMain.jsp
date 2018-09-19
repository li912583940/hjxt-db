<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<script src="<%=basePath %>js/materialMessage.js" type="text/javascript" ></script>
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
	 </script>
	 <style type="text/css">
		   a{text-decoration: NONE}
		</style>   
  </head>
  <body>
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：罪犯管理 </span></div>
       <form action="materialMessage.do?method=search" method="post">
        	<div id="content">
		     	<table  border="0" cellspacing="0" cellpadding="0" >
		     			<tr>
			     			<th nowrap="nowrap">证件类型</th>
			     			<th nowrap="nowrap">证件号码</th>
			     			<th nowrap="nowrap">是否禁止</th>
			     			<!-- <th nowrap="nowrap">证件物理号</th> -->
			     			<th nowrap="nowrap">罪犯名称</th>
			     			<th nowrap="nowrap">亲属姓名</th>
			     			<th nowrap="nowrap">IC卡号</th>
			     			<th nowrap="nowrap">关系</th>
			     			<th nowrap="nowrap">性别</th>
			     			<th nowrap="nowrap">住址</th>
			     			<th nowrap="nowrap">电话号码</th>
			     			<th nowrap="nowrap">备注</th>
			     			<th nowrap="nowrap">审批状态</th>
			     			<th nowrap="nowrap">添加人/时间</th>
			     			<th nowrap="nowrap">操作</th>
		     			</tr>
		     			<logic:notEmpty name="qsList">
		     				<logic:iterate id="qs" name="qsList">
		     				  <tr>
		     				  	<td width="4%" nowrap="nowrap">
		     				  		<c:if test="${qs.qsZjlb==1}">身份证</c:if>
					     			<c:if test="${qs.qsZjlb==2}">警官证</c:if>
					     			<c:if test="${qs.qsZjlb==3}">工作证</c:if>
					     			<c:if test="${qs.qsZjlb==4}">其他</c:if>
		     				  	
		     				  	</td>
		     				    <td width="9%" nowrap="nowrap">${qs.qsSfz }</td>
		     				    <td width="4%" nowrap="nowrap">
		     				    		<c:if test="${qs.hjStopTime==null}">否</c:if>
					     				<c:if test="${qs.hjStopTime!=null}"><font color="red">是</font></c:if>
					     		</td>
		     				    <!-- <td width="9%" nowrap="nowrap">${qs.qsSfzWlh }</td> -->
		     					<td width="6%" nowrap="nowrap">${qs.frName }</td>
				     			<td width="6%" nowrap="nowrap">${qs.qsName}</td>
				     			<td width="6%" nowrap="nowrap">${qs.qsCard }</td>
				     			<td width="4%" nowrap="nowrap">${qs.gx }</td>
				     			<td width="2%" nowrap="nowrap">${qs.xb }</td>
				     			<td width="15%" nowrap="nowrap">${qs.dz }</td>
				     			<td width="6%" nowrap="nowrap">${qs.tele }</td>
				     			<td width="9%" nowrap="nowrap">${qs.bz }</td>
				     			<td width="4%" nowrap="nowrap">
										<c:if test="${qs.spState==1}">已通过</c:if>
					     				<c:if test="${qs.spState==0}"><font color="red">未通过</font></c:if>
								</td>
								<td width="6%" nowrap="nowrap">${qs.spUserNo}<br>${qs.spTime}</td>
				     			<td width="5%" nowrap="nowrap"><a href="javascript:void(0)" onclick="updateFrQs();return false;">修改</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="delFrQs();return false;">删除</a><input type="hidden" id="frNo1" name="frNo1" value="${qs.frNo }" /><input type="hidden" id="webId" name="webId" value="${qs.webId }" /></td>
		     				 </tr>
		     				</logic:iterate>
		     			</logic:notEmpty>
		     			<logic:empty name="qsList">
		     				<tr>
		     					<td colspan="13"><font color="red">没有相关信息</font></td>
		     				</tr>
		     			</logic:empty>
		     </table>
		     </div>
		     <a href="javascript:void(0)" onclick="addFrQs();return false;" style="position:absolute;margin-top:20px;margin-left:70px;height:25px; line-height:25px;"><img src="images/zengjia.gif"></img></a>
		     <a href="materialMessage.do?method=search&frNo3=${frNo3}&frName2=${frName2}&jy2=${jy2}&jq2=${jq2}&jbNo2=${jbNo2}&pageNo2=${pageNo2}&stateZdzf2=${stateZdzf2}&state2=${state2}&frCard2=${frCard2}" style="position:absolute;margin-top:20px;margin-left:120px;height:25px; line-height:25px;"><img src="images/fanhui.gif"></img></a>
		   <!--   <c:if test="${sysParam.paramData1==1}"><a href="materialMessage.do?method=updateDownQs&frNo3=${frNo3}&frName2=${frName2}&jy2=${jy2}&jq2=${jq2}&jbNo2=${jbNo2}&pageNo2=${pageNo2}&frNo=${frNo}" style="position:absolute;margin-top:20px;margin-left:180px;height:25px; line-height:25px;">更新数据</a></c:if> -->
		     <a href="javascript:void(0)" onclick="updateDownQs1();" style="position:absolute;margin-top:20px;margin-left:180px;height:25px; line-height:25px;">更新数据</a>

		     
		     <input type="hidden" id="frNo" name="frNo" value="${frNo}" />
		     <input type="hidden" id="frNo3" name="frNo3" value="${frNo3}"/>
		     <input type="hidden" id="jy" name="jy" value="${jy2}"/>
		     <input type="hidden" id="jq" name="jq" value="${jq2}"/>
		     <input type="hidden" id="jbNo" name="jbNo" value="${jbNo2}"/>
		     <input type="hidden" id="monitorFlag" name="monitorFlag" value="${monitorFlag2}"/>
		     <input type="hidden" id="state" name="state" value="${state2}"/>
		     <input type="hidden" id="frName" name="frName" value="${frName2}"/>
		     <input type="hidden" id="frCard" name="frCard" value="${frCard2}"/>
		     <input type="hidden" id="pageNo" name="pageNo" value="${pageNo2}"/>
		     <input type="hidden" id="stateZdzf" name="stateZdzf" value="${stateZdzf2}"/>
		     <input type="hidden" id="state" name="state" value="${state2}"/>
      </form>
  </body>
</html>

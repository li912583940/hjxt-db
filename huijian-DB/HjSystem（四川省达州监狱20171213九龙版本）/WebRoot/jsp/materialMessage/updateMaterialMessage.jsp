<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
		</script>
		<script  language="javascript" for="WM1711" event="cardEvent()">
			var idcard=document.getElementById("WM1711").FunGetEveData();
			//从第6个字符开始，第14个字符结束，截取M1卡卡号，再转成10进制
			idcard1=idcard.substring(6,8);
	 		idcard2=idcard.substring(8,10);
	 		idcard3=idcard.substring(10,12);
	 		idcard4=idcard.substring(12,14);
	 		idcard=idcard4+idcard3+idcard2+idcard1;
			var ten=parseInt(idcard,16);
			document.getElementById("frCard").value=ten;
		 </script>   
  </head>
   <!--  <body onload="openPort1();" onunload="colsePort1();">
   <body>-->
   <body>
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：罪犯管理 </span></div>
    <form action="materialMessage.do?method=search" method="post">
		      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
		      	 	<tr>
		      	 		<td width="30%">罪犯编号：</td>
		      	 		<td width="30%"><input type="text" id="frNo1" name="frNo1" value="${jlFr.frNo }" disabled="disabled" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>罪犯姓名：</td>
		      	 		<td><input type="text" id="frName1" name="frName1" value="${jlFr.frName}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>罪犯IC卡号：</td>
		      	 		<td><input type="text" id="frCard1" name="frCard1" value="${jlFr.frCard}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>会见剩余次数：</td>
		      	 		<td><input type="text" id="hjleft" name="hjleft" value="${jlFr.hjLeft}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>服务器名称：</td>
		      	 		<td>
				      	 		<select id="jy1" style="width:130px" onchange="checkJq1();">
			      	 				<logic:iterate id="sqs" name="sysQqServerList">
			      	 					<c:if test="${sqs.serverName == jlFr.jy}">
			      	 						<option value="${sqs.serverName }" selected="selected">${sqs.serverName }</option>
			      	 					</c:if>
			      	 					<c:if test="${sqs.serverName != jlFr.jy}">
			      	 						<option value="${sqs.serverName }">${sqs.serverName }</option>
			      	 					</c:if>
			      	 				</logic:iterate>
      	 						</select>
      	 				</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>所属监区：</td>
				      	<td>
					      	<select id="jq1" style="width:130px">
			      	 				<logic:iterate id="jq" name="jlJqList">
			      	 					<c:if test="${jq.jqNo == jlFr.jq}">
			      	 						<option value="${jq.jqNo}" selected="selected">${jq.jqName}</option>
			      	 					</c:if>
			      	 					<c:if test="${jq.jqNo!= jlFr.jq}">
			      	 						<option value="${jq.jqNo}">${jq.jqName}</option>
			      	 					</c:if>
			      	 				</logic:iterate>
	      	 				</select>
      	 				</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>分管等级：</td>
		      	 		<td>
		      	 			<select id="jb1" style="width:130px">
		      	 				<logic:iterate id="jljb" name="jlJbList">
		      	 					    <c:if test="${jljb.jbNo == jlFr.jbNo}">
			      	 						<option value="${jljb.jbNo}" selected="selected">${jljb.jbName}</option>
			      	 					</c:if>
			      	 					<c:if test="${jljb.jbNo != jlFr.jbNo}">
			      	 						<option value="${jljb.jbNo}">${jljb.jbName}</option>
			      	 				   </c:if>
		      	 				</logic:iterate>
	      	 				</select>
		      	 		</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>会见级别：</td>
		      	 		<td>
		      	 			<select id="hjJb" style="width:130px" onchange="hjST();">
		      	 					    <c:if test="${jlFr.hjJb==-1}">
			      	 						<option value="-1" selected="selected">禁止</option>
			      	 						<option value="1">正常</option>
			      	 					</c:if>
			      	 				   <c:if test="${jlFr.hjJb==1}">
			      	 						<option value="-1">禁止</option>
			      	 						<option value="1" selected="selected">正常</option>
			      	 				   </c:if>
	      	 				</select>
		      	 		</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>会见禁止日期：</td>
		      	 		<td>
	      	 				<c:if test="${jlFr.hjJb==-1}">
	      	 					<input  id="hjStopTime"   value="${jlFr.hjStopTime }" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
		      	 			</c:if>
		      	 			<c:if test="${jlFr.hjJb!=-1}">
		      	 				<input  id="hjStopTime"   value="${jlFr.hjStopTime }" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" disabled="disabled"/>
		      	 			</c:if>		      	 			
		      	 		</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>会见禁止说明：</td>
		      	 		<td>
	      	 				<c:if test="${jlFr.hjJb==-1}">
	      	 					<input  id="hjStopSM"   value="${jlFr.hjStopSM }"  />
		      	 			</c:if>
		      	 			<c:if test="${jlFr.hjJb!=-1}">
		      	 				<input  id="hjStopSM"   value="${jlFr.hjStopSM }"  disabled="disabled"/>
		      	 			</c:if>
		      	 		</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>国籍：</td>
		      	 		<td><input type="text" id="frGj" name="frGj" value="${jlFr.frGj}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>入监时间：</td>
		      	 		<td><input type="text" id="infoRjsj" name="infoRjsj" value="${jlFr.infoRjsj}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>罪名：</td>
		      	 		<td><input type="text" id="infoZm" name="infoZm" value="${jlFr.infoZm}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>刑期：</td>
		      	 		<td><input type="text" id="infoXq" name="infoXq"  value="${jlFr.infoXq}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>出生日期：</td>
		      	 		<td><input type="text" id="infoCsrq" name="infoCsrq"  value="${jlFr.infoCsrq}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>住址：</td>
		      	 		<td><input type="text" id="infoHome" name="infoHome" value="${jlFr.infoHome}" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>是否重点监控：</td>
		      	 		<td>
		      	 			<select id="monitorFlag" style="width:130px">
		      	 					    <c:if test="${jlFr.monitorFlag==1}">
			      	 						<option value="1" selected="selected">是</option>
			      	 						<option value="0">否</option>
			      	 					</c:if>
			      	 					<c:if test="${jlFr.monitorFlag==0}">
			      	 						<option value="0" selected="selected">否</option>
			      	 						<option value="1">是</option>
			      	 				   </c:if>
			      	 				   <c:if test="${jlFr.monitorFlag!=1 && jlFr.monitorFlag!=0}">
			      	 						<option value="0" selected="selected">否</option>
			      	 						<option value="1">是</option>
			      	 				   </c:if>
	      	 				</select>
		      	 		</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>是否重点罪犯：</td>
		      	 		<td>
		      	 			<!-- <select id="stateZdzf" style="width:130px" onchange="changeZdzfType()"> -->
		      	 			<select id="stateZdzf" style="width:130px">
		      	 					    <c:if test="${jlFr.stateZdzf==1}">
			      	 						<option value="1" selected="selected">是</option>
			      	 						<option value="0">否</option>
			      	 					</c:if>
			      	 					<c:if test="${jlFr.stateZdzf==0}">
			      	 						<option value="0" selected="selected">否</option>
			      	 						<option value="1">是</option>
			      	 				   </c:if>
			      	 				   <c:if test="${jlFr.stateZdzf!=1 && jlFr.stateZdzf!=0}">
			      	 						<option value="0" selected="selected">否</option>
			      	 						<option value="1">是</option>
			      	 				   </c:if>
	      	 				</select>
		      	 		</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>备注：</td>
		      	 		<td>
		      	 		<!-- 
		      	 		<c:if test="${jlFr.stateZdzf==1}"><input  id="zdzfType"   value="${jlFr.zdzfType }" /></c:if>
			      	 	<c:if test="${jlFr.stateZdzf!=1}"><input  id="zdzfType"   value="${jlFr.zdzfType }" disabled="disabled"/></c:if>
		      	 		</td> -->
		      	 		<input  id="zdzfType"   value="${jlFr.zdzfType }" />

		      	 		</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>罪犯状态：</td>
		      	 		<td><select id="state" onchange="setCysj();" style="width:130px">
		      	 				<c:if test="${jlFr.state==0}">
		      	 					<option value="0" selected="selected">正在服刑</option>
		      	 					<option value="1">出狱</option>
		      	 				</c:if>
		      	 				<c:if test="${jlFr.state==1}">
		      	 					<option value="0">正在服刑</option>
		      	 					<option value="1" selected="selected">出狱</option>
		      	 				</c:if>
		      	 			</select>
		      	 		</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td>出狱时间：</td>
		      	 		<td>
		      	 			<c:if test="${jlFr.state==1}">
		      	 				<input type="text" id="outTime" name="outTime" value="${Fr.outTime}" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
		      	 			</c:if>
		      	 			<c:if test="${jlFr.state==0}">
		      	 				<input type="text" id="outTime" name="outTime"  onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" disabled="disabled"/>
		      	 			</c:if>
		      	 		</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td><a href="javascript:void(0);" onclick="updateSaveFr();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="frId" name="frId" value="${jlFr.webId }" /></td>
		      	 		<td><a id="returnBack" href="materialMessage.do?method=search&frNo3=${frNo3}&frName2=${frName2}&jy2=${jy2}&jq2=${jq2}&jbNo2=${jbNo2}&pageNo2=${pageNo2}&stateZdzf2=${stateZdzf2}&state2=${state2}&frCard2=${frCard2}"><img src="images/fanhui.gif"></img></a></td>
		      	 	</tr>
		      	 </table>
     </form>
    <!-- <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
      	 <object id="WM02R" classid="CLSID:54A61884-0949-41BE-8FB9-DB55BCCE486C" codebase="<%=basePath %>ocx/WM02R.CAB#version=1,0,0,0" style="display: none"></object>
      	 <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
    	  <object id="WM1711" name="WM1711" codebase="<%=basePath%>/ocx/WM171.CAB#version=1,0,0,0"  classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object>  -->
  </body>
</html>

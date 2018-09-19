<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>提交审批</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>FlexiGrid/css/flexigrid.css"/>
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.2.6.js"></script>
		<script src="<%=basePath %>js/hjdj.js" type="text/javascript" ></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/jqueryUI/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/jqueryUI/themes/icon.css" />
		<script src="<%=basePath %>js/jqueryUI/jquery-1.7.2.min.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/jqueryUI/jquery.easyui.min.js"type="text/javascript"> </script>
		<script src="<%=basePath %>js/jqueryUI/locale/easyui.lang-zh_CN.js"type="text/javascript"> </script>
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
		<style type="text/css">
		.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		 a{text-decoration: NONE}
		</style> 
		
		 <script  language="javascript" for="WM1711" event="cardEvent()">
		 		var idcard=document.getElementById("WM1711").FunGetEveData();
			 	//从第6个字符开始，第14个字符结束，截取M1卡卡号，再转成10进制
		 		idcard=idcard.substring(6,14);
		 		var ten=parseInt(idcard,16);
		 		document.getElementById("qsCard").value=ten;
		 		//截取结束
		 </script>
  </head>
  
  <body>
  		<div id="user_info2"><span>登录姓名：${users.userName }    登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：会见审批 </span></div>
		<form action="hjdj.do?method=search2" method="post">
	 	 	<div id="content2">
	 	 			<table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px; margin-left:5%">
						<tr>
							<th width="25%" nowrap="nowrap">监区</th>
							<th width="25%" nowrap="nowrap">罪犯编号</th>
							<th width="25%" nowrap="nowrap">罪犯姓名</th>
							<th width="25%" nowrap="nowrap">审批原因</th>
							
						</tr>
						<tr >
								<td nowrap="nowrap">${hjsp.jqName }</td>
								<td nowrap="nowrap">${hjsp.frNo } <input type="hidden" id="frNo" value="${hjsp.frNo }" /></td>
								<td nowrap="nowrap">${hjsp.frName }</td>
								<td nowrap="nowrap">${hjsp.spReason }</td>
								
						</tr>
					
			</table>
			<table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px; margin-left:5%">
						<tr>
							<th width="8%" nowrap="nowrap">亲属姓名</th>
							<th width="8%" nowrap="nowrap">证件类别</th>
							<th width="8%" nowrap="nowrap">证件号码</th>
							<th width="8%" nowrap="nowrap">关系</th>
							<th width="8%" nowrap="nowrap">是否特批亲属</th>
							<th width="8%" nowrap="nowrap">审批原因</th>
							<th width="8%" nowrap="nowrap">审批备注</th>
							<!-- <th width="8%" nowrap="nowrap">人脸注册状态</th> -->
							<th width="8%" nowrap="nowrap">操作</th>
							<!-- <th width="8%" nowrap="nowrap">操作二</th> -->
							<!-- <th width="10%" nowrap="nowrap">审批备注</th> -->
						</tr>
						
					<logic:notEmpty name="spqslist">
						<logic:iterate id="jl" name="spqslist">
							<tr>
								<td nowrap="nowrap">${jl.qsName } <input type="hidden" name="webId" value="${jl.webId}" ><input type="hidden" name="qsName" value="${jl.qsName}" > </td>
								<td nowrap="nowrap">
									<c:if test="${jl.qsZjlb==1}">身份证</c:if>
					     			<c:if test="${jl.qsZjlb==2}">警官证</c:if>
					     			<c:if test="${jl.qsZjlb==3}">工作证</c:if>
					     			<c:if test="${jl.qsZjlb==4}">其他</c:if>
								</td>
								<td nowrap="nowrap">${jl.qsSFZ }<input type="hidden" name=qsSFZs value="${jl.qsSFZ} " ></td>
								<td nowrap="nowrap">${jl.qsGx }</td>
								<c:if test="${jl.special==0}"><td nowrap="nowrap">否</td></c:if>
								<c:if test="${jl.special==1}"><td nowrap="nowrap"><font color="red">是</font></td> </c:if>
								<td nowrap="nowrap">${jl.spReason }</td>
								<td nowrap="nowrap">${jl.spbz }</td>
								<!-- <td nowrap="nowrap">
				     					<c:if test="${jl.faceState==1}">已注册</c:if>
					     				<c:if test="${jl.faceState==0}"><font color="red">未注册</font></c:if>
					     				<c:if test="${jl.faceState==2}"><font color="red">中途取消注册</font></c:if>
					     				<c:if test="${jl.faceState==3}">人脸模板已存在</c:if>
					     				<c:if test="${jl.faceState==4}"><font color="red">注册超时</font></c:if>
				     			</td> -->
								<c:if test="${jl.special==0}"><td nowrap="nowrap"><input type="button" value="填写审批原因" onclick="addspyy();" /></td></c:if>
								<c:if test="${jl.special==1}"><td nowrap="nowrap"><input type="button" value="添加审批亲属" onclick="addtpqs();" /></td> </c:if>
								<!-- <td nowrap="nowrap"><input type="button" style="color:red;" value="注册人脸" onclick="addFace2();" /></td> -->
								
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<tr>
						<td colspan="2" nowrap="nowrap">审批部门：
							<select id="spbm" onchange="checkSpUser();">
								<logic:notEmpty name="bmlist">
								<option value="null">全部</option>
		                   		  	<logic:iterate id="bm" name="bmlist">
		                   		  			
		                   		  			<option value="${bm.groupNo}">${bm.groupName}</option>
		                   		 	</logic:iterate>
		                   		 </logic:notEmpty>
		                   		 <logic:empty name="bmlist">
		                   		 	<option value="null">没有下级审批部门</option>
		                   		 </logic:empty>
      	 					</select>
						
						</td>
						<td colspan="2">
		     				   部门成员：
		     				   <c:if test="${spUserIsNodisable==0}"> 
		     				   		<select id="spUser" disabled="true">
		   									 <option value="null">全部</option>
		     				 	    </select>
		     				  </c:if>
		     				  <c:if test="${spUserIsNodisable==1}"> 
		     				   		<select id="spUser">
		   									 <option value="null">全部</option>
		   									 <logic:notEmpty name="list1">
		   									 	<logic:iterate id="li" name="list1">
		   									 		<option value="${li.userNo}">${li.userName}</option>
		   									 	</logic:iterate>
		   									 </logic:notEmpty>
		     				 	    </select>
		     				  </c:if>
		     				  
		     			</td>
						<td colspan="4" nowrap="nowrap"><input type="button" value="提交"  onclick="tjhjsp();" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回"  onclick="goHjDj1();" /><input type="hidden" id="hjid" value="${hjsp.hjid} " /><input type="hidden" id="spid" value="${hjsp.spId} " /></td>
					</tr>
					
			</table>
				<div id="load" style="display:none;position:absolute;margin-top:1%;position:absolute;z-index:2;margin-left:45%"></div>		
	 	 </div>
	 </form>
	<!--  <object width="0px" height="0px" id="IDCard2" name="IDCard2"  codebase="<%=basePath %>ocx/SynCardOcx.CAB#version=1,0,0,1" classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039">
					</object>
	 <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
   	 <object id="WM1711" name="WM1711" codebase="<%=basePath%>/ocx/WM171.CAB#version=1,0,0,0"  classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object> -->
	 <div id="info2"></div>
  </body>
</html>

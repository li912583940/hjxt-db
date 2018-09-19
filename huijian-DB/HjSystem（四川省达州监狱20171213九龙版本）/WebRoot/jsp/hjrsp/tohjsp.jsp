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
		<script src="<%=basePath %>js/hjrsp.js" type="text/javascript" ></script>
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
  </head>
  
  <body>
  		<div id="user_info2"><span>登录姓名：${users.userName }    登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：会见网络审批 </span></div>
		<form action="hjdj.do?method=search2" method="post">
	 	 	<div id="content2">
	 	 			<table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; margin:0 auto; margin-top:20px; margin-left:2%;font-size:20px;font-family:微软雅黑;">
						<tr style="height:80px">
							<th width="10%" nowrap="nowrap">监区</th>
							<th width="10%" nowrap="nowrap">罪犯编号</th>
							<th width="10%" nowrap="nowrap">罪犯姓名</th>
							<th width="10%" nowrap="nowrap">提交审批时原因</th>
							<th width="10%" nowrap="nowrap">填写审批备注</th>
						</tr>
						<tr style="height:80px">
								<td nowrap="nowrap">${hjsp.jqName }</td>
								<td nowrap="nowrap">${hjsp.frNo }</td>
								<td nowrap="nowrap">${hjsp.frName }</td>
								<td nowrap="nowrap">${hjsp.spReason }</td>
								<td nowrap="nowrap"><input type="text" id="frRemarks" value="${hjsp.spRemarks }" onblur="frLikai(this.value);return false;" /></td>
						</tr>
					
			</table>
			<table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; margin:0 auto; margin-top:30px; margin-left:2%;font-size:15px;font-family:微软雅黑;">
						<tr>
							<th width="10%" nowrap="nowrap">亲属姓名 <input type="hidden" id="qsLinShiWebId" value="-1" ></th>
							<th width="10%" nowrap="nowrap">证件类别</th>
							<th width="10%" nowrap="nowrap">证件号码</th>
							<th width="6%" nowrap="nowrap">关系</th>
							<th width="6%" nowrap="nowrap">是否特批亲属</th>
							<th width="8%" nowrap="nowrap">审批原因</th>
							<th width="8%" nowrap="nowrap">提交审批时备注</th>
							<th width="8%" nowrap="nowrap">填写审批备注</th>
							
						</tr>
						
					<logic:notEmpty name="spqslist">
						<logic:iterate id="jl" name="spqslist">
							<tr >
								<td nowrap="nowrap">${jl.qsName } <input type="hidden" name="webId" value="${jl.webId} " > </td>
								<td nowrap="nowrap">
									<c:if test="${jl.qsZjlb==1}">身份证</c:if>
					     			<c:if test="${jl.qsZjlb==2}">警官证</c:if>
					     			<c:if test="${jl.qsZjlb==3}">工作证</c:if>
					     			<c:if test="${jl.qsZjlb==4}">其他</c:if>
								</td>
								<td nowrap="nowrap">${jl.qsSFZ }</td>
								<td nowrap="nowrap">${jl.qsGx }</td>
								<c:if test="${jl.special==0}"><td nowrap="nowrap">否</td></c:if>
								<c:if test="${jl.special==1}"><td nowrap="nowrap"><font color="red">是</font></td> </c:if>
								<td>${jl.spReason }</td>
								<td>${jl.spbz }</td>
								<c:if test="${jl.special==0}"><td nowrap="nowrap"><input type="text" id="qsRemarks" name="qsRemarks" value="${jl.spRemarks }" onfocus="huoqu(${jl.webId});return false;" onblur="qsLikai(this.value);return false;"/></td></c:if>
								<c:if test="${jl.special==1}"><td nowrap="nowrap"><input type="text" id="qsRemarks" name="qsRemarks" value="${jl.spRemarks }" onfocus="huoqu(${jl.webId});return false;" onblur="qsLikai(this.value);return false;"/></td></c:if>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<tr>
						<td colspan="1" nowrap="nowrap">
							审批结果： <select id="spjg" onchange="spjg1()">
										<option value="1" >同意</option>
										<option value="2">不同意</option>
									  </select>
						</td>
						<td  colspan="1" nowrap="nowrap">
							<logic:notEmpty name="bmlist">
								继续审批：<select id="jxsp" onchange="jxsp1()" >
											<option value="2">否</option>
											<option value="1">是</option>
										 </select>
							</logic:notEmpty>
							<logic:empty name="bmlist">
			                   	继续审批：<select id="jxsp" disabled="disabled">
			                   				<option value="2">否</option>
			                   			 </select>
			                </logic:empty>			 
						 </td>
						<td colspan="1" nowrap="nowrap">审批部门：
							<select id="spbm" onchange="checkSpUser();"  disabled="true">
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
						 <input type="hidden" id="spjb" value="${hjsp.spJb} " >
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
						<td colspan="3" nowrap="nowrap"><input type="button" value="提交"  onclick="tjhjsp();" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="返回"  onclick="goHjSp();" /><input type="hidden" id="hjid" value="${hjsp.hjid} " /><input type="hidden" id="spid" value="${hjsp.spId} " />
					    </td>
					</tr>
					
			</table>
						
	 	 </div>
	 </form>
	 <object width="0px" height="0px" id="IDCard2" name="IDCard2"  codebase="<%=basePath %>ocx/SynCardOcx.CAB#version=1,0,0,1" classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039">
					</object>
	 <div id="info2"></div>
  </body>
</html>

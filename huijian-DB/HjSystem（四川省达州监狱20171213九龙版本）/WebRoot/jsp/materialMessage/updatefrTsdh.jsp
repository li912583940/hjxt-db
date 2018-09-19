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
  </head>
   <body>
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：罪犯管理 </span></div>
    <form action="materialMessage.do?method=checkTpDh&frNo=${jlFr.frNo}" method="post">
      	 <table style="border-style:solid; border-color:#e6e6e6; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td width="30%">罪犯编号：</td>
      	 		<td width="30%"><input type="text" id="frNo1" name="frNo1" value="${jlFr.frNo }" disabled="disabled" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td>罪犯姓名：</td>
      	 		<td><input type="text" id="frName1" name="frName1" value="${jlFr.frName }" disabled="disabled" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td>特批电话号码：</td>
      	 		<td><input type="text" id="tpTele" name="tpTele" value="${jlQqTpdh.tpTele}" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td>通话人姓名：</td>
      	 		<td><input type="text" id="tpxm" name="tpxm" value="${jlQqTpdh.tpxm}" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td>与通话人的关系：</td>
      	 		<td>
      	 			<select id="tpgx" style="width:130px;">
      	 			   <logic:iterate id="qsgx" name="qsGxList">
				         <c:if test="${jlQqTpdh.tpgx==qsgx}">
				         	<option value="${qsgx}" selected="selected">${qsgx}</option>
				         </c:if>
				           <c:if test="${jlQqTpdh.tpgx!=qsgx}">
				         	<option value="${qsgx}">${qsgx}</option>
				         </c:if>
				     </logic:iterate>
      	 			</select>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td>特批次数：</td>
      	 		<td>
      	 			<c:if test="${jlQqTpdh.tpcs==jlQqTpdh.sycs}">
      	 			<input type="text" id="tpcs" name="tpcs" value="${jlQqTpdh.tpcs}" />
      	 			</c:if>
      	 			<c:if test="${jlQqTpdh.tpcs!=jlQqTpdh.sycs}">
      	 			<input type="text" id="tpcs" name="tpcs" value="${jlQqTpdh.tpcs}" disabled="disabled" />
      	 			</c:if>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td>特批时长：</td>
      	 		<td><input type="text" id="tpsc" name="tpsc" value="${jlQqTpdh.tpsc}" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td><a href="javascript:void(0);" onclick="updateSaveSaveFrTpdh();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="tpid" name="tpid" value="${jlQqTpdh.tpid }" /></td>
      	 		<td><a href="materialMessage.do?method=checkTpDh&frNo=${jlFr.frNo}"><img src="images/fanhui.gif"></img></a></td>
      	 	</tr>
      	  </table>
     </form>
  </body>
</html>

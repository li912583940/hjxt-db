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
		<script src="<%=basePath %>js/hjdj.js" type="text/javascript" ></script>
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
			    var basePath='<%=basePath %>';
		</script>   
  </head>
   <body>
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：会见登记 </span></div>
    <form action="hjdj.do?method=search" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td width="30%">家属信息：</td>
      	 		<td width="30%"><input type="text" id="qsInfo" name="qsInfo" value="${hjDjVO.qsInfo1 }" disabled="disabled"/></td>
      	 	</tr>
      	 	<tr>
      	 		<td>罪犯姓名：</td>
      	 		<td><input type="text" id="frName" name="frName" value="${hjDjVO.frName }" disabled="disabled"/></td>
      	 	</tr>
      	 	<tr>
      	 		<td>监区：</td>
      	 		<td>
      	 			<logic:notEmpty name="list">
      	 				<logic:iterate id="jl" name="list">
      	 					<c:if test="${jl.jqNo==hjDjVO.jqNo}"><input type="text" id="jqName" name="jqName" value="${jl.jqName}" disabled="disabled" /></c:if>
      	 				</logic:iterate>
      	 			</logic:notEmpty>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td>会见时长：</td>
      	 		<td><input type="text" id="hjTime" name="hjTime" value="${hjDjVO.hjTime }" disabled="disabled"/></td>
      	 	</tr>
      	 	<tr>
      	 		<td>登记时间：</td>
      	 		<td><input type="text" id="djTime" name="djTime" value="${hjDjVO.djTime }" disabled="disabled"/></td>
      	 	</tr>
      	 	<tr>
      	 		<td>取消原因：</td>
      	 		<td><textarea style="width:130px" id="cancelInfo"></textarea></td>
      	 	</tr>
      	 	<tr>
      	 		<td><a href="javascript:void(0);" onclick="cancel();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="djId" name="djId" value="${hjDjVO.hjid}" /></td>
      	 		<td><a href="hjdj.do?method=search"><img src="images/fanhui.gif"></img></a></td>
      	 	</tr>
      	 </table>
      	</form> 
  </body>
</html>

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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>FlexiGrid/css/flexigrid.css"/>
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
		</script>
	</head>
	<body>
	 	 	<div id="content2">
	 	 			<table>
	 	 				
						<tr>
							<th width="33%"  nowrap="nowrap">序号</th>
							<th width="33%"  nowrap="nowrap">罪犯姓名</th>
							<th width="33%"  nowrap="nowrap">返回时间</th>
						  
					    </tr>
					
					<logic:notEmpty name="mv">
						<logic:iterate id="jl" name="mv" indexId="index">
							<tr>
								<td>${index+1}</td>
								<td>${jl.frName }</td>
								<td>${jl.xx}</td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					
			</table>
	 	 </div>	
    </body>
</html>

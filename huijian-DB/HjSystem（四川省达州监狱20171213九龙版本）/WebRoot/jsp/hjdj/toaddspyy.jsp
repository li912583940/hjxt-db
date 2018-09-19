<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="<%=path %>/css/layout.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath %>FlexiGrid/css/flexigrid.css" />
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.2.6.js"></script>
		<script src="<%=basePath %>js/hjdj.js" type="text/javascript"></script>
		<script   language="javascript"> 
			
				var basePath='<%=basePath%>';
				
				
		</script>

		     
	</head>
	<body >
		
		
			<table>
				
				<tr>
					<td colspan="2">
						审批原因：
						<select id="spyy" style="width:300px">
      	 					<option value="1" selected="selected">亲属及监护人未在规定时间或未带有效身份证件会见</option>
							<option value="2">非首次会见的港澳台和外国籍亲属、监护人</option>
							<option value="3">首次会见的港澳台和外国籍亲属、监护人</option>
							<option value="4">受委托的律师、亲友会见</option>
							<option value="5">帮教单位、社会团体会见</option>
							<option value="6">B类重点罪犯会见</option>
							<option value="7">C类重点罪犯会见</option>
							<option value="8">A类重点罪犯会见</option>
							<option value="9">超次数、人数、时间的会见</option>
							<option value="10">区监狱局批准的会见</option>
							<option value="11">公检法的会见</option>
      	 			</select>
					</td>

				</tr>
				<tr>
					<td colspan="2">
						审批备注：<input type="text" id="spbz" name="spbz" style="width:300px"/>
						
					</td>

				</tr>

				<tr>
					<td>
						<input type="button" value="保存并退出"  onclick="savespyy()" />  <input type="hidden" id="webId1" name="webId1" value="${hjspqs.webId}"  /><input type="hidden" id="hjid" name="hjid" value="${hjspqs.hjid}"  />
					</td>
					<td>
						<input type="button" value="取消" onclick="qxtpqs();" />
					</td>
				</tr>


			</table>
			
		
		
	</body>
</html>

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
		<script src="<%=basePath %>js/jySet.js" type="text/javascript" ></script>
		<script type="text/javascript">
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
	 		.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		</style> 
  </head>
  <body>
	  <div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：会见审批参数</span></div>
	       <form action="" method="post">
	        	
			     	<table  style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:30%; margin:0 auto; margin-top:20px;">
			     		
			     		<c:if test="${hjspParam.totalSwitch==1}">
				     		<tr>
				     			<td colspan="2">
				     					<input type="checkbox" id="totalSwitch" name="totalSwitch" onclick="total()" checked="checked" />&nbsp;&nbsp; 是否开启审批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				     			</td>
				     		</tr>
				     		<tr>
				     			<td colspan="2">
				     				<c:if test="${hjspParam.monNumSwitch==1}">
			     						<input type="checkbox" id="monNumSwitch" name="monNumSwitch"  checked="checked" />&nbsp;&nbsp; 罪犯本月会见次数已用完<br />
			     					</c:if>
				     				<c:if test="${hjspParam.monNumSwitch!=1}">
			     						<input type="checkbox" id="monNumSwitch" name="monNumSwitch"  />&nbsp;&nbsp; 罪犯本月会见次数已用完<br />
			     					</c:if>
			     					
			     					<c:if test="${hjspParam.jbSwitch==1}">
			     						<input type="checkbox" id="jbSwitch" name="jbSwitch"   checked="checked"  />&nbsp;&nbsp; 罪犯级别不允许会见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
			     					</c:if>
			     					<c:if test="${hjspParam.jbSwitch!=1}">
			     						<input type="checkbox" id="jbSwitch" name="jbSwitch"   />&nbsp;&nbsp; 罪犯级别不允许会见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
			     					</c:if>
			     					
			     					<c:if test="${hjspParam.banSwitch==1}">
			     						<input type="checkbox" id="banSwitch" name="banSwitch"   checked="checked" />&nbsp;&nbsp; 罪犯当前被禁止会见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
			     					</c:if>
			     					<c:if test="${hjspParam.banSwitch!=1}">
			     						<input type="checkbox" id="banSwitch" name="banSwitch"   />&nbsp;&nbsp; 罪犯当前被禁止会见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
			     					</c:if>
			     					
			     					<c:if test="${hjspParam.jqBanSwitch==1}">
			     						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="jqBanSwitch" name="jqBanSwitch"   checked="checked"/>&nbsp;&nbsp; 服刑人员当前监区不是会见日<br />
			     					</c:if>
			     					<c:if test="${hjspParam.jqBanSwitch!=1}">
			     						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="jqBanSwitch" name="jqBanSwitch"/>&nbsp;&nbsp; 服刑人员当前监区不是会见日<br />
			     					</c:if>
			     					
			     					<c:if test="${hjspParam.qsgxSwitch==1}">
			     						<input type="checkbox" id="qsgxSwitch" name="qsgxSwitch"   checked="checked" />&nbsp;&nbsp; 亲属关系为“其他”&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
			     					</c:if>
			     					<c:if test="${hjspParam.qsgxSwitch!=1}">
			     						<input type="checkbox" id="qsgxSwitch" name="qsgxSwitch"   />&nbsp;&nbsp; 亲属关系为“其他”&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
			     					</c:if>
				     			</td>
				     		</tr>
			     		</c:if>
			     		
			     		<c:if test="${hjspParam.totalSwitch!=1}">
			     			<tr>
			     				<td colspan="2">
			     					<input type="checkbox" id="totalSwitch" name="totalSwitch" onclick="total()"  />&nbsp;&nbsp; 是否开启审批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			     				</td>
			     			</tr>
			     			<tr>
			     				<td colspan="2">
			     					<input type="checkbox" id="monNumSwitch" name="monNumSwitch" disabled="disabled" />&nbsp;&nbsp; 罪犯本月会见次数已用完<br />
			     					<input type="checkbox" id="jbSwitch" name="jbSwitch"  disabled="disabled" />&nbsp;&nbsp; 罪犯级别不允许会见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
			     					<input type="checkbox" id="banSwitch" name="banSwitch"  disabled="disabled" />&nbsp;&nbsp; 罪犯当前被禁止会见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
			     					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="jqBanSwitch" name="jqBanSwitch"  disabled="disabled"/>&nbsp;&nbsp; 服刑人员当前监区不是会见日<br />
			     					<input type="checkbox" id="qsgxSwitch" name="qsgxSwitch"  disabled="disabled" />&nbsp;&nbsp; 亲属关系为“其他”&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
			     				</td>
			     			</tr>
			     		</c:if>
			     		
			     		
				     	<tr>
				     		<td> <input type="button" value="保存" onclick="saveHjSpParam()" /></td>
				     		<td><input type="button" value="返回" onclick="cancelHjSpParam()" /></td>
				     	</tr>
				     
			     	</table>
			    
			    
			 </form>
	</body>
</html>

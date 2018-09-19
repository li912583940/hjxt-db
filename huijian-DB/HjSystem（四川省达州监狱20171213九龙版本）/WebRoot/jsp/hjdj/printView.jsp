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
  	<script type="text/javascript">
  				//window.setInterval(printZjz, 1500); 
  				function printZjz(){
  					window.print();
  					window.close();
  				}
  			</script>
  </head>
  
  <body onload="printZjz();">
    	<form action="" method="post">
    		<logic:iterate id="list" name="list1" indexId="index">
    			<c:if test="${index==0}"><font style="margin-left: 29px;"> ${list}</font><br/></c:if>
    			<c:if test="${index==1}"><font style="margin-left: 35px;"> ${list}</font><br/></c:if>
    			<c:if test="${index!=0 && index!=1}">${list}<br/></c:if>
    		</logic:iterate>
    	</form>
  </body>
</html>

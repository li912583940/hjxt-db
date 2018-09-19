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
		<script src="<%=basePath %>js/wpdj.js" type="text/javascript" ></script>
		<script language="javascript" type="text/javascript" src="<%=basePath %>js/textselect.js"></script>
		<script type="text/javascript" language="javascript" > 
			 document.onkeydown   =   function(){       
					if(event.keyCode   ==   8) {
						if(event.srcElement.tagName.toLowerCase()   !=   "input"  &&   event.srcElement.tagName.toLowerCase()   !=   "textarea")   
						event.returnValue   =   false;   
					}   
				}
				function printZjz(){
  					window.print();
  					window.close();
  				}
		</script>
		 
  </head>
   <body onload="printZjz();">
       <form action="wpdj.do?method=search" method="post">
        	<div id="content2">
		     	<table  border="0" cellspacing="0" cellpadding="0">
		        	<tr>
		      	 		<td width="20%">罪犯编号：${jlHjDj.frNo }</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td width="20%">罪犯姓名：${jlHjDj.frName} </td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td width="20%">监区： ${jlHjDj.jqName }</td>
		      	 	</tr>
		      	 	<logic:notEmpty name="list1">
		      	 		<logic:iterate id="wp" name="list1">
		      	 		  <tr>
		      	 			<td>${wp.wpName}${wp.wpCount}${wp.wpUnit}</td>
		      	 		  </tr>
		      	 		</logic:iterate>
		      	 	</logic:notEmpty>
		      	 	<tr>
		      	 		<td width="20%">登记人：${jlHjDj.wpDjr} </td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td width="20%">登记时间：${jlHjDj.wpDjTime} </td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td width="20%">家属签字：</td>
		      	 	</tr>
		      	 	<tr>
		      	 		<td width="20%">罪犯签字：</td>
		      	 	</tr>
      		 </table>
      		 </div>
      	 </form>
  </body>
</html>

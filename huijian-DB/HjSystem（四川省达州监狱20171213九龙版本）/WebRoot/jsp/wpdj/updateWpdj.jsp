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
				var basePath='<%=basePath%>';
		</script>
		 
  </head>
   <body>
   	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：物品登记 </span></div>
       <form action="wpdj.do?method=search" method="post">
        	<div id="content2">
		     	<table  border="0" cellspacing="0" cellpadding="0">
		        	<tr>
		      	 		<td width="20%">罪犯编号：<input type="text" id="frNo1" name="frNo1" value="${jlHjDj.frNo }" /></td>
		      	 		<td width="20%">罪犯姓名：<input type="text" id="frNo1" name="frNo1" value="${jlHjDj.frName }" /></td>
		      	 		<td width="20%">罪犯监区：<input type="text" id="frNo1" name="frNo1" value="${jlHjDj.jqName }" /></td>
		      	 	</tr>
		      	 	<tr>
		      	 		<th>物品名称</th>
		      	 		<th>物品数量</th>
		      	 		<th>单位</th>
		      	 	</tr>
		      	 	<logic:notEmpty name="list1">
		      	 		<logic:iterate id="wp" name="list1">
		      	 		  <tr>
		      	 			<td>${wp.wpName}</td>
		      	 			<td><input type="text" id="wp${wp.wpNo}" value="${wp.wpCount}" style="width:50px"/></td>
		      	 			<td>${wp.wpUnit}</td>
		      	 		  </tr>
		      	 		</logic:iterate>
		      	 	</logic:notEmpty>
		      	 	<tr>
		      	 		<td><a href="javascript:void(0);" onclick="updateSaveWpdj();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="hjid" value="${jlHjDj.hjid }"/><input type="hidden" id="info" value="${info}"/></td>
		      	 		<td colspan="2"><a id="returnBack" href="wpdj.do?method=search"><img src="images/fanhui.gif"></img></a></td>
		      	 	</tr>
      		 </table>
      		 </div>
      	 </form>
  </body>
</html>

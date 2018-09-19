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
		<script src="<%=basePath %>js/yjABDoorSet.js" type="text/javascript" ></script>
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
	  <div id="lm_info"><span>当前位置：陪同警察管理</span></div>
	       <form action="" method="post">
	        	<div id="content">
			     	<table  cellspacing="0" cellpadding="0">
			     		<tr>
			     			<th nowrap="nowrap">警号</th>
			     			<th nowrap="nowrap">姓名</th>
			     			<th nowrap="nowrap">操作</th>
			     		</tr>
				     	 <logic:notEmpty name="JlYjABDoorList">
				     		<logic:iterate id="yjABDoor" name="JlYjABDoorList">
					     		<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
					     			<td width="16%" nowrap="nowrap">${yjABDoor.yjNo}</td>
					     			<td width="16%" nowrap="nowrap">${yjABDoor.yjName}</td>
					     			<td width="15%" nowrap="nowrap"><a href="javascript:void(0)" onclick="updateYjABDoor();return false;">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="deleteYjABDoor();return false;">删除</a><input type="hidden" id="webId" name="webId" value="${yjABDoor.webId}" /></td>
					     		</tr>
				     		</logic:iterate>
				     	 </logic:notEmpty>
				     	 	<logic:empty name="JlYjABDoorList">
			     			<tr>
			     				<td colspan=3><font color="red">没有相关记录</font></td>
			     			</tr>
			     		</logic:empty>
			     	</table>
			    </div>

			    <a href="yjABDoorMessage.do?method=addYjABDoor" style="position:absolute;margin-top:20px;margin-left:125px;height:25px; line-height:25px;"><img src="images/zengjia.gif"></img></a>
			 </form>
	</body>
</html>

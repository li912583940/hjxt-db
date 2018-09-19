<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
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
	<div id="lm_info"><span>当前位置：用户组管理 </span></div>
       <form action="userTeam.do?method=search" method="post">
        <div id="content">
		     	<table  border="0" cellspacing="0" cellpadding="0">
	    	   <tr>
	    	   		<th>登陆帐号</th>
	    	   		<th>用户姓名</th>
	    	   		<th>部门</th>
	    	   		<th>用户组号</th>
	    	   </tr>
    	  <logic:notEmpty name="groupUser">
	    	   <logic:iterate id="gu" name="groupUser">
	    	      <tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
	    	   		<td width="24%">${gu.userNo }</td>
	    	   		<td width="24%">${gu.userName }</td>
	    	   		<td width="24%">${gu.userDepart }</td>
	    	   		<td width="23%">${gu.groupNo }</td>
	    	   	 </tr>
	    	   </logic:iterate>
    	   </logic:notEmpty>
    	   	<logic:empty name="groupUser">
		     				<tr>
		     					<td colspan="4"><font color="red">没有相关记录</font></td>
		     				</tr>
		     		</logic:empty>
    	    <tr>
    	    </tr>
    	</table>
       <a href="userTeam.do?method=search" style="position:absolute;margin-top:20px;margin-left:120px;height:25px; line-height:25px;"><img src="images/fanhui.gif"></img></a>
 	</div>
 	</form>
  </body>
</html>

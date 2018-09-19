<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
		<script src="<%=basePath %>js/user.js" type="text/javascript" ></script>
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
   	<div id="user_info"><span>登录姓名：${users.userName }   登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：用户管理 </span></div>
        	 <form action="user.do?method=search" method="post">
		     	<table  style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
      		<tr>
               <td width="30%">登录帐号：</td>
               <td width="30%"><input type="text" name="userNo1" id="userNo1" /></td>        		
      		</tr>
      		<tr>
      		    <td>用户姓名：</td>
                <td><input type="text" name="userName1" id="userName1" /></td>     		
      		</tr>
      		<tr>
                <td>所在部门：</td>
                <td><input type="text" name="userDepart" id="userDepart" /></td>       		
      		</tr>
      		<tr>
               <td>&nbsp;&nbsp;&nbsp;&nbsp;用户组：</td>
               <td>
                   <select id="userGroup" style="width:130px;">
                   		  <option value="0">请选择用户组</option>
                   		  <logic:iterate id="us" name="usGroup">
                   		  			<option value="${us. webId}">${us. groupName}</option>
                   		  </logic:iterate>
                   </select>
               </td>        		
      		</tr>
      		<tr>
      			<td><a href="javascript:void(0);" onclick="saveUser();return false;"><img src="images/baocun.gif"></img></a></td>
      			<td><a href="user.do?method=search"><img src="images/fanhui.gif"></img></a></td>
      		</tr>
      </table>
      </form>
  </body>
</html>

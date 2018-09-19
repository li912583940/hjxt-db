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
		<script src="<%=basePath %>js/userTeam.js" type="text/javascript" ></script>
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
		<style type="text/css">
			a{
			cursor:hand;
			}
		
		</style>
  </head>
   <body>
   	<div id="user_info1"><span>&quot;登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info1"><span>当前位置：用户组管理 </span></div>
    <form action="userTeam.do?method=search" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:40%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td style="text-align: left" width="20%">用户组号：<input type="text" id="groupNo" name="groupNo" value="${sysUserGroup.groupNo}" disabled="disabled" /></td>
      	 		<td style="text-align: left" width="20%">用户组名：<input type="text" id="groupName" name="groupName" value="${sysUserGroup.groupName}" disabled="disabled" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td style="text-align: left">
      	 		<div id="valuedive" style="margin-top:0px;margin-left:0px;">
					监区权限：<input type="checkbox" name="Alljq" onclick="checkAll()" />全选
					</div>
					<div id="valuediv" style="height:350px;  overflow-y:auto; margin-top:0px;border:2px solid #FFFFFF;">
					<logic:notEmpty name="sysSlicGroupList">
						<logic:iterate id="limt" name="sysSlicGroupList">
						   	<input type="checkbox" name="slicGroupListbox"  value="${limt.jy},${limt.jqNo}"  checked="checked" />${limt.jy}${limt.jqName }<br />
						</logic:iterate>
					</logic:notEmpty>
					<logic:notEmpty name="unsysSlicGroupList">
						<logic:iterate id="unlimt" name="unsysSlicGroupList">
							<input type="checkbox" name="slicGroupListbox"  value="${unlimt.jy},${unlimt.jqNo}" />${unlimt.jy}${unlimt.jqName }<br />
						</logic:iterate>
					</logic:notEmpty>
					</div>
      	 		</td>
      	 		<td style="text-align: left">
      	 		<div id="valuedive" style="margin-top:0px;margin-left:0px">
					功能权限：<input type="checkbox" name="AllLine" onclick="checkAll1()"/>全选
					</div>
					<div id="valuediv" style="height:350px; overflow-y:auto;margin-top:0px;;border:2px solid #2E3385；">
					<logic:notEmpty name="menuList">
						<logic:iterate id="menu" name="menuList">
						   	<input type="checkbox" name="menubox"  value="${menu.menuNo }"  checked="checked" />${menu.menuName }<br />
						</logic:iterate>
					</logic:notEmpty>
					<logic:notEmpty name="unmenuList">
						<logic:iterate id="unmenu" name="unmenuList">
							<input type="checkbox" name="menubox"  value="${unmenu.menuNo }"/>${unmenu.menuName }<br />
						</logic:iterate>
					</logic:notEmpty>
					</div>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td><a href="javascript:void(0);" onclick="updateSaveLimt();return false;"><img src="images/baocun.gif"></img></a></td>
      	 		<td><a href="userTeam.do?method=search"><img src="images/fanhui.gif"></img></a></td>
      	 	</tr>
      	 	</table>
    </form>
  </body>
</html>

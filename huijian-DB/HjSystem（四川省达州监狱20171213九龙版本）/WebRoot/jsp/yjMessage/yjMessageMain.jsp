<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
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
		<script src="<%=basePath %>js/yjMessage.js" type="text/javascript" ></script>
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
		 	 a{text-decoration: NONE}
	 		.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		</style>   
  </head>
  <body onkeydown="return enterSubmit(this,event);">
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：警察信息 </span></div>
       <html:form action="yjMessage.do?method=search" method="post">
        	<div id="content2">
		     	<table  border="0" cellspacing="0" cellpadding="0">
		     		<tr>
		     			<td nowrap="nowrap">警察编号：<html:text property="yjNum" styleId="yjNum" /></td>
		     			<td nowrap="nowrap">警察姓名：<html:text property="jyName" styleId="jyName" /></td>
		     			<td nowrap="nowrap">部门：
		     				<html:select property="deptName" styleId="deptName" style="width:130px">
		     					<html:option value="null">全部</html:option>
		     					<logic:iterate id="jl" name="deptList">
		     						<html:option value="${jl}">${jl}</html:option>
		     					</logic:iterate>
		     				</html:select>
		     			</td>
		     			<td nowrap="nowrap" colspan="2"><a href="javascript:void(0)" onclick="chaxun();return false;"><img src="images/chaxun.gif" /></a>  <a href="javascript:void(0)" onclick="addYj();return false;"><img src="images/zengjia.gif" /></a></td>
		     		</tr>
		     		<tr>
		     	   	 	<th width="24%" height="25px" nowrap="nowrap">警察编号</th>
		     			<th width="24%" height="25px" nowrap="nowrap">警察姓名</th>
		     			<th width="24%" height="25px" nowrap="nowrap">警察IC卡号</th>
		     			<th width="12%" height="25px" nowrap="nowrap">部门</th>
		     			<th width="12%" height="25px" nowrap="nowrap">操作</th>
		     		</tr>
		     
		     		 <logic:notEmpty name="page" property="list">
		     				<logic:iterate id="yj" name="page" property="list">
		     				<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
		     					<td height="25px" nowrap="nowrap">${yj.yjNum }</td>
		     					<td height="25px" nowrap="nowrap">${yj.yjName }</td>
		     					<td height="25px" nowrap="nowrap">${yj.yjCard }</td>
		     					<td height="25px" nowrap="nowrap">${yj.deptName }</td>
		     					<td height="25px" nowrap="nowrap"><a href="javascript:void(0)" onclick="updateYjMessage();return false;">修改</a> <a href="javascript:void(0)" onclick="delYjMessage();return false;">删除</a><input type="hidden" name="webId" value="${yj.webId}" /></td>
		     				</tr>
		     				</logic:iterate>
		     				<tr>
		     					<td colspan="5" height="25px"><page:page pageNo="${page.pageNo}" pageSize="${page.pageSize}" recordCount="${page.recordCount}" url="yjMessage.do?method=search1"></page:page></td>
		     				</tr>
		     		</logic:notEmpty>
		     		<logic:empty name="page" property="list">
		     				<tr>
		     					<td colspan="5"><font color="red">没有相关信息</font></td>
		     				</tr>
		     		</logic:empty>
		     	</table>
		     </div>
		 </html:form>
  </body>
</html>

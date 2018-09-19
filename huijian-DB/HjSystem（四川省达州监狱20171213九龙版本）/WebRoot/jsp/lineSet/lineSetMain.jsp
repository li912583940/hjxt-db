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
		<script src="<%=basePath %>js/lineSet.js" type="text/javascript" ></script>
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
	  <div id="user_info"><span>&nbsp;登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：线路设置</span></div>
	       <form action="" method="post">
	        	<div id="content">
			     	<table  cellspacing="0" cellpadding="0">
			     		<tr>
			     		<th nowrap="nowrap">电话编号</th>
			     		<th nowrap="nowrap">线路状态</th>
			     		<th nowrap="nowrap">服务器名称</th>
			     		<th nowrap="nowrap">座位名称</th>
			     		<th nowrap="nowrap">读卡器</th>
			     		<th nowrap="nowrap">IC卡号</th>
			     		<th nowrap="nowrap">操作</th>
			     		</tr>
			     		<logic:notEmpty name="sysQqLineList">
			     			<logic:iterate id="sql" name="sysQqLineList">
					     		<tr  onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
					     			<td width="14%" nowrap="nowrap">${sql.lineNo }</td>
					     			<td width="14%" nowrap="nowrap">
					     				<c:if test="${sql.state==1}">开启</c:if>
					     				<c:if test="${sql.state==0}"><font color="red">关闭</font></c:if>
					     			</td>
					     			<td width="14%" nowrap="nowrap">${sql.jy}</td>
					     			<td width="14%" nowrap="nowrap">${sql.zw }</td>
					     			<td width="13%" nowrap="nowrap">${sql.dkq}</td>
					     			<td width="13%" nowrap="nowrap">${sql.cardNo}</td>
					     			<td width="13%" nowrap="nowrap"><a href="javascript:void(0)" onclick="updateLine();return false;">配置</a><input type="hidden"  name="lineId" value="${sql.webId }" /><input type="hidden"  name="lineNo" value="${sql.lineNo}" /><input type="hidden"  name="jy" value="${sql.jy }" />&nbsp;&nbsp;<!--  <a href="javascript:void(0)" onclick="updateCardNo();return false;">卡号设置</a>--></td>
					     		</tr>
				     		</logic:iterate>
			     		</logic:notEmpty>
			     		<logic:empty name="sysQqLineList">
			     			<tr>
			     				<td colspan=7><font color="red">没有相关记录</font></td>
			     			</tr>
			     		</logic:empty>
			     	</table>
			     </div>
			</form>
  </body>
</html>

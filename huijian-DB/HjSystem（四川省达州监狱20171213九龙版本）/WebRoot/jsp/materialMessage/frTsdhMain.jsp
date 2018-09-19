<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
        <script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/materialMessage.js" type="text/javascript" ></script>
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
		</style>   
  </head>
  <body>
  	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：罪犯管理 </span></div>
       <form action="materialMessage.do?method=checkTpDh&frNo=${frNo}" method="post">
        	<div id="content">
		     	<table  border="0" cellspacing="0" cellpadding="0">
		     			<tr>
			     			<th width="8%" nowrap="nowrap">特批ID</th>
			     			<th width="8%" nowrap="nowrap">罪犯姓名</th>
			     			<th width="8%" nowrap="nowrap">特批号码</th>
			     			<th width="8%" nowrap="nowrap">通话人姓名</th>
			     			<th width="8%" nowrap="nowrap">与通话人关系</th>
			     			<th width="8%" nowrap="nowrap">特批次数</th>
			     			<th width="8%" nowrap="nowrap">剩余次数</th>
			     			<th width="8%" nowrap="nowrap">特批电话时长</th>
			     			<th width="8%" nowrap="nowrap">特批人编号</th>
			     			<th width="8%" nowrap="nowrap">特批人姓名</th>
			     			<th width="8%" nowrap="nowrap">特批时间</th>
			     			<th width="7%" nowrap="nowrap">操作</th>	
		     			</tr>
		     			<logic:notEmpty name="frTsdhList">
		     				<logic:iterate id="frTsdh" name="frTsdhList">
		     						<tr>
		     							<td width="7%" nowrap="nowrap">${frTsdh.tpid }</td>
		     							<td width="7%" nowrap="nowrap">${frTsdh.frName }</td>
		     							<td width="7%" nowrap="nowrap">${frTsdh.tpTele}</td>
		     							<td width="7%" nowrap="nowrap">${frTsdh.tpxm}</td>
		     							<td width="7%" nowrap="nowrap">${frTsdh.tpgx }</td>
		     							<td width="7%" nowrap="nowrap">${frTsdh.tpcs}</td>
		     							<td width="7%" nowrap="nowrap">${frTsdh.sycs }</td>
		     							<td width="7%" nowrap="nowrap">${frTsdh.tpsc }</td>
		     							<td width="7%" nowrap="nowrap">${frTsdh.tprNo }</td>
		     							<td width="7%" nowrap="nowrap">${frTsdh.tprName}</td>
		     							<td width="12%" nowrap="nowrap">${frTsdh.tpsj}</td>
		     							<td width="13%" nowrap="nowrap">
		     							<a href="javascript:void(0)" onclick="updatefrTsdh();return false;">修改</a>
		     							<a href="javascript:void(0)" onclick="delfrTsdh();return false;">删除</a>
		     							<input type="hidden" id="frTsdhId" name="frTsdhId" value="${frTsdh.tpid}" />
		     							<input type="hidden" id="frNo1" name="frNo1" value="${frNo}" /></td>
		     						</tr>
		     				</logic:iterate>
		     			</logic:notEmpty>
		     			<logic:empty name="frTsdhList">
		     						<tr>
		     							<td colspan="12"><font color="red">没有相关信息</font></td>
		     						</tr>
		     			</logic:empty>
		     	</table>
		     </div>
		     <a href="materialMessage.do?method=addFrTpdh&frNo=${frNo}" style="position:absolute;margin-top:20px;margin-left:70px;height:25px; line-height:25px;"><img src="images/zengjia.gif"></img></a><a href="materialMessage.do?method=search" style="position:absolute;margin-top:20px;margin-left:120px;height:25px; line-height:25px;"><img src="images/fanhui.gif"></img></a>
		</form>
  </body>
</html>

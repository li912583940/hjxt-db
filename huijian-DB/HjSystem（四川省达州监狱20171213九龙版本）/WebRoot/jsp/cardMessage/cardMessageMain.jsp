<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String http=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>main</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
 	    <script language="javascript">   
			document.onkeydown   =   function()   
			{       
			if(event.keyCode   ==   8)   
			{   
			if(event.srcElement.tagName.toLowerCase()   !=   "input"   
			&&   event.srcElement.tagName.toLowerCase()   !=   "textarea")   
			event.returnValue   =   false;   
			}   
			}
			var basePath='<%=basePath%>';
			function chaxun(){
				document.forms[0].submit();
			}
	 </script>
	 <style type="text/css">
	 	.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		a{text-decoration: NONE}
	</style>   
			 
  </head>
  <body onkeydown="return enterSubmit(this,event);">
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：卡片管理 </span></div>
       <html:form action="cardMessage.do?method=search" method="post">
        	<div id="content2">
		     	<table  cellspacing="0" cellpadding="0">
		     	  <tr>
		     	     <td colspan="2" height="25px" nowrap="nowrap" width="32%">通话开始时间：<html:text  property="callTimeStart"  styleId="callTimeStart" value="${todayBegin}" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
		     	     <td colspan="2" height="25px" nowrap="nowrap" width="32%">通话结束时间：<html:text  property="callTimeEnd"   styleId="callTimeEnd" value="${todayEnd}" onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
		     	     <td colspan="2" height="25px" nowrap="nowrap" width="32%">&nbsp;&nbsp;&nbsp;&nbsp;罪犯编号：<html:text property="frNo" styleId="frNo"></html:text></td>
		     	  </tr>
		     	  <tr>
		     	      <td colspan="2" height="25px" nowrap="nowrap" width="32%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;罪犯姓名：<html:text property="frName" styleId="frName"></html:text></td>
		     	      
		     	       <td colspan="2" height="25px" nowrap="nowrap" width="32%">&nbsp;&nbsp;&nbsp;&nbsp;罪犯监区：
	     				   		<html:select property="jqNo" styleId="jqNo" style="width:130px">
	   									<html:option value="null">全部</html:option>
	   									 <logic:notEmpty name="jlJqList">
	   									 	<logic:iterate id="jl" name="jlJqList">
	   									 		<html:option value="${jl.jqNo}">${jl.jqName}</html:option>
	   									 	</logic:iterate>
	   									 </logic:notEmpty>
	     				 	    </html:select>
		     				  
		     	      </td>
		     	   	 
		     	     <td colspan="2" height="25px" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲属姓名：<html:text property="qsName" styleId="qsName"></html:text><a href="javascript:void(0)" onclick="chaxun();return false;"><img src="images/chaxun.gif"></img></a></td>
		     	     
		     	   </tr>
		    	 	<tr>
		    	 		<th nowrap="nowrap" height="25px" width="16%">监区名称</th>
		    	 		<th nowrap="nowrap" height="25px" width="16%">罪犯编号</th>
						<th nowrap="nowrap" height="25px" width="16%">罪犯姓名</th>
						<th nowrap="nowrap" height="25px" width="16%">家属姓名</th>
						<th nowrap="nowrap" height="25px" width="16%">卡号 </th>
						<th nowrap="nowrap" height="25px" width="16%">办卡时间</th>
					</tr>
					<logic:notEmpty name="page" property="list">
						<logic:iterate id="rs" name="page" property="list">
					   	<tr>
					   	  <td nowrap="nowrap" height="25px">${rs.jqName}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.frNo}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.frName}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.qsName}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.cardNum}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.sj}</td>
					   	
					   </tr>
					   </logic:iterate>
					   <tr>
					   		<td colspan="6" align="left" height="75px"><page:page pageNo="${page.pageNo}" recordCount="${page.recordCount}" url="cardMessage.do?method=search1" pageSize="${page.pageSize}"/></td>
					   </tr>
				   </logic:notEmpty>
				   <logic:empty name="page" property="list">
				         <tr>
				         	<td colspan="6"><font color="red">没有相关记录</font><input type="hidden" id="isDc" name="isDc" value="0"/></td>
				         </tr>
				   </logic:empty>
	  			 </table>
	  	 	</div>
	  	 </html:form>	
  </body>
</html>

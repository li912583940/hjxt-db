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
		<script src="<%=basePath %>js/accessControl.js" type="text/javascript" ></script>
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
	 </script>
	 <style type="text/css">
	 	.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		a{text-decoration: NONE}
	</style>   
			 
  </head>
  <body onkeydown="return enterSubmit(this,event);">
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：门禁记录 </span></div>
       <html:form action="accessControlMessage.do?method=search" method="post">
        	<div id="content2">
		     	<table  cellspacing="0" cellpadding="0">
		     	  <tr>
		     	     <td colspan="2" height="25px" nowrap="nowrap" width="25%">开始时间：<html:text  property="callTimeStart"  styleId="callTimeStart" value="${todayBegin}" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
		     	     <td colspan="2" height="25px" nowrap="nowrap" width="25%">结束时间：<html:text  property="callTimeEnd"   styleId="callTimeEnd" value="${todayEnd}" onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
		     	     <td colspan="3" height="25px" nowrap="nowrap" width="25%">&nbsp;&nbsp;&nbsp;&nbsp;监区：
	     				   		<html:select property="jqNo" styleId="jqNo" style="width:130px">
	   									<html:option value="null">全部</html:option>
	   									 <logic:notEmpty name="jlJqList">
	   									 	<logic:iterate id="jl" name="jlJqList">
	   									 		<html:option value="${jl.jqNo}">${jl.jqName}</html:option>
	   									 	</logic:iterate>
	   									 </logic:notEmpty>
	     				 	    </html:select>
		     				  
		     	      </td>
		     	     <td colspan="1">类型：
						<html:select property="state" styleId="state">
							<html:option value="null">全部</html:option>
							<html:option value="0">异常</html:option>
							<html:option value="1">正常</html:option>
						</html:select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		     	     <td colspan="1" height="25px" nowrap="nowrap"><a href="javascript:void(0)" onclick="chaxun();return false;"><img src="images/chaxun.gif"></img></a></td>
		     	  </tr>
		     	  
		    	 	<tr>
		    	 		<th nowrap="nowrap" height="25px" width="10%">罪犯编号</th>
		    	 		<th nowrap="nowrap" height="25px" width="10%">罪犯姓名</th>
						<th nowrap="nowrap" height="25px" width="10%">监区</th>
						<th nowrap="nowrap" height="25px" width="10%">进入时间</th>
						<th nowrap="nowrap" height="25px" width="10%">离开时间</th>
						<th nowrap="nowrap" height="25px" width="10%">进出时间差</th>
						<th nowrap="nowrap" height="25px" width="10%">警察编号</th>
						<th nowrap="nowrap" height="25px" width="10%">警察姓名</th>
						<th nowrap="nowrap" height="25px" width="10%">类型</th>
					</tr>
					<logic:notEmpty name="page" property="list">
						<logic:iterate id="rs" name="page" property="list">
					   	<tr>
					   	  <td nowrap="nowrap" height="25px">${rs.frNo}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.frName}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.jqName}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.inTime}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.outTime}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.sjc}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.yjNo}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.yjName}</td>
					   	  <td nowrap="nowrap" height="25px">
					   	  		<c:if test="${rs.state==1}">正常</c:if>
					     		<c:if test="${rs.state==0}"><font color="red">异常</font></c:if>
					      </td>
					   	
					   </tr>
					   </logic:iterate>
					   <tr>
					   		<td colspan="9" align="left" height="75px"><page:page pageNo="${page.pageNo}" recordCount="${page.recordCount}" url="accessControlMessage.do?method=search1" pageSize="${page.pageSize}"/><input type="hidden" id="isDc" name="isDc" value="1"/></td>
					   </tr>
				   </logic:notEmpty>
				   <logic:empty name="page" property="list">
				         <tr>
				         	<td colspan="9"><font color="red">没有相关记录</font><input type="hidden" id="isDc" name="isDc" value="0"/></td>
				         </tr>
				   </logic:empty>
	  			 </table>
	  	 	</div>
	  	 	<div style="position:absolute;margin-top:18px;margin-left:30px;height:25px; line-height:25px;">
				   <input type="button" value="导出记录至Excel表格" onclick="dcSearch();"/>
				  
	        </div>
	  	 </html:form>	
  </body>
</html>

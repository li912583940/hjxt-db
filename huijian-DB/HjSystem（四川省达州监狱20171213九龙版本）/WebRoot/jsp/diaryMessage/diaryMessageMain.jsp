<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="<%=basePath%>js/diaryMessage.js" type="text/javascript"></script>
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
  <body onkeydown="return enterSubmit(this,event);">
	  <div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：操作日志</span></div>
	       <html:form action="diaryMessage.do?method=search" method="post">
	        	<div id="content">
	        		<table>
	        			<tr>
	        			  <td colspan="2" nowrap="nowrap" width="19%">开始时间：<html:text  property="callTimeStart"  styleId="callTimeStart" value="${logBegin}" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
	        			  <td colspan="2" nowrap="nowrap" width="19%">结束时间：<html:text  property="callTimeEnd"    styleId="callTimeEnd" value="${logEnd}" onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" ></html:text></td>
	        			  <td colspan="2" nowrap="nowrap" width="19%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级别：<html:select property="type" styleId="type" style="width:130px;">
	        			  				<html:option value="null">全部</html:option>
	        			  				<html:option value="正常">正常</html:option>
	        			  				<html:option value="警告">警告</html:option>
	        			  				<html:option value="严重">严重</html:option>
	        			            </html:select>
	        			  </td>
	        			  <td colspan="2" nowrap="nowrap" width="19%">&nbsp;&nbsp;&nbsp;&nbsp;模块：<html:select property="model" styleId="model" style="width:130px;">
	        			  										<html:option value="null">全部</html:option>
	        			  										<logic:iterate id="sm" name="sysMenuList">
	        			  											<html:option value="${sm.menuName }">${sm.menuName }</html:option>
	        			  										</logic:iterate>
	        			                                   </html:select></td>
	        			  <td colspan="2" nowrap="nowrap" width="19%">操作：<html:text property="op" styleId="op"/></td>
	        		</tr>
	        			<tr>
	        			  <td colspan="2" nowrap="nowrap" width="19%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内容：<html:text property="info" styleId="info"/></td>
	        			  <td colspan="2" nowrap="nowrap" width="19%">用户编号：<html:text property="userNo" styleId="userNo"/></td>
	        			  <td colspan="2" nowrap="nowrap" width="19%">用户姓名：<html:text property="userName" styleId="userName"/></td>
	        			  <td colspan="2" nowrap="nowrap" width="19%">来源IP：<html:text property="userIp" styleId="userIp"/></td>
	        			  <td colspan="2" nowrap="nowrap" width="19%"><a href="javascript:void(0)" onclick="rizhiSearch();return false;"><img src="images/chaxun.gif" /></a></td>
	        			</tr>
	        			<tr>
		        			 <th width="12%" nowrap="nowrap">时间</th>
		        			 <th width="7%" nowrap="nowrap">级别	</th>
		        			 <th width="10%" nowrap="nowrap">模块</th>
		        			 <th width="9%" nowrap="nowrap">操作</th>
		        			 <th colspan="2" width="19%" nowrap="nowrap">内容</th>
		        			 <th width="9.5%" nowrap="nowrap">用户编号</th>
		        			 <th width="9.5%" nowrap="nowrap">用户姓名	</th>
		        			 <th width="9.5%" nowrap="nowrap">来源IP</th>
		        			 <th width="9.5%" nowrap="nowrap">操作</th>
	        			 </tr>
	        			 <logic:notEmpty name="page" property="list">
	        					<logic:iterate id="jl" name="page" property="list">
	        						<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
	        							<td nowrap="nowrap" width="12%" >${jl.logTime }</td>
	        							<td nowrap="nowrap" width="7%">${jl.type }</td>
	        							<td nowrap="nowrap" width="10%">${jl.model }</td>
	        							<td nowrap="nowrap"  width="9%">${jl.op }</td>
	        							<td colspan="2" nowrap="nowrap" width="19%"><textarea style="width:130px">${jl.info}</textarea></td>
	        							<td nowrap="nowrap" width="9.5%">${jl.userNo }</td>
	        							<td nowrap="nowrap" width="9.5%">${jl.userName }</td>
	        							<td nowrap="nowrap" width="9.5%">${jl.userIp }</td>
	        							<td nowrap="nowrap" width="9.5%"><a href="javascript:void(0)" onclick="logXx();return false;">查看详细信息</a><input type="hidden" name="logId" value="${jl.logId}"/></td>
	        						</tr>
	        					</logic:iterate>
	        				<tr>
	        				  <td colspan="10"><page:page pageSize="${page.pageSize}" pageNo="${page.pageNo}" url="diaryMessage.do?method=search1" recordCount="${page.recordCount}"></page:page></td>
	        			    </tr>
	        			</logic:notEmpty>
	        			<logic:empty name="page" property="list">
	        			     <tr><td colspan="10"><font color="red">没有相关信息</font></td></tr>
	        			</logic:empty>
	        		</table>
	        	</div>
	        </html:form>
  </body>
</html>

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
		<script src="<%=basePath %>js/jqSet.js" type="text/javascript" ></script>
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
			.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
			 a{text-decoration: NONE}
		</style>   
  </head>
  <body>
	  <div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：监区设置</span></div>
	       <form action="" method="post">
	        	<div id="content">
			     	<table  cellspacing="0" cellpadding="0">
			     		<tr>
				     	    <th nowrap="nowrap">监区编号</th>
				     	    <th nowrap="nowrap">监区名称</th>
				     	    <th nowrap="nowrap">可会见星期</th>
				     	    <th nowrap="nowrap">操作</th>
			     	    </tr>
			     	   <logic:notEmpty name="jlJqWeekTimeList">
			     	   		<logic:iterate id="jl" name="jlJqWeekTimeList">
					     	    <tr>
					     	    	<td width="24%" nowrap="nowrap">${jl.jqNo}</td>
					     	    	<td width="24%" nowrap="nowrap">${jlJq.jqName}</td>
					     	    	<td width="24%" nowrap="nowrap">
					     	    	    <c:if test="${jl.jqWeek==0}">星期天</c:if>
					     	    	    <c:if test="${jl.jqWeek==1}">星期一</c:if>
					     	    	    <c:if test="${jl.jqWeek==2}">星期二</c:if>
					     	    	    <c:if test="${jl.jqWeek==3}">星期三</c:if>
					     	    	    <c:if test="${jl.jqWeek==4}">星期四</c:if>
					     	    	    <c:if test="${jl.jqWeek==5}">星期五</c:if>
					     	    	    <c:if test="${jl.jqWeek==6}">星期六</c:if>
					     	    	</td>
					     	    	<td width="24%" nowrap="nowrap">
					     	    		<a href="javascript:void(0)" onclick="updateJqWeek();return false;">修改</a><input type="hidden" id="timeIndex" name="timeIndex" value="${jl.webId}" />  <a href="javascript:void(0)" onclick="deleteJqWeek(); return false;">删除</a>
					     	    	</td>
					     	    </tr>
				     	    </logic:iterate>
			     	    </logic:notEmpty>
			     	    <logic:empty name="jlJqWeekTimeList">
			     			<tr>
			     				<td colspan=4><font color="red">没有相关记录</font></td>
			     			</tr>
			     		</logic:empty>
			     	</table>
			    </div>
			    	<a href="jqSet.do?method=addjqWeek&webId=${jlJq.webId }" style="position:absolute;margin-top:20px;margin-left:125px;height:25px; line-height:25px;"><img src="images/zengjia.gif"></img></a>
			        <a href="jqSet.do?method=search" style="position:absolute;margin-top:20px;margin-left:175px;height:25px; line-height:25px;"><img src="images/fanhui.gif"></img></a>
			</form>
  </body>
</html>

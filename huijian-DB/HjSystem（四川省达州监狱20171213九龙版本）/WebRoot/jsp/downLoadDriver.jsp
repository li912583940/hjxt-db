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
			var basePath='<%=basePath%>';
	  </script>
	 <style type="text/css">
		.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		 a{text-decoration: NONE}
		</style>   
  </head>
  <body onkeydown="return enterSubmit(this,event);">
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：下载驱动 </span></div>
        	<div id="content2">
		     	<table  border="0" cellspacing="0" cellpadding="0">
		    
		     		<tr>
		     			<td width="32%">驱动名称</td>
		     			<td width="32%">系统</td>
		     			<td width="32%">操作</td>
		     		</tr>
		     		<tr>
		     			<td>新中新二代身份证读卡器验证软件</td>
		     			<td>XP 32位/WIN7  32位</td>
		     			<td><a href="<%=basePath%>Driver/二代证验证软件.rar">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>新中新二代身份证读卡器驱动</td>
		     			<td>XP 32位/WIN7  32位</td>
		     			<td><a href="<%=basePath%>Driver/USBDrv3.0-Win32.msi">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>新中新二代身份证读卡器驱动</td>
		     			<td>WIN7  64位</td>
		     			<td><a href="<%=basePath%>Driver/Win7_64bit.rar">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>佳博打印机驱动</td>
		     			<td>ALL</td>
		     			<td><a href="<%=basePath%>Driver/GP80DRVCN V13.exe">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>中琦打印机驱动</td>
		     			<td>ALL</td>
		     			<td><a href="<%=basePath%>Driver/Printer_Drivers.rar">下载</a></td>
		     		</tr>		     		
		     		<tr>
		     			<td>IE8</td>
		     			<td>WindowsXP</td>
		     			<td><a href="<%=basePath%>Driver/IE8-WindowsXP-x86-CHS.exe">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>IE8</td>
		     			<td>Windows server 2003</td>
		     			<td><a href="<%=basePath%>Driver/IE8-WindowsServer2003-x86-CHS.exe">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>Microsoft NET Framework 4</td>
		     			<td>ALL</td>
		     			<td><a href="<%=basePath%>Driver/Microsoft .NET Framework 4.exe">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>会见登记电脑安装</td>
		     			<td>ALL</td>
		     			<td><a href="<%=basePath%>Driver/刷身份证电脑安装.rar">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>IC读卡器驱动</td>
		     			<td>ALL</td>
		     			<td><a href="<%=basePath%>Driver/CH341SER.rar">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>海康DSFilters播放插件V6.1.2.2（安装插件后，可以使用微软媒体播放器等通用播放器播放海康威视格式的媒体文件及流数据）</td>
		     			<td>win7/vista/xp 32/64位， Server2003/2008</td>
		     			<td><a href="<%=basePath%>Driver/Hik_DSFilters_Setup.exe">下载</a></td>
		     		</tr>
		     		<tr>
		     			<td>网页调取实时监控画面控件</td>
		     			<td>win7/vista/xp 32/64位， Server2003/2008</td>
		     			<td><a href="<%=basePath%>Driver/Dvr_1.exe">下载</a></td>
		     		</tr>
		         </table>
		    
		    </div>
		     		
  </body>
</html>

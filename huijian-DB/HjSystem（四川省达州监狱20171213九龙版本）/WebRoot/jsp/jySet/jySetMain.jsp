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
		<script src="<%=basePath %>js/jySet.js" type="text/javascript" ></script>
		<script type="text/javascript">
			//window.setInterval(refreshMeetInfo,5000);
			function refreshMeetInfo(){
		 		 $.ajax({
		 		      type:"POST",
		 		      url:"jySet.do",
		 		      data:"method=getFaceInfo",
		 		      dataType:"json",
		 		      success:callback1   
		 		});
			}
			function callback1(){
				
			} 
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
	  <div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	  <div id="lm_info"><span>当前位置：系统参数</span></div>
	       <form action="" method="post">
	        	<div id="content">
			     	<table  cellspacing="0" cellpadding="0">
			     		<tr>
			     			<th nowrap="nowrap">服务器名称</th>
			     			<th nowrap="nowrap">服务器IP</th>
			     			<th nowrap="nowrap">状态端口</th>
			     			<th nowrap="nowrap">监听端口</th>
			     			<th nowrap="nowrap">录音网络地址</th>
			     			<th nowrap="nowrap">操作</th>
			     		</tr>
				     	 <logic:notEmpty name="sysHjServerList">
				     		<logic:iterate id="sqs" name="sysHjServerList">
					     		<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
					     			<td width="16%" nowrap="nowrap">${sqs.serverName}</td>
					     			<td width="16%" nowrap="nowrap">${sqs.ip}</td>
					     			<td width="16%" nowrap="nowrap">${sqs.port}</td>
					     			<td width="16%" nowrap="nowrap">${sqs.audioPort}</td>
					     			<td width="16%" nowrap="nowrap">${sqs.recUrl}</td>
					     			<td width="15%" nowrap="nowrap"><a href="javascript:void(0)" onclick="updateJySet();return false;">配置</a><input type="hidden" id="jyId" name="JyId" value="${sqs.webId}" /></td>
					     		</tr>
				     		</logic:iterate>
				     	 </logic:notEmpty>
				     	 	<logic:empty name="sysHjServerList">
			     			<tr>
			     				<td colspan=5><font color="red">没有相关记录</font></td>
			     			</tr>
			     		</logic:empty>
			     	</table>
			    </div>
			     <input type="button" value="会见审批参数设置" onclick="hjSpParam();" style="margin-left:3%;margin-top:20px" />
			     <!-- <input type="button" value="清空人脸注册设备（一）数据" onclick="clearFace();" style="margin-left:2%;margin-top:20px" />
			     <input type="button" value="清空人脸注册设备（二）数据" onclick="clearFace1();" style="margin-left:2%;margin-top:20px" />
			     <input type="button" value="清空人脸识别设备（一）数据" onclick="clearFace2();" style="margin-left:2%;margin-top:20px" />
			     <input type="button" value="清空人脸识别设备（二）数据" onclick="clearFace3();" style="margin-left:2%;margin-top:20px" />
			     <input type="button" value="清空所有人脸设备上的数据" onclick="clearFace4();" style="margin-left:2%;margin-top:20px" /> -->
			 </form>
	</body>
</html>

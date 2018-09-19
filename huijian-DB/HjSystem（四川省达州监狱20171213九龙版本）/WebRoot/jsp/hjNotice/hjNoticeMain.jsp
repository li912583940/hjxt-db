<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Flexigrid</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>FlexiGrid/css/flexigrid.css"/>
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.2.6.js"></script>
		<script src="<%=basePath %>js/hjNotice.js" type="text/javascript" ></script>
		<script  type="text/javascript"> 
			 window.setInterval(refreshMeetInfo,15000); 
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
	</head>
	<body>
		<div id="user_info2"><span>登录姓名：${users.userName }    登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：会见网络通知 </span></div>
		<form action="hjNotice.do?method=search" method="post">
	 	 	<div id="content2">
	 	 			<font size="4"> <div id="info3" align="center" >当前座位已分配&nbsp;&nbsp;<font color="red">${hjs.yifenpei }</font>，&nbsp;&nbsp;未分配&nbsp;&nbsp;<font color="red">${hjs.weifenpei }</font><!-- ；&nbsp;&nbsp;当前通知 已发起&nbsp;&nbsp;<font color="red">${hjs.yifaqi }</font>，&nbsp;&nbsp;未发起&nbsp;&nbsp;<font color="red">${hjs.weifaqi }</font> --></div></font>
	 	 			<table>
						<tr>
							<th width="7%" nowrap="nowrap">罪犯<br>监区</th>
							<th width="9%" nowrap="nowrap">罪犯<br>编号</th>
							<!-- <th width="7%" nowrap="nowrap">罪犯档案号</th> -->
							<th width="9%" nowrap="nowrap">罪犯<br>姓名</th>
							<th width="5%" nowrap="nowrap">会见<br>窗口</th>
							<th width="7%" nowrap="nowrap">会见通知<br>接收状态</th>
							<th width="4%" nowrap="nowrap">会见<br>类型</th>
							<th width="10%" nowrap="nowrap">会见<br>说明</th>
							<th width="9%" nowrap="nowrap">登记<br>时间</th>
							<th width="15%" nowrap="nowrap">亲属信息</th>
							<!--  <th width="4%" nowrap="nowrap">选中</th>-->
							<th width="7%" nowrap="nowrap">会见<br>状态</th>
							<th width="7%" nowrap="nowrap">会见通知<br>接收人</th>
							<th width="9%" nowrap="nowrap">接收<br>时间</th>
							<th width="9%" nowrap="nowrap">接收通知<br>是否超时</th>
						
						</tr>
					<tbody id="hjdjTable">
					<logic:notEmpty name="list1">
						<logic:iterate id="jl" name="list1" indexId="index">
							<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
								<td nowrap="nowrap">${jl.jqName}<c:if test="${index==0}"><input type="hidden" id="isNoPrint" name="isNoPrint" value="1" /></c:if><input type="hidden" name="webId" value="${jl.hjid}" /><input type="hidden" name="fpFlag" value="${jl.fpFlag}" /></td>
								<td nowrap="nowrap">${jl.frNo}</td>
								<!-- <td nowrap="nowrap"><font color="red">${jl.frDah}</font></td> -->
								<td nowrap="nowrap">${jl.frName}</td>
								<td nowrap="nowrap">
									<c:if test="${jl.fpFlag==0}"><font color="red">未分配</font></c:if>
									<c:if test="${jl.fpFlag!=0}">${jl.zw}</c:if>
								</td>
								<td nowrap="nowrap">
									<c:if test="${jl.pageTzState==0}"><a href="javascript:void(0)" onclick="sdNotice();return false;"><font color="red">未接收</font></a></c:if>
									<c:if test="${jl.pageTzState==1}">已接收</c:if>
								</td>
								<td nowrap="nowrap">
									<c:if test="${jl.hjType==1}">电话会见</c:if>
									<c:if test="${jl.hjType==2}"><font color="red">面对面会见</font></c:if>
									<c:if test="${jl.hjType==3}"><font color="red">视频会见</font></c:if>
									<c:if test="${jl.hjType==4}"><font color="red">帮教</font></c:if>
									<c:if test="${jl.hjType==5}"><font color="red">提审</font></c:if>
								</td>
								<td>${jl.hjInfo }</td>
								<td>${jl.djTime}</td>
								<td>${jl.qsInfo1}</td>
								  
								<td nowrap="nowrap">
									<c:if test="${jl.fpFlag==2}"><font color="red">已在会见</font></c:if>
									<c:if test="${jl.fpFlag!=2}">未在会见</c:if>
								</td>
								<td nowrap="nowrap">${jl.pageTzUserNo}<br>${jl.pageTzUserName}</td>
								<td>${jl.pageTzTime}</td>
								<td>
									<c:if test="${jl.isOverTime==0}">未超时</c:if>
									<c:if test="${jl.isOverTime==1}"><font color="red">已超时</font></c:if>
								</td>
							</tr>
						</logic:iterate>
						
					</logic:notEmpty>
					<logic:empty name="list1">
							<tr><td colspan="14"><font color="red">没有相关信息</font><input type="hidden" id="isNoPrint" name="isNoPrint" value="0" /></td></tr>
					</logic:empty>
					</tbody>
			</table>
	 	 </div>
	 	 <object id="Mediaplayer" width="0" height="0" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">
				    <param name="AutoStart" value="0" />
				    <!--是否name动播放-->
				    <param name="Balance" value="0" />
				    <!--调整左右声道平衡,同上面旧播放器代码-->
				    <param name="enabled" value="1" />
				    <!--播放器是否可人为控制-->
				    <param name="EnableContextMenu" value="1" />
				    <!--是否启用上下文菜单-->
				    <param name="url" value="${hJClient1.paramData5}" />
				    <!--播放的文件地址-->
				    <param name="PlayCount" value="1" />
				    <!--播放次数控制,为整数-->
				    <param name="rate" value="1" />
				    <!--播放速率控制,1为正常,允许小数,1.0-2.0-->
				    <param name="currentPosition" value="0" />
				    <!--控件设置:当前位置-->
				    <param name="currentMarker" value="0" />
				    <!--控件设置:当前标记-->
				    <param name="defaultFrame" value="" />
				    <!--显示默认框架-->
				    <param name="invokeURLs" value="0" />
				    <!--脚本命令设置:是否调用URL-->
				    <param name="baseURL" value="" />
				    <!--脚本命令设置:被调用的URL-->
				    <param name="stretchToFit" value="0" />
				    <!--是否按比例伸展-->
				    <param name="volume" value="50" />
				    <!--默认声音大小0%-100%,50则为50%-->
				    <param name="mute" value="0" />
				    <!--是否静音-->
				    <param name="uiMode" value="invisible" />
				    <!--播放器显示模式:Full显示全部;mini最简化;None不显示播放控制,只显示视频窗口;invisible全部不显示-->
				    <param name="windowlessVideo" value="0" />
				    <!--如果是0可以允许全屏,否则只能在窗口中查看-->
				    <param name="fullScreen" value="0" />
				    <!--开始播放是否自动全屏-->
				    <param name="enableErrorDialogs" value="0" />
				    <!--是否启用错误提示报告-->
				    <param name="SAMIStyle" value />
				    <!--SAMI样式-->
				    <param name="SAMILang" value />
				    <!--SAMI语言-->
				    <param name="SAMIFilename" value />
				    <!--字幕ID-->
     		</object>
	    
	 </form>
    </body>
</html>

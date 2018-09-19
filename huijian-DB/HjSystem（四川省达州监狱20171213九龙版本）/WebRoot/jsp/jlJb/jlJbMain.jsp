<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
		<script src="<%=basePath %>js/jlJb.js" type="text/javascript" ></script>
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
		.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		 a{text-decoration: NONE}
	 </style>    
  </head>
  
  <body>
  	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：罪犯级别 </span></div>
       <form action="" method="post">
	        <div id="content">
			     	<table  border="0" cellspacing="0" cellpadding="0">
			     			<tr>
								<th nowrap="nowrap">级别编号</th>
								<th nowrap="nowrap">级别名称</th>
								<th nowrap="nowrap">每月会见次数</th>
								<th nowrap="nowrap">每次会见时长（分钟）</th>
								<th nowrap="nowrap">复听录音超时时间（天）</th>
								<!-- <th nowrap="nowrap">每月亲情电话次数</th>
								<th nowrap="nowrap">每次亲情电话时长</th> -->
								<th nowrap="nowrap">操作</th>
							</tr>
							<logic:notEmpty name="jlJbList">
								<logic:iterate id="jljb" name="jlJbList">
									<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
										<td width="14%" nowrap="nowrap">${jljb.jbNo}</td>
										<td width="14%" nowrap="nowrap">${jljb.jbName}</td>
										<td width="14%" nowrap="nowrap">${jljb.hjCount}</td>
										<td width="14%" nowrap="nowrap">${jljb.hjTime}</td>
										<td width="14%" nowrap="nowrap">${jljb.recordOverTime}</td>
										<!-- <td width="14%" nowrap="nowrap">${jljb.qqCount}</td>
										<td width="14%" nowrap="nowrap">${jljb.qqTime}</td> -->
										<td width="15%" nowrap="nowrap"><a href="javascript:void(0)" onclick="updatejlJb();return false;">修改</a>  <a href="javascript:void(0)" onclick="deljlJb();return false;">删除</a><input type="hidden" id="jljbId" name="jljbId" value="${jljb.webId }" /></td>
								     </tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="jlJbList">
					     		<tr>
					     		  <td colspan="6"><font color="red">没有相关信息</font></td>
					     		</tr>
				     		</logic:empty>
					</table>
			</div>
			<a href="gradeMessage.do?method=addJlJb" style="position:absolute;margin-top:20px;margin-left:100px;height:25px; line-height:25px;"><img src="images/zengjia.gif"></img></a>
			<!-- <a href="javascript:void(0);" onclick="fwQqCount();return false;" style="position:absolute;margin-top:18px;margin-left:150px;height:25px; line-height:25px;">复位会见次数</a>
			<a href="gradeMessage.do?method=plAddQqCount" style="position:absolute;margin-top:18px;margin-left:250px;height:25px; line-height:25px;">批量增加会见次数</a> -->
	   </form>
  </body>
</html>

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
		<script type="text/javascript" language="javascript" > 
			    document.onkeydown   =   function(){
					if(event.keyCode   ==   8) {
						if(event.srcElement.tagName.toLowerCase()   !=   "input"  &&   event.srcElement.tagName.toLowerCase()   !=   "textarea")   
						event.returnValue   =   false;   
					}   
				}
				function openPort(){
					var isSuc=false;
					for(var i=1;i<10;i++){
						 isSuc=document.getElementById("WM02R").OpenPort(i);
						 if(isSuc==true){
						 	break;
						 }
					}
				}
				
				function colsePort(){
					document.getElementById("WM02R").ClosePort();
				}
				function callback2(data){
					if(data.length>0){
						document.getElementById("frNo").value=data[0].frNo;
						document.getElementById("frName").value=data[0].frName;
						document.getElementById("jq").value=data[0].jq;
						document.getElementById("jb").value=data[0].jbNo;
						document.getElementById("zm").value=data[0].infoZm;
						document.getElementById("xq").value=data[0].infoXq;
						document.getElementById("rjsj").value=data[0].infoRjsj;
						document.getElementById("csrq").value=data[0].infoCsrq;
						document.getElementById("jtzz").value=data[0].infoHome;
						if(data[0].zp!=""){
						    //document.getElementById("sfzz1").innerHTML="<img src=\"data:image/gif;base64,"+data[0].jlQs.jz+"\"/>";
		  					$("#sfzz").attr("src","data:image/gif;base64,"+data[0].zp);
		  				}
					}else{
						alert("卡号非法");
						return false;
					}
				}
		</script> 
		<script  language="javascript" for="WM02R" event="cardevent()">
				$.ajax({
				      type:"POST",
				      url:"sfYz.do?method=jquerSearch2",
				      data : {
						idCardNo:document.getElementById("WM02R").GetCardNum()
					  },
				      dataType:"json",
				      success:callback2   
				});
		 </script>   
  </head>
   <body onload="openPort();" onunload="colsePort();">
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：亲情就餐 </span></div>
	   <form action="sfYz.do?method=search" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td width="30%">罪犯编号：<input type="text" id="frNo" name="frNo" /></td>
      	 		<td width="30%">罪犯姓名：<input type="text" id="frName" name="frName" /></td>
      	 		<td width="30%" rowspan="3" >
      	 			<img id="sfzz" src="<%=basePath %>images/zpbj.jpg" width="100px" height="126px"></img>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;监区：<input type="text" id="jq" name="jq" /></td>
      	 		<td width="30%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级别：<input type="text" id="jb" name="jb" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;罪名：<input type="text" id="zm" name="zm" /></td>
      	 		<td width="30%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;刑期：<input type="text" id="xq" name="xq" /></td>			
      	 	</tr>
      	 	<tr>
      	 		<td width="30%">入监时间：<input type="text" id="rjsj" name="rjsj" /></td>
      	 		<td width="30%">出生日期：<input type="text" id="csrq" name="csrq" /></td>
      	 		<td width="30%">家庭住址：<input type="text" id="jtzz" name="jtzz" style="width:160px"/></td>				
      	 	</tr>
      	 </table>
      	 </form>
      	  <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
      	 <object id="WM02R" classid="CLSID:54A61884-0949-41BE-8FB9-DB55BCCE486C" codebase="<%=basePath %>ocx/WM02R.CAB#version=1,0,0,0" style="display: none"></object>
  </body>
</html>

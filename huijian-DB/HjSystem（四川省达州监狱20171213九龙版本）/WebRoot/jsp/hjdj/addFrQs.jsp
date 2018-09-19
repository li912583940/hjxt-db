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
       <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title>main</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
      	<script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/hjdj.js" type="text/javascript" ></script>
		<script language="javascript" type="text/javascript" src="<%=basePath %>js/textselect.js"></script>
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
				function swfLoadedHandler() {
					document.getElementById("camera").savefile("c:\\temp.jpg",150,176);
					document.getElementById("jz").value=document.getElementById("camera").jpegbase64;
					//document.getElementById("jz").value = document.getElementById("MyFlexApps").paserbytes();
					document.getElementById("zp").innerHTML="<img src=\"C:\\\\temp.jpg\"/>";
				}
			
			
		</script>
		  <!--  <script  language="javascript" for="reID" event="ReadEvent(cardid);">
			 		document.getElementById("qsCard").value=cardid;
			 	
		</script>-->
		
		 <script  language="javascript" for="WM1711" event="cardEvent()">
		 	var idcard=document.getElementById("WM1711").FunGetEveData();
		 	//从第6个字符开始，第14个字符结束，截取M1卡卡号，再转成10进制
	 		idcard=idcard.substring(6,14);
	 		var ten=parseInt(idcard,16);
	 		document.getElementById("qsCard").value=ten;
	 		//截取结束

		 </script>
		<!-- 	 <script  language="javascript" for="WM1711" event="cardEvent()">
		 	
		 		var strdata=document.getElementById("WM1711").FunGetEveData();
		 		
		 			
				if(strdata.length>6){
						var temp="";
						for(var i=6;i<=strdata.length-3;i++){
							
							temp+=strdata.charAt(i);
							
						}
						
						document.getElementById("qsCard").value=parseInt(temp,16);
					}else{
						document.getElementById("WM1711").FunCloseCard();
				}
		 
				
		 </script>-->
		  <script language="javascript" for="IDCard2" event="CardIn( State );">
			{ 
			  if(State == 1)
			  {
				  var IDCard2=document.getElementById("IDCard2");
				  IDCard2.SetPhotoName(2);
				  document.getElementById("qsName").value=IDCard2.NameA;
				  document.getElementById("qsSfz").value=IDCard2.CardNo;
				  document.getElementById("dz").value=IDCard2.Address;
				  var sex=IDCard2.Sex;
				  var xb=document.getElementById("xb");
				  if(sex==2){
					 for(var i=0;i<xb.length;i++){
						if(xb.options[i].value=='女'){
							xb.options[i].selected=true;
							break;
						}
					}
				  }else{
				  	for(var i=0;i<xb.length;i++){
						if(xb.options[i].value=='男'){
							xb.options[i].selected=true;
							break;
						}
					}
				  }
					var zpAddress=IDCard2.PhotoName;
					document.getElementById("photoAddress").value=zpAddress;
					document.getElementById("sfzz").src=basePath+"images/zpbj.jpg";
					document.getElementById("sfzz").src=zpAddress;
			  }
			}
			</script>        
  </head>
   <body onload="openPort();" onunload="colsePort();">
   <!-- <body> -->
   	<div id="user_info"><span>&nbsp;登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：会见登记 </span></div>
	   <form action="hjdj.do?method=checkQs&frNo=${jlFr.frNo}" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px;font-size:15px;font-family:微软雅黑;">
      	 	<tr>
      	 		<td>罪犯编号：<input type="text" id="frNo1" name="frNo1" value="${jlFr.frNo }" disabled="disabled" /></td>
      	 		<td width="60%" rowspan="3">
      	 			<object width="0px" height="0px" id="IDCard2" name="IDCard2"  codebase="<%=basePath %>ocx/SynCardOcx.CAB#version=1,0,0,1" classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039">
					</object>		
					<img id="sfzz" src="<%=basePath %>images/zpbj.jpg" width="150px" height="176px"></img>
						<c:if test="${sysParam.paramData1==1}">
							<!--  
							<object  width="135px" height="141px" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" type="application/x-shockwave-flash" data="<%=basePath%>swf/newCamera.swf" id="MyFlexApps" name="MyFlexApps">
									<param name="movie" value="<%=basePath%>swf/newCamera.swf" />
									<param name="quality" value="high" />
									<param name="bgcolor" value="#ffffff" />
									<param name="allowScriptAccess" value="sameDomain" />
									<param name="allowFullScreen" value="true" />
							</object>
							-->
							<object id="camera" classid="clsid:792FD9B8-5917-45D2-889D-C49FD174D4E0"
								  codebase="<%=basePath %>ocx/capProj1.ocx#version=1,0,0,0"
								  width=160
								  height=176
								  hspace=0
								  vspace=0
							></object>
							<script   language="javascript"> 
			 						document.getElementById("camera").start(160,176);
							</script>   
							<span id="zp" style="width:160,height:176"></span>

							
						</c:if>
						<input type="hidden" id="jz" name="jz" value=""/>
						<input type="hidden" id="photoAddress" name="photoAddress" value=""/>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%">罪犯姓名：<input type="text" id="frName1" name="frName1" value="${jlFr.frName}" disabled="disabled" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%">证件类别：<select id="zjlb" style="width:130px;">
      	 					<option value="1" selected="selected">身份证</option>
      	 					<option value="2">警官证</option>
	      	 				<option value="3">工作证</option>
	      	 				<option value="4">其他</option>
      	 					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      	 	</tr>
      	 	<tr style="height:40px">
      	 		<td>证件号码：<input type="text" id="qsSfz" name="qsSfz" value="${qsSfz}" /></td>
      	 		<td><input type="button" style="color:red;" value="识别身份证" onclick="shibie1();" />&nbsp;&nbsp;<c:if test="${sysParam.paramData1==1}"><input type="button" style="color:red;" value="拍照" onclick="swfLoadedHandler();" />&nbsp;&nbsp;<!-- <input type="button" value="重拍" onclick="clearpicture()" />--></c:if> </td>
      	 	</tr>
      	 	<tr style="height:40px">
      	 		<td>亲属姓名：<input type="text" id="qsName" name="qsName" /> <!--  <input type="radio" name="cardType" value="0" checked="checked"/>旧卡<input type="radio" name="cardType" value="1"/>新卡--></td>
      	 		<td>关系：<select name="gx" id="gx" style="width:130px;height:20px;margin:-2px;" onchange="this.parentNode.nextSibling.value=this.value"> 
								<option value="" style="color:#c2c2c2;"></option> 
								<logic:iterate id="qsgx" name="qsGxList">
					         		<option value="${qsgx}">${qsgx}</option>
				    			 </logic:iterate>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							</td>
      	 	</tr>
      	 	<tr style="height:40px">
      	 		<td>&nbsp;&nbsp;&nbsp;&nbsp;IC卡号：<input type="text" id="qsCard" name="qsCard" /></td>
      	 		<td>
      	 			地址：<input type="text" id="dz" name="dz" />
      	 		</td>
      	 	</tr>
      	 	<tr style="height:40px">
      	 		<td>性别：<select id="xb" style="width:130px;">
      	 					<option value="男" selected="selected">男</option>
      	 					<option value="女">女</option>
      	 					</select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
      	 		<td>电话号码：<input type="text" id="tele" name="tele" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      	 	</tr>
      	 	<tr style="height:40px">
      	 	<c:if test="${users.isSp==1}">
      	 		<td>审批状态：<input type="hidden" id="qsSfzWlh" name="qsSfzWlh" />
      	 			<select id="spState" style="width:130px;">
      	 					<option value="0">未通过</option>
      	 					<option value="1" selected="selected">已通过</option>
      	 			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      	 	</c:if>
      	 	<c:if test="${users.isSp==0}">
      	 		<td>审批状态：<input type="hidden" id="qsSfzWlh" name="qsSfzWlh" />
      	 			<select id="spState" style="width:130px;" disabled="disabled">
      	 					<option value="0" selected="selected">未通过</option>
      	 					<option value="1">已通过</option>
      	 			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      	 	</c:if>
      	 		<td>
      	 			备注：<input type="text" id="bz" name="bz" />
      	 		</td>
      	 	</tr>
      	 	<tr style="height:40px">
      	 		<td><a href="javascript:void(0);" onclick="addSaveQs();return false;"><img src="images/baocun.gif"></img></a><input type="hidden" id="jqNo1" name="jqNo1" value="${jlFr.jq }"/></td>
      	 		<td><a id="returnBack" href="hjdj.do?method=checkQs&frNo=${jlFr.frNo}"><img src="images/fanhui.gif"></img></a></td>
      	 	</tr>
      	 </table>
      	 </form>
      	  	<!--  <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>-->
 		<!--<object id="WM1711" name="WM1711" codebase="<%=path%>/ocx/WM171.CAB#version=1,0,0,0" classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object>--> 
  	  <!--  <object classid="clsid:0185EF68-468E-4380-BA78-8C713E37969A" id="reID" width="0" height="0"></object>
      	   <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
    	  <object id="WM1711" name="WM1711" codebase="<%=basePath%>/ocx/WM171.CAB#version=1,0,0,0"  classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object>-->
  </body>
</html>

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
				  document.getElementById("qsName11").value=IDCard2.NameA;
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
				<object width="0px" height="0px" id="IDCard2" name="IDCard2"  codebase="<%=basePath %>ocx/SynCardOcx.CAB#version=1,0,0,1" classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039">
					</object>
					<td width="30%" style="height:40px">&nbsp;&nbsp;&nbsp; 亲属姓名： 
						<input type="text" id="qsName11" name="qsName11" />
					</td>
					
					<td width="30%" style="height:40px" rowspan="6">
						<img id="sfzz" src="<%=basePath %>images/zpbj.jpg" width="160px"
							height="176px"></img>
						<input type="hidden" id="photoAddress" name="photoAddress"
							value="" />
							
							<br><input type="button" style="color:red;" value="识别身份证" onclick="shibie2();" />
					</td>
				</tr>
				<td width="30%" style="height:40px">
						&nbsp;&nbsp;&nbsp;&nbsp;证件类别：
						<select id="zjlb" style="width:130px;">
      	 					<option value="1" selected="selected">身份证</option>
      	 					<option value="2">警官证</option>
	      	 				<option value="3">工作证</option>
	      	 				<option value="4">其他</option>
      	 					</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<tr>
					<td width="30%" style="height:40px">&nbsp;&nbsp;&nbsp;
						证件号码：
						<input type="text" id="qsSfz" name="qsSfz" />
					</td>
				</tr>
				<tr>
					<td width="30%" style="height:40px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IC卡号：
						<input type="text" id="qsCard" name="qsCard" />
					</td>
				</tr>
				<tr>
					<td width="30%" style="height:40px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：
						<select id="xb" style="width:135px;">
      	 					<option value="男" selected="selected">男</option>
      	 					<option value="女">女</option>
      	 				</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td width="30%" style="height:40px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关系：
							<select name="gx2" id="gx2" style="width:135px;"> 
								<option value="" style="color:#c2c2c2;"></option> 
								<logic:iterate id="qsgx" name="qsList">
					         		<option value="${qsgx}">${qsgx}</option>
				    			 </logic:iterate>
							</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" style="height:40px">
						审批原因：
						<select id="spyy" style="width:470px">
      	 					<option value="1" selected="selected">亲属及监护人未在规定时间或未带有效身份证件会见</option>
							<option value="2">非首次会见的港澳台和外国籍亲属、监护人</option>
							<option value="3">首次会见的港澳台和外国籍亲属、监护人</option>
							<option value="4">受委托的律师、亲友会见</option>
							<option value="5">帮教单位、社会团体会见</option>
							<option value="6">B类重点罪犯会见</option>
							<option value="7">C类重点罪犯会见</option>
							<option value="8">A类重点罪犯会见</option>
							<option value="9">超次数、人数、时间的会见</option>
							<option value="10">区监狱管理局批准的会见</option>
							<option value="11">公检法的会见</option>
      	 				</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

				</tr>
				<tr>
					<td colspan="2" style="height:40px">&nbsp;&nbsp;&nbsp;&nbsp;
						审批备注：
						<input type="text" id="spbz" name="spbz" style="width: 65%;" />
					</td>

				</tr>
				<tr>
					<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						地址：
						<input type="text" id="dz" name="dz" style="width: 65%;" />
					</td>

				</tr>

				<tr>
					<td>
						<c:if test="${hJClient3.paramData2==0}"><input type="button" value="保存并退出"  onclick="savetpqs()" /></c:if>
						<c:if test="${hJClient3.paramData2==1}"><input type="button" value="保存并退出"  onclick="savetpqs1()" /></c:if>
						  <input type="hidden" id="webId1" name="webId1" value="${hjspqs.webId } "  /><input type="hidden" id="hjid" name="hjid" value="${hjspqs.hjid } "  />
					</td>
					<td>
						<input type="button" value="取消" onclick="qxtpqs();" />
					</td>
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

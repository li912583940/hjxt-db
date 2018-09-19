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
				var basePath='<%=basePath%>';
				function openPort(){
					//var isSuc=false;
					//for(var i=1;i<10;i++){
					//	 isSuc=document.getElementById("WM02R").OpenPort(i);
					//	 if(isSuc==true){
					//	 	break;
					//	 }
					//}
					var isSuc=false;
					for(var i=1;i<10;i++){
						 isSuc=document.getElementById("WM1711").OpenPort1(i,"115200");
						 if(isSuc==true){
						 	break;
						 }
					}
				}
				
				function colsePort(){
					document.getElementById("WM02R").ClosePort();
					document.getElementById("WM1711").FunCloseCard();
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
						document.getElementById("qsSfz").value=data[0].qsSfz;
						document.getElementById("qsName").value=data[0].qsName;
						
						document.getElementById("gx").value=data[0].gx;
						document.getElementById("xb").value=data[0].xb;
						document.getElementById("cardNo").value=data[0].cardNo;
						document.getElementById("dz").value=data[0].jtdz;
						if(data[0].qsZp!=""){
						    //document.getElementById("sfzz1").innerHTML="<img src=\"data:image/gif;base64,"+data[0].jlQs.jz+"\"/>";
		  					$("#sfzz").attr("src","data:image/gif;base64,"+data[0].qsZp);
		  				}else{
		  					$("#sfzz").attr("src",basePath+"images/zpbj.jpg");
		  				}
					}else{
						alert("请注意：系统无该卡数据");
						return false;
					}
				}
		</script> 
		<!--  <script  language="javascript" for="WM02R" event="cardevent()">
				$.ajax({
				      type:"POST",
				      url:"sfYz.do?method=jquerSearch2",
				      data : {
						idCardNo:document.getElementById("WM02R").GetCardNum()
					  },
				      dataType:"json",
				      success:callback2   
				});
		 </script>   -->
		 
		  <script  language="javascript" for="WM1711" event="cardEvent()">
		 	
		 		var strdata=document.getElementById("WM1711").FunGetEveData();
		 		
		 			
				if(strdata.length>6){
//						var temp="";
//						for(var i=6;i<=strdata.length-3;i++){
							
//							temp+=strdata.charAt(i);
							
//						}
//						alert(temp);
						$.ajax({
						      type:"POST",
						      url:"sfYz.do?method=jquerSearch3",
						      data : {
								idCardNo:strdata
							  },
						      dataType:"json",
						      success:callback2   
						});
					}else{
						document.getElementById("WM1711").FunCloseCard();
				}
		 
				
		 </script>
  </head>
   <body onload="openPort();" onunload="colsePort();">
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：身份验证 </span></div>
	   <form action="sfYz.do?method=search" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td>家属身份证：<input type="text" id="qsSfz" name="qsSfz" /></td>
      	 		<td>家属姓名：<input type="text" id="qsName" name="qsName" /></td>
      	 		<td width="30%" rowspan="3" >
      	 			<img id="sfzz" src="<%=basePath %>images/zpbj.jpg" width="100px" height="126px"></img>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关系：<input type="text" id="gx" name="gx" /></td>
      	 		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：<input type="text" id="xb" name="xb" /></td>
      	 	
      	 	</tr>
      	 	<tr>
      	 		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卡号：<input type="text" id="cardNo" name="cardNo" /></td>
      	 		<td>家庭地址：<input type="text" id="dz" name="dz" /></td>
      	 	
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" style="height:50px;font-size:15px;BACKGROUND-COLOR:#FA8072">&nbsp;&nbsp;&nbsp;&nbsp;罪犯编号：<input type="text" id="frNo" name="frNo" /></td>
      	 		<td width="30%" style="height:50px;font-size:15px;BACKGROUND-COLOR:#FA8072">罪犯姓名：<input type="text" id="frName" name="frName" /></td>
      	 		<td width="30%" style="height:50px;font-size:15px;BACKGROUND-COLOR:#FA8072">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;监区：<input type="text" id="jq" name="jq" /></td>
      	 		
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" style="height:50px;font-size:15px;BACKGROUND-COLOR:#FA8072">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级别：<input type="text" id="jb" name="jb" /></td>
      	 		<td width="30%" style="height:50px;font-size:15px;BACKGROUND-COLOR:#FA8072">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;罪名：<input type="text" id="zm" name="zm" /></td>
      	 		<td width="30%" style="height:50px;font-size:15px;BACKGROUND-COLOR:#FA8072">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;刑期：<input type="text" id="xq" name="xq" /></td>			
      	 	</tr>
      	 	<tr>
      	 		
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" style="height:50px;font-size:15px;BACKGROUND-COLOR:#FA8072">&nbsp;&nbsp;&nbsp;&nbsp;入监时间：<input type="text" id="rjsj" name="rjsj" /></td>
      	 		<td width="30%" style="height:50px;font-size:15px;BACKGROUND-COLOR:#FA8072">出生日期：<input type="text" id="csrq" name="csrq" /></td>
      	 		<td width="30%" style="height:50px;font-size:15px;BACKGROUND-COLOR:#FA8072">家庭住址：<input type="text" id="jtzz" name="jtzz" style="width:160px"/></td>				
      	 	</tr>
      	 	
      	 </table>
      	 </form>
      	  <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
      		<!-- <object id="WM02R" classid="CLSID:54A61884-0949-41BE-8FB9-DB55BCCE486C" codebase="<%=basePath %>ocx/WM02R.CAB#version=1,0,0,0" style="display: none"></object> --> 
 		<object id="WM1711" name="WM1711" codebase="<%=basePath%>/ocx/WM171.CAB#version=1,0,0,0" classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object>
  </body>
</html>

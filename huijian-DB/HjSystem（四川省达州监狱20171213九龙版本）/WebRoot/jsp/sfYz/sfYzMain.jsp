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
		<script language="javascript" type="text/javascript" src="<%=basePath %>js/textselect.js"></script>
		<script type="text/javascript" language="javascript" > 
				var basePath='<%=basePath%>';
			    document.onkeydown   =   function(){
					if(event.keyCode   ==   8) {
						if(event.srcElement.tagName.toLowerCase()   !=   "input"  &&   event.srcElement.tagName.toLowerCase()   !=   "textarea")   
						event.returnValue   =   false;   
					}   
				}
			
		
				
				function callback3(data){
				var frTable=document.getElementById("info");
					frTable.innerText="";
				var qsTable=document.getElementById("info1");
					qsTable.innerText="";
					if(data !=""){
							var tr=frTable.insertRow();
							var td1=tr.insertCell();
							td1.innerText=data[data.length-1].jqName;
							var td2=tr.insertCell();
							td2.innerText=data[data.length-1].frNo;
							var td3=tr.insertCell();
							td3.innerText=data[data.length-1].frName;
							var td4=tr.insertCell();
							td4.innerText=data[data.length-1].djTime;
							var td5=tr.insertCell();
							td5.innerText=data[data.length-1].zwNo;
						document.getElementById("hjid").value=data[data.length-1].hjid;
							
						for(var i=0;i<data.length-1;i++){
							var index = i;
							var tr1=qsTable.insertRow();
							var td10=tr1.insertCell();
							td10.innerText=index+1;
							var td6=tr1.insertCell();
							td6.innerHTML=data[i].qsName+"<input type=\"hidden\" name=\"qsSfz\"  value=\""+data[i].qsSfz+"\"><input type=\"hidden\" name=\"qsName\" value=\""+data[i].qsName+"\"><input type=\"hidden\" name=\"webID\" value=\""+data[i].webId+"\"><input type=\"hidden\" name=\"xb\" value=\""+data[i].xb+"\"><input type=\"hidden\" name=\"dz\" value=\""+data[i].dz+"\">";
							var td7=tr1.insertCell();
							td7.innerText=data[i].gx;
							var td8=tr1.insertCell();
							td8.innerText=data[i].xb;
							var td9=tr1.insertCell();
							td9.innerText=data[i].qsSfz;
							tr1.onclick=jquerSearch1;
						}
						
					}else{
						alert("该亲属没有被登记");
						return false;					
					}
				}
				
				function jquerSearch1(){
					var tr = event.srcElement;
					while (tr && tr.tagName != "TR") {
						tr = tr.parentElement;
					}
					var qsSfzs=document.getElementsByName("qsSfz");
					var qsNames=document.getElementsByName("qsName");
					var webIDs=document.getElementsByName("webID");
					var xbs=document.getElementsByName("xb");
					var dzs=document.getElementsByName("dz");
					var qsSfz= qsSfzs[tr.rowIndex].value
					var qsName= qsNames[tr.rowIndex].value
					var xb= xbs[tr.rowIndex].value
					var dz= dzs[tr.rowIndex].value
					var webID= webIDs[tr.rowIndex-1].value
					 document.getElementById("qsName").value=qsName;
					 document.getElementById("qsSfz").value=qsSfz;
					 document.getElementById("xb").value=xb;
					 document.getElementById("dz").value=dz;
					document.getElementById("sfzz").src="sfYz.do?method=showPhoto&webID="+webID;
						$.ajax({
						      type:"POST",
						      url:"sfYz.do?method=jquerSearch",
						      data : {
								qsSfz:qsSfz,
								qsName:qsName
							 },
						      dataType:"json",
						      success:callback3  
						});
				}
				
				function openPort(){
					var str=document.getElementById("IDCard2").FindReader();
					if(str>1000){
						document.getElementById("IDCard2").SetloopTime(1000);
				  		document.getElementById("IDCard2").SetReadType(1);
				  		document.getElementById("IDCard2").SetPhotoType(1);
					}
				
				}
				function colsePort(){
					document.getElementById("IDCard2").SetReadType(0);
				}
				
			
			function chaxun(){
				 var qsName=document.getElementById("qsName").value;
				 var qsSfz=document.getElementById("qsSfz").value;
				if(qsSfz==""){
					alert("亲属身份证不能为空");
					return false;
				}
				$.ajax({
						      type:"POST",
						      url:"sfYz.do?method=jquerSearch",
						      data : {
								qsSfz:qsSfz,
								qsName:qsName
							  },
						      dataType:"json",
						      success:callback3   
						});

			}
				
		</script>
		 <script language="javascript" for="IDCard2" event="CardIn( State );">
			{ 
			  if(State == 1)
			  {
			  	  var IDCard2=document.getElementById("IDCard2");
				  document.getElementById("qsName").value=IDCard2.NameA;
				  var qsName=document.getElementById("qsName").value;
				  document.getElementById("qsSfz").value=IDCard2.CardNo;
				  var qsSfz=document.getElementById("qsSfz").value;
				  document.getElementById("dz").value=IDCard2.Address;
				   var sex=IDCard2.Sex;
				  var xb=document.getElementById("xb");
				  if(sex==2){
					document.getElementById("xb").value="女";
					
				  }else{
				  	document.getElementById("xb").value="男";
				  }
					var zpAddress=IDCard2.PhotoName;
					document.getElementById("photoAddress").value=zpAddress;
					document.getElementById("sfzz").src=basePath+"images/zpbj.jpg";
					document.getElementById("sfzz").src=zpAddress;
					
					$.ajax({
						      type:"POST",
						      url:"sfYz.do?method=jquerSearch",
						      data : {
								qsSfz:qsSfz,
								qsName:qsName
							  },
						      dataType:"json",
						      success:callback3   
						});
			  }
			}
			</script> 
  </head>
   <body onload="openPort();" onunload="colsePort();">
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：身份验证 </span></div>
	   <form action="sfYz.do?method=search" method="post">
<!-- <div style="color:red;margin-left:8%" ><font size="5px">当刷身份证无反应或者提示家属没有登记,再或者证件是驾照时，请手工输入“通知单”上家属证件号码查询</font></div> -->
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px;font-size:15px;font-family:微软雅黑;">
      	 	<tr>
      	 		<td width="30%" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;亲属姓名：<input type="text" id="qsName" name="qsName" /></td>
      	 		<td width="30%" colspan="3" rowspan="4">
					<img id="sfzz" src="<%=basePath %>images/zpbj.jpg" width="150px" height="176px"></img>			
					<input type="hidden" id="photoAddress" name="photoAddress" value=""/>	
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲属身份证：<input type="text" id="qsSfz" name="qsSfz"  /> <input type="button" style="margin-left:3%;BACKGROUND-COLOR:#ffc0cb;font-weight:bolder;color:blue" onclick="chaxun();" value="查询" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：<input type="text" id="xb" name="xb"  /></td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址：<input type="text" id="dz" name="dz" /></td>
      	 		<!--  <td width="30%" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关系：<input type="text" id="gx" name="gx" /></td>-->
      	 	</tr>
      	 	<tr>
      	 		<td width="15%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">监区</td>
      	 		<td width="15%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">犯人编号</td>
      	 		<td width="15%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">犯人姓名</td>
      	 		<td width="15%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">登记时间</td>
      	 		<td width="15%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">座位号</td>	
      	 	</tr>
      	 	<tbody id="info"></tbody>
      	 	
      	 	
      	 </table>
      	 
      	  <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px;font-size:15px;font-family:微软雅黑;">
      	 		<tr>
      	 		<td width="10%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">序号</td>
      	 		<td width="15%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">亲属姓名</td>
      	 		<td width="10%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">亲属关系</td>
      	 		<td width="10%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">亲属性别</td>
      	 		<td width="15%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">亲属身份证</td>	
      	 	</tr>
      	 		<tbody id="info1"></tbody>
      	  </table>

      	  <input type="hidden"  id="hjid" value="" />
      	 </form>
      	  
      	      <object width="0px" height="0px" id="IDCard2" name="IDCard2"   classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039">
					</object> 
 		
  </body>
</html>

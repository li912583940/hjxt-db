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
		var basePath='<%=basePath%>'; 
			    document.onkeydown   =   function(){
					if(event.keyCode   ==   8) {
						if(event.srcElement.tagName.toLowerCase()   !=   "input"  &&   event.srcElement.tagName.toLowerCase()   !=   "textarea")   
						event.returnValue   =   false;   
					}   
				}
				function callback2(data){
				    if(data[i]=='-1'){
				    	var frTable=document.getElementById("info");
						frTable.innerText="";
				    	alert("该身份证信息不存在");
				    }else{
				    	var frTable=document.getElementById("info");
				    	frTable.innerText='';
				    	for(var i=0;i<data.length;i++){
				    		var index = i;
				    		var tr=frTable.insertRow();
				    		var td4=tr.insertCell();
							td4.innerText=index+1;
				    		var td0=tr.insertCell();
				    		td0.style.fontSize=16;
							td0.style.background='yellow';
							td0.innerHTML="<b>"+data[i].jqName+"</b>";
//				    		td0.innerText=data[i].jqName;
				    		var td1=tr.insertCell();
				    		td1.style.fontSize=16;
							td1.style.background='yellow';
				    		td1.innerHTML="<b>"+data[i].frName+"</b>";
//alert(data[i].hjUse);
				    		if(data[i].hjUse<1){
				    			var td2=tr.insertCell();
				    			td2.style.fontSize=16;
								td2.style.background='#FFFACD';
				    			td2.innerHTML="<b>"+data[i].hjUse+"</b>";
				    			var td3=tr.insertCell();
				    			td3.style.fontSize=16;
								td3.style.background='#FFFACD';
				    			td3.innerHTML="<b>"+data[i].hjLastTime+"</b>";
				    		}else{
				    			var td2=tr.insertCell();
				    			td2.style.fontSize=16;
								td2.style.background='#FFFACD';
				    			td2.innerHTML="<font color=\"red\">"+data[i].hjUse+"</font>";
				    			var td3=tr.insertCell();
				    			td3.style.fontSize=16;
								td3.style.background='#FFFACD';
				    			td3.innerHTML="<font color=\"red\">"+data[i].hjLastTime;
				    		}
				    		
				    	}
					 }
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
					var qsSfz=document.getElementById("qsSfz").value;
					if(qsSfz==""){
						alert("亲属身份证不能为空");
						return false;
					}
					$.ajax({
					      type:"POST",
					      url:"sfYz.do?method=jquerSearch5",
					      data : {
							idCardNo:document.getElementById("qsSfz").value
						  },
					      dataType:"json",
					      success:callback2   
					});

				}
		</script> 
		<script language="javascript" for="IDCard2" event="CardIn( State );">
			{ 
			 if(State == 1)
			  {		
				 
					
//			  	 document.getElementById("sfzz").src=""
			  	 var IDCard2=document.getElementById("IDCard2");
			  	 var zpAddress=IDCard2.PhotoName;
				 document.getElementById("photoAddress").value=zpAddress;
				 document.getElementById("sfzz").src=basePath+"images/zpbj.jpg";
				 document.getElementById("sfzz").src=zpAddress;
				 document.getElementById("qsName").value=IDCard2.NameA;
				 document.getElementById("qsSfz").value=IDCard2.CardNo;
				 document.getElementById("dz").value=IDCard2.Address;
				 var sex=IDCard2.Sex;
				 if(sex==2){
					document.getElementById("sex").value='女';
				 }else{
					document.getElementById("sex").value='男';
				 }
//				 var zpAddress=IDCard2.PhotoName;
//				 $("#sfzz").attr("src",zpAddress);
				 //document.getElementById("sfzz").src="";
				 //document.getElementById("sfzz").src=zpAddress;
				 $.ajax({
				      type:"POST",
				      url:"sfYz.do?method=jquerSearch5",
				      data : {
						idCardNo:document.getElementById("qsSfz").value
					  },
				      dataType:"json",
				      success:callback2   
				});
			  }
			}
		 </script>   
  </head>
   <body onload="openPort();" onunload="colsePort();">
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：身份验证</span></div>
	   <form action="sfYz.do?method=search3" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:80%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td width="30%" colspan="3" style="height:50px;font-size:20px">亲属姓名：<input type="text" id="qsName" name="qsName" /></td>
      	 		<td width="40%" colspan="2" rowspan="4">
      	 			<img id="sfzz" src="<%=basePath %>images/zpbj.jpg" width="170px" height="190px"></img><input type="hidden" id="photoAddress" name="photoAddress" value=""/>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="3" style="height:50px;font-size:20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲属身份证：<input type="text" id="qsSfz" name="qsSfz" /><input type="button" style="margin-left:3%;BACKGROUND-COLOR:#ffc0cb;font-weight:bolder;color:blue" onclick="chaxun();" value="查询" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="3" style="height:50px;font-size:20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：<input type="text" id="sex" name="sex" /></td>
      	 					
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="3" style="height:50px;font-size:20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址：<input type="text" id="dz" name="dz" /></td>
      	 		
      	 	</tr>
      	 	<tr>
      	 		<td width="5%" style="height:50px;font-size:30px;BACKGROUND-COLOR:#FA8072">序号 </td>
      	 		<td width="10%" style="height:50px;font-size:30px;BACKGROUND-COLOR:#FA8072">监区</td>
      	 		<td width="15%" style="height:50px;font-size:30px;BACKGROUND-COLOR:#FA8072">罪犯姓名</td>
      	 		<td width="15%" style="height:50px;font-size:30px;BACKGROUND-COLOR:#FA8072">本月会见次数</td>
      	 		<td width="15%" style="height:50px;font-size:30px;BACKGROUND-COLOR:#FA8072">上次会见时间 </td>
      	 						
      	 	</tr>
      	 	<tbody id="info">
      	 	<tr>
      	 		<td width="100%" colspan="5" style="height:50px;font-size:30px">暂无数据</td>
      	 						
      	 	</tr>
      	 	</tbody>
      	 </table>
      	 </form>
      	 <object  width="0px" height="0px" id="IDCard2" name="IDCard2"  codebase="<%=basePath %>ocx/SynCardOcx.CAB#version=1,0,0,1" classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039">
						</object>
  </body>
</html>

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
				
				function openPort(){
					//reID.ReadCardID(4, "baud=9600 parity=N data=8 stop=1");
					var isSuc=false;
					for(var i=1;i<10;i++){
						 isSuc=document.getElementById("WM1711").OpenPort1(i,"115200");
						 if(isSuc==true){
						 	break;
						 }
					}
				}
				function colsePort(){
					document.getElementById("WM1711").FunCloseCard();
				}
			
				
				
				function callback3(data){
				var frTable=document.getElementById("info");
					frTable.innerText="";
				var qsTable=document.getElementById("info1");
					qsTable.innerText="";
					if(data[0]=='-1'){
						alert("由于数据有误，此张卡片绑定了两位亲属，无法通过验证！");
						return false;
					}else if(data[0]=='-2'){
						alert("此张卡片没有绑定任何亲属，无法通过验证！");
					}else if(data !=""){
							
							document.getElementById("qsName").value=data[data.length-2].qsName;
							document.getElementById("qsSfz").value=data[data.length-2].qsSfz;
							document.getElementById("xb").value=data[data.length-2].xb;
							document.getElementById("dz").value=data[data.length-2].dz;
							if(data[data.length-2].zpState==1){
								document.getElementById("sfzz").src="sfYz.do?method=showPhoto&webID="+data[data.length-2].webId;
							}else{
								document.getElementById("sfzz").src=basePath+"images/zpbj.jpg";
							}
							
							
							var tr=frTable.insertRow();
							var td1=tr.insertCell();
							td1.innerText=data[data.length-1].jqName;
							var td2=tr.insertCell();
							td2.innerText=data[data.length-1].frNo;
							var td3=tr.insertCell();
							td3.innerText=data[data.length-1].frName;
							var td5=tr.insertCell();
							td5.innerText=data[data.length-1].djTime;
						document.getElementById("hjid").value=data[data.length-1].hjid;
							
						for(var i=0;i<data.length-2;i++){
							var index = i;
							var tr1=qsTable.insertRow();
							var td10=tr1.insertCell();
							td10.innerText=index+1;
							var td6=tr1.insertCell();
							td6.innerHTML=data[i].qsName+"<input type=\"hidden\" name=\"qsSfz\"  value=\""+data[i].qsSfz+"\"><input type=\"hidden\" name=\"qsName\" value=\""+data[i].qsName+"\"><input type=\"hidden\" name=\"webID\" value=\""+data[i].webId+"\"><input type=\"hidden\" name=\"xb\" value=\""+data[i].xb+"\"><input type=\"hidden\" name=\"dz\" value=\""+data[i].dz+"\"> <input type=\"hidden\" name=\"zpState\" value=\""+data[i].zpState+"\">";
							var td7=tr1.insertCell();
							td7.innerText=data[i].gx;
							var td8=tr1.insertCell();
							td8.innerText=data[i].xb;
							var td9=tr1.insertCell();
							td9.innerText=data[i].qsSfz;
							if(data[i].faceState==0){
								var td10=tr1.insertCell();
								td10.innerHTML="<font color=\"red\">未注册</font>";
							}else if(data[i].faceState==1){
								var td10=tr1.insertCell();
								td10.innerHTML="已注册";
							}else if(data[i].faceState==2){
								var td10=tr1.insertCell();
								td10.innerHTML="<font color=\"red\">未注册</font>";
							}else if(data[i].faceState==3){
								var td10=tr1.insertCell();
								td10.innerHTML="已注册";
							}else if(data[i].faceState==4){
								var td10=tr1.insertCell();
								td10.innerHTML="<font color=\"red\">未注册</font>";
							}else{
								var td10=tr1.insertCell();
								td10.innerHTML="<font color=\"red\">未注册</font>";
							}
							
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
					var zpStates=document.getElementsByName("zpState");
					var qsSfz= qsSfzs[tr.rowIndex].value
					var qsName= qsNames[tr.rowIndex].value
					var xb= xbs[tr.rowIndex].value
					var dz= dzs[tr.rowIndex].value
					var zpState= zpStates[tr.rowIndex-1].value
					var webID= webIDs[tr.rowIndex-1].value
					 document.getElementById("qsName").value=qsName;
					 document.getElementById("qsSfz").value=qsSfz;
					 document.getElementById("xb").value=xb;
					 document.getElementById("dz").value=dz;
					 if(zpState==1){
								document.getElementById("sfzz").src="sfYz.do?method=showPhoto&webID="+webID;
							}else{
								document.getElementById("sfzz").src=basePath+"images/zpbj.jpg";
							}
					
						$.ajax({
						      type:"POST",
						      url:"sfYz.do?method=jquerSearch",
						      data : {
								qsSfz:qsSfz,
								qsName:qsName
							 },
						      dataType:"json",
						      success:callback6
						});
				}
				function callback6(data){
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
							var td5=tr.insertCell();
							td5.innerText=data[data.length-1].djTime;
						document.getElementById("hjid").value=data[data.length-1].hjid;
							
						for(var i=0;i<data.length-1;i++){
							var index = i;
							var tr1=qsTable.insertRow();
							var td10=tr1.insertCell();
							td10.innerText=index+1;
							var td6=tr1.insertCell();
							td6.innerHTML=data[i].qsName+"<input type=\"hidden\" name=\"qsSfz\"  value=\""+data[i].qsSfz+"\"><input type=\"hidden\" name=\"qsName\" value=\""+data[i].qsName+"\"><input type=\"hidden\" name=\"webID\" value=\""+data[i].webId+"\"><input type=\"hidden\" name=\"xb\" value=\""+data[i].xb+"\"><input type=\"hidden\" name=\"dz\" value=\""+data[i].dz+"\"> <input type=\"hidden\" name=\"zpState\" value=\""+data[i].zpState+"\">";
							var td7=tr1.insertCell();
							td7.innerText=data[i].gx;
							var td8=tr1.insertCell();
							td8.innerText=data[i].xb;
							var td9=tr1.insertCell();
							td9.innerText=data[i].qsSfz;
							if(data[i].faceState==0){
								var td10=tr1.insertCell();
								td10.innerHTML="<font color=\"red\">未注册</font>";
							}else if(data[i].faceState==1){
								var td10=tr1.insertCell();
								td10.innerHTML="已注册";
							}else if(data[i].faceState==2){
								var td10=tr1.insertCell();
								td10.innerHTML="<font color=\"red\">未注册</font>";
							}else if(data[i].faceState==3){
								var td10=tr1.insertCell();
								td10.innerHTML="已注册";
							}else if(data[i].faceState==4){
								var td10=tr1.insertCell();
								td10.innerHTML="<font color=\"red\">未注册</font>";
							}else{
								var td10=tr1.insertCell();
								td10.innerHTML="<font color=\"red\">未注册</font>";
							}
							tr1.onclick=jquerSearch1;
						}
						
					}else{
						alert("该亲属没有被登记");
						return false;					
					}
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
				function red(){
					 var kah=document.getElementById("kah").value;
					 $.ajax({
						      type:"POST",
						      url:"sfYz.do?method=jquerSearch4",
						      data : {
								cardNo:kah
							  },
						      dataType:"json",
						      success:callback3   
						});
				}
		</script>
		 <script  language="javascript" for="WM1711" event="cardEvent()">
		 	var idcard=document.getElementById("WM1711").FunGetEveData();
		 	//从第6个字符开始，第14个字符结束，截取M1卡卡号，再转成10进制
	 		idcard=idcard.substring(6,14);
	 		var ten=parseInt(idcard,16);
		 		 $.ajax({
			 		      type:"POST",
			 		      url:"sfYz.do?method=jquerSearch4",
			 		       data : {
								cardNo:ten								
							  },
			 		      dataType:"json",
			 		      success:callback3  
			 		});	
		 </script>
		<!--  <script  language="javascript" for="reID" event="ReadEvent(cardid);">
		//alert(cardNo);
			 $.ajax({
			 		      type:"POST",
			 		      url:"sfYz.do?method=jquerSearch4",
			 		       data : {
								cardNo:cardid
							  },
			 		      dataType:"json",
			 		      success:callback3  
			 		});		
			 		
		</script>-->
  </head>
   <body onload="openPort();" onunload="colsePort();">
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：身份验证 </span></div>
	   <form action="sfYz.do?method=search" method="post">
<!-- <div style="color:red;margin-left:8%" ><font size="5px">当刷身份证无反应或者提示家属没有登记,再或者证件是驾照时，请手工输入“通知单”上家属证件号码查询</font></div> -->
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px;font-size:15px;font-family:微软雅黑;">
      	 	<tr>
      	 		<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;亲属姓名：<input type="text" id="qsName" name="qsName" /></td>
      	 		<td width="60%" colspan="2" rowspan="4">
					<img id="sfzz" src="<%=basePath %>images/zpbj.jpg" width="150px" height="176px"></img>			
					<input type="hidden" id="photoAddress" name="photoAddress" value=""/>	
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="2">亲属身份证：<input type="text" id="qsSfz" name="qsSfz"  />  </td>
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
      	 	</tr>
      	 	<tbody id="info"></tbody>
      	 	
      	 	
      	 </table>
      	 
      	  <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:90%; margin:0 auto; margin-top:10px;font-size:15px;font-family:微软雅黑;">
      	 		<tr>
      	 		<td width="10%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">序号</td>
      	 		<td width="10%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">亲属姓名</td>
      	 		<td width="10%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">亲属关系</td>
      	 		<td width="10%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">亲属性别</td>
      	 		<td width="10%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">亲属身份证</td>
      	 		<td width="10%" style="height:30px;font-size:20px;color:#ffffff;BACKGROUND-COLOR:#76a5af">人脸注册状态</td>	
      	 	</tr>
      	 		<tbody id="info1">
      	 		<tr>
      	 			<td width="100%" colspan="6" style="height:50px;font-size:30px;color:#980000">暂无数据</td>      	 						
      	 		</tr>
      	 	</tbody>
      	  </table>

      	  <input type="hidden"  id="hjid" value="" />
      	 </form>
      	  
      	    <!--  <object classid="clsid:0185EF68-468E-4380-BA78-8C713E37969A" id="reID" width="0" height="0"></object>-->
      	   <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
    	  <object id="WM1711" name="WM1711" codebase="<%=basePath%>/ocx/WM171.CAB#version=1,0,0,0"  classid="clsid:B56F7942-B054-416C-9BF8-C7339EB593D1" style="display: none"></object>
  </body>
</html>

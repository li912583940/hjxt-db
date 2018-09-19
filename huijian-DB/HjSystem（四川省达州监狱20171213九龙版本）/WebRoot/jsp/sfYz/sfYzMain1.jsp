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
				function callback2(data){
				    if(data[0]=='-1'){
				    	alert("该卡号不存在");
				    	return false;
				    }if(data[0]=='-2'){
				    	alert("今天没有登记");
				    	return false;
				    }if(data[0]=='-3'){
				    	alert("登记已取消");
				    	return false;
				    }if(data[0]=='-4'){
				    	alert("该卡号没有被登记");
				    	return false;
				    }if(data[0]=='-5'){
				    	alert("重复刷卡");
				    	return false;
				    }if(data[0]=='-6'){
				    	alert("登记未审批");
				    	return false;
				    }else{
				    	var frTable=document.getElementById("info");
						frTable.innerText="";
						if(data[0].list.length>0){
							for(var i=0;i<data[0].list.length;i++){
								var tr=frTable.insertRow();
								var td1=tr.insertCell();
								td1.innerText=data[0].list[i].jqName;
								var td2=tr.insertCell();
								td2.innerText=data[0].list[i].frNo;
								var td3=tr.insertCell();
								td3.innerText=data[0].list[i].frName;
								var td5=tr.insertCell();
								td5.innerText=data[0].list[i].zwNo;
								var td6=tr.insertCell();
								td6.innerText=data[0].list[i].hjUse;
								var td7=tr.insertCell();
								td7.innerText=data[0].list[i].hjLastTime;
							}
					    }
					 }
					 document.getElementById("qsName").value=data[0].jlQs.qsName;
					 document.getElementById("qsSfz").value=data[0].jlQs.qsSfz;
					 document.getElementById("dz").value=data[0].jlQs.dz;
					 document.getElementById("gx").value=data[0].jlQs.gx;
					 document.getElementById("state").value=data[0].state;
					 document.getElementById("inCount").value=data[0].inCount;
					 document.getElementById("outCount").value=data[0].outCount;
					 document.getElementById("leftCount").value=data[0].leftCount;
					 if(data[0].jzBase64!=""){
					    //document.getElementById("sfzz1").innerHTML="<img src=\"data:image/gif;base64,"+data[0].jlQs.jz+"\"/>";
	  					$("#sfzz1").attr("src","data:image/gif;base64,"+data[0].jzBase64);
	  				 }
	  				 if(data[0].zpBase64!=""){
					    //document.getElementById("sfzz1").innerHTML="<img src=\"data:image/gif;base64,"+data[0].jlQs.jz+"\"/>";
	  					$("#sfzz").attr("src","data:image/gif;base64,"+data[0].zpBase64);
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
				
		</script> 
		<script  language="javascript" for="WM02R" event="cardevent()">
				$.ajax({
				      type:"POST",
				      url:"sfYz.do?method=jquerSearch1",
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
	<div id="lm_info"><span>当前位置：身份验证</span></div>
	   <form action="sfYz.do?method=search" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:70%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td width="30%" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;亲属姓名：<input type="text" id="qsName" name="qsName" /></td>
      	 		<td width="40%" colspan="3" rowspan="3">
      	 			<img id="sfzz" src="<%=basePath %>images/zpbj.jpg" width="100px" height="126px"></img>
      	 			<img id="sfzz1" src="<%=basePath %>images/zpbj.jpg" width="100px" height="126px"></img>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="3">亲属身份证：<input type="text" id="qsSfz" name="qsSfz" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关系：<input type="text" id="gx" name="gx" /></td>
      	 					
      	 	</tr>
      	 	<tr>
      	 		<td width="30%" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址：<input type="text" id="dz" name="dz" /></td>
      	 		<td width="30%" colspan="3">刷卡状态：<input type="text"  id="state" size="1"/>&nbsp;进入人数：<input type="text"  id="inCount" size="1" value="${inCount}"/>&nbsp;离开人数：<input type="text"  id="outCount" size="1" value="${outCount}"/>	&nbsp;剩余人数：<input type="text"  id="leftCount" size="1" value="${leftCount}"/>					</td>
      	 	</tr>
      	 	<tr>
      	 		<td width="10%">监区</td>
      	 		<td width="10%">犯人编号</td>
      	 		<td width="10%">犯人姓名</td>
      	 		<td width="10%">座位号</td>
      	 		<td width="10%">本月会见次数</td>
      	 		<td width="10%">上次会见时间 </td>	
      	 	</tr>
      	 	<tbody id="info"></tbody>
      	 </table>
      	 </form>
      	  <object id="MSCOMM32" name="MSCOMM32" codebase="<%=basePath%>ocx/MSCOMM32.OCX" classid="clsid:648A5600-2C6E-101B-82B6-000000000014" style="display: none"></object>
      	 <object id="WM02R" classid="CLSID:54A61884-0949-41BE-8FB9-DB55BCCE486C" codebase="<%=basePath %>ocx/WM02R.CAB#version=1,0,0,0" style="display: none"></object>
  </body>
</html>

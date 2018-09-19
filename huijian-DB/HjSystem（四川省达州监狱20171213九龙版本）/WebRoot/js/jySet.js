function updateJySet(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var jyId=tr.cells[5].children[1].value;
	document.forms[0].action="jySet.do?method=updateJySet&jyId="+jyId;
	document.forms[0].submit();
}
function saveJySet(){
	var serverIP=document.getElementById("serverIP").value;
	var port=document.getElementById("port").value;
	var audioPort=document.getElementById("audioPort").value;
	var recordURL=document.getElementById("recordURL").value;
	var jyId=document.getElementById("jyId").value;
	serverIP=serverIP.replace(/\s+$/g,"");
	if(checkIP(serverIP)==false){
		return false
	};
	port=port.replace(/\s+$/g,"");
	if(port==""){
		alert("状态端口号不能为空");
		return false;
	}
	if(audioPort==""){
		alert("监听端口号不能为空");
		return false;
	}
	var patrn1=/^\d+$/;
	if (!patrn1.test(port)){
		alert("状态端口号包含非法字符，请输入正整数");
		return false;
	}
	if (!patrn1.test(audioPort)){
		alert("监听端口号包含非法字符，请输入正整数");
		return false;
	}
	if(serverIP.indexOf("'")>-1){
		alert("服务器IP输入有误，不能包含特殊符号");
		return false;
	}
	if(recordURL.indexOf("'")>-1){
		alert("录音网络地址，不能包含特殊符号");
		return false;
	}
	port=port.replace(/\s+$/g,"");
	serverIP=encodeURI(encodeURI(serverIP));
	recordURL=encodeURI(encodeURI(recordURL));
	 $.ajax({
	      type:"POST",
	      url:"jySet.do",
	      data:"method=saveJySet&jyId="+jyId+"&port="+port+"&audioPort="+audioPort+"&serverIP="+serverIP+"&recordURL="+recordURL,
	      dataType:"json",
	      success:callback,
	      error:callback1
	});
}
function callback(data){
	if(data[0]==null){
		document.forms[0].action="jySet.do?method=search";
		document.forms[0].submit();
	}
}
function callback1(a,b,c){
	alert(a);
	alert(b);
	alert(c);
}
function checkIP(obj) 
{ 
var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/; 
var reg = obj.match(exp); 
if(reg==null) 
{ 
alert("IP地址不合法！"); 
 return false;
} 
else 
{ 
 return true;
} 
}


//会见审批参数设置
function hjSpParam(){
	document.forms[0].action="jySet.do?method=toHjSpParam";
	document.forms[0].submit();
}
function total(){
	var totalSwitch= document.getElementById("totalSwitch");
	if(totalSwitch.checked==true){
		document.getElementById("monNumSwitch").disabled=false;
		document.getElementById("jbSwitch").disabled=false;
		document.getElementById("banSwitch").disabled=false;
		document.getElementById("jqBanSwitch").disabled=false;
		document.getElementById("qsgxSwitch").disabled=false;
	}else{
		document.getElementById("monNumSwitch").disabled=true;
		document.getElementById("monNumSwitch").checked=false;
		document.getElementById("jbSwitch").disabled=true;
		document.getElementById("jbSwitch").checked=false;
		document.getElementById("banSwitch").disabled=true;
		document.getElementById("banSwitch").checked=false;
		document.getElementById("jqBanSwitch").disabled=true;
		document.getElementById("jqBanSwitch").checked=false;
		document.getElementById("qsgxSwitch").disabled=true;
		document.getElementById("qsgxSwitch").checked=false;
	}
}
function saveHjSpParam(){
	var totalSwitch= document.getElementById("totalSwitch");
	var totalSwitch1="0";
	
	var monNumSwitch =document.getElementById("monNumSwitch");
	var monNumSwitch1="0";
	var jbSwitch =document.getElementById("jbSwitch");
	var jbSwitch1="0";
	var banSwitch =document.getElementById("banSwitch");
	var banSwitch1="0";
	var jqBanSwitch =document.getElementById("jqBanSwitch");
	var jqBanSwitch1="0";
	var qsgxSwitch =document.getElementById("qsgxSwitch");
	var qsgxSwitch1="0";
	if(totalSwitch.checked==true){
		if(monNumSwitch.checked==false && jbSwitch.checked==false && banSwitch.checked==false && jqBanSwitch.checked==false && qsgxSwitch.checked==false){
			alert("请选择至少一项需要审批的条件");
			return false;
		}else{
			totalSwitch1="1";
			if(monNumSwitch.checked==true){
				monNumSwitch1="1";
			}
			if(jbSwitch.checked==true){
				jbSwitch1="1";
			}
			if(banSwitch.checked==true){
				banSwitch1="1";
			}
			if(jqBanSwitch.checked==true){
				jqBanSwitch1="1";
			}
			if(qsgxSwitch.checked==true){
				qsgxSwitch1="1";
			}
		}
		 $.ajax({
		      type:"POST",
		      url:"jySet.do",
		      data:"method=saveHjSpParam&monNumSwitch1="+monNumSwitch1+"&jbSwitch1="+jbSwitch1+"&banSwitch1="+banSwitch1+"&jqBanSwitch1="+jqBanSwitch1+"&qsgxSwitch1="+qsgxSwitch1+"&totalSwitch1="+totalSwitch1,
		      dataType:"json",
		      success:callback2
		      
		});
	}else{
		$.ajax({
		      type:"POST",
		      url:"jySet.do",
		      data:"method=saveHjSpParam&monNumSwitch1="+monNumSwitch1+"&jbSwitch1="+jbSwitch1+"&banSwitch1="+banSwitch1+"&jqBanSwitch1="+jqBanSwitch1+"&qsgxSwitch1="+qsgxSwitch1+"&totalSwitch1="+totalSwitch1,
		      dataType:"json",
		      success:callback2
		      
		});
	}
}

function callback2(data){
	if(data[0]=="0"){
		document.forms[0].action="jySet.do?method=search";
		document.forms[0].submit();
	}
}
function cancelHjSpParam(){
	document.forms[0].action="jySet.do?method=search";
	document.forms[0].submit();
}
//清空人脸注册设备一数据
function clearFace(){
	$.ajax({
	      type:"POST",
	      url:"jySet.do",
	      data:"method=clearFace",
	      dataType:"json",
	      success:callback222
	      
	});
	
}
function callback222(data){
	if(data[0]==null){
		alert("连接人脸系统失败，请检查人脸系统是否已启动正常！");
		return false;
	}else if(data[0]==0){
		alert("清空人脸数据成功");
		return false;
	}else{
		alert("连接人脸设备失败，请检查设备网络是否已连通！");
		return false;
	}
}
//清空人脸注册设备二数据
function clearFace1(){
	$.ajax({
	      type:"POST",
	      url:"jySet.do",
	      data:"method=clearFace1",
	      dataType:"json",
	      success:callback333
	      
	});
	
}
function callback333(data){
	if(data[0]==null){
		alert("连接人脸系统失败，请检查人脸系统是否已启动正常！");
		return false;
	}else if(data[0]==0){
		alert("清空人脸数据成功");
		return false;
	}else{
		alert("连接人脸设备失败，请检查设备网络是否已连通！");
		return false;
	}
}
//清空人脸识别设备一数据
function clearFace2(){
	$.ajax({
	      type:"POST",
	      url:"jySet.do",
	      data:"method=clearFace2",
	      dataType:"json",
	      success:callback444
	      
	});
	
}
function callback444(data){
	if(data[0]==null){
		alert("连接人脸系统失败，请检查人脸系统是否已启动正常！");
		return false;
	}else if(data[0]==0){
		alert("清空人脸数据成功");
		return false;
	}else{
		alert("连接人脸设备失败，请检查设备网络是否已连通！");
		return false;
	}
}
//清空人脸识别设备二数据
function clearFace3(){
	$.ajax({
	      type:"POST",
	      url:"jySet.do",
	      data:"method=clearFace3",
	      dataType:"json",
	      success:callback555
	      
	});
	
}
function callback555(data){
	if(data[0]==null){
		alert("连接人脸系统失败，请检查人脸系统是否已启动正常！");
		return false;
	}else if(data[0]==0){
		alert("清空人脸数据成功");
		return false;
	}else{
		alert("连接人脸设备失败，请检查设备网络是否已连通！");
		return false;
	}
}
//清空所有人脸设备上的数据
function clearFace4(){
	$.ajax({
	      type:"POST",
	      url:"jySet.do",
	      data:"method=clearFace4",
	      dataType:"json",
	      success:callback666
	      
	});
	
}
function callback666(data){
	if(data[0]==null){
		alert("连接人脸系统失败，请检查人脸系统是否已启动正常！");
		return false;
	}else if(data[0]==0){
		alert("清空人脸数据成功");
		return false;
	}else{
		alert("连接人脸设备失败，请检查设备网络是否已连通！");
		return false;
	}
}
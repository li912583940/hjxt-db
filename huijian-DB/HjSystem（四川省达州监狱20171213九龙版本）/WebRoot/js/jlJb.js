function addSaveJlJb(){
	var jbNo=document.getElementById("jbNo").value;
	var jbName=document.getElementById("jbName").value;
//	var qqCount=document.getElementById("qqCount").value;
//	var qqTime=document.getElementById("qqTime").value;
	var hjCount=document.getElementById("hjCount").value;
	var hjTime=document.getElementById("hjTime").value;
	var recordOverTime=document.getElementById("recordOverTime").value;
	jbNo=jbNo.replace(/\s+$/g,"");
	if(jbNo==""){
		alert("级别编号不能为空");
		return false;
	}
//	qqCount=qqCount.replace(/\s+$/g,"");
//	if(qqCount==""){
//		alert("每月亲情电话次数不能为空");
//		return false;
//	}
//	qqTime=qqTime.replace(/\s+$/g,"");
//	if(qqTime==""){
//		alert("每次亲情电话时长不能为空");
//		return false;
//	}
	hjCount=hjCount.replace(/\s+$/g,"");
	if(hjCount==""){
		alert("每月会见次数不能为空");
		return false;
	}
	hjTime=hjTime.replace(/\s+$/g,"");
	if(hjTime==""){
		alert("每次会见时长不能为空");
		return false;
	}
	recordOverTime=recordOverTime.replace(/\s+$/g,"");
	if(recordOverTime==""){
		alert("复听录音超时时间不能为空");
		return false;
	}
	var patrn=/^-?\d+$/;
	if (!patrn.test(hjCount) || !patrn.test(hjTime) || !patrn.test(recordOverTime)){ 
		alert("非法字符,请输入整数");
		return false;
	} 
	if(jbNo.indexOf("'")>-1){
		alert("级别编号输入有误，不能包含特殊符号");
		return false;
	}
	if(jbName.indexOf("'")>-1){
		alert("级别名称输入有误，不能包含特殊符号");
		return false;
	}
	jbNo=encodeURI(encodeURI(jbNo));
	jbName=encodeURI(encodeURI(jbName));
	$.ajax({
	      type:"POST",
	      url:basePath+"gradeMessage.do",
	      data:"method=addSavejlJb&jbNo="+jbNo+"&hjCount="+hjCount+"&hjTime="+hjTime+"&jbName="+jbName+"&recordOverTime="+recordOverTime,
	      dataType:"json",
	      success:callback,
	      error:callback1
	});
}
function callback(data){
	if(data[0]==null){
		document.forms[0].action=basePath+"gradeMessage.do?method=search";
		document.forms[0].submit();
	}else{
		if(data[0]==0){
			alert("编号已存在");
			return false;
		}
		if(data[0]==1){
			alert("会见次数需大于-1，复听录音超时时间需大于0");
			return false;
		}
	}
}
function callback1(a,b,c){
	alert(a);
	alert(b);
	alert(c);
}
function deljlJb(){
	 var tr = event.srcElement;
	 while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var jlJbId=tr.cells[5].children[2].value;
	if(window.confirm("确认删除记录？")==true){
		document.forms[0].action="gradeMessage.do?method=delejlJb&jlJbId="+jlJbId;
		document.forms[0].submit();
	}
}
function updatejlJb(){
	 var tr = event.srcElement;
	 while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var jlJbId=tr.cells[5].children[2].value;
	document.forms[0].action="gradeMessage.do?method=checkjlJb&jlJbId="+jlJbId;
	document.forms[0].submit();
}
function updateSaveJlJb(){
	var webId=document.getElementById("webId").value
	var jbName=document.getElementById("jbName").value;
//	var qqCount=document.getElementById("qqCount").value;
//	var qqTime=document.getElementById("qqTime").value;
	var hjCount=document.getElementById("hjCount").value;
	var hjTime=document.getElementById("hjTime").value;
	var recordOverTime=document.getElementById("recordOverTime").value;
//	qqCount=qqCount.replace(/\s+$/g,"");
//	if(qqCount==""){
//		alert("每月亲情电话次数不能为空");
//		return false;
//	}
//	qqTime=qqTime.replace(/\s+$/g,"");
//	if(qqTime==""){
//		alert("每次亲情电话时长不能为空");
//		return false;
//	}
	hjCount=hjCount.replace(/\s+$/g,"");
	if(hjCount==""){
		alert("每月会见次数不能为空");
		return false;
	}
	hjTime=hjTime.replace(/\s+$/g,"");
	if(hjTime==""){
		alert("每次会见时长不能为空");
		return false;
	}
	recordOverTime=recordOverTime.replace(/\s+$/g,"");
	if(recordOverTime==""){
		alert("复听录音超时时间不能为空");
		return false;
	}
	var patrn=/^-?\d+$/;
	if (!patrn.test(hjCount) || !patrn.test(hjTime) || !patrn.test(recordOverTime)){ 
		alert("非法字符");
		return false
	}
	if(jbName.indexOf("'")>-1){
		alert("级别编号输入有误，不能包含特殊符号");
		return false;
	}
	jbName=encodeURI(encodeURI(jbName));
	 $.ajax({
	      type:"POST",
	      url:"gradeMessage.do",
	      data:"method=updateSavejlJb&webId="+webId+"&hjCount="+hjCount+"&hjTime="+hjTime+"&jbName="+jbName+"&recordOverTime="+recordOverTime,
	      dataType:"json",
	      success:callback2,
	      error:callback1
	});
}
function callback2(data){
	if(data[0]==null){
		alert("会见次数需大于-1，复听录音超时时间需大于0");
		return false;
	}else{
		document.forms[0].action="gradeMessage.do?method=search";
		document.forms[0].submit();
	}
}
function fwQqCount(){
	if(window.confirm("是否手工复位")==true){
	$.ajax({
		 type:"POST",
	     url:"gradeMessage.do",
	     data:"method=fwQqCount",
	     dataType:"json",
	     success:callback3,
	     error:callback1
	});
	}
}
function callback3(data){
	if(data[0]==0){
		if(window.confirm("是否强制复位")==true){
			document.forms[0].action="gradeMessage.do?method=qzfwQqCount";
			document.forms[0].submit();
		}
	}else{
		window.location.href("../jsp/jlJb/fwSuccess.jsp");
	}
}
function pladdSaveQqcount(){
	if(window.confirm("是否批量增加亲情次数")==true){
	var selectJb=document.getElementById("selectJb");
	var jljB;
	for(var i=0;i<selectJb.length;i++){
		if(selectJb.options[i].selected){
			jljB=selectJb.options[i].value;
			break;
		}
	}
	var addQqcount=document.getElementById("addQqcount").value
	var patrn1=/^\d+$/;
	if (!patrn1.test(addQqcount)){
		alert("增加亲情电话次数包含非法字符，请输入正整数");
		return false;
	}
	document.forms[0].action="gradeMessage.do?method=plAddSaveQqCount&jljB="+jljB+"&addQqcount="+addQqcount;
	document.forms[0].submit();
	}
}
function changAutoDown(){
	var autoDown=document.getElementById("autoDown");
	for(var i=0;i<autoDown.length;i++){
		if(autoDown.options[i].selected){
			if(autoDown.options[i].value=="0"){
				document.getElementById("downTime").value="1";
				var downJB=document.getElementById("downJB");
				for(var i=0;i<downJB.length;i++){
					if(downJB.options[i].value=="null"){
						downJB.options[i].selected=true;
						break;
					}
				}
				document.getElementById("downTime").disabled=true;
				document.getElementById("downJB").disabled=true;
			}else{
				document.getElementById("downTime").disabled=false;
				document.getElementById("downJB").disabled=false;
			}
			break;
		}
		
	}
}
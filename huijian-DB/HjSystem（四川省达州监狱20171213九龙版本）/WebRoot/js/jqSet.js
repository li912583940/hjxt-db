function deleteJq(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var jqId=tr.cells[4].children[2].value;
	 if(window.confirm("确认删除记录？")==true){
		document.forms[0].action="jqSet.do?method=deleteJq&jqId="+jqId;
		document.forms[0].submit();
	 }
}
function addSaveJq(){
	var jqNo=document.getElementById("jqNo").value;
	var jqName=document.getElementById("jqName").value;
	var jySelect=document.getElementById("jyId");
	var isTs=document.getElementsByName("isTs");
	var jyId;
	var isTsValue;
	jqNo=jqNo.replace(/\s+$/g,"");
	if(jqNo==""){
		alert("监区编号不能为空");
		return false;
	}
	for(var i=0;i<jySelect.length;i++){
		if(jySelect.options[i].selected){
			jyId=jySelect.options[i].value;
			break;
		}
	}
	if(jyId=='null'){
		alert("请选择服务器名称");
		return false;
	}
	for(var i=0;i<isTs.length;i++){
		if(isTs[i].checked){
			isTsValue=isTs[i].value;
			break;
		}
	}
	if(jqNo.indexOf("'")>-1){
		alert("监区编号输入有误，不能包含特殊符号");
		return false;
	}
	if(jqName.indexOf("'")>-1){
		alert("监区名称输入有误，不能包含特殊符号");
		return false;
	}
	jqNo=encodeURI(encodeURI(jqNo));
	jqName=encodeURI(encodeURI(jqName));
	jyId=encodeURI(encodeURI(jyId));
	 $.ajax({
	      type:"POST",
	      url:"jqSet.do",
	      data:"method=addSaveJq&jqNo="+jqNo+"&jyId="+jyId+"&jqName="+jqName+"&isTs="+isTsValue,
	      dataType:"json",
	      success:callback,
	      error:callback1
	});
}
function callback(data){
	if(data[0]==null){
		alert("监区编号已存在请重新输入");
		return false;
	}else{
		document.forms[0].action="jqSet.do?method=search";
		document.forms[0].submit();
	}
}
function callback1(a,b,c){
	alert(a);
	alert(b);
	alert(c);
}
function updateJq(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var jqId=tr.cells[4].children[2].value;
	document.forms[0].action="jqSet.do?method=updateJq&jqId="+jqId;
	document.forms[0].submit();
}
function saveUpdateJq(){
	var jyMc=document.getElementById("jyMc");
	var jqName=document.getElementById("jqName").value;
	var jyMc1;
	for(var i=0;i<jyMc.length;i++){
		if(jyMc.options[i].selected){
			jyMc1=jyMc.options[i].value;
			break;
		}
	}
	var isTsValue;
	var isTs=document.getElementsByName("isTs");
	for(var i=0;i<isTs.length;i++){
		if(isTs[i].checked){
			isTsValue=isTs[i].value;
			break;
		}
	}
	if(jqName.indexOf("'")>-1){
		alert("监区名称输入有误，不能包含特殊符号");
		return false;
	}
	var jqId=document.getElementById("jqId").value;
	jyMc1=encodeURI(encodeURI(jyMc1));
	jqName=encodeURI(encodeURI(jqName));
	 $.ajax({
	      type:"POST",
	      url:"jqSet.do",
	      data:"method=updateSaveJq&jqId="+jqId+"&jqName="+jqName+"&jyMc="+jyMc1+"&isTs="+isTsValue,
	      dataType:"json",
	      success:callback2,
	      error:callback1
	});
}
function callback2(data){
	if(data[0]==null){
		document.forms[0].action="jqSet.do?method=search";
		document.forms[0].submit();
	}
}
function qinWeek(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var jqId=tr.cells[4].children[2].value;
	document.forms[0].action="jqSet.do?method=checkJqWeek&jqId="+jqId;
	document.forms[0].submit();
}
function callback3(data){
	if(data[0]==null){
		document.forms[0].action="jqSet.do?method=search";
		document.forms[0].submit();
	}
}
function addSaveJqWeek(){
	var jqNo=document.getElementById("jqNo").value;
	var jy=document.getElementById("jy").value;
	var webId=document.getElementById("webId").value;
	var week=document.getElementById("week");
	var selectedWeek;
	for(var i=0;i<week.length;i++){
		if(week.options[i].selected){
			selectedWeek=week.options[i].value;
			break;
		}
	}
	var reg=/^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
	var reg1=/^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
	jqNo=encodeURI(encodeURI(jqNo));
	jy=encodeURI(encodeURI(jy));
	 $.ajax({
	      type:"POST",
	      url:"jqSet.do",
	      data:"method=addSaveJqWeek&jqNo="+jqNo+"&jy="+jy+"&selectedWeek="+selectedWeek+"&webId="+webId,
	      dataType:"json",
	      success:callback4,
	      error:callback1
	});
}
function callback4(data){
	if(data[0]==null){
		alert("该监区的会见星期已存在");
		return false;
	}else{
		document.forms[0].action="jqSet.do?method=checkJqWeek&jqId="+data[0];
		document.forms[0].submit();
	}
}
function updateJqWeek(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var timeIndex=tr.cells[3].children[1].value;
	document.forms[0].action="jqSet.do?method=updateJqWeek&timeIndex="+timeIndex;
	document.forms[0].submit();
}
function updateSaveJqWeek(){
	var timeIndex=document.getElementById("timeIndex").value;
	var webId=document.getElementById("webId").value;
	var week=document.getElementById("week");
	var selectedWeek;
	for(var i=0;i<week.length;i++){
		if(week.options[i].selected){
			selectedWeek=week.options[i].value;
			break;
		}
	}
	 $.ajax({
	      type:"POST",
	      url:"jqSet.do",
	      data:"method=updateSaveJqWeek&timeIndex="+timeIndex+"&selectedWeek="+selectedWeek+"&webId="+webId,
	      dataType:"json",
	      success:callback5,
	      error:callback1
	});
}
function callback5(data){
	if(data[0]==null){
		alert("该监区的会见星期已存在");
		return false;
	}else{
		document.forms[0].action="jqSet.do?method=checkJqWeek&jqId="+data[0];
		document.forms[0].submit();
	}
}
function deleteJqWeek(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var timeIndex=tr.cells[3].children[1].value;
	var jqNo=tr.cells[0].innerText;
	document.forms[0].action="jqSet.do?method=delJqWeek&timeIndex="+timeIndex+"&jqNo="+jqNo;
	document.forms[0].submit();
}

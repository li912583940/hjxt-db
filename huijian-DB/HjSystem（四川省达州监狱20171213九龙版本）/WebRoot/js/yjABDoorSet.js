function updateYjABDoor(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var webId=tr.cells[2].children[2].value;
	document.forms[0].action="yjABDoorMessage.do?method=updateYjABDoor&webId="+webId;
	document.forms[0].submit();
}
function saveYjABDoor(){
	var yjName=document.getElementById("yjName").value;
	var webId=document.getElementById("webId").value;
	yjName=yjName.replace(/\s+$/g,"");

	if(yjName==""){
		alert("姓名不能为空");
		return false;
	}

	if(yjName.indexOf("'")>-1){
		alert("姓名，不能包含特殊符号");
		return false;
	}
	yjName=encodeURI(encodeURI(yjName));
	 $.ajax({
	      type:"POST",
	      url:"yjABDoorMessage.do",
	      data:"method=saveYjABDoor&webId="+webId+"&yjName="+yjName,
	      dataType:"json",
	      success:callback,
	      error:callback1
	});
}
function callback(data){
	document.forms[0].action="yjABDoorMessage.do?method=search";
	document.forms[0].submit();
}
function callback1(a,b,c){
	alert(a);
	alert(b);
	alert(c);
}
function addSaveYjABDoor(){
	var yjNo=document.getElementById("yjNo").value;
	var yjName=document.getElementById("yjName").value;

	yjNo=yjNo.replace(/\s+$/g,"");
	yjName=yjName.replace(/\s+$/g,"");
	if(yjNo==""){
		alert("警号不能为空");
		return false;
	}
	if(yjName==""){
		alert("姓名不能为空");
		return false;
	}
	
	if(yjNo.indexOf("'")>-1){
		alert("警号输入有误，不能包含特殊符号");
		return false;
	}
	if(yjName.indexOf("'")>-1){
		alert("姓名输入有误，不能包含特殊符号");
		return false;
	}
	yjNo=encodeURI(encodeURI(yjNo));
	yjName=encodeURI(encodeURI(yjName));
	 $.ajax({
	      type:"POST",
	      url:"yjABDoorMessage.do",
	      data:"method=addSaveYjABDoor&yjNo="+yjNo+"&yjName="+yjName,
	      dataType:"json",
	      success:callback111,
	      error:callback1
	});
}
function callback111(data){
	if(data[0]==null){
		alert("警号已存在，请重新输入");
		return false;
	}else{
		document.forms[0].action="yjABDoorMessage.do?method=search";
		document.forms[0].submit();
	}
}
function deleteYjABDoor(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var webId=tr.cells[2].children[2].value;
	 if(window.confirm("确认删除记录？")==true){
		document.forms[0].action="yjABDoorMessage.do?method=deleteYjABDoor&webId="+webId;
		document.forms[0].submit();
	 }
}




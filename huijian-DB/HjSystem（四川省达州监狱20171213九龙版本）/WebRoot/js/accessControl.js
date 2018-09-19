function dcSearch(){
	var isDc=document.getElementById("isDc").value;
	if(isDc==0){
		alert("请查询后再点击导出按钮");
		return false;
	}else{
		document.forms[0].action="accessControlMessage.do?method=dcSearch";
		document.forms[0].submit();
	}
}
function chaxun(){
	document.forms[0].action="accessControlMessage.do?method=search";
	document.forms[0].submit();
}
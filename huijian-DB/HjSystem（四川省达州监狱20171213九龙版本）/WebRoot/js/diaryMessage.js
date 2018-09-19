function enterSubmit(src,e){
    var keyPressed;
    if(window.event){
   	 keyPressed = window.event.keyCode; // IE
    }else{
       keyPressed = e.which; // Firefox
    }
    if(keyPressed==13){ 
    	rizhiSearch();                     
        return false;
    }
}
function rizhiSearch(){
	var op=document.getElementById("op").value;
	var info=document.getElementById("info").value;
	var userNo=document.getElementById("userNo").value;
	var userName=document.getElementById("userName").value;
	var userIp=document.getElementById("userIp").value;
	if(op.indexOf("'")>-1){
		alert("操作输入有误，不能包含特殊符号");
		return false;
	}
	if(info.indexOf("'")>-1){
		alert("内容输入有误，不能包含特殊符号");
		return false;
	}
	if(userNo.indexOf("'")>-1){
		alert("用户编号输入有误，不能包含特殊符号");
		return false;
	}
	if(userName.indexOf("'")>-1){
		alert("用户姓名输入有误，不能包含特殊符号");
		return false;
	}
	if(userIp.indexOf("'")>-1){
		alert("来源IP输入有误，不能包含特殊符号");
		return false;
	}
	document.forms[0].submit();
}
function logXx(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var logIds=document.getElementsByName("logId");
	var logId=logIds[tr.rowIndex-3].value;
	
	var type=document.getElementById("type");
	var model=document.getElementById("model");
	var op3=document.getElementById("op").value;
	var info3=document.getElementById("info").value;
	var userNo3=document.getElementById("userNo").value;
	var userName3=document.getElementById("userName").value;
	var userIp3=document.getElementById("userIp").value;
	var callTimeStart3=document.getElementById("callTimeStart").value;
	var callTimeEnd3=document.getElementById("callTimeEnd").value;
	var pageNo3=document.getElementById("pageNo").value;
	var type2='';
	for(var i=0;i<type.length;i++){
		if(type.options[i].selected){
			type2=type.options[i].value;
			break;
		}
	}
	var model2='';
	for(var i=0;i<model.length;i++){
		if(model.options[i].selected){
			model2=model.options[i].value;
			break;
		}
	}
	op3=encodeURI(encodeURI(op3));
	info3=encodeURI(encodeURI(info3));
	userNo3=encodeURI(encodeURI(userNo3));
	userName3=encodeURI(encodeURI(userName3));
	userIp3=encodeURI(encodeURI(userIp3));
	document.forms[0].action="diaryMessage.do?method=diaryXiangxi&logId="+logId+"&op3="+op3+"&info3="+info3+"&userNo3="+userNo3+"&userName3="+userName3+"&userIp3="+userIp3+"&callTimeEnd3="+callTimeEnd3+"&callTimeStart3="+callTimeStart3+"&pageNo3="+pageNo3+"&type2="+type2+"&model2="+model2;
	document.forms[0].submit();
}
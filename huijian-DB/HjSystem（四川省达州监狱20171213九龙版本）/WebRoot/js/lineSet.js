function updateLine(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var lineId=tr.cells[6].children[1].value;
	document.forms[0].action="lineSet.do?method=checkLine&lineId="+lineId;
	document.forms[0].submit();
}

function callback(data){
	if(data[0]==null){
		document.forms[0].submit();
	}
}
function callback1(a,b,c){
	alert(a);
	alert(b);
	alert(c);
}
function updateSaveLine(){
	var dkq=document.getElementById("dkq").value;
	var lineId=document.getElementById("lineId").value;
	var jyMc=document.getElementById("jyMc");
	var zw = document.getElementById("zw").value;
	var state = document.getElementById("state");
	var jy;
	for(var i=0;i<jyMc.length;i++){
		if(jyMc.options[i].selected){
			jy=jyMc.options[i].value;
			break;
		}
	}
	var state1;
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			state1=state.options[i].value;
			break;
		}
	}
	if(zw.indexOf("'")>-1){
		alert("座位名称输入有误，不能包含特殊符号");
		return false;
	}
	if(dkq.indexOf("'")>-1){
		alert("读卡器输入有误，不能包含特殊符号");
		return false;
	}
	jy=encodeURI(encodeURI(jy));
	dkq=encodeURI(encodeURI(dkq));
	zw=encodeURI(encodeURI(zw));
	 $.ajax({
	      type:"POST",
	      url:"lineSet.do",
	      data:"method=updateSaveLine&lineId="+lineId+"&jy="+jy+"&zw="+zw+"&dkq="+dkq+"&state="+state1,
	      dataType:"json",
	      success:callback,
	      error:callback1
	});
	
}
function updateCardNo(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var lineNo=tr.cells[6].children[2].value;
	var jy=tr.cells[6].children[3].value;
	document.forms[0].action="lineSet.do?method=lineCard&lineNo="+lineNo+"&jy="+jy;
	document.forms[0].submit();
	
}
function updateSaveCardNo(){
		var lineNo = document.getElementById("lineNo").value;
		var jy= document.getElementById("jy").value;
		var cardNo = document.getElementById("cardNo").value;
		var op = document.getElementById("op").value;
		var patrn=/^-?\d+$/;
		if (!patrn.test(cardNo)){ 
			alert("非法字符,请输入整数");
			return false;
		}
		$.ajax({
	      type:"POST",
	      url:"lineSet.do",
	      data:"method=updateSaveCardNo&lineNo="+lineNo+"&jy="+jy+"&cardNo="+cardNo+"&op="+op,
	      dataType:"json",
	      success:callback,
	      error:callback1
	    });
}
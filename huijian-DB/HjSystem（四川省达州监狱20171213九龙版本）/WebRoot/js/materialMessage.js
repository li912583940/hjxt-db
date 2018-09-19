function enterSubmit(src,e){
         if(window.event)
             keyPressed = window.event.keyCode; // IE
         else
            keyPressed = e.which; // Firefox
         if(keyPressed==13){ 
        	 checkFr();                     
             return false;
         }
}
function enterSubmit1(src,e){
    if(window.event)
        keyPressed = window.event.keyCode; // IE
    else
       keyPressed = e.which; // Firefox
    if(keyPressed==13){ 
   	 checkFr1();                     
        return false;
    }
}
function checkFr(){
	var frName=document.getElementById("frName").value;
	var frNo=document.getElementById("frNo").value;
	if(frNo.indexOf("'")>-1){
		alert("罪犯编号输入有误，不能包含特殊符号");
		return false;
	}
	if(frName.indexOf("'")>-1){
		alert("罪犯姓名输入有误，不能包含特殊符号");
		return false;
	}
	document.forms[0].action="materialMessage.do?method=search";
	document.forms[0].submit();
}
function checkFr1(){
	var frName=document.getElementById("frName").value;
	var frNo=document.getElementById("frNo").value;
	if(frNo.indexOf("'")>-1){
		alert("罪犯编号输入有误，不能包含特殊符号");
		return false;
	}
	if(frName.indexOf("'")>-1){
		alert("罪犯姓名输入有误，不能包含特殊符号");
		return false;
	}
	document.forms[0].action="materialMessage.do?method=search2";
	document.forms[0].submit();
}
function addFr(){
	var jy=document.getElementById("jy");
	var jq=document.getElementById("jq");
	var jbNo=document.getElementById("jbNo");
	var frName2=document.getElementById("frName").value;
	var frCard2=document.getElementById("frCard").value;
	var frNo3=document.getElementById("frNo").value;
	var isDc=document.getElementById("isDc").value;
	var stateZdzf=document.getElementById("stateZdzf");
	var state=document.getElementById("state");
	var jy2='';
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy2=jy.options[i].value;
			break;
		}
	}
	var jq2='';
	for(var i=0;i<jq.length;i++){
		if(jq.options[i].selected){
			jq2=jq.options[i].value;
			break;
		}
	}
	var jbNo2='';
	for(var i=0;i<jbNo.length;i++){
		if(jbNo.options[i].selected){
			jbNo2=jbNo.options[i].value;
			break;
		}
	}
	var stateZdzf2='';
	for(var i=0;i<stateZdzf.length;i++){
		if(stateZdzf.options[i].selected){
			stateZdzf2=stateZdzf.options[i].value;
			break;
		}
	}
	var state2='';
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			state2=state.options[i].value;
			break;
		}
	}
	var pageNo2=-1;
	if(isDc==1){
		pageNo2=document.getElementById("pageNo").value;
	}
	frNo3=encodeURI(encodeURI(frNo3));
	frName2=encodeURI(encodeURI(frName2));
	frCard2=encodeURI(encodeURI(frCard2));
	jy2=encodeURI(encodeURI(jy2));
	jq2=encodeURI(encodeURI(jq2));
	jbNo2=encodeURI(encodeURI(jbNo2));
	stateZdzf2=encodeURI(encodeURI(stateZdzf2));
	state2=encodeURI(encodeURI(state2));
	document.forms[0].action="materialMessage.do?method=addFr&frNo3="+frNo3+"&frName2="+frName2+"&jy2="+jy2+"&jq2="+jq2+"&jbNo2="+jbNo2+"&pageNo2="+pageNo2+"&stateZdzf2="+stateZdzf2+"&state2="+state2+"&frCard2="+frCard2;
	document.forms[0].submit();
}
function addSaveFr(){
	var frNo=document.getElementById("frNo1").value;
	var frName=document.getElementById("frName1").value;
	var frCard=document.getElementById("frCard").value;
	var zdzfType=document.getElementById("zdzfType").value;
	var infoRjsj=document.getElementById("infoRjsj").value;
	var infoZm=document.getElementById("infoZm").value;
	var infoXq=document.getElementById("infoXq").value;
	var infoCsrq=document.getElementById("infoCsrq").value;
	var infoHome=document.getElementById("infoHome").value;
	var frGj=document.getElementById("frGj").value;
	frGj=frGj.replace(/\s+$/g,"");
	infoRjsj=infoRjsj.replace(/\s+$/g,"");
	infoZm=infoZm.replace(/\s+$/g,"");
	infoXq=infoXq.replace(/\s+$/g,"");
	infoCsrq=infoCsrq.replace(/\s+$/g,"");
	infoHome=infoHome.replace(/\s+$/g,"");
	frNo=frNo.replace(/\s+$/g,"");
	zdzfType=zdzfType.replace(/\s+$/g,"");
	frCard=frCard.replace(/\s+$/g,"");
	frName=frName.replace(/\s+$/g,"");
	if(frNo==''){
		alert("罪犯编号不能为空");
		return false;
	}
	var jy=document.getElementById("jy1");
	var jq=document.getElementById("jq1");
	var jb=document.getElementById("jb1");
	var hjjb=document.getElementById("hjjb");
	var monitorFlag=document.getElementById("monitorFlag");
	var stateZdzf=document.getElementById("stateZdzf");
	var jy1;
	var jq1;
	var jb1;
	var hjjb1;
	var monitorFlag1;
	var stateZdzf1;
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy1=jy.options[i].value;
			break;
		}
	}
	if(jy1=='null'){
		alert("请选择服务器名称");
		return false;
	}
	for(var i=0;i<jq.length;i++){
		if(jq.options[i].selected){
			jq1=jq.options[i].value;
			break;
		}
	}
	if(jq1=='null'){
		alert("请选择监区名称");
		return false;
	}
	for(var i=0;i<jb.length;i++){
		if(jb.options[i].selected){
			jb1=jb.options[i].value;
			break;
		}
	}
	if(jb1=='null'){
		alert("请选择级别");
		return false;
	}
	for(var i=0;i<hjjb.length;i++){
		if(hjjb.options[i].selected){
			hjjb1=hjjb.options[i].value;
			break;
		}
	}
	for(var i=0;i<monitorFlag.length;i++){
		if(monitorFlag.options[i].selected){
			monitorFlag1=monitorFlag.options[i].value;
			break;
		}
	}
	for(var i=0;i<stateZdzf.length;i++){
		if(stateZdzf.options[i].selected){
			stateZdzf1=stateZdzf.options[i].value;
			break;
		}
	}
	if(frNo.indexOf("'")>-1){
		alert("罪犯编号输入有误，不能包含特殊符号");
		return false;
	}
	if(frName.indexOf("'")>-1){
		alert("罪犯姓名输入有误，不能包含特殊符号");
		return false;
	}
	if(frCard.indexOf("'")>-1){
		alert("罪犯IC卡输入有误，不能包含特殊符号");
		return false;
	}
	if(frGj.indexOf("'")>-1){
		alert("国籍输入有误，不能包含特殊符号");
		return false;
	}	
	if(infoRjsj.indexOf("'")>-1){
		alert("入监时间输入有误，不能包含特殊符号");
		return false;
	}
	if(infoZm.indexOf("'")>-1){
		alert("罪名输入有误，不能包含特殊符号");
		return false;
	}
	if(infoXq.indexOf("'")>-1){
		alert("刑期输入有误，不能包含特殊符号");
		return false;
	}
	if(infoCsrq.indexOf("'")>-1){
		alert("出生日期输入有误，不能包含特殊符号");
		return false;
	}
	if(infoHome.indexOf("'")>-1){
		alert("住址输入有误，不能包含特殊符号");
		return false;
	}
	if(zdzfType.indexOf("'")>-1){
		alert("重点罪犯类型输入有误，不能包含特殊符号");
		return false;
	}
	frNo=encodeURI(encodeURI(frNo));
	frName=encodeURI(encodeURI(frName));
	frCard=encodeURI(encodeURI(frCard));
	jy1=encodeURI(encodeURI(jy1));
	jq1=encodeURI(encodeURI(jq1));
	jb1=encodeURI(encodeURI(jb1));
	zdzfType=encodeURI(encodeURI(zdzfType));
	frGj=encodeURI(encodeURI(frGj));
	$.ajax({
	      type:"POST",
	      url:"materialMessage.do",
	      data:"method=addSaveFr&frNo="+frNo+"&jy="+jy1+"&jq="+jq1+"&jb="+jb1+"&hjjb="+hjjb1+"&frName="+frName+"&frCard="+frCard+"&monitorFlag="+monitorFlag1+"&stateZdzf="+stateZdzf1+"&zdzfType="+zdzfType+"&infoRjsj="+infoRjsj+"&infoZm="+infoZm+"&infoXq="+infoXq+"&infoCsrq="+infoCsrq+"&infoHome="+infoHome+"&frGj="+frGj,
	      dataType:"json",
	      success:callback2,
	      error:callback1
	});
	
}
function callback2(data){
	 if(data[0]=="-1"){
		 alert("罪犯编号以存在，请重新输入");
		 return false;
	 }else if(data[0]=="1"){
		 //document.forms[0].action="materialMessage.do?method=search";
		 document.forms[0].action=document.getElementById("returnBack").href;
		 document.forms[0].submit();
	 }else{
		 var str="该罪犯的IC卡号与其他罪犯编号";
		 for(var i=0;i<data.length;i++){
			 if(i==0){
				 str+=data[i].frNo;
			 }else{
				 str+="、"+data[i].frNo;
			 }
		 }
		 str+="的IC卡号重复，请重新输入";
		 alert(str);
		 return false;
	 }
}
function callback1(a,b,c){
 	alert(a);
 	alert(b);
 	alert(c);
 }
function delFr(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frId=tr.cells[9].children[2].value;

	var jy=document.getElementById("jy");
	var jq=document.getElementById("jq");
	var jbNo=document.getElementById("jbNo");
	var frName2=document.getElementById("frName").value;
	var frCard2=document.getElementById("frCard").value;
	var stateZdzf=document.getElementById("stateZdzf");
	var state=document.getElementById("state");
	var frNo3=document.getElementById("frNo").value;
	var isDc=document.getElementById("isDc").value;
	var jy2='';
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy2=jy.options[i].value;
			break;
		}
	}
	var jq2='';
	for(var i=0;i<jq.length;i++){
		if(jq.options[i].selected){
			jq2=jq.options[i].value;
			break;
		}
	}
	var jbNo2='';
	for(var i=0;i<jbNo.length;i++){
		if(jbNo.options[i].selected){
			jbNo2=jbNo.options[i].value;
			break;
		}
	}
	var stateZdzf2='';
	for(var i=0;i<stateZdzf.length;i++){
		if(stateZdzf.options[i].selected){
			stateZdzf2=stateZdzf.options[i].value;
			break;
		}
	}
	var state2='';
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			state2=state.options[i].value;
			break;
		}
	}
	var pageNo2=-1;
	if(isDc==1){
		pageNo2=document.getElementById("pageNo").value;
	}
	frNo3=encodeURI(encodeURI(frNo3));
	frName2=encodeURI(encodeURI(frName2));
	frCard2=encodeURI(encodeURI(frCard2));
	jy2=encodeURI(encodeURI(jy2));
	jq2=encodeURI(encodeURI(jq2));
	jbNo2=encodeURI(encodeURI(jbNo2));
	stateZdzf2=encodeURI(encodeURI(stateZdzf2));
	state2=encodeURI(encodeURI(state2));
	var frId=tr.cells[9].children[2].value;
	if(window.confirm("确认删除记录？")==true){
		 document.forms[0].action="materialMessage.do?method=delFr&frId="+frId+"&frNo3="+frNo3+"&frName2="+frName2+"&jy2="+jy2+"&jq2="+jq2+"&jbNo2="+jbNo2+"&pageNo2="+pageNo2+"&stateZdzf2="+stateZdzf2+"&state2="+state2+"&frCard2="+frCard2;
		 document.forms[0].submit();
	}
}
function updateFr(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var jy=document.getElementById("jy");
	var jq=document.getElementById("jq");
	var jbNo=document.getElementById("jbNo");
	var stateZdzf=document.getElementById("stateZdzf");
	var state=document.getElementById("state");
	var frName2=document.getElementById("frName").value;
	var frCard2=document.getElementById("frCard").value;
	var frNo3=document.getElementById("frNo").value;
	var jy2='';
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy2=jy.options[i].value;
			break;
		}
	}
	var jq2='';
	for(var i=0;i<jq.length;i++){
		if(jq.options[i].selected){
			jq2=jq.options[i].value;
			break;
		}
	}
	var jbNo2='';
	for(var i=0;i<jbNo.length;i++){
		if(jbNo.options[i].selected){
			jbNo2=jbNo.options[i].value;
			break;
		}
	}
	var stateZdzf2='';
	for(var i=0;i<stateZdzf.length;i++){
		if(stateZdzf.options[i].selected){
			stateZdzf2=stateZdzf.options[i].value;
			break;
		}
	}
	var state2='';
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			state2=state.options[i].value;
			break;
		}
	}
	var pageNo2=pageNo2=document.getElementById("pageNo").value;
	frNo3=encodeURI(encodeURI(frNo3));
	frName2=encodeURI(encodeURI(frName2));
	frCard2=encodeURI(encodeURI(frCard2));
	jy2=encodeURI(encodeURI(jy2));
	jq2=encodeURI(encodeURI(jq2));
	jbNo2=encodeURI(encodeURI(jbNo2));
	stateZdzf2=encodeURI(encodeURI(stateZdzf2));
	state2=encodeURI(encodeURI(state2));
	
	var frId=tr.cells[9].children[2].value;
	document.forms[0].action="materialMessage.do?method=updateFr&frId="+frId+"&frNo3="+frNo3+"&frName2="+frName2+"&jy2="+jy2+"&jq2="+jq2+"&jbNo2="+jbNo2+"&pageNo2="+pageNo2+"&stateZdzf2="+stateZdzf2+"&state2="+state2+"&frCard2="+frCard2;
	document.forms[0].submit();

}
function updateSaveFr(){
	var frId=document.getElementById("frId").value;
	var frName=document.getElementById("frName1").value;
	var frCard=document.getElementById("frCard1").value;
	var hjleft=document.getElementById("hjleft").value;
	var jy=document.getElementById("jy1");
	var jq=document.getElementById("jq1");
	var jb=document.getElementById("jb1");
	var hjjb=document.getElementById("hjJb");
	var infoRjsj=document.getElementById("infoRjsj").value;
	var infoZm=document.getElementById("infoZm").value;
	var infoXq=document.getElementById("infoXq").value;
	var infoCsrq=document.getElementById("infoCsrq").value;
	var infoHome=document.getElementById("infoHome").value;
	var frGj=document.getElementById("frGj").value;
	var monitorFlag=document.getElementById("monitorFlag");
	var stateZdzf=document.getElementById("stateZdzf");
	var zdzfType=document.getElementById("zdzfType").value;
	var state=document.getElementById("state");
	var hjStopTime=document.getElementById("hjStopTime").value;
	var hjStopSM=document.getElementById("hjStopSM").value;
	var state1="";
	if(frCard.length>10){
		alert("卡号长度有误");
		return false;
	}
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			state1=state.options[i].value
			break;
		}
	}
	var outTime=document.getElementById("outTime").value;
	outTime=outTime.replace(/\s+$/g,"");
	if(state1==1 && outTime==""){
		alert("请输入出狱时间");
		return false;
	}
	infoRjsj=infoRjsj.replace(/\s+$/g,"");
	infoZm=infoZm.replace(/\s+$/g,"");
	infoXq=infoXq.replace(/\s+$/g,"");
	infoCsrq=infoCsrq.replace(/\s+$/g,"");
	infoHome=infoHome.replace(/\s+$/g,"");
	zdzfType=zdzfType.replace(/\s+$/g,"");
	frCard=frCard.replace(/\s+$/g,"");
	frName=frName.replace(/\s+$/g,"");
	frGj=frGj.replace(/\s+$/g,"");
	hjStopSM=hjStopSM.replace(/\s+$/g,"");
	var jy1;
	var jq1;
	var jb1;
	var hjjb1;
	var monitorFlag1;
	var stateZdzf1;
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy1=jy.options[i].value;
			break;
		}
	}
	for(var i=0;i<jq.length;i++){
		if(jq.options[i].selected){
			jq1=jq.options[i].value;
			break;
		}
	}
	if(jq1=="null"){
		alert("请选择所属监区");
		return false;
	}
	var patrn1=/^-?\d+$/;
	if (!patrn1.test(hjleft) || parseInt(hjleft)<-1){
		alert("会见剩余次数包含非法字符，必须大于等于-1");
		return false;
	}
	for(var i=0;i<jb.length;i++){
		if(jb.options[i].selected){
			jb1=jb.options[i].value;
			break;
		}
	}
	for(var i=0;i<hjjb.length;i++){
		if(hjjb.options[i].selected){
			hjjb1=hjjb.options[i].value;
			break;
		}
	}
	for(var i=0;i<monitorFlag.length;i++){
		if(monitorFlag.options[i].selected){
			monitorFlag1=monitorFlag.options[i].value;
			break;
		}
	}
	for(var i=0;i<stateZdzf.length;i++){
		if(stateZdzf.options[i].selected){
			stateZdzf1=stateZdzf.options[i].value;
			break;
		}
	}
	if(frName.indexOf("'")>-1){
		alert("罪犯姓名输入有误，不能包含特殊符号");
		return false;
	}
	if(frCard.indexOf("'")>-1){
		alert("罪犯IC卡输入有误，不能包含特殊符号");
		return false;
	}
	if(hjStopSM.indexOf("'")>-1){
		alert("禁止会见说明输入有误，不能包含特殊符号");
		return false;
	}
	if(infoRjsj.indexOf("'")>-1){
		alert("入监时间输入有误，不能包含特殊符号");
		return false;
	}
	if(infoZm.indexOf("'")>-1){
		alert("罪名输入有误，不能包含特殊符号");
		return false;
	}
	if(infoXq.indexOf("'")>-1){
		alert("刑期输入有误，不能包含特殊符号");
		return false;
	}
	if(infoCsrq.indexOf("'")>-1){
		alert("出生日期输入有误，不能包含特殊符号");
		return false;
	}
	if(infoHome.indexOf("'")>-1){
		alert("住址输入有误，不能包含特殊符号");
		return false;
	}
	if(hjjb1==-1){
		if(hjStopTime==""){
			alert("请选择会见禁止日期");
			return false;
		}
	}
	frName=encodeURI(encodeURI(frName));
	frCard=encodeURI(encodeURI(frCard));
	jy1=encodeURI(encodeURI(jy1));
	jq1=encodeURI(encodeURI(jq1));
	jb1=encodeURI(encodeURI(jb1));
	zdzfType=encodeURI(encodeURI(zdzfType));
	 $.ajax({
	      type:"POST",
	      url:"materialMessage.do",
	      data:"method=updateSaveFr&frId="+frId+"&jy="+jy1+"&jq="+jq1+"&jb="+jb1+"&hjjb="+hjjb1+"&frName="+frName+"&frCard="+frCard+"&hjleft="+hjleft+"&monitorFlag="+monitorFlag1+"&stateZdzf="+stateZdzf1+"&zdzfType="+zdzfType+"&infoRjsj="+infoRjsj+"&infoZm="+infoZm+"&infoXq="+infoXq+"&infoCsrq="+infoCsrq+"&infoHome="+infoHome+"&state="+state1+"&outTime="+outTime+"&frGj="+frGj+"&hjStopTime="+hjStopTime+"&hjStopSM="+hjStopSM,
	      dataType:"json",
	      success:callback,
	      error:callback1
	});
}
function callback(data){
	if(data[0]==null){
		//document.forms[0].action="materialMessage.do?method=search";
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}else{
		 var str="该罪犯的IC卡号与其他罪犯编号";
		 for(var i=0;i<data.length;i++){
			 if(i==0){
				 str+=data[i].frNo;
			 }else{
				 str+="、"+data[i].frNo;
			 }
		 }
		 str+="的IC卡号重复，请重新输入";
		 alert(str);
		 return false;
	}
}
function updateFr1(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var jy=document.getElementById("jy");
	var jq=document.getElementById("jq");
	var jbNo=document.getElementById("jbNo");
	var stateZdzf=document.getElementById("stateZdzf");
	var state=document.getElementById("state");
	var frName2=document.getElementById("frName").value;
	var frCard2=document.getElementById("frCard").value;
	var frNo3=document.getElementById("frNo").value;
	var jy2='';
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy2=jy.options[i].value;
			break;
		}
	}
	var jq2='';
	for(var i=0;i<jq.length;i++){
		if(jq.options[i].selected){
			jq2=jq.options[i].value;
			break;
		}
	}
	var jbNo2='';
	for(var i=0;i<jbNo.length;i++){
		if(jbNo.options[i].selected){
			jbNo2=jbNo.options[i].value;
			break;
		}
	}
	var stateZdzf2='';
	for(var i=0;i<stateZdzf.length;i++){
		if(stateZdzf.options[i].selected){
			stateZdzf2=stateZdzf.options[i].value;
			break;
		}
	}
	var state2='';
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			state2=state.options[i].value;
			break;
		}
	}
	var pageNo2=pageNo2=document.getElementById("pageNo").value;
	frNo3=encodeURI(encodeURI(frNo3));
	frName2=encodeURI(encodeURI(frName2));
	frCard2=encodeURI(encodeURI(frCard2));
	jy2=encodeURI(encodeURI(jy2));
	jq2=encodeURI(encodeURI(jq2));
	jbNo2=encodeURI(encodeURI(jbNo2));
	stateZdzf2=encodeURI(encodeURI(stateZdzf2));
	state2=encodeURI(encodeURI(state2));
	
	var frId=tr.cells[9].children[2].value;
	document.forms[0].action="materialMessage.do?method=updateFr1&frId="+frId+"&frNo3="+frNo3+"&frName2="+frName2+"&jy2="+jy2+"&jq2="+jq2+"&jbNo2="+jbNo2+"&pageNo2="+pageNo2+"&stateZdzf2="+stateZdzf2+"&state2="+state2+"&frCard2="+frCard2;
	document.forms[0].submit();

}
function updateSaveFr1(){
	var frId=document.getElementById("frId").value;
	var frName=document.getElementById("frName1").value;
	var frCard=document.getElementById("frCard1").value;
	var hjleft=document.getElementById("hjleft").value;
	var jy=document.getElementById("jy1");
	var jq=document.getElementById("jq1");
	var jb=document.getElementById("jb1");
	var hjjb=document.getElementById("hjJb");
	var infoRjsj=document.getElementById("infoRjsj").value;
	var infoZm=document.getElementById("infoZm").value;
	var infoXq=document.getElementById("infoXq").value;
	var infoCsrq=document.getElementById("infoCsrq").value;
	var infoHome=document.getElementById("infoHome").value;
	var frGj=document.getElementById("frGj").value;
	var monitorFlag=document.getElementById("monitorFlag");
	var stateZdzf=document.getElementById("stateZdzf");
	var zdzfType=document.getElementById("zdzfType").value;
	var state=document.getElementById("state");
	var hjStopTime=document.getElementById("hjStopTime").value;
	var hjStopSM=document.getElementById("hjStopSM").value;
	var state1="";
	if(frCard.length>10){
		alert("卡号长度有误");
		return false;
	}
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			state1=state.options[i].value
			break;
		}
	}
	var outTime=document.getElementById("outTime").value;
	outTime=outTime.replace(/\s+$/g,"");
	if(state1==1 && outTime==""){
		alert("请输入出狱时间");
		return false;
	}
	infoRjsj=infoRjsj.replace(/\s+$/g,"");
	infoZm=infoZm.replace(/\s+$/g,"");
	infoXq=infoXq.replace(/\s+$/g,"");
	infoCsrq=infoCsrq.replace(/\s+$/g,"");
	infoHome=infoHome.replace(/\s+$/g,"");
	zdzfType=zdzfType.replace(/\s+$/g,"");
	frCard=frCard.replace(/\s+$/g,"");
	frName=frName.replace(/\s+$/g,"");
	frGj=frGj.replace(/\s+$/g,"");
	hjStopSM=hjStopSM.replace(/\s+$/g,"");
	var jy1;
	var jq1;
	var jb1;
	var hjjb1;
	var monitorFlag1;
	var stateZdzf1;
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy1=jy.options[i].value;
			break;
		}
	}
	for(var i=0;i<jq.length;i++){
		if(jq.options[i].selected){
			jq1=jq.options[i].value;
			break;
		}
	}
	if(jq1=="null"){
		alert("请选择所属监区");
		return false;
	}
	var patrn1=/^-?\d+$/;
	if (!patrn1.test(hjleft) || parseInt(hjleft)<-1){
		alert("会见剩余次数包含非法字符，必须大于等于-1");
		return false;
	}
	for(var i=0;i<jb.length;i++){
		if(jb.options[i].selected){
			jb1=jb.options[i].value;
			break;
		}
	}
	for(var i=0;i<hjjb.length;i++){
		if(hjjb.options[i].selected){
			hjjb1=hjjb.options[i].value;
			break;
		}
	}
	for(var i=0;i<monitorFlag.length;i++){
		if(monitorFlag.options[i].selected){
			monitorFlag1=monitorFlag.options[i].value;
			break;
		}
	}
	for(var i=0;i<stateZdzf.length;i++){
		if(stateZdzf.options[i].selected){
			stateZdzf1=stateZdzf.options[i].value;
			break;
		}
	}
	if(frName.indexOf("'")>-1){
		alert("罪犯姓名输入有误，不能包含特殊符号");
		return false;
	}
	if(frCard.indexOf("'")>-1){
		alert("罪犯IC卡输入有误，不能包含特殊符号");
		return false;
	}
	if(hjStopSM.indexOf("'")>-1){
		alert("禁止会见说明输入有误，不能包含特殊符号");
		return false;
	}
	if(infoRjsj.indexOf("'")>-1){
		alert("入监时间输入有误，不能包含特殊符号");
		return false;
	}
	if(infoZm.indexOf("'")>-1){
		alert("罪名输入有误，不能包含特殊符号");
		return false;
	}
	if(infoXq.indexOf("'")>-1){
		alert("刑期输入有误，不能包含特殊符号");
		return false;
	}
	if(infoCsrq.indexOf("'")>-1){
		alert("出生日期输入有误，不能包含特殊符号");
		return false;
	}
	if(infoHome.indexOf("'")>-1){
		alert("住址输入有误，不能包含特殊符号");
		return false;
	}
	if(hjjb1==-1){
		if(hjStopTime==""){
			alert("请选择会见禁止日期");
			return false;
		}
	}
	frName=encodeURI(encodeURI(frName));
	frCard=encodeURI(encodeURI(frCard));
	jy1=encodeURI(encodeURI(jy1));
	jq1=encodeURI(encodeURI(jq1));
	jb1=encodeURI(encodeURI(jb1));
	zdzfType=encodeURI(encodeURI(zdzfType));
	 $.ajax({
	      type:"POST",
	      url:"materialMessage.do",
	      data:"method=updateSaveFr1&frId="+frId+"&jy="+jy1+"&jq="+jq1+"&jb="+jb1+"&hjjb="+hjjb1+"&frName="+frName+"&frCard="+frCard+"&hjleft="+hjleft+"&monitorFlag="+monitorFlag1+"&stateZdzf="+stateZdzf1+"&zdzfType="+zdzfType+"&infoRjsj="+infoRjsj+"&infoZm="+infoZm+"&infoXq="+infoXq+"&infoCsrq="+infoCsrq+"&infoHome="+infoHome+"&state="+state1+"&outTime="+outTime+"&frGj="+frGj+"&hjStopTime="+hjStopTime+"&hjStopSM="+hjStopSM,
	      dataType:"json",
	      success:callbackabcd,
	      error:callback1
	});
}
function callbackabcd(data){
	if(data[0]==null){
		//document.forms[0].action="materialMessage.do?method=search";
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}else{
		 var str="该罪犯的IC卡号与其他罪犯编号";
		 for(var i=0;i<data.length;i++){
			 if(i==0){
				 str+=data[i].frNo;
			 }else{
				 str+="、"+data[i].frNo;
			 }
		 }
		 str+="的IC卡号重复，请重新输入";
		 alert(str);
		 return false;
	}
}
function checkQs(){
	var jy=document.getElementById("jy");
	var jq=document.getElementById("jq");
	var jbNo=document.getElementById("jbNo");
	var frName2=document.getElementById("frName").value;
	var frCard2=document.getElementById("frCard").value;
	var frNo3=document.getElementById("frNo").value;
	var isDc=document.getElementById("isDc").value;
	var stateZdzf=document.getElementById("stateZdzf");
	var state=document.getElementById("state");
	var jy2='';
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy2=jy.options[i].value;
			break;
		}
	}
	var jq2='';
	for(var i=0;i<jq.length;i++){
		if(jq.options[i].selected){
			jq2=jq.options[i].value;
			break;
		}
	}
	var jbNo2='';
	for(var i=0;i<jbNo.length;i++){
		if(jbNo.options[i].selected){
			jbNo2=jbNo.options[i].value;
			break;
		}
	}
	var stateZdzf2='';
	for(var i=0;i<stateZdzf.length;i++){
		if(stateZdzf.options[i].selected){
			stateZdzf2=stateZdzf.options[i].value;
			break;
		}
	}
	var state2='';
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			state2=state.options[i].value;
			break;
		}
	}
	var pageNo2=-1;
	if(isDc==1){
		pageNo2=document.getElementById("pageNo").value;
	}
	frNo3=encodeURI(encodeURI(frNo3));
	frName2=encodeURI(encodeURI(frName2));
	frCard2=encodeURI(encodeURI(frCard2));
	jy2=encodeURI(encodeURI(jy2));
	jq2=encodeURI(encodeURI(jq2));
	jbNo2=encodeURI(encodeURI(jbNo2));
	stateZdzf2=encodeURI(encodeURI(stateZdzf2));
	state2=encodeURI(encodeURI(state2));
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frNo=tr.cells[9].children[3].value;
	document.forms[0].action="materialMessage.do?method=checkQs&frNo="+frNo+"&frNo3="+frNo3+"&frName2="+frName2+"&jy2="+jy2+"&jq2="+jq2+"&jbNo2="+jbNo2+"&pageNo2="+pageNo2+"&stateZdzf2="+stateZdzf2+"&state2="+state2+"&frCard2="+frCard2;
	document.forms[0].submit();
}
function addFrQs(){
	var jy2=document.getElementById("jy").value;
	var jq2=document.getElementById("jq").value;
	var jbNo2=document.getElementById("jbNo").value;
	var frName2=document.getElementById("frName").value;
	var frCard2=document.getElementById("frCard").value;
	var frNo3=document.getElementById("frNo3").value;
	var frNo=document.getElementById("frNo").value;
	var pageNo2=document.getElementById("pageNo").value;
	var stateZdzf2=document.getElementById("stateZdzf").value;
	var state2=document.getElementById("state").value;
	frNo3=encodeURI(encodeURI(frNo3));
	frName2=encodeURI(encodeURI(frName2));
	frCard2=encodeURI(encodeURI(frCard2));
	jy2=encodeURI(encodeURI(jy2));
	jq2=encodeURI(encodeURI(jq2));
	jbNo2=encodeURI(encodeURI(jbNo2));
	stateZdzf2=encodeURI(encodeURI(stateZdzf2));
	state2=encodeURI(encodeURI(state2));
	document.forms[0].action="materialMessage.do?method=addQs&frNo="+frNo+"&frNo3="+frNo3+"&frName2="+frName2+"&jy2="+jy2+"&jq2="+jq2+"&jbNo2="+jbNo2+"&pageNo2="+pageNo2+"&stateZdzf2="+stateZdzf2+"&state2="+state2+"&frCard2="+frCard2;
	document.forms[0].submit();
}

function clearpicture(){
	document.getElementById('cap1').clear();
	//document.getElementById("MyFlexApps").clearpicture();
}
function setWidth(){
	return 135;//设置宽度
}
function setHeight(){
	return 141;//设置高度
}
function shibie(){
 	var IDCard2=document.getElementById("IDCard2");
	var aa=IDCard2.Syn_ReadMsg(1);
	if(aa==0){
		IDCard2.SetPhotoName(2);
		document.getElementById("qsName").value=IDCard2.PeopleName;
		document.getElementById("qsSfz").value=IDCard2.IDCardNo;
		document.getElementById("dz").value=IDCard2.Address;
		var sex=IDCard2.Sex;
		var xb=document.getElementById("xb");
		for(var i=0;i<xb.length;i++){
			if(sex.indexOf(xb.options[i].value)>=0){
				xb.options[i].selected=true;
				break;
			}
		}
		var zp=IDCard2.PhotoStr;
		var zpAddress=IDCard2.StrToJpg(zp);
		document.getElementById("photoAddress").value=zpAddress;
	}
}
function shibie1(){
 	var IDCard2=document.getElementById("IDCard2");
 	var str = IDCard2.FindReader();
  	if (str <= 0){
  		alert("没有找到读卡器");
  		return false;
  	}
	IDCard2.SetPhotoName(2);
 	IDCard2.SetPhotoType(1);
 	var aa=IDCard2.ReadCardMsg();
	if(aa==0){
		document.getElementById("qsName").value=IDCard2.NameA;
		document.getElementById("qsSfz").value=IDCard2.CardNo;
		document.getElementById("dz").value=IDCard2.Address;
		var sex=IDCard2.Sex;
		var xb=document.getElementById("xb");
		if(sex==2){
					 for(var i=0;i<xb.length;i++){
						if(xb.options[i].value=='女'){
							xb.options[i].selected=true;
							break;
						}
					}
				  }else{
				  	for(var i=0;i<xb.length;i++){
						if(xb.options[i].value=='男'){
							xb.options[i].selected=true;
							break;
						}
					}
				  }
		var zpAddress=IDCard2.PhotoName;
		document.getElementById("photoAddress").value=zpAddress;
		document.getElementById("sfzz").src="";
		document.getElementById("sfzz").src=zpAddress;
	}
}
function checkRate(nubmer){
	var re=/^[0-9a-zA-Z]*$/g;
	if(!re.test(nubmer)){
		return false;
	}else{
		return true;
	}
}
function addSaveQs(){
	var frNo1=document.getElementById("frNo1").value;
	var dz=document.getElementById("dz").value;
	var qsSfz=document.getElementById("qsSfz").value;
	var qsName=document.getElementById("qsName").value;
	var qsCard=document.getElementById("qsCard").value;
	var tele=document.getElementById("tele").value;
	var photoAddress=document.getElementById("photoAddress").value;
	var jz=document.getElementById("jz").value;
	var qsSfzWlh=document.getElementById("qsSfzWlh").value;
	var bz=document.getElementById("bz").value;
	qsSfz=qsSfz.replace(/\s+$/g,"");
	var flag=checkRate(qsSfz);
	if(!flag){
		alert("证件号码只能是数字和英文组合");
		return false;
	}
	if(qsCard.length>10){
		alert("卡号长度有误");
		return false;
	}
//	var gx=document.getElementById("gx");
//	var gx1;
//	var gx3;
//	for(var i=0;i<gx.length;i++){
//		if(gx.options[i].selected){
//			gx3=gx.options[i].value;
//			break;
//		}
//	}
	var gx=document.getElementById("gx").value;
	gx=gx.replace(/\s+$/g,"");
//	if(gx2!=""){
//		gx1=gx2;
//	}else{
//		gx1="";
//	}
	var xb=document.getElementById("xb");
	var xb1;
	for(var i=0;i<xb.length;i++){
		if(xb.options[i].selected){
			xb1=xb.options[i].value;
			break;
		}
	}
	var zjlb=document.getElementById("zjlb");
	var zjlb1;
	for(var i=0;i<zjlb.length;i++){
		if(zjlb.options[i].selected){
			zjlb1=zjlb.options[i].value;
			break;
		}
		
	}
	var spState=document.getElementById("spState");
	var spState1;
	for(var i=0;i<zjlb.length;i++){
		if(spState.options[i].selected){
			spState1=spState.options[i].value;
			break;
		}
		
	}
//	var sw=document.getElementById("sw");
//	var sw1;
//	for(var i=0;i<sw.length;i++){
//		if(sw.options[i].selected){
//			sw1=sw.options[i].value;
//			break;
//		}
//		
//	}
	if(qsSfz==''){
		alert("亲属证件号码不能为空");
		return false;
	}
	if(qsName==''){
		alert("亲属姓名不能为空");
		return false;
	}
	if(gx==''){
		alert("关系不能为空");
		return false;
	}
//	if(qsSfzWlh==''){
//		alert("身份证物理号不能为空");
//		return false;
//	}
//	if(qsCard==''){
//		alert("亲属IC卡号不能为空");
//		return false;
//	}
	if(qsSfz.indexOf("'")>-1){
		alert("亲属证件号码输入有误，不能包含特殊符号");
		return false;
	}
	if(qsName.indexOf("'")>-1){		
		alert("亲属姓名输入有误，不能包含特殊符号");
		return false;
	}
//	if(gx2.indexOf("'")>-1){
//		alert("关系输入有误，不能包含特殊符号");
//		return false;
//	}
	if(qsCard.indexOf("'")>-1){
		alert("亲属IC卡输入有误，不能包含特殊符号");
		return false;
	}
	if(dz.indexOf("'")>-1){
		alert("地址输入有误，不能包含特殊符号");
		return false;
	}
	if(tele.indexOf("'")>-1){
		alert("电话号码输入有误，不能包含特殊符号");
		return false;
	}
	if(qsSfzWlh.indexOf("'")>-1){
		alert("身份证物理号输入有误，不能包含特殊符号");
		return false;
	}
	qsName=qsName.replace(/\s+$/g,"");
	dz=dz.replace(/\s+$/g,"");
	qsCard=qsCard.replace(/\s+$/g,"");
	tele=tele.replace(/\s+$/g,"");
	qsSfzWlh=qsSfzWlh.replace(/\s+$/g,"");
	bz=bz.replace(/\s+$/g,"");
	$.ajax({
	      type:"POST",
	      url:"materialMessage.do?method=addSaveFrQs",
	      data : {
				frNo:frNo1,
				qsSfz:qsSfz,
				qsName:qsName,
				qsCard:qsCard,
				dz:dz,
				tele:tele,
				gx:gx,
				xb:xb1,
				zjlb:zjlb1,
				spState:spState1,
				photoAddress:photoAddress,
				qsSfzWlh:qsSfzWlh,
				bz:bz,
				jz:"'"+jz+"'"
			  },
	      dataType:"json",
	      success:callback20,
	      error:callback1
	});
	
}
function callback20(data){
	if(data[0]==1){
		alert("罪犯亲属证件号码不能重复");
		return false;
	}else{
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}
}
function delFrQs(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frNo=tr.cells[13].children[2].value;
	var webId=tr.cells[13].children[3].value;
	if(window.confirm("确认删除记录？")==true){
		var jy2=document.getElementById("jy").value;
		var jq2=document.getElementById("jq").value;
		var jbNo2=document.getElementById("jbNo").value;
		var frName2=document.getElementById("frName").value;
		var frCard2=document.getElementById("frCard").value;
		var frNo3=document.getElementById("frNo3").value;
		var frNo=document.getElementById("frNo").value;
		var pageNo2=document.getElementById("pageNo").value;
		var stateZdzf2=document.getElementById("stateZdzf").value;
		var state2=document.getElementById("state").value;
		frNo3=encodeURI(encodeURI(frNo3));
		frName2=encodeURI(encodeURI(frName2));
		jy2=encodeURI(encodeURI(jy2));
		jq2=encodeURI(encodeURI(jq2));
		jbNo2=encodeURI(encodeURI(jbNo2));
		stateZdzf2=encodeURI(encodeURI(stateZdzf2));
		state2=encodeURI(encodeURI(state2));
		document.forms[0].action="materialMessage.do?method=delFrQs&frNo="+frNo+"&webId="+webId+"&frNo3="+frNo3+"&frName2="+frName2+"&jy2="+jy2+"&jq2="+jq2+"&jbNo2="+jbNo2+"&pageNo2="+pageNo2+"&stateZdzf2="+stateZdzf2+"&state2="+state2+"&frCard2="+frCard2;
		document.forms[0].submit();
	}
}
function updateFrQs(){
	var jy2=document.getElementById("jy").value;
	var jq2=document.getElementById("jq").value;
	var jbNo2=document.getElementById("jbNo").value;
	var frName2=document.getElementById("frName").value;
	var frCard2=document.getElementById("frCard").value;
	var frNo3=document.getElementById("frNo3").value;
	var frNo=document.getElementById("frNo").value;
	var pageNo2=document.getElementById("pageNo").value;
	var stateZdzf2=document.getElementById("stateZdzf").value;
	var state2=document.getElementById("state").value;
	frNo3=encodeURI(encodeURI(frNo3));
	frName2=encodeURI(encodeURI(frName2));
	frCard2=encodeURI(encodeURI(frCard2));
	jy2=encodeURI(encodeURI(jy2));
	jq2=encodeURI(encodeURI(jq2));
	jbNo2=encodeURI(encodeURI(jbNo2));
	stateZdzf2=encodeURI(encodeURI(stateZdzf2));
	state2=encodeURI(encodeURI(state2));
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frNo=tr.cells[13].children[2].value;
	var webId=tr.cells[13].children[3].value;
	document.forms[0].action="materialMessage.do?method=updateFrQs&webId="+webId+"&frNo="+frNo+"&frNo3="+frNo3+"&frName2="+frName2+"&jy2="+jy2+"&jq2="+jq2+"&jbNo2="+jbNo2+"&pageNo2="+pageNo2+"&stateZdzf2="+stateZdzf2+"&state2="+state2+"&frCard2="+frCard2;
	document.forms[0].submit();
}
function updateshibie(){
	var IDCard2=document.getElementById("IDCard2");
	var str = IDCard2.FindReader();
  	if (str <= 0){
  		alert("没有找到读卡器");
  		return false;
  	}
	IDCard2.SetPhotoName(2);
 	IDCard2.SetPhotoType(1);
 	var aa=IDCard2.ReadCardMsg();
	if(aa==0){
		document.getElementById("qsName").value=IDCard2.NameA;
		document.getElementById("qsSfz").value=IDCard2.CardNo;
		document.getElementById("dz").value=IDCard2.Address;
		var sex=IDCard2.Sex;
		var xb=document.getElementById("xb");
		if(sex==2){
		 	for(var i=0;i<xb.length;i++){
				if(xb.options[i].value=='女'){
					xb.options[i].selected=true;
					break;
			}
				}
	  	}else{
		  	for(var i=0;i<xb.length;i++){
				if(xb.options[i].value=='男'){
					xb.options[i].selected=true;
					break;
				}
			}
		}
		var zpAddress=IDCard2.PhotoName;
		document.getElementById("photoAddress").value=zpAddress;
		document.getElementById("sfzz").src="";
		document.getElementById("sfzz").src=zpAddress;
	}
}
function updateSaveFrQs(){
	var qsId=document.getElementById("qsId").value;
	var qsSfz=document.getElementById("qsSfz").value;
	var dz=document.getElementById("dz").value;
	var qsName=document.getElementById("qsName").value;
	var qsCard=document.getElementById("qsCard").value;
	var tele=document.getElementById("tele").value;
	var gx=document.getElementById("gx").value;
	var photoAddress=document.getElementById("photoAddress").value;
	var jz=document.getElementById("jz").value;
	var qsSfzWlh=document.getElementById("qsSfzWlh").value;
	var bz=document.getElementById("bz").value;
	var hjStopTime=document.getElementById("hjStopTime").value;
	var flag=checkRate(qsSfz);
	if(!flag){
		alert("证件号码只能是数字和英文组合");
		return false;
	}
	if(qsCard.length>10){
		alert("卡号长度有误");
		return false;
	}
	gx=gx.replace(/\s+$/g,"");
//	var gx1;
//	for(var i=0;i<gx.length;i++){
//		if(gx.options[i].selected){
//			gx1=gx.options[i].value;
//			break;
//		}
//	}
	var xb=document.getElementById("xb");
	var xb1;
	for(var i=0;i<xb.length;i++){
		if(xb.options[i].selected){
			xb1=xb.options[i].value;
			break;
		}
	}
	var zjlb=document.getElementById("zjlb");
	var zjlb1;
	for(var i=0;i<zjlb.length;i++){
		if(zjlb.options[i].selected){
			zjlb1=zjlb.options[i].value;
			break;
		}
	}
	var spState=document.getElementById("spState");
	var spState1;
	for(var i=0;i<spState.length;i++){
		if(spState.options[i].selected){
			spState1=spState.options[i].value;
			break;
		}
	}
//	var sw=document.getElementById("sw");
//	var sw1;
//	for(var i=0;i<sw.length;i++){
//		if(sw.options[i].selected){
//			sw1=sw.options[i].value;
//			break;
//		}
//	}
	if(qsSfz==''){
		alert("亲属证件号码不能为空");
		return false;
	}
	if(qsName==''){
		alert("亲属姓名不能为空");
		return false;
	}
	if(gx==''){
		alert("关系不能为空");
		return false;
	}
//	if(qsSfzWlh==''){
//		alert("身份证物理号不能为空");
//		return false;
//	}
//	if(qsCard==''){
//		alert("亲属IC卡号不能为空");
//		return false;
//	}
	if(qsSfz.indexOf("'")>-1){
		alert("亲属证件号码输入有误，不能包含特殊符号");
		return false;
	}
	if(qsName.indexOf("'")>-1){
		alert("亲属姓名输入有误，不能包含特殊符号");
		return false;
	}
//	if(gx.indexOf("'")>-1){
//		alert("关系输入有误，不能包含特殊符号");
//		return false;
//	}
	if(qsCard.indexOf("'")>-1){
		alert("亲属ID卡输入有误，不能包含特殊符号");
		return false;
	}
	if(dz.indexOf("'")>-1){
		alert("地址输入有误，不能包含特殊符号");
		return false;
	}
	if(tele.indexOf("'")>-1){
		alert("电话号码输入有误，不能包含特殊符号");
		return false;
	}
	if(qsSfzWlh.indexOf("'")>-1){
		alert("身份证物理号输入有误，不能包含特殊符号");
		return false;
	}
	qsName=qsName.replace(/\s+$/g,"");
	dz=dz.replace(/\s+$/g,"");
	qsCard=qsCard.replace(/\s+$/g,"");
	tele=tele.replace(/\s+$/g,"");
	qsSfzWlh=qsSfzWlh.replace(/\s+$/g,"");
	bz=bz.replace(/\s+$/g,"");
	qsSfz=qsSfz.replace(/\s+$/g,"");
	$.ajax({
	      type:"POST",
	      url:"materialMessage.do?method=updateSaveFrQs",
	      data : {
				qsId:qsId,
				qsSfz:qsSfz,
				qsName:qsName,
				qsCard:qsCard,
				dz:dz,
				tele:tele,
				gx:gx,
				xb:xb1,
				zjlb:zjlb1,
				spState:spState1,
				photoAddress:photoAddress,
				qsSfzWlh:qsSfzWlh,
				bz:bz,
				hjStopTime:hjStopTime,
				jz:"'"+jz+"'"
			  },
	      dataType:"json",
	      success:callback3,
	      error:callback1
	});
}
function callback3(data){
	if(data[0]==0){
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}else{
		alert("罪犯亲属证件号码不能重复");
		return false;
	}
}
function checkTpDh(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frNo=tr.cells[7].children[3].value;
	 document.forms[0].action="materialMessage.do?method=checkTpDh&frNo="+frNo;
	 document.forms[0].submit();
}
function addSaveFrTpdh(){
	var frNo1=document.getElementById("frNo1").value;
	var tpTele=document.getElementById("tpTele").value;
	var tpxm=document.getElementById("tpxm").value;
	var tpcs=document.getElementById("tpcs").value;
	var tpsc=document.getElementById("tpsc").value;
	var tpgx=document.getElementById("tpgx");
	var tpgx1;
	tpTele=tpTele.replace(/\s+$/g,"");
	if(tpTele==""){
		alert("特批电话不能为空");
		return false;
	}
	tpcs=tpcs.replace(/\s+$/g,"");
	if(tpcs==""){
		alert("特批次数不能为空");
		return false;
	}
	tpsc=tpsc.replace(/\s+$/g,"");
	if(tpsc==""){
		alert("特批时长不能为空");
		return false;
	}
	for(var i=0;i<tpgx.length;i++){
		if(tpgx.options[i].selected){
			tpgx1=tpgx.options[i].value;
			break;
		}
	}
	frNo1=encodeURI(encodeURI(frNo1));
	tpTele=encodeURI(encodeURI(tpTele));
	tpxm=encodeURI(encodeURI(tpxm));
	tpgx1=encodeURI(encodeURI(tpgx1));
	tpcs=encodeURI(encodeURI(tpcs));
	tpsc=encodeURI(encodeURI(tpsc));
	$.ajax({
	      type:"POST",
	      url:"materialMessage.do",
	      data:"method=addSaveFrTpDh&frNo1="+frNo1+"&tpTele="+tpTele+"&tpxm="+tpxm+"&tpgx1="+tpgx1+"&tpcs="+tpcs+"&tpsc="+tpsc,
	      dataType:"json",
	      success:callback4,
	      error:callback1
	});
	
}
function callback4(data){
	if(data[0]==null){
		document.forms[0].submit();
	}
}
function delfrTsdh(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frTsdhId=tr.cells[11].children[2].value;
	var frNo=tr.cells[11].children[3].value;
	if(window.confirm("确认删除记录？")==true){
		document.forms[0].action="materialMessage.do?method=delfrTsdh&frTsdhId="+frTsdhId+"&frNo="+frNo;
		document.forms[0].submit();
	}
}
function updatefrTsdh(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frTsdhId=tr.cells[11].children[2].value;
	var frNo=tr.cells[11].children[3].value;
	document.forms[0].action="materialMessage.do?method=updatefrTsdh&frTsdhId="+frTsdhId+"&frNo="+frNo;
	document.forms[0].submit();
}
function updateSaveSaveFrTpdh(){
	var tpid=document.getElementById("tpid").value;
	var tpTele=document.getElementById("tpTele").value;
	var tpxm=document.getElementById("tpxm").value;
	var tpcs=document.getElementById("tpcs").value;
	var tpsc=document.getElementById("tpsc").value;
	var tpgx=document.getElementById("tpgx");
	var tpgx1;
	tpTele=tpTele.replace(/\s+$/g,"");
	if(tpTele==""){
		alert("特批电话不能为空");
		return false;
	}
	tpcs=tpcs.replace(/\s+$/g,"");
	if(tpcs==""){
		alert("特批次数不能为空");
		return false;
	}
	tpsc=tpsc.replace(/\s+$/g,"");
	if(tpsc==""){
		alert("特批时长不能为空");
		return false;
	}
	for(var i=0;i<tpgx.length;i++){
		if(tpgx.options[i].selected){
			tpgx1=tpgx.options[i].value;
			break;
		}
	}
	tpTele=encodeURI(encodeURI(tpTele));
	tpxm=encodeURI(encodeURI(tpxm));
	tpgx1=encodeURI(encodeURI(tpgx1));
	tpcs=encodeURI(encodeURI(tpcs));
	tpsc=encodeURI(encodeURI(tpsc));
	$.ajax({
	      type:"POST",
	      url:"materialMessage.do",
	      data:"method=updateSaveFrTpDh&tpid="+tpid+"&tpTele="+tpTele+"&tpxm="+tpxm+"&tpgx1="+tpgx1+"&tpcs="+tpcs+"&tpsc="+tpsc,
	      dataType:"json",
	      success:callback5,
	      error:callback1
	});
}
function callback5(data){
	if(data[0]==null){
		document.forms[0].submit();
	}
}

function isIdCardNo(num){   
    num = num.toUpperCase();  
   //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。   
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))){ 
         alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。'); 
        return false; 
    } 
		//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
		//下面分别分析出生日期和校验位 
		var len, re; 
		len = num.length; 
		if (len == 15) { 
			re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/); 
			var arrSplit = num.match(re); 
			//检查生日日期是否正确 
			var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]); 
			var bGoodDay; 
			bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
			if (!bGoodDay) { 
				alert('输入的身份证号里出生日期不对！');   
				return false; 
			}else{
				//将15位身份证转成18位 
				//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
				var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
				var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
				var nTemp = 0, i;   
				num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6); 
				for(i = 0; i < 17; i ++) { 
					nTemp += num.substr(i, 1) * arrInt[i]; 
				} 
				num += arrCh[nTemp % 11];   
				return num;   
			}   
		} 
		if (len == 18) { 
			re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/); 
			var arrSplit = num.match(re); 
			//检查生日日期是否正确 
			var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]); 
			var bGoodDay; 
			bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
			if (!bGoodDay) { 
				alert('输入的身份证号里出生日期不对！'); 
				return false; 
			}else { 
				//检验18位身份证的校验码是否正确。 
				//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
				var valnum; 
				var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
				var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
				var nTemp = 0, i; 
				for(i = 0; i < 17; i ++) { 
					nTemp += num.substr(i, 1) * arrInt[i]; 
				} 
				valnum = arrCh[nTemp % 11]; 
				if (valnum != num.substr(17, 1)) {
					alert('18位身份证的校验码不正确！应该为：' + valnum); 
					return false; 
				} 
				return num; 
			} 
		} 
		return false; 
	}   
	
function checkJq(){
	var jy=document.getElementById("jy");
	var jy1="";
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy1=jy.options[i].value;
			break;
		}
	}
	if(jy1!="null"){
		jy1=encodeURI(encodeURI(jy1));
		$.ajax({
		      type:"POST",
		      url:"materialMessage.do",
		      data:"method=checkJq&jy1="+jy1,
		      dataType:"json",
		      success:callback7,
		      error:callback1
		});
	}else{
		document.getElementById("jq").disabled=true;
		var jq=document.getElementById("jq");
		jq.options.length=0;
		jq.add(new  Option("全部","null"));
	}
}
function callback7(data){
	document.getElementById("jq").disabled=false;
	var jq=document.getElementById("jq");
	jq.options.length=0;   
	if(data.length>0){
		jq.add(new  Option("全部","null")); 
		for(var i=0;i<data.length;i++){
			jq.add(new  Option(data[i].jqName,data[i].jqNo)); 
		}
	}else{
		jq.add(new  Option("全部","null")); 
	}
	return false;
}
function checkJq1(){
	var jy=document.getElementById("jy1");
	var jy1="";
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy1=jy.options[i].value;
			break;
		}
	}
	if(jy1!="null"){
		jy1=encodeURI(encodeURI(jy1));
		$.ajax({
		      type:"POST",
		      url:"materialMessage.do",
		      data:"method=checkJq&jy1="+jy1,
		      dataType:"json",
		      success:callback8,
		      error:callback1
		});
	}else{
		document.getElementById("jq1").disabled=true;
		var jq1=document.getElementById("jq1");
		jq1.options.length=0;
		jq1.add(new  Option("全部","null"));
	}
}
function callback8(data){
	document.getElementById("jq1").disabled=false;
	var jq=document.getElementById("jq1");
	jq.options.length=0;   
	if(data.length>0){
		jq.add(new  Option("全部","null")); 
		for(var i=0;i<data.length;i++){
			jq.add(new  Option(data[i].jqName,data[i].jqNo)); 
		}
	}else{
		jq.add(new  Option("全部","null")); 
	}
	return false;
}
function dcAllFr(){
	var isDc=document.getElementById("isDc").value;
	if(isDc==0){
		alert("请查询到数据后再导出");
		return false;
	}else{
		document.forms[0].action=basePath+"materialMessage.do?method=dcAllFrXx";
		document.forms[0].submit();
//		document.forms[0].action=basePath+"materialMessage.do?method=drAllFrXx" ;
	}

}
function myFormCheck(){
	var myFile=document.getElementById("myFile").value;
	if(myFile==""){
		alert("请点击浏览按钮，选择您要上传的文件!");
		return false;
	}else{
		var strs=myFile.toLowerCase();
		var lens=strs.length;
		var extname=strs.substring(lens-4,lens);
		var extname1=strs.substring(lens-5,lens);
		if(extname!=".xls"){
			alert("系统目前只支持.xls文件导入，请转换为office2003格式的.xls文件后重试")
			return false;
		}
		document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>处理中，请稍后</b>";
		document.getElementById("load").style.display="block";
		document.forms[0].action="materialMessage.do?method=drAllFrXx";
		document.forms[0].submit();
	}
}
function daoRuZsDB(){
	if(window.confirm("确定要将临时数据导入吗？")==true){
		document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>处理中，请稍后</b>";
		document.getElementById("load").style.display="block";
		document.forms[0].action="materialMessage.do?method=daoRuZsDB";
		document.forms[0].submit();
	}
}
function remoteUpdate(){
	var frNo=document.getElementById("frNo").value;
	frNo=encodeURI(encodeURI(frNo));
	$.ajax({
	      type:"POST",
	      url:"materialMessage.do",
	      data:"method=remoteUpdate&frNo="+frNo,
	      dataType:"json",
	      success:callback9,
	      error:callback1
	});
}
function callback9(data){
	var frNo=document.getElementById("frNo").value;
	if(data[0]==1){
		alert("更新数据成功");
		document.forms[0].action="materialMessage.do?method=checkQs&frNo="+frNo;
		document.forms[0].submit();
	}else{
		alert("更新数据失败,无法连接到远程服务器");
		return false;
	}
}
function drAllFrXx(){
	document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>处理中，请稍后</b>";
	document.getElementById("load").style.display="block";
	document.forms[0].action="materialMessage.do?method=drAllFrXx";
	document.forms[0].submit();

}
function remoteFr(){
	var frNo5=document.getElementById("frNo5").value;
	frNo5=frNo5.replace(/\s+$/g,"");
	if(frNo5==""){
		alert("请输入需要更新的罪犯编号");
		return false;
	}
	frNo5=encodeURI(encodeURI(frNo5));
	$.ajax({
	      type:"POST",
	      url:"materialMessage.do",
	      data:"method=remoteFr&frNo5="+frNo5,
	      dataType:"json",
	      success:callback10,
	      error:callback1
	});
}
function callback10(data){
	if(data[0]==1){
		alert("更新数据成功");
		return false;
	}else{
		alert("更新数据失败");
		return false;
	}
}
function openPort(){
//	reID.ReadCardID(4, "baud=9600 parity=N data=8 stop=1");
//	var isSuc=false;
//	for(var i=1;i<10;i++){
//		 isSuc=document.getElementById("WM1711").OpenPort1(i,"115200");
//		 if(isSuc==true){
//		 	break;
//		 }
//	}
	
	var str=document.getElementById("IDCard2").FindReader();
	if(str>1000){
		document.getElementById("IDCard2").SetloopTime(1000);
  		document.getElementById("IDCard2").SetReadType(1);
  		document.getElementById("IDCard2").SetPhotoType(1);
	}
}

function colsePort(){
	document.getElementById("IDCard2").SetReadType(0);
//	document.getElementById("WM1711").FunCloseCard();	
}
function openPort1(){
	var isSuc=false;
	for(var i=1;i<10;i++){
		 isSuc=document.getElementById("WM1711").OpenPort1(i,"115200");
		 if(isSuc==true){
		 	break;
		 }
	}
}

function colsePort1(){
	document.getElementById("WM1711").FunCloseCard();
	
}
function selectServer(){
	
	var jy=document.getElementById("jy");
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].value=='Server1'){
			jy.options[i].selected=true;
			break;
		}
	}
	var jy1='Server1';
	if(jy1!="null"){
		jy1=encodeURI(encodeURI(jy1));
		$.ajax({
		      type:"POST",
		      url:"materialMessage.do",
		      data:"method=checkJq&jy1="+jy1,
		      dataType:"json",
		      success:callback7,
		      error:callback1
		});
	}else{
		document.getElementById("jq").disabled=true;
		var jq=document.getElementById("jq");
		jq.options.length=0;
		jq.add(new  Option("全部","null"));
	}
}
function updateDownQs1(){
	var frNo =document.getElementById("frNo").value;
	$.ajax({
	      type:"POST",
	      url:"materialMessage.do",
	      data:"method=updateDownQs1&frNo="+frNo,
	      dataType:"json",
	      success:callback23
	});
}
function callback23(data){
	if(data[0]!="-1"){
		alert("此次更新"+data[0]+"条亲属数据");
	}else{
		alert("此次更新0条亲属数据");
	}
}
function changeZdzfType(){
	var stateZdzf=document.getElementById("stateZdzf");
	var stateZdzf1;
	for(var i=0;i<stateZdzf.length;i++){
		if(stateZdzf.options[i].selected){
			stateZdzf1=stateZdzf.options[i].value;
			break;
		}
	}
	if(stateZdzf1==1){
		document.getElementById("zdzfType").disabled=false;
//		document.getElementById("hjStopSM").disabled=false;
	}else{
		document.getElementById("zdzfType").disabled=true;
		document.getElementById("zdzfType").value="";
//		document.getElementById("hjStopSM").disabled=true;
	}
}
function setCysj(){
	var state=document.getElementById("state");
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			if(state.options[i].value==0){
				document.getElementById("outTime").disabled=true;
				document.getElementById("outTime").value="";
			}else{
				document.getElementById("outTime").disabled=false;
			}
			break;
		}
	}
}
function hjST(){
	var hjjb=document.getElementById("hjJb");
	var hjjb1;
	for(var i=0;i<hjjb.length;i++){
		if(hjjb.options[i].selected){
			hjjb1=hjjb.options[i].value;
			break;
		}
	}
	if(hjjb1==-1){
		document.getElementById("hjStopTime").disabled=false;
		document.getElementById("hjStopSM").disabled=false;
	}else{
		document.getElementById("hjStopTime").disabled=true;
		document.getElementById("hjStopTime").value="";
		document.getElementById("hjStopSM").disabled=true;
	}
}
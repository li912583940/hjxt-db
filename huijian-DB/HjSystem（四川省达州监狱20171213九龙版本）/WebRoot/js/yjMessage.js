function enterSubmit(src,e){
         if(window.event)
             keyPressed = window.event.keyCode; // IE
         else
            keyPressed = e.which; // Firefox
         if(keyPressed==13){ 
        	 chaxun();                     
             return false;
        }
}
function chaxun(){
	var yjNum=document.getElementById("yjNum").value;
	var jyName=document.getElementById("jyName").value;
	if(yjNum.indexOf("'")>-1){
		alert("警察警号输入有误，不能包含特殊符号");
		return false;
	}
	if(jyName.indexOf("'")>-1){
		alert("警察姓名输入有误，不能包含特殊符号");
		return false;
	}
	document.forms[0].action="yjMessage.do?method=search";
	document.forms[0].submit();
}
function addYj(){
	document.forms[0].action="yjMessage.do?method=addYj";
	document.forms[0].submit();
}
function addSaveYj(){
	var yjNum1=document.getElementById("yjNum1").value;
	var yjName1=document.getElementById("yjName1").value;
	var yjCard=document.getElementById("yjCard").value;
	//var jyId1=document.getElementById("jyId");
	var jyId="";
	//var jqId1=document.getElementById("jqId");
	var jqId="";
	var deptName1=document.getElementById("deptName");
	var deptName;
	
	yjNum1=yjNum1.replace(/\s+$/g,"");
	if(yjNum1 == ""){
		alert("警察编号不能为空");
		return false;
	}
//	for(var i=0;i<jyId1.length;i++){
//		if(jyId1.options[i].selected){
//			jyId=jyId1.options[i].value;
//			break;
//		}
//	}
//	if(jyId=="null"){
//		alert("请选择服务器名称");
//		return false;
//	}
//	for(var i=0;i<jqId1.length;i++){
//		if(jqId1.options[i].selected){
//			jqId=jqId1.options[i].value;
//			break;
//		}
//	}
//	if(jqId==null){
//		alert("请选择所属监区");
//		return false;
//	}
	yjName1=yjName1.replace(/\s+$/g,"");
	if(yjName1!=""){
		yjName1=encodeURI(encodeURI(yjName1));
	}
	
	for(var i=0;i<deptName1.length;i++){
		if(deptName1.options[i].selected){
			deptName=deptName1.options[i].value;
			break;
		}
	}
	$.ajax({
	      type:"POST",
	      url:"yjMessage.do",
	      data:"method=addSaveyjMessage&yjNum1="+yjNum1+"&yjName1="+yjName1+"&yjCard="+yjCard+"&jyId="+jyId+"&jqId="+jqId+"&deptName="+deptName,
	      dataType:"json",
	      success:callback,
	      error:callback1
	});
}
function callback(data){
	if(data[0]==null){
		document.forms[0].action="yjMessage.do?method=search"
        document.forms[0].submit();
	}else{
		if(data[0]=="1"){
			alert("警察编号已存在，请重新输入");
			return false;
		}
	}
}
function callback1(a,b,c){
   alert(a);
   alert(b);
   alert(c);
}
function updateYjMessage(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var webId1=document.getElementsByName("webId");
	var webId=webId1[tr.rowIndex-2].value;
	document.forms[0].action="yjMessage.do?method=updateYjMessage&webId="+webId;
    document.forms[0].submit();
}
function saveUpdateYj(){
	var webId=document.getElementById("webId").value;
	var yjName1=document.getElementById("yjName1").value;
	var yjCard=document.getElementById("yjCard").value;
	//var jyId1=document.getElementById("jyId");
	//var jqId1=document.getElementById("jqId");
	var jqId="";
	var jyId="";
	var deptName1=document.getElementById("deptName");
	var deptName;
//	for(var i=0;i<jyId1.length;i++){
//		if(jyId1.options[i].selected){
//			jyId=jyId1.options[i].value;
//			break;
//		}
//	}
//	for(var i=0;i<jqId1.length;i++){
//		if(jqId1.options[i].selected){
//				jqId=jqId1.options[i].value;
//				break;
//		}
//	}
//	if(jqId=="null"){
//		alert("请选择所属监区");
//		return false;
//	}
	yjName1=yjName1.replace(/\s+$/g,"");
	if(yjName1!=""){
		yjName1=encodeURI(encodeURI(yjName1));
	}
	for(var i=0;i<deptName1.length;i++){
		if(deptName1.options[i].selected){
			deptName=deptName1.options[i].value;
			break;
		}
	}
	$.ajax({
	      type:"POST",
	      url:"yjMessage.do",
	      data:"method=updateSaveyjMessage&webId="+webId+"&yjName1="+yjName1+"&yjCard="+yjCard+"&jqId="+jqId+"&jyId="+jyId+"&deptName="+deptName,
	      dataType:"json",
	      success:callback2,
	      error:callback1
	});
}
function updateYjMessage1(){
	var deptName=document.getElementById("deptName");
	var yjNum2=document.getElementById("yjNum").value;
	var jyName2=document.getElementById("jyName").value;
	var deptName2='';
	for(var i=0;i<deptName.length;i++){
		if(deptName.options[i].selected){
			deptName2=deptName.options[i].value;
			break;
		}
	}
	var pageNo2=pageNo2=document.getElementById("pageNo").value;
	
	deptName2=encodeURI(encodeURI(deptName2));
	yjNum2=encodeURI(encodeURI(yjNum2));
	jyName2=encodeURI(encodeURI(jyName2));
	
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var webId1=document.getElementsByName("webId");
	var webId=webId1[tr.rowIndex-2].value;
	document.forms[0].action="yjMessage.do?method=updateYjMessage1&webId="+webId+"&deptName2="+deptName2+"&yjNum2="+yjNum2+"&jyName2="+jyName2+"&pageNo2="+pageNo2;
    document.forms[0].submit();
}
function saveUpdateYj1(){
	var webId=document.getElementById("webId").value;
	var yjNum=document.getElementById("yjNum1").value;
	if(yjNum.indexOf("'")>-1){
		alert("警察警号输入有误，不能包含特殊符号");
		return false;
	}
	yjName1=encodeURI(encodeURI(yjNum));
	$.ajax({
	      type:"POST",
	      url:"yjMessage.do",
	      data:"method=updateSaveyjMessage1&webId="+webId+"&yjNum="+yjNum,
	      dataType:"json",
	      success:callback2,
	      error:callback1
	});
	
}
function delYjMessage(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var webId1=document.getElementsByName("webId");
	var webId=webId1[tr.rowIndex-2].value;
	if(window.confirm("确认删除记录?")==true){
		document.forms[0].action="yjMessage.do?method=delYjMessage&webId="+webId;
	    document.forms[0].submit();
	}
}
function callback2(data){
	if(data[0]==1){
		alert("警察亲情电话账号已存在，请重新输入");
		return false;
	}else{
		document.forms[0].action="yjMessage.do?method=search";
		document.forms[0].submit();
	}
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
		      url:"yjMessage.do",
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
	var jy=document.getElementById("jyId");
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
		      url:"yjMessage.do",
		      data:"method=checkJq&jy1="+jy1,
		      dataType:"json",
		      success:callback8,
		      error:callback1
		});
	}else{
		document.getElementById("jqId").disabled=true;
		var jqId=document.getElementById("jqId");
		jqId.options.length=0;
		jqId.add(new  Option("全部","null"));
	}
}
function callback8(data){
	document.getElementById("jqId").disabled=false;
	var jq=document.getElementById("jqId");
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
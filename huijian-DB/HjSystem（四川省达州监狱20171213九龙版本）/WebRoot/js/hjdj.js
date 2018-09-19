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
function goHjdj(){
	document.forms[0].action=basePath+"hjdj.do?method=checkFr";
	document.forms[0].submit();
}
function chaxun(){
	var photoAddress=document.getElementById("photoAddress").value;
	var qsName=document.getElementById("qsName").value;
	var qssfz=document.getElementById("qssfz").value;
	var frName=document.getElementById("frName").value;
	var jq=document.getElementById("jq");
	qsName=qsName.replace(/\s+$/g,"");
	qssfz=qssfz.replace(/\s+$/g,"");
	frName=frName.replace(/\s+$/g,"");
	var jq1="";
	for(var i=0;i<jq.length;i++){
		if(jq.options[i].selected){
			jq1=jq.options[i].value;
			break;
		}
	}
	if(qsName.length<=0 && qssfz.length<=0 && frName.length<=0 && jq1=='null'){
		alert("请输入任意查询条件");
		return false;
	}
	if(photoAddress==""){
		document.forms[0].action="hjdj.do?method=checkFr";
		document.forms[0].submit();
	}else{
		if(qsName.indexOf("'")>-1){
			alert("亲属姓名输入有误，不能包含特殊符号");
			return false;
		}
		if(qssfz.indexOf("'")>-1){
			alert("证件号码输入有误，不能包含特殊符号");
			return false;
		}
		if(frName.indexOf("'")>-1){
			alert("罪犯姓名输入有误，不能包含特殊符号");
			return false;
		}
		var jq=document.getElementById("jq");
		var jq1;
		for(var i=0;i<jq.length;i++){
			if(jq.options[i].selected){
				jq1=jq.options[i].value;
				break;
			}
		}
		qsName=encodeURI(encodeURI(qsName));
		frName=encodeURI(encodeURI(frName));
		qssfz=encodeURI(encodeURI(qssfz));
		$.ajax({
		      type:"POST",
		      url:"hjdj.do",
		      data:"method=checkJqueryFr&qsName="+qsName+"&qsSfz="+qssfz+"&frName="+frName+"&jq="+jq1,
		      dataType:"json",
		      success:callback5
		});
	}
}
function callback5(data){
	var frTable=document.getElementById("frTable");
	frTable.innerText="";
	for(var i=0;i<data.length;i++){
		//var tr=frTable.insertRow();
		//tr.setAttribute("onclick","checkQs("+data[i].frNo+");");
		//tr.setAttribute("ondblclick","goQsMain("+data[i].frNo+");");
		//checkQs(data[i].frNo);
		//tr.attachEvent('onclick',checkQs("+data[i].frNo+"));
		//var td1=tr.insertCell();
		//td1.innerText=data[i].jqName;
		//var td2=tr.insertCell();
		//td2.innerText=data[i].frNo;
		//var td3=tr.insertCell();
		//td3.innerText=data[i].frName;
		//var td5=tr.insertCell();
		//td5.innerText=data[i].jbName;
		//var td=tr.insertCell();
		//td.innerText=data[i].infoRjsj;
		//var td8=tr.insertCell();
		//td8.innerText=data[i].infoZdzf;
		//var td6=tr.insertCell();
		//td6.innerText=data[i].hjUse;
		//var td7=tr.insertCell();
		//td7.innerHTML=data[i].hjLastTime+"<input type=\"hidden\" name=\"monitorFlag\"  value=\""+data[i].monitorFlag+"\"><input type=\"hidden\" name=\"frNo1\" value=\""+data[i].frNo+"\">";
		//var td4=tr.insertCell();
		//td4.innerText=data[i].infoJg;
		//var frNo=data[i].frNo;
		//tr.onclick=ckQs;
		//tr.ondblclick=gqMain;
		var tr=frTable.insertRow();
		//tr.setAttribute("onclick","checkQs("+data[i].frNo+");");
		//tr.setAttribute("ondblclick","goQsMain("+data[i].frNo+");");
		//checkQs(data[i].frNo);
		//tr.attachEvent('onclick',checkQs("+data[i].frNo+"));
		var td1=tr.insertCell();
		td1.innerText=data[i].jqName;
		
		var td3=tr.insertCell();
		if(data[i].jbName=='严管级' && data[i].monitorFlag=='1'){
			td3.innerHTML="<font color=\"blue\">"+data[i].frName+"<br>"+data[i].frNo+"</font>";
		}else if(data[i].monitorFlag=='1' && data[i].jbName!='严管级'){
				td3.innerHTML="<font color=\"green\">"+data[i].frName+"<br>"+data[i].frNo+"</font>";
		}else if(data[i].jbName=='严管级' && data[i].monitorFlag!='1'){
			td3.innerHTML="<font color=\"red\">"+data[i].frName+"<br>"+data[i].frNo+"</font>";
		}else{
			td3.innerHTML=data[i].frName+"<br>"+data[i].frNo;
		}
		if(data[i].hjUse<1){
			var td2=tr.insertCell();
			td2.innerHTML=data[i].hjUse+"<br>"+data[i].hjLastTime+"<input type=\"hidden\" name=\"frNo1\" value=\""+data[i].frNo+"\">";
//			var td7=tr.insertCell();
//			td7.innerHTML=data[i].hjLastTime+"<input type=\"hidden\" name=\"frNo1\" value=\""+data[i].frNo+"\">";
		}else{
			var td2=tr.insertCell();
			td2.innerHTML="<font color=\"red\">"+data[i].hjUse+"</font><br>"+"<font color=\"red\">"+data[i].hjLastTime+"<input type=\"hidden\" name=\"frNo1\" value=\""+data[i].frNo+"\">";
//			var td7=tr.insertCell();
//			td7.innerHTML="<font color=\"red\">"+data[i].hjLastTime+"<input type=\"hidden\" name=\"frNo1\" value=\""+data[i].frNo+"\">";
		}
		var td7=tr.insertCell();
		td7.innerText=data[i].qsName;
		var td4=tr.insertCell();
		td4.innerText=data[i].infoRjsj;
		var td5=tr.insertCell();
		td5.innerText=data[i].jbName;
		var td6=tr.insertCell();
		td6.innerText=data[i].infoXq;
		var td=tr.insertCell();
		td.innerText=data[i].infoZm;
		var td8=tr.insertCell();
		td8.innerText=data[i].infoHome;
		var td9=tr.insertCell();
		td9.innerHTML="<font color=\"red\">"+data[i].formerJQName+"</font>";
		
		var td10=tr.insertCell();
		if(data[i].stateZdzf=='1'){
			td10.innerHTML="<font color=\"red\">是</font>";
		}else{
			td10.innerHTML="否";
		}
		var td11=tr.insertCell();
		td11.innerHTML="<font color=\"red\">"+data[i].zdzfType+"</font>";
		var td12=tr.insertCell();
		td12.innerText=data[i].frGj;
		var hjst=data[i].hjStopTime.substring(0,10);
		var td13=tr.insertCell();
		if(data[i].hjJb=='-1'){
			td13.innerHTML="<font color=\"red\">是<br>"+hjst+"</font>";
		}else{
			td13.innerHTML="否";
		}
		var frNo=data[i].frNo;
		tr.onclick=ckQs;
		tr.ondblclick=gqMain;
		
	}
	for(var i=0;i<5-data.length;i++){
		var tr=frTable.insertRow();
		var td=tr.insertCell();
		td.innerText="";
		var td1=tr.insertCell();
		td1.innerText="";
		var td2=tr.insertCell();
		td2.innerText="";
		var td3=tr.insertCell();
		td3.innerText="";
		var td4=tr.insertCell();
		td4.innerText="";
		var td5=tr.insertCell();
		td5.innerText="";
		var td6=tr.insertCell();
		td6.innerText="";
		var td7=tr.insertCell();
		td7.innerText="";
		var td8=tr.insertCell();
		td8.innerText="";
		var td9=tr.insertCell();
		td9.innerText="";
		var td10=tr.insertCell();
		td10.innerText="";
		var td11=tr.insertCell();
		td11.innerText="";
		var td12=tr.insertCell();
		td12.innerText="";
		var td13=tr.insertCell();
		td13.innerText="";
		
	}
	var table=document.getElementById("qsTable");
	table.innerText="";
	for(var k=0; k<10; k++){
		 var nextRow = table.insertRow(k);
		 var cell0 = nextRow.insertCell(); 
		 cell0.innerText="";
		 var cell1 = nextRow.insertCell(); 
		 cell1.innerText="";
		 var cell2 = nextRow.insertCell(); 
		 cell2.innerText="";
		 var cell3 = nextRow.insertCell(); 
		 cell3.innerText="";
		 var cell4 = nextRow.insertCell(); 
		 cell4.innerText="";
		 var cell5 = nextRow.insertCell(); 
		 cell5.innerText="";
		 var cell6 = nextRow.insertCell(); 
		 cell6.innerText="";
		 var cell7 = nextRow.insertCell(); 
		 cell7.innerText="";
		 var cell8 = nextRow.insertCell(); 
		 cell8.innerText="";
		 var cell9 = nextRow.insertCell(); 
		 cell9.innerText="";
		 var cell10 = nextRow.insertCell(); 
		 cell10.innerText="";
		 var cell11 = nextRow.insertCell(); 
		 cell11.innerText="";
		 var cell12 = nextRow.insertCell(); 
		 cell12.innerText="";
	 }
}
function ckQs(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frNo2=document.getElementsByName("frNo1");
	checkQs(frNo2[tr.rowIndex-1].value);
}
function checkQs(frNo){
	var selectRow=document.getElementById("selectRow").value;
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	if(selectRow==-1){
		tr.style.background ='#EEEE00';
		document.getElementById("selectRow").value=tr.rowIndex;
	}else{
		var frTable=document.getElementById("frTable");
		frTable.rows[selectRow-1].style.background="#FFFFFF";
		tr.style.background ='#EEEE00';
		document.getElementById("selectRow").value=tr.rowIndex;
	}
//	var monitorFlag = document.getElementsByName("monitorFlag")[tr.rowIndex-1].value;
//	if(monitorFlag=='' || monitorFlag=='0'){
//		document.getElementById("info1").innerHTML="<input type=\"checkbox\" name=\"isMonitor\" value=\""+monitorFlag+"\"/>&nbsp;&nbsp;临时重点监控";
//	}else{
//		document.getElementById("info1").innerHTML="<input type=\"checkbox\" name=\"isMonitor\" value=\""+monitorFlag+"\" checked=\"checked\" disabled=\"disabled\"/>&nbsp;&nbsp;重点监控";
//	}
	document.getElementById("selectFrId").value=frNo;
	frNo=encodeURI(encodeURI(frNo));
	$.ajax({
	      type:"POST",
	      url:"hjdj.do",
	      data:"method=checkFrQs&frNo="+frNo,
	      dataType:"json",
	      success:callback2
	});
}
function callback2(data){
	 var table=document.getElementById("qsTable");
	 table.innerText="";
	 var list=data[0].list;
	 for(var i=0;i<list.length;i++){
		 var nextRow = table.insertRow(i);
		 //nextRow.setAttribute("onclick","checkHj()");
		 nextRow.onclick=checkHj;
		 nextRow.ondblclick=updateFrQs1;
		 var cell0 = nextRow.insertCell(); 
		 cell0.innerHTML="<input type=\"checkbox\" id=\"isNo\" name=\"isNo\" value=\""+list[i].webId+"\"  disabled=\"disabled\"/>";
		 var cell1 = nextRow.insertCell(); 
		 cell1.innerText=list[i].qsName;
		 var cell2 = nextRow.insertCell(); 
		 cell2.innerText=list[i].gx;
		 if(list[i].qsZjlb==1){
			 var cell3 = nextRow.insertCell(); 
			 cell3.innerHTML="身份证";
		 }
		 if(list[i].qsZjlb==2){
			 var cell3 = nextRow.insertCell(); 
			 cell3.innerHTML="警官证";
		 }
		 if(list[i].qsZjlb==3){
			 var cell3 = nextRow.insertCell(); 
			 cell3.innerHTML="工作证";
		 }
		 if(list[i].qsZjlb==4){
			 var cell3 = nextRow.insertCell(); 
			 cell3.innerHTML="其他";
		 }
		 var cell4 = nextRow.insertCell(); 
		 cell4.innerText=list[i].qsSfz;
		 var cell5 = nextRow.insertCell(); 
		 cell5.innerText=list[i].qsSfzWlh;
		 var cell6 = nextRow.insertCell(); 
		 cell6.innerText=list[i].qsCard;
		 var cell7 = nextRow.insertCell(); 
		 cell7.innerText=list[i].xb;
		 var cell8 = nextRow.insertCell(); 
		 cell8.innerText=list[i].dz;
		 var cell9 = nextRow.insertCell(); 
		 cell9.innerText=list[i].tele;
		 var cell10 = nextRow.insertCell(); 
		 cell10.innerText=list[i].bz;
		 if(list[i].spState==1){
			 var cell11 = nextRow.insertCell(); 
			 cell11.innerHTML="已通过";
		 }
		 if(list[i].spState==0){
			 var cell11 = nextRow.insertCell(); 
			 cell11.innerHTML="<font color=\"red\">未通过</font>";
		 }
		 var cell12 = nextRow.insertCell(); 
		 if(list[i].hjStopTime!=''){
			 cell12.innerHTML="<font color=\"red\">是<br>"+list[i].hjStopTime+"</font>";
		 }else{ 
			 cell12.innerHTML="否";
		 }
//		 if(list[i].faceState==0){
//			 var cell9 = nextRow.insertCell(); 
//			 cell9.innerHTML="<font color=\"red\">未注册</font>";
//		 }
//		 if(list[i].faceState==1){
//			 var cell9 = nextRow.insertCell(); 
//			 cell9.innerHTML="已注册";
//		 }
//		 if(list[i].faceState==2){
//			 var cell9 = nextRow.insertCell(); 
//			 cell9.innerHTML="<font color=\"red\">中途取消注册</font>";
//		 }
//		 if(list[i].faceState==3){
//			 var cell9 = nextRow.insertCell(); 
//			 cell9.innerHTML="已注册";
//		 }
//		 if(list[i].faceState==4){
//			 var cell9 = nextRow.insertCell(); 
//			 cell9.innerHTML="<font color=\"red\">注册超时</font>";
//		 }
		 
	 }
	 if(list.length<10){
		 for(var k=list.length; k<10; k++){
			 var nextRow = table.insertRow(k);
			 var cell0 = nextRow.insertCell(); 
			 cell0.innerText="";
			 var cell1 = nextRow.insertCell(); 
			 cell1.innerText="";
			 var cell2 = nextRow.insertCell(); 
			 cell2.innerText="";
			 var cell3 = nextRow.insertCell(); 
			 cell3.innerText="";
			 var cell4 = nextRow.insertCell(); 
			 cell4.innerText="";
			 var cell5 = nextRow.insertCell(); 
			 cell5.innerText="";
			 var cell6 = nextRow.insertCell(); 
			 cell6.innerText="";
			 var cell7 = nextRow.insertCell(); 
			 cell7.innerText="";
			 var cell8 = nextRow.insertCell(); 
			 cell8.innerText="";
			 var cell9 = nextRow.insertCell(); 
			 cell9.innerText="";
			 var cell10 = nextRow.insertCell(); 
			 cell10.innerText="";
			 var cell11 = nextRow.insertCell(); 
			 cell11.innerText="";
			 var cell12 = nextRow.insertCell(); 
			 cell12.innerText="";
		 }
	 }
	 if(data[0].enter==1){
		 document.getElementById("hjsc").value=data[0].hjTime;
	 }else{
		 document.getElementById("hjsc").value=data[0].hjTime;
		 document.getElementById("hjsc").disabled=true;
	 }
	
}
function checkHj(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var isNo=document.getElementsByName("isNo");
	if(isNo[tr.rowIndex-1].checked==true){
		isNo[tr.rowIndex-1].checked=false;
		tr.style.background ='#FFFFFF';
	}else{
		isNo[tr.rowIndex-1].checked=true;
		tr.style.background ='#EEEE00';
	}
	
}
function gqMain(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frNo2=document.getElementsByName("frNo1");
	goQsMain(frNo2[tr.rowIndex-1].value);
}

function goQsMain(frNo){
	frNo=encodeURI(encodeURI(frNo));
	window.location.href("hjdj.do?method=checkQs&frNo="+frNo);
}
function addFrQs(){
	var frNo=document.getElementById("frNo").value;
	document.forms[0].action="hjdj.do?method=addQs&frNo="+frNo;
	document.forms[0].submit();
}

function clearpicture(){
	document.getElementById("MyFlexApps").clearpicture();
}
function setWidth(){
	return 130;//设置宽度
}
function setHeight(){
	return 136;//设置高度
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
 	IDCard2.SetPhotoType(1);
 	IDCard2.SetPhotoName(2);
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
function shibie2(){
 	var IDCard2=document.getElementById("IDCard2");
 	var str = IDCard2.FindReader();
  	if (str <= 0){
  		alert("没有找到读卡器");
  		return false;
  	}
 	IDCard2.SetPhotoType(1);
 	IDCard2.SetPhotoName(2);
 	var aa=IDCard2.ReadCardMsg();
	if(aa==0){
		document.getElementById("qsName11").value=IDCard2.NameA;
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
	var frName1=document.getElementById("frName1").value;
	var jqNo1=document.getElementById("jqNo1").value;
	var cardTypes=document.getElementsByName("cardType");
	var qsSfzWlh=document.getElementById("qsSfzWlh").value;
	var bz=document.getElementById("bz").value;
	var flag=checkRate(qsSfz);
	if(!flag){
		alert("证件号码只能是数字和英文组合");
		return false;
	}
	if(qsCard.length>10){
		alert("卡号长度有误");
		return false;
	}
//	qsSfzWlh1=qsSfzWlh.substring(6,7);
//	alert(qsSfzWlh1);
//	qsSfzWlh2=qsSfzWlh.substring(3,5);
//	qsSfzWlh3=qsSfzWlh.substring(6,5);
//	qsSfzWlh4=qsSfzWlh.substring(6,7);
//	qsSfzWlh=qsSfzWlh4+qsSfzWlh3+qsSfzWlh2+qsSfzWlh1;
//	alert(qsSfzWlh);
	qsSfz=qsSfz.replace(/\s+$/g,"");
	var gx=document.getElementById("gx").value;
	gx=gx.replace(/\s+$/g,"");
//	var gx1;
//	var gx3;
//	for(var i=0;i<gx.length;i++){
//		if(gx.options[i].selected){
//			gx3=gx.options[i].value;
//			break;
//		}
//	}
//	var gx2=document.getElementById("gx2").value;
//	gx2=gx2.replace(/\s+$/g,"");
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
	var cardType=0;
	for(var i=0;i<cardTypes.length;i++){
		if(cardTypes[i].checked){
			cardType=cardTypes[i].value;
			break;
		}
	}
	if(qsSfz==''){
		alert("证件号码不能为空");
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
	      url:"hjdj.do?method=addSaveFrQs",
	      data : {
				frNo:frNo1,
				frName1:frName1,
				qsSfz:qsSfz,
				qsName:qsName,
				qsCard:qsCard,
				dz:dz,
				tele:tele,
				gx:gx,
				xb:xb1,
				zjlb:zjlb1,
				spState:spState1,
				jqNo1:jqNo1,
				cardType:cardType,
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
	if(data[0]==-1){
		alert("罪犯亲属证件号码不能重复");
		return false;
//	}else if(data[0]>0){
//		var frNo1=document.getElementById("frNo1").value;
//		document.forms[0].action="hjdj.do?method=spcj&spqsId="+data[0]+"&frNo2="+frNo1;
//		document.forms[0].submit();
	}else{
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}
}
function callback1(){
 	alert(a);
 	alert(b);	
 	alert(c);
}
function delFrQs(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frNo=tr.cells[12].children[2].value;
	var webId=tr.cells[12].children[3].value;
	if(window.confirm("确认删除记录？")==true){
		document.forms[0].action="hjdj.do?method=delFrQs&frNo="+frNo+"&webId="+webId;
		document.forms[0].submit();
	}
}
function updateFrQs(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frNo=tr.cells[12].children[2].value;
	var webId=tr.cells[12].children[3].value;
	document.forms[0].action="hjdj.do?method=updateFrQs&webId="+webId+"&frNo="+frNo;
	document.forms[0].submit();
}
function updateshibie(){
	var IDCard2=document.getElementById("IDCard2");
	var aa=IDCard2.Syn_ReadMsg(1);
	if(aa==0){
		IDCard2.SetPhotoName(2);
		document.getElementById("qsName").value=IDCard2.PeopleName;
		document.getElementById("qsSfz").value=IDCard2.IDCardNo;
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
		var zp=IDCard2.PhotoStr;
		var zpAddress=IDCard2.StrToJpg(zp);
		document.getElementById("photoAddress").value=zpAddress;
	}
}
function updateshibie1(){
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
	var frName1=document.getElementById("frName1").value;
	var jqNo1=document.getElementById("jqNo1").value;
	var cardTypes=document.getElementsByName("cardType");
	var photoAddress=document.getElementById("photoAddress").value;
	var jz=document.getElementById("jz").value;
	var qsSfzWlh=document.getElementById("qsSfzWlh").value;
	var bz=document.getElementById("bz").value;
	var flag=checkRate(qsSfz);
	if(!flag){
		alert("证件号码只能是数字和英文组合");
		return false;
	}
	if(qsCard.length>10){
		alert("卡号长度有误");
		return false;
	}
//	var gx1;
//	for(var i=0;i<gx.length;i++){
//		if(gx.options[i].selected){
//			gx1=gx.options[i].value;
//			break;
//		}
//	}
	gx=gx.replace(/\s+$/g,"");
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
	var cardType=0;
	for(var i=0;i<cardTypes.length;i++){
		if(cardTypes[i].checked){
			cardType=cardTypes[i].value;
			break;
		}
	}
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
//	dz=encodeURI(encodeURI(dz));
//	qsName=encodeURI(encodeURI(qsName));
//	qsCard=encodeURI(encodeURI(qsCard));
//	tele=encodeURI(encodeURI(tele));
//	gx1=encodeURI(encodeURI(gx1));
//	xb1=encodeURI(encodeURI(xb1));
//	sw1=encodeURI(encodeURI(sw1));
	qsName=qsName.replace(/\s+$/g,"");
	dz=dz.replace(/\s+$/g,"");
	qsCard=qsCard.replace(/\s+$/g,"");
	tele=tele.replace(/\s+$/g,"");
	qsSfzWlh=qsSfzWlh.replace(/\s+$/g,"");
	bz=bz.replace(/\s+$/g,"");
	$.ajax({
	      type:"POST",
	      url:"hjdj.do?method=updateSaveFrQs",
	      // data:"method=updateSaveFrQs&qsId="+qsId+"&qsName="+qsName+"&qsCard="+qsCard+"&dz="+dz+"&tele="+tele+"&gx="+gx1+"&xb="+xb1+"&sw="+sw1+"&qsSfz="+qsSfz+"&photoAddress="+photoAddress,
	  	  data : {
			qsId:qsId,
			frName1:frName1,
			qsName:qsName,
			qsCard:qsCard,
			dz:dz,
			tele:tele,
			gx:gx,
			xb:xb1,
			spState:spState1,
			zjlb:zjlb1,
			qsSfz:qsSfz,
			jqNo1:jqNo1,
			cardType:cardType,
			photoAddress:photoAddress,
			qsSfzWlh:qsSfzWlh,
			bz:bz,
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
function addHjdjSb(){
	var IDCard2=document.getElementById("IDCard2");
	var aa=IDCard2.Syn_ReadMsg(1);
	if(aa==0){
		IDCard2.SetPhotoName(2);
		document.getElementById("qsName").value=IDCard2.PeopleName;
		document.getElementById("qssfz").value=IDCard2.IDCardNo;
		var zp=IDCard2.PhotoStr;
		var zpAddress=IDCard2.StrToJpg(zp);
		document.getElementById("photoAddress").value=zpAddress;
	}
}
function addHjdjSb1(){
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
		document.getElementById("qssfz").value=IDCard2.CardNo;
		var zpAddress=IDCard2.PhotoName;
		document.getElementById("photoAddress").value=zpAddress;
		document.getElementById("sfzz").src=zpAddress;
		chaxun();
	}
}
function addHjdj(){
	document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>处理中，请稍后</b>";
	document.getElementById("load").style.display="block";
	var selectFrId=document.getElementById("selectFrId").value;
	var isPrint=document.getElementById("isPrint").value;
	var isNo=document.getElementsByName("isNo");
	var tpQs = document.getElementById("tpQs");
	var tpQs1 ="";
	for(var i=0;i<tpQs.length;i++){
		if(tpQs.options[i].selected){
			tpQs1=tpQs.options[i].value;
			break;
		}
	}
	var date = new Date();
	if(selectFrId==""){
		alert("请鼠标左键单击选中要参加会见的罪犯信息");
		document.getElementById("load").style.display="none";
		return false;
	}
	var flag=false;
	var k=1;
	var isNo1="";
	for(var i=0;i<isNo.length;i++){
		if(isNo[i].checked){
			if(k==1){
				isNo1+=isNo[i].value;
				k++;
			}else{
				isNo1+=","+isNo[i].value;
			}
			flag=true;
		}
	}
	if(tpQs1==0){
		if(!flag){
			alert("请鼠标左键单击选中该罪犯要参加会见的亲属信息");
			document.getElementById("load").style.display="none";
			return false;
		}
	}
	
	isNo1=encodeURI(encodeURI(isNo1));
	var hjType=document.getElementById("hjType");
	var hjType1="";
	for(var i=0;i<hjType.length;i++){
		if(hjType.options[i].selected){
			hjType1=hjType.options[i].value;
			break;
		}
	}
	
	var qzSp=document.getElementById("qzSp");
	var qzSp1="";
	if(qzSp.checked){
		qzSp1="1";
	}else{
		qzSp1="0";
	}
//	var monitor="";
//	if(document.getElementsByName("isMonitor")[0].checked){
//		monitor="1";
//	}
	var hjsc=document.getElementById("hjsc").value;
	var patrn=/^-?\d+$/;
	if (!patrn.test(hjsc)){ 
		alert("会见时长包含非法字符,请输入整数");
		document.getElementById("load").style.display="none";
		return false;
	}
	var hjsm=document.getElementById("hjsm").value;
	var callNo=document.getElementById("callNo").value;
	if(hjsm.indexOf("'")>-1){
		alert("会见说明输入有误，不能包含特殊符号");
		document.getElementById("load").style.display="none";
		return false;
	}
	hjsm=encodeURI(encodeURI(hjsm));
//	monitor=encodeURI(encodeURI(monitor));
	if(isPrint==0){
		$.ajax({
		      type:"POST",
		      url:basePath+"hjdj.do",
		      //data:"method=addSaveHjdj&selectFrId="+selectFrId+"&isNo="+isNo1+"&hjsc="+hjsc+"&hjsm="+hjsm+"&hjsc="+hjsc+"&hjType="+hjType1+"monitor="+monitor+"&callNo="+callNo+"&tpQs="+tpQs1+"&qzSp="+qzSp1,
		      data:"method=addSaveHjdj&selectFrId="+selectFrId+"&isNo="+isNo1+"&hjsc="+hjsc+"&hjsm="+hjsm+"&hjsc="+hjsc+"&hjType="+hjType1+"&callNo="+callNo+"&tpQs="+tpQs1+"&qzSp="+qzSp1,
		      dataType:"json",
		      success:callback,
		      error:callback1
		});
	}else{
		$.ajax({
		      type:"POST",
		      url:basePath+"hjdj.do",
		      //data:"method=addSaveHjdj&selectFrId="+selectFrId+"&isNo="+isNo1+"&hjsc="+hjsc+"&hjsm="+hjsm+"&hjsc="+hjsc+"&hjType="+hjType1+"monitor="+monitor+"&callNo="+callNo+"&tpQs="+tpQs1+"&qzSp="+qzSp1,
		      data:"method=addSaveHjdj&selectFrId="+selectFrId+"&isNo="+isNo1+"&hjsc="+hjsc+"&hjsm="+hjsm+"&hjsc="+hjsc+"&hjType="+hjType1+"&callNo="+callNo+"&tpQs="+tpQs1+"&qzSp="+qzSp1,
		      dataType:"json",
		      success:callback9,
		      error:callback1
		});
	}
	
}
function addHjdj1(){
	document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>处理中，请稍后</b>";
	document.getElementById("load").style.display="block";
	var selectFrId=document.getElementById("selectFrId").value;
	var isPrint=document.getElementById("isPrint").value;
	var isNo=document.getElementsByName("isNo");
	var tpQs = document.getElementById("tpQs");
	var tpQs1 ="";
	for(var i=0;i<tpQs.length;i++){
		if(tpQs.options[i].selected){
			tpQs1=tpQs.options[i].value;
			break;
		}
	}
	var date = new Date();
	if(selectFrId==""){
		alert("请鼠标左键单击选中要参加会见的罪犯信息");
		document.getElementById("load").style.display="none";
		return false;
	}
	var flag=false;
	var k=1;
	var isNo1="";
	for(var i=0;i<isNo.length;i++){
		if(isNo[i].checked){
			if(k==1){
				isNo1+=isNo[i].value;
				k++;
			}else{
				isNo1+=","+isNo[i].value;
			}
			flag=true;
		}
	}
	if(tpQs1==0){
		if(!flag){
			alert("请鼠标左键单击选中该罪犯要参加会见的亲属信息");
			document.getElementById("load").style.display="none";
			return false;
		}
	}
	
	isNo1=encodeURI(encodeURI(isNo1));
	var hjType=document.getElementById("hjType");
	var hjType1="";
	for(var i=0;i<hjType.length;i++){
		if(hjType.options[i].selected){
			hjType1=hjType.options[i].value;
			break;
		}
	}
	
	var qzSp=document.getElementById("qzSp");
	var qzSp1="";
	if(qzSp.checked){
		qzSp1="1";
	}else{
		qzSp1="0";
	}
//	var monitor="";
//	if(document.getElementsByName("isMonitor")[0].checked){
//		monitor="1";
//	}
	var hjsc=document.getElementById("hjsc").value;
	var patrn=/^-?\d+$/;
	if (!patrn.test(hjsc)){ 
		alert("会见时长包含非法字符,请输入整数");
		document.getElementById("load").style.display="none";
		return false;
	}
	var hjsm=document.getElementById("hjsm").value;
	var callNo=document.getElementById("callNo").value;
	if(hjsm.indexOf("'")>-1){
		alert("会见说明输入有误，不能包含特殊符号");
		document.getElementById("load").style.display="none";
		return false;
	}
	hjsm=encodeURI(encodeURI(hjsm));
//	monitor=encodeURI(encodeURI(monitor));
	if(isPrint==0){
		$.ajax({
		      type:"POST",
		      url:basePath+"hjdj.do",
		      //data:"method=addSaveHjdj&selectFrId="+selectFrId+"&isNo="+isNo1+"&hjsc="+hjsc+"&hjsm="+hjsm+"&hjsc="+hjsc+"&hjType="+hjType1+"monitor="+monitor+"&callNo="+callNo+"&tpQs="+tpQs1+"&qzSp="+qzSp1,
		      data:"method=addSaveHjdj123&selectFrId="+selectFrId+"&isNo="+isNo1+"&hjsc="+hjsc+"&hjsm="+hjsm+"&hjsc="+hjsc+"&hjType="+hjType1+"&callNo="+callNo+"&tpQs="+tpQs1+"&qzSp="+qzSp1,
		      dataType:"json",
		      success:callback919,
		      error:callback1
		});
	}else{
		$.ajax({
		      type:"POST",
		      url:basePath+"hjdj.do",
		      //data:"method=addSaveHjdj&selectFrId="+selectFrId+"&isNo="+isNo1+"&hjsc="+hjsc+"&hjsm="+hjsm+"&hjsc="+hjsc+"&hjType="+hjType1+"monitor="+monitor+"&callNo="+callNo+"&tpQs="+tpQs1+"&qzSp="+qzSp1,
		      data:"method=addSaveHjdj123&selectFrId="+selectFrId+"&isNo="+isNo1+"&hjsc="+hjsc+"&hjsm="+hjsm+"&hjsc="+hjsc+"&hjType="+hjType1+"&callNo="+callNo+"&tpQs="+tpQs1+"&qzSp="+qzSp1,
		      dataType:"json",
		      success:callback919,
		      error:callback1
		});
	}
	
}
function callback(data){
	document.getElementById("load").style.display="none";
	if(data[0]==0){
		alert("该罪犯已经被登记或者被提交审批，请核实今天已经登记或审批过的记录");
		return false;
	}else if(data[0]==-2){
		alert("当前所有会见线路已经被分配，已经没有空闲座位，请稍后");
		document.forms[0].action=basePath+"hjdj.do?method=checkFr1";
		document.forms[0].submit();
	}else if(data[0]==-3){
		alert("该罪犯本月会见次数为0，如需会见请修改罪犯会见剩余次数");
		return false;
	}else if(data[0]==-4){
		alert("该罪犯会见级别被禁止");
		return false;
	}else if(data[0]==-5){
		alert("参加会见的亲属可能未绑定“证件号码”，请核实后再次提交登记");
		return false;
	}else if(data[0]==-7){
		alert("系统检测到有参加会见的亲属“亲属关系”栏为空，请核实后再次提交登记");
		return false;
	}else if(data[0]==-8){
		alert("系统检测到有参加会见的亲属未审核，请核实后再次提交登记");
		return false;
	}else if(data[0]==-9){
		alert("无法与门禁服务器连接，请检查网络");
		return false;
	}else if(data[0]==-10){
		alert("向门禁服务器提交数据时出现错误");
		return false;
	}else if(data[0]==-11){
		alert("目前系统只支持会见类型是“考察”的审批");
		return false;
	}else if(data[0].zzz==-6){
		if(window.confirm(data[0].zzzReason+"：是否确认提交审批？")==true){
			document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
			
		}else{
			alert("您取消了此次会见登记");
			document.forms[0].action=basePath+"hjdj.do?method=checkFr&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
		}
		
		
	}else{
		alert("提交登记成功");
		var a = document.getElementById("qssfz");
		var b = document.getElementById("qsName");
		var c = document.getElementById("frName");
		var d = document.getElementById("hjsm");
		a.value= "";
		b.value= "";
		c.value= "";
		d.value= "";
		document.forms[0].action=basePath+"hjdj.do?method=checkFr1";
		
		document.forms[0].submit();
		//document.getElementsByName("qssfz");
		
		
	}
}
function callback9(data){
	document.getElementById("load").style.display="none";
//	return false;
	if(data[0]==0){
		alert("该罪犯已经被登记或者被提交审批，请核实今天已经登记或审批过的记录");
		return false;
	}else if(data[0]==-2){
		alert("当前所有会见线路已经被分配，已经没有空闲座位，请稍后");
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else if(data[0]==-3){
		alert("该罪犯本月会见次数为0，如需会见请修改罪犯会见剩余次数");
		return false;
	}else if(data[0]==-4){
		alert("该罪犯会见级别被禁止");
		return false;
	}else if(data[0]==-5){
		alert("参加会见的亲属可能未绑定“证件号码”，请核实后再次提交登记");
		return false;
	}else if(data[0]==-7){
		alert("系统检测到有参加会见的亲属“亲属关系”栏为空，请核实后再次提交登记");
		return false;
	}else if(data[0]==-8){
		alert("系统检测到有参加会见的亲属未审核，请核实后再次提交登记");
		return false;
	}else if(data[0]==-9){
		alert("无法与门禁服务器连接，请检查网络");
		return false;
	}else if(data[0]==-10){
		alert("向门禁服务器提交数据时出现错误");
		return false;
	}else if(data[0]==-11){
		alert("目前系统只支持会见类型是“考察”的审批");
		return false;
	}else if(data[0].zzz==-6){
		if(window.confirm(data[0].zzzReason+"：是否确认提交审批？")==true){
			document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
			
		}else{
			alert("您取消了此次会见登记");
			document.forms[0].action=basePath+"hjdj.do?method=checkFr&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
		}
		
		
	}else{
		alert("提交登记成功");
		var a = document.getElementById("qssfz");
		var b = document.getElementById("qsName");
		var c = document.getElementById("frName");
		var d = document.getElementById("hjsm");
		a.value= "";
		b.value= "";
		c.value= "";
		d.value= "";
		var selectFrId=document.getElementById("selectFrId").value;
//		document.forms[0].action=basePath+"hjdj.do?method=printXp&webId1="+selectFrId;
//		document.forms[0].submit();
		
//		document.forms[0].action=basePath+"hjdj.do?method=search";
//		document.forms[0].submit();
		
		var url=basePath+"hjdj.do?method=printXp&webId1="+selectFrId;
		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		document.forms[0].action=basePath+"hjdj.do?method=checkFr1";		
		document.forms[0].submit();
	}
}
function callback919(data){
	document.getElementById("load").style.display="none";
//	return false;
	if(data[0]==0){
		alert("该罪犯已经被登记或者被提交审批，请核实今天已经登记或审批过的记录");
		return false;
	}else if(data[0]==-2){
		alert("当前所有会见线路已经被分配，已经没有空闲座位，请稍后");
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else if(data[0]==-3){
		alert("该罪犯本月会见次数为0，如需会见请修改罪犯会见剩余次数");
		return false;
	}else if(data[0]==-4){
		alert("该罪犯会见级别被禁止");
		return false;
	}else if(data[0]==-5){
		alert("参加会见的亲属可能未绑定“证件号码”或者“IC卡号”，请核实后再次提交登记");
		return false;
	}else if(data[0]==-7){
		alert("系统检测到有参加会见的亲属“亲属关系”栏为空，请核实后再次提交登记");
		return false;
	}else if(data[0]==-8){
		alert("系统检测到有参加会见的亲属未审核，请核实后再次提交登记");
		return false;
	}else if(data[0]==-9){
		alert("无法与门禁服务器连接，请检查网络");
		return false;
	}else if(data[0]==-10){
		alert("向门禁服务器提交数据时出现错误");
		return false;
	}else if(data[0]==-11){
		alert("目前系统只支持会见类型是“考察”的审批");
		return false;
	}else if(data[0].zzz==-6){
		if(window.confirm(data[0].zzzReason+"：是否确认提交审批？")==true){
			document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
			
		}else{
			alert("您取消了此次会见登记");
			document.forms[0].action=basePath+"hjdj.do?method=checkFr&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
		}
		
		
	}else{
		alert("提交登记成功");
		var a = document.getElementById("qssfz");
		var b = document.getElementById("qsName");
		var c = document.getElementById("frName");
		var d = document.getElementById("hjsm");
		a.value= "";
		b.value= "";
		c.value= "";
		d.value= "";
		var selectFrId=document.getElementById("selectFrId").value;
//		document.forms[0].action=basePath+"hjdj.do?method=printXp&webId1="+selectFrId;
//		document.forms[0].submit();
		
//		document.forms[0].action=basePath+"hjdj.do?method=search";
//		document.forms[0].submit();
		
		var url=basePath+"hjdj.do?method=printXp&webId1="+selectFrId;
		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		document.forms[0].action=basePath+"hjdj.do?method=checkFr1";		
		document.forms[0].submit();
	}
}
function callback11111(data){
	document.getElementById("load").style.display="none";
	if(data[0]==0){
		alert("该罪犯已经被登记或者被提交审批，请核实今天已经登记或审批过的记录");
		return false;
	}else if(data[0]==-2){
		alert("当前所有会见线路已经被分配，已经没有空闲座位，请稍后");
		document.forms[0].action=basePath+"hjdj.do?method=checkFr1";
		document.forms[0].submit();
	}else if(data[0]==-3){
		alert("该罪犯本月会见次数为0，如需会见请修改罪犯会见剩余次数");
		return false;
	}else if(data[0]==-4){
		alert("该罪犯会见级别被禁止");
		return false;
	}else if(data[0]==-5){
		alert("参加会见的亲属可能未绑定“证件号码”，请核实后再次提交登记");
		return false;
	}else if(data[0]==-7){
		alert("系统检测到有参加会见的亲属“亲属关系”栏为空，请核实后再次提交登记");
		return false;
	}else if(data[0]==-8){
		alert("系统检测到有参加会见的亲属未审核，请核实后再次提交登记");
		return false;
	}else if(data[0]==-9){
		alert("无法与门禁服务器连接，请检查网络");
		return false;
	}else if(data[0]==-10){
		alert("向门禁服务器提交数据时出现错误");
		return false;
	}else if(data[0]==-11){
		alert("目前系统只支持会见类型是“考察”的审批");
		return false;
	}else if(data[0].zzz==-6){
		if(window.confirm(data[0].zzzReason+"：是否确认提交审批？")==true){
			document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
			
		}else{
			alert("您取消了此次会见登记");
			document.forms[0].action=basePath+"hjdj.do?method=checkFr&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
		}
		
		
	}else{
		alert("提交登记成功");
		var a = document.getElementById("qssfz");
		var b = document.getElementById("qsName");
		var c = document.getElementById("frName");
		var d = document.getElementById("hjsm");
		a.value= "";
		b.value= "";
		c.value= "";
		d.value= "";
		if(window.confirm("是否继续向门禁系统传递家属信息？")==true){
			if(data.length==1){
//				alert("进入1="+1);
				$.ajax({
					 type:"POST",
					 url:basePath+"hjdj.do",
				     data:"method=addQsInfoToABDoor1&webId="+data[0].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
//				document.forms[0].action=basePath+"hjdj.do?method=addQsInfoToABDoor1&webId="+data[0].webId;
//				document.forms[0].submit();
//				return false;
			}
			if(data.length==2){
//				alert("进入2="+2);
				$.ajax({
					 type:"POST",
					 url:basePath+"hjdj.do",
				     data:"method=addQsInfoToABDoor2&webId="+data[0].webId+"&webId1="+data[1].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==3){
//				alert("进入3="+3);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor3&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==4){
//				alert("进入4="+4);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor4&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==5){
//				alert("进入5="+5);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor5&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==6){
//				alert("进入6="+6);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor6&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==7){
//				alert("进入7="+7);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor7&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId+"&webId6="+data[6].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==8){
//				alert("进入8="+8);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor8&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId+"&webId6="+data[6].webId+"&webId7="+data[7].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==9){
//				alert("进入9="+9);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor9&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId+"&webId6="+data[6].webId+"&webId7="+data[7].webId+"&webId8="+data[8].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			
		}
//		document.forms[0].action=basePath+"hjdj.do?method=search";
//		document.forms[0].submit();
		
		var url=basePath+"hjdj.do?method=printXp&webId1="+selectFrId;
		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		document.forms[0].action=basePath+"hjdj.do?method=checkFr1";		
		document.forms[0].submit();
	}
}
function callback99999(data){
	document.getElementById("load").style.display="none";
//	return false;
	if(data[0]==0){
		alert("该罪犯已经被登记或者被提交审批，请核实今天已经登记或审批过的记录");
		return false;
	}else if(data[0]==-2){
		alert("当前所有会见线路已经被分配，已经没有空闲座位，请稍后");
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else if(data[0]==-3){
		alert("该罪犯本月会见次数为0，如需会见请修改罪犯会见剩余次数");
		return false;
	}else if(data[0]==-4){
		alert("该罪犯会见级别被禁止");
		return false;
	}else if(data[0]==-5){
		alert("参加会见的亲属可能未绑定“证件号码”，请核实后再次提交登记");
		return false;
	}else if(data[0]==-7){
		alert("系统检测到有参加会见的亲属“亲属关系”栏为空，请核实后再次提交登记");
		return false;
	}else if(data[0]==-8){
		alert("系统检测到有参加会见的亲属未审核，请核实后再次提交登记");
		return false;
	}else if(data[0]==-9){
		alert("无法与门禁服务器连接，请检查网络");
		return false;
	}else if(data[0]==-10){
		alert("向门禁服务器提交数据时出现错误");
		return false;
	}else if(data[0]==-11){
		alert("目前系统只支持会见类型是“考察”的审批");
		return false;
	}else if(data[0].zzz==-6){
		if(window.confirm(data[0].zzzReason+"：是否确认提交审批？")==true){
			document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
			
		}else{
			alert("您取消了此次会见登记");
			document.forms[0].action=basePath+"hjdj.do?method=checkFr&hjid="+data[0].zzzhjid;
			document.forms[0].submit();
			return false;
		}
		
		
	}else{
		alert("提交登记成功");
		var a = document.getElementById("qssfz");
		var b = document.getElementById("qsName");
		var c = document.getElementById("frName");
		var d = document.getElementById("hjsm");
		a.value= "";
		b.value= "";
		c.value= "";
		d.value= "";
		var selectFrId=document.getElementById("selectFrId").value;
		if(window.confirm("是否继续向门禁系统传递家属信息？")==true){
			if(data.length==1){
//				alert("进入1="+1);
				$.ajax({
					 type:"POST",
					 url:basePath+"hjdj.do",
				     data:"method=addQsInfoToABDoor1&webId="+data[0].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
//				document.forms[0].action=basePath+"hjdj.do?method=addQsInfoToABDoor1&webId="+data[0].webId;
//				document.forms[0].submit();
//				return false;
			}
			if(data.length==2){
//				alert("进入2="+2);
				$.ajax({
					 type:"POST",
					 url:basePath+"hjdj.do",
				     data:"method=addQsInfoToABDoor2&webId="+data[0].webId+"&webId1="+data[1].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==3){
//				alert("进入3="+3);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor3&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==4){
//				alert("进入4="+4);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor4&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==5){
//				alert("进入5="+5);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor5&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==6){
//				alert("进入6="+6);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor6&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==7){
//				alert("进入7="+7);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor7&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId+"&webId6="+data[6].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==8){
//				alert("进入8="+8);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor8&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId+"&webId6="+data[6].webId+"&webId7="+data[7].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==9){
//				alert("进入9="+9);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor9&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId+"&webId6="+data[6].webId+"&webId7="+data[7].webId+"&webId8="+data[8].webId,
				     dataType:"json",
				     success:callbackAB,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			
		}
//		document.forms[0].action=basePath+"hjdj.do?method=search";
//		document.forms[0].submit();
		
//		var url=basePath+"hjdj.do?method=printXp&webId1="+selectFrId;
//		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		
	}
}
function callbackAB(data){
	if(data[0]==1){
		alert("传输成功");
		document.forms[0].action=basePath+"hjdj.do?method=checkFr1";		
		document.forms[0].submit();
	}else{
		alert("传输失败");
		document.forms[0].action=basePath+"hjdj.do?method=checkFr1";		
		document.forms[0].submit();
	}	
}
function sleep(obj,iMinSecond){
	alert("开始传输");
	if (window.eventList==null) window.eventList=new Array();  　　 
	var ind=-1;  　　 
	for (var i=0;i<window.eventList.length;i++){  　　  
		if (window.eventList[i]==null) {  　　  
			 window.eventList[i]=obj;  　　   
			 ind=i;  　　  
			 break;  　　  
		}  　　
	}  　　  　　 
	if (ind==-1){  　　 
		ind=window.eventList.length;  　　 
		window.eventList[ind]=obj;  　　 
	}     　　 
	setTimeout("goon(" + ind + ")",iMinSecond);  　　
}  　　 　　
function goon(ind){  　　
	var obj=window.eventList[ind];  　　 
	window.eventList[ind]=null;  　　 
	if (obj.NextStep) obj.NextStep();  　　 
	else obj();  　　
}
function returnBack(){
	document.forms[0].action=basePath+"hjdj.do?method=search";
	document.forms[0].submit();
}
function selectRow(){
	var selectRow=document.getElementById("selectRow").value;
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	if(selectRow==-1){
		tr.style.background ='#EEEE00';
		document.getElementById("selectRow").value=tr.rowIndex;
	}else{
		var hjdjTable=document.getElementById("hjdjTable");
		hjdjTable.rows[selectRow-1].style.background="#FFFFFF";
		tr.style.background ='#EEEE00';
		document.getElementById("selectRow").value=tr.rowIndex;
	}
}
function printZjz(){

	var hjdjTable=document.getElementById("hjdjTable");
	var uptr=hjdjTable.getElementsByTagName("tr");
	var selectRow=document.getElementById("selectRow").value;
	var isNoPrint=document.getElementById("isNoPrint").value;
	if(isNoPrint==0){
		alert("当前没有登记记录无法打印");
		return false;
	}
	if(selectRow==-1 && uptr.length>2){
		alert("请单击需要打印准见证的记录");
		return false;
	}
	var web2=document.getElementsByName("webId");
	var webId=web2[selectRow-1].value;
	var web1=document.getElementsByName("webId1");
	var webId1=web1[selectRow-1].value;
	if(uptr.length>2){
		var url=basePath+"hjdj.do?method=printXp&webId="+webId+"&webId1="+webId1;
		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
	}else{
		var url=basePath+"hjdj.do?method=printXp&webId="+web2[0].value+"&webId1="+webId1;
		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
	}
	
}
function deleteDj(){
	var isNoPrint=document.getElementById("isNoPrint").value;
	var hjdjTable=document.getElementById("hjdjTable");
	var uptr=hjdjTable.getElementsByTagName("tr");
	var selectRow=document.getElementById("selectRow").value;
	if(isNoPrint==0){
		alert("当前没有登记记录，无法取消登记");
		return false;
	}
	if(selectRow==-1 && uptr.length>2){
		alert("请单击需要取消登记的记录");
		return false;
	}
	var webId=document.getElementsByName("webId");
	if(window.confirm("确认取消登记？")==true){
		if(uptr.length>2){
			
//删除登记			$.ajax({
//			      type:"POST",
//			      url:basePath+"hjdj.do",
//			      data:"method=deleteDj&webId="+webId[selectRow-1].value,
//			      dataType:"json",
//			      success:callback7,
//			      error:callback1
//			});
			document.forms[0].action=basePath+"hjdj.do?method=cancelDj&webId="+webId[selectRow-1].value;
			document.forms[0].submit();
		}else{
//删除登记		  $.ajax({
//			      type:"POST",
//			      url:basePath+"hjdj.do",
//			      data:"method=deleteDj&webId="+webId[0].value,
//			      dataType:"json",
//			      success:callback7,
//			      error:callback1
//			});
			document.forms[0].action=basePath+"hjdj.do?method=cancelDj&webId="+webId[0].value;
			document.forms[0].submit();
		}
	}
	
}
function callback7(data){
	if(data[0]=='0'){
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else{
		alert("已处会见通话状态，无法取消");
		return false;
	}
	
}
function giveZw(){
	var isNoPrint=document.getElementById("isNoPrint").value;
	var hjdjTable=document.getElementById("hjdjTable");
	var uptr=hjdjTable.getElementsByTagName("tr");
	var selectRow=document.getElementById("selectRow").value;
	if(isNoPrint==0){
		alert("当前没有登记记录无法分配座位");
		return false;
	}
	if(selectRow==-1 && uptr.length>2){
		alert("请单击需要分配座位的记录");
		return false;
	}
	var webId=document.getElementsByName("webId");
	var fpFlag=document.getElementsByName("fpFlag");
	if(uptr.length>2){
		if(fpFlag[selectRow-1].value==0){
			$.ajax({
			      type:"POST",
			      url:basePath+"hjdj.do",
			      data:"method=giveZw&webId="+webId[selectRow-1].value,
			      dataType:"json",
			      success:callback8,
			      error:callback1
			});
		}else{
			alert("该记录已经分配座位");
			return false;
		}
	}else{
		if(fpFlag[0].value=='0'){
			$.ajax({
			      type:"POST",
			      url:basePath+"hjdj.do",
			      data:"method=giveZw&webId="+webId[0].value,
			      dataType:"json",
			      success:callback8,
			      error:callback1
			});
		}else{
			alert("该记录已经分配座位");
			return false;
		}
		
	}
}
function callback8(data){
	if(data[0]=='0'){
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else{
		alert("所有线路已被分配");
		return false;
	}
	
}
function cancel(){
	var djId=document.getElementById("djId").value;
	var cancelInfo=document.getElementById("cancelInfo").value;
	cancelInfo=encodeURI(encodeURI(cancelInfo));
	$.ajax({
	      type:"POST",
	      url:basePath+"hjdj.do",
	      data:"method=qxdj&djId="+djId+"&cancelInfo="+cancelInfo,
	      dataType:"json",
	      success:callback10,
	      error:callback1
	});	
}
function callback10(data){
	if(data[0]=='0'){
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else{
		window.location.href(basePath+"jsp/hjdj/ingHj.jsp");
	}
}
function updateHjDj(){
	var hjdjTable=document.getElementById("hjdjTable");
	var uptr=hjdjTable.getElementsByTagName("tr");
	var selectRow=document.getElementById("selectRow").value;
	var isNoPrint=document.getElementById("isNoPrint").value;
	if(isNoPrint==0){
		alert("当前没有会见登记记录");
		return false;
	}
	if(selectRow==-1 && uptr.length>2){
		alert("请单击需要修改的记录");
		return false;
	}
	var webId=document.getElementsByName("webId");
	if(uptr.length>2){
		document.forms[0].action=basePath+"hjdj.do?method=updateHjDj&webId="+webId[selectRow-1].value;
		document.forms[0].submit();
	}else{
		document.forms[0].action=basePath+"hjdj.do?method=updateHjDj&webId="+webId[0].value;
		document.forms[0].submit();
	}
}
function selectQs(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var isNo=document.getElementsByName("isNo");
	if(isNo[tr.rowIndex-4].checked==true){
		isNo[tr.rowIndex-4].checked=false;
		tr.style.background ='#FFFFFF';
	}else{
		isNo[tr.rowIndex-4].checked=true;
		tr.style.background ='#EEEE00';
	}
}
function saveupdateHjDj(){
	document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>处理中，请稍后</b>";
	document.getElementById("load").style.display="block";
	var djId=document.getElementById("hjId").value;
	var isNo=document.getElementsByName("isNo");
	var isNo1="";
	var k=1;
	for(var i=0;i<isNo.length;i++){
		if(isNo[i].checked){
			if(k==1){
				isNo1+=isNo[i].value;
				k++;
			}else{
				isNo1+=","+isNo[i].value;
			}
		}
	}
	isNo1=encodeURI(encodeURI(isNo1));
	var hjType=document.getElementById("hjType");
	var hjType1="";
	for(var i=0;i<hjType.length;i++){
		if(hjType.options[i].selected){
			hjType1=hjType.options[i].value;
			break;
		}
	}
	var hjTime=document.getElementById("hjTime").value;
	var patrn=/^-?\d+$/;
	if (!patrn.test(hjTime)){ 
		alert("会见时长包含非法字符,请输入整数");
		return false;
	}
	var hjInfo=document.getElementById("hjInfo").value;
	if(hjInfo.indexOf("'")>-1){
		alert("会见说明输入有误，不能包含特殊符号");
		return false;
	}
	hjInfo=encodeURI(encodeURI(hjInfo));
	$.ajax({
	      type:"POST",
	      url:basePath+"hjdj.do",
	      data:"method=saveupdateHjDj&djId="+djId+"&isNo="+isNo1+"&hjTime="+hjTime+"&hjInfo="+hjInfo+"&hjType="+hjType1,
	      dataType:"json",
	      success:callback11,
	      error:callback1
	});
}
function saveupdateHjDj1(){
	document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>处理中，请稍后</b>";
	document.getElementById("load").style.display="block";
	var djId=document.getElementById("hjId").value;
	var isNo=document.getElementsByName("isNo");
	var isNo1="";
	var k=1;
	for(var i=0;i<isNo.length;i++){
		if(isNo[i].checked){
			if(k==1){
				isNo1+=isNo[i].value;
				k++;
			}else{
				isNo1+=","+isNo[i].value;
			}
		}
	}
	isNo1=encodeURI(encodeURI(isNo1));
	var hjType=document.getElementById("hjType");
	var hjType1="";
	for(var i=0;i<hjType.length;i++){
		if(hjType.options[i].selected){
			hjType1=hjType.options[i].value;
			break;
		}
	}
	var hjTime=document.getElementById("hjTime").value;
	var patrn=/^-?\d+$/;
	if (!patrn.test(hjTime)){ 
		alert("会见时长包含非法字符,请输入整数");
		return false;
	}
	var hjInfo=document.getElementById("hjInfo").value;
	if(hjInfo.indexOf("'")>-1){
		alert("会见说明输入有误，不能包含特殊符号");
		return false;
	}
	hjInfo=encodeURI(encodeURI(hjInfo));
	$.ajax({
	      type:"POST",
	      url:basePath+"hjdj.do",
	      data:"method=saveupdateHjDj12&djId="+djId+"&isNo="+isNo1+"&hjTime="+hjTime+"&hjInfo="+hjInfo+"&hjType="+hjType1,
	      dataType:"json",
	      success:callback12345,
	      error:callback1
	});
}
function callback11(data){
	document.getElementById("load").style.display="none";
	var djId=document.getElementById("hjId").value;
	var webId1 = document.getElementById("frNo").value;	
	if(data[0]==-5){
		alert("参加会见的亲属可能未绑定“证件号码”，请核实后重试");
		return false;
	}else if(data[0]==-7){
		alert("系统检测到有参加会见的亲属“亲属关系”栏为空，请核实后重试");
		return false;
	}else if(data[0]==-8){
		alert("系统检测到有参加会见的亲属未审核，请核实后重试");
		return false;
	}else if(data[0]==-9){
		alert("无法与门禁服务器连接，请检查网络");
		return false;
	}else if(data[0]==-10){
		alert("向门禁服务器提交数据时出现错误");
		return false;
	}else if(data[0]==-1){
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else{
		alert("修改会见登记成功");
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
		var url=basePath+"hjdj.do?method=printXp&webId="+djId+"&webId1="+webId1;
		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
	}
}
function callback12345(data){
	document.getElementById("load").style.display="none";
	var djId=document.getElementById("hjId").value;
	var webId1 = document.getElementById("frNo").value;	
	if(data[0]==-5){
		alert("参加会见的亲属可能未绑定“证件号码”或“IC卡号”，请核实后重试");
		return false;
	}else if(data[0]==-7){
		alert("系统检测到有参加会见的亲属“亲属关系”栏为空，请核实后重试");
		return false;
	}else if(data[0]==-8){
		alert("系统检测到有参加会见的亲属未审核，请核实后重试");
		return false;
	}else if(data[0]==-9){
		alert("无法与门禁服务器连接，请检查网络");
		return false;
	}else if(data[0]==-10){
		alert("向门禁服务器提交数据时出现错误");
		return false;
	}else if(data[0]==-1){
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else{
		alert("修改会见登记成功");
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
		var url=basePath+"hjdj.do?method=printXp&webId="+djId+"&webId1="+webId1;
		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
	}
}
function callback5678(data){
	document.getElementById("load").style.display="none";
	var djId=document.getElementById("hjId").value;
	var webId1 = document.getElementById("frNo").value;	
	if(data[0]==-5){
		alert("参加会见的亲属可能未绑定“证件号码”，请核实后重试");
		return false;
	}else if(data[0]==-7){
		alert("系统检测到有参加会见的亲属“亲属关系”栏为空，请核实后重试");
		return false;
	}else if(data[0]==-8){
		alert("系统检测到有参加会见的亲属未审核，请核实后重试");
		return false;
	}else if(data[0]==-10){
		alert("向门禁服务器提交数据时出现错误");
		return false;
	}else if(data[0]==-1){
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else{
		alert("修改会见登记成功");
		if(window.confirm("是否继续向门禁系统传递家属信息？")==true){
			if(data.length==1){
//				alert("进入1="+1);
				$.ajax({
					 type:"POST",
					 url:basePath+"hjdj.do",
				     data:"method=addQsInfoToABDoor1&webId="+data[0].webId,
				     dataType:"json",
				     success:callbackAB1,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
//				document.forms[0].action=basePath+"hjdj.do?method=addQsInfoToABDoor1&webId="+data[0].webId;
//				document.forms[0].submit();
//				return false;
			}
			if(data.length==2){
//				alert("进入2="+2);
				$.ajax({
					 type:"POST",
					 url:basePath+"hjdj.do",
				     data:"method=addQsInfoToABDoor2&webId="+data[0].webId+"&webId1="+data[1].webId,
				     dataType:"json",
				     success:callbackAB1,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==3){
//				alert("进入3="+3);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor3&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId,
				     dataType:"json",
				     success:callbackAB1,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==4){
//				alert("进入4="+4);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor4&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId,
				     dataType:"json",
				     success:callbackAB1,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==5){
//				alert("进入5="+5);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor5&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId,
				     dataType:"json",
				     success:callbackAB1,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==6){
//				alert("进入6="+6);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor6&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId,
				     dataType:"json",
				     success:callbackAB1,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==7){
//				alert("进入7="+7);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor7&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId+"&webId6="+data[6].webId,
				     dataType:"json",
				     success:callbackAB1,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==8){
//				alert("进入8="+8);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor8&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId+"&webId6="+data[6].webId+"&webId7="+data[7].webId,
				     dataType:"json",
				     success:callbackAB1,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			if(data.length==9){
//				alert("进入9="+9);
				$.ajax({
					 type:"POST",
				     url:"hjdj.do",
				     data:"method=addQsInfoToABDoor9&webId="+data[0].webId+"&webId1="+data[1].webId+"&webId2="+data[2].webId+"&webId3="+data[3].webId+"&webId4="+data[4].webId+"&webId5="+data[5].webId+"&webId6="+data[6].webId+"&webId7="+data[7].webId+"&webId8="+data[8].webId,
				     dataType:"json",
				     success:callbackAB1,
				     error:callback1
				});
				sleep(this,10000);//调用暂停函数
			}
			
		}
		
		var url=basePath+"hjdj.do?method=printXp&webId="+djId+"&webId1="+webId1;
		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
	}
}
function callbackAB1(data){
	if(data[0]==1){
		alert("传输成功");
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}else{
		alert("传输失败");
		document.forms[0].action=basePath+"hjdj.do?method=search";
		document.forms[0].submit();
	}	
}
function updateFrQs1(){
		var tr = event.srcElement;
		while (tr && tr.tagName != "TR") {
			tr = tr.parentElement;
		}
		var isNos=document.getElementsByName("isNo");
		var isNo=isNos[tr.rowIndex-1].value;
		window.location.href("hjdj.do?method=updateFrQs1&webId="+isNo);
	
}
function updateSaveFrQs1(){
	var qsId=document.getElementById("qsId").value;
	var qsSfz=document.getElementById("qsSfz").value;
	var dz=document.getElementById("dz").value;
	var qsName=document.getElementById("qsName").value;
	var qsCard=document.getElementById("qsCard").value;
	var tele=document.getElementById("tele").value;
	var gx=document.getElementById("gx").value;
	var jqNo1=document.getElementById("jqNo1").value;
	var cardTypes=document.getElementsByName("cardType");
	var frName1=document.getElementById("frName1").value;
	var photoAddress=document.getElementById("photoAddress").value;
	var jz=document.getElementById("jz").value;
	var qsSfzWlh=document.getElementById("qsSfzWlh").value;
	var bz=document.getElementById("bz").value;
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
	var cardType=0;
	for(var i=0;i<cardTypes.length;i++){
		if(cardTypes[i].checked){
			cardType=cardTypes[i].value;
			break;
		}
	}
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
	if(gx.indexOf("'")>-1){
		alert("关系输入有误，不能包含特殊符号");
		return false;
	}
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
//	dz=encodeURI(encodeURI(dz));
//	qsName=encodeURI(encodeURI(qsName));
//	qsCard=encodeURI(encodeURI(qsCard));
//	tele=encodeURI(encodeURI(tele));
//	gx1=encodeURI(encodeURI(gx1));
//	xb1=encodeURI(encodeURI(xb1));
//	sw1=encodeURI(encodeURI(sw1));
	qsName=qsName.replace(/\s+$/g,"");
	dz=dz.replace(/\s+$/g,"");
	qsCard=qsCard.replace(/\s+$/g,"");
	tele=tele.replace(/\s+$/g,"");
	qsSfzWlh=qsSfzWlh.replace(/\s+$/g,"");
	bz=bz.replace(/\s+$/g,"");
	$.ajax({
	      type:"POST",
	      url:"hjdj.do?method=updateSaveFrQs",
	      // data:"method=updateSaveFrQs&qsId="+qsId+"&qsName="+qsName+"&qsCard="+qsCard+"&dz="+dz+"&tele="+tele+"&gx="+gx1+"&xb="+xb1+"&sw="+sw1+"&qsSfz="+qsSfz+"&photoAddress="+photoAddress,
	  	  data : {
			qsId:qsId,
			frName1:frName1,
			qsName:qsName,
			qsCard:qsCard,
			dz:dz,
			tele:tele,
			gx:gx,
			xb:xb1,
			zjlb:zjlb1,
			spState:spState1,
			qsSfz:qsSfz,
			jqNo1:jqNo1,
			cardType:cardType,
			photoAddress:photoAddress,
			qsSfzWlh:qsSfzWlh,
			bz:bz,
			jz:"'"+jz+"'"
		  },
	      dataType:"json",
	      success:callback30,
	      error:callback1
	});
}
function callback30(data){
	if(data[0]==0){
		document.forms[0].submit();
	}else{
		alert("罪犯亲属证件号码不能重复");
		return false;
	}
}
function openPort(){
//	document.all.qsSfzWlh.focus();
//	var isSuc=false;
//	for(var i=1;i<10;i++){
//		 isSuc=document.getElementById("WM1711").OpenPort1(i,"115200");
//		 if(isSuc==true){
//		 	break;
//		 }
//	}
	//reID.ReadCardID(4, "baud=9600 parity=N data=8 stop=1");
	
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
function spPass(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var webIds=document.getElementsByName("webId");
	var webId=webIds[tr.rowIndex-1].value;
	if(window.confirm("确认审批通过吗")==true){
		$.ajax({
	      type:"POST",
	      url:basePath+"hjdj.do",
	  	  data :"method=spPass&webId="+webId,
	      dataType:"json",
	      success:callback29,
	      error:callback1
		});
	}
}
function callback29(data){
	document.forms[0].action=basePath+"hjdj.do?method=search2";
	document.forms[0].submit();
}
function spNotPass(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var webIds=document.getElementsByName("webId");
	var webId=webIds[tr.rowIndex-1].value;
	if(window.confirm("确认审批不通过吗")==true){
		$.ajax({
	      type:"POST",
	      url:basePath+"hjdj.do",
	  	  data :"method=spNotPass&webId="+webId,
	      dataType:"json",
	      success:callback29,
	      error:callback1
		});
	}
	
}
function openPort1(){
	var str=document.getElementById("IDCard2").FindReader();
	if(str>1000){
		document.getElementById("IDCard2").SetloopTime(1000);
  		document.getElementById("IDCard2").SetReadType(1);
  		document.getElementById("IDCard2").SetPhotoType(1);
	}
}

function colsePort1(){
	document.getElementById("IDCard2").SetReadType(0);
	
}
function selectSpr(){
	var sqdept=document.getElementById("sqdept");
	var sqdept1="";
	for(var i=0;i<sqdept.length;i++){
		if(sqdept.options[i].selected){
			sqdept1=sqdept.options[i].value;
			break;
		}
	}
	if(sqdept1!="null"){
		sqdept1=encodeURI(encodeURI(sqdept1));
		$.ajax({
		      type:"POST",
		      url:"hjdj.do",
		      data:"method=checkSpr&sqdept="+sqdept1,
		      dataType:"json",
		      success:callback7,
		      error:callback1
		});
	}else{
		var spr=document.getElementById("spr");
		spr.options.length=0;
		spr.add(new  Option("全部","null"));
	}
}
function callback7(data){
	var spr=document.getElementById("spr");
	spr.options.length=0;   
	if(data.length>0){
		spr.add(new  Option("全部","null")); 
		for(var i=0;i<data.length;i++){
			spr.add(new  Option(data[i].userName,data[i].userNo)); 
		}
	}else{
		spr.add(new  Option("全部","null")); 
	}
	return false;
}
function addHjSq(){
	var qsInfo=document.getElementById("qsInfo").value;
	if(qsInfo==0){
		alert("请添加亲属信息");
		return false;
	}
	var sqid=document.getElementById("sqid").value;
	var frNo1=document.getElementById("frNo1").value;
	var jqName=document.getElementById("jqName").value;
	var frName1=document.getElementById("frName1").value;
	var jqNo1=document.getElementById("jqNo1").value;
	var sqsm=document.getElementById("sqsm").value;
	var sqx=document.getElementById("sqx");
	var sqx1;
	for(var i=0;i<sqx.length;i++){
		if(sqx.options[i].selected){
			sqx1=sqx.options[i].text;
			break;
		}
	}
	var sqdept=document.getElementById("sqdept");
	var sqdept1;
	for(var i=0;i<sqdept.length;i++){
		if(sqdept.options[i].selected){
			sqdept1=sqdept.options[i].value;
			break;
		}
	}
	var spr=document.getElementById("spr");
	var spr1;
	var sprName;
	for(var i=0;i<spr.length;i++){
		if(spr.options[i].selected){
			spr1=spr.options[i].value;
			sprName=spr.options[i].text;
			break;
		}
	}
	var hjType=document.getElementById("hjType");
	var hjType1;
	for(var i=0;i<hjType.length;i++){
		if(hjType.options[i].selected){
			hjType1=hjType.options[i].value;
			break;
		}
	}
	if(sqx1=='null'){
		alert("请选择申请内容");
		return false;
	}
	if(sqdept1=='null'){
		alert("请选择审批部门");
		return false;
	}
	
	$.ajax({
	      type:"POST",
	      url:"hjdj.do?method=addHjSq",
	      data : {
				frNo:frNo1,
				frName1:frName1,
				sqid:sqid,
				jqNo1:jqNo1,
				sqsm:sqsm,
				sqx:sqx1,
				sqdept:sqdept1,
				spr:spr1,
				hjType:hjType1,
				jqName:jqName,
				sprName:sprName
			  },
	      dataType:"json",
	      success:callback28,
	      error:callback1
	});
}
function callback28(data){
	if(data[0]=='0'){
		alert("此会见人已经审批");
		return false;
	}else{
		document.forms[0].submit();
	}
}
function addHjr(frNo){
	var sqid=document.getElementById("sqid").value;
	document.forms[0].action="hjdj.do?method=addHjr&frNo="+frNo+"&sqid="+sqid;
	document.forms[0].submit();
}
function addSaveHjr(){
	var frNo1=document.getElementById("frNo1").value;
	var dz=document.getElementById("dz").value;
	var qsSfz=document.getElementById("qsSfz").value;
	var qsName=document.getElementById("qsName").value;
	var qsCard=document.getElementById("qsCard").value;
	var sqid=document.getElementById("sqid").value;
	var photoAddress=document.getElementById("photoAddress").value;
	var jz=document.getElementById("jz").value;
	var frName1=document.getElementById("frName1").value;
	var jqNo1=document.getElementById("jqNo1").value;
	var fjName=document.getElementById("fjName").value;
	qsSfz=qsSfz.replace(/\s+$/g,"");
	var gx=document.getElementById("gx");
	var gx1;
	for(var i=0;i<gx.length;i++){
		if(gx.options[i].selected){
			gx1=gx.options[i].value;
			break;
		}
	}
	var xb=document.getElementById("xb");
	var xb1;
	for(var i=0;i<xb.length;i++){
		if(xb.options[i].selected){
			xb1=xb.options[i].value;
			break;
		}
	}
	
	if(qsSfz.indexOf("'")>-1){
		alert("亲属证件号码输入有误，不能包含特殊符号");
		return false;
	}
	if(qsName.indexOf("'")>-1){
		alert("亲属姓名输入有误，不能包含特殊符号");
		return false;
	}
	if(qsCard.indexOf("'")>-1){
		alert("亲属ID卡输入有误，不能包含特殊符号");
		return false;
	}
	if(dz.indexOf("'")>-1){
		alert("地址输入有误，不能包含特殊符号");
		return false;
	}
	qsName=qsName.replace(/\s+$/g,"");
	dz=dz.replace(/\s+$/g,"");
	qsCard=qsCard.replace(/\s+$/g,"");
	$.ajax({
	      type:"POST",
	      url:"hjdj.do?method=addSaveHjr",
	      data : {
				frNo:frNo1,
				frName1:frName1,
				qsSfz:qsSfz,
				qsName:qsName,
				qsCard:qsCard,
				dz:dz,
				gx:gx1,
				xb:xb1,
				jqNo1:jqNo1,
				sqid:sqid,
				fjName:fjName,
				photoAddress:photoAddress,
				jz:"'"+jz+"'"
			  },
	      dataType:"json",
	      success:callback21,
	      error:callback1
	});
}
function callback21(data){
	var frNo1=document.getElementById("frNo1").value;
	var sqid=document.getElementById("sqid").value;
	document.forms[0].action="hjdj.do?method=spData&frNo="+frNo1+"&sqid="+sqid;
	document.forms[0].submit();
}
function delSpqs(spqsid){
		if(window.confirm("确认删除记录?")==true){
			$.ajax({
			      type:"POST",
			      url:"hjdj.do",
			      data:"method=delSpqs&webId="+spqsid,
			      dataType:"json",
			      success:callback21,
			      error:callback1
			});
		}
}

function TZHandle(){
	if(window.confirm("是否发起停止会见办理通知?")==true){
		$.ajax({
		      type:"POST",
		      url:"hjdj.do",
		      data:"method=tzHandle",
		      dataType:"json",
		      success:callback22
		});
	}
}
function callback22(data){
	if(data[0]=="1"){
		alert("已成功发起通知");
	}else{
		alert("由于未知原因，通知发送失败");
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
function addtpqs(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid =document.getElementById("hjid").value;
	var webIds=document.getElementsByName("webId");
	var webId = webIds[tr.rowIndex-1].value;
	
	document.forms[0].action="hjdj.do?method=toaddtpqs&webId="+webId+"&hjid="+hjid;
	document.forms[0].submit();
	
//	$('#info2').window({
//		title:"特批亲属信息",
//		href:"hjdj.do?method=toaddtpqs&webId="+webId+"&hjid="+hjid,
//		width:730,
//		height:300,
//		collapsible:false,
//		minimizable:false,
//		maximizable:false,
//		resizable:false,
//		cache:false,
//		onClose:function(){
//			//document.forms[0].action=basePath+"hjdj.do?method=search";
//			//document.forms[0].submit();
//		}
//	});
}
function addspyy(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid =document.getElementById("hjid").value;
	var webIds=document.getElementsByName("webId");
	var webId = webIds[tr.rowIndex-1].value;
	$('#info2').window({
		title:"填写审批原因",
		href:"hjdj.do?method=toaddspyy&webId="+webId+"&hjid="+hjid,
		width:400,
		height:150,
		collapsible:false,
		minimizable:false,
		maximizable:false,
		resizable:false,
		cache:false,
		onClose:function(){
			//document.forms[0].action=basePath+"hjdj.do?method=search";
			//document.forms[0].submit();
		}
	});
}
function tjhjsp(){
	var spid=document.getElementById("spid").value;
	var spbms=document.getElementById("spbm");
	var spUsers=document.getElementById("spUser");
	var spbm;
	var spUser;
	for(var i=0;i<spbms.length;i++){
		if(spbms.options[i].selected){
			spbm=spbms.options[i].value;
			break;
		}
	}
	for(var i=0;i<spUsers.length;i++){
		if(spUsers.options[i].selected){
			spUser=spUsers.options[i].value;
			break;
		}
	}
	if(spbm=='null' || spUser=='null'){
		alert("请选择审批部门和审批人后再提交");
		return false;
	}
	$.ajax({
	      type:"POST",
	      url:"hjdj.do?method=tjhjsp",
	      data : {
			spid:spid,
			spbm:spbm,
			spUser:spUser
			
		  },
	      dataType:"json",
	      success:callback24  
	});
}
function callback24(data){
	if(data[0]=="0"){
		alert("请选择审批部门和审批人后再提交");
		return false;
	}
	goHjdj();
}
function savetpqs(){
	var webId=document.getElementById("webId1").value;
	var hjid=document.getElementById("hjid").value;
	
	var dz=document.getElementById("dz").value;
	
	var qsSfz=document.getElementById("qsSfz").value;
	var qsCard =document.getElementById("qsCard").value;
	var qsName=document.getElementById("qsName11").value;
	var spyy =document.getElementById("spyy");
	var photoAddress=document.getElementById("photoAddress").value;
	var spbz=document.getElementById("spbz").value;
	qsSfz=qsSfz.replace(/\s+$/g,"");
	var flag=checkRate(qsSfz);
	if(!flag){
		alert("证件号码只能是数字和英文组合");
		return false;
	}
	if(qsName==''){
		alert("亲属姓名不能为空");
		return false;
	}
	if(qsSfz==''){
		alert("亲属证件号码不能为空");
		return false;
	}
//	if(qsCard==''){
//		alert("亲属卡号不能为空");
//		return false;
//	}
	var spyy1;
	for(var i=0;i<spyy.length;i++){
		if(spyy.options[i].selected){
			spyy1=spyy.options[i].value;
			break;
		}
	}
	if(spyy1==1){
		spyy1="亲属及监护人未在规定时间或未带有效身份证件会见";
	}else if(spyy1==2){
		spyy1="非首次会见的港澳台和外国籍亲属、监护人";
	}else if(spyy1==3){
		spyy1="首次会见的港澳台和外国籍亲属、监护人";
	}else if(spyy1==4){
		spyy1="受委托的律师、亲友会见";
	}else if(spyy1==5){
		spyy1="帮教单位、社会团体会见";
	}else if(spyy1==6){
		spyy1="B类重点罪犯会见";
	}else if(spyy1==7){
		spyy1="C类重点罪犯会见";
	}else if(spyy1==8){
		spyy1="A类重点罪犯会见";
	}else if(spyy1==9){
		spyy1="超次数、人数、时间的会见";
	}else if(spyy1==10){
		spyy1="区监狱管理局批准的会见";
	}else{
		spyy1="公检法的会见";
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
	var gx2=document.getElementById("gx2").value;
	gx2=gx2.replace(/\s+$/g,"");
//	if(gx2!=""){
//		gx1=gx2;
//	}else{
//		gx1="";
//	}
	if(gx2==''){
		alert("关系不能为空");
		return false;
	}
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
	$.ajax({
		      type:"POST",
		      url:"hjdj.do?method=savetpqs",
		      data : {
				webId:webId,
				qsSfz:qsSfz,
				qsCard:qsCard,
				qsName:qsName,
				photoAddress:photoAddress,
				gx:gx2,
				xb:xb1,
				zjlb:zjlb1,
				hjid:hjid,
				spyy:spyy1,
				spbz:spbz,
				dz:dz
			  },
		      dataType:"json",
		      success:callback25  
		});
}
function savetpqs1(){
	var webId=document.getElementById("webId1").value;
	var hjid=document.getElementById("hjid").value;
	
	var dz=document.getElementById("dz").value;
	
	var qsSfz=document.getElementById("qsSfz").value;
	var qsCard =document.getElementById("qsCard").value;
	var qsName=document.getElementById("qsName11").value;
	var spyy =document.getElementById("spyy");
	var photoAddress=document.getElementById("photoAddress").value;
	var spbz=document.getElementById("spbz").value;
	qsSfz=qsSfz.replace(/\s+$/g,"");
	var flag=checkRate(qsSfz);
	if(!flag){
		alert("证件号码只能是数字和英文组合");
		return false;
	}
	if(qsName==''){
		alert("亲属姓名不能为空");
		return false;
	}
	if(qsSfz==''){
		alert("亲属证件号码不能为空");
		return false;
	}
	if(qsCard==''){
		alert("IC卡号不能为空");
		return false;
	}
	var spyy1;
	for(var i=0;i<spyy.length;i++){
		if(spyy.options[i].selected){
			spyy1=spyy.options[i].value;
			break;
		}
	}
	if(spyy1==1){
		spyy1="亲属及监护人未在规定时间或未带有效身份证件会见";
	}else if(spyy1==2){
		spyy1="非首次会见的港澳台和外国籍亲属、监护人";
	}else if(spyy1==3){
		spyy1="首次会见的港澳台和外国籍亲属、监护人";
	}else if(spyy1==4){
		spyy1="受委托的律师、亲友会见";
	}else if(spyy1==5){
		spyy1="帮教单位、社会团体会见";
	}else if(spyy1==6){
		spyy1="B类重点罪犯会见";
	}else if(spyy1==7){
		spyy1="C类重点罪犯会见";
	}else if(spyy1==8){
		spyy1="A类重点罪犯会见";
	}else if(spyy1==9){
		spyy1="超次数、人数、时间的会见";
	}else if(spyy1==10){
		spyy1="区监狱管理局批准的会见";
	}else{
		spyy1="公检法的会见";
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
	var gx2=document.getElementById("gx2").value;
	gx2=gx2.replace(/\s+$/g,"");
//	if(gx2!=""){
//		gx1=gx2;
//	}else{
//		gx1="";
//	}
	if(gx2==''){
		alert("关系不能为空");
		return false;
	}
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
	$.ajax({
		      type:"POST",
		      url:"hjdj.do?method=savetpqs",
		      data : {
				webId:webId,
				qsSfz:qsSfz,
				qsCard:qsCard,
				qsName:qsName,
				photoAddress:photoAddress,
				gx:gx2,
				xb:xb1,
				zjlb:zjlb1,
				hjid:hjid,
				spyy:spyy1,
				spbz:spbz,
				dz:dz
			  },
		      dataType:"json",
		      success:callback25  
		});
}
function savespyy(){
	var webId=document.getElementById("webId1").value;
	var hjid=document.getElementById("hjid").value;

//	var dz=document.getElementById("dz").value;
//	
//	var qsSfz=document.getElementById("qsSfz").value;
//	var qsCard =document.getElementById("qsCard").value;
//	var qsName=document.getElementById("qsName11").value;
	var spyy1 =document.getElementById("spyy").value;
	var spbz =document.getElementById("spbz").value;

	
//	var photoAddress=document.getElementById("photoAddress").value;
//	qsSfz=qsSfz.replace(/\s+$/g,"");
//	if(qsName==''){
//		alert("亲属姓名不能为空");
//		return false;
//	}
//	if(qsSfz==''){
//		alert("亲属身份证不能为空");
//		return false;
//	}
//	if(qsCard==''){
//		alert("亲属卡号不能为空");
//		return false;
//	}
//	var gx=document.getElementById("gx");
//	var gx1;
//	var gx3;
//	for(var i=0;i<gx.length;i++){
//		if(gx.options[i].selected){
//			gx3=gx.options[i].value;
//			break;
//		}
//	}
//	var gx2=document.getElementById("gx2").value;
//	if(gx2!=""){
//		gx1=gx2;
//	}else{
//		gx1="";
//	}
//	if(gx1==''){
//		alert("亲属关系不能为空");
//		return false;
//	}
//	var xb=document.getElementById("xb");
//	var spyy1;
//	for(var i=0;i<spyy.length;i++){
//		if(spyy.options[i].selected){
//			spyy1=spyy.options[i].value;
//			break;
//		}
//	}
//	alert(spyy1);
	if(spyy1==1){
		spyy1="亲属及监护人未在规定时间或未带有效身份证件会见";
	}else if(spyy1==2){
		spyy1="非首次会见的港澳台和外国籍亲属、监护人";
	}else if(spyy1==3){
		spyy1="首次会见的港澳台和外国籍亲属、监护人";
	}else if(spyy1==4){
		spyy1="受委托的律师、亲友会见";
	}else if(spyy1==5){
		spyy1="帮教单位、社会团体会见";
	}else if(spyy1==6){
		spyy1="B类重点罪犯会见";
	}else if(spyy1==7){
		spyy1="C类重点罪犯会见";
	}else if(spyy1==8){
		spyy1="A类重点罪犯会见";
	}else if(spyy1==9){
		spyy1="超次数、人数、时间的会见";
	}else if(spyy1==10){
		spyy1="区监狱管理局批准的会见";
	}else{
		spyy1="公检法的会见";
	}
	
//	alert(spyy1);
//	spyy1=encodeURI(encodeURI(spyy1));
	$.ajax({
		      type:"POST",
		      url:"hjdj.do?method=updatespyy",
		      data : {
				webId:webId,
				hjid:hjid,
				spyy:spyy1,
				spbz:spbz

			  },
		      dataType:"json",
		      success:callback25  
		});
}
function callback25(data){
	if(data[0]=="-1"){
		alert("此次会见审批中已有此亲属");
		return false;
	}
	document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+data[0];
	document.forms[0].submit();
}
function qxtpqs(){
	var hjid=document.getElementById("hjid").value;
	document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+hjid;
	document.forms[0].submit();
}

function goHjDj1(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var qsSFZs=document.getElementsByName("qsSFZs");
	var k=1;
	var qsSFZ;
	for(var i=0;i<qsSFZs.length;i++){
		if(k==1){
			qsSFZ=qsSFZs[i].value;
			k++;
		}else{
			qsSFZ+=";"+qsSFZs[i].value;
		}
	}
	var frNo=document.getElementById("frNo").value;
	var hjid=document.getElementById("hjid").value;
	document.forms[0].action=basePath+"hjdj.do?method=checkFr&frNo="+frNo+"&qsSFZ="+qsSFZ+"&hjid="+hjid;
	document.forms[0].submit();
}
function addFace(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var frNo=tr.cells[10].children[3].value;
	var webId=tr.cells[10].children[4].value;
	 $.ajax({
		  beforeSend:function(){
		  document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>加载中</b>";
		  document.getElementById("load").style.display="block";
	},
	      type:"POST",
	      url:"hjdj.do",
	      data:"method=addFace&frNo="+frNo+"&webId="+webId,
	      dataType:"json",
	      success:callback777,
	      error:callback1
	});
}

function addFace1(){
	var frNo=document.getElementById("frNo1").value;
	var webId=document.getElementById("qsId").value;
	 $.ajax({
		  beforeSend:function(){
		  document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>加载中</b>";
		  document.getElementById("load").style.display="block";
	},
	      type:"POST",
	      url:"hjdj.do",
	      data:"method=addFace&frNo="+frNo+"&webId="+webId,
	      dataType:"json",
	      success:callback888,
	      error:callback1
	});
}

function addFace2(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var webId=tr.cells[0].children[0].value;
	var qsName=tr.cells[0].children[1].value;
	var frNo=document.getElementById("frNo").value;
	if(qsName==''){
		alert("请添加特批家属后再注册人脸");
		return false;
	}
	 $.ajax({
		  beforeSend:function(){
		  document.getElementById("load").innerHTML="<img height =\"50px\" width=\"50px\" src=\"images/wait.gif\" /><b>加载中</b>";
		  document.getElementById("load").style.display="block";
	},
	      type:"POST",
	      url:"hjdj.do",
	      data:"method=addFaceSP&frNo="+frNo+"&webId="+webId,
	      dataType:"json",
	      success:callback999,
	      error:callback1
	});
}

function callback777(data){

	document.getElementById("load").style.display="none";
	var frNo=document.getElementById("frNo1").value;
	if(data[0]==null){
		alert("开启人脸注册失败，请稍后重试！");
		goQsMain(frNo);
	}else if(data[0]==2){
		alert("中途取消注册，请重试！");
		goQsMain(frNo);
	}else if(data[0]==3){
		alert("人脸模板已存在，不需要重新注册");		
		goQsMain(frNo);
	}else if(data[0]==4){
		alert("由于家属长时间没有将人脸靠近注册设备，导致注册已经超时，请重试！");
		goQsMain(frNo);
	}else if(data[0]==1){
		alert("人脸注册成功");
		goQsMain(frNo);
	}else if(data[0]==5){
		alert("系统检测到您的电脑没有注册人脸的权限！");
		return false;
	}else if(data[0]==6){
		alert("连接人脸注册设备失败！");
		return false;
	}else{
		alert("人脸注册设备异常，请尝试重启设备后重试！");
		return false;
	}
}
function callback888(data){

	document.getElementById("load").style.display="none";
	if(data[0]==null){
		alert("开启人脸注册失败，请稍后重试！");
		return false;
	}else if(data[0]==2){
		alert("中途取消注册，请重试！");
		return false;
	}else if(data[0]==3){
		alert("人脸模板已存在，不需要重新注册");		
		return false;
	}else if(data[0]==4){
		alert("由于家属长时间没有将人脸靠近注册设备，导致注册已经超时，请重试！");
		return false;
	}else if(data[0]==1){
		alert("人脸注册成功");
		return false;
	}else if(data[0]==5){
		alert("系统检测到您的电脑没有注册人脸的权限！");
		return false;
	}else if(data[0]==6){
		alert("连接人脸注册设备失败！");
		return false;
	}else{
		alert("人脸注册设备异常，请尝试重启设备后重试！");
		return false;
	}
}
function callback999(data){

	document.getElementById("load").style.display="none";
	var hjid=document.getElementById("hjid").value;
	if(data[0]==null){
		alert("开启人脸注册失败，请稍后重试！");
		document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+hjid;
		document.forms[0].submit();
	}else if(data[0]==2){
		alert("中途取消注册，请重试！");
		document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+hjid;
		document.forms[0].submit();
	}else if(data[0]==3){
		alert("人脸模板已存在，不需要重新注册");		
		document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+hjid;
		document.forms[0].submit();
	}else if(data[0]==4){
		alert("由于家属长时间没有将人脸靠近注册设备，导致注册已经超时，请重试！");
		document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+hjid;
		document.forms[0].submit();
	}else if(data[0]==1){
		alert("人脸注册成功");
		document.forms[0].action=basePath+"hjdj.do?method=tohjsp&hjid="+hjid;
		document.forms[0].submit();
	}else if(data[0]==5){
		alert("系统检测到您的电脑没有注册人脸的权限！");
		return false;
	}else if(data[0]==6){
		alert("连接人脸注册设备失败！");
		return false;
	}else{
		alert("人脸注册设备异常，请尝试重启设备后重试！");
		return false;
	}
}
function checkSpUser(){
	var spbm=document.getElementById("spbm");
	var spbm1="";
	for(var i=0;i<spbm.length;i++){
		if(spbm.options[i].selected){
			spbm1=spbm.options[i].value;
			break;
		}
	}
	if(spbm1!="null"){
		spbm1=encodeURI(encodeURI(spbm1));
		$.ajax({
		      type:"POST",
		      url:"hjdj.do",
		      data:"method=checkSpUser&spbm1="+spbm1,
		      dataType:"json",
		      success:callback7777,
		      error:callback1
		});
	}else{
		document.getElementById("spUser").disabled=true;
		var spUser=document.getElementById("spUser");
		spUser.options.length=0;
		spUser.add(new  Option("全部","null"));
	}
}
function callback7777(data){
	document.getElementById("spUser").disabled=false;
	var spUser=document.getElementById("spUser");
	spUser.options.length=0;   
	if(data.length>0){
		spUser.add(new  Option("全部","null")); 
		for(var i=0;i<data.length;i++){
			spUser.add(new  Option(data[i].userName,data[i].userNo)); 
		}
	}else{
		spUser.add(new  Option("全部","null")); 
	}
	return false;
}
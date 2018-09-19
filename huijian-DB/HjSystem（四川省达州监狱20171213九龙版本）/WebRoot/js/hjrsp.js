function chaxun(){
	document.forms[0].action="hjrsp.do?method=search";
	document.forms[0].submit();
}

function updateHjSp(spId){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var groupNo =document.getElementById("groupNo").value;
	var userName =document.getElementById("userName").value;
	var userNo =document.getElementById("userNo").value;
	var spGroupNos=document.getElementsByName("spGroupNo");
	var spGroupNo=spGroupNos[tr.rowIndex-2].value;
	var spUsers=document.getElementsByName("spUser");
	var spUser=spUsers[tr.rowIndex-2].value;
	if((userNo!=spUser) && (groupNo!='') && (userName!='超级管理员')){
		alert("您当前没有权限");
		return false;
	}
	
	var spStates=document.getElementsByName("spState1");
	var spState=spStates[tr.rowIndex-2].value;
	if(spState!=1){
		alert("已经审批过了");
		return false;
	}
	document.forms[0].action="hjrsp.do?method=updateHjSp&spId="+spId,
	document.forms[0].submit();

}
function spjg1(){
	var spjgs=document.getElementById("spjg");
	var spjg="";
	for(var i=0;i<spjgs.length;i++){
		if(spjgs.options[i].selected){
			spjg=spjgs.options[i].value;
			break;
		}
	}
	if(spjg==1){
		document.getElementById("jxsp").disabled=false;
	}else{
		document.getElementById("jxsp").options[0].selected=true;
		document.getElementById("jxsp").disabled=true;
		
	}
}
function jxsp1(){
	var jxsps=document.getElementById("jxsp");
	var jxsp="";
	for(var i=0;i<jxsps.length;i++){
		if(jxsps.options[i].selected){
			jxsp=jxsps.options[i].value;
			break;
		}
	}
	if(jxsp==1){
		document.getElementById("spbm").disabled=false;
	}else{
		document.getElementById("spbm").options[0].selected=true;
		document.getElementById("spUser").options[0].selected=true;
		document.getElementById("spbm").disabled=true;
		
		
		document.getElementById("spUser").disabled=true;
		
//		document.getElementById("spbm").disabled=true;
//		var spbm=document.getElementById("spbm");
//		spbm.options.length=0;
//		spbm.add(new  Option("全部","null"));
//		
//		document.getElementById("spUser").disabled=true;
//		var spUser=document.getElementById("spUser");
//		spUser.options.length=0;
//		spUser.add(new  Option("全部","null"));
		
	}
}
function tjhjsp(){
	var spid=document.getElementById("spid").value;
	var spjb=document.getElementById("spjb").value;
	var hjid=document.getElementById("hjid").value;
	var spjgs=document.getElementById("spjg");
	var spUsers=document.getElementById("spUser");
	var spjg="";
	var spUser;
	for(var i=0;i<spjgs.length;i++){
		if(spjgs.options[i].selected){
			spjg=spjgs.options[i].value;
			break;
		}
	}
	for(var i=0;i<spUsers.length;i++){
		if(spUsers.options[i].selected){
			spUser=spUsers.options[i].value;
			break;
		}
	}
	
	var jxsp=document.getElementById("jxsp");
	var jxsp1="";
	for(var i=0;i<jxsp.length;i++){
		if(jxsp.options[i].selected){
			jxsp1=jxsp.options[i].value;
			break;
		}
	}
	var spbm=document.getElementById("spbm");
	var spbm1="";
	for(var i=0;i<spbm.length;i++){
		if(spbm.options[i].selected){
			spbm1=spbm.options[i].value;
			break;
		}
	}
	if(jxsp1=='1' && (spbm1=='null' || spUser=='null')){
		alert("请选择部门和部门成员");
		return false;
	}

	$.ajax({
	      type:"POST",
	      url:"hjrsp.do",
	      data:"method=tjhjsp&spid="+spid+"&spbm1="+spbm1+"&spjg="+spjg+"&spjb="+spjb+"&hjid="+hjid+"&spUser="+spUser,
	      dataType:"json",
	      success:callback3
	});
}
function callback3(data){
	if(data[0]==0){
		document.forms[0].action="hjrsp.do?method=search";
		document.forms[0].submit();
	}
	
}


function frLikai(str){
	var spid=document.getElementById("spid").value;
	$.ajax({
	      type:"POST",
	      url:"hjrsp.do",
	      data:"method=updateFRRemarks&spid="+spid+"&frRemarks="+str,
	      dataType:"json"
	     
	});
}
function huoqu(webid){
	document.getElementById("qsLinShiWebId").value=webid;
	return false;
}
function qsLikai(str){
	var qsLinShiWebId=document.getElementById("qsLinShiWebId").value;
	if(qsLinShiWebId!=-1 ){
		$.ajax({
		      type:"POST",
		      url:"hjrsp.do",
		      data:"method=updateQSRemarks&webId="+qsLinShiWebId+"&qsRemarks="+str,
		      dataType:"json"
		     
		});
	}
}
function goHjSp(){
	document.forms[0].action="hjrsp.do?method=search";
	document.forms[0].submit();
}

function seeHjSp(spId){
	document.forms[0].action="hjrsp.do?method=seeHjSp&spId="+spId,
	document.forms[0].submit();
	
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
		      success:callback777,
		      error:callback1
		});
	}else{
		document.getElementById("spUser").disabled=true;
		var spUser=document.getElementById("spUser");
		spUser.options.length=0;
		spUser.add(new  Option("全部","null"));
	}
}
function callback777(data){
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
function callback1(data){
	
}
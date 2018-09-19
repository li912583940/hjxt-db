function enterSubmit(src,e){
         if(window.event)
             keyPressed = window.event.keyCode; // IE
         else
            keyPressed = e.which; // Firefox
         if(keyPressed==13){ 
        	 checkUser();                     
             return false;
        }
}
function checkUser(){
	var userNo=document.getElementById("userNo").value;
	var userName=document.getElementById("userName").value;
	if(userNo.indexOf("'")>-1){
		alert("用户编号输入有误，不能包含特殊符号");
		return false;
	}
	if(userName.indexOf("'")>-1){
		alert("用户姓名输入有误，不能包含特殊符号");
		return false;
	}
	document.forms[0].action="user.do?method=search";
	document.forms[0].submit();
}
function delUser(){
	 var tr = event.srcElement;
	 while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	 var userId=tr.cells[6].children[2].value;
	 if(window.confirm("确认删除记录？")==true){
		 document.forms[0].action="user.do?method=delUser&userId="+userId;
		 document.forms[0].submit();
	}
//	 $.ajax({
//	      type:"POST",
//	      url:"user.do",
//	      data:"method=delUser&userId="+userId,
//	      dataType:"json",
//	      success:callback,
//	      error:callback1
//	});
}
function callback(data){
	document.getElementById("info").innerText="";
	for(var i=0;i<data.length;i++){
		var table=document.getElementById("info");
		var tr=document.createElement("tr");
		var td=document.createElement("td");
		td.innerText=data[i].user_No;
		tr.appendChild(td);
		var td2=document.createElement("td");
		td2.innerText=data[i].user_Name;
		tr.appendChild(td2);
		var td3=document.createElement("td");
		td3.innerText=data[i].user_Depart;
		tr.appendChild(td3);
		var td4=document.createElement("td");
		td4.innerText=data[i].group_Name;
		tr.appendChild(td4);
		var td5=document.createElement("td");
		td5.innerHTML="<input type=\"button\" value=\"删除\" onclick=\"delUser();\"><input type=\"button\" value=\"修改\" onclick=\"sreachUserGroup();\"><input type=\"hidden\" id=\"userId\" name=\"userId\" value=\""+data[i].user_Id+"\"><input type=\"hidden\" id=\"groupNo\" name=\"groupNo\" value=\""+data[i].group+"\">";
	    tr.appendChild(td5);
	    table.appendChild(tr);
	    if(i==data.length-1)
		 {
			 var tr1=document.createElement("tr");
			 var td5=document.createElement("td");
			 td5.innerHTML="<input type=\"button\" value=\"添加用户\" onclick=\"checkUserGroup();\">";
			 tr1.appendChild(td5);
			 table.appendChild(tr1);
		 }
	}
}

function callback1(a,b,c){
	alert(a);
 	alert(b);
 	alert(c);
}
function checkUserGroup(){
	document.forms[0].action="user.do?method=checkUserGroup";
	document.forms[0].submit();
}
function saveUser(){
	var userNo=document.getElementById("userNo1").value;
	var userNo1=userNo.replace(/\s+$/g,"");
	if(userNo1.length<=0){
		alert("请输入登录帐号");
		return false;
	}
	var userDepart=document.getElementById("userDepart").value;
	var userName=document.getElementById("userName1").value;
	var userName=userName.replace(/\s+$/g,"");
	if(userName.length<=0){
		alert("请输入用户姓名");
		return false;
	}
	var userGroup=document.getElementById("userGroup");
	var usGroup=0;
	for(var i=0;i<userGroup.length;i++){
		if(userGroup.options[i].selected)
		{
			usGroup=userGroup.options[i].value;
			break;
		}
	}
	if(usGroup==0){
		alert("请选择用户组");
		return false;
	}
	if(userNo1.indexOf("'")>-1){
		alert("用户编号输入有误，不能包含特殊符号");
		return false;
	}
	if(userName.indexOf("'")>-1){
		alert("用户姓名输入有误，不能包含特殊符号");
		return false;
	}
	if(userDepart.indexOf("'")>-1){
		alert("所在部门输入有误，不能包含特殊符号");
		return false;
	}
	userNo1=encodeURI(encodeURI(userNo1));
	userDepart=encodeURI(encodeURI(userDepart));
	userName=encodeURI(encodeURI(userName));
	usGroup=encodeURI(encodeURI(usGroup));
	 $.ajax({
	      type:"POST",
	      url:"user.do",
	      data:"method=saveUser&userNo="+userNo1+"&userDepart="+userDepart+"&userName="+userName+"&usGroup="+usGroup,
	      dataType:"json",
	      success:callback2,
	      error:callback1
	});
}
function callback2(data){
	if(data[0]==null){
		alert("用户名已存在请重新输入");
		document.getElementById("userNo").Value="";
		return false;
	}else{
		document.forms[0].action="user.do?method=search";
		document.forms[0].submit();
	}
}
function sreachUserGroup(){
	 var tr = event.srcElement;
	 while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var userId=tr.cells[6].children[2].value;
	var group_No=tr.cells[6].children[3].value;
	group_No=encodeURI(encodeURI(group_No));
	document.forms[0].action="user.do?method=sreachUserGroup&userId="+userId+"&groupNo="+group_No;
	document.forms[0].submit();
}
function saveUpdateUser(){
	var userNo=document.getElementById("userNo").value;
	var userName=document.getElementById("userName").value;
	var userDepart=document.getElementById("userDepart").value;
	var userId=document.getElementById("userId").value;
	var arr;
	var userGroup=document.getElementById("userGroup");
	for(var i=0;i<userGroup.length;i++){
		if(userGroup.options[i].selected)
		{
			arr=userGroup.options[i].value;
			break;
		}
	}
	if(userName.indexOf("'")>-1){
		alert("用户姓名输入有误，不能包含特殊符号");
		return false;
	}
	if(userDepart.indexOf("'")>-1){
		alert("所在部门输入有误，不能包含特殊符号");
		return false;
	}
	var isSp=document.getElementById("isSp");
	var isSp1;
	for(var i=0;i<isSp.length;i++){
		if(isSp.options[i].selected){
			isSp1=isSp.options[i].value;
			break;
		}
		
	}
	var isBj=document.getElementById("isBj");
	var isBj1;
	for(var i=0;i<isBj.length;i++){
		if(isBj.options[i].selected){
			isBj1=isBj.options[i].value;
			break;
		}
		
	}


	var userName1=userName.replace(/\s+$/g,"");
	var userDepart1=userDepart.replace(/\s+$/g,"");
	userNo=encodeURI(encodeURI(userNo));
	userName1=encodeURI(encodeURI(userName1));
	userDepart1=encodeURI(encodeURI(userDepart1));
	arr=encodeURI(encodeURI(arr));
	 $.ajax({
	      type:"POST",
	      url:"user.do",
	      data:"method=updataSaveUser&userNo="+userNo+"&userDepart="+userDepart1+"&userName="+userName1+"&usGroup="+arr+"&userId="+userId+"&isSp="+isSp1+"&isBj="+isBj1,
	      dataType:"json",
	      success:callback3,
	      error:callback1
	});
	
}
function callback3(data){
	if(data[0]==null){
		document.forms[0].action="user.do?method=search";
		document.forms[0].submit();
	}
	
}
function resetPwd(){
	var tr=event.srcElement;
	while (tr && tr.tagName != "TR") {
			tr = tr.parentElement;
		}
	var userId=tr.cells[6].children[2].value;
	if(window.confirm("确认重置该用户密码？")==true){
		$.ajax({
		      type:"POST",
		      url:"user.do",
		      data:"method=resetPassword&userId="+userId,
		      dataType:"json",
		      success:callback4,
		      error:callback1
		});
	}
	 
}
function callback4(data){
	if(data[0]==null){
		alert("密码已重置");
		document.forms[0].action="user.do?method=search";
		document.forms[0].submit();
	}
	
}

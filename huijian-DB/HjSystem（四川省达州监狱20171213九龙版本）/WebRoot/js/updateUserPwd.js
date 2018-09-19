function save(){
	var password=document.getElementById("password").value;
	var password1=document.getElementById("password1").value;
	var password2=document.getElementById("password2").value;
	var userId=document.getElementById("userId").value;
	var loginPassword=document.getElementById("loginPassword").value;
	password=password.replace(/\s+$/g,"");
	password1=password1.replace(/\s+$/g,"");
	password2=password2.replace(/\s+$/g,"");
	if(password =="" && password.length<=0){
		alert("请输入原始密码");
		return false;
	}
	if(password1 =="" && password1.length<=0){
		alert("请输入新密码");
		return false;
	}
	if(password =="" && password.length<=0){
		alert("请输入确认新密码");
		return false;
	}
	if(password.toLowerCase!=loginPassword.toLowerCase){
		document.getElementById("info").style.display="block"; 
		return false;
	}
	if(password.indexOf("'")>-1){
		alert("原始密码输入有误，不能包含特殊符号");
		return false;
	}
	if(password1.indexOf("'")>-1){
		alert("新密码输入有误，不能包含特殊符号");
		return false;
	}
	if(password2.indexOf("'")>-1){
		alert("确认密码输入有误，不能包含特殊符号");
		return false;
	}
	if(password1!=password2){
		alert("输入密码不一致");
		document.getElementById("password2").value="";
		return false;
	}
	document.forms[0].action=basePath+"updateLoginUser.do?method=updatePasswordUser";
	document.forms[0].submit();
	
}
function chongzhi(){
	document.getElementById("password").value="";
	document.getElementById("password1").value="";
	document.getElementById("password2").value="";
}
function saveYj(){
	var password=document.getElementById("password").value;
	var password1=document.getElementById("password1").value;
	var password2=document.getElementById("password2").value;
	var userId=document.getElementById("userId").value;
	var loginPassword=document.getElementById("loginPassword").value;
	password=password.replace(/\s+$/g,"");
	password1=password1.replace(/\s+$/g,"");
	password2=password2.replace(/\s+$/g,"");
	if(password =="" && password.length<=0){
		alert("请输入原始密码");
		return false;
	}
	if(password1 =="" && password1.length<=0){
		alert("请输入新密码");
		return false;
	}
	if(password =="" && password.length<=0){
		alert("请输入确认新密码");
		return false;
	}
	if(password.toLowerCase!=loginPassword.toLowerCase){
		document.getElementById("info").style.display="block"; 
		return false;
	}
	if(password1!=password2){
		alert("输入密码不一致");
		document.getElementById("password2").value="";
		return false;
	}
	var lujin=document.getElementById("lujin").value;
	var url=lujin+"updateLoginUser.do?method=updatePasswordYj"
	document.forms[0].action=url;
	document.forms[0].submit();
	
}
function goHomeYJ(){
     if(window.confirm("退出登录?")==true){
    	 var lujin=document.getElementById("lujin").valuel;
    	 var url=lujin+"updateLoginUser.do"
    	 window.top.location.href("login.jsp");
         $.ajax({
	      type:"POST",
	      url:url,
	      data:"method=closeSessionYj",
	      dataType:"json",
	      success:callback5
	    });
      }
  }
  function callback5(data){
	  window.top.location.href("login.jsp");
  }
  function goHomeUser(){
      if(window.confirm("退出登录?")==true){
    	   var lujin=document.getElementById("lujin").value;
     	   var url=lujin+"updateLoginUser.do";
     	  
               // top.window.opener=null; 
				//top.window.open("","_self"); 
         		//top.window.close(); 
         		 $.ajax({
         		      type:"POST",
         		      url:url,
         		      data:"method=closeSessionUser",
         		      dataType:"json",
         		      success:callback5
         		    });
         		
       }}

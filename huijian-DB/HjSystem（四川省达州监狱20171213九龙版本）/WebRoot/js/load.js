 	function login(){
	 	var username = $("#username").val();
		var password = $("#password").val();
		if(username.indexOf("'")>-1){
			alert("帐号输入有误，不能包含特殊符号");
			return false;
		}
		if(password.indexOf("'")>-1){
			alert("密码输入有误，不能包含特殊符号");
			return false;
		}
		//必须两次转码
		username=username.replace(/\s+$/g,""); //去右边空格
		username=encodeURI(username);
		username=encodeURI(username);
		password=encodeURI(password);
		password=encodeURI(password); 
	 	if(username.length<=0 || password.length<=0){
	 		alert("请输入帐号密码");
	 		return false;
	 	}
	   $.ajax({
	 	      type:"POST",
	 	      url:basePath+"login.do",
	 	      data:"method=login&aa="+username+"&bb="+password,
	 	      dataType:"json",
	 	      success:callback,
	 	      error:callback1
	 	});
 	
 	}
 
 function callback(data){
	 if(data[0] == 1){
		 document.getElementById("info").style.display="block"; 
		 document.getElementById("password").value="";
	 }else{ 
 		document.forms[0].action=basePath+"jsp/rsmain.jsp";
 		document.forms[0].submit();
 	 }
 }
 function callback1(a,b,c){
 	alert(a);
 	alert(b);
 	alert(c);
 
 }
 function enterSubmit(src,e){
         if(window.event)
             keyPressed = window.event.keyCode; // IE
         else
            keyPressed = e.which; // Firefox
         if(keyPressed==13){ 
        	 login();                     
             return false;
        }
}



	



 

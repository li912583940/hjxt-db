
function delUserTeam(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	 var isAdmin = tr.cells[4].children[2].value;
	 var groupNo = tr.cells[4].children[3].value;
	 var groupId = tr.cells[4].children[4].value;
	 groupNo=encodeURI(groupNo);
	 groupNo=encodeURI(groupNo);
	 if(isAdmin==1){
		 alert("不能删除管理员组");
	 }
	 else{
		 if(window.confirm("确认删除记录?")==true){
			 document.forms[0].action="userTeam.do?method=delUserTeam&groupNo="+groupNo+"&groupId="+groupId;
			 document.forms[0].submit();
		 }
	 }
	 
 }
 function callback1(a,b,c){
	 	alert(a);
	 	alert(b);
	 	alert(c);
	 }
  function sreachMenu(){
	  document.forms[0].action="userTeam.do?method=sreachMenu";
	  document.forms[0].submit();
  }
  function addUserGroup(){
	  var groupNo = $("#groupNo").val();
	  var groupName = $("#groupName").val();
	  var deptName = $("#deptName").val();
	  var groupName1=groupName.replace(/\s+$/g,"");
	  var groupNo1=groupNo.replace(/\s+$/g,"");
	  if(groupNo1.length<=0){
		  alert("用户编号不能为空 ");
		  document.getElementById("groupNo").value="";
		  return false;
	  }
	  if(groupNo.length>groupNo1.length){
		  alert("用户编号里有特殊符号，请重新输入 ");
		  document.getElementById("groupNo").value="";
	  }
	  if(groupName1.length<=0){
		  alert("用户组名不能为空 ");
		  document.getElementById("groupName").value="";
		  return false;
	  }
	  if(groupNo.indexOf("'")>-1){
		  alert("用户组编号输入有误，不能包含特殊符号");
		  return false;
	  }
	  if(groupName1.indexOf("'")>-1){
		  alert("用户组名输入有误，不能包含特殊符号");
		  return false;
	  }
	  groupNo1=encodeURI(encodeURI(groupNo1));
	  groupName1=encodeURI(encodeURI(groupName1));
	  deptName=encodeURI(encodeURI(deptName));
	  
	  var isSpDept = $("#isSpDept").val();
	  var spQxJb = $("#spQxJb").val();
	  if(isSpDept==1 && spQxJb==0){
		  alert("审批部门级别不能是0");
		  return false;
	  }
	  
	  $.ajax({
 	      type:"POST",
 	      url:"userTeam.do",
 	      data:"method=addUserTeam&groupNo="+groupNo1+"&groupName="+groupName1+"&deptName="+deptName+"&isSpDept="+isSpDept+"&spQxJb="+spQxJb,
 	      dataType:"json",
 	      success:callback2,
 	      error:callback1
 	});
  }
  function callback2(data){
	  if(data[0]==null){
		  alert("该用户组以存在请重新输入");
		  document.getElementById("groupNo").value="";
		  document.getElementById("groupName").value="";
		  var menuArray=document.getElementsByName("checkbox");
		  for(var i=0;i<menuArray.length;i++){
			  menuArray[i].checked=false;
		  }
	  }else{
		  document.forms[0].action="userTeam.do?method=search";
		  document.forms[0].submit();
	  }
	  
  }
  function updateUserTeam(){
		var tr = event.srcElement;
		while (tr && tr.tagName != "TR") {
			tr = tr.parentElement;
		}
		 var isAdmin = tr.cells[4].children[2].value;
		 var groupNo = tr.cells[4].children[3].value;
		 var groupId = tr.cells[4].children[4].value;
		 groupNo=encodeURI(encodeURI(groupNo));
		 groupId=encodeURI(encodeURI(groupId));
		 if(isAdmin==1){
			 alert("不能修改管理员组");
			 return false;
		 }
		 else{
			 document.forms[0].action="userTeam.do?method=updateUserGroup&groupNo="+groupNo+"&groupId="+groupId;
			 document.forms[0].submit();
		 }
	  
  }
  function saveUpdateUserGroup(){
	  var groupNo=document.getElementById("groupNo").value;
	  var groupName=document.getElementById("groupName").value;
	  var deptName = $("#deptName").val();
	  var groupName1=groupName.replace(/\s+$/g,"");
	  if(groupName1.length<=0){
		  alert("用户组名不能为空 ");
		  document.getElementById("groupName").Value="";
		  return false;
	  }
	  if(groupName1.indexOf("'")>-1){
		  alert("用户组名输入有误，不能包含特殊符号");
		  return false;
	  }
	  var groupId=document.getElementById("groupId").value;
	  groupNo=encodeURI(encodeURI(groupNo));
	  groupName=encodeURI(encodeURI(groupName));
	    deptName=encodeURI(encodeURI(deptName));
	    
	    var isSpDept = $("#isSpDept").val();
		  var spQxJb = $("#spQxJb").val();
		  if(isSpDept==1 && spQxJb==0){
			  alert("审批部门级别不能是0");
			  return false;
		  }
		  
	  $.ajax({
 	      type:"POST",
 	      url:"userTeam.do",
 	      data:"method=savaUpdateUserGroup&groupNo="+groupNo+"&groupName="+groupName+"&groupId="+groupId+"&deptName="+deptName+"&isSpDept="+isSpDept+"&spQxJb="+spQxJb,
 	      dataType:"json",
 	      success:callback3,
 	      error:callback1
 	});
	  
  }
   function callback3(data){
	   if(data[0]==null){
		   document.forms[0].action="userTeam.do?method=search";
		   document.forms[0].submit();
	   }
   }
   function setSlicGroup(){
	    var tr = event.srcElement;
		while (tr && tr.tagName != "TR") {
			tr = tr.parentElement;
		}
		var isAdmin = tr.cells[4].children[2].value;
		var groupNo = tr.cells[4].children[3].value;
		var groupId = tr.cells[4].children[4].value;
		if(isAdmin==1){
			alert("不能修改管理员组权限");
			return false;
		}
		groupNo=encodeURI(encodeURI(groupNo));
		document.forms[0].action="userTeam.do?method=listSlicGroup&groupId="+groupId+"&groupNo="+groupNo;
		document.forms[0].submit();
   }
   function updateSaveLimt(){
	   var groupNo=document.getElementById("groupNo").value;
	   var slicGroupListbox=document.getElementsByName("slicGroupListbox");
	   var flag="failure"
	   var arr=""
	   for(var i=0;i<slicGroupListbox.length;i++){
		   if(slicGroupListbox[i].checked){
			    arr+=slicGroupListbox[i].value;
			    arr+=","
			    flag="true";
		   }
	   }
	   if(flag=="failure"){
		   alert("至少选择一个监区权限");
		   return false;
	   }
	   var menubox=document.getElementsByName("menubox")
	   var flag1="failure"
	   var arr1=""
	   for(var i=0;i<menubox.length;i++){
			if(menubox[i].checked){
					    arr1+=menubox[i].value;
					    arr1+=","
					    flag1="true";
				   }
			   }
			if(flag1=="failure"){
				   alert("至少选择一个监区权限");
				   return false;
			   }
		groupNo=encodeURI(encodeURI(groupNo));
		arr=encodeURI(encodeURI(arr));
		arr1=encodeURI(encodeURI(arr1));
		 $.ajax({
	 	      type:"POST",
	 	      url:"userTeam.do",
	 	      data:"method=updateSaveLimt&groupNo="+groupNo+"&slicGroupList="+arr+"&menu="+arr1,
	 	      dataType:"json",
	 	      success:callback4,
	 	      error:callback1
	 	});
		
   }
function callback4(data){
	if(data[0]==null){
		document.forms[0].action="userTeam.do?method=search";
		document.forms[0].submit();
	}
}
function checkGroupUser(){
	 var tr = event.srcElement;
	 while (tr && tr.tagName != "TR") {
			tr = tr.parentElement;
		}
	
	var groupNo = tr.cells[4].children[3].value;
	 groupNo=encodeURI(encodeURI(groupNo));
	document.forms[0].action="userTeam.do?method=searchUser&groupNo="+groupNo;
	document.forms[0].submit();
}
function checkAll(){
	var alljq=document.getElementsByName("Alljq");
	var slicGroupListbox=document.getElementsByName("slicGroupListbox");
	for(var i=0;i<slicGroupListbox.length;i++){
		slicGroupListbox[i].checked=alljq[0].checked;
	}
}
function checkAll1(){
	var allLine=document.getElementsByName("AllLine");
	var menubox=document.getElementsByName("menubox");
	for(var i=0;i<menubox.length;i++){
		menubox[i].checked=allLine[0].checked;
	}
}
function selectSpbm(){
	var isSpDept=document.getElementById("isSpDept");
	var isSpDept1="";
	for(var i=0;i<isSpDept.length;i++){
		if(isSpDept.options[i].selected){
			isSpDept1=isSpDept.options[i].value;
			break;
		}
	}
	if(isSpDept1=="1"){
		document.getElementById("spQxJb").disabled=false;
	}else{
		var spQxJb=document.getElementById("spQxJb");
		spQxJb.options.length=0;
		spQxJb.add(new  Option("0","0"));
		spQxJb.add(new  Option("1","1"));
		spQxJb.add(new  Option("2","2"));
		spQxJb.add(new  Option("3","3"));
		spQxJb.add(new  Option("4","4"));
		spQxJb.add(new  Option("5","5"));
		spQxJb.add(new  Option("6","6"));
//		document.getElementById("spQxJb").options[0].selected=true;
		document.getElementById("spQxJb").disabled=true;
	}
}

	 
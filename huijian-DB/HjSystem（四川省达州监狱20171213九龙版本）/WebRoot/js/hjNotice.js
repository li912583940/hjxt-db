function refreshMeetInfo(){
 		 $.ajax({
 		      type:"POST",
 		      url:"hjNotice.do",
 		      data:"method=jquerSearch",
 		      dataType:"json",
 		      success:callback2   
 		});
}

function callback2(data){
	var hjdjTable=document.getElementById("hjdjTable");
	var hjtz = data[0].list1;
	var hjtz4 =data[0].list4;
	var pageTzState =data[0].pageTzState;
	var rows = hjdjTable.rows.length;
	var yifenpei=0;
	var weifenpei=0;
	var yifaqi=0;
	var weifaqi=0;
	
	if(pageTzState==1){
		hjdjTable.innerText="";
		for(var i=0;i<hjtz.length;i++){
			var tr=hjdjTable.insertRow();
			tr.onmouseover=function(){this.style.background ='#FFC1C1';};
			tr.onmouseout=function(){this.style.background ='#FFFFFF';};
			var td1=tr.insertCell();
			td1.noWrap=true;
			if(i==0){
				td1.innerHTML=hjtz[i].jqName+"<input type=\"hidden\" id=\"isNoPrint\" name=\"isNoPrint\" value=\"1\" /><input type=\"hidden\" name=\"webId\" value=\""+hjtz[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+hjtz[i].fpFlag+"\" />";
			}else{
				td1.innerHTML=hjtz[i].jqName+"<input type=\"hidden\" name=\"webId\" value=\""+hjtz[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+hjtz[i].fpFlag+"\" />";
			}
			var td2=tr.insertCell();
			td2.noWrap=true;
			td2.innerText=hjtz[i].frNo;
			var td4=tr.insertCell();
			td4.noWrap=true;
			td4.innerText=hjtz[i].frName;
			var td5=tr.insertCell();
			td5.noWrap=true;
			if(hjtz[i].fpFlag=='0'){
				td5.innerHTML="<font color=\"red\">未分配</font>";
				weifenpei++;
			}else{
				td5.innerText=hjtz[i].zw;
				yifenpei++;
			}
			var td6=tr.insertCell();
			td6.noWrap=true;
			if(hjtz[i].pageTzState==0){
				td6.innerHTML="<a href=\"javascript:void(0)\" onclick=\"sdNotice();return false;\"><font color=\"red\">未接收</font></a>";
				weifaqi++;
			}else if(hjtz[i].pageTzState==1){
				td6.innerText="已接收";
				yifaqi++;
			}
			var td7=tr.insertCell();
			td7.noWrap=true;
			if(hjtz[i].hjType==1){
				td7.innerText="电话会见";
			}else if(hjtz[i].hjType==2){
				td7.innerHTML="<font color=\"red\">面对面会见</font>";
			}else if(hjtz[i].hjType==3){
				td7.innerHTML="<font color=\"red\">视频会见</font>";
			}else if(hjtz[i].hjType==4){
				td7.innerHTML="<font color=\"red\">帮教</font>";
			}else if(hjtz[i].hjType==5){
				td7.innerHTML="<font color=\"red\">提审</font>";
			}
			var td11=tr.insertCell();
			td11.innerText=hjtz[i].hjInfo;
			var td8=tr.insertCell();
			td8.innerText=hjtz[i].djTime;
			var td9=tr.insertCell();
			td9.innerText=hjtz[i].qsInfo1;
			var td10=tr.insertCell();
			td10.noWrap=true;
			if(hjtz[i].fpFlag=='2'){
				td10.innerHTML="<font color=\"red\">已在会见</font>";
			}else{
				td10.innerHTML="未在会见";
			}
			var td12=tr.insertCell();
			td12.noWrap=true;
			td12.innerHTML=hjtz[i].pageTzUserNo+"<br>"+hjtz[i].pageTzUserName;
			var td13=tr.insertCell();
			td13.innerText=hjtz[i].pageTzTime;
			var td14=tr.insertCell();
			if(hjtz[i].isOverTime=='1'){
				td14.innerHTML="<font color=\"red\">已超时</font>";
			}else{
				td14.innerHTML="未超时";
			}
		}
//		document.getElementById("info3").innerHTML="当前座位已分配&nbsp;&nbsp;<font color=\"red\">"+yifenpei+"</font>，&nbsp;&nbsp;未分配&nbsp;&nbsp;<font color=\"red\">"+weifenpei+"</font>；&nbsp;&nbsp;当前通知 已发起&nbsp;&nbsp;<font color=\"red\">"+yifaqi+"</font>，&nbsp;&nbsp;未发起&nbsp;&nbsp;<font color=\"red\">"+weifaqi+"</font>";
		document.getElementById("info3").innerHTML="当前座位已分配&nbsp;&nbsp;<font color=\"red\">"+yifenpei+"</font>，&nbsp;&nbsp;未分配&nbsp;&nbsp;<font color=\"red\">"+weifenpei+"</font>";
		if(hjtz4[0].tzHandle==1){
			alert("注意：探访中心已停止办理会见  发起时间："+hjtz4[0].tzSJ);
		}
		document.getElementById("Mediaplayer").controls.play();
	}else{
		var webId=document.getElementsByName("webId");
		if(hjtz.length>0){
			
			hjdjTable.innerText="";
			for(var i=0;i<hjtz.length;i++){
				var tr=hjdjTable.insertRow();
				tr.onmouseover=function(){this.style.background ='#FFC1C1';};
				tr.onmouseout=function(){this.style.background ='#FFFFFF';};
				var td1=tr.insertCell();
				td1.noWrap=true;
				if(i==0){
					td1.innerHTML=hjtz[i].jqName+"<input type=\"hidden\" id=\"isNoPrint\" name=\"isNoPrint\" value=\"1\" /><input type=\"hidden\" name=\"webId\" value=\""+hjtz[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+hjtz[i].fpFlag+"\" />";
				}else{
					td1.innerHTML=hjtz[i].jqName+"<input type=\"hidden\" name=\"webId\" value=\""+hjtz[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+hjtz[i].fpFlag+"\" />";
				}
				var td2=tr.insertCell();
				td2.noWrap=true;
				td2.innerText=hjtz[i].frNo;
				var td4=tr.insertCell();
				td4.noWrap=true;
				td4.innerText=hjtz[i].frName;
				var td5=tr.insertCell();
				td5.noWrap=true;
				if(hjtz[i].fpFlag=='0'){
					td5.innerHTML="<font color=\"red\">未分配</font>";
					weifenpei++;
				}else{
					td5.innerText=hjtz[i].zw;
					yifenpei++;
				}
				var td6=tr.insertCell();
				td6.noWrap=true;
				if(hjtz[i].pageTzState==0){
					td6.innerHTML="<a href=\"javascript:void(0)\" onclick=\"sdNotice();return false;\"><font color=\"red\">未接收</font></a>";
					weifaqi++;
				}else if(hjtz[i].pageTzState==1){
					td6.innerText="已接收";
					yifaqi++;
				}

				var td7=tr.insertCell();
				td7.noWrap=true;
				if(hjtz[i].hjType==1){
					td7.innerText="电话会见";
				}else if(hjtz[i].hjType==2){
					td7.innerHTML="<font color=\"red\">面对面会见</font>";
				}else if(hjtz[i].hjType==3){
					td7.innerHTML="<font color=\"red\">视频会见</font>";
				}else if(hjtz[i].hjType==4){
					td7.innerHTML="<font color=\"red\">帮教</font>";
				}else if(hjtz[i].hjType==5){
					td7.innerHTML="<font color=\"red\">提审</font>";
				}
				var td11=tr.insertCell();
				td11.innerText=hjtz[i].hjInfo;
				var td8=tr.insertCell();
				td8.innerText=hjtz[i].djTime;
				var td9=tr.insertCell();
				td9.innerText=hjtz[i].qsInfo1;
				var td10=tr.insertCell();
				td10.noWrap=true;
				if(hjtz[i].fpFlag=='2'){
					td10.innerHTML="<font color=\"red\">已在会见</font>";
				}else{
					td10.innerHTML="未在会见";
				}
				var td12=tr.insertCell();
				td12.noWrap=true;
				td12.innerHTML=hjtz[i].pageTzUserNo+"<br>"+hjtz[i].pageTzUserName;
				var td13=tr.insertCell();
				td13.innerText=hjtz[i].pageTzTime;
				var td14=tr.insertCell();
				if(hjtz[i].isOverTime=='1'){
					td14.innerHTML="<font color=\"red\">已超时</font>";
				}else{
					td14.innerHTML="未超时";
				}
			}
//			document.getElementById("info3").innerHTML="当前座位已分配&nbsp;&nbsp;<font color=\"red\">"+yifenpei+"</font>，&nbsp;&nbsp;未分配&nbsp;&nbsp;<font color=\"red\">"+weifenpei+"</font>；&nbsp;&nbsp;当前通知 已发起&nbsp;&nbsp;<font color=\"red\">"+yifaqi+"</font>，&nbsp;&nbsp;未发起&nbsp;&nbsp;<font color=\"red\">"+weifaqi+"</font>";
			document.getElementById("info3").innerHTML="当前座位已分配&nbsp;&nbsp;<font color=\"red\">"+yifenpei+"</font>，&nbsp;&nbsp;未分配&nbsp;&nbsp;<font color=\"red\">"+weifenpei+"</font>";
			if(hjtz4[0].tzHandle==1){
				alert("注意：探访中心已停止办理会见  发起时间："+hjtz4[0].tzSJ);
			}
		}else{
			hjdjTable.innerText="";
			var tr=hjdjTable.insertRow();
			var td=tr.insertCell();
			td.colSpan=14;
			td.innerHTML="<font color=\"red\">没有相关信息</font>";
		}
	}
		
}

function refreshMeetInfo3(){
	 $.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=jquerSearch",
	      dataType:"json",
	      success:refresh
	});
}
function refresh(data){
	var hjdjTable=document.getElementById("hjdjTable");
	hjdjTable.innerText="";
	if(data.length>0){
		for(var i=0;i<data.length;i++){
			var tr=hjdjTable.insertRow();
			tr.onmouseover=function(){this.style.background ='#FFC1C1';};
			tr.onmouseout=function(){this.style.background ='#FFFFFF';};
			var td1=tr.insertCell();
			td1.noWrap=true;
			td1.innerHTML="<a href=\"javacript:void(0)\" onclick=\"yjcome();return false;\"><font color=\"Fuchsia\">警察签到</font></a>&nbsp;&nbsp;<a href=\"javacript:void(0)\" onclick=\"delQd();return false;\">取消签到</a>&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"rgfp();return false;\"><font color=\"Olive\">人工分配</font></a><input type=\"hidden\" name=\"hjid\" value=\""+data[i].hjid+"\"/>";
			var td2=tr.insertCell();
			td2.noWrap=true;
			if(i==0){
				td2.innerHTML=data[i].jqName+"<input type=\"hidden\" id=\"isNoPrint\" name=\"isNoPrint\" value=\"1\" /><input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
			}else{
				td2.innerHTML=data[i].jqName+"<input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
			}
//			var td1=tr.insertCell();
//			td1.noWrap=true;
//			td1.innerText=data[i].hjIndex;
			var td3=tr.insertCell();
			td3.noWrap=true;
			td3.innerText=data[i].frName;
			
			var td4=tr.insertCell();
			td4.noWrap=true;
			if(data[i].hjType==1){
				td4.innerText="考察";
			}else if(data[i].hjType==2){
				td4.innerHTML="<font color=\"red\">宽管<font>";
			}else if(data[i].hjType==3){
				td4.innerHTML="<font color=\"red\">普管<font>";
			}else if(data[i].hjType==4){
				td4.innerHTML="<font color=\"red\">帮教<font>";
			}else if(data[i].hjType==5){
				td4.innerHTML="<font color=\"red\">提审<font>";
			}
			var td5=tr.insertCell();
			td5.noWrap=true;
			td5.innerText=data[i].hjInfo;
			var td6=tr.insertCell();
			td6.noWrap=true;
			if(data[i].fpFlag=='0'){
				td6.innerHTML="<font color=\"red\">未分配</font>";
			}else{
				td6.innerText=data[i].zw;
			}
			var td7=tr.insertCell();
			td7.noWrap=true;
			td7.innerText=data[i].frNo;
			var td8=tr.insertCell();
			//td8.colSpan=2;
			td8.noWrap=true;
			td8.innerText=data[i].qsInfo1;
			var td9=tr.insertCell();
			//td4.colSpan=2;
			td9.noWrap=true;
			if(data[i].fpFlag=='0'){
				td9.innerHTML="<input type=\"checkbox\" name=\"selectId\" onclick=\"selectRecord("+data[i].hjid+")\" value=\""+data[i].hjid+"\" />";
			}
			if(data[i].fpFlag=='1'){
				td9.innerHTML="<input type=\"checkbox\" name=\"selectId\" disabled=\"disabled\" value=\""+data[i].hjid+"\" />";
			}
//			var td4=tr.insertCell();
//			td4.noWrap=true;
//			td4.innerText=data[i].hjTime+"分钟";
//			var td5=tr.insertCell();
//			td5.noWrap=true;
//			td5.innerText=data[i].djTime;
		}
	}else{
		hjdjTable.innerText="";
		var tr=hjdjTable.insertRow();
		var td=tr.insertCell();
		td.colSpan=9;
		td.innerHTML="<font color=\"red\">没有相关信息</font>";
	}
}
function refreshMeetInfo1(){
	 $.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=jquerSearch",
	      dataType:"json",
	      success:callback3   
	});
}
function callback3(data){
//	var hjdjTable=document.getElementById("hjdjTable");
//	hjdjTable.innerText="";
//	if(data.length>0){
//		for(var i=0;i<data.length;i++){
//			var tr=hjdjTable.insertRow();
//			tr.onmouseover=function(){this.style.background ='#FFFFFF';};
//			tr.onmouseout=function(){this.style.background ='#DDF2FF';};
//			//var djid=data[i].hjid;
//			//tr.onclick= new Function("selectRecord('"+djid+"');");
//			var td7=tr.insertCell();
//			td7.noWrap=true;
//			td7.innerHTML="<a href=\"javacript:void(0)\" onclick=\"yjcome();return false;\">警察签到</a>&nbsp;&nbsp;<a href=\"javacript:void(0)\" onclick=\"delQd();return false;\">取消签到</a>&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"rgfp();return false;\">人工分配</a><input type=\"hidden\" name=\"hjid\" value=\""+data[i].hjid+"\"/>";
//			var td=tr.insertCell();
//			td.noWrap=true;
//			if(i==0){
//				td.innerHTML=data[i].jqName+"<input type=\"hidden\" id=\"isNoPrint\" name=\"isNoPrint\" value=\"1\" /><input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
//			}else{
//				td.innerHTML=data[i].jqName+"<input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
//			}
//			var td2=tr.insertCell();
//			td2.noWrap=true;
//			td2.innerText=data[i].frName;
//			
//			var td10=tr.insertCell();
//			td10.noWrap=true;
//			if(data[i].hjType==1){
//				td10.innerText="严见";
//			}else if(data[i].hjType==2){
//				td10.innerHTML="<font color=\"red\">宽见<font>";
//			}
//			var td11=tr.insertCell();
//			td11.noWrap=true;
//			td11.innerText=data[i].hjInfo;
//			var td6=tr.insertCell();
//			td6.noWrap=true;
//			if(data[i].fpFlag=='0'){
//				td6.innerHTML="<font color=\"red\">未分配</font>";
//			}else{
//				td6.innerText=data[i].zw;
//			}
//			var td9=tr.insertCell();
//			td9.noWrap=true;
//			td9.innerText=data[i].frNo;
//			var td3=tr.insertCell();
//			td3.noWrap=true;
//			td3.innerText=data[i].qsInfo1;
//			var td13=tr.insertCell();
//			//if(data[i].infoWp==1){
//			//	td13.innerHTML="<font color=\"red\">有</font>";
//			//}else{
//			//	td13.innerHTML="<font>无</font>";
//			//}
//			if(data[i].fpFlag=='0'){
//				td13.innerHTML="<input type=\"checkbox\" name=\"selectId\"  value=\""+data[i].hjid+"\" />";
//			}else{
//				td13.innerHTML="<input type=\"checkbox\" name=\"selectId\" disabled=\"disabled\" value=\""+data[i].hjid+"\" />";
//			}
//			
//			
//		}
//	}else{
//		hjdjTable.innerText="";
//		var tr=hjdjTable.insertRow();
//		var td=tr.insertCell();
//		td.colSpan=9;
//		td.innerHTML="<font color=\"red\">没有相关信息</font>";
//	}
//	document.getElementById("rowIndex").value="";
	var hjdjTable=document.getElementById("hjdjTable");
	hjdjTable.innerText="";
	if(data.length>0){
		for(var i=0;i<data.length;i++){
			var tr=hjdjTable.insertRow();
			tr.onmouseover=function(){this.style.background ='#FFC1C1';};
			tr.onmouseout=function(){this.style.background ='#FFFFFF';};
			var td7=tr.insertCell();
			td7.noWrap=true;
			td7.innerHTML="<a href=\"javacript:void(0)\" onclick=\"yjcome1();return false;\">自动分配</a>&nbsp;&nbsp;<a href=\"javacript:void(0)\" onclick=\"delQd1();return false;\">取消分配</a>&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"rgfp1();return false;\">人工分配</a><input type=\"hidden\" name=\"hjid\" value=\""+data[i].hjid+"\"/>";
//			td7.innerHTML="<a href=\"javacript:void(0)\" onclick=\"yjcome1();return false;\">自动分配</a>&nbsp;&nbsp;<a href=\"javacript:void(0)\" onclick=\"delQd1();return false;\">取消分配</a>&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"rgfp1();return false;\">人工分配</a>&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"setCard();return false;\">设置卡号</a><input type=\"hidden\" name=\"hjid\" value=\""+data[i].hjid+"\"/>";
			var td=tr.insertCell();
			td.noWrap=true;
			if(i==0){
				td.innerHTML=data[i].jqName+"<input type=\"hidden\" id=\"isNoPrint\" name=\"isNoPrint\" value=\"1\" /><input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
			}else{
				td.innerHTML=data[i].jqName+"<input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
			}
			var td1=tr.insertCell();
			td1.noWrap=true;
			td1.innerText=data[i].jbName;
			var td2=tr.insertCell();
			td2.noWrap=true;
			td2.innerHTML="<font color=\"green\">"+data[i].frName+"</font>";
			var td12=tr.insertCell();
			td12.noWrap=true;
			if(data[i].stateZdzf=='否'){
				td12.innerText="否";
			}else{
				td12.innerHTML="<font color=\"red\">"+data[i].stateZdzf+"</font>";
			}
			
			var td10=tr.insertCell();
			td10.noWrap=true;
			if(data[i].hjType==1){
				td10.innerText="电话会见";
			}else if(data[i].hjType==2){
				td10.innerHTML="<font color=\"red\">面对面会见<font>";
			}else if(data[i].hjType==3){
				td10.innerHTML="<font color=\"red\">视频会见<font>";
			}else if(data[i].hjType==4){
				td10.innerHTML="<font color=\"red\">帮教<font>";
			}else if(data[i].hjType==5){
				td10.innerHTML="<font color=\"red\">提审<font>";
			}
			var td11=tr.insertCell();
			td11.noWrap=true;
			td11.innerText=data[i].hjInfo;
			var td6=tr.insertCell();
			td6.noWrap=true;
			if(data[i].fpFlag=='0'){
				td6.innerHTML="<font color=\"red\">未分配</font>";
			}else{
				td6.innerText=data[i].zw;
			}
			var td9=tr.insertCell();
			td9.noWrap=true;
			td9.innerText=data[i].frNo;
			var td3=tr.insertCell();
			td3.colSpan=2;
			td3.noWrap=true;
			td3.innerText=data[i].qsInfo1;
			var td4=tr.insertCell();
			td4.noWrap=true;
			td4.innerText=data[i].hjTime+"分钟";
			var td5=tr.insertCell();
			td5.noWrap=true;
			td5.innerText=data[i].djTime;
			var td13=tr.insertCell();
			td13.noWrap=true;
			if(data[i].shState==1){
				td13.innerText="已授权";
			}else{
				td13.innerHTML="<font color=\"red\">未授权<font>";
			}
			var td11=tr.insertCell();
			td11.noWrap=true;
			td11.innerHTML="<a href=\"javascript:void(0);\" onclick=\"updateShState();return false;\">授权</a>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"delShState();return false;\">取消授权</a>";;
		}
	}else{
		hjdjTable.innerText="";
		var tr=hjdjTable.insertRow();
		var td=tr.insertCell();
		td.colSpan=15;
		td.innerHTML="<font color=\"red\">没有相关信息</font>";
	}
	
}
function yjcome(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("hjid");
	var hjid1=hjid[tr.rowIndex-2].value;
	document.forms[0].action="yjCome.do?method=goToYjCome&hjid="+hjid1;
	document.forms[0].submit();
}
function fpZw(){
	var yjNum=document.getElementById("yjNum").value;
	var hjid=document.getElementById("hjid").value;
	yjNum=yjNum.replace(/\s+$/g,"");
	if(yjNum==''){
		alert("警察警号不能为空");
		return false;
	}
	if(yjNum.indexOf("'")>-1){
		alert("警察警号输入有误，不能包含特殊符号");
		return false;
	}
	
	yjNum=encodeURI(encodeURI(yjNum));
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=fpZw&hjid="+hjid+"&yjNum="+yjNum,
	      dataType:"json",
	      success:callback4   
	});
}
function callback4(data){
	//if(data[0]=='0'){
	//	var hjid=document.getElementById("hjid").value;
	//	document.forms[0].action=basePath+"yjCome.do?method=search2";
	//	document.forms[0].submit();
		//var url=basePath+"yjCome.do?method=printXp&webId="+hjid;
		//val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
	//}else
	//通用型if(data[0]=='-1'){
	if(data[0]=='1'){
		alert("当前已经没有空闲窗口");
		return false;
	//通用型}else if(data[0]=='-2'){
	}else if(data[0]=='2'){
		alert("警察警号不存在，请重新输入");
		return false;
	}else{
		//通用型才有alert(data[0].frName+" : "+data[0].xx);
		document.forms[0].action=basePath+"yjCome.do?method=search";
		document.forms[0].submit();
	}
}
function sdNotice(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("webId");
	var webId=hjid[tr.rowIndex-1].value
	 $.ajax({
	      type:"POST",
	      url:"hjNotice.do",
	      data:"method=sdNotice&hjid="+webId,
	      dataType:"json",
	      success:callback5   
	});
}
function callback5(data){
	document.forms[0].action=basePath+"hjNotice.do?method=search";
	document.forms[0].submit();
}
function delQd(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("webId");
	var webId=hjid[tr.rowIndex-2].value
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=delQd&hjid="+webId,
	      dataType:"json",
	      success:callback6   
	});
}
function callback6(data){
	if(data[0]=='0'){
		alert("已取消签到");
		document.forms[0].action=basePath+"yjCome.do?method=search2";
		document.forms[0].submit();
	}else if(data[0]=='1'){
		alert("已处于会见通话状态，无法取消");
		return false;
	}
}
function yjComeSearch(){
	var frName=document.getElementById("frName").value;
	var frNo=document.getElementById("frNo").value;
	//var hjIndex=document.getElementById("hjIndex").value;
	if(frName.indexOf("'")>-1){
		alert("罪犯姓名输入有误，不能包含特殊符号");
		return false;
	}
	if(frNo.indexOf("'")>-1){
		alert("罪犯编号输入有误，不能包含特殊符号");
		return false;
	}
//	if(hjIndex.indexOf("'")>-1){
//		alert("会见编号输入有误，不能包含特殊符号");
//		return false;
//	}
	document.forms[0].action=basePath+"yjCome.do?method=search";
	document.forms[0].submit();
}
function enterSubmit(src,e){
    var keyPressed;
    if(window.event){
   	 keyPressed = window.event.keyCode; // IE
    }else{
       keyPressed = e.which; // Firefox
    }
    if(keyPressed==13){ 
    	yjComeSearch();                     
        return false;
    }
}
function changeState(hjid1){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var infowps=document.getElementsByName("infowp");
	if(infowps[tr.rowIndex-1].checked){
		$.ajax({
		      type:"POST",
		      url:"hjNotice.do",
		      data:"method=changeWpState&hjid="+hjid1+"&infoWp=1",
		      dataType:"json",
		      success:callback7   
		});
	}else{
		$.ajax({
		      type:"POST",
		      url:"hjNotice.do",
		      data:"method=changeWpState&hjid="+hjid1+"&infoWp=0",
		      dataType:"json",
		      success:callback7   
		});
	}
  
}
function callback7(data){
	return false;
}
function rgfp(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("hjid");
	var hjid1=hjid[tr.rowIndex-2].value;
	document.forms[0].action="yjCome.do?method=rgfp&hjid="+hjid1;
	document.forms[0].submit();
}
function checkLine(){
	
	var hjType=document.getElementById("hjType").value;
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
		      url:basePath+"yjCome.do",
		      data:"method=checkLine&jy1="+jy1+"&hjType="+hjType,
		      dataType:"json",
		      success:callback8
		});
	}else{
		var zwNo=document.getElementById("zwNo");
		zwNo.options.length=0;
		zwNo.add(new  Option("全部","null"));
	}
}
function callback8(data){
	var zwNo=document.getElementById("zwNo");
	zwNo.options.length=0;   
	if(data.length>0){
		zwNo.add(new  Option("全部","null")); 
		for(var i=0;i<data.length;i++){
			zwNo.add(new  Option(data[i].zw,data[i].lineNo)); 
		}
	}else{
		zwNo.add(new  Option("全部","null")); 
	}
	return false;
}
function rgfpZw(){
	var jy=document.getElementById("jy");
	var jy1="";
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy1=jy.options[i].value;
			break;
		}
	}
	if(jy1=='null'){
		alert("请选择服务器");
		return false;
	}
	var zwNo=document.getElementById("zwNo");
	var zwNo1="";
	for(var i=0;i<zwNo.length;i++){
		if(zwNo.options[i].selected){
			zwNo1=zwNo.options[i].value;
			break;
		}
	}
	if(zwNo1=='null'){
		alert("请选择座位");
		return false;
	}
	var yjNum=document.getElementById("yjNum").value;
	var hjid=document.getElementById("hjid").value;
	yjNum=yjNum.replace(/\s+$/g,"");
	if(yjNum==''){
		alert("警察警号不能为空");
		return false;
	}
	if(yjNum.indexOf("'")>-1){
		alert("警察警号输入有误，不能包含特殊符号");
		return false;
	}
	yjNum=encodeURI(encodeURI(yjNum));
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=rgfpZw&hjid="+hjid+"&yjNum="+yjNum+"&jy="+jy1+"&zwNo="+zwNo1,
	      dataType:"json",
	      success:callback9   
	});
}
function callback9(data){
	if(data[0]=='0'){
		var hjid=document.getElementById("hjid").value;
		document.forms[0].action=basePath+"yjCome.do?method=search";
		document.forms[0].submit();
	//	var url=basePath+"yjCome.do?method=printXp&webId="+hjid;
	//	val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
	}else if(data[0]=='1'){
		alert("线路已被分配");
		return false;
	}else if(data[0]=='2'){
		alert("警察警号不存在，请重新输入");
		return false;
	}
}
function jrNotice(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("webId");
	var webId=hjid[tr.rowIndex-1].value
	 $.ajax({
	      type:"POST",
	      url:"hjNotice.do",
	      data:"method=jrNotice&hjid="+webId,
	      dataType:"json",
	      success:callback11   
	});
}
function callback11(data){
	document.forms[0].action=basePath+"hjNotice.do?method=search";
	document.forms[0].submit();
}
function checkDj(){
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
	document.forms[0].action="cancelDj.do?method=search";
	document.forms[0].submit();
}
function checkDj1(){
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
	document.forms[0].action="cancelDj.do?method=search4";
	document.forms[0].submit();
}
function dcCancelDjSearch(){
	var isDc=document.getElementById("isDc").value;
	if(isDc==0){
		alert("请查询后再点击导出按钮");
		return false;
	}else{
		document.forms[0].action="cancelDj.do?method=dcCancelDjSearch";
		document.forms[0].submit();
	}
}
function refreshMeetInfo2(){
	 var cardNum=document.getElementById("cardNum").value;
	 if(cardNum.length>3){
		 alert(cardNum);
		 document.getElementById("cardNum").value="";
	 }
}
function yjcome1(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("webId");
	var webId=hjid[tr.rowIndex-1].value
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=fpZw1&hjid="+webId,
	      dataType:"json",
	      success:callback10   
	});
}
function callback10(data){
	if(data[0]=='3'){
		alert("电脑IP地址非法");
		return false;
	}else if(data[0]=='2'){		
		alert("当前记录已分配座位");
		return false;
	}else if(data[0]=='1'){
		alert("当前已没有空闲座位可供使用");
		return false;
	}else{
//		var url=basePath+"yjCome.do?method=printXp&webId="+data[0];
//		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		document.forms[0].action=basePath+"yjCome.do?method=search1";
		document.forms[0].submit();
	}
}
function delQd1(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("webId");
	var webId=hjid[tr.rowIndex-1].value
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=delQd&hjid="+webId,
	      dataType:"json",
	      success:callback21   
	});
}
function callback21(data){
	if(data[0]=='3'){
		alert("电脑IP地址非法");
		return false;
	}else if(data[0]=='0'){
		alert("已取消签到");
		document.forms[0].action=basePath+"yjCome.do?method=search1";
		document.forms[0].submit();
	}else if(data[0]=='1'){
		alert("已处于会见通话状态，无法取消");
		return false;
	}
}
function rgfp1(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("hjid");
	var hjid1=hjid[tr.rowIndex-1].value;
	document.forms[0].action="yjCome.do?method=rgfp1&hjid="+hjid1;
	document.forms[0].submit();
}
function rgfpZw1(){
	var jy=document.getElementById("jy");
	var jy1="";
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy1=jy.options[i].value;
			break;
		}
	}
	if(jy1=='null'){
		alert("请选择服务器");
		return false;
	}
	var zwNo=document.getElementById("zwNo");
	var zwNo1="";
	for(var i=0;i<zwNo.length;i++){
		if(zwNo.options[i].selected){
			zwNo1=zwNo.options[i].value;
			break;
		}
	}
	if(zwNo1=='null'){
		alert("请选择座位");
		return false;
	}
	var hjid=document.getElementById("hjid").value;
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=rgfpZw1&hjid="+hjid+"&jy="+jy1+"&zwNo="+zwNo1,
	      dataType:"json",
	      success:callback12   
	});
}
function callback12(data){
	if(data[0]=='3'){
		alert("电脑IP地址非法");
		return false;
	}else if(data[0]=='2'){		
		alert("当前记录已分配座位");
		return false;
	}else if(data[0]=='1'){
		alert("当前已没有空闲座位可供使用");
		return false;
	}else{
//		var url=basePath+"yjCome.do?method=printXp&webId="+data[0];
//		val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		document.forms[0].action=basePath+"yjCome.do?method=search1";
		document.forms[0].submit();
	}
}



function callback13(data){
	 if(data[0]=='-1'){
    	alert("该卡号不存在");
    	return false;
    }if(data[0]=='-2'){
    	alert("今天没有登记");
    	return false;
    }if(data[0]=='-3'){
    	alert("登记已取消");
    	return false;
    }if(data[0]=='-4'){
    	alert("该卡号没有被登记会见");
    	return false;
    }if(data[0]=='-5'){
    	alert("重复刷卡");
    	return false;
    }if(data[0]=='-6'){
    	alert("登记未审批");
    	return false;
    }if(data[0]=='-7'){
    	alert("座位已满，请稍后");
    	return false;
    }if(data[0]=='-8'){
    	alert("座位已分配");
    	return false;
    }if(data[0]=='-9'){
    	alert("卡信息不存在");
    	return false;
    }else{
    	refreshMeetInfo1();
		//document.forms[0].action=basePath+"yjCome.do?method=search1";
		//document.forms[0].submit();
	}
}
function setCard(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("hjid");
	var hjid1=hjid[tr.rowIndex-1].value;
	document.forms[0].action="yjCome.do?method=setCard&hjid="+hjid1;
	document.forms[0].submit();
}
function updateSaveFr(){
	var frId=document.getElementById("frId").value;
	var frCard=document.getElementById("frCard").value;
	var djId=document.getElementById("djId").value;
	frCard=frCard.replace(/\s+$/g,"");
	if(frCard==''){
		alert("IC卡号不能为空");
		return false;
	}
	if(frCard.indexOf("'")>-1){
		alert("罪犯IC卡输入有误，不能包含特殊符号");
		return false;
	}
	frCard=encodeURI(encodeURI(frCard));
	 $.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=updateSaveFr&frId="+frId+"&frCard="+frCard+"&djId="+djId,
	      dataType:"json",
	      success:callback31
	});
}
function callback31(data){
	if(data[0]=="1"){
		alert("座位已满，请稍后");
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}else if(data[0]=="2"){
		alert("已经分配了座位");
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();		
	}else{
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}
}
function selectRecord(webId){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	document.getElementById("rowIndex").value=tr.rowIndex-2;
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=selectRecord&webId="+webId,
	      dataType:"json",
	      success:callback33
	});
}
function callback33(data){
	
	if(data[0]=="0"){
		
	}else{
		alert("该罪犯座位已被分配");
		var selectIds=document.getElementsByName("selectId");
		selectIds[document.getElementById("rowIndex").value].disabled=true;
		return false;
	}
}
//广东揭阳
function plQd(){
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=jquerSearch1",
	      dataType:"json",
	      success:callback35
	      
	});
}
function callback35(list){
	var selectIds=document.getElementsByName("selectId");
	var str='';
	var n=0;
	for(var i=0;i<selectIds.length;i++){
		if(selectIds[i].checked){
			if(n==0){
				str+=selectIds[i].value;
				n++;
			}else{
				str+=";"+selectIds[i].value;
				n++;
			}
		}
	}
	if(n==0){
		alert("请选中你要批量分配座位的记录！");
		return false;
	}
	if(n>list){
		if(window.confirm("当前三楼只有  "+list+" 个空闲座位，是否继续在四楼寻找空闲座位！")==false){
			return false;
		}
		$.ajax({
		      type:"POST",
		      url:"yjCome.do",
		      data:"method=jquerSearch2",
		      dataType:"json",
		      success:callback36		      
		});	
	}else{
		document.forms[0].action="yjCome.do?method=plQd1&str="+str;
		document.forms[0].submit();
	}
	
}
function callback36(list1){
	var selectIds=document.getElementsByName("selectId");
	var str='';
	var n=0;
	for(var i=0;i<selectIds.length;i++){
		if(selectIds[i].checked){
			if(n==0){
				str+=selectIds[i].value;
				n++;
			}else{
				str+=";"+selectIds[i].value;
				n++;
			}
		}
	}
	if(n>list1){		
		alert("当前四楼也只有  "+list1+" 个空闲座位，请减少勾选人数后重试！");
		return false;
	}else{
		document.forms[0].action="yjCome.do?method=plQd2&str="+str;
		document.forms[0].submit();
	}
	
}
//普通批量签到
function plQd1(){
	var selectIds=document.getElementsByName("selectId");
	var str='';
	var n=0;
	for(var i=0;i<selectIds.length;i++){
		if(selectIds[i].checked){
			if(n==0){
				str+=selectIds[i].value;
				n++;
			}else{
				str+=";"+selectIds[i].value;
				n++;
			}
		}
	}
	if(n==0){
		alert("请选中你要批量分配座位的记录");
		return false;
	}else{
		document.forms[0].action="yjCome.do?method=plQd&str="+str;
		document.forms[0].submit();
	}
	
}
//普通批量分配
function plfpZw(){
//	alert("测试");
	var hjids=document.getElementsByName("hjid");
	var str='';
	for(var i=0;i<hjids.length;i++){
		if(i==0){
			str+=hjids[i].value;
		
		}else{
			str+=";"+hjids[i].value;
		}
	}
	
	var yjNum=document.getElementById("yjNum").value;
	yjNum=yjNum.replace(/\s+$/g,"");
	if(yjNum==''){
		alert("警察警号不能为空");
		return false;
	}
	if(yjNum.indexOf("'")>-1){
		alert("警察警号输入有误，不能包含特殊符号");
		return false;
	}
	
	yjNum=encodeURI(encodeURI(yjNum));
	//$.ajax({
	//      type:"POST",
	//      url:"yjCome.do",
	//      data:"method=plfpZw&hjid="+str+"&yjNum="+yjNum,
	//      dataType:"json",
	//      success:callback32   
	//});
	$('#info2').window({
		title:"分配座位信息",
		href:"yjCome.do?method=plfpZw&hjid="+str+"&yjNum="+yjNum,
		width:730,
		height:180,
		collapsible:false,
		minimizable:false,
		maximizable:false,
		resizable:false,
		cache:false,
		onClose:function(){
			document.forms[0].action=basePath+"yjCome.do?method=search";
			document.forms[0].submit();
		}
	});
}
//批量分配到三楼（广东揭阳监狱）
function plfpZw1(){
//	alert("测试1");
	var hjids=document.getElementsByName("hjid");
	var str='';
	for(var i=0;i<hjids.length;i++){
		if(i==0){
			str+=hjids[i].value;
		
		}else{
			str+=";"+hjids[i].value;
		}
	}
	
	var yjNum=document.getElementById("yjNum").value;
	yjNum=yjNum.replace(/\s+$/g,"");
	if(yjNum==''){
		alert("警察警号不能为空");
		return false;
	}
	if(yjNum.indexOf("'")>-1){
		alert("警察警号输入有误，不能包含特殊符号");
		return false;
	}
	
	yjNum=encodeURI(encodeURI(yjNum));
	//$.ajax({
	//      type:"POST",
	//      url:"yjCome.do",
	//      data:"method=plfpZw&hjid="+str+"&yjNum="+yjNum,
	//      dataType:"json",
	//      success:callback32   
	//});
	$('#info2').window({
		title:"分配座位信息",
		href:"yjCome.do?method=plfpZw1&hjid="+str+"&yjNum="+yjNum,
		width:730,
		height:180,
		collapsible:false,
		minimizable:false,
		maximizable:false,
		resizable:false,
		cache:false,
		onClose:function(){
			document.forms[0].action=basePath+"yjCome.do?method=search";
			document.forms[0].submit();
		}
	});
}
//批量分配到四楼（广东揭阳监狱）
function plfpZw2(){
//	alert("四楼");
	var hjids=document.getElementsByName("hjid");
	var str='';
	for(var i=0;i<hjids.length;i++){
		if(i==0){
			str+=hjids[i].value;
		
		}else{
			str+=";"+hjids[i].value;
		}
	}
	
	var yjNum=document.getElementById("yjNum").value;
	yjNum=yjNum.replace(/\s+$/g,"");
	if(yjNum==''){
		alert("警察警号不能为空");
		return false;
	}
	if(yjNum.indexOf("'")>-1){
		alert("警察警号输入有误，不能包含特殊符号");
		return false;
	}
	
	yjNum=encodeURI(encodeURI(yjNum));
	//$.ajax({
	//      type:"POST",
	//      url:"yjCome.do",
	//      data:"method=plfpZw&hjid="+str+"&yjNum="+yjNum,
	//      dataType:"json",
	//      success:callback32   
	//});
	$('#info2').window({
		title:"分配座位信息",
		href:"yjCome.do?method=plfpZw2&hjid="+str+"&yjNum="+yjNum,
		width:730,
		height:180,
		collapsible:false,
		minimizable:false,
		maximizable:false,
		resizable:false,
		cache:false,
		onClose:function(){
			document.forms[0].action=basePath+"yjCome.do?method=search";
			document.forms[0].submit();
		}
	});
}
function callback32(data){
	var str="";
	for(var i=0;i<data.length;i++){
		str+=data[i].frName+":"+data[i].xx+"\n";
	}
	alert(str);
	
	
}
function selectServer(){
	var hjType=document.getElementById("hjType").value;
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
		      url:basePath+"yjCome.do",
		      data:"method=checkLine&jy1="+jy1+"&hjType="+hjType,
		      dataType:"json",
		      success:callback8
		});
	}else{
		var zwNo=document.getElementById("zwNo");
		zwNo.options.length=0;
		zwNo.add(new  Option("全部","null"));
	}
}
function selectRecord1(webId){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	document.getElementById("rowIndex").value=tr.rowIndex-3;
	
	$.ajax({
	      type:"POST",
	      url:"cancelDj.do",
	      data:"method=selectRecord&webId="+webId,
	      dataType:"json",
	      success:callback34
	});
}
function callback34(data){
	
	if(data[0]=="0"){
		
	}else{
		alert("该罪犯已签到离开");
		var selectIds=document.getElementsByName("selectId");
		selectIds[document.getElementById("rowIndex").value].checked=false;
		return false;
	}
}
function plqdlk(){
	var selectIds=document.getElementsByName("selectId");
	var str='';
	var n=0;
	for(var i=0;i<selectIds.length;i++){
		if(selectIds[i].checked){
			if(n==0){
				str+=selectIds[i].value;
				n++;
			}else{
				str+=";"+selectIds[i].value;
				n++;
			}
		}
	}
	if(n==0){
		alert("请选中你要批量分配座位的记录");
		return false;
	}else{
		document.forms[0].action="cancelDj.do?method=plqdlk&str="+str;
		document.forms[0].submit();
	}
	
}
function savePlqdlk(){
	var hjids=document.getElementsByName("hjid");
	var str='';
	for(var i=0;i<hjids.length;i++){
		if(i==0){
			str+=hjids[i].value;
		
		}else{
			str+=";"+hjids[i].value;
		}
	}
	
	var yjNum=document.getElementById("yjNum").value;
	yjNum=yjNum.replace(/\s+$/g,"");
	if(yjNum==''){
		alert("警察警号不能为空");
		return false;
	}
	if(yjNum.indexOf("'")>-1){
		alert("警察警号输入有误，不能包含特殊符号");
		return false;
	}
	
	yjNum=encodeURI(encodeURI(yjNum));
	//$.ajax({
	//      type:"POST",
	//      url:"yjCome.do",
	//      data:"method=plfpZw&hjid="+str+"&yjNum="+yjNum,
	//      dataType:"json",
	//      success:callback32   
	//});
	$('#info2').window({
		title:"签到离开信息",
		href:"cancelDj.do?method=plfpZw&hjid="+str+"&yjNum="+yjNum,
		width:730,
		height:180,
		collapsible:false,
		minimizable:false,
		maximizable:false,
		resizable:false,
		cache:false,
		onClose:function(){
			document.forms[0].action=basePath+"cancelDj.do?method=search2";
			document.forms[0].submit();
		}
	});
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
function printZw(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("webId");
	var webId=hjid[tr.rowIndex-1].value

	var url=basePath+"yjCome.do?method=printXp&webId="+webId;
	val=window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
	
}
function updateShState(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("webId");
	var webId=hjid[tr.rowIndex-1].value
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=updateShState&webId="+webId,
	      dataType:"json",
	      success:callback1011   
	});
}
function callback1011(data){
	if(data[0]=='3'){
		alert("电脑IP地址非法");
		return false;
	}
	document.forms[0].action=basePath+"yjCome.do?method=search1";
	document.forms[0].submit();
}
function delShState(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var hjid=document.getElementsByName("webId");
	var webId=hjid[tr.rowIndex-1].value
	$.ajax({
	      type:"POST",
	      url:"yjCome.do",
	      data:"method=delShState&webId="+webId,
	      dataType:"json",
	      success:callback1011   
	});
}
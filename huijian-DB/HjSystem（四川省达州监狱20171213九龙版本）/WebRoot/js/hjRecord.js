function refreshMeetInfo(){
	 $.ajax({
	      type:"POST",
	      url:"hjMonitor.do",
	      data:"method=jquerSearch",
	      dataType:"json",
	      success:callback2   
	});
}
function callback2(data){
	if(data.length>0){
		var table=document.getElementById("info5");
		var uptr=table.getElementsByTagName("tr");
//		var shiyong=0;
//		var weishiyong=0;
		var ThreeShiyong=0;
		var ThreeWeishiyong=0;
		var ThreePingbi=0;
		var FourShiyong=0;
		var FourWeishiyong=0;
		var FourPingbi=0;
		var ThreeKJshiyong=0;
		var ThreeKJweishiyong=0;
		var ThreeKJpingbi=0;
		for(var i=0;i<uptr.length;i++){
			var uptd=uptr[i].getElementsByTagName("td");
			for(var j=0;j<uptd.length;j++){
				if(uptd[3].children[1].value!=data[i].monitorState){
					if(data[i].monitorState=="空闲"){
						uptd[3].innerHTML="<img src=\"images/kongxian.bmp\"><input type=\"hidden\" id=\"state\" name=\"state\" value=\"空闲\">";
					}
					if(data[i].monitorState=="应答"){
						uptd[3].innerHTML="<img src=\"images/huru.bmp\"><input type=\"hidden\" id=\"state\" name=\"state\" value=\"应答\">";
					}
					if(data[i].monitorState=="通话"){
						uptd[3].innerHTML="<img src=\"images/tonghua.bmp\"><input type=\"hidden\" id=\"state\" name=\"state\" value=\"通话\">";
					}
				}
				if(uptd[4].innerText!=data[i].monitorJq){
					uptd[4].innerText=data[i].monitorJq;
				}
				if(uptd[5].innerText!=data[i].monitorFr){
					uptd[5].innerText=data[i].monitorFr;
				}
				if(uptd[6].innerText!=data[i].monitorQs){
					uptd[6].innerText=data[i].monitorQs;
				}
				uptd[7].innerHTML="<font>"+data[i].monitorTime+"</font><input type=\"hidden\" id=\"monitorCallID\" name=\"monitorCallID\" value=\""+data[i].monitorCallID+"\"><input type=\"hidden\"  name=\"userNo\" value=\""+data[i].userNo+"\"><input type=\"hidden\"  name=\"monitorFlagId\" value=\""+data[i].monitorFlagId+"\"><input type=\"hidden\"  name=\"writeTxt\" value=\""+data[i].writeTxt+"\"><input type=\"hidden\"  name=\"hjid\" value=\""+data[i].hjId+"\">";
			}
//			if(data[i].hJState==1){
//				shiyong++;
//			}else if(data[i].state==1){
//				weishiyong++;
//			}
//			alert(data[i].zw.substr(0,1));
			if(data[i].lineType==0 && data[i].state==1 && data[i].hjId!=''){
				ThreeShiyong++;
//				alert(ThreeShiyong);
		    }
			if(data[i].lineType==0 && data[i].state==1 && data[i].hjId=='' && data[i].hJState==0){
				ThreeWeishiyong++;
//				alert(ThreeWeishiyong)
		    }
			if(data[i].lineType==0 && data[i].state==0){
				ThreePingbi++;
		    }
			if(data[i].lineType==0 && data[i].state==1 && data[i].hjId!=''){
				FourShiyong++;
		    }
			if(data[i].lineType==0 && data[i].state==1 && data[i].hjId=='' && data[i].hJState==0){
				FourWeishiyong++;
		    }
			if(data[i].lineType==0 && data[i].state==0){
				FourPingbi++;
		    }
			if(data[i].lineType==1 && data[i].state==1 && data[i].hjId!=''){
				ThreeKJshiyong++;
		    }
			if(data[i].lineType==1 && data[i].state==1 && data[i].hjId=='' && data[i].hJState==0){
				ThreeKJweishiyong++;
		    }
			if(data[i].lineType==1 && data[i].state==0){
				ThreeKJpingbi++;
		    }
		}
//		alert(ThreeShiyong);
//		alert(ThreeWeishiyong);
//		alert(ThreePingbi);
		
//		document.getElementById("info3").innerHTML="当前会见窗口有&nbsp;&nbsp;<font color=\"red\">"+shiyong+"</font>&nbsp;&nbsp;路正在使用, &nbsp;&nbsp;<font color=\"red\">"+weishiyong+"</font>&nbsp;&nbsp;路空闲";
//		document.getElementById("info3").innerHTML="<font color=\"green\"></font>&nbsp;&nbsp;&nbsp;&nbsp;会见窗口使用情况：总数量&nbsp;&nbsp;<font color=\"red\">19</font>&nbsp;&nbsp; 个，正在使用&nbsp;&nbsp;<font color=\"red\">"+ThreeShiyong+"</font>&nbsp;&nbsp;个，未使用&nbsp;&nbsp;<font color=\"red\">"+ThreeWeishiyong+"</font>&nbsp;&nbsp;个，屏蔽&nbsp;&nbsp;<font color=\"red\">"+ThreePingbi+"</font>&nbsp;&nbsp;个；";		
//		document.getElementById("info4").innerHTML="<font color=\"green\"></font>&nbsp;&nbsp;&nbsp;&nbsp;<font color=\"red\">宽见</font>窗口使用情况：总数量&nbsp;&nbsp;<font color=\"red\">8</font>&nbsp;&nbsp; 个，正在使用&nbsp;&nbsp;<font color=\"red\">"+ThreeKJshiyong+"</font>&nbsp;&nbsp;个，未使用&nbsp;&nbsp;<font color=\"red\">"+ThreeKJweishiyong+"</font>&nbsp;&nbsp;个，屏蔽&nbsp;&nbsp;<font color=\"red\">"+ThreeKJpingbi+"</font>&nbsp;&nbsp;个；";
//		document.getElementById("info6").innerHTML="<font color=\"green\">四楼</font>&nbsp;&nbsp;&nbsp;&nbsp;会见窗口使用情况：总数量&nbsp;&nbsp;<font color=\"red\">27</font>&nbsp;&nbsp; 个，正在使用&nbsp;&nbsp;<font color=\"red\">"+FourShiyong+"</font>&nbsp;&nbsp;个，未使用&nbsp;&nbsp;<font color=\"red\">"+FourWeishiyong+"</font>&nbsp;&nbsp;个，屏蔽&nbsp;&nbsp;<font color=\"red\">"+FourPingbi+"</font>&nbsp;&nbsp;个；";
	}
}
function refreshMeetInfo1(){
	 $.ajax({
	      type:"POST",
	      url:"hjMonitor.do",
	      data:"method=jquerSearch1",
	      dataType:"json",
	      success:callback222   
	});
}
function callback222(data){
	if(data.length>0){
		var table=document.getElementById("info5");
		var uptr=table.getElementsByTagName("tr");
//		var shiyong=0;
//		var weishiyong=0;
		var ThreeShiyong=0;
		var ThreeWeishiyong=0;
		var ThreePingbi=0;
		var FourShiyong=0;
		var FourWeishiyong=0;
		var FourPingbi=0;
		var ThreeKJshiyong=0;
		var ThreeKJweishiyong=0;
		var ThreeKJpingbi=0;
		for(var i=0;i<uptr.length;i++){
			var uptd=uptr[i].getElementsByTagName("td");
			for(var j=0;j<uptd.length;j++){
				if(uptd[3].children[1].value!=data[i].monitorState){
					if(data[i].monitorState=="空闲"){
						uptd[3].innerHTML="<img src=\"images/kongxian.bmp\"><input type=\"hidden\" id=\"state\" name=\"state\" value=\"空闲\">";
					}
					if(data[i].monitorState=="应答"){
						uptd[3].innerHTML="<img src=\"images/huru.bmp\"><input type=\"hidden\" id=\"state\" name=\"state\" value=\"应答\">";
					}
					if(data[i].monitorState=="通话"){
						uptd[3].innerHTML="<img src=\"images/tonghua.bmp\"><input type=\"hidden\" id=\"state\" name=\"state\" value=\"通话\">";
					}
				}
				if(uptd[4].innerText!=data[i].monitorJq){
					uptd[4].innerText=data[i].monitorJq;
				}
				if(uptd[5].innerText!=data[i].monitorFr){
					uptd[5].innerText=data[i].monitorFr;
				}
				if(uptd[6].innerText!=data[i].monitorQs){
					uptd[6].innerText=data[i].monitorQs;
				}
				uptd[7].innerHTML="<font>"+data[i].monitorTime+"</font><input type=\"hidden\" id=\"monitorCallID\" name=\"monitorCallID\" value=\""+data[i].monitorCallID+"\"><input type=\"hidden\"  name=\"userNo\" value=\""+data[i].userNo+"\"><input type=\"hidden\"  name=\"monitorFlagId\" value=\""+data[i].monitorFlagId+"\"><input type=\"hidden\"  name=\"writeTxt\" value=\""+data[i].writeTxt+"\"><input type=\"hidden\"  name=\"hjid\" value=\""+data[i].hjId+"\">";
			}
//			if(data[i].hJState==1){
//				shiyong++;
//			}else if(data[i].state==1){
//				weishiyong++;
//			}
//			alert(data[i].zw.substr(0,1));
			if(data[i].lineType==0 && data[i].state==1 && data[i].hjId!=''){
				ThreeShiyong++;
//				alert(ThreeShiyong);
		    }
			if(data[i].lineType==0 && data[i].state==1 && data[i].hjId=='' && data[i].hJState==0){
				ThreeWeishiyong++;
//				alert(ThreeWeishiyong)
		    }
			if(data[i].lineType==0 && data[i].state==0){
				ThreePingbi++;
		    }
			if(data[i].lineType==0 && data[i].state==1 && data[i].hjId!=''){
				FourShiyong++;
		    }
			if(data[i].lineType==0 && data[i].state==1 && data[i].hjId=='' && data[i].hJState==0){
				FourWeishiyong++;
		    }
			if(data[i].lineType==0 && data[i].state==0){
				FourPingbi++;
		    }
			if(data[i].lineType==1 && data[i].state==1 && data[i].hjId!=''){
				ThreeKJshiyong++;
		    }
			if(data[i].lineType==1 && data[i].state==1 && data[i].hjId=='' && data[i].hJState==0){
				ThreeKJweishiyong++;
		    }
			if(data[i].lineType==1 && data[i].state==0){
				ThreeKJpingbi++;
		    }
		}
//		alert(ThreeShiyong);
//		alert(ThreeWeishiyong);
//		alert(ThreePingbi);
		
//		document.getElementById("info3").innerHTML="当前会见窗口有&nbsp;&nbsp;<font color=\"red\">"+shiyong+"</font>&nbsp;&nbsp;路正在使用, &nbsp;&nbsp;<font color=\"red\">"+weishiyong+"</font>&nbsp;&nbsp;路空闲";
//		document.getElementById("info3").innerHTML="<font color=\"green\"></font>&nbsp;&nbsp;&nbsp;&nbsp;会见窗口使用情况：总数量&nbsp;&nbsp;<font color=\"red\">19</font>&nbsp;&nbsp; 个，正在使用&nbsp;&nbsp;<font color=\"red\">"+ThreeShiyong+"</font>&nbsp;&nbsp;个，未使用&nbsp;&nbsp;<font color=\"red\">"+ThreeWeishiyong+"</font>&nbsp;&nbsp;个，屏蔽&nbsp;&nbsp;<font color=\"red\">"+ThreePingbi+"</font>&nbsp;&nbsp;个；";		
//		document.getElementById("info4").innerHTML="<font color=\"green\"></font>&nbsp;&nbsp;&nbsp;&nbsp;<font color=\"red\">宽见</font>窗口使用情况：总数量&nbsp;&nbsp;<font color=\"red\">8</font>&nbsp;&nbsp; 个，正在使用&nbsp;&nbsp;<font color=\"red\">"+ThreeKJshiyong+"</font>&nbsp;&nbsp;个，未使用&nbsp;&nbsp;<font color=\"red\">"+ThreeKJweishiyong+"</font>&nbsp;&nbsp;个，屏蔽&nbsp;&nbsp;<font color=\"red\">"+ThreeKJpingbi+"</font>&nbsp;&nbsp;个；";
//		document.getElementById("info6").innerHTML="<font color=\"green\">四楼</font>&nbsp;&nbsp;&nbsp;&nbsp;会见窗口使用情况：总数量&nbsp;&nbsp;<font color=\"red\">27</font>&nbsp;&nbsp; 个，正在使用&nbsp;&nbsp;<font color=\"red\">"+FourShiyong+"</font>&nbsp;&nbsp;个，未使用&nbsp;&nbsp;<font color=\"red\">"+FourWeishiyong+"</font>&nbsp;&nbsp;个，屏蔽&nbsp;&nbsp;<font color=\"red\">"+FourPingbi+"</font>&nbsp;&nbsp;个；";
	}
}
function enterSubmit(src,e){
    var keyPressed;
    if(window.event){
   	 keyPressed = window.event.keyCode; // IE
    }else{
       keyPressed = e.which; // Firefox
    }
    if(keyPressed==13){ 
   	    chaxun();                     
        return false;
    }
}
function chaxun(){
	var frNo=document.getElementById("frNo").value;
	var frName=document.getElementById("frName").value;
	var zw=document.getElementById("zw").value;
	var qsName=document.getElementById("qsName").value;
	if(frNo.indexOf("'")>-1){
		alert("罪犯编号输入有误，不能包含特殊符号");
		return false;
	}
	if(frName.indexOf("'")>-1){
		alert("罪犯姓名输入有误，不能包含特殊符号");
		return false;
	}
	if(zw.indexOf("'")>-1){
		alert("座位输入有误，不能包含特殊符号");
		return false;
	}
	if(qsName.indexOf("'")>-1){
		alert("亲属姓名输入有误，不能包含特殊符号");
		return false;
	}
	document.forms[0].action="hjRecord.do?method=search";
	document.forms[0].submit();
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
		      url:"hjRecord.do",
		      data:"method=checkJq&jy1="+jy1,
		      dataType:"json",
		      success:callback7,
		      error:callback1
		});
	}else{
		document.getElementById("jqNo").disabled=true;
		var jq=document.getElementById("jqNo");
		jq.options.length=0;
		jq.add(new  Option("全部","null"));
	}
}
function callback7(data){
	document.getElementById("jqNo").disabled=false;
	var jq=document.getElementById("jqNo");
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
function callback1(){
	alert(a);
 	alert(b);
 	alert(c);
}
function playRecor(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var wjljs = document.getElementsByName("wjdz");
	var wjlj=wjljs[tr.rowIndex-4].value;
	var wjlj1s = document.getElementsByName("callVideoFile1");
	var wjlj1=wjlj1s[tr.rowIndex-4].value;
	if(wjlj1.length<30){
		wjlj1='';
	}
	var wjlj2s = document.getElementsByName("callVideoFile2");
	var wjlj2=wjlj2s[tr.rowIndex-4].value;
	if(wjlj2.length<30){
		wjlj2='';
	}
	var webIds = document.getElementsByName("webId");
	var webId=webIds[tr.rowIndex-4].value;
	var flag=document.getElementById("boFangFlag").value;
	if(flag!=0){
		document.getElementById("boFangFlag").value=0;
		window.close();
		if(wjlj2!="" && wjlj2!="/"  && wjlj1!="" && wjlj!=""){
			document.getElementById("downLoad").DownByUrl(wjlj,wjlj1,wjlj2);
			var url='hjRecord.do?method=bofangId&id='+wjlj+"&id1="+wjlj1+"&id2="+wjlj2+"&webId="+webId;
			wjlj=encodeURI(encodeURI(wjlj));
			wjlj1=encodeURI(encodeURI(wjlj));
			wjlj2=encodeURI(encodeURI(wjlj));
			window.open(url,"","width=650,height=400,left=900,top=620,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		}else if(wjlj1!="" && wjlj!=""){
			document.getElementById("downLoad").DownByUrl(wjlj,wjlj1,"");
			var url='hjRecord.do?method=bofangId&id='+wjlj+"&id1="+wjlj1+"&id2="+wjlj2+"&webId="+webId;
			wjlj=encodeURI(encodeURI(wjlj));
			wjlj1=encodeURI(encodeURI(wjlj));
			wjlj2=encodeURI(encodeURI(wjlj));
			window.open(url,"","width=400,height=370,left=1000,top=620,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		}else{
			var url='hjRecord.do?method=bofangId&id='+wjlj+"&id1="+wjlj1+"&id2="+wjlj2+"&webId="+webId;
			wjlj=encodeURI(encodeURI(wjlj));
			wjlj1=encodeURI(encodeURI(wjlj));
			wjlj2=encodeURI(encodeURI(wjlj));
			window.open(url,"","width=360,height=65,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		}
		document.getElementById("boFangFlag").value=1;
	}else{
		if(wjlj2!="" && wjlj2!="/"  && wjlj1!="" && wjlj!=""){

			document.getElementById("downLoad").DownByUrl(wjlj,wjlj1,wjlj2);
			var url='hjRecord.do?method=bofangId&id='+wjlj+"&id1="+wjlj1+"&id2="+wjlj2+"&webId="+webId;
			wjlj=encodeURI(encodeURI(wjlj));
			wjlj1=encodeURI(encodeURI(wjlj));
			wjlj2=encodeURI(encodeURI(wjlj));
			window.open(url,"","width=650,height=400,left=900,top=620,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		}else if(wjlj1!="" && wjlj!=""){

			document.getElementById("downLoad").DownByUrl(wjlj,wjlj1,"");
			var url='hjRecord.do?method=bofangId&id='+wjlj+"&id1="+wjlj1+"&id2="+wjlj2+"&webId="+webId;
			wjlj=encodeURI(encodeURI(wjlj));
			wjlj1=encodeURI(encodeURI(wjlj));
			wjlj2=encodeURI(encodeURI(wjlj));
			window.open(url,"","width=400,height=370,left=1000,top=620,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		}else{

			var url='hjRecord.do?method=bofangId&id='+wjlj+"&id1="+wjlj1+"&id2="+wjlj2+"&webId="+webId;
			wjlj=encodeURI(encodeURI(wjlj));
			wjlj1=encodeURI(encodeURI(wjlj));
			wjlj2=encodeURI(encodeURI(wjlj));
			window.open(url,"","width=360,height=65,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 
		}
		document.getElementById("boFangFlag").value=1;
	}
}
function playRecor1(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var wjljs = document.getElementsByName("wjdz");
	var wjlj=wjljs[tr.rowIndex-4].value;
	var wjlj1='';
	var wjlj2='';
	var webIds = document.getElementsByName("webId");
	var webId=webIds[tr.rowIndex-4].value;


	var flag=document.getElementById("boFangFlag").value;
	if(flag!=0){
		document.getElementById("boFangFlag").value=0;
		window.close();
		
		var url='hjRecord.do?method=bofangId&id='+wjlj+"&id1="+wjlj1+"&id2="+wjlj2+"&webId="+webId;
		wjlj=encodeURI(encodeURI(wjlj));
		wjlj1=encodeURI(encodeURI(wjlj));
		wjlj2=encodeURI(encodeURI(wjlj));
		window.open(url,"","width=360,height=65,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 

		document.getElementById("boFangFlag").value=1;
	}else{
		var url='hjRecord.do?method=bofangId&id='+wjlj+"&id1="+wjlj1+"&id2="+wjlj2+"&webId="+webId;
		wjlj=encodeURI(encodeURI(wjlj));
		wjlj1=encodeURI(encodeURI(wjlj));
		wjlj2=encodeURI(encodeURI(wjlj));
		window.open(url,"","width=360,height=65,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no"); 

		document.getElementById("boFangFlag").value=1;
	}
}
function downRecord(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var wjljs = document.getElementsByName("wjdz1");
	var wjlj=wjljs[tr.rowIndex-4].value;
	//var wjlj1s = document.getElementsByName("callVideoFile1");
	//var wjlj1=wjlj1s[tr.rowIndex-4].value;
	//var wjlj2s = document.getElementsByName("callVideoFile2");
	//var wjlj2=wjlj2s[tr.rowIndex-4].value;
	var wjlj1s = document.getElementsByName("downCallVideoFile1");
	var wjlj1=wjlj1s[tr.rowIndex-4].value;
	var wjlj2s = document.getElementsByName("downCallVideoFile2");
	var wjlj2=wjlj2s[tr.rowIndex-4].value;
	if(wjlj!='' && wjlj1!='' && wjlj2!=''){
		document.getElementById("downLoad").DownByUrl(wjlj,wjlj1,wjlj2);
		alert("已将文件下载至C:＼tempdown");
	}else{
		wjlj=encodeURI(encodeURI(wjlj));
		document.forms[0].action="hjRecord.do?method=downFile&path="+wjlj;
		document.forms[0].submit();
	}

}
function downRecord1(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var wjljs = document.getElementsByName("wjdz1");
	var wjlj=wjljs[tr.rowIndex-4].value;
	//var wjlj1s = document.getElementsByName("callVideoFile1");
	//var wjlj1=wjlj1s[tr.rowIndex-4].value;
	//var wjlj2s = document.getElementsByName("callVideoFile2");
	//var wjlj2=wjlj2s[tr.rowIndex-4].value;
	var wjlj1s = document.getElementsByName("downCallVideoFile1");
	var wjlj1=wjlj1s[tr.rowIndex-4].value;
	var wjlj1='';
	
	var wjlj2s = document.getElementsByName("downCallVideoFile2");
	var wjlj2=wjlj2s[tr.rowIndex-4].value;
	var wjlj2='';
	if(wjlj!='' && wjlj1!='' && wjlj2!=''){
		document.getElementById("downLoad").DownByUrl(wjlj,wjlj1,wjlj2);
		alert("已将文件下载至C:＼tempdown");
	}else{
		wjlj=encodeURI(encodeURI(wjlj));
		document.forms[0].action="hjRecord.do?method=downFile&path="+wjlj;
		document.forms[0].submit();
	}

}
function zhushi(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var userNos = document.getElementsByName("userNo");
	var userNo=userNos[tr.rowIndex-4].value;
	var zhuShiWebIDs = document.getElementsByName("zhuShiWebID");
	var zhuShiWebID=zhuShiWebIDs[tr.rowIndex-4].value;
	var callIds = document.getElementsByName("callId");
	var callId=callIds[tr.rowIndex-4].value;
	
	
	var jy=document.getElementById("jy");
	var jqNo=document.getElementById("jqNo");
	var type=document.getElementById("type");
	var recRatingState=document.getElementById("recRatingState");
	var recAssessmentState=document.getElementById("recAssessmentState");
	var recordOverTime=document.getElementById("recordOverTime");
	
	var callTimeStart2=document.getElementById("callTimeStart").value;
	var callTimeEnd2=document.getElementById("callTimeEnd").value;
	var frName2=document.getElementById("frName").value;
	var frNo3=document.getElementById("frNo").value;
	var zw2=document.getElementById("zw").value;
	var qsName2=document.getElementById("qsName").value;
	
	var jy2='';
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy2=jy.options[i].value;
			break;
		}
	}
	var jqNo2='';
	for(var i=0;i<jqNo.length;i++){
		if(jqNo.options[i].selected){
			jqNo2=jqNo.options[i].value;
			break;
		}
	}
	var type2='';
	for(var i=0;i<type.length;i++){
		if(type.options[i].selected){
			type2=type.options[i].value;
			break;
		}
	}
	var recRatingState2='';
	for(var i=0;i<recRatingState.length;i++){
		if(recRatingState.options[i].selected){
			recRatingState2=recRatingState.options[i].value;
			break;
		}
	}
	var recAssessmentState2='';
	for(var i=0;i<recAssessmentState.length;i++){
		if(recAssessmentState.options[i].selected){
			recAssessmentState2=recAssessmentState.options[i].value;
			break;
		}
	}
	var recordOverTime2='';
	for(var i=0;i<recordOverTime.length;i++){
		if(recordOverTime.options[i].selected){
			recordOverTime2=recordOverTime.options[i].value;
			break;
		}
	}
	var pageNo2=pageNo2=document.getElementById("pageNo").value;
	frName2=encodeURI(encodeURI(frName2));
	frNo3=encodeURI(encodeURI(frNo3));
	zw2=encodeURI(encodeURI(zw2));
	qsName2=encodeURI(encodeURI(qsName2));
	jq2=encodeURI(encodeURI(jqNo2));
	jy2=encodeURI(encodeURI(jy2));
	type2=encodeURI(encodeURI(type2));
	recRatingState2=encodeURI(encodeURI(recRatingState2));
	recAssessmentState2=encodeURI(encodeURI(recAssessmentState2));
	recordOverTime2=encodeURI(encodeURI(recordOverTime2));
	
//	if(userNo==""){
		window.location.href("hjRecord.do?method=addRecordFlag&callId="+callId+"&callTimeStart2="+callTimeStart2+"&callTimeEnd2="+callTimeEnd2+"&frName2="+frName2+"&frNo3="+frNo3+"&zw2="+zw2+"&type2="+type2+"&jy2="+jy2+"&jq2="+jq2+"&qsName2="+qsName2+"&pageNo2="+pageNo2+"&recRatingState2="+recRatingState2+"&recAssessmentState2="+recAssessmentState2+"&recordOverTime2="+recordOverTime2);
//	}else{
//		window.location.href("hjRecord.do?method=updateRecordFlag&zhuShiWebID="+zhuShiWebID+"&callTimeStart2="+callTimeStart2+"&callTimeEnd2="+callTimeEnd2+"&frName2="+frName2+"&frNo3="+frNo3+"&zw2="+zw2+"&type2="+type2+"&jy2="+jy2+"&jq2="+jq2+"&qsName2="+qsName2+"&pageNo2="+pageNo2+"&recRatingState2="+recRatingState2+"&recAssessmentState2="+recAssessmentState2);
//	}
}
function zhushiMessage(){
	var userGroup=document.getElementById("userGroup").value;
	var isSuper=document.getElementById("isSuper").value;
//	if(userGroup=="Admin" || isSuper==1){
		var jy=document.getElementById("jy");
		var jqNo=document.getElementById("jqNo");
		var type=document.getElementById("type");
		var recRatingState=document.getElementById("recRatingState");
		var recAssessmentState=document.getElementById("recAssessmentState");
		var recordOverTime=document.getElementById("recordOverTime");
		
		var callTimeStart2=document.getElementById("callTimeStart").value;
		var callTimeEnd2=document.getElementById("callTimeEnd").value;
		var frName2=document.getElementById("frName").value;
		var frNo3=document.getElementById("frNo").value;
		var zw2=document.getElementById("zw").value;
		var qsName2=document.getElementById("qsName").value;
		
		var jy2='';
		for(var i=0;i<jy.length;i++){
			if(jy.options[i].selected){
				jy2=jy.options[i].value;
				break;
			}
		}
		var jqNo2='';
		for(var i=0;i<jqNo.length;i++){
			if(jqNo.options[i].selected){
				jqNo2=jqNo.options[i].value;
				break;
			}
		}
		var type2='';
		for(var i=0;i<type.length;i++){
			if(type.options[i].selected){
				type2=type.options[i].value;
				break;
			}
		}
		var recRatingState2='';
		for(var i=0;i<recRatingState.length;i++){
			if(recRatingState.options[i].selected){
				recRatingState2=recRatingState.options[i].value;
				break;
			}
		}
		var recAssessmentState2='';
		for(var i=0;i<recAssessmentState.length;i++){
			if(recAssessmentState.options[i].selected){
				recAssessmentState2=recAssessmentState.options[i].value;
				break;
			}
		}
		var recordOverTime2='';
		for(var i=0;i<recordOverTime.length;i++){
			if(recordOverTime.options[i].selected){
				recordOverTime2=recordOverTime.options[i].value;
				break;
			}
		}
		var pageNo2=pageNo2=document.getElementById("pageNo").value;
		frName2=encodeURI(encodeURI(frName2));
		frNo3=encodeURI(encodeURI(frNo3));
		zw2=encodeURI(encodeURI(zw2));
		qsName2=encodeURI(encodeURI(qsName2));
		jq2=encodeURI(encodeURI(jqNo2));
		jy2=encodeURI(encodeURI(jy2));
		recRatingState2=encodeURI(encodeURI(recRatingState2));
		recAssessmentState2=encodeURI(encodeURI(recAssessmentState2));
		recordOverTime2=encodeURI(encodeURI(recordOverTime2));
		
		var tr = event.srcElement;
		while (tr && tr.tagName != "TR") {
			tr = tr.parentElement;
		}
		var callIds = document.getElementsByName("callId");
		var callId=callIds[tr.rowIndex-4].value;
		window.location.href("hjRecord.do?method=checkAllZhuShi&callId="+callId+"&callTimeStart2="+callTimeStart2+"&callTimeEnd2="+callTimeEnd2+"&frName2="+frName2+"&frNo3="+frNo3+"&zw2="+zw2+"&type2="+type2+"&jy2="+jy2+"&jq2="+jq2+"&qsName2="+qsName2+"&pageNo2="+pageNo2+"&recRatingState2="+recRatingState2+"&recAssessmentState2="+recAssessmentState2+"&recordOverTime2="+recordOverTime2);
//	}else{
//		alert("你没有这个权限");
//	}
}
function addsaveRecordFlag(){
	var callId=document.getElementById("callId").value;
	var userNo=document.getElementById("userNo").value;
	var writeTxt=document.getElementById("writeTxt").value;
	if(writeTxt==''){
		alert("摘要内容不能为空");
		return fasle;
	}
	if(writeTxt.indexOf("'")>-1){
		alert("摘要内容输入有误，不能包含特殊符号");
		return fasle;
	}
	userNo=encodeURI(encodeURI(userNo));
	writeTxt=encodeURI(encodeURI(writeTxt));
	$.ajax({
		type:"POST",
		url:"hjRecord.do",
		data:"method=addSaveRecordFlag&callId="+callId+"&userNo="+userNo+"&writeTxt="+writeTxt,
		dataType:"json",
		success:callback3,
		error:callback1
	});
}
function callback3(data){
	if(data[0]==null){
		//document.forms[0].action="hjRecord.do?method=search";
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}
}
function updatesaveRecordFlag(){
	var webId=document.getElementById("webId").value;
	var writeTxt=document.getElementById("writeTxt").value;
	if(writeTxt.indexOf("'")>-1){
		alert("注释内容输入有误，不能包含特殊符号");
		return fasle;
	}
	writeTxt=encodeURI(encodeURI(writeTxt));
	$.ajax({
		type:"POST",
		url:"hjRecord.do",
		data:"method=updateSaveRecordFlag&webId="+webId+"&writeTxt="+writeTxt,
		dataType:"json",
		success:callback4,
		error:callback1
	});
}
function callback4(data){
	if(data[0]==null){
		//document.forms[0].action="hjRecord.do?method=search";
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}
}
function addSaveMonitorFlag(){
	var ingmonitorCallID=document.getElementById("ingmonitorCallID").value;
	var writeTxt=document.getElementById("ingwriteTxt").value;
	if(writeTxt.indexOf("'")>-1){
		alert("注释内容输入有误，不能包含特殊符号");
		return fasle;
	}
	writeTxt=encodeURI(encodeURI(writeTxt));
	 $.ajax({
	      type:"POST",
	      url:"hjMonitor.do",
	      data:"method=addSaveMonitorFlag&ingmonitorCallID="+ingmonitorCallID+"&writeTxt="+writeTxt,
	      dataType:"json",
	      success:callback5,
	      error:callback1
	});
}
function updateSaveMonitorFlag(){
	var ingmonitorFlagId=document.getElementById("ingmonitorFlagId").value;
	var ingwriteTxt=document.getElementById("ingwriteTxt").value;
	if(ingwriteTxt.indexOf("'")>-1){
		alert("注释内容输入有误，不能包含特殊符号");
		return fasle;
	}
	writeTxt=encodeURI(encodeURI(ingwriteTxt));
	 $.ajax({
	      type:"POST",
	      url:"hjMonitor.do",
	      data:"method=updateSaveMonitorFlag&ingmonitorFlagId="+ingmonitorFlagId+"&writeTxt="+ingwriteTxt,
	      dataType:"json",
	      success:callback6,
	      error:callback1
	});
}
function callback5(data){
	if(data[0]==-1){
		 var ingwriteTxt=document.getElementById("ingwriteTxt").value;
		 var ingLineNo=document.getElementById("ingLineNo").value;
         var table=document.getElementById("info5");
		 var uptr=table.getElementsByTagName("tr");
		 for(var i=0;i<uptr.length;i++){
	        var uptd=uptr[i].getElementsByTagName("td");
            if(uptd[0].innerText==ingLineNo){
                 uptd[6].children[4].innerText="";
                 uptd[6].children[4].value=data[0].writeTxt;
                 uptd[6].children[3].innerText="";
                 uptd[6].children[3].value=data[0].webId;
                 break;
              }
		 }
	}
//	document.getElementById("info1").innerText="";
	alert("保存成功，10秒后再次监听该线路，在监听摘要中可查看已录入内容");
}
function callback6(data){
	if(data[0]==null){
		   var ingwriteTxt=document.getElementById("ingwriteTxt").value;
		   var ingLineNo=document.getElementById("ingLineNo").value;
           var table=document.getElementById("info5");
			  var uptr=table.getElementsByTagName("tr");
			  for(var i=0;i<uptr.length;i++){
			        var uptd=uptr[i].getElementsByTagName("td");
		            if(uptd[0].innerText==ingLineNo){
		                 uptd[6].children[4].innerText="";
		                 uptd[6].children[4].value=ingwriteTxt;
		                 break;
		              }
		         }
//		document.getElementById("info1").innerText="";
	}
	alert("保存成功，10秒后再次监听该线路，在监听摘要中可查看已录入内容");
}
function dcHjRecord(){
	var isDc=document.getElementById("isDc").value;
	if(isDc==0){
		alert("请查询后再点击导出按钮");
		return false;
	}else{
		document.forms[0].action=basePath+"hjRecord.do?method=dcHjRecord";
		document.forms[0].submit();
	}
}
function callback20(data){
	document.getElementById("ingHjid").value="";
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
function updateRatingState(){
	var tr = event.srcElement;
	while (tr && tr.tagName != "TR") {
		tr = tr.parentElement;
	}
	var userNos = document.getElementsByName("userNo");
	var userNo=userNos[tr.rowIndex-4].value;
	var zhuShiWebIDs = document.getElementsByName("zhuShiWebID");
	var zhuShiWebID=zhuShiWebIDs[tr.rowIndex-4].value;
	var webIds = document.getElementsByName("webId");
	var webId=webIds[tr.rowIndex-4].value;
	var callIds = document.getElementsByName("callId");
	var callId=callIds[tr.rowIndex-4].value;
	
	var jy=document.getElementById("jy");
	var jqNo=document.getElementById("jqNo");
	var type=document.getElementById("type");
	var recRatingState=document.getElementById("recRatingState");
	var recAssessmentState=document.getElementById("recAssessmentState");
	var recordOverTime=document.getElementById("recordOverTime");
	
	var callTimeStart2=document.getElementById("callTimeStart").value;
	var callTimeEnd2=document.getElementById("callTimeEnd").value;
	var frName2=document.getElementById("frName").value;
	var frNo3=document.getElementById("frNo").value;
	var zw2=document.getElementById("zw").value;
	var qsName2=document.getElementById("qsName").value;
	
	var jy2='';
	for(var i=0;i<jy.length;i++){
		if(jy.options[i].selected){
			jy2=jy.options[i].value;
			break;
		}
	}
	var jqNo2='';
	for(var i=0;i<jqNo.length;i++){
		if(jqNo.options[i].selected){
			jqNo2=jqNo.options[i].value;
			break;
		}
	}
	var type2='';
	for(var i=0;i<type.length;i++){
		if(type.options[i].selected){
			type2=type.options[i].value;
			break;
		}
	}
	var recRatingState2='';
	for(var i=0;i<recRatingState.length;i++){
		if(recRatingState.options[i].selected){
			recRatingState2=recRatingState.options[i].value;
			break;
		}
	}
	var recAssessmentState2='';
	for(var i=0;i<recAssessmentState.length;i++){
		if(recAssessmentState.options[i].selected){
			recAssessmentState2=recAssessmentState.options[i].value;
			break;
		}
	}
	var recordOverTime2='';
	for(var i=0;i<recordOverTime.length;i++){
		if(recordOverTime.options[i].selected){
			recordOverTime2=recordOverTime.options[i].value;
			break;
		}
	}
	var pageNo2=pageNo2=document.getElementById("pageNo").value;
	frName2=encodeURI(encodeURI(frName2));
	frNo3=encodeURI(encodeURI(frNo3));
	zw2=encodeURI(encodeURI(zw2));
	qsName2=encodeURI(encodeURI(qsName2));
	jq2=encodeURI(encodeURI(jqNo2));
	jy2=encodeURI(encodeURI(jy2));
	type2=encodeURI(encodeURI(type2));
	recRatingState2=encodeURI(encodeURI(recRatingState2));
	recAssessmentState2=encodeURI(encodeURI(recAssessmentState2));
	recordOverTime2=encodeURI(encodeURI(recordOverTime2));
	

	window.location.href("hjRecord.do?method=updateRatingState&callId="+callId+"&callTimeStart2="+callTimeStart2+"&callTimeEnd2="+callTimeEnd2+"&frName2="+frName2+"&frNo3="+frNo3+"&zw2="+zw2+"&type2="+type2+"&jy2="+jy2+"&jq2="+jq2+"&qsName2="+qsName2+"&pageNo2="+pageNo2+"&webId="+webId+"&recRatingState2="+recRatingState2+"&recAssessmentState2="+recAssessmentState2+"&recordOverTime2="+recordOverTime2);

}

function updateSaveRatingState(){
	var callId=document.getElementById("callId").value;
	var userNo=document.getElementById("userNo").value;
	var userName=document.getElementById("userName").value;
	var writeTxt=document.getElementById("writeTxt").value;
	var webId=document.getElementById("webId").value;
	var state = document.getElementById("state");
	if(writeTxt.indexOf("'")>-1){
		alert("注释内容输入有误，不能包含特殊符号");
		return fasle;
	}
	var state1;
	for(var i=0;i<state.length;i++){
		if(state.options[i].selected){
			state1=state.options[i].value;
			break;
		}
	}
	userNo=encodeURI(encodeURI(userNo));
	userName=encodeURI(encodeURI(userName));
	writeTxt=encodeURI(encodeURI(writeTxt));
	$.ajax({
		type:"POST",
		url:"hjRecord.do",
		data:"method=updateSaveRatingState&callId="+callId+"&userNo="+userNo+"&writeTxt="+writeTxt+"&userName="+userName+"&state="+state1+"&webId="+webId,
		dataType:"json",
		success:callback333,
		error:callback1
	});
}
function callback333(data){
	if(data[0]==null){
		//document.forms[0].action="hjRecord.do?method=search";
		document.forms[0].action=document.getElementById("returnBack").href;
		document.forms[0].submit();
	}
}
function checkAllRatingInfo(){
	var userGroup=document.getElementById("userGroup").value;
	var isSuper=document.getElementById("isSuper").value;
//	if(userGroup=="Admin" || isSuper==1){
		var jy=document.getElementById("jy");
		var jqNo=document.getElementById("jqNo");
		var type=document.getElementById("type");
		var recRatingState=document.getElementById("recRatingState");
		var recAssessmentState=document.getElementById("recAssessmentState");
		var recordOverTime=document.getElementById("recordOverTime");
		
		var callTimeStart2=document.getElementById("callTimeStart").value;
		var callTimeEnd2=document.getElementById("callTimeEnd").value;
		var frName2=document.getElementById("frName").value;
		var frNo3=document.getElementById("frNo").value;
		var zw2=document.getElementById("zw").value;
		var qsName2=document.getElementById("qsName").value;
		
		var jy2='';
		for(var i=0;i<jy.length;i++){
			if(jy.options[i].selected){
				jy2=jy.options[i].value;
				break;
			}
		}
		var jqNo2='';
		for(var i=0;i<jqNo.length;i++){
			if(jqNo.options[i].selected){
				jqNo2=jqNo.options[i].value;
				break;
			}
		}
		var type2='';
		for(var i=0;i<type.length;i++){
			if(type.options[i].selected){
				type2=type.options[i].value;
				break;
			}
		}
		var recRatingState2='';
		for(var i=0;i<recRatingState.length;i++){
			if(recRatingState.options[i].selected){
				recRatingState2=recRatingState.options[i].value;
				break;
			}
		}
		var recAssessmentState2='';
		for(var i=0;i<recAssessmentState.length;i++){
			if(recAssessmentState.options[i].selected){
				recAssessmentState2=recAssessmentState.options[i].value;
				break;
			}
		}
		var recordOverTime2='';
		for(var i=0;i<recordOverTime.length;i++){
			if(recordOverTime.options[i].selected){
				recordOverTime2=recordOverTime.options[i].value;
				break;
			}
		}
		var pageNo2=pageNo2=document.getElementById("pageNo").value;
		frName2=encodeURI(encodeURI(frName2));
		frNo3=encodeURI(encodeURI(frNo3));
		zw2=encodeURI(encodeURI(zw2));
		qsName2=encodeURI(encodeURI(qsName2));
		jq2=encodeURI(encodeURI(jqNo2));
		jy2=encodeURI(encodeURI(jy2));
		type2=encodeURI(encodeURI(type2));
		recRatingState2=encodeURI(encodeURI(recRatingState2));
		recAssessmentState2=encodeURI(encodeURI(recAssessmentState2));
		recordOverTime2=encodeURI(encodeURI(recordOverTime2));
		
		var tr = event.srcElement;
		while (tr && tr.tagName != "TR") {
			tr = tr.parentElement;
		}
		var callIds = document.getElementsByName("callId");
		var callId=callIds[tr.rowIndex-4].value;
		window.location.href("hjRecord.do?method=checkAllRatingInfo&callId="+callId+"&callTimeStart2="+callTimeStart2+"&callTimeEnd2="+callTimeEnd2+"&frName2="+frName2+"&frNo3="+frNo3+"&zw2="+zw2+"&type2="+type2+"&jy2="+jy2+"&jq2="+jq2+"&qsName2="+qsName2+"&pageNo2="+pageNo2+"&recRatingState2="+recRatingState2+"&recAssessmentState2="+recAssessmentState2+"&recordOverTime2="+recordOverTime2);
//	}else{
//		alert("你没有这个权限");
//	}
}
function checkAllAssessmentInfo(){
	var userGroup=document.getElementById("userGroup").value;
	var isSuper=document.getElementById("isSuper").value;
//	if(userGroup=="Admin" || isSuper==1){
		var jy=document.getElementById("jy");
		var jqNo=document.getElementById("jqNo");
		var type=document.getElementById("type");
		var recRatingState=document.getElementById("recRatingState");
		var recAssessmentState=document.getElementById("recAssessmentState");
		var recordOverTime=document.getElementById("recordOverTime");
		
		var callTimeStart2=document.getElementById("callTimeStart").value;
		var callTimeEnd2=document.getElementById("callTimeEnd").value;
		var frName2=document.getElementById("frName").value;
		var frNo3=document.getElementById("frNo").value;
		var zw2=document.getElementById("zw").value;
		var qsName2=document.getElementById("qsName").value;
		
		var jy2='';
		for(var i=0;i<jy.length;i++){
			if(jy.options[i].selected){
				jy2=jy.options[i].value;
				break;
			}
		}
		var jqNo2='';
		for(var i=0;i<jqNo.length;i++){
			if(jqNo.options[i].selected){
				jqNo2=jqNo.options[i].value;
				break;
			}
		}
		var type2='';
		for(var i=0;i<type.length;i++){
			if(type.options[i].selected){
				type2=type.options[i].value;
				break;
			}
		}
		var recRatingState2='';
		for(var i=0;i<recRatingState.length;i++){
			if(recRatingState.options[i].selected){
				recRatingState2=recRatingState.options[i].value;
				break;
			}
		}
		var recAssessmentState2='';
		for(var i=0;i<recAssessmentState.length;i++){
			if(recAssessmentState.options[i].selected){
				recAssessmentState2=recAssessmentState.options[i].value;
				break;
			}
		}
		var recordOverTime2='';
		for(var i=0;i<recordOverTime.length;i++){
			if(recordOverTime.options[i].selected){
				recordOverTime2=recordOverTime.options[i].value;
				break;
			}
		}
		var pageNo2=pageNo2=document.getElementById("pageNo").value;
		frName2=encodeURI(encodeURI(frName2));
		frNo3=encodeURI(encodeURI(frNo3));
		zw2=encodeURI(encodeURI(zw2));
		qsName2=encodeURI(encodeURI(qsName2));
		jq2=encodeURI(encodeURI(jqNo2));
		jy2=encodeURI(encodeURI(jy2));
		type2=encodeURI(encodeURI(type2));
		recRatingState2=encodeURI(encodeURI(recRatingState2));
		recAssessmentState2=encodeURI(encodeURI(recAssessmentState2));
		recordOverTime2=encodeURI(encodeURI(recordOverTime2));
		
		var tr = event.srcElement;
		while (tr && tr.tagName != "TR") {
			tr = tr.parentElement;
		}
		var callIds = document.getElementsByName("callId");
		var callId=callIds[tr.rowIndex-4].value;
		window.location.href("hjRecord.do?method=checkAllAssessmentInfo&callId="+callId+"&callTimeStart2="+callTimeStart2+"&callTimeEnd2="+callTimeEnd2+"&frName2="+frName2+"&frNo3="+frNo3+"&zw2="+zw2+"&type2="+type2+"&jy2="+jy2+"&jq2="+jq2+"&qsName2="+qsName2+"&pageNo2="+pageNo2+"&recRatingState2="+recRatingState2+"&recAssessmentState2="+recAssessmentState2+"&recordOverTime2="+recordOverTime2);
//	}else{
//		alert("你没有这个权限");
//	}
}
function checkRecordInfo(){
	var userGroup=document.getElementById("userGroup").value;
	var isSuper=document.getElementById("isSuper").value;
//	if(userGroup=="Admin" || isSuper==1){
		var jy=document.getElementById("jy");
		var jqNo=document.getElementById("jqNo");
		var type=document.getElementById("type");
		var recRatingState=document.getElementById("recRatingState");
		var recAssessmentState=document.getElementById("recAssessmentState");
		var recordOverTime=document.getElementById("recordOverTime");
		
		var callTimeStart2=document.getElementById("callTimeStart").value;
		var callTimeEnd2=document.getElementById("callTimeEnd").value;
		var frName2=document.getElementById("frName").value;
		var frNo3=document.getElementById("frNo").value;
		var zw2=document.getElementById("zw").value;
		var qsName2=document.getElementById("qsName").value;
		
		var jy2='';
		for(var i=0;i<jy.length;i++){
			if(jy.options[i].selected){
				jy2=jy.options[i].value;
				break;
			}
		}
		var jqNo2='';
		for(var i=0;i<jqNo.length;i++){
			if(jqNo.options[i].selected){
				jqNo2=jqNo.options[i].value;
				break;
			}
		}
		var type2='';
		for(var i=0;i<type.length;i++){
			if(type.options[i].selected){
				type2=type.options[i].value;
				break;
			}
		}
		var recRatingState2='';
		for(var i=0;i<recRatingState.length;i++){
			if(recRatingState.options[i].selected){
				recRatingState2=recRatingState.options[i].value;
				break;
			}
		}
		var recAssessmentState2='';
		for(var i=0;i<recAssessmentState.length;i++){
			if(recAssessmentState.options[i].selected){
				recAssessmentState2=recAssessmentState.options[i].value;
				break;
			}
		}
		var recordOverTime2='';
		for(var i=0;i<recordOverTime.length;i++){
			if(recordOverTime.options[i].selected){
				recordOverTime2=recordOverTime.options[i].value;
				break;
			}
		}
		var pageNo2=pageNo2=document.getElementById("pageNo").value;
		frName2=encodeURI(encodeURI(frName2));
		frNo3=encodeURI(encodeURI(frNo3));
		zw2=encodeURI(encodeURI(zw2));
		qsName2=encodeURI(encodeURI(qsName2));
		jq2=encodeURI(encodeURI(jqNo2));
		jy2=encodeURI(encodeURI(jy2));
		type2=encodeURI(encodeURI(type2));
		recRatingState2=encodeURI(encodeURI(recRatingState2));
		recAssessmentState2=encodeURI(encodeURI(recAssessmentState2));
		recordOverTime2=encodeURI(encodeURI(recordOverTime2));
		
		var tr = event.srcElement;
		while (tr && tr.tagName != "TR") {
			tr = tr.parentElement;
		}
		var callIds = document.getElementsByName("callId");
		var callId=callIds[tr.rowIndex-4].value;
		window.location.href("hjRecord.do?method=checkRecordInfo&callId="+callId+"&callTimeStart2="+callTimeStart2+"&callTimeEnd2="+callTimeEnd2+"&frName2="+frName2+"&frNo3="+frNo3+"&zw2="+zw2+"&type2="+type2+"&jy2="+jy2+"&jq2="+jq2+"&qsName2="+qsName2+"&pageNo2="+pageNo2+"&recRatingState2="+recRatingState2+"&recAssessmentState2="+recAssessmentState2+"&recordOverTime2="+recordOverTime2);
//	}else{
//		alert("你没有这个权限");
//	}
}

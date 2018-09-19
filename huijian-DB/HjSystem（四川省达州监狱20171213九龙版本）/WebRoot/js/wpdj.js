function refreshMeetInfo(){
 		 $.ajax({
 		      type:"POST",
 		      url:"wpdj.do",
 		      data:"method=jquerSearch",
 		      dataType:"json",
 		      success:callback2   
 		});
}

function callback2(data){
	var hjdjTable=document.getElementById("hjdjTable");
	var rows = hjdjTable.rows.length ;
	if(data.length>rows){
		hjdjTable.innerText="";
		for(var i=0;i<data.length;i++){
			var tr=hjdjTable.insertRow();
			tr.onmouseover=function(){this.style.background ='#FFC1C1';};
			tr.onmouseout=function(){this.style.background ='#FFFFFF';};
			var td=tr.insertCell();
			td.noWrap=true;
			td.innerHTML="<a href=\"javascript:void(0)\" onclick=\"wpdj("+data[i].hjid+");return false;\">登记物品</a>";
			alert(td.innerHTML);
			var td1=tr.insertCell();
			td1.noWrap=true;
			if(i==0){
				td1.innerHTML=data[i].jqName+"<input type=\"hidden\" id=\"isNoPrint\" name=\"isNoPrint\" value=\"1\" /><input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
			}else{
				td1.innerHTML=data[i].jqName+"<input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
			}
			var td2=tr.insertCell();
			td2.noWrap=true;
			td2.innerText=data[i].frNo;
			var td3=tr.insertCell();
			td3.noWrap=true;
			td3.innerText=data[i].frName;
			var td8=tr.insertCell();
			td8.noWrap=true;
			if(data[i].fpFlag=='0'){
				td8.innerHTML="<font color=\"red\">未分配</font>";
			}else{
				td8.innerText=data[i].zw;
			}
			var td12=tr.insertCell();
			td12.noWrap=true;
			if(data[i].fpTzfrFlag==0){
				td12.innerHTML="<font color=\"red\">未通知</font>";
			}else if(data[i].fpTzfrFlag==1){
				td12.innerText="已通知";
			}
			var td10=tr.insertCell();
			td10.noWrap=true;
			if(data[i].hjType==1){
				td10.innerText="考察";
			}else if(data[i].hjType==2){
				td10.innerText="宽管";
			}else if(data[i].hjType==3){
				td10.innerText="普管";
			}else if(data[i].hjType==4){
				td10.innerText="帮教";
			}else if(data[i].hjType==5){
				td10.innerText="提审";
			}
			var td11=tr.insertCell();
			td11.noWrap=true;
			td11.innerText=data[i].hjInfo;
			var td6=tr.insertCell();
			td6.noWrap=true;
			td6.innerText=data[i].djTime;
			var td4=tr.insertCell();
			td4.noWrap=true;
			td4.innerText=data[i].qsInfo1;
			var td13=tr.insertCell();
			td13.noWrap=true;
			if(data[i].infoWp==1){
				td13.innerHTML="<font color=\"red\">有</font>";
			}else{
				td13.innerText="无";
			}
			
		}
		document.getElementById("Mediaplayer").controls.play();
	}else{
		var webId=document.getElementsByName("webId");
		if(data.length>0){
			for(var j=0;j<data.length;j++){
				var m=0;
				for(var i=0;i<webId.length;i++){
					if(webId[i].value!=data[j].hjid){
						m++;
					}else{
						break;
					}
				}
				if(m==webId.length){
					document.getElementById("Mediaplayer").controls.play();
					j=webId.length;
				}
			}
			hjdjTable.innerText="";
			for(var i=0;i<data.length;i++){
				var tr=hjdjTable.insertRow();
				tr.onmouseover=function(){this.style.background ='#FFC1C1';};
				tr.onmouseout=function(){this.style.background ='#FFFFFF';};
				var td=tr.insertCell();
				td.noWrap=true;
				td.innerHTML="<a href=\"javascript:void(0)\" onclick=\"wpdj("+data[i].hjid+");return false;\">登记物品</a>";
				var td1=tr.insertCell();
				td1.noWrap=true;
				if(i==0){
					td1.innerHTML=data[i].jqName+"<input type=\"hidden\" id=\"isNoPrint\" name=\"isNoPrint\" value=\"1\" /><input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
				}else{
					td1.innerHTML=data[i].jqName+"<input type=\"hidden\" name=\"webId\" value=\""+data[i].hjid+"\" /><input type=\"hidden\" name=\"fpFlag\" value=\""+data[i].fpFlag+"\" />";
				}
				var td2=tr.insertCell();
				td2.noWrap=true;
				td2.innerText=data[i].frNo;
				var td3=tr.insertCell();
				td3.noWrap=true;	
				td3.innerText=data[i].frName;
				var td8=tr.insertCell();
				td8.noWrap=true;
				if(data[i].fpFlag=='0'){
					td8.innerHTML="<font color=\"red\">未分配</font>";
				}else{
					td8.innerText=data[i].zw;
				}
				var td12=tr.insertCell();
				td12.noWrap=true;
				if(data[i].fpTzfrFlag==0){
					td12.innerHTML="<a href=\"javascript:void(0)\" onclick=\"sdNotice();return false;\"><font color=\"red\">未通知</font></a>";
				}else if(data[i].fpTzfrFlag==1){
					td12.innerText="已通知";
				}
				var td10=tr.insertCell();
				td10.noWrap=true;
				if(data[i].hjType==1){
					td10.innerText="考察";
				}else if(data[i].hjType==2){
					td10.innerText="宽管";
				}else if(data[i].hjType==3){
					td10.innerText="普管";
				}else if(data[i].hjType==4){
					td10.innerText="帮教";
				}else if(data[i].hjType==5){
					td10.innerText="提审";
				}
				var td11=tr.insertCell();
				td11.noWrap=true;
				td11.innerText=data[i].hjInfo;
				var td6=tr.insertCell();
				td6.noWrap=true;
				td6.innerText=data[i].djTime;
				var td4=tr.insertCell();
				td4.noWrap=true;
				td4.innerText=data[i].qsInfo1;
				var td13=tr.insertCell();
				td13.noWrap=true;
				if(data[i].infoWp==1){
					td13.innerHTML="<font color=\"red\">有</font>";
				}else{
					td13.innerText="无";
				}
				
			}
		}else{
			hjdjTable.innerText="";
			var tr=hjdjTable.insertRow();
			var td=tr.insertCell();
			td.colSpan=11;
			td.innerHTML="<font color=\"red\">没有相关信息</font>";
		}
	}
		
}
function wpdj(hjid){
	document.forms[0].action="wpdj.do?method=updateWpdj&hjid="+hjid;
	document.forms[0].submit();
}
function updateSaveWpdj(){
	var hjid=document.getElementById("hjid").value;
	var wpNo=document.getElementById("info").value;
	var infos=wpNo.split(",");
	var href="";
	var patrn=/^-?\d+$/;
	for(var i=0;i<infos.length;i++){
		var bb="wp"+infos[i];
		var count=document.getElementById(bb).value;
		if (!patrn.test(count) || count<0){ 
			alert("非法字符,请输入整数");
			return false;
		} 
		href+="&count"+infos[i]+"="+count;
	}
	 $.ajax({
 		      type:"POST",
 		      url:"wpdj.do",
 		      data:"method=updateSaveWpdj&hjid="+hjid+href+"&wpNo="+wpNo,
 		      dataType:"json",
 		      success:callback3   
 		});
	
		
}
function callback3 (data){
	var hjid=document.getElementById("hjid").value;
	if(data[0]=='1'){
		//alert(basePath);
		var url=basePath+"wpdj.do?method=printWp&hjid="+hjid;
		window.open(url,"","width=360,height=150,left=1120,top=720,dependent=yes,scroll:no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,directories=no,status=no");
	}
	document.forms[0].action="wpdj.do?method=search";
	document.forms[0].submit();
}

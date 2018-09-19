<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>main</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>js/hjRecord.js" type="text/javascript" ></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/jqueryUI/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>js/jqueryUI/themes/icon.css" />
		<script src="<%=basePath %>js/jqueryUI/jquery-1.7.2.min.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/jqueryUI/jquery.easyui.min.js"type="text/javascript"> </script>
		<script src="<%=basePath %>js/jqueryUI/locale/easyui.lang-zh_CN.js"type="text/javascript"> </script>
		<script type="text/javascript">
		window.setInterval(refreshMeetInfo1, 10000); 
		document.onkeydown   =   function(){       
			if(event.keyCode   ==   8)   
			{   
			if(event.srcElement.tagName.toLowerCase()   !=   "input"   
			&&   event.srcElement.tagName.toLowerCase()   !=   "textarea")   
			event.returnValue   =   false;   
			}   
		}
		
		function jiantingyinpin(){
			var tr = event.srcElement;
			while (tr && tr.tagName != "TR") {
				tr = tr.parentElement;
			}
				 var lineNos = document.getElementsByName("lineNo");
			  	 var serverNames = document.getElementsByName("jy");
			  	 var zwNos = document.getElementsByName("zwNo");
			  	 var hjids = document.getElementsByName("hjid");
				 var lineTypes = document.getElementsByName("lineType");
				 var lineNo = lineNos[tr.rowIndex-1].value;
				 var serverName = serverNames[tr.rowIndex-1].value;
				 var zwNo = zwNos[tr.rowIndex-1].value;
				 var hjid = hjids[tr.rowIndex-1].value;
				 var lineType = lineTypes[tr.rowIndex-1].value;
				 document.getElementById(serverName).ListenTele(lineNo);
				 var serverName = serverNames[tr.rowIndex-1].value;
			 	 if(document.getElementById("info").innerText==""){
					document.getElementById("info").innerHTML="<font color=\"red\" style=\"font-size:100%;font-weight:normal;\">"+lineNo+"号线路正在被监听</font><input type=\"hidden\" id=\"ingLineNo\" name=\"ingLineNo\" value=\""+lineNo+"\">&nbsp;&nbsp;&nbsp;&nbsp;";
					tr.cells[8].children[1].innerHTML="<a href=\"javascript:void(0)\" onclick=\"jiesujianting();return false;\">停止监听</a>";
					tr.cells[8].children[2].innerHTML="<a href=\"javascript:void(0)\" onclick=\"qieduan();return false;\">切断</a>";
			  	   	tr.cells[8].children[3].innerHTML="<span id=\"jtyp\">监听音频</span>";
			  	   	document.getElementById("lineCh").value=zwNo;
			  	   	document.getElementById("lineCh1").value=lineNo;
			  	   	document.getElementById("serverNameCh").value=serverName;
			  	    document.getElementById("ingHjid").value=hjid;
			  	   	document.getElementById("ingLineType").value=lineType;
			  	    var table=document.getElementById("info5");
					var uptr=table.getElementsByTagName("tr");
					for(var i=0;i<uptr.length;i++){
				  	  var uptd=uptr[i].getElementsByTagName("td");
			          if(uptd[1].innerText!=lineNo || uptd[0].innerText!=serverName){
		   	          	uptd[8].children[0].innerHTML="<span id=\"jt\">监听音视频</span>";
		   	          	uptd[8].children[3].innerHTML="<span id=\"jypt\">监听音频</span>";
			          }
			        }
			     }
				document.getElementById("jtyinpin1").value=1;
			    
		}
		function jianting(){
			var tr = event.srcElement;
			while (tr && tr.tagName != "TR") {
				tr = tr.parentElement;
			}
			var jttype=document.getElementById("jttype").value;
			if(jttype=='0' || jttype==''){
				var lineNos = document.getElementsByName("lineNo");
				var ips = document.getElementsByName("ip");
				var ports = document.getElementsByName("port");
				var audioPorts = document.getElementsByName("audioPort");
				var serverNames = document.getElementsByName("jy");
				var zwNos = document.getElementsByName("zwNo");
				var hjids = document.getElementsByName("hjid");
				var lineTypes = document.getElementsByName("lineType");
				var lineNo = lineNos[tr.rowIndex-1].value;
				var ip = ips[tr.rowIndex-1].value;
				var port = ports[tr.rowIndex-1].value;
				var audioPort = audioPorts[tr.rowIndex-1].value;
				var serverName = serverNames[tr.rowIndex-1].value;
				var zwNo = zwNos[tr.rowIndex-1].value;
				var hjid = hjids[tr.rowIndex-1].value;
				var lineType = lineTypes[tr.rowIndex-1].value;
				document.getElementById(serverName).ListenTele(lineNo);
				if(document.getElementById("info").innerText==""){
					document.getElementById("info").innerHTML="<font color=\"red\" style=\"font-size:100%;font-weight:normal;\">"+lineNo+"号线路正在被监听</font><input type=\"hidden\" id=\"ingLineNo\" name=\"ingLineNo\" value=\""+lineNo+"\">&nbsp;&nbsp;&nbsp;&nbsp;";
					tr.cells[8].children[1].innerHTML="<a href=\"javascript:void(0)\" onclick=\"jiesujianting();return false;\">停止监听</a>";
					tr.cells[8].children[2].innerHTML="<a href=\"javascript:void(0)\" onclick=\"qieduan();return false;\">切断</a>";
					tr.cells[8].children[3].innerHTML="<span id=\"jtyp\">监听音频</span>";
			  	   	document.getElementById("lineCh").value=zwNo;
			  	   	document.getElementById("lineCh1").value=lineNo;
			  	   	document.getElementById("serverNameCh").value=serverName;
			  	   	document.getElementById("ingHjid").value=hjid;
			  	   	document.getElementById("ingLineType").value=lineType;
			  	    var table=document.getElementById("info5");
					var uptr=table.getElementsByTagName("tr");
					for(var i=0;i<uptr.length;i++){
				  	  var uptd=uptr[i].getElementsByTagName("td");
			          if(uptd[1].innerText!=lineNo || uptd[0].innerText!=serverName){
		   	          	uptd[8].children[0].innerHTML="<span id=\"jt\">监听音视频</span>";
		   	          	uptd[8].children[3].innerHTML="<span id=\"jtyp\">监听音频</span>";
			          }
			        }
			    }
			}else{
				 var lineNos = document.getElementsByName("lineNo");
			  	 var serverNames = document.getElementsByName("jy");
			  	 var zwNos = document.getElementsByName("zwNo");
			  	 var hjids = document.getElementsByName("hjid");
				 var lineTypes = document.getElementsByName("lineType");
				 var lineNo = lineNos[tr.rowIndex-1].value;
				 var serverName = serverNames[tr.rowIndex-1].value;
				 var zwNo = zwNos[tr.rowIndex-1].value;
				 var hjid = hjids[tr.rowIndex-1].value;
				 var lineType = lineTypes[tr.rowIndex-1].value;
				 document.getElementById(serverName).ListenTele(lineNo);
				 var serverName = serverNames[tr.rowIndex-1].value;
			 	 if(document.getElementById("info").innerText==""){
					document.getElementById("info").innerHTML="<font color=\"red\" style=\"font-size:100%;font-weight:normal;\">"+lineNo+"号线路正在被监听</font><input type=\"hidden\" id=\"ingLineNo\" name=\"ingLineNo\" value=\""+lineNo+"\">&nbsp;&nbsp;&nbsp;&nbsp;";
					tr.cells[8].children[1].innerHTML="<a href=\"javascript:void(0)\" onclick=\"jiesujianting();return false;\">停止监听</a>";
					tr.cells[8].children[2].innerHTML="<a href=\"javascript:void(0)\" onclick=\"qieduan();return false;\">切断</a>";
			  	   	tr.cells[8].children[3].innerHTML="<span id=\"jtyp\">监听音频</span>";
			  	   	document.getElementById("lineCh").value=zwNo;
			  	   	document.getElementById("lineCh1").value=lineNo;
			  	   	document.getElementById("serverNameCh").value=serverName;
			  	    document.getElementById("ingHjid").value=hjid;
			  	   	document.getElementById("ingLineType").value=lineType;
			  	    var table=document.getElementById("info5");
					var uptr=table.getElementsByTagName("tr");
					for(var i=0;i<uptr.length;i++){
				  	  var uptd=uptr[i].getElementsByTagName("td");
			          if(uptd[1].innerText!=lineNo || uptd[0].innerText!=serverName){
		   	          	uptd[8].children[0].innerHTML="<span id=\"jt\">监听音视频</span>";
		   	          	uptd[8].children[3].innerHTML="<span id=\"jypt\">监听音频</span>";
			          }
			        }
			     }
				 var videoChan1Servers=document.getElementsByName("videoChan1Server");
				 var videoChan1Nos=document.getElementsByName("videoChan1No");
				 //var videoChan2Servers=document.getElementsByName("videoChan2Server");
				 var videoChan2Nos=document.getElementsByName("videoChan2No");
				 var videoChan1Server=videoChan1Servers[tr.rowIndex-1].value;
				 
				 if(videoChan1Server==""){
				 	return false;
				 }
				 
				 var videoChan1No=videoChan1Nos[tr.rowIndex-1].value;
				// var videoChan2Server=videoChan2Servers[tr.rowIndex-1].value;
				var videoChan2No='';
				if(videoChan2Nos[tr.rowIndex-1].value=='' || videoChan2Nos[tr.rowIndex-1].value==-1){
					  videoChan2No=videoChan1No;
				}else{
					  videoChan2No=videoChan2Nos[tr.rowIndex-1].value;
				}
				 
				$('#info2').window({
					title:zwNo+"监控画面",
					href:"hjMonitor.do?method=spMonitor&videoChan1Server="+videoChan1Server+"&videoChan1No="+videoChan1No+"&videoChan2No="+videoChan2No,
					width:730,
					height:400,
					collapsible:false,
					minimizable:false,
					maximizable:false,
					resizable:false,
					cache:false,
					onClose:function(){
						stop();
						jiesujianting1(serverName,lineNo);
					},
					onLoad:function(){
						play();
					}
				});
			}
			
		}
		function jiesujianting(){
		    var tr = event.srcElement;
			while (tr && tr.tagName != "TR") {
				tr = tr.parentElement;
			}
			var jttype=document.getElementById("jttype").value;
			if(jttype=='0' || jttype==''){
				var lineNos = document.getElementsByName("lineNo");
				var serverNames = document.getElementsByName("jy");
				var lineNo = lineNos[tr.rowIndex-1].value;
				var serverName = serverNames[tr.rowIndex-1].value;
				document.getElementById(serverName).ListenStop(lineNo);
				document.getElementById("info").innerText="";
				document.getElementById("info1").innerText="";
				document.getElementById("lineCh").value="";
				document.getElementById("serverNameCh").value="";
				document.getElementById("ingHjid").value="";
			  	document.getElementById("ingLineType").value="";
			  	tr.cells[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
				tr.cells[8].children[1].innerHTML="<span id=\"tz\">停止监听</span>";
			    tr.cells[8].children[2].innerHTML="<span id=\"qd\">切断</span>";
			    tr.cells[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
			    var table=document.getElementById("info5");
				var uptr=table.getElementsByTagName("tr");
				for(var i=0;i<uptr.length;i++){
				      var uptd=uptr[i].getElementsByTagName("td");
			          if(uptd[1].innerText!=lineNo || uptd[0].innerText!=serverName){
			                uptd[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
			                uptd[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
			          }
			    }
			 }else{
			 	var lineNos = document.getElementsByName("lineNo");
				var serverNames = document.getElementsByName("jy");
				var lineNo = lineNos[tr.rowIndex-1].value;
				var serverName = serverNames[tr.rowIndex-1].value;
				document.getElementById(serverName).ListenStop(lineNo);
				document.getElementById("info").innerText="";
				document.getElementById("info1").innerText="";
				document.getElementById("lineCh").value="";
				document.getElementById("serverNameCh").value="";
				document.getElementById("ingHjid").value="";
			  	document.getElementById("ingLineType").value="";
			  	tr.cells[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
				tr.cells[8].children[1].innerHTML="<span id=\"tz\">停止监听</span>";
			    tr.cells[8].children[2].innerHTML="<span id=\"qd\">切断</span>";
			    tr.cells[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
			    var table=document.getElementById("info5");
				var uptr=table.getElementsByTagName("tr");
				for(var i=0;i<uptr.length;i++){
				      var uptd=uptr[i].getElementsByTagName("td");
			          if(uptd[1].innerText!=lineNo || uptd[0].innerText!=serverName){
			                uptd[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
			         		uptd[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
			          }
			    }
			     var videoChan1Servers=document.getElementsByName("videoChan1Server");
				 var videoChan1Server=videoChan1Servers[tr.rowIndex-1].value;
				 if(videoChan1Server==""){
				 	return false;
				 }
			    //document.getElementById("MediaPlayer1").StopPlayer();
			    var jtyinpin1= document.getElementById("jtyinpin1").value;
			    if(jtyinpin1==0){
			    	 $('#info2').panel('close');
			    }
			   
			    
			 }
			 document.getElementById("jtyinpin1").value=0;
		} 
		function  jiesujianting1(serverName,lineNo){
			document.getElementById(serverName).ListenStop(lineNo);
			document.getElementById("info").innerText="";
			document.getElementById("info1").innerText="";
			document.getElementById("lineCh").value="";
			document.getElementById("serverNameCh").value="";
		    var table=document.getElementById("info5");
			var uptr=table.getElementsByTagName("tr");
			for(var i=0;i<uptr.length;i++){
			      var uptd=uptr[i].getElementsByTagName("td");
		          if(uptd[1].innerText==lineNo && uptd[0].innerText==serverName){
		          		uptd[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
		          		uptd[8].children[1].innerHTML="<span id=\"tz\">停止监听</span>";
		   				uptd[8].children[2].innerHTML="<span id=\"qd\">切断</span>";
		   				uptd[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
		   				
		          }else{
		          		uptd[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
		        		uptd[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
		          }
		    }
		}
		function qieduan(){
			if(window.confirm("是否确定切断通话？注意：切断后，需要前往会见登记处重新登记会见！")==false){
				return false;
			}
			
			var tr = event.srcElement;
			while (tr && tr.tagName != "TR") {
				tr = tr.parentElement;
			}
			var jtyinpin1=document.getElementById("jtyinpin1").value;
			var jttype=document.getElementById("jttype").value;
			if(jttype=='1' && jtyinpin1==0){
				$('#info2').panel('close');
		    }
		    var lineNos = document.getElementsByName("lineNo");
			var serverNames = document.getElementsByName("jy");
			var lineNo = lineNos[tr.rowIndex-1].value;
			var serverName = serverNames[tr.rowIndex-1].value;
			if(document.getElementById("ingLineType").value==0){
				document.getElementById(serverName).StopTele(lineNo);
				document.getElementById("info").innerText="";
				document.getElementById("info1").innerText="";
				document.getElementById("lineCh").value="";
				document.getElementById("serverNameCh").value="";
				document.getElementById("ingHjid").value="";
				document.getElementById("ingLineType").value="";
				tr.cells[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
				tr.cells[8].children[1].innerHTML="<span id=\"tz\">停止监听</span>";
			    tr.cells[8].children[2].innerHTML="<span id=\"qd\">切断</span>";
			    tr.cells[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
			    var table=document.getElementById("info5");
				var uptr=table.getElementsByTagName("tr");
				for(var i=0;i<uptr.length;i++){
				      var uptd=uptr[i].getElementsByTagName("td");
			          if(uptd[1].innerText!=lineNo || uptd[0].innerText!=serverName){
			                   uptd[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
			       	 		   uptd[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
			       	  }
			    }
			}else if(document.getElementById("ingLineType").value==1 && document.getElementById("ingHjid").value!="") { 
				document.getElementById("info").innerText="";
				document.getElementById("info1").innerText="";
				document.getElementById("lineCh").value="";
				document.getElementById("serverNameCh").value="";
				document.getElementById("ingLineType").value="";
				tr.cells[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
				tr.cells[8].children[1].innerHTML="<span id=\"tz\">停止监听</span>";
			    tr.cells[8].children[2].innerHTML="<span id=\"qd\">切断</span>";
			    tr.cells[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
			    var table=document.getElementById("info5");
				var uptr=table.getElementsByTagName("tr");
				for(var i=0;i<uptr.length;i++){
				      var uptd=uptr[i].getElementsByTagName("td");
			          if(uptd[1].innerText!=lineNo || uptd[0].innerText!=serverName){
			                   uptd[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听音视频</a></span>";
			       	 		   uptd[8].children[3].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jiantingyinpin();return false;\">监听音频</a></span>";
			       	  }
			    }
			     $.ajax({
			     
			   
				      type:"POST",
				      url:"hjMonitor.do",
				      data:"method=endKj&hjid="+document.getElementById("ingHjid").value,
				      dataType:"json",
				      success:callback20   
				});
			}
			document.getElementById("jtyinpin1").value=0;
		}
		function aa(){
		   	var ip='<%=request.getAttribute("sysQqServerList1")%>';
		   	var sysServer=ip.split("|");
		    for(var i=0;i<sysServer.length;i++){
		        var prop=sysServer[i].split(",");
		        document.getElementById(prop[0]).ConnectSvr(prop[1],prop[2]);//修改
		    }
		}
		function jtZhushi(){	
  			var info=document.getElementById("info").innerText;
 			if(info==""){
    			alert("没有在监听中");
 	 		}else{
     			var ingLineNo=document.getElementById("ingLineNo").value;
      			var table=document.getElementById("info5");
			    var ingmonitorCallID="";
			    var ingwriteTxt="";
			    var ingmonitorFlagId="";
				var uptr=table.getElementsByTagName("tr");
				for(var i=0;i<uptr.length;i++){
					var uptd=uptr[i].getElementsByTagName("td");
			        if(uptd[1].innerText==ingLineNo){
			        	ingmonitorCallID=uptd[7].children[1].value;
			            ingwriteTxt=uptd[7].children[4].value;
			            ingmonitorFlagId=uptd[7].children[3].value;
			            break;
			        }
			    }
        		if(ingmonitorCallID==""){
           			alert("该线路未处于通话中，无法注释");
       			 }else{
         			 if(ingmonitorFlagId==""){
	         			 document.getElementById("info1").innerHTML="注释内容：<input type=\"text\" name=\"ingwriteTxt\" id=\"ingwriteTxt\">&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"button\" value=\"保存\" onclick=\"addSaveMonitorFlag();\"><input type=\"hidden\" id=\"ingmonitorCallID\" name=\"ingmonitorCallID\" value=\""+ingmonitorCallID+"\"></a>";
	       			}else{
	        			 document.getElementById("info1").innerHTML="注释内容：<input type=\"text\" name=\"ingwriteTxt\" id=\"ingwriteTxt\" value=\""+ingwriteTxt+"\">&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"button\" value=\"保存\" onclick=\"updateSaveMonitorFlag();\" /><input type=\"hidden\" id=\"ingmonitorFlagId\" name=\"ingmonitorFlagId\" value=\""+ingmonitorFlagId+"\"></a>";
	        		}
     			 }
 			} 		
		 }
		 function chahua(){
		 	var lineCh=document.getElementById("lineCh1").value; 
		 	var serverNameCh=document.getElementById("serverNameCh").value; 
		 	var chId;
		 	if(lineCh!="" && serverNameCh!=""){
		 	    var chId=document.getElementById("chId");
		 	    for(var i=0;i<chId.length;i++){
		 	    	if(chId.options[i].selected){
		 	    		chId=chId.options[i].value;
		 	    		break;
		 	    	}
		 	    }
		 	    document.getElementById(serverNameCh).InsertVoc(lineCh,chId);
		 	}else{
		 		alert("当前不在监听状态，无法插话")
		 	}
		 }
		 function AllBreakLine(){
			var lineNos = document.getElementsByName("lineNo");
			var serverNames = document.getElementsByName("jy");
			document.getElementById("info").innerText="";
			document.getElementById("info1").innerText="";
			document.getElementById("lineCh").value="";
			document.getElementById("serverNameCh").value="";
		    var table=document.getElementById("info5");
			var uptr=table.getElementsByTagName("tr");
			for(var i=0;i<uptr.length;i++){
			      var uptd=uptr[i].getElementsByTagName("td");
			  	  var lineNo = lineNos[i].value;
				  var serverName = serverNames[i].value;
				  document.getElementById(serverName).StopTele(lineNo);
		          uptd[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听</a></span>";
		          uptd[8].children[1].innerHTML="<span id=\"tz\">停止监听</span>";
		         uptd[8].children[2].innerHTML="<span id=\"qd\">切断</span>";
		    }


	     } 
	     function breakAll(){
	        if(window.confirm("确认 全部切断?")==true){
	        	var lineNos = document.getElementsByName("lineNo");
				var serverNames = document.getElementsByName("jy");
				var table=document.getElementById("info5");
				var uptr=table.getElementsByTagName("tr");
				for(var i=0;i<lineNos.length;i++){
					var lineNo = lineNos[i].value;
					var serverName = serverNames[i].value;
					document.getElementById(serverName).StopTele(lineNo);
					document.getElementById("info").innerText="";
					document.getElementById("info1").innerText="";
					document.getElementById("lineCh").value="";
					document.getElementById("serverNameCh").value="";
			        var uptd=uptr[i].getElementsByTagName("td");
		            if(uptd.length>8){
		                uptd[8].children[0].innerHTML="<span><a href=\"javascript:void(0)\" onclick=\"jianting();return false;\">监听</a></span>";
		       	    	uptd[8].children[1].innerHTML="<span id=\"tz\">停止监听</span>";
			            uptd[8].children[2].innerHTML="<span id=\"qd\">切断</span>";
		       	    }
				}
	        }
	     	
	     }   
		</script>
       
  </head>
  <body onload="aa();" >
	  <div id="user_info5"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	  <div id="lm_info5"><span>当前位置：实时监控</span></div>
	       <form action="" method="post">
	        	<div id="content5">
			     	<table  border="0" cellspacing="0" cellpadding="0">
			     	<tr>
			     	   <th width="10%" nowrap="nowrap">服务器名称</th> 
			     	   <th width="10%" nowrap="nowrap">通道</th> 
			     	   <th width="10%" nowrap="nowrap">座位号</th> 
			     	   <th width="10%" nowrap="nowrap">状态</th>
			     	   <th width="10%" nowrap="nowrap">罪犯监区</th>
			     	   <th width="10%" nowrap="nowrap">罪犯姓名</th>
			     	   <th width="10%" nowrap="nowrap">亲属信息</th>
			     	   <th width="12%" nowrap="nowrap">剩余时间</th>
			     	   <th width="14%" nowrap="nowrap">操作</th>
			     	</tr>
		 			<logic:notEmpty name="sysQqLineList">
		 				<tbody id="info5">
			     	   		<logic:iterate id="sl" name="sysQqLineList">
					     	   <tr>
					     	   	   <td width="10%" nowrap="nowrap">${sl. jy}</td>
					     	   	   <td width="10%" nowrap="nowrap">${sl. lineNo}</td>
					     	   	   <td width="10%" nowrap="nowrap">${sl. zw}</td>
					     	   	   <logic:empty name="sl" property="monitorState">
					     	        	<td width="10%" nowrap="nowrap"> <img src="images/kongxian.bmp"></img><input type="hidden" id="state" name="state" value="空闲"/></td>
					     	       </logic:empty>
					     	       <logic:notEmpty name="sl" property="monitorState">
					     	            <c:if test="${sl.monitorState == '通话'}">
					     	             <td width="10%" nowrap="nowrap"> <img src="images/tonghua.bmp"></img><input type="hidden" id="state" name="state" value="通话"/></td>
					     	            </c:if>
					     	              <c:if test="${sl.monitorState == '空闲'}">
					     	             <td width="10%" nowrap="nowrap"> <img src="images/kongxian.bmp"></img><input type="hidden" id="state" name="state" value="空闲"/></td>
					     	            </c:if>
					     	            <c:if test="${sl.monitorState == '应答'}">
					     	               <td width="10%" nowrap="nowrap"> <img src="images/huru.bmp"></img><input type="hidden" id="state" name="state" value="应答"/></td>
					     	            </c:if>
					     	       </logic:notEmpty>
					     	       <td width="10%" nowrap="nowrap">${sl. monitorJq}</td>
					     	       <td width="10%" nowrap="nowrap">${sl. monitorFr}</td>
					     	       <td width="10%" nowrap="nowrap">${sl. monitorQs}</td>
					     	       <td width="12%" nowrap="nowrap"><font>${sl. monitorTime}</font>
					     	                                      <input type="hidden" name="monitorCallID" value="${sl.monitorCallID }"/>
					     	                                      <input type="hidden" name="userNo" value="${sl.userNo }"/>
					     	                                      <input type="hidden" name="monitorFlagId" value="${sl.monitorFlagId }"/>
					     	                                      <input type="hidden" name="writeTxt" value="${sl.writeTxt}"/>
					     	                                      <input type="hidden" name="hjid" value="${sl.hjId}"/>
					     	                               
					     	       </td>
					     	       <td width="14%" nowrap="nowrap"><span id="jt"><a href="javascript:void(0)" onclick="jianting();return false;">监听音视频</a></span>
					     	                                      <span id="tz">停止监听</span>
					     	                                   	  <span id="qd">切断</span>
					     	                                   	  <span id="jtyp"><a href="javascript:void(0)" onclick="jiantingyinpin();return false;">监听音频</a></span>
					     	                                      <input type="hidden" name="lineNo" value="${sl.lineNo }" />
					     	                                      <input type="hidden" name="ip" value="${sl.ip }"/>
					     	                                      <input type="hidden" name="port" value="${sl.port }"/>
					     	                                      <input type="hidden" name="audioPort" value="${sl.audioPort }"/>
					     	                                      <input type="hidden" name="jy" value="${sl.jy}"/>
					     	                                      <input type="hidden" name="videoChan1Server" value="${sl.videoChan1Server}"/>
					     	                                      <input type="hidden" name="videoChan1No" value="${sl.videoChan1No}"/>
					     	                                      <input type="hidden" name="videoChan2Server" value="${sl.videoChan2Server}"/>
					     	                                      <input type="hidden" name="videoChan2No" value="${sl.videoChan2No}"/>
					     	                                      <input type="hidden" name="zwNo" value="${sl.zw }" />
					     	                                      <input type="hidden" name="lineType" value="${sl.lineType }" />
					     	      </td>
					     	   </tr>
						  	</logic:iterate>
						  	
				  		</tbody>
				  </logic:notEmpty>
					     <logic:empty name="sysQqLineList">
					     	<tr><td colspan="9"><font color="red">没有权限</font></td></tr>
					     </logic:empty>
			     	</table>
			     </div>
			     <div id="info" style="position:absolute;margin-top:20px;margin-left:20px;height:25px; line-height:25px;"></div><span style="position:absolute;margin-top:20px;margin-left:130px;height:25px; line-height:25px;">座位：<input type="text" id="lineCh" disabled="disabled" size="10" /><input type="hidden" id="serverNameCh"/><input type="hidden" id="lineCh1"  /><input type="hidden" id="ingHjid"  /><input type="hidden" id="ingLineType"  /><input type="hidden" id="jttype" value="${sysParam.paramData2}"/>&nbsp;&nbsp;<select id="chId"><logic:iterate name="jlMonitorVocList" id="jl"><option value="${jl. vocId}">${jl. vocInfo}</option></logic:iterate></select>&nbsp;&nbsp;<input type="button" onclick="chahua();" value="插话" /></span><a href="javascript:void(0)" onclick="jtZhushi();return false;" style="position:absolute;margin-top:20px;margin-left:600px;height:25px; line-height:25px;">监听注释</a><span id="info1" style="position:absolute;margin-top:20px;margin-left:670px;height:25px; line-height:25px;"></span><div id="addtime" style="display:block;position:absolute;position:absolute;margin-top:20px;z-index:2;margin-left:80%">增加时长：
			     <select id="time" style="width:80px;">
      	 				<option value="null">20分钟</option>
      	 				<option value="null">15分钟</option>
      	 				<option value="null">10分钟</option>
      	 				<option value="null">5分钟</option>
      	 				<option value="null">-5分钟</option>
      	 				<option value="null">-10分钟</option>
      	 				<option value="null">-15分钟</option>
      	 				<option value="null">-20分钟</option>
      	 		</select>&nbsp;&nbsp;<input type="button" value="保存" onclick="insertAddTime();" /></div>
		   		 <!-- <div id="info3" style="position:absolute;margin-top:50px;margin-left:130px;height:25px; line-height:25px;"><font color="green"></font>&nbsp;&nbsp;&nbsp;&nbsp;会见窗口使用情况：总数量&nbsp;&nbsp;<font color="red">19</font>&nbsp;&nbsp; 个，正在使用&nbsp;&nbsp;<font color="red">${hjm.threeShiyong }</font>&nbsp;&nbsp;个，未使用&nbsp;&nbsp;<font color="red">${hjm.threeWeishiyong }</font>&nbsp;&nbsp;个，屏蔽&nbsp;&nbsp;<font color="red">${hjm.threePingbi }</font>&nbsp;&nbsp;个；</div>
		   		 <div id="info4" style="position:absolute;margin-top:80px;margin-left:130px;height:25px; line-height:25px;"><font color="green"></font>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">宽见</font>窗口使用情况：总数量&nbsp;&nbsp;<font color="red">8</font>&nbsp;&nbsp; 个，正在使用&nbsp;&nbsp;<font color="red">${hjm.threeKJshiyong }</font>&nbsp;&nbsp;个，未使用&nbsp;&nbsp;<font color="red">${hjm.threeKJweishiyong }</font>&nbsp;&nbsp;个，屏蔽&nbsp;&nbsp;<font color="red">${hjm.threeKJpingbi }</font>&nbsp;&nbsp;个；</div>  
		   	<div id="info6" style="position:absolute;margin-top:110px;margin-left:180px;height:25px; line-height:25px;"><font color="green">三楼</font>&nbsp;&nbsp;&nbsp;&nbsp;会见窗口使用情况：总数量&nbsp;&nbsp;<font color="red">27</font>&nbsp;&nbsp; 个，正在使用&nbsp;&nbsp;<font color="red">${hjm.fourShiyong }</font>&nbsp;&nbsp;个，未使用&nbsp;&nbsp;<font color="red">${hjm.fourWeishiyong }</font>&nbsp;&nbsp;个，屏蔽&nbsp;&nbsp;<font color="red">${hjm.fourPingbi }</font>&nbsp;&nbsp;个；</div>-->
		   		 <div id="info2"></div><input type="hidden" id="jtyinpin1" vaule="0" />
		   		 <div id="load" style="display:none;position:absolute;margin-top:20%;position:absolute;z-index:2;margin-left:45%"></div>
		   </form>
		   <logic:notEmpty name="sysQqServerList">
		   		<logic:iterate id="jl" name="sysQqServerList">
				    <object id="${jl.serverName }" name="${jl.serverName}" codebase="<%=path%>/ocx/TeleQqOcx.ocx#version=1,0,0,1" classid="clsid:561E476B-6C4F-4FCC-A8CE-A85C7F781620" 
		 		 		width="0" height="0">
					</object>
				</logic:iterate>
			</logic:notEmpty>
  </body>
</html>

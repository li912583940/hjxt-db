<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- <object classid="CLSID:745473B3-810B-4EA7-8FC6-5E59115D80EB" width="720" height="330"  id="MediaPlayer1"></object> -->
<object classid="CLSID:5C1FFD28-7371-4821-BC43-7145AB66AF82" width="700" height="350" standby="Loading Windows Media Player components..." id="MediaPlayer1"></object>
 <form action="" method="post">
 		 
 		<input type="hidden" id="txtlChannel" value="${videoChan1No}" />
 		<input type="hidden" id="txt2Channel" value="${videoChan2No}" />
 		<input type="hidden" id="txtDivIP" value="${sysHjVideo.ip}" />
 		<input type="hidden" id="txtDivPort" value="${sysHjVideo.port}" />
 		<input type="hidden" id=txtLogName value="${sysHjVideo.loginUser}" />
 		<input type="hidden" id=txtLogPassWord value="${sysHjVideo.loginPwd}" />
 </form>
  <script language="javascirpt" type="text/javascript">
  
  		var isSetDivInf = false;   //是否已经在控件中注册,默认为false
		
//		function play() {
//		    var lChannel = document.getElementById("txtlChannel").value.replace(/^\s+|\s+$/g, "");
//		    var lChannel1 = document.getElementById("txt2Channel").value.replace(/^\s+|\s+$/g, "");         //通道号
//		    var divIP = document.getElementById("txtDivIP").value.replace(/^\s+|\s+$/g, "");              //硬盘录像机IP
//		    var txtDivPort = document.getElementById("txtDivPort").value.replace(/^\s+|\s+$/g, "");       //硬盘录像机端口号
//		    var txtLogName = document.getElementById("txtLogName").value.replace(/^\s+|\s+$/g, "");       //硬盘录像机登录用户名
//		    var txtLogPassWord = document.getElementById("txtLogPassWord").value.replace(/^\s+|\s+$/g, ""); //硬盘录像机登录密码
//		    var aa=document.getElementById("MediaPlayer1").Open(divIP, txtDivPort, txtLogName, txtLogPassWord,lChannel,lChannel1); 
	       // if (aa!=50000){
	        //	alert(aa);
	       // }
	        //if (!isSetDivInf) {
	        //    isSetDivInf = document.getElementById("MediaPlayer1").RegistDvr(divIP, txtDivPort, txtLogName, txtLogPassWord,lChannel,lChannel1);
	        //}
	        //if (isSetDivInf) {
	        //    document.getElementById("MediaPlayer1").Player();
	            
	       // }
//		}
//		function stop(){
	//		 var a = getElementById("MediaPlayer1");
	//		 a.remove();
	//		MediaPlayer1.removeNode(true)
	//	}
		function play() {
		    var lChannel = document.getElementById("txtlChannel").value.replace(/^\s+|\s+$/g, "");
		    var lChannel1 = document.getElementById("txt2Channel").value.replace(/^\s+|\s+$/g, "");         //通道号
		    var divIP = document.getElementById("txtDivIP").value.replace(/^\s+|\s+$/g, "");              //硬盘录像机IP
		    var txtDivPort = document.getElementById("txtDivPort").value.replace(/^\s+|\s+$/g, "");       //硬盘录像机端口号
		    var txtLogName = document.getElementById("txtLogName").value.replace(/^\s+|\s+$/g, "");       //硬盘录像机登录用户名
		    var txtLogPassWord = document.getElementById("txtLogPassWord").value.replace(/^\s+|\s+$/g, ""); //硬盘录像机登录密码
		     
	        if (!isSetDivInf) {
	            isSetDivInf = document.getElementById("MediaPlayer1").RegistDvr(divIP, txtDivPort, txtLogName, txtLogPassWord,lChannel,lChannel1);
	        }
	        if (isSetDivInf) {
	           
	            document.getElementById("MediaPlayer1").Player();
	            
	        }
		}
		function stop(){
			 document.getElementById("MediaPlayer1").StopPlayer();
		}
</script>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>播放录音</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
	<script src="<%=basePath %>js/hjRecord.js" type="text/javascript" ></script>
	<script   language="javascript">
		window.setInterval(refreshMeetInfo1, 1000);
		function refreshMeetInfo1() {
			var webId=document.getElementById("webId").value;
			var callTimeLen=document.getElementById("callTimeLen").value;
			var callId=document.getElementById("callId").value;
//			alert(callTimeLen);
//			alert(webId);
			var callTimeLen1=callTimeLen-9;
			var callTimeLen2=callTimeLen-10;
			
			var mp=document.getElementById("Mediaplayer");
			var time=mp.controls.currentPositionString;
			//alert(time);
			var Temp = time.split(':')
			var Seconds = 60 * Number(Temp[0]) + Number(Temp[1])
			//alert(Seconds);
			
			if(Seconds==callTimeLen1 || Seconds==callTimeLen2){
//				alert(callTimeLen1);
//				alert(Seconds);

				$.ajax({
				      type:"POST",
				      url:"hjRecord.do",
				      data:"method=updateSaveAssessmentState&webId="+webId+"&callId="+callId,
				      dataType:"json",
				      success:callback666,
				      error:callback1
				});
			}
		}
		function callback666(data){
			if(data[0]==null){
				alert("谢谢，当前录音的播放时长已达到复听要求");
				return false;
			}
		} 
		
	</script>
  </head>
  <body>
				<div><object name="Mediaplayer" id="Mediaplayer" width="350" height="65" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">
				    <param name="AutoStart" value="1" />
				    <!--是否name动播放-->
				    <param name="Balance" value="0" />
				    <!--调整左右声道平衡,同上面旧播放器代码-->
				    <param name="enabled" value="1" />
				    <!--播放器是否可人为控制-->
				    <param name="EnableContextMenu" value="1" />
				    <!--是否启用上下文菜单-->
				    <param name="url" value="${bofangId}" />
				    <!--播放的文件地址-->
				    <param name="PlayCount" value="1" />
				    <!--播放次数控制,为整数-->
				    <param name="rate" value="1" />
				    <!--播放速率控制,1为正常,允许小数,1.0-2.0-->
				    <param name="currentPosition" value="0" />
				    <!--控件设置:当前位置-->
				    <param name="currentMarker" value="0" />
				    <!--控件设置:当前标记-->
				    <param name="defaultFrame" value="" />
				    <!--显示默认框架-->
				    <param name="invokeURLs" value="0" />
				    <!--脚本命令设置:是否调用URL-->
				    <param name="baseURL" value="" />
				    <!--脚本命令设置:被调用的URL-->
				    <param name="stretchToFit" value="0" />
				    <!--是否按比例伸展-->
				    <param name="volume" value="50" />
				    <!--默认声音大小0%-100%,50则为50%-->
				    <param name="mute" value="0" />
				    <!--是否静音-->
				    <param name="uiMode" value="Full" />
				    <!--播放器显示模式:Full显示全部;mini最简化;None不显示播放控制,只显示视频窗口;invisible全部不显示-->
				    <param name="windowlessVideo" value="0" />
				    <!--如果是0可以允许全屏,否则只能在窗口中查看-->
				    <param name="fullScreen" value="0" />
				    <!--开始播放是否自动全屏-->
				    <param name="enableErrorDialogs" value="0" />
				    <!--是否启用错误提示报告-->
				    <param name="SAMIStyle" value />
				    <!--SAMI样式-->
				    <param name="SAMILang" value />
				    <!--SAMI语言-->
				    <param name="SAMIFilename" value />
				    <!--字幕ID-->
     		</object>
	</div>
	<input type="hidden" id="webId" name="webId" value="${webId}" /><input type="hidden" id="callTimeLen" name="callTimeLen" value="${jlHjRec.callTimeLen}" /><input type="hidden" id="callId" name="callId" value="${jlHjRec.callId}" />
  </body>
</html>

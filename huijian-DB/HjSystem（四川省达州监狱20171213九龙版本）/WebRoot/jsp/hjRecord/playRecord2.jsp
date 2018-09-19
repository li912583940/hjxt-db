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
			window.setInterval(refreshMeetInfo2, 1000);
			function refreshMeetInfo2() {
				var webId=document.getElementById("webId").value;
				var callTimeLen=document.getElementById("callTimeLen").value;
				var callId=document.getElementById("callId").value;
		//		alert(callTimeLen);
		//		alert(webId);
				var callTimeLen1=callTimeLen-9;
				var callTimeLen2=callTimeLen-10;
				
				var mp=document.getElementById("Player1");
				var time=mp.controls.currentPositionString;
				//alert(time);
				var Temp = time.split(':')
				var Seconds = 60 * Number(Temp[0]) + Number(Temp[1])
				//alert(Seconds);
				
				if(Seconds==callTimeLen1 || Seconds==callTimeLen2){
		//			alert(callTimeLen1);
		//			alert(Seconds);
		
					$.ajax({
					      type:"POST",
					      url:"hjRecord.do",
					      data:"method=updateSaveAssessmentState&webId="+webId+"&callId="+callId,
					      dataType:"json",
					      success:callback777,
					      error:callback1
					});
				}
			}
			function callback777(data){
				if(data[0]==null){
					alert("谢谢，当前录音的播放时长已达到复听要求");
					return false;
				}
			} 
	</script>
	<script type="text/javascript" language="javascript">
			function play() { 
				//document.getElementById("Player1").controls.currentPosition=20;
		       
		        if (document.getElementById("Player1").controls.isavailable('play')) { 
		           //	document.getElementById("Player").controls.play(); 
		            document.getElementById("Player1").controls.play(); 
		          //  document.getElementById("Player2").controls.play(); 
		        } 
			} 
			function  fastForward(){
				  document.getElementById("Player1").controls.currentPosition += 5;
				  //document.getElementById("Player2").controls.currentPosition += 5;
				  //document.getElementById("Player").controls.currentPosition += 5;
				  //document.getElementById("Player1").controls.fastForward(); 
				  //document.getElementById("Player").controls.fastForward;
			}
			function  dfastForward(){
				alert("点击过快");
				return false;
			}
			function  fastReverse(){
				 document.getElementById("Player1").controls.currentPosition -=5;
				 //document.getElementById("Player1").controls.currentPosition -=5;
				 //document.getElementById("Player").controls.currentPosition -= 5; 
			}
			function  dfastReverse(){
				alert("点击过快");
				return false;
			}
			function voldown() { 
		         if (document.getElementById("Player1").settings.volume < 5 ) { 
		             document.getElementById("Player").settings.volume = 0;
		             document.getElementById("Player1").settings.volume = 0; 
		             document.getElementById("Player2").settings.volume = 0;  
		         } 
		         else{
		             document.getElementById("Player").settings.volume -= 5; 
		             document.getElementById("Player1").settings.volume -= 5; 
		             document.getElementById("Player2").settings.volume -= 5; 
		         } 
			} 
			
			function volup() { 
		        if (document.getElementById("Player1").settings.volume > 95 ) { 
		              document.getElementById("Player").settings.volume = 100; 
		              document.getElementById("Player1").settings.volume = 100; 
		              document.getElementById("Player2").settings.volume = 100; 
		        } 
		        else{ 
		             document.getElementById("Player").settings.volume += 5; 
		             document.getElementById("Player1").settings.volume += 5; 
		             document.getElementById("Player2").settings.volume += 5; 
		        } 
			} 
			
			function pause() {
				// document.getElementById("Player").controls.pause(); 
				 //document.getElementById("Player1").controls.pause(); 
				 //document.getElementById("Player2").controls.pause(); 
			 	 if (document.getElementById("Player1").controls.isavailable('pause')) { 
				     document.getElementById("Player1").controls.pause(); 
				 }
			    // if (document.getElementById("Player2").controls.isavailable('pause')) { 
			    //	 document.getElementById("Player2").controls.pause(); 
				// }
		        //if (document.getElementById("Player").controls.isavailable('pause')) { 
		        //     document.getElementById("Player").controls.pause(); 
		              
		       // } 
			} 
			
			function stop(){
		    	if (document.getElementById("Player1").controls.isavailable('stop')){ 
		    		//document.getElementById("Player").controls.stop(); 
		    		document.getElementById("Player1").controls.stop(); 
		    		//document.getElementById("Player2").controls.stop(); 
		         } 
			} 
			
			function mute() { 
		      		document.getElementById("Player").settings.mute = ! document.getElementById("Player").settings.mute; 
		          	document.getElementById("Player1").settings.mute = ! document.getElementById("Player1").settings.mute;
		          	document.getElementById("Player2").settings.mute = ! document.getElementById("Player2").settings.mute;  
			} 
			var i=0;
	</script>
	<script  type="text/javascript" for="Player1" event="PlayStateChange(lOldState, lNewState)" >
						if(i<=2){
							if(Player1.playState==3){
								if(i==2){
									document.getElementById("Player2").controls.play();
									document.getElementById("Player").controls.play();
								}else{
									document.getElementById("Player1").controls.pause();
								}
								i++;
								
							}
						}else if(i>2){
							if(Player1.playState==3){
								document.getElementById("Player2").controls.play();
								document.getElementById("Player").controls.play();
							}
							if(Player1.playState==2){
								document.getElementById("Player2").controls.pause();
								document.getElementById("Player").controls.pause();
							}else if(Player1.playState==1){
								document.getElementById("Player2").controls.stop();
								document.getElementById("Player").controls.stop();
							}
						}
						
  						
  	</script>
  	<script  type="text/javascript" for="Player2" event="PlayStateChange(lOldState, lNewState)" >
						if(i<=2){
							if(Player2.playState==3){
								if(i==2){
									document.getElementById("Player1").controls.play();
									document.getElementById("Player").controls.play();
								}else{
									document.getElementById("Player2").controls.pause();
								}
								i++;
							}
						}
						//else if(Player1.playState==2){
						//	document.getElementById("Player2").controls.pause();
						//	document.getElementById("Player").controls.pause();
						//}else if(Player1.playState==1){
						//	document.getElementById("Player2").controls.stop();
						//	document.getElementById("Player").controls.stop();
						//}
  						
  	</script>
  	<script  type="text/javascript" for="Player" event="PlayStateChange(lOldState, lNewState)" >
						if(i<=2){
							if(Player.playState==3){
								if(i==2){
									document.getElementById("Player1").controls.play();
									document.getElementById("Player2").controls.play();
								}else{
									document.getElementById("Player").controls.pause();
								}
								i++;
							}
						}
						//else if(Player1.playState==2){
						//	document.getElementById("Player2").controls.pause();
						//	document.getElementById("Player").controls.pause();
						//}else if(Player1.playState==1){
						//	document.getElementById("Player2").controls.stop();
						//	document.getElementById("Player").controls.stop();
						//}
  						
  	</script>
	<script  type="text/javascript" for="Player1" event="PositionChange(oldPosition,newPosition)" >
						if(Player1.playState==3){
								document.getElementById("Player2").controls.currentPosition=document.getElementById("Player1").controls.currentPosition;
								document.getElementById("Player2").controls.play();
								document.getElementById("Player").controls.currentPosition=document.getElementById("Player1").controls.currentPosition;
								document.getElementById("Player").controls.play();
						}else if(Player1.playState==2){
							document.getElementById("Player2").controls.currentPosition=document.getElementById("Player1").controls.currentPosition;
							document.getElementById("Player2").controls.pause();
						    document.getElementById("Player").controls.currentPosition=document.getElementById("Player1").controls.currentPosition;
							document.getElementById("Player").controls.pause();
						}
					
  	</script>
  </head>
  <body>
        <table border="0">
        	<tr>
        		<td><font color="red" size="6" >亲属画面 </font></td>
        		<td><font color="red" size="6" >罪犯画面</font></td>
        	</tr>
  			<tr>                                                                                                                                              
				<td>
			<object id="Player1" width=300 height=300 classid="CLSID:6BF52A52-394A-11D3-B153-00C04F79FAA6" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701standby=Loading Microsoft? Windows Media? Player components... type=application/x-oleobject">
                <param name="AutoStart" value="1" />
				    <!--是否name动播放-->
				    <param name="Balance" value="0" />
				    <!--调整左右声道平衡,同上面旧播放器代码-->
				    <param name="enabled" value="1" />
				    <!--播放器是否可人为控制-->
				    <param name="EnableContextMenu" value="0" />
				    <!--是否启用上下文菜单-->
				    <param name="url" value="${bofangId2}" />
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
				    <param name="ShowAudioControls" value="0" />
				</object>
				</td>
				<td>
				<object id="Player2" width=300 height=300 classid="CLSID:6BF52A52-394A-11D3-B153-00C04F79FAA6" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701standby=Loading Microsoft? Windows Media? Player components... type=application/x-oleobject">
                <param name="AutoStart" value="1" />
				    <!--是否name动播放-->
				    <param name="Balance" value="0" />
				    <!--调整左右声道平衡,同上面旧播放器代码-->
				    <param name="enabled" value="0" />
				    <!--播放器是否可人为控制-->
				    <param name="EnableContextMenu" value="0" />
				    <!--是否启用上下文菜单-->
				    <param name="url" value="${bofangId1}" />
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
				    <param name="ShowAudioControls" value="0" />
				     
				</object>
				<object id="Player" width="0" height="0" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701standby=Loading Microsoft? Windows Media? Player components... type=application/x-oleobject">
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
				    <param name="uiMode" value="invisible" />
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
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" name="play"  onclick="play();" value="播放" /> 
		        <input type="button" name="pause"  onclick="pause();" value="暂停" />
		         <input type="button" name="pause"  onclick="stop();" value="停止" />  
			    <input type="button" name="fastForward"  onclick="fastForward();" ondblclick="dfastForward();" value="快进" /> 
			    <input type="button" name="fastReverse"  onclick="fastReverse();" ondblclick="dfastReverse()" value="快退" /> 
		        <input type="button" name="voldown"  onclick="voldown();" value="音量-" /> 
		        <input type="button" name="volup" " onclick="volup();" value="音量+" /> 
		        <input type="button" name="mute"  onclick="mute();" value="静音" />
			</td>
        </tr>
        </table>
        <input type="hidden" id="webId" name="webId" value="${webId}" /><input type="hidden" id="callTimeLen" name="callTimeLen" value="${jlHjRec.callTimeLen}" /><input type="hidden" id="callId" name="callId" value="${jlHjRec.callId}" />
  </body>
</html>

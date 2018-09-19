<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Flexigrid</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>FlexiGrid/css/flexigrid.css"/>
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.2.6.js"></script>
		<script src="<%=basePath %>js/hjdj.js" type="text/javascript" ></script>
		<script type="text/javascript" >
				window.setInterval(refreshMeetInfo, 10000);
				window.setInterval(playZt, 100); 
				var basePath='<%=basePath%>';
				function refreshMeetInfo(){
					var windowsIndex=document.getElementById("windowsIndex").value;
					if(windowsIndex!=''){
						 $.ajax({
						     type:"POST",
						     url:basePath+"hjdj.do",
						     data:"method=refreshMeetInfo",
						     dataType:"json",
						     success:callback7
						});
					}
				}
				function callback7(data){
					var table=document.getElementById("info");
					table.innerText="";
					table.innerHTML="<font color=\"red\">排队序号："+data[0].acdNo+"</font>&nbsp; &nbsp;<font color=\"red\">叫号序号："+data[0].acdCount+"</font>&nbsp; &nbsp;<input type=\"button\" value=\"下一位\" onclick=\"nextCallNum()\"/> ";
				}
				function nextCallNum(){
					var windowsIndex=document.getElementById("windowsIndex").value;
					var callNoServer=document.getElementById("callNoServer").value;
					var callNoServerUrl=document.getElementById("callNoServerUrl").value;
					callNoServerUrl=encodeURI(encodeURI(callNoServerUrl));
					$.ajax({
					      type:"POST",
					      url:basePath+"hjdj.do",
					      data:"method=nextCallNum&windowsIndex="+windowsIndex+"&callNoServer="+callNoServer+"&callNoServerUrl="+callNoServerUrl,
					      dataType:"json",
					      success:callback6,
					      error:callback1
					});	
				}
				var flag=0;
				var currentPlayNum;
				var total;
				var urlList;
				var repeatCurrentPlayNum;
				var repeatTotal;
				var repeatUrlList="";
				function callback6(data){
					if(data[0]=='0'){
						alert("目前没有排队序号");
						return false;
					}else{
						var table=document.getElementById("info");
						table.innerText="";
						table.innerHTML="<font color=\"red\">排队序号："+data[0].acdNo+"</font>&nbsp; &nbsp;<font color=\"red\">叫号序号："+data[0].acdCount+"</font>&nbsp; &nbsp;<input type=\"button\" value=\"下一位\" onclick=\"nextCallNum()\"/>";
						document.getElementById("callNo").value=data[0].acdCount;
				
						flag=0;
						currentPlayNum=0;
						urlList=data[0].list;
						total=urlList.length;
						repeatCurrentPlayNum=0;
						repeatUrlList=data[0].repeatList;
						repeatTotal=repeatUrlList.length;
						var Player=document.getElementById("Player");
						Player.url=urlList[0];
						Player.controls.play(); 
					}
				}
				function playZt(){
					if(flag==0){
						var Player=document.getElementById("Player");
						if(Player.playState==1){
							currentPlayNum++;
							if(currentPlayNum<total){
								Player.url=urlList[currentPlayNum];
								Player.controls.play(); 
							}
						}
					}else{
						var Player=document.getElementById("Player");
						if(Player.playState==1){
							if(repeatCurrentPlayNum<repeatTotal){
								Player.url=repeatUrlList[repeatCurrentPlayNum];
								Player.controls.play(); 
								repeatCurrentPlayNum++;
						     }   
					   }
					}
				}
				function swfLoadedHandlerExt(){//调用再次播放方法
						flag=1;
						repeatCurrentPlayNum=0;
						repeatCurrentPlayNum++;
						if(repeatUrlList.length>0){
							var Player=document.getElementById("Player");
							Player.url=repeatUrlList[0];
							Player.controls.play();
						}
						 
				}
				function repeat(){
					var showId=document.getElementById("showId").value;
					if(showId!=""){
						$.ajax({
					      type:"POST",
					      url:basePath+"hjdj.do",
					      data:"method=repeat&showId="+showId,
					      dataType:"json",
					      success:callback12,
					      error:callback1
						});	
					}
				}
				function callback12(data){
					
				}
				 function enterSubmit(src,e){
			         if(window.event)
				        keyPressed = window.event.keyCode; // IE
			         else
			            keyPressed = e.which; // Firefox
			         if(keyPressed==13){ 
			        	 chaxun();                     
			             return false;
			        }
				}
		</script>
		  <script language="javascript" for="IDCard2" event="CardIn( State );">
			{ 
			  if(State == 1)
			  {
					document.getElementById("qsName").value=IDCard2.NameA;
					document.getElementById("qssfz").value=IDCard2.CardNo;
					var zpAddress=IDCard2.PhotoName;
					document.getElementById("photoAddress").value=zpAddress;
					document.getElementById("sfzz").src=basePath+"images/zpbj.jpg";
					document.getElementById("sfzz").src=zpAddress;
					chaxun();
			  }
			}
			</script>   
	  <style type="text/css">
			#content4{ margin-left:30px; width:96%;}
			#content4 table{  border-color:#2A65A1; border:0; width:100%;}
			#content4 table th{ border-color:#2A65A1; border:0; text-align:center;font-weight:bold; background:#2A65A1; color:#FFF;}
			#content4 table td{ border-color:#2A65A1; border:0; text-align:center;}
		</style>
	</head>
	<body onload="openPort1();" onunload="colsePort1();">
		<div id="user_info2"><span>登录姓名：${users.userName }    登录时间：${loginTime }</span></div>
		<div id="lm_info2"><span>当前位置：会见登记 </span></div>
		<html:form action="hjdj.do?method=checkFr" method="post">
		<div id="content4">
			<table id="abc">
				<tr>
					<td nowrap="nowrap" width="20%">亲属证件：<html:text property="qssfz" styleId="qssfz" /></td>
					<td nowrap="nowrap" width="20%">亲属姓名：<html:text property="qsName" styleId="qsName"/></td>
					<td rowspan="2" width="10%">
					<object width="0px" height="0px" id="IDCard2" name="IDCard2"  codebase="<%=basePath %>ocx/SynCardOcx.CAB#version=1,0,0,1" classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039">
					</object>		
					<img id="sfzz" src="<%=basePath %>images/zpbj.jpg" width="100px" height="126px"></img><input type="hidden" id="photoAddress" name="photoAddress" value=""/>
					</td>
					<td width="5%" nowrap="nowrap"><input type="button" value="识   别" onclick="addHjdjSb1()"/></td>
					<td width="5%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">会见类型：</font>
						<html:select styleId="hjType" property="hjType" style="width:130px">
							<option value="1">电话会见</option>
							<option value="2">面对面会见</option>
							<option value="3">视频会见</option>
						</html:select>
					</td>
					<td width="1%" nowrap="nowrap">会见时长：<logic:notEmpty name="hjTime"><html:text styleId="hjsc" property="hjsc" value="${hjTime}"></html:text></logic:notEmpty><logic:empty name="hjTime"><html:text styleId="hjsc" property="hjsc" style="width:30px" ></html:text></logic:empty>
					</td >
					<td width="20%" nowrap="nowrap"> 特批亲属： 
						<select id="tpQs"  style="width:50px">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
						</select>人
					</td>
					<td width="15%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;强制审批：
						<input type="checkbox" id="qzSp" />
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;监区：
						<html:select styleId="jq" property="jq" style="width:130px">
							<option value="null">全部</option>
							<logic:notEmpty name="jljqList">
								<logic:iterate id="jl" name="jljqList">
									<option value="${jl.jqNo }">${jl.jqName }</option>
								</logic:iterate>
							</logic:notEmpty>
						</html:select>
					</td>
					<td nowrap="nowrap" width="20%">罪犯姓名：<html:text property="frName" styleId="frName" onkeydown="return enterSubmit(this,event);" /></td>
					<td nowrap="nowrap" width="5%" ><input type="button" onclick="chaxun();" value="查   询" /></td>
					<td nowrap="nowrap" width="20%" >&nbsp;&nbsp;&nbsp;&nbsp;会见说明：<html:text property="hjsm" styleId="hjsm" ></html:text></td>
					<td nowrap="nowrap" width="40%" >
						<c:if test="${hJClient3.paramData2==0}"><input type="button" style="color:red;" value="提   交" onclick="addHjdj();"/></c:if>
					    <c:if test="${hJClient3.paramData2==1}"><input type="button" style="color:red;" value="提   交" onclick="addHjdj1();"/></c:if>
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="returnBack();" value="返   回"/><input type="hidden" id="selectRow" name="selectRow" value="-1"/><input type="hidden" id="selectFrId" name="selectFrId" value="${frNo}"/><input type="hidden" id="callNo" name="callNo" value="0"/><input type="hidden" id="isPrint" name="isPrint" value="${hJClient.paramData3}"/></td>
				</tr>
			</table>
		</div>
 	 <div id="content3">
 	 	<table>
				<tr>
					<th width="6%">现监区</th>
					<th width="7%">罪犯姓名<br>罪犯编号</th>					
					<th width="7%">本月已见次数<br>上次会见时间</th>
					<th width="8%">上次会见<br>家属信息</th>					
					<th width="6%">入监时间</th>
					<th width="5%">分管等级</th>
					<th width="5%">刑期</th>
					<th width="5%">罪名</th>
					<th width="12%">家庭住址</th>
					<th width="8%">上次会见<br>所在监区</th>
					<th width="4%">重点<br>罪犯</th>
					<th width="7%">备注</th>
					<th width="5%">国籍</th>
					<th width="8%">是否禁止<br>禁止时间</th>
				</tr>
				
				<tbody id="frTable">
					<logic:notEmpty name="jlFrList">
						<logic:iterate id="jl" name="jlFrList">
							<tr onclick="checkQs('${jl.frNo}')" ondblclick="goQsMain('${jl.frNo}')">
								<td>${jl.jqName }</td>				
								<td>
									<c:if test="${jl.jbName=='严管级' && jl.monitorFlag=='1'}"><font color="blue">${jl.frName }<br>${jl.frNo }</font></c:if>
									<c:if test="${jl.jbName=='严管级' && jl.monitorFlag!='1'}"><font color="red">${jl.frName }<br>${jl.frNo }</font></c:if>
									<c:if test="${jl.monitorFlag=='1' && jl.jbName!='严管级'}"><font color="green">${jl.frName }<br>${jl.frNo }</font></c:if>									
									<c:if test="${jl.jbName!='严管级' && jl.monitorFlag!='1'}">${jl.frName }<br>${jl.frNo }</c:if>
								</td>
								
								<c:if test="${jl.hjUse<1}">
								<td>${jl.hjUse}<br>${jl.hjLastTime }<input type="hidden" name="monitorFlag" value="${jl.monitorFlag }" /></td>
								
								</c:if>
								<c:if test="${jl.hjUse>=1}">
								<td><font color="red">${jl.hjUse}</font><br><font color="red">${jl.hjLastTime }<input type="hidden" name="monitorFlag" value="${jl.monitorFlag }" /></font></td>								
								</c:if>
								<td>${jl.qsName}</td> 
								 <td>${jl.infoRjsj}</td> 
								<td>${jl.jbName }</td>
								<td>${jl.infoXq }</td>
								<td>${jl.infoZm }</td>
								<td>${jl.infoHome}</td>
								<td><font color="red">${jl.formerJQName}</font></td>
								<td>
									<c:if test="${jl.stateZdzf=='0'}">否</c:if>
									<c:if test="${jl.stateZdzf=='1'}"><font color="red">是</font></c:if>									
								</td>
								<td><font color="red">${jl.zdzfType}</font></td>
								<td><font color="red">${jl.frGj}</font></td>
								<td>
									<c:if test="${jl.hjJb=='-1'}"><font color="red">是<br>${jl.hjStopTime}</font></c:if>
									<c:if test="${jl.hjJb!='-1'}">否</c:if>									
								</td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					
					<logic:notEmpty name="unjlFrList">
						<logic:iterate id="jl" name="unjlFrList">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						</logic:iterate>
					</logic:notEmpty>
			</tbody>
		</table>
 	 </div>
 	 
 	 <div id="content6">
 	 	<table>
				<tr>
					<th width="5%">是否选中</th>
					<th width="6%">亲属姓名</th>
					<th width="3%">关系</th>
					<th width="5%">证件类别</th>
					<th width="9%">证件号码</th>
					<th width="6%">证件物理号</th>
					<th width="6%">IC卡编号</th>
					<th width="4%">性别</th>
					<th width="15%">地址</th>
					<th width="6%">电话</th>
					<th width="15%">备注</th>
					<th width="5%">审批状态</th>
					<th width="5%">是否禁止<br>禁止时间</th>
				</tr>
				<tbody id="qsTable">
					<logic:empty name="listJlQs">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					</logic:empty>
					<logic:notEmpty name="listJlQs">
						<logic:iterate id="jl" name="listJlQs">
							<tr onclick="checkHj();" ondblclick="updateFrQs1();">
								<td><input type="checkbox" id="isNo" name="isNo" value="${jl.webId }"/></td>
								<td>${jl.qsName}</td>
								<td>${jl.gx}</td>
								<td>
										<c:if test="${jl.qsZjlb==1}">身份证</c:if>
					     				<c:if test="${jl.qsZjlb==2}">警官证</c:if>
					     				<c:if test="${jl.qsZjlb==3}">工作证</c:if>
					     				<c:if test="${jl.qsZjlb==4}">其他</c:if>
								</td>
								<td>${jl.qsSfz}</td>
								<td>${jl.qsSfzWlh}</td>
								<td>${jl.qsCard}</td>
								<td>${jl.xb}</td>
								<td>${jl.dz}</td>
								<td>${jl.tele}</td>
								<td>${jl.bz}</td>
								<td>
										<c:if test="${jl.spState==1}">已通过</c:if>
					     				<c:if test="${jl.spState==0}"><font color="red">未通过</font></c:if>
								</td>
								<td>
									<c:if test="${jl.hjStopTime!=null}"><font color="red">是<br>${fn:substring(jl.hjStopTime,0,10)}</font></c:if>
									<c:if test="${jl.hjStopTime==null}">否</c:if>
								</td>
								<!-- <td>
										<c:if test="${jl.faceState==1}">已注册</c:if>
					     				<c:if test="${jl.faceState==0}"><font color="red">未注册</font></c:if>
					     				<c:if test="${jl.faceState==2}"><font color="red">中途取消注册</font></c:if>
					     				<c:if test="${jl.faceState==3}">已注册</c:if>
					     				<c:if test="${jl.faceState==4}"><font color="red">注册超时</font></c:if>
								</td> -->
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<logic:notEmpty name="unjlListJlQs">
						<logic:iterate id="un" name="unjlListJlQs">
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					
			</tbody>
		</table>
 	 </div>
 	 <div id="load" style="display:none;position:absolute;position:absolute;z-index:2;margin-left:45%"></div>
 	  <div id="info" style="position:absolute;margin-top:20px;margin-left:70px;height:25px; line-height:25px;">
 	  		<logic:notEmpty name="hjdjAcdInfo">
 	  			<c:if test="${hJClient.paramData4==1}">
 	  				<font color="red">排队序号：${hjdjAcdInfo.acdgetNo}</font>&nbsp; &nbsp;<font color="red">叫号序号：${hjdjAcdInfo.acdsetNo}</font>&nbsp; &nbsp;<input type="button" value="下一位" onclick="nextCallNum()"/><!-- <input type="button" value="重  复" onclick="swfLoadedHandlerExt()"/>-->
 	  			</c:if>
 	  		</logic:notEmpty>
 	  </div>
 	  <!--  
 	  <div id="info1" style="position:absolute;margin-top:20px;margin-left:370px;height:35px; line-height:25px;">
 	  		<logic:notEmpty name="monitorFlag">
 	  			<c:if test="${monitorFlag=='' || monitorFlag=='0'}">
 	  				<input type="checkbox" name="isMonitor" value="${monitorFlag}"/>&nbsp;&nbsp;临时重点监控
 	  			</c:if>
 	  			<c:if test="${monitorFlag!='' && monitorFlag!='0'}">
 	  				<input type="checkbox" name="isMonitor" value="${monitorFlag}" checked="checked" disabled="disabled"/>&nbsp;&nbsp;重点监控"
 	  			</c:if>
 	  		</logic:notEmpty>
 	  </div>
 	  -->
 	  <div>
 	  		<input type="hidden" id="windowsIndex" value="${hjdjAcdWindowsInfo.acdindex}"/>
 	  		<input type="hidden" id="callNoServer" value="${hjdjAcdWindowsInfo.serverName}"/>
 	  		<input type="hidden" id="callNoServerUrl" value="${hjdjAcdInfo.acdurl}"/>
 	  		<input type="hidden" id="showId" value=""/>
	 	  	   <object id="Player" classid="CLSID:6BF52A52-394A-11D3-B153-00C04F79FAA6" style="display:none">
                <param name="AutoStart" value="1" />
				    <!--是否name动播放-->
				    <param name="Balance" value="0" />
				    <!--调整左右声道平衡,同上面旧播放器代码-->
				    <param name="enabled" value="1" />
				    <!--播放器是否可人为控制-->
				    <param name="EnableContextMenu" value="1" />
				    <!--是否启用上下文菜单-->
				    <param name="url" value="" />
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
				
		</div>
 	 </html:form>
 	 
    </body>
</html>

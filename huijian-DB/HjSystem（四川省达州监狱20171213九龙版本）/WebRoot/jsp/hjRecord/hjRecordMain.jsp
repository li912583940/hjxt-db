<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String http=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>main</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/hjRecord.js" type="text/javascript" ></script>
		<!-- <object id="downLoad" codebase="<%=basePath%>/ocx/DownFile.ocx#version=1,0,0,1" classid="clsid:F23BC229-FB42-43FF-B819-62ADC5D83744" width="0" height="0"></object> -->
		<object id="WebBrowser" width="0" height="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"> </object>
		<script src="<%=basePath %>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
 	    <script language="javascript">   
			document.onkeydown   =   function()   
			{       
			if(event.keyCode   ==   8)   
			{   
			if(event.srcElement.tagName.toLowerCase()   !=   "input"   
			&&   event.srcElement.tagName.toLowerCase()   !=   "textarea")   
			event.returnValue   =   false;   
			}   
			}
			var basePath='<%=basePath%>';
	 </script>
	 <style type="text/css">
	 	.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		a{text-decoration: NONE}
	</style>   
			 
  </head>
  <body onkeydown="return enterSubmit(this,event);" ><!-- onload="selectServer();" -->
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：会见通话记录 </span></div>
       <html:form action="hjRecord.do?method=drThRecord" method="post" enctype="multipart/form-data" onsubmit="return myFormCheck(this);">
        	<div id="content2">
		     	<table  cellspacing="0" cellpadding="0">
		     	  <tr>
		     	     <td colspan="5" height="25px" nowrap="nowrap" width="40%">通话开始时间：<html:text  property="callTimeStart"  styleId="callTimeStart" value="${todayBegin}" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
		     	     <td colspan="5" height="25px" nowrap="nowrap" width="40%">通话结束时间：<html:text  property="callTimeEnd"   styleId="callTimeEnd" value="${todayEnd}" onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text></td>
		     	     <td colspan="10" height="25px" nowrap="nowrap" width="20%">罪犯编号：<html:text property="frNo" styleId="frNo"></html:text>&nbsp;&nbsp;罪犯姓名：<html:text property="frName" styleId="frName"></html:text></td>
		     	  </tr>
		     	  <tr>

		     	       <td colspan="5" height="25px" nowrap="nowrap" width="32%">服务器名称：<html:select property="jy" styleId="jy" style="width:130px" onchange="checkJq();">
		     	                                  <html:option value="null">全部</html:option>
		     	                     	          	   <logic:notEmpty name="sysQqServerList">
		     												<logic:iterate id="sqs" name="sysQqServerList">
		     													<html:option value="${sqs.serverName}">${sqs.serverName}</html:option>
		     												</logic:iterate>
		     											</logic:notEmpty>
		     	     						  </html:select>
		     	      </td>
		     	       <td colspan="5" height="25px" nowrap="nowrap" width="32%">罪犯监区：
		     	       		 <c:if test="${jqIsNodisable==0}"> 
		     				   		<html:select property="jqNo" styleId="jqNo" style="width:130px" disabled="true">
		   									 <html:option value="null">全部</html:option>
		     				 	    </html:select>
		     				  </c:if>
		     				  <c:if test="${jqIsNodisable==1}"> 
		     				   		<html:select property="jqNo" styleId="jqNo" style="width:130px">
		   									 <html:option value="null">全部</html:option>
		   									 <logic:notEmpty name="jlJqList">
		   									 	<logic:iterate id="jl" name="jlJqList">
		   									 		<html:option value="${jl.jqNo}">${jl.jqName}</html:option>
		   									 	</logic:iterate>
		   									 </logic:notEmpty>
		     				 	    </html:select>
		     				  </c:if>
		     	      </td>
		     	      <td colspan="10" nowrap="nowrap">
		     	     		评级状态：<html:select property="recRatingState" styleId="recRatingState" style="width:130px">
		     						<html:option value="null">全部</html:option>
		     						<html:option value="0">未评</html:option>
		     						<html:option value="1">异常</html:option>
		     						<html:option value="2">正常</html:option>
		     				    </html:select>
		     				 &nbsp;复听状态：<html:select property="recAssessmentState" styleId="recAssessmentState" style="width:130px">
		     						<html:option value="null">全部</html:option>
		     						<html:option value="0">未听</html:option>
		     						<html:option value="1">已听</html:option>
		     				    </html:select>
		     			</td>
		     	 </tr>
		     	   <tr>
		     	   	 <td colspan="5" height="25px" nowrap="nowrap">座位：<html:text property="zw" styleId="zw"></html:text>

		     	     </td>
		     	     <td colspan="5" height="25px" nowrap="nowrap">亲属姓名：<html:text property="qsName" styleId="qsName"></html:text></td>
		     	     <td colspan="9" height="25px" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会见类型：
		     	     		<html:select property="type" styleId="type" style="width:130px">
								 <html:option value="null">全部</html:option>
								 <html:option value="1">电话会见</html:option>
								<html:option value="2">面对面会见</html:option>
								<html:option value="3">视频会见</html:option>
							
		     				</html:select>
		     				复听超时：
		     	     		<html:select property="recordOverTime" styleId="recordOverTime" style="width:130px">
								 <html:option value="null">全部</html:option>
								 <html:option value="0">未超时</html:option>
								<html:option value="1">已超时</html:option>
							
		     				</html:select>
		     				&nbsp;<a href="javascript:void(0)" onclick="chaxun();return false;"><img src="images/chaxun.gif"></img></a></td>
		     	     
		     	   </tr>
		    	 	<tr>
						<th nowrap="nowrap" height="25px" width="10%">通话开始时间<br>通话结束时间</th>
						<th nowrap="nowrap" height="25px" width="4%">通话<br>时长</th>
						<th nowrap="nowrap" height="25px" width="3%">座位<br>名称</th>
						<th nowrap="nowrap" height="25px" width="4%">会见<br>类型</th>
						<th nowrap="nowrap" height="25px" width="5%">监区<br>名称</th>
						<th nowrap="nowrap" height="25px" width="7%">罪犯<br>编号</th>
						<th nowrap="nowrap" height="25px" width="6%">罪犯<br>姓名</th>
						<th nowrap="nowrap" height="25px" width="1%">亲属<br>个数</th>
						<th nowrap="nowrap" height="25px" width="13%">亲属<br>信息</th>
						<th nowrap="nowrap" height="25px" width="6%">警察<br>信息</th>						
						<th nowrap="nowrap" height="25px" width="7%">音视频<br>操作</th>
						<th nowrap="nowrap" height="25px" width="8%">摘要<br>操作</th>
						<th nowrap="nowrap" height="25px" width="6%">录音<br>操作</th>
						<th nowrap="nowrap" height="25px" width="3%">录音<br>评级</th>
						<th nowrap="nowrap" height="25px" width="3%">评级<br>详情</th>
						<th nowrap="nowrap" height="25px" width="3%">复听<br>状态</th>
						<th nowrap="nowrap" height="25px" width="3%">复听<br>详情</th>
						<th nowrap="nowrap" height="25px" width="3%">复听<br>超时</th>
						<th nowrap="nowrap" height="25px" width="3%">其它<br>详情</th>
					</tr>
					<logic:notEmpty name="page" property="list">
						<logic:iterate id="rs" name="page" property="list">
					   	<tr  onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
					   	  <td nowrap="nowrap" height="25px">${rs.callTimeStart}<br>${rs.callTimeEnd}</td>
					   	  <td nowrap="nowrap" height="25px">${rs. callTimeLen}秒</td>
					   	  <td nowrap="nowrap" height="25px">${rs. zw}</td>
					   	  <logic:equal value="1" name="rs" property="type" ><td nowrap="nowrap" height="25px"><B>普见</B> </td></logic:equal>
					   	  <logic:equal value="2" name="rs" property="type"><td nowrap="nowrap" height="25px"><B><font color="red">宽见</font></B></td></logic:equal>
					   	  <td nowrap="nowrap" height="25px">${rs.jqName}</td>
					   	  <td nowrap="nowrap" height="25px">${rs.frNo}</td>
					   	  <td nowrap="nowrap" height="25px"><B>${rs.frName}</B></td>
					   	  <td nowrap="nowrap" height="25px">${rs.qsIndex}</td>
					   	  <td height="25px">${rs.qsInfo}</td>
					   	    <td nowrap="nowrap" height="25px">${rs.yjNo}</br>${rs.yjName}</td>
					   	  <logic:notEmpty name="rs" property="callRecFile" >
					   	 		 <td nowrap="nowrap" height="25px"><a href="javascript:void(0)" onclick="playRecor();return false;">
					   	 		 
					   	 		 
					   	 		 <logic:notEmpty name="rs" property="downCallVideoFile1">播放录音录像</logic:notEmpty><logic:empty name="rs" property="downCallVideoFile1"></logic:empty> </a><input type="hidden"  name="wjdz" value="${rs.callRecFile}"/><input type="hidden"  name="wjdz1" value="${rs.callRecFile}"/><input type="hidden" name="callId" value="${rs.call_ID }"/><input type="hidden"  name="zhuShiWebID" value="${rs.web_Id }"/><input type="hidden" name="userNo" value="${rs.userNo}"/><input type="hidden" name="callVideoFile1" value="${rs.callVideoFile1}"/><input type="hidden"  name="callVideoFile2" value="${rs.callVideoFile2}"/><input type="hidden" name="downCallVideoFile1" value="${rs.callVideoFile1}"/><input type="hidden"  name="downCallVideoFile2" value="${rs.callVideoFile2}"/><input type="hidden"  name="webId" value="${rs.webId}"/> 
					   	 		<c:if test="${users.groupNo=='Admin'}"><a href="javascript:void(0)" onclick="downRecord();return false;"><logic:notEmpty name="rs" property="downCallVideoFile1">下载录音录像</logic:notEmpty><logic:empty name="rs" property="downCallVideoFile1"></logic:empty></a></c:if>
					   	 		<c:if test="${users.groupNo!='Admin'}"><a href="javascript:void(0)" onclick="downRecord();return false;"><logic:notEmpty name="rs" property="downCallVideoFile1">下载录音录像</logic:notEmpty><logic:empty name="rs" property="downCallVideoFile1"></logic:empty></a></c:if></td>
					   	 		 
					   	 		 
					   	 		 <td><a href="javascript:void(0)" onclick="zhushi();return false;">[录入回放摘要]</a><br>
					   	 		 <a href="javascript:void(0)" onclick="zhushiMessage();return false;">[查看所有摘要]</a></td>
					   	 		 <td>
					   	 		 <a href="javascript:void(0)" onclick="playRecor1();return false;">[播放录音]</a><br>
					   	 		 <a href="javascript:void(0)" onclick="downRecord1();return false;">[下载录音]</a>
					   	 		 </td>
					   	 </logic:notEmpty>
					   	 <logic:empty name="rs" property="callRecFile">
					   	 		<td nowrap="nowrap" height="25px"><a href="javascript:void(0)" onclick="delSgRecord();return false;">删除</a><input type="hidden" name="wjdz" value="<%=http %>${rs.callRecFile}"/><input type="hidden"  name="wjdz1" value="${rs.downCallRecFile}"/><input type="hidden"  name="callId" value="${rs.call_ID }"/><input type="hidden"  name="zhuShiWebID" value="${rs.web_Id }"/><input type="hidden" name="userNo" value="${rs.userNo}"/><input type="hidden" name="callVideoFile1" value="${rs.callVideoFile1}"/><input type="hidden"  name="callVideoFile2" value="${rs.callVideoFile2}"/>  
					   	 		 <a href="javascript:void(0)" onclick="updateSgRecord();return false;">修改</a>
					   	 		 <a href="javascript:void(0)" onclick="zhushi();return false;">注释</a>
					   	 		 <a href="javascript:void(0)" onclick="zhushiMessage();return false;">注释管理</a>
					   	 		 </td>
					   	 </logic:empty>
					   	 <td nowrap="nowrap" height="25px">
					   	 		<c:if test="${rs.recRatingState==0}"><B><font color="#ff9900">未评</font></B><br><a href="javascript:void(0)" style="text-decoration:underline;" onclick="updateRatingState();return false;">修改</a></c:if>
					     		<c:if test="${rs.recRatingState==1}"><B><font color="red">异常</font></B><br><a href="javascript:void(0)" style="text-decoration:underline;" onclick="updateRatingState();return false;">修改</a></c:if>
					   	 		<c:if test="${rs.recRatingState==2}"><B><font color="green">正常</font></B><br><a href="javascript:void(0)" style="text-decoration:underline;" onclick="updateRatingState();return false;">修改</a></c:if>
					   	 </td>
					   	 <td nowrap="nowrap" height="25px">
					   	 		<a href="javascript:void(0)" style="text-decoration:underline;" onclick="checkAllRatingInfo();return false;">查看</a>
					   	 </td>
					   	 <td nowrap="nowrap" height="25px">
					   	 		<c:if test="${rs.recAssessmentState==0}"><B><font color="red">未听</font></B></c:if>
					     		<c:if test="${rs.recAssessmentState==1}"><B><font color="green">已听</font></B></c:if>
					   	 </td>
					   	 <td nowrap="nowrap" height="25px">
					   	 		<a href="javascript:void(0)" style="text-decoration:underline;" onclick="checkAllAssessmentInfo();return false;">查看</a>
					   	 </td>
					   	 <td nowrap="nowrap" height="25px">
					   	 		<c:if test="${rs.recordOverTime==0}"><B><font color="green">未超时</font></B></c:if>
					     		<c:if test="${rs.recordOverTime==1}"><B><font color="red">已超时</font></B></c:if>
					   	 </td>
					   	 <td nowrap="nowrap" height="25px">
					   	 		<a href="javascript:void(0)" style="text-decoration:underline;" onclick="checkRecordInfo();return false;">查看</a>
					   	 </td>
					   </tr>
					   </logic:iterate>
					   <tr>
					   		<td colspan="21" align="left" height="75px"><page:page pageNo="${page.pageNo}" recordCount="${page.recordCount}" url="hjRecord.do?method=search1" pageSize="${page.pageSize}"/><input type="hidden" id="userGroup" name="userGroup" value="${users.groupNo }" /><input type="hidden" id="isSuper" name="isSuper" value="${users.isSuper }" /><input type="hidden" id="boFangFlag" value="0" /><input type="hidden" id="isDc" name="isDc" value="1"/></td>
					   </tr>
				   </logic:notEmpty>
				   <logic:empty name="page" property="list">
				         <tr>
				         	<td colspan="21"><font color="red">没有相关记录</font><input type="hidden" id="isDc" name="isDc" value="0"/></td>
				         </tr>
				   </logic:empty>
	  			 </table>
	  	 	</div>
	  	 	<div style="position:absolute;margin-top:18px;margin-left:30px;height:25px; line-height:25px;">
				   <input type="button" value="导出会见记录至Excel表格" onclick="dcHjRecord();"/>
				   <logic:notEmpty name="BatchDownLoadSwitch">
				   		  <input type="button" onclick="plLoadDown();" value="批量下载" />
				   </logic:notEmpty>
	        </div>
	  	 </html:form>	
	  	 
  </body>
</html>

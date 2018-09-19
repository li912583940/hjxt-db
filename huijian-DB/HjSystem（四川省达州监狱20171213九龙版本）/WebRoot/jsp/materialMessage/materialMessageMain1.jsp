<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
         <script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/materialMessage.js" type="text/javascript" ></script>
 	    <script   language="javascript">   
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
  <body onkeydown="return enterSubmit1(this,event);" ><!--onload="selectServer();"  -->
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：罪犯管理 </span></div>
       <html:form action="materialMessage.do?method=drAllFrXx" method="post" enctype="multipart/form-data" onsubmit="return myFormCheck(this);">
        	<div id="content2">
		     	<table  border="0" cellspacing="0" cellpadding="0">
		     		<tr>		
		     			<td colspan="4" style="width:25%;">
		     				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务器名称：<html:select property="jy" styleId="jy" style="width:130px" onchange="checkJq();">
		     							<html:option value="null">全部</html:option>
		     							<logic:notEmpty name="sysQqServerList">
		     								<logic:iterate id="sqs" name="sysQqServerList">
		     									<html:option value="${sqs.serverName}">${sqs.serverName}</html:option>
		     								</logic:iterate>
		     							</logic:notEmpty>
		     							
		     				    </html:select>
		     			</td>
		     			<td colspan="4" style="width:25%;">
		     				   监区名称：
		     				   <c:if test="${jqIsNodisable==0}"> 
		     				   		<html:select property="jq" styleId="jq" style="width:130px" disabled="true">
		   									 <html:option value="null">全部</html:option>
		     				 	    </html:select>
		     				  </c:if>
		     				  <c:if test="${jqIsNodisable==1}"> 
		     				   		<html:select property="jq" styleId="jq" style="width:130px">
		   									 <html:option value="null">全部</html:option>
		   									 <logic:notEmpty name="jlJqList">
		   									 	<logic:iterate id="jl" name="jlJqList">
		   									 		<html:option value="${jl.jqNo}">${jl.jqName}</html:option>
		   									 	</logic:iterate>
		   									 </logic:notEmpty>
		     				 	    </html:select>
		     				  </c:if>
		     				  
		     			</td>
		     			<td colspan="3" style="width:25%;">
		     				级别：<html:select property="jbNo" styleId="jbNo" style="width:130px">
		     						<html:option value="null">全部</html:option>
		     						<logic:iterate id="jljb" name="jlJbList">
		     							<html:option value="${jljb.jbNo}">${jljb.jbName}</html:option>
		     						</logic:iterate>
		     				    </html:select>
		     			</td>
		     		</tr>
		     		<tr>
		     			<td nowrap="nowrap" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;罪犯编号：<html:text property="frNo" styleId="frNo" size="20"/></td>
		     			<td colspan="4" nowrap="nowrap">罪犯姓名：<html:text property="frName" styleId="frName" size="20"/></td>
		     			<td colspan="3">
		     			IC卡号：<html:text property="frCard" styleId="frCard" size="20"/>

		     			&nbsp;&nbsp;</td>
		     		</tr>
		     		<tr>
		     			<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		     			服刑状态：
						<html:select property="state" styleId="state" style="width:130px">
							<html:option value="null">全部</html:option>
							<html:option value="0">服刑中</html:option>
							<html:option value="1">已出狱</html:option>
						</html:select>		     				
		     			</td>
		     			<td colspan="4">是否黑名单：
						<html:select property="isHjStop" styleId="isHjStop" style="width:130px">
							<html:option value="null">全部</html:option>
							<html:option value="0">是</html:option>
							<html:option value="1">否</html:option>
						</html:select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		     			<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		     			重点罪犯：
						<html:select property="stateZdzf" styleId="stateZdzf" style="width:130px">
							<html:option value="null">全部</html:option>
							<html:option value="0">否</html:option>
							<html:option value="1">是</html:option>
						</html:select>
		     			<a href="javascript:void(0)" onclick="checkFr1();return false;"><img src="images/chaxun.gif"></img></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		     		</tr>
		     		<tr>
			     		<th width="6%" height="25px" nowrap="nowrap">罪犯编号<br>罪犯姓名</th>
				     	<th width="6%" height="25px" nowrap="nowrap">黑名单</th>
			     		<!-- <th width="10%" height="25px" nowrap="nowrap">服务器名称</th> -->
			     		<th width="6%" height="25px" nowrap="nowrap">所属<br>监区</th>
			     		<th width="6%" height="25px" nowrap="nowrap">分管<br>等级</th>
			     		<th width="4%" height="25px" nowrap="nowrap">本月已经<br>会见次数</th>
			     		<th width="8%" height="25px" nowrap="nowrap">上次会见<br>时间</th>
			     		<th width="4%" height="25px" nowrap="nowrap">重点<br>罪犯</th>
			     		<th width="4%" height="25px" nowrap="nowrap">IC卡号</th>
			     		<!-- <th width="4%" height="25px" nowrap="nowrap">服刑<br>状态</th> -->
			     		<th width="14%" height="25px" nowrap="nowrap">备注</th>
			     		<th width="4%" height="25px" nowrap="nowrap">操作</th>
			     		<th width="4%" height="25px" nowrap="nowrap">设置</th>
		     		</tr>
		     		  <logic:notEmpty name="page" property="list">
		     				<logic:iterate id="fr" name="page" property="list">
		     				<tr onmouseover= "this.style.background ='#FFC1C1';" onmouseout="this.style.background ='#FFFFFF';">
		     					<td height="25px" nowrap="nowrap">${fr.frNo}<br>${fr.frName}</td>
		     					<td height="25px" nowrap="nowrap">
									<c:if test="${fr.hjJb=='-1'}"><font color="red">是</font></c:if>
			      	 				<c:if test="${fr.hjJb!='-1'}">否</c:if>
			      	 			</td>
		     					<!-- <td height="25px" nowrap="nowrap">${fr.frGj}</td> -->
		     					<!-- <td height="25px" nowrap="nowrap">${fr.jy }</td> -->
		     					<td height="25px" nowrap="nowrap">${fr.jqName}</td>
		     					<td height="25px" nowrap="nowrap">${fr.jbName}</td>
		     					<td height="25px" nowrap="nowrap">${fr.hjUse}</td>
		     					<td height="25px" nowrap="nowrap">${fr.hjLastTime}</td>
		     					<td height="25px" nowrap="nowrap">
		     						<c:if test="${fr.stateZdzf==1}"><font color="red">是</font></c:if>
			      	 				<c:if test="${fr.stateZdzf!=1}">否</c:if>
		     					</td>
		     					<td height="25px" nowrap="nowrap">${fr.frCard}</td>
		     					<!-- <td height="25px" nowrap="nowrap">
		     						<c:if test="${fr.state==1}"><font color="red">已出狱</font></c:if>
			      	 				<c:if test="${fr.state!=1}">服刑中</c:if>
		     					</td> -->
		     					<td height="25px">${fr.zdzfType}</td>
		     					<td height="25px" nowrap="nowrap"><a href="javascript:void(0)" onclick="updateFr1();return false;">修改</a>&nbsp;&nbsp;<a href="" onclick="return false;">删除</a><input type="hidden" id="webId" name="webId" value="${fr.webId}" /><input type="hidden" id="frNo2" name="frNo2" value="${fr.frNo}" /></td>
		     					<td height="25px" nowrap="nowrap"></td>
		     				</tr>
		     				</logic:iterate>
		     				<tr>
		     					<td colspan="11" height="25px"><page:page pageNo="${page.pageNo}" pageSize="${page.pageSize}" recordCount="${page.recordCount}" url="materialMessage.do?method=search3"></page:page><input type="hidden" id="isDc" name="isDc" value="1"/></td>
		     				</tr>
		     		</logic:notEmpty>
		     		<logic:empty name="page" property="list">
		     				<tr>
		     					<td colspan="11" height="25px"><font color="red">没有相关信息</font><input type="hidden" id="isDc" name="isDc" value="0"/></td>
		     				</tr>
		     		</logic:empty>
		     	</table>
		     </div>
		     <!--  <div style="position:absolute;margin-top:20px;margin-left:70px;height:25px; line-height:25px;font-size:12px;">
	        	<c:if test="${sysParam.paramData5==0}">未开启</c:if><c:if test="${sysParam.paramData5==1}">已开启</c:if>罪犯编号、亲属身份证自动生成 <a href="materialMessage.do?method=search&paramData5=${sysParam.paramData5}">切换</a>
	        </div>
	       <a href="javascript:void(0)" onclick="dcAllFr();return false;" style="position:absolute;margin-top:20px;margin-left:40px;height:25px; line-height:25px;"><img src="images/daochu.gif" /></a> -->
	        <!-- <div style="position:absolute;margin-top:18px;margin-left:750px;height:25px; line-height:25px;">
				   从Excel导入数据：<input type="file" name="myFile" id="myFile" size="30" class="ifile"  onchange="upfile.value=this.value;"/>
				   <input type="text" id="upfile" name="upfile" size="30" readonly="readonly" />
				  <input type="button" value="浏览文件" onclick="this.form.myFile.click();" />&nbsp;&nbsp;&nbsp;&nbsp;
				   <input type="button" onclick="myFormCheck();" value="导入数据" /> -->
				   <!-- <c:if test="${sysParam1.paramData1==1}">罪犯编号：<input type="text" id="frNo5"/><input type="button" value="更新数据" onclick="remoteFr()"/></c:if> -->
	           </div>
	           <!--  <div style="position:absolute;margin-top:18px;margin-left:100px;height:25px; line-height:25px;">
				   <c:if test="${sysParam1.paramData1==1}">罪犯编号：<input type="text" id="frNo5" value="请输入编号" onfocus="javascript:if(this.value=='请输入编号')this.value='';"/><input type="button" value="更新数据" onclick="remoteFr()"/></c:if> 
	           </div> -->
		 </html:form>
		 <div id="load" style="display:none;position:absolute;position:absolute;z-index:2;margin-left:45%"></div>
  </body>
</html>

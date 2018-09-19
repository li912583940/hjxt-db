<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
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
		<script src="<%=basePath %>js/hjdj.js" type="text/javascript" ></script>
		<script language="javascript" type="text/javascript" src="<%=basePath %>js/textselect.js"></script>
		<script   language="javascript"> 
			 document.onkeydown   =   function()   
				{
				if(event.keyCode   ==   8)   
				{   
				if(event.srcElement.tagName.toLowerCase()!="input"  &&  event.srcElement.tagName.toLowerCase()   !=   "textarea")   
				event.returnValue   =   false;   
				}   
				}
				var basePath='<%=basePath%>';
		</script>   
  </head>
   <body>
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：会见登记 </span></div>
	   <form action="hjdj.do?method=search" method="post">
      	 <table style="border-style:solid; border-color:#76a5af; border-width:1px 0 0 1px; width:80%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td colspan="9">监区：<input type="text" id="jqName" name="jqName" value="${jlHjDj.jqName }" disabled="disabled" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;罪犯编号：<input type="text" id="frNo" name="frNo" value="${jlHjDj.frNo }" disabled="disabled" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;罪犯姓名：<input type="text" id="frName" name="frName" value="${jlHjDj.frName}" disabled="disabled" /></td>
      	 		
      	 	</tr>
      	 	<tr>
      	 		<td colspan="4">会见时长：<input type="text" id="hjTime" name="hjTime" value="${jlHjDj.hjTime }" disabled="disabled"/></td>
      	 		<td colspan="5">会见类型：<select id="hjType" style="width:130px">
      	 					<c:if test="${jlHjDj.hjType==1 }">
								<option value="1" selected="selected">电话会见</option>
								<option value="2">面对面会见</option>
								<option value="3">视频会见</option>
							</c:if>
							<c:if test="${jlHjDj.hjType==2 }">
								<option value="1">电话会见</option>
								<option value="2" selected="selected">面对面会见</option>
								<option value="3">视频会见</option>
							</c:if>
							<c:if test="${jlHjDj.hjType==3 }">
								<option value="1">电话会见</option>
								<option value="2">面对面会见</option>
								<option value="3" selected="selected">视频会见</option>
							</c:if>
							
						</select>
				</td>
      	 		
      	 	</tr>
      	 	<tr style="height:40px">
      	 	<td colspan="9">会见说明：<input type="text" id="hjInfo" name="hjInfo" style="height:20px;width:700px" value="${jlHjDj.hjInfo}"/></td>
      	 	</tr>	
      	 	<tr style="background:#76a5af;color:#FFFFFF;font-weight:900;">
      	 		
      	 		<td width="6%">亲属姓名</td>
      	 		<td nowrap="nowrap" width="3%">证件类别</td>
      	 		<td width="10%">证件号码</td>
      	 		<td width="6%">证件物理号</td>
      	 		<td width="6%">IC卡号</td>
      	 		<td width="2%">关系</td>
      	 		<td width="10%">备注</td>
      	 		<td width="6%">审批状态</td>
      	 		<td width="4%">是否选中</td>
      	 	</tr>
      	 	<logic:notEmpty name="listJlQs">
      	 		<logic:iterate id="jl" name="listJlQs">
      	 			<tr onclick="selectQs();">
								
								<td>${jl.qsName }</td>
								<td><c:if test="${jl.qsZjlb==1}">身份证</c:if>
					     			<c:if test="${jl.qsZjlb==2}">警官证</c:if>
					     			<c:if test="${jl.qsZjlb==3}">工作证</c:if>
					     			<c:if test="${jl.qsZjlb==4}">其他</c:if>
								</td>
								<td>${jl.qsSfz }</td>
								<td>${jl.qsSfzWlh }</td>
								<td>${jl.qsCard }</td>
								<td>${jl.gx }</td>
								<td>${jl.bz }</td>
								<td>
										<c:if test="${jl.spState==1}">已通过</c:if>
					     				<c:if test="${jl.spState==0}"><font color="red">未通过</font></c:if>
								</td>
								<td><input type="checkbox"  name="isNo" value="${jl.webId }" disabled="disabled"/></td>
					</tr>
      	 		</logic:iterate>
      	 	</logic:notEmpty>
      	 	<tr>
      	 		<td colspan="4">
      	 		<c:if test="${hJClient3.paramData2==0}"><a href="javascript:void(0);" onclick="saveupdateHjDj();return false;"><img src="images/baocun.gif"></img></a></c:if>
      	 		<c:if test="${hJClient3.paramData2==1}"><a href="javascript:void(0);" onclick="saveupdateHjDj1();return false;"><img src="images/baocun.gif"></img></a></c:if>
      	 		<input type="hidden" id="hjId" value="${jlHjDj.hjid }" /></td>
      	 		<td colspan="5"><a href="hjdj.do?method=search"><img src="images/fanhui.gif"></img></a></td>
      	 	</tr>
      	 </table>
      	 </form>
      	 <div id="load" style="display:none;position:absolute;position:absolute;z-index:2;margin-left:45%"></div>
  </body>
</html>

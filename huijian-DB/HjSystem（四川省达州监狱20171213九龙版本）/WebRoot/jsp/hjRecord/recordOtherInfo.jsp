<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<script src="<%=basePath %>js/hjRecord.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/DateTimeCalendar.js" type="text/javascript"></script>
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
	 </script>
	 <style type="text/css">
			   a{text-decoration: NONE}
			</style>   
  </head>
  <body>
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：会见通话记录 </span></div>
       <html:form action="hjRecord.do?method=search" method="post">
        	<div id="content2">
		     	<table>
		     	<tr>
		     	    <th width="10%" height="20px">会见登记人</th>
		     	    <th width="20%" height="20px">登记时间</th>
		     	    <th width="10%" height="20px">罪犯到达进入<br>会见室审核人</th>
		     	    <th width="20%" height="20px">到达进入时间</th>
		     	    <th width="40%" height="20px">会见说明</th>
		     	 </tr>
		     	    <logic:notEmpty name="recordOtherInfoVOList">
		     	       <logic:iterate id="jl" name="recordOtherInfoVOList">
			     	    	<tr>
			     	    		<td width="10%" height="20px">${jl.djUser }</td>
			     	    		<td width="20%" height="20px">${jl.djTime }</td>
			     	    		<td width="10%" height="20px">${jl.frInUser }</td>
			     	    		<td width="20%" height="20px">${jl.frInTime }</td>
			     	    		<td width="40%" height="20px">${jl.hjInfo }</td>
			     	    		
			     	    	</tr>
		     	    	</logic:iterate>
		     	    </logic:notEmpty>
		     	    
		     	    <logic:empty name="recordOtherInfoVOList">
			     	    	<tr>
			     	    		<td colspan="5"><font color="red">没有相关记录</font></td>
			     	    	</tr>
		     	    </logic:empty>
	  			 </table>
	  			 <table>
		     	<tr>
		     	    <th width="10%" height="20px">亲属姓名</th>
		     	    <th width="20%" height="20px">身份证号码</th>
		     	    <th width="10%" height="20px">关系</th>
		     	    <th width="10%" height="20px">性别</th>
		     	    <th width="20%" height="20px">地址</th>
		     	    <th width="20%" height="20px">照片</th>
		     	 </tr>
		     	    
		     	    <logic:notEmpty name="jlHjDjQslist">
		     	       <logic:iterate id="jl1" name="jlHjDjQslist">
			     	    	<tr>
			     	    		<td width="10%" height="20px">${jl1.qsName }</td>
			     	    		<td width="20%" height="20px">${jl1.qsSfz }</td>
			     	    		<td width="10%" height="20px">${jl1.gx }</td>
			     	    		<td width="20%" height="20px">${jl1.xb }</td>
			     	    		<td width="20%" height="20px">${jl1.dz }</td>
			     	    		<td width="20%" height="20px">
				     	    		<c:if test="${not empty jl1.zp}"><img id="photo" src="hjRecord.do?method=showPhoto&qsid=${jl1.webId}" /></c:if>
				     	    		<c:if test="${empty jl1.zp}"><font color="red">暂无照片</font></c:if>
	      	 							

	      	 					</td>
			     	    	</tr>
		     	    	</logic:iterate>
		     	    </logic:notEmpty>
		     	    <logic:empty name="jlHjDjQslist">
			     	    	<tr>
			     	    		<td colspan="5"><font color="red">没有相关记录</font></td>
			     	    	</tr>
		     	    </logic:empty>
	  			 </table>
	  	 	</div>
	  	 	<a href="hjRecord.do?method=search&callTimeStart2=${callTimeStart2}&callTimeEnd2=${callTimeEnd2}&frName2=${frName2}&frNo3=${frNo3}&zw2=${zw2}&type2=${type2}&jy2=${jy2}&jq2=${jq2}&qsName2=${qsName2}&pageNo2=${pageNo2}&recRatingState2=${recRatingState2}&recAssessmentState2=${recAssessmentState2}&recordOverTime2=${recordOverTime2}" style="position:absolute;margin-top:20px;margin-left:900px;height:25px; line-height:25px;"><img src="images/fanhui.gif"></img></a>
	  	 </html:form>	
  </body>
</html>

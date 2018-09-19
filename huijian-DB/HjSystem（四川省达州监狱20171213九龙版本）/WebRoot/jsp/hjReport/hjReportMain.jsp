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
		<script src="<%=basePath %>js/hjReport.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
 	    <script language="javascript">   
			document.onkeydown   =   function(){
				if(event.keyCode   ==   8){
					if(event.srcElement.tagName.toLowerCase()   !=   "input"  &&  event.srcElement.tagName.toLowerCase()   !=   "textarea")   
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
  <body onkeydown="return enterSubmit(this,event);">
  	<div id="user_info2"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：会见报表 </span></div>
       <html:form action="hjReport.do?method=search" method="post">
        	<div id="content2">
		     	<table  cellspacing="0" cellpadding="0">
		     	  <tr>
		     	     <td height="25px" nowrap="nowrap" width="32%">通话开始时间：<html:text styleId="callTimeStart" property="callTimeStart" value="${beginTime}" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" /></td>
		     	     <td height="25px" nowrap="nowrap" width="32%">通话结束时间：<html:text styleId="callTimeEnd"   property="callTimeEnd"   value="${endTime}"   onfocus="WdatePicker({startDate:'%y-%M-01 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" /></td>
		     	 	 <td height="25px" nowrap="nowrap" width="32%">监区：<html:select styleId="jq"  property="jq" style="width:130px">
	 	 													<html:option value="null">全部</html:option>
	 	 													<logic:notEmpty name="list">
	 	 														<logic:iterate id="jq" name="list">
	 	 															<html:option value="${jq.jqNo}">${jq.jqName}</html:option>
	 	 														</logic:iterate>
	 	 													</logic:notEmpty>
	 	 													</html:select>
		     	 	 &nbsp;&nbsp;<a href="javascript:void(0);" onclick="goCountMap();return false;"><img src="<%=basePath %>images/chaxun.gif"></img></a>&nbsp;&nbsp;<input type="button" value="未会见犯人名单" onclick="whjfrMap()" /></td>
		     	  </tr>
		     	</table>
	  	 	</div>
	  	 </html:form>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<script type="text/javascript">
  				//window.setInterval(printZjz, 1500); 
  				function printZjz(){
  					window.print();
  					window.close();
  				}
  			</script>
  </head>
  
  <body onload="printZjz();">
    	<form action="" method="post">
                <br><br>
    		<div style="text-align: center"><font size="5">晋中监狱亲属会见登记表</font></div>
    		<div style="width:80%;margin-left: 10%">服刑人员姓名：<strong>${printHjDjVO.frName }</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;监区：<strong>${printHjDjVO.jqName }</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;座位号：<strong>${printHjDjVO.seatNo }</strong>&nbsp;&nbsp;&nbsp;${printHjDjVO.djTime }</div>
    		<div>
	    		<table width="80%" style="margin-left: 10%" border="1"  cellpadding="0" cellspacing="0">
	    			<tr>
	    				<td width="20%" height="30px" nowrap="nowrap" align="center">序号</td>
	    				<td width="20%" height="30px" nowrap="nowrap" align="center">亲属姓名</td>
	    				<td width="20%" height="30px" nowrap="nowrap" align="center">关系</td>
	    				<td width="20%" height="30px" nowrap="nowrap" align="center">性别</td>
	    				<td width="20%" height="30px" nowrap="nowrap" align="center">证件号码</td>
	    			</tr>
	    	
	    				<logic:notEmpty name="printHjDjVO" property="list">
	    					<logic:iterate id="qs" name="printHjDjVO" property="list" indexId="index">
	    						<tr>
	    								<td width="20%" height="30px" nowrap="nowrap" align="center">${index+1}&nbsp;</td>
			    						<td width="20%" height="30px" nowrap="nowrap" align="center">${qs.qsName }&nbsp;</td>
			    						<td width="20%" height="30px" nowrap="nowrap" align="center">${qs.gx }&nbsp;</td>
			    						<td width="20%" height="30px" nowrap="nowrap" align="center">
			    							<c:if test="${qs.xb=='男' }">男</c:if>
			    							<c:if test="${qs.xb=='女' }">女</c:if>
			    							<c:if test="${qs.xb!='女' && qs.xb!='男'}">&nbsp;</c:if>
			    						</td>
			    						<td width="20%" nowrap="nowrap" align="center">${qs.qsSfz }&nbsp;</td>
	    						</tr>
	    					</logic:iterate>
	    				</logic:notEmpty>
	    				<tr>
	    					<td nowrap="nowrap" height="50px" style="text-align:center;">会见说明:</td>
	    					<td height="50px" colspan="4" style="text-align:center;">${printHjDjVO.hjSm } &nbsp;</td>
	    				</tr>
	    				<tr>
	    					<td nowrap="nowrap" height="50px" style="text-align:center;">领导指示:</td>
	    					<td height="50px" colspan="2" style="text-align:center;">&nbsp;</td>
	    					<td nowrap="nowrap" height="50px" style="text-align:center;">主管干警:</td>
	    					<td nowrap="nowrap" height="50px" style="text-align:center;">&nbsp;</td>
	    				</tr>
	    		</table>
    		</div>
    	</form>
  </body>
</html>

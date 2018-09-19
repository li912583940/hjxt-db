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
				document.all.WebBrowser.ExecWB(6,1);
				window.close();
				}
</script>
  </head>
  
  <body onload="printZjz();">
    	<form action="" method="post">
                <br></br>
    		<div style="text-align: center"><font size="5"><b>广东省东莞监狱会见通知书（存根）</b></font></div>
    		<br></br>
    		<div style="margin-left: 1%;text-align: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">${printHjDjVO.jqName }罪犯:&nbsp;${printHjDjVO.frName }（编号:${printHjDjVO.frNo}）的亲属：<logic:notEmpty name="printHjDjVO" property="list"><logic:iterate id="qs" name="printHjDjVO" property="list" indexId="index">${qs.qsName }&nbsp;</logic:iterate></logic:notEmpty>等${printHjDjVO.hjRsCount}于${printHjDjVO.djTime1 }前往会见室窗口办理会见</font></div>
    		<br></br>
    		<div style="margin-left: 1%;text-align: left%"><font size="4">经办人：${printHjDjVO.jpUser }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会见编号：${printHjDjVO.hjIndex }</div>
    		<br></br>
    		<div >-------------------------------------------------------------------------------------------------------------------</div>                                                                               
    		<br></br>
    		<div style="text-align: center"><font size="5"><b>广东省东莞监狱会见通知书</b></font></div>
    		<br></br>
    		<div style="margin-left: 1%"><font size="4">${printHjDjVO.jqName }: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<b>会见编号：${printHjDjVO.hjIndex }</b></div>&nbsp;&nbsp;&nbsp;
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<div style="margin-left: 1%;text-align: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">现有罪犯${printHjDjVO.frName }（编号:${printHjDjVO.frNo}；罪名：${printHjDjVO.infoZm }）的亲属（共${printHjDjVO.hjRsCount}）：</font></div>

    		<div>
	    		<table width="100%" style="margin-left: 1%" border="1"  cellpadding="0" cellspacing="0" font size="4">
	    			<tr>
	    				<td width="30%" height="30px" nowrap="nowrap" align="center">关系</td>
	    				<td width="30%" height="30px" nowrap="nowrap" align="center">亲属姓名</td>
	    				<td width="30%" height="30px" nowrap="nowrap" align="center">证件号码</td>
	    			</tr>
	    				<logic:notEmpty name="printHjDjVO" property="list">
	    					<logic:iterate id="qs" name="printHjDjVO" property="list" >
	    						<tr>
			    						<td width="30%" height="30px" nowrap="nowrap" align="center">${qs.gx }&nbsp;</td>
			    						<td width="30%" height="30px" nowrap="nowrap" align="center">${qs.qsName }&nbsp;</td>
			    						<td width="30%" nowrap="nowrap" align="center">${qs.qsSfz }&nbsp;</td>
	    						</tr>
	    					</logic:iterate>
	    				</logic:notEmpty>
	    		</table>

    		</div>
    		<div style="margin-left: 1%"><font size="4">于${printHjDjVO.djTime1 }前往会见室窗口办理会见</div>
    		<br></br>
    		
    		<br></br>
    		<div style="margin-left: 1%"><font size="4">经办人：${printHjDjVO.jpUser }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${printHjDjVO.djTime1 }
    	</form>
    	 <object id="WebBrowser" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"
                viewastext>
            </object>
    	
  </body>
</html>

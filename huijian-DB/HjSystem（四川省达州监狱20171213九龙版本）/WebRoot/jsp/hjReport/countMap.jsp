<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/slxt-rs-tags" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String http=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
String printImage = "<img alt=打印 src='" + path + "/images/print.gif' border=no style='vertical-align:middle'>";
String wordImage = "<img alt=导出Word src='" + path + "/images/doc.gif' border=no style='vertical-align:middle'>";
String excelImage = "<img alt=导出excel src='" + path + "/images/excel.gif' border=no style='vertical-align:middle'>";
String pdfImage = "<img alt=导出PDF文件 src='" + path + "/images/pdf.gif' border=no style='vertical-align:middle'>";
String firstPageImage = "<img src='" + path + "/images/firstpage.gif' border=no style='vertical-align:middle'>";
String lastPageImage = "<img src='" + path + "/images/lastpage.gif' border=no style='vertical-align:middle'>";
String nextPageImage = "<img src='" + path + "/images/nextpage.gif' border=no style='vertical-align:middle'>";
String prevPageImage = "<img src='" + path + "/images/prevpage.gif' border=no style='vertical-align:middle'>";
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
	 </script>
	 <style type="text/css">
	 	.ifile {position:absolute;opacity:0;margin-top:4px;filter:alpha(opacity=0);}
		a{text-decoration: NONE}
	</style>   
			 
  </head>
  <body onkeydown="return enterSubmit(this,event);">
  	<div id="user_info2"><span>'登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info2"><span>当前位置：会见报表 &nbsp; &nbsp;<a href="#" onclick="report1_saveAsExcel();return false;"><%=excelImage%></a> &nbsp; &nbsp;<a href="#" onclick="report1_saveAsWord();return false;"><%=wordImage%></a>&nbsp; &nbsp;<a href="#" onclick="report1_saveAsPdf();return false;"><%=pdfImage%></a></span></div>
	<table align="center"  cellspacing="0" cellpadding="0" style="margin-top:10px;">
		<tr><td>
			<report:html name="report1"
				srcType="file"
				reportFileName="/hjReport.raq"
				funcBarLocation=""
				exceptionPage="/jsp/myError.jsp"
				params="Call_Time_Start1=${beginTime};Call_Time_Start2=${endTime};"
				width="250"
				height="-1"
				scale="2.0"
				needPageMark="no"
			/>
		</td></tr>
	</table>
	<div style="position:absolute;margin-top:20px;margin-left:200px;height:25px; line-height:25px;"><a href="<%=basePath %>hjReport.do?method=search"><img src="<%=basePath %>images/fanhui.gif"/></a></div>
</body>
</html>

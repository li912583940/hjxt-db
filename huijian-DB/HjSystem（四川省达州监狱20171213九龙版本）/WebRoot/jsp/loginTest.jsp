<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript"></script>
    <script src="<%=basePath %>js/load.js" type="text/javascript"></script>
    <script type="text/javascript">
    		 var basePath="<%=basePath%>";
    </script>
  </head>
  <body>
		  <center>
		   <form action="" method="post">
		      <table border="1">
		      	<tr>
		      	    <td nowrap="nowrap">姓名：<input type="text" id="qsName" name="qsName" /></td>
		      	    <td height="126px" rowspan="2" width="100px" id="photo" >
			      	  <OBJECT width="100px" height="126px" ID="IDCard2" NAME="IDCard2"  codeBase="<%=basePath %>ocx/IDCardCom.CAB" CLASSID="CLSID:7B4E34BA-C415-4388-B13C-184FDBD956F3">
							    <PARAM NAME="Visible" VALUE="-1">
							    <PARAM NAME="AutoScroll" VALUE="0">
							    <PARAM NAME="AutoSize" VALUE="0">
							    <PARAM NAME="AxBorderStyle" VALUE="1">
							    <PARAM NAME="Caption" VALUE="IDCard">
							    <PARAM NAME="Color" VALUE="4278190095">
							    <PARAM NAME="Font" VALUE="MS Sans Serif">
							    <PARAM NAME="KeyPreview" VALUE="0">
							    <PARAM NAME="PixelsPerInch" VALUE="96">
							    <PARAM NAME="PrintScale" VALUE="1">
							    <PARAM NAME="Scaled" VALUE="-1">
							    <PARAM NAME="DropTarget" VALUE="0">
							    <PARAM NAME="HelpFile" VALUE="">
							    <PARAM NAME="DoubleBuffered" VALUE="0">
							    <PARAM NAME="Enabled" VALUE="-1">
							    <PARAM NAME="Cursor" VALUE="0">
							    <PARAM NAME="HelpType" VALUE="1">
							    <PARAM NAME="HelpKeyword" VALUE="">
						</OBJECT>
					</td>
		      	    <td width="100px"><input type="button" value="头像" /></td>
		        </tr>
		       <tr> <td nowrap="nowrap">身份证：<input type="text" id="qssfz" name="qssfz" /></td><td width="100px"><input type="button" value="识别" onclick="aa();" /></td></tr>
		      </table>
		     </form> 
		     
		 </center>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>会见系统</title>
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
  </head>
  <frameset rows="100,*,30" frameborder="no" border="0" framespacing="0">
  <frame src="rstop.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" noresize="noresize" >
    <frameset cols="130,17,*" frameborder="no" border="0" framespacing="0">
		      <frame src="<%=basePath%>/login.do?method=search"  scrolling="auto" name="tree" noresize="noresize" style="SCROLLBAR-FACE-COLOR:red;  SCROLLBAR-HIGHLIGHT-COLOR:red; SCROLLBAR-SHADOW-COLOR:red;">
		      <frame src="rsmiddle.jsp" name="middleFrame" id="middleFrame" scrolling="No" title="middleFrame" noresize="noresize"/>
		      <frame src="<%=basePath%>/jsp/welcome.jsp" name="main" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" id="main" />
	</frameset>
  <frame src="rsbottom.jsp" scrolling="no" marginwidth="0" id="bottom" noresize="noresize">
  
</frameset>
<noframes><body>
</body></noframes>
</html>

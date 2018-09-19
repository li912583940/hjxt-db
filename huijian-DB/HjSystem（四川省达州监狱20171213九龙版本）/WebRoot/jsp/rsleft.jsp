<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>left</title>
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
</head>
  
  <body id="left_bg">
    <div id="left">
         <form action="" method="post">
            <ul>
     		   <logic:iterate id="menu" name="listMenu">
     		       <li> <a href="${menu.menuUrl}" target="main" >${menu.menuName }</a></li>
     		   </logic:iterate>
     		</ul>   
     	</form>
     </div>
  </body>
</html>

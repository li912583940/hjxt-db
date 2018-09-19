<%@ page contentType="text/html;charset=GBK" %>

<%
	String width = "980";  //定义网页中页面宽度
	try{ 
		
	}catch( Exception e ) {}
%>

<html>
<head>
<title>润乾报表4.0演示</title>
<LINK href="Style.css" type=text/css rel=stylesheet>
</head>
<body topMargin=0 leftMargin=0 rightMargin=0 bottomMargin=0 background="images/mainbg.gif">

<table id=topTable cellspacing=0 cellpadding=0 width="<%=width%>" border="0" align="center">
  
  <tr>
    <td><img src="images/runqian.jpg" width="100%" ></td>
  </tr>
 
</table>

<table cellspacing=0 cellpadding=0 width="<%=width%>" border="0" align="center">
  <tr>
    <td width="100%"><iframe id=frame1 src="reportJsp/frame.html" style="background-color:white;height:expression( document.body.offsetHeight - topTable.offsetHeight -  4 );" 
    	width=100% height=100 
    	marginWidth=0 marginHeight=0 frameborder=no onload="javascript:{ dyniframesize( 'frame1' );}"></iframe></td>
  </tr>
</table>

<script language="Javascript">
	var getFFVersion = navigator.userAgent.substring( navigator.userAgent.indexOf( "Firefox" ) ).split( "/" )[1];
	//extra height in px to add to iframe in FireFox 1.0+ browsers
	var FFextraHeight = getFFVersion >= 0.1 ? 16 : 0 

	function dyniframesize( iframename ) {
		if( document.all ) return;
	  	var pTar = null;
	  	if ( document.getElementById ) {
	    	pTar = document.getElementById( iframename );
	  	}
	  	else{
	    	eval( 'pTar = ' + iframename + ';' );
	  	}
	  	if(	pTar && !window.opera ) {
	    	//begin resizing iframe
	    	pTar.style.display = "block";
	    	if( pTar.contentDocument && pTar.contentDocument.body.offsetHeight ) {
	      		//ns6 syntax
	      		pTar.height = document.body.clientHeight - document.getElementById( "topTable" ).offsetHeight - 2 + FFextraHeight; 
	    	}
	    	else if( pTar.Document && pTar.Document.body.scrollHeight ) {
	      		//ie5+ syntax
	      		pTar.height = document.body.offsetHeight - topTable.offsetHeight - 5;
	    	}
	  	}
	}
</script>

</body>
</html>

<%@ page language="java" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<style>
#uploader{
position:relative;
}
#uploader_queue{

width:200px;
left:200px;
top:0;
}
</style>
<title>My JSP 'upLoad.jsp' starting page</title>
<link type="text/css" rel="stylesheet" href="<%=basePath%>js/uploadify/uploadify.css"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/uploadify/jquery.uploadify-3.1.min.js"></script>
<script>
$(function() {
	$("#file_upload").uploadify({
		'auto' : true,
		'method' : "get",
		'formData' : {'folder' : 'file'},
		'height' : 30,
		'swf' : '<%=basePath%>js/uploadify/uploadify.swf', // flash
		'uploader' : '<%=basePath%>login.do?method=uploadfj', // 数据处理url
		'width' : 80,
		'fileTypeDesc' : '',
		'fileTypeExts' : '*.gif;*.bmp;*.jpg;*.pcx;*.fax;*.png',
		'fileSizeLimit' : '102400KB',
		'buttonText' : '添加附件',
		'uploadLimit' : 2,
		'successTimeout' : 5,
		'requeueErrors' : false,
		'removeTimeout' : 10,
		'removeCompleted' : false,
		'queueSizeLimit' :10,
		'queueID' : 'uploader_queue',
		'progressData' : 'percentage',
		'onInit' : function (){},
		 'onClearQueue' : function(queueItemCount) {
            alert(queueItemCount + ' file(s) were removed from the queue');
        },'onUploadStart': function (file) {  
                    $("#file_upload").uploadify("settings", "formData", { 'ctrlid': document.getElementById("hjid").value});  
                    //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
                }  
         
		
	});
});
		 
</script>
</head>
<body>
<form action="hjdj.do?method=search" method="post">
<input type="hidden" id="hjid" value="1">
<a href="javascript:$('#file_upload').uploadify('cancel','*');">Clear Queue</a>
<div id="uploader">
<p><input type="file" name="file_upload" id="file_upload" /></p>
<div id="uploader_queue"></div>

</div>
</form>
</body>
</html>

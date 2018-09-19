<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>main</title>
		<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
      	<script src="<%=basePath %>js/jquery-1.2.6.js" type="text/javascript" ></script>
		<script src="<%=basePath %>js/materialMessage.js" type="text/javascript" ></script>
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
   <body>
   	<div id="user_info"><span>登录姓名：${users.userName } 登录时间：${loginTime }</span></div>
	<div id="lm_info"><span>当前位置：罪犯管理 </span></div>
    <form action="materialMessage.do?method=checkTpDh&frNo=${jlFr.frNo}" method="post">
      	 <table style="border-style:solid; border-color:#e6e6e6; border-width:1px 0 0 1px; width:60%; margin:0 auto; margin-top:10px;">
      	 	<tr>
      	 		<td>罪犯编号：</td>
      	 		<td><input type="text" id="frNo1" name="frNo1" value="${jlFr.frNo }" disabled="disabled" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td>罪犯姓名：</td>
      	 		<td><input type="text" id="frName1" name="frName1" value="${jlFr.frName }" disabled="disabled" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td>特批电话号码：</td>
      	 		<td><input type="text" id="tpTele" name="tpTele" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td>通话人姓名：</td>
      	 		<td><input type="text" id="tpxm" name="tpxm" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td>与通话人的关系：</td>
      	 		<td>
      	 			<select id="tpgx" style="width:130px;" >
      	 				<option value="夫妻">夫妻</option>
				   		<option value="父子">父子</option>
				   		<option value="母子">母子</option>
				   		<option value="父女">父女</option>
				   		<option value="母女">母女</option>
				   		<option value="爷孙">爷孙</option>
				   		<option value="婆孙">婆孙</option>
				   		<option value="兄弟">兄弟</option>
				   		<option value="姊妹">姊妹</option>
				   		<option value="兄妹">兄妹</option>
				   		<option value="姐弟">姐弟</option>
				   		<option value="朋友">朋友</option>
				   		<option value="同事">同事</option>
				   		<option value="表哥">表哥</option>
				   		<option value="表弟">表弟</option>
				   		<option value="表兄">表兄</option>
				   		<option value="表妹">表妹</option>
				   		<option value="表姐">表姐</option>
				   		<option value="姨夫">姨夫</option>
				   		<option value="叔侄">叔侄</option>
				   		<option value="儿媳">儿媳</option>
				   		<option value="岳母">岳母</option>
				   		<option value="岳父">岳父</option>
				   		<option value="岳母">岳母</option>
				   		<option value="妹夫">妹夫</option>
				   		<option value="舅舅">舅舅</option>
				   		<option value="姐夫">姐夫</option>
				   		<option value="内弟">内弟</option>
				   		<option value="表兄">表兄</option>
				   		<option value="表妹">表妹</option>
				   		<option value="姑侄">姑侄</option>
				   		<option value="姨侄">姨侄</option>
				   		<option value="其它">其它</option>
      	 			</select>
      	 		</td>
      	 	</tr>
      	 	<tr>
      	 		<td>特批次数：</td>
      	 		<td><input type="text" id="tpcs" name="tpcs" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td>特批时长：</td>
      	 		<td><input type="text" id="tpsc" name="tpsc" /></td>
      	 	</tr>
      	 	<tr>
      	 		<td><a href="javascript:void(0);" onclick="addSaveFrTpdh();return false;"><img src="images/baocun.gif"></img></a></td>
      	 		<td><a href="materialMessage.do?method=checkTpDh&frNo=${jlFr.frNo}"><img src="images/fanhui.gif"></img></a></td>
      	 	</tr>
      	  </table>
     </form>
  </body>
</html>

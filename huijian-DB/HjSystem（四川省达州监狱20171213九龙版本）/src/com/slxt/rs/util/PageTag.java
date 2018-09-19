package com.slxt.rs.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PageTag extends BodyTagSupport {

	private String url;//点击上一页或其他分页信息时的请求路径
	private String pageNo;//第几页
	private String recordCount;//记录数
	private String pageSize;  //行树

	public int doStartTag() throws JspException {
		int pageNo = 0;//第几页
		int pageCount = 0;//总页数
		int recordCount = 0;//记录总数
	    int pageSize=0;
		pageNo = Integer.parseInt(this.pageNo);
		recordCount = Integer.parseInt(this.recordCount);
		pageSize=Integer.parseInt(this.pageSize);
		int temp = recordCount % pageSize ;
		if(temp!=0 && recordCount> pageSize){
			pageCount=recordCount/pageSize+1 ;
		}else{
			pageCount=recordCount/pageSize ;			
		}
		if(pageCount==0){
			pageCount++ ;
		}
		
		StringBuffer html = new StringBuffer();
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();
		String host = request.getContextPath();
//		if (url.indexOf("/") != 0) {
//			url = "/" + url;
//		}
		
		if (pageCount <= 0) {
			pageNo = 0;
			pageCount = 0;
		}
		html.append("<input type=\"hidden\" id=\"pageNo\"  name=\"pageNo\" value=\"")
		.append(pageNo)
		.append("\">");
//		html.append(
//			"<table width=\"100%\" border=\"0\"");
//		html.append("<tr> <td colspan=2 background=\"")
//			.append(host)
//			.append("/images/d_4.gif\"><img src=\"")
//			.append(host)
//			.append("/images/d_4.gif\" width=4 height=1></td></tr>");
//		html.append("<tr>");
//		html.append("<td height=\"25\">总记录数: <span>")
		html.append("总记录数： <span>")
//		html.append("<td height=\"25\" align=\"right\">Total:")
			.append(recordCount)
			.append("</span>&nbsp;&nbsp;页数：&nbsp;")	
			.append(pageNo)
			.append("/")
			.append(pageCount+"&nbsp;&nbsp;");
	//		.append("</td>");
	//	html.append("<td align=\"right\">");
		if (pageNo <= 1) {
//			html.append("<input type=\"button\" value=\"首页\" disabled >\n");
//			html.append("<input type=\"button\" value=\"前一页\" disabled >\n");
			html.append("<a disabled>首页</a> \n");
			html.append("<a disabled>前一页</a>\n");
		} else {
//			html.append(
//				"<input type=\"button\" value=\"首页\" onclick=\"onPage(1);\">\n");
			html.append(
			"<a href=\"javascript:void(0)\" onclick=\"onPage(1);return false;\">首页</a>\n");
//			html.append("<input type=\"button\" value=\"前一页\" onclick=\"onPage(")
			html.append("<a href=\"javascript:void(0)\" onclick=\"onPage(")
				.append(pageNo - 1)
				.append(");return false;\">前一页</a>\n");
		}
		if (pageNo == pageCount) {
//			html.append("<input type=\"button\" value=\"下一页\" disabled>\n");
//			html.append("<input type=\"button\" value=\"末页\" disabled>\n");
			html.append("<a disabled>下一页</a> \n");
			html.append("<a disabled>末页</a>\n");
		} else {
//			html.append("<input type=\"button\" value=\"下一页\" onclick=\"onPage(")
			html.append("<a href=\"javascript:void(0)\" onclick=\"onPage(")
				.append(pageNo + 1)
				.append(");return false;\">下一页</a>\n");
//			html.append("<input type=\"button\" value=\"末页\" onclick=\"onPage(")
			html.append("<a href=\"javascript:void(0)\" onclick=\"onPage(")
				.append(pageCount)
				.append(");return false;\">末页</a>\n");
		}
		if (pageCount <= 1) {
			html.append("&nbsp;").append(
	//			"<input type=\"button\" value=\"转到\"  disabled>\n");
			"<a disabled>转到</a> \n");
			html.append("<input type=\"text\" name=\"gotopageno\" size=3 maxsize=5  value=")
				.append(pageNo)
				.append(" class=\"textfield\" disabled>");
		//		.append("</td>\n");
		} else {
//			html.append("&nbsp;").append("<input type=\"button\" value=\"转到\" onclick=\"onGoPage(");
			html.append("&nbsp;").append("<a href=\"javascript:void(0)\" onclick=\"onGoPage(");
			html.append("document.all('gotopageno').value");
			html.append(");return false;\">转到</a>\n")
				.append("<input type=\"text\" name=\"gotopageno\" size=3 maxsize=5  value=")
				.append(pageNo)
				.append(" class=\"textfield\">");
				//.append("</td>\n");
		}
//		html.append("</tr>\n");
//		html.append("</table>\n");

		html.append("<script language=\"javascript\">\n");
		html.append("function onPage(pageno){\n");
		html.append("\tvar form = document.forms[0];\n");
		html.append("\tdocument.all('pageNo').value = pageno;\n");
		html.append("\tform.action = \"").append(url).append("\";");
		html.append("\tform.submit();\n");
		html.append("}\n");

		html.append("function onGoPage(pageno){\n");
		html.append("\tvar form = document.forms[0];\n");
		html.append("\tfor(var i=0 ; i<pageno.length ;i++){\n");
		html.append("\t\tif(pageno.charAt(i)<'0'||pageno.charAt(i)>'9'){\n");
		html.append("\t\t\tpageno=-1;\n");
		html.append("\t\t\tbreak;\n");
		html.append("\t\t}\n");
		html.append("\t}\n");
		html.append("\tpageno=parseInt(pageno);\n");
		html.append("\tif(pageno<=0||pageno>").append(pageCount).append("){\n");
		html.append("\t\talert(\"请输入正确的页码，页码范围在 1 到 ")
			.append(pageCount)
			.append("\");\n");
		html.append("\t\treturn;\n");
		html.append("\t}\n");
		html.append("\tdocument.all('pageNo').value = pageno;\n");
		html.append("\tform.action = \"").append(url).append("\";");
		html.append("\tform.submit();\n");
		html.append("}\n");
		html.append("</script>\n");
		try {
			pageContext.getOut().write(html.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException("write error!");
		}
		return EVAL_BODY_INCLUDE;
	}
	
	
	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}	
}

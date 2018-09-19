package com.slxt.rs.form;

import org.apache.struts.action.ActionForm;

public class CardMessageForm extends ActionForm{
	private String callTimeStart; 
	private String callTimeEnd;
	private String frName;
	private String qsName;
	private String jqNo;
	private String frNo;
	
	public String getFrNo() {
		return frNo;
	}
	public void setFrNo(String frNo) {
		this.frNo = frNo;
	}
	public String getCallTimeStart() {
		return callTimeStart;
	}
	public void setCallTimeStart(String callTimeStart) {
		this.callTimeStart = callTimeStart;
	}
	public String getCallTimeEnd() {
		return callTimeEnd;
	}
	public void setCallTimeEnd(String callTimeEnd) {
		this.callTimeEnd = callTimeEnd;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getQsName() {
		return qsName;
	}
	public void setQsName(String qsName) {
		this.qsName = qsName;
	}
	public String getJqNo() {
		return jqNo;
	}
	public void setJqNo(String jqNo) {
		this.jqNo = jqNo;
	}
	
}

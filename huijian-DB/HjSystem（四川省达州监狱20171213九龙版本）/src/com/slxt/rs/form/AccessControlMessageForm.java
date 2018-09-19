package com.slxt.rs.form;

import org.apache.struts.action.ActionForm;

public class AccessControlMessageForm extends ActionForm{
	private String callTimeStart; 
	private String callTimeEnd;
	private String state;
	private String jqNo;
	
	public String getJqNo() {
		return jqNo;
	}
	public void setJqNo(String jqNo) {
		this.jqNo = jqNo;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}

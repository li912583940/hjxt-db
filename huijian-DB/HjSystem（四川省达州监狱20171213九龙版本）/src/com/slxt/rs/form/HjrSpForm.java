package com.slxt.rs.form;

import org.apache.struts.action.ActionForm;

public class HjrSpForm extends ActionForm{
	private String callTimeStart; 
	private String callTimeEnd;
	private String spState;
	private String frName;
	private String sqx;
	
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getSqx() {
		return sqx;
	}
	public void setSqx(String sqx) {
		this.sqx = sqx;
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
	public String getSpState() {
		return spState;
	}
	public void setSpState(String spState) {
		this.spState = spState;
	}
	
}

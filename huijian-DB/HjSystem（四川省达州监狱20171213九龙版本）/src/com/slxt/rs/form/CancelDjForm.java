package com.slxt.rs.form;

import org.apache.struts.action.ActionForm;

public class CancelDjForm extends ActionForm{
	private String beginTime;
	private String endTime;
	private String state;
	private String hjType;
	private String frName;
	private String frNo;
	private String jq;
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getFrNo() {
		return frNo;
	}
	public void setFrNo(String frNo) {
		this.frNo = frNo;
	}
	public String getJq() {
		return jq;
	}
	public void setJq(String jq) {
		this.jq = jq;
	}
	public String getHjType() {
		return hjType;
	}
	public void setHjType(String hjType) {
		this.hjType = hjType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}

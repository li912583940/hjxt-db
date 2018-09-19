package com.slxt.rs.form;

import org.apache.struts.action.ActionForm;

public class YjComeForm extends ActionForm{
	private String frName;
	private String frNo;
	private String hjIndex;
	private String jq;
	
	public String getJq() {
		return jq;
	}
	public void setJq(String jq) {
		this.jq = jq;
	}
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
	public String getHjIndex() {
		return hjIndex;
	}
	public void setHjIndex(String hjIndex) {
		this.hjIndex = hjIndex;
	}
	
}

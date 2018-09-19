package com.slxt.rs.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FrForm extends ActionForm{
	private String frNo;
	private String frName;
	private String frCard;
	private String jy;
	private String jq;
	private String jbNo;
	private String stateZdzf;
	private String state;
	private String isHjStop;
	private FormFile myFile;
	public FormFile getMyFile(){
		return myFile;
	}
	public void setMyFile(FormFile myFile) {
		this.myFile = myFile;
	}
	public String getFrNo() {
		return frNo;
	}
	public void setFrNo(String frNo) {
		this.frNo = frNo;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getJy() {
		return jy;
	}
	public void setJy(String jy) {
		this.jy = jy;
	}
	public String getJq() {
		return jq;
	}
	public void setJq(String jq) {
		this.jq = jq;
	}
	public String getJbNo() {
		return jbNo;
	}
	public void setJbNo(String jbNo) {
		this.jbNo = jbNo;
	}
	public String getStateZdzf() {
		return stateZdzf;
	}
	public void setStateZdzf(String stateZdzf) {
		this.stateZdzf = stateZdzf;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsHjStop() {
		return isHjStop;
	}
	public void setIsHjStop(String isHjStop) {
		this.isHjStop = isHjStop;
	}
	public String getFrCard() {
		return frCard;
	}
	public void setFrCard(String frCard) {
		this.frCard = frCard;
	}

}

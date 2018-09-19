package com.slxt.rs.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class HjRecordForm extends ActionForm{
	private String callTimeStart; 
	private String callTimeEnd;
	private String type;
	private String jqNo;
	private String frNo;
	private String frName;
	private String jy;
	private String qsName;
	private String zw;
	private String recRatingState;
	private String recAssessmentState;
	private String recordOverTime;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJqNo() {
		return jqNo;
	}
	public void setJqNo(String jqNo) {
		this.jqNo = jqNo;
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
	public String getQsName() {
		return qsName;
	}
	public void setQsName(String qsName) {
		this.qsName = qsName;
	}
	public String getJy() {
		return jy;
	}
	public void setJy(String jy) {
		this.jy = jy;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getRecRatingState() {
		return recRatingState;
	}
	public void setRecRatingState(String recRatingState) {
		this.recRatingState = recRatingState;
	}
	public String getRecAssessmentState() {
		return recAssessmentState;
	}
	public void setRecAssessmentState(String recAssessmentState) {
		this.recAssessmentState = recAssessmentState;
	}
	public String getRecordOverTime() {
		return recordOverTime;
	}
	public void setRecordOverTime(String recordOverTime) {
		this.recordOverTime = recordOverTime;
	}
	

}

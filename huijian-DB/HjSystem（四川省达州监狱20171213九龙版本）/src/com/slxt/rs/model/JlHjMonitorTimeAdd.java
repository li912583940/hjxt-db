package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlQs entity. @author MyEclipse Persistence Tools
 */

public class JlHjMonitorTimeAdd implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String callId;
	private Integer lineNo;
	private String jy;
	private Integer addTime;
	private Integer state;
	private Timestamp createTime;
	

	// Constructors

	/** default constructor */
	public JlHjMonitorTimeAdd() {
	}
	
	/** full constructor */
	public JlHjMonitorTimeAdd(String callId, Integer lineNo, String jy, Integer addTime, Integer state) {
		this.callId = callId;
		this.lineNo = lineNo;
		this.jy = jy;
		this.addTime = addTime;
		this.state = state;
	}

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	public String getJy() {
		return jy;
	}

	public void setJy(String jy) {
		this.jy = jy;
	}

	public Integer getAddTime() {
		return addTime;
	}

	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	

}
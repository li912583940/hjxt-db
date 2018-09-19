package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlHjInfo entity. @author MyEclipse Persistence Tools
 */

public class JlHjRecAssessmentInfo implements java.io.Serializable {

	// Fields

	private Long webId;
	private String callId;
	private String userNo;
	private String userName;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public JlHjRecAssessmentInfo() {
	}

	/** minimal constructor */
	public JlHjRecAssessmentInfo(String callId, String userNo,Timestamp createTime) {
		this.callId = callId;
		this.userNo = userNo;
		this.createTime = createTime;
	}

	/** full constructor */
	public JlHjRecAssessmentInfo(String callId, String userNo, String userName,Timestamp createTime) {
		this.callId = callId;
		this.userNo = userNo;
		this.userName = userName;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getWebId() {
		return this.webId;
	}

	public void setWebId(Long webId) {
		this.webId = webId;
	}

	public String getCallId() {
		return this.callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
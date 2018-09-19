package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlHjInfo entity. @author MyEclipse Persistence Tools
 */

public class JlHjRecRatingInfo implements java.io.Serializable {

	// Fields

	private Long webId;
	private String callId;
	private String userNo;
	private String userName;
	private String writeTxt;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public JlHjRecRatingInfo() {
	}

	/** minimal constructor */
	public JlHjRecRatingInfo(String callId, String userNo, String writeTxt,Timestamp createTime) {
		this.callId = callId;
		this.userNo = userNo;
		this.createTime = createTime;
		this.writeTxt = writeTxt;
	}

	/** full constructor */
	public JlHjRecRatingInfo(String callId, String userNo, String userName, String writeTxt,Timestamp createTime) {
		this.callId = callId;
		this.userNo = userNo;
		this.writeTxt = writeTxt;
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

	public String getWriteTxt() {
		return this.writeTxt;
	}

	public void setWriteTxt(String writeTxt) {
		this.writeTxt = writeTxt;
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
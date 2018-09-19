package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlHjMon entity. @author MyEclipse Persistence Tools
 */

public class JlHjMon implements java.io.Serializable {

	// Fields

	private Long webId;
	private String callId;
	private String userNo;
	private String userName;
	private String writeTxt;
	private String writeTxtLx;

	// Constructors

	/** default constructor */
	public JlHjMon() {
	}

	/** minimal constructor */
	public JlHjMon(String callId, String userNo) {
		this.callId = callId;
		this.userNo = userNo;
	}

	/** full constructor */
	public JlHjMon(String callId, String userNod, String userNo, String userName, String writeTxt, String writeTxtLx) {
		this.callId = callId;
		this.userNo = userNo;
		this.userName = userName;
		this.writeTxt = writeTxt;
		this.writeTxtLx = writeTxtLx;

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

	public String getWriteTxtLx() {
		return writeTxtLx;
	}

	public void setWriteTxtLx(String writeTxtLx) {
		this.writeTxtLx = writeTxtLx;
	}


}
package com.slxt.rs.model;

/**
 * JlHjInfo entity. @author MyEclipse Persistence Tools
 */

public class JlHjInfo implements java.io.Serializable {

	// Fields

	private Long webId;
	private String callId;
	private String userNo;
	private String writeTxt;
	private String userName;

	// Constructors

	/** default constructor */
	public JlHjInfo() {
	}

	/** minimal constructor */
	public JlHjInfo(String callId, String userNo) {
		this.callId = callId;
		this.userNo = userNo;
	}

	/** full constructor */
	public JlHjInfo(String callId, String userNo, String writeTxt, String userName) {
		this.callId = callId;
		this.userNo = userNo;
		this.writeTxt = writeTxt;
		this.userName = userName;
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

}
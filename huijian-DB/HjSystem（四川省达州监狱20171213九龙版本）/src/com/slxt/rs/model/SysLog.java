package com.slxt.rs.model;

/**
 * SysLog entity. @author MyEclipse Persistence Tools
 */

public class SysLog implements java.io.Serializable {

	// Fields

	private Long logId;
	private String type;
	private String model;
	private String op;
	private String info;
	private String userNo;
	private String userName;
	private String userIp;
	private String logTime;

	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(String type, String model, String op, String userNo,
			String logTime) {
		this.type = type;
		this.model = model;
		this.op = op;
		this.userNo = userNo;
		this.logTime = logTime;
	}

	/** full constructor */
	public SysLog(String type, String model, String op, String info,
			String userNo, String userName, String userIp, String logTime) {
		this.type = type;
		this.model = model;
		this.op = op;
		this.info = info;
		this.userNo = userNo;
		this.userName = userName;
		this.userIp = userIp;
		this.logTime = logTime;
	}

	// Property accessors

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOp() {
		return this.op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getLogTime() {
		return this.logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

}
package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlQs entity. @author MyEclipse Persistence Tools
 */

public class ErrorInfoWebService implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String actionName;
	private String errorInfo;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public ErrorInfoWebService() {
	}

	/** minimal constructor */
	public ErrorInfoWebService(String actionName) {
		this.actionName = actionName;
	}

	/** full constructor */
	public ErrorInfoWebService(String actionName, String errorInfo, Timestamp createTime) {
		this.actionName = actionName;
		this.errorInfo = errorInfo;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getWebId() {
		return this.webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	
}
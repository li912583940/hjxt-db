package com.slxt.rs.model;

/**
 * JlHjIcCard entity. @author MyEclipse Persistence Tools
 */

public class JlYjABDoor implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String yjNo;
	private String yjName;

	// Constructors

	/** default constructor */
	public JlYjABDoor() {
	}

	/** full constructor */
	public JlYjABDoor(String yjNo, String yjName) {
		this.yjNo = yjNo;
		this.yjName = yjName;
	}

	// Property accessors

	public Integer getWebId() {
		return this.webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getYjNo() {
		return yjNo;
	}

	public void setYjNo(String yjNo) {
		this.yjNo = yjNo;
	}

	public String getYjName() {
		return yjName;
	}

	public void setYjName(String yjName) {
		this.yjName = yjName;
	}

	
}
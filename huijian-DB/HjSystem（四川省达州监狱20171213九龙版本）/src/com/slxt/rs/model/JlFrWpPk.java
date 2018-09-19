package com.slxt.rs.model;

/**
 * JlFrWpPk entity. @author MyEclipse Persistence Tools
 */

public class JlFrWpPk implements java.io.Serializable {

	// Fields

	private Long webId;
	private Long hjid;
	private String wpNo;
	private Integer wpCount;

	// Constructors

	/** default constructor */
	public JlFrWpPk() {
	}

	/** full constructor */
	public JlFrWpPk(Long hjid, String wpNo, Integer wpCount) {
		this.hjid = hjid;
		this.wpNo = wpNo;
		this.wpCount = wpCount;
	}

	// Property accessors

	public Long getWebId() {
		return this.webId;
	}

	public void setWebId(Long webId) {
		this.webId = webId;
	}

	public Long getHjid() {
		return this.hjid;
	}

	public void setHjid(Long hjid) {
		this.hjid = hjid;
	}

	public String getWpNo() {
		return this.wpNo;
	}

	public void setWpNo(String wpNo) {
		this.wpNo = wpNo;
	}

	public Integer getWpCount() {
		return this.wpCount;
	}

	public void setWpCount(Integer wpCount) {
		this.wpCount = wpCount;
	}

}
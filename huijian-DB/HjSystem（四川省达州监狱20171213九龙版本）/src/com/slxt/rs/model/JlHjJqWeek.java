package com.slxt.rs.model;

/**
 * JlHjJqWeek entity. @author MyEclipse Persistence Tools
 */

public class JlHjJqWeek implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String jqNo;
	private String jy;
	private Integer jqWeek;

	// Constructors

	/** default constructor */
	public JlHjJqWeek() {
	}

	/** full constructor */
	public JlHjJqWeek(String jqNo, String jy, Integer jqWeek) {
		this.jqNo = jqNo;
		this.jy = jy;
		this.jqWeek = jqWeek;
	}

	// Property accessors

	public Integer getWebId() {
		return this.webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getJqNo() {
		return this.jqNo;
	}

	public void setJqNo(String jqNo) {
		this.jqNo = jqNo;
	}

	public String getJy() {
		return this.jy;
	}

	public void setJy(String jy) {
		this.jy = jy;
	}

	public Integer getJqWeek() {
		return this.jqWeek;
	}

	public void setJqWeek(Integer jqWeek) {
		this.jqWeek = jqWeek;
	}

}
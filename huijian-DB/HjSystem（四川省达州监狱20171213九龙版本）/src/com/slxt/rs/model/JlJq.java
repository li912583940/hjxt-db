package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlJq entity. @author MyEclipse Persistence Tools
 */

public class JlJq implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String jqNo;
	private String jy;
	private String jqName;
	private Integer isTs;
	private String pwd;
	private Integer useTimeLen;
	private Timestamp lastTime;

	// Constructors

	/** default constructor */
	public JlJq() {
	}

	/** minimal constructor */
	public JlJq(String jqNo, String jy, String jqName, Integer isTs) {
		this.jqNo = jqNo;
		this.jy = jy;
		this.jqName = jqName;
		this.isTs = isTs;
	}

	/** full constructor */
	public JlJq(String jqNo, String jy, String jqName, Integer isTs,
			String pwd, Integer useTimeLen, Timestamp lastTime) {
		this.jqNo = jqNo;
		this.jy = jy;
		this.jqName = jqName;
		this.isTs = isTs;
		this.pwd = pwd;
		this.useTimeLen = useTimeLen;
		this.lastTime = lastTime;
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

	public String getJqName() {
		return this.jqName;
	}

	public void setJqName(String jqName) {
		this.jqName = jqName;
	}

	public Integer getIsTs() {
		return this.isTs;
	}

	public void setIsTs(Integer isTs) {
		this.isTs = isTs;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getUseTimeLen() {
		return this.useTimeLen;
	}

	public void setUseTimeLen(Integer useTimeLen) {
		this.useTimeLen = useTimeLen;
	}

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

}
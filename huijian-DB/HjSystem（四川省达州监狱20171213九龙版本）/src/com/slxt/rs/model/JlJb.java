package com.slxt.rs.model;

/**
 * JlJb entity. @author MyEclipse Persistence Tools
 */

public class JlJb implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String jbNo;
	private String jbName;
	private Integer qqCount;
	private Integer qqTime;
	private Integer hjCount;
	private Integer hjTime;
	private Integer autoDown;
	private Integer downTime;
	private String downJb;
	private int hjQy;
	private int recordOverTime;
	// Constructors

	/** default constructor */
	public JlJb() {
	}

	/** minimal constructor */
	public JlJb(String jbNo, String jbName, Integer qqCount, Integer qqTime,
			Integer hjCount, Integer hjTime, Integer autoDown, Integer downTime) {
		this.jbNo = jbNo;
		this.jbName = jbName;
		this.qqCount = qqCount;
		this.qqTime = qqTime;
		this.hjCount = hjCount;
		this.hjTime = hjTime;
		this.autoDown = autoDown;
		this.downTime = downTime;
	}

	/** full constructor */
	public JlJb(String jbNo, String jbName, Integer qqCount, Integer qqTime,
			Integer hjCount, Integer hjTime, Integer autoDown,
			Integer downTime, String downJb,int recordOverTime) {
		this.jbNo = jbNo;
		this.jbName = jbName;
		this.qqCount = qqCount;
		this.qqTime = qqTime;
		this.hjCount = hjCount;
		this.hjTime = hjTime;
		this.autoDown = autoDown;
		this.downTime = downTime;
		this.downJb = downJb;
		this.recordOverTime = recordOverTime;
	}

	// Property accessors
	
	public Integer getWebId() {
		return this.webId;
	}

	public int getHjQy() {
		return hjQy;
	}

	public void setHjQy(int hjQy) {
		this.hjQy = hjQy;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getJbNo() {
		return this.jbNo;
	}

	public void setJbNo(String jbNo) {
		this.jbNo = jbNo;
	}

	public String getJbName() {
		return this.jbName;
	}

	public void setJbName(String jbName) {
		this.jbName = jbName;
	}

	public Integer getQqCount() {
		return this.qqCount;
	}

	public void setQqCount(Integer qqCount) {
		this.qqCount = qqCount;
	}

	public Integer getQqTime() {
		return this.qqTime;
	}

	public void setQqTime(Integer qqTime) {
		this.qqTime = qqTime;
	}

	public Integer getHjCount() {
		return this.hjCount;
	}

	public void setHjCount(Integer hjCount) {
		this.hjCount = hjCount;
	}

	public Integer getHjTime() {
		return this.hjTime;
	}

	public void setHjTime(Integer hjTime) {
		this.hjTime = hjTime;
	}

	public Integer getAutoDown() {
		return this.autoDown;
	}

	public void setAutoDown(Integer autoDown) {
		this.autoDown = autoDown;
	}

	public Integer getDownTime() {
		return this.downTime;
	}

	public void setDownTime(Integer downTime) {
		this.downTime = downTime;
	}

	public String getDownJb() {
		return this.downJb;
	}

	public void setDownJb(String downJb) {
		this.downJb = downJb;
	}

	public int getRecordOverTime() {
		return recordOverTime;
	}

	public void setRecordOverTime(int recordOverTime) {
		this.recordOverTime = recordOverTime;
	}

}
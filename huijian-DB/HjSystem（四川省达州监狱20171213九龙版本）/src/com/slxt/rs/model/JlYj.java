package com.slxt.rs.model;

/**
 * JlYj entity. @author MyEclipse Persistence Tools
 */

public class JlYj implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String yjNo;
	private String yjName;
	private String yjCard;
	private String jy;
	private String jq;
	private String teleUser;
	private String telePwd;
	private String yjNum;
	private String deptName;

	// Constructors

	/** default constructor */
	public JlYj() {
	}

	/** minimal constructor */
	public JlYj(String yjNo, String jy, String jq) {
		this.yjNo = yjNo;
		this.jy = jy;
		this.jq = jq;
	}

	/** full constructor */
	public JlYj(String yjNo, String yjName, String yjCard, String jy,
			String jq, String teleUser, String telePwd, String yjNum,
			String deptName) {
		this.yjNo = yjNo;
		this.yjName = yjName;
		this.yjCard = yjCard;
		this.jy = jy;
		this.jq = jq;
		this.teleUser = teleUser;
		this.telePwd = telePwd;
		this.yjNum = yjNum;
		this.deptName = deptName;
	}

	// Property accessors

	public Integer getWebId() {
		return this.webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getYjNo() {
		return this.yjNo;
	}

	public void setYjNo(String yjNo) {
		this.yjNo = yjNo;
	}

	public String getYjName() {
		return this.yjName;
	}

	public void setYjName(String yjName) {
		this.yjName = yjName;
	}

	public String getYjCard() {
		return this.yjCard;
	}

	public void setYjCard(String yjCard) {
		this.yjCard = yjCard;
	}

	public String getJy() {
		return this.jy;
	}

	public void setJy(String jy) {
		this.jy = jy;
	}

	public String getJq() {
		return this.jq;
	}

	public void setJq(String jq) {
		this.jq = jq;
	}

	public String getTeleUser() {
		return this.teleUser;
	}

	public void setTeleUser(String teleUser) {
		this.teleUser = teleUser;
	}

	public String getTelePwd() {
		return this.telePwd;
	}

	public void setTelePwd(String telePwd) {
		this.telePwd = telePwd;
	}

	public String getYjNum() {
		return this.yjNum;
	}

	public void setYjNum(String yjNum) {
		this.yjNum = yjNum;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
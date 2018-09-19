package com.slxt.rs.model;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */

public class SysUser implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String userNo;
	private String userPwd;
	private String userDepart;
	private String userName;
	private String groupNo;
	private Integer isSuper;
	private Integer isSp;
	private Integer isBj;
	
	
	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(String userNo, String userPwd, String groupNo,
			Integer isSuper) {
		this.userNo = userNo;
		this.userPwd = userPwd;
		this.groupNo = groupNo;
		this.isSuper = isSuper;
	}

	/** full constructor */
	public SysUser(String userNo, String userPwd, String userDepart,
			String userName, String groupNo, Integer isSuper, Integer isSp, Integer isBj) {
		this.userNo = userNo;
		this.userPwd = userPwd;
		this.userDepart = userDepart;
		this.userName = userName;
		this.groupNo = groupNo;
		this.isSuper = isSuper;
		this.isSp = isSp;
		this.isBj = isBj;
	}

	// Property accessors

	public Integer getWebId() {
		return this.webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserDepart() {
		return this.userDepart;
	}

	public void setUserDepart(String userDepart) {
		this.userDepart = userDepart;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public Integer getIsSuper() {
		return this.isSuper;
	}

	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}

	public Integer getIsSp() {
		return isSp;
	}

	public void setIsSp(Integer isSp) {
		this.isSp = isSp;
	}

	public Integer getIsBj() {
		return isBj;
	}

	public void setIsBj(Integer isBj) {
		this.isBj = isBj;
	}

}
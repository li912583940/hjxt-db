package com.slxt.rs.model;

/**
 * SysUserGroup entity. @author MyEclipse Persistence Tools
 */

public class SysUserGroup implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String groupNo;
	private String groupName;
	private Integer isAdmin;
	private String userDepart;
	private Integer isSpDept;
	private Integer spQxJb;
//	private Integer isSuper;
	// Constructors

	/** default constructor */
	public SysUserGroup() {
	}

	/** full constructor */
	public SysUserGroup(String groupNo, String groupName, Integer isAdmin,String userDepart,Integer isSpDept,Integer spQxJb) {
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.isAdmin = isAdmin;
		this.userDepart = userDepart;
		this.isSpDept = isSpDept;
		this.isAdmin = isAdmin;
//		this.isSuper = isSuper;
	}

	// Property accessors

	public Integer getWebId() {
		return this.webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUserDepart() {
		return userDepart;
	}

	public void setUserDepart(String userDepart) {
		this.userDepart = userDepart;
	}

	public Integer getIsSpDept() {
		return isSpDept;
	}

	public void setIsSpDept(Integer isSpDept) {
		this.isSpDept = isSpDept;
	}

	public Integer getSpQxJb() {
		return spQxJb;
	}

	public void setSpQxJb(Integer spQxJb) {
		this.spQxJb = spQxJb;
	}

//	public Integer getIsSuper() {
//		return isSuper;
//	}
//
//	public void setIsSuper(Integer isSuper) {
//		this.isSuper = isSuper;
//	}
//	
	
	
}
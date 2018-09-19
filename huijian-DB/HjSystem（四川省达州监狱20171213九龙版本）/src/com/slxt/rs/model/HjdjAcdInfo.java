package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * HjdjAcdInfo entity. @author MyEclipse Persistence Tools
 */

public class HjdjAcdInfo implements java.io.Serializable {

	// Fields

	private Integer acdindex;
	private Integer acdgetNo;
	private Timestamp acdgetTime;
	private Integer acdsetNo;
	private Timestamp acdsetTime;
	private Integer curDate;
	private Integer initNo;
	private Integer unitNo;
	private String serverName;
	private String acdurl;

	// Constructors

	/** default constructor */
	public HjdjAcdInfo() {
	}

	/** minimal constructor */
	public HjdjAcdInfo(Integer acdgetNo, Integer acdsetNo, Integer curDate,
			Integer initNo, Integer unitNo, String serverName) {
		this.acdgetNo = acdgetNo;
		this.acdsetNo = acdsetNo;
		this.curDate = curDate;
		this.initNo = initNo;
		this.unitNo = unitNo;
		this.serverName = serverName;
	}

	/** full constructor */
	public HjdjAcdInfo(Integer acdgetNo, Timestamp acdgetTime,
			Integer acdsetNo, Timestamp acdsetTime, Integer curDate,
			Integer initNo, Integer unitNo, String serverName, String acdurl) {
		this.acdgetNo = acdgetNo;
		this.acdgetTime = acdgetTime;
		this.acdsetNo = acdsetNo;
		this.acdsetTime = acdsetTime;
		this.curDate = curDate;
		this.initNo = initNo;
		this.unitNo = unitNo;
		this.serverName = serverName;
		this.acdurl = acdurl;
	}

	// Property accessors

	public Integer getAcdindex() {
		return this.acdindex;
	}

	public void setAcdindex(Integer acdindex) {
		this.acdindex = acdindex;
	}

	public Integer getAcdgetNo() {
		return this.acdgetNo;
	}

	public void setAcdgetNo(Integer acdgetNo) {
		this.acdgetNo = acdgetNo;
	}

	public Timestamp getAcdgetTime() {
		return this.acdgetTime;
	}

	public void setAcdgetTime(Timestamp acdgetTime) {
		this.acdgetTime = acdgetTime;
	}

	public Integer getAcdsetNo() {
		return this.acdsetNo;
	}

	public void setAcdsetNo(Integer acdsetNo) {
		this.acdsetNo = acdsetNo;
	}

	public Timestamp getAcdsetTime() {
		return this.acdsetTime;
	}

	public void setAcdsetTime(Timestamp acdsetTime) {
		this.acdsetTime = acdsetTime;
	}

	public Integer getCurDate() {
		return this.curDate;
	}

	public void setCurDate(Integer curDate) {
		this.curDate = curDate;
	}

	public Integer getInitNo() {
		return this.initNo;
	}

	public void setInitNo(Integer initNo) {
		this.initNo = initNo;
	}

	public Integer getUnitNo() {
		return this.unitNo;
	}

	public void setUnitNo(Integer unitNo) {
		this.unitNo = unitNo;
	}

	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getAcdurl() {
		return this.acdurl;
	}

	public void setAcdurl(String acdurl) {
		this.acdurl = acdurl;
	}

}
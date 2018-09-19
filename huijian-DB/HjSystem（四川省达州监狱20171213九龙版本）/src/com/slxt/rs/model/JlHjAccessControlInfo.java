package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * SysHjServer entity. @author MyEclipse Persistence Tools
 */

public class JlHjAccessControlInfo implements java.io.Serializable {

	// Fields

	private Long hjid;
	private String ip;
	private String kzqAddress;
	private String dianAddress;
	private String card;
	private Timestamp inTime;
	private Timestamp outTime;
	private Integer state;
	private Integer webId;

	// Constructors

	/** default constructor */
	public JlHjAccessControlInfo() {
	}

	/** minimal constructor */
	public JlHjAccessControlInfo(Long hjid, String ip, String kzqAddress,
			String dianAddress) {
		this.hjid = hjid;
		this.ip = ip;
		this.kzqAddress = kzqAddress;
		this.dianAddress = dianAddress;
	}

	/** full constructor */
	public JlHjAccessControlInfo(Long hjid, String ip, String kzqAddress,
			String dianAddress,String card,Timestamp inTime,Timestamp outTime,Integer state) {
		this.hjid = hjid;
		this.ip = ip;
		this.kzqAddress = kzqAddress;
		this.dianAddress = dianAddress;
		this.card = card;
		this.inTime = inTime;
		this.outTime = outTime;
		this.state = state;
	}

	public Long getHjid() {
		return hjid;
	}

	public void setHjid(Long hjid) {
		this.hjid = hjid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getKzqAddress() {
		return kzqAddress;
	}

	public void setKzqAddress(String kzqAddress) {
		this.kzqAddress = kzqAddress;
	}

	public String getDianAddress() {
		return dianAddress;
	}

	public void setDianAddress(String dianAddress) {
		this.dianAddress = dianAddress;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public Timestamp getInTime() {
		return inTime;
	}

	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}

	public Timestamp getOutTime() {
		return outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	// Property accessors

	

}
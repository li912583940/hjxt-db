package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlQs entity. @author MyEclipse Persistence Tools
 */

public class JlQs implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String frNo;
	private Integer qsZjlb;
	private String qsSfz;
	private String qsName;
	private String qsCard;
	private String gx;
	private String xb;
	private String dz;
	private String tele;
	private Integer sw;
	private byte[] zp;
	private byte[] jz;
	private Integer spState;
	private String spUserNo;
	private Timestamp spTime;
	private String spInfo;
	private String spMon;
	private Timestamp createTime;
	private byte[] faceZp;
	private Integer faceState;
	private Long faceId;
	private String qsSfzWlh;
	private String bz;
	private Timestamp hjStopTime;
	private String zpUrl;

	// Constructors

	/** default constructor */
	public JlQs() {
	}

	/** minimal constructor */
	public JlQs(String frNo, Integer spState) {
		this.frNo = frNo;
		this.spState = spState;
	}

	/** full constructor */
	public JlQs(String frNo, String qsSfz, String qsName, String qsCard,
			String gx, String xb, String dz, String tele, Integer sw,
			byte[] zp, byte[] jz, Integer spState, String spUserNo,
			Timestamp spTime, String spInfo, String spMon, Timestamp createTime,
			byte[] faceZp,Integer faceState,Long faceId,Integer qsZjlb,
			String qsSfzWlh,String bz,Timestamp hjStopTime, String zpUrl) {
		this.frNo = frNo;
		this.qsSfz = qsSfz;
		this.qsName = qsName;
		this.qsCard = qsCard;
		this.gx = gx;
		this.xb = xb;
		this.dz = dz;
		this.tele = tele;
		this.sw = sw;
		this.zp = zp;
		this.jz = jz;
		this.spState = spState;
		this.spUserNo = spUserNo;
		this.spTime = spTime;
		this.spInfo = spInfo;
		this.spMon = spMon;
		this.createTime = createTime;
		this.faceZp = faceZp;
		this.faceState = faceState;
		this.faceId = faceId;
		this.qsZjlb = qsZjlb;
		this.qsSfzWlh = qsSfzWlh;
		this.bz = bz;
		this.hjStopTime=hjStopTime;
		this.zpUrl = zpUrl;
	}

	// Property accessors

	public Integer getWebId() {
		return this.webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getFrNo() {
		return this.frNo;
	}

	public void setFrNo(String frNo) {
		this.frNo = frNo;
	}

	public String getQsSfz() {
		return this.qsSfz;
	}

	public void setQsSfz(String qsSfz) {
		this.qsSfz = qsSfz;
	}

	public String getQsName() {
		return this.qsName;
	}

	public void setQsName(String qsName) {
		this.qsName = qsName;
	}

	public String getQsCard() {
		return this.qsCard;
	}

	public void setQsCard(String qsCard) {
		this.qsCard = qsCard;
	}

	public String getGx() {
		return this.gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getDz() {
		return this.dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getTele() {
		return this.tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public Integer getSw() {
		return this.sw;
	}

	public void setSw(Integer sw) {
		this.sw = sw;
	}

	public byte[] getZp() {
		return this.zp;
	}

	public void setZp(byte[] zp) {
		this.zp = zp;
	}

	public byte[] getJz() {
		return this.jz;
	}

	public void setJz(byte[] jz) {
		this.jz = jz;
	}

	public Integer getSpState() {
		return this.spState;
	}

	public void setSpState(Integer spState) {
		this.spState = spState;
	}

	public String getSpUserNo() {
		return this.spUserNo;
	}

	public void setSpUserNo(String spUserNo) {
		this.spUserNo = spUserNo;
	}

	public Timestamp getSpTime() {
		return this.spTime;
	}

	public void setSpTime(Timestamp spTime) {
		this.spTime = spTime;
	}

	public String getSpInfo() {
		return this.spInfo;
	}

	public void setSpInfo(String spInfo) {
		this.spInfo = spInfo;
	}

	public String getSpMon() {
		return this.spMon;
	}

	public void setSpMon(String spMon) {
		this.spMon = spMon;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte[] getFaceZp() {
		return faceZp;
	}

	public void setFaceZp(byte[] faceZp) {
		this.faceZp = faceZp;
	}

	public Integer getFaceState() {
		return faceState;
	}

	public void setFaceState(Integer faceState) {
		this.faceState = faceState;
	}

	public Long getFaceId() {
		return faceId;
	}

	public void setFaceId(Long faceId) {
		this.faceId = faceId;
	}

	public Integer getQsZjlb() {
		return qsZjlb;
	}

	public void setQsZjlb(Integer qsZjlb) {
		this.qsZjlb = qsZjlb;
	}

	public String getQsSfzWlh() {
		return qsSfzWlh;
	}

	public void setQsSfzWlh(String qsSfzWlh) {
		this.qsSfzWlh = qsSfzWlh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Timestamp getHjStopTime() {
		return hjStopTime;
	}

	public void setHjStopTime(Timestamp hjStopTime) {
		this.hjStopTime = hjStopTime;
	}

	public String getZpUrl() {
		return zpUrl;
	}

	public void setZpUrl(String zpUrl) {
		this.zpUrl = zpUrl;
	}

}
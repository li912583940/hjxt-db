package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlQs entity. @author MyEclipse Persistence Tools
 */

public class JlHjDjQs implements java.io.Serializable {

	// Fields

	private Integer webId;
	private Long hjId;
	private Integer qsId;
	private String frNo;
	private Integer qsZjlb;
	private String qsSfz;
	private String qsName;
	private String qsCard;
	private String gx;
	private String xb;
	private String dz;
	private String tele;
	private byte[] zp;
	private byte[] jz;
	private String qsSfzWlh;
	

	// Constructors

	/** default constructor */
	public JlHjDjQs() {
	}

	/** minimal constructor */
	public JlHjDjQs(Long hjId, Integer qsId) {
		this.hjId = hjId;
		this.qsId = qsId;
	}
	
	/** full constructor */
	public JlHjDjQs(String frNo, String qsSfz, String qsName, String qsCard,
			String gx, String xb, String dz, String tele,
			byte[] zp, byte[] jz,Integer qsZjlb,String qsSfzWlh) {
		this.frNo = frNo;
		this.qsSfz = qsSfz;
		this.qsName = qsName;
		this.qsCard = qsCard;
		this.gx = gx;
		this.xb = xb;
		this.dz = dz;
		this.tele = tele;
		this.zp = zp;
		this.jz = jz;
		this.qsZjlb = qsZjlb;
		this.qsSfzWlh = qsSfzWlh;
	}
	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public Long getHjId() {
		return hjId;
	}

	public void setHjId(Long hjId) {
		this.hjId = hjId;
	}

	public Integer getQsId() {
		return qsId;
	}

	public void setQsId(Integer qsId) {
		this.qsId = qsId;
	}

	public String getFrNo() {
		return frNo;
	}

	public void setFrNo(String frNo) {
		this.frNo = frNo;
	}

	public Integer getQsZjlb() {
		return qsZjlb;
	}

	public void setQsZjlb(Integer qsZjlb) {
		this.qsZjlb = qsZjlb;
	}

	public String getQsSfz() {
		return qsSfz;
	}

	public void setQsSfz(String qsSfz) {
		this.qsSfz = qsSfz;
	}

	public String getQsName() {
		return qsName;
	}

	public void setQsName(String qsName) {
		this.qsName = qsName;
	}

	public String getQsCard() {
		return qsCard;
	}

	public void setQsCard(String qsCard) {
		this.qsCard = qsCard;
	}

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public byte[] getZp() {
		return zp;
	}

	public void setZp(byte[] zp) {
		this.zp = zp;
	}

	public byte[] getJz() {
		return jz;
	}

	public void setJz(byte[] jz) {
		this.jz = jz;
	}

	public String getQsSfzWlh() {
		return qsSfzWlh;
	}

	public void setQsSfzWlh(String qsSfzWlh) {
		this.qsSfzWlh = qsSfzWlh;
	}

	

}
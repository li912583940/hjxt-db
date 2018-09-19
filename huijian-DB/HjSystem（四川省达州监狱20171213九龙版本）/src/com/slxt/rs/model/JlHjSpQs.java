package com.slxt.rs.model;

import java.sql.Timestamp;

public class JlHjSpQs implements java.io.Serializable {
	private Integer spId;
	private String qsInfo;
	private String qsSfz;
	private Integer special;
	private String spRemarks;
	private String qsAnnex;
	private String qsXb;
	private byte[] qsZp;
	private String spReason;
	private String qsDZ;
	private Integer webId;
	private Integer faceState;
	private Long faceId;
	private Integer qsZjlb;
	private String spbz;
	
	public JlHjSpQs(){
		
	}
	
	public JlHjSpQs(String qsAnnex, String qsDZ, String qsInfo, String qsSfz,
			String qsXb, byte[] qsZp, Integer spId, String spReason,
			String spRemarks, Integer special,Integer faceState,Long faceId,Integer qsZjlb, String spbz) {
		super();
		this.qsAnnex = qsAnnex;
		this.qsDZ = qsDZ;
		this.qsInfo = qsInfo;
		this.qsSfz = qsSfz;
		this.qsXb = qsXb;
		this.qsZp = qsZp;
		this.spId = spId;
		this.spReason = spReason;
		this.spRemarks = spRemarks;
		this.special = special;
		this.faceState = faceState;
		this.faceId = faceId;
		this.qsZjlb = qsZjlb;
		this.spbz = spbz;
		
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public String getQsInfo() {
		return qsInfo;
	}
	public void setQsInfo(String qsInfo) {
		this.qsInfo = qsInfo;
	}
	public String getQsSfz() {
		return qsSfz;
	}
	public void setQsSfz(String qsSfz) {
		this.qsSfz = qsSfz;
	}
	public Integer getSpecial() {
		return special;
	}
	public void setSpecial(Integer special) {
		this.special = special;
	}
	public String getSpRemarks() {
		return spRemarks;
	}
	public void setSpRemarks(String spRemarks) {
		this.spRemarks = spRemarks;
	}
	public String getQsAnnex() {
		return qsAnnex;
	}
	public void setQsAnnex(String qsAnnex) {
		this.qsAnnex = qsAnnex;
	}
	public String getQsXb() {
		return qsXb;
	}
	public void setQsXb(String qsXb) {
		this.qsXb = qsXb;
	}
	public byte[] getQsZp() {
		return qsZp;
	}
	public void setQsZp(byte[] qsZp) {
		this.qsZp = qsZp;
	}
	public String getSpReason() {
		return spReason;
	}
	public void setSpReason(String spReason) {
		this.spReason = spReason;
	}
	public String getQsDZ() {
		return qsDZ;
	}
	public void setQsDZ(String qsDZ) {
		this.qsDZ = qsDZ;
	}
	public Integer getWebId() {
		return webId;
	}
	public void setWebId(Integer webId) {
		this.webId = webId;
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

	public String getSpbz() {
		return spbz;
	}

	public void setSpbz(String spbz) {
		this.spbz = spbz;
	}
	
	
}

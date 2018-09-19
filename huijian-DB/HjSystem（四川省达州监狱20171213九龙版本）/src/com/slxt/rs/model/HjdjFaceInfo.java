package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * HjdjAcdWindowsInfo entity. @author MyEclipse Persistence Tools
 */

public class HjdjFaceInfo implements java.io.Serializable {

	// Fields

	private Integer faceIndex;
	private String enrollFacePcIp;
	private String enrollFaceId;


	// Constructors

	/** default constructor */
	public HjdjFaceInfo() {
	}

	/** minimal constructor */
	public HjdjFaceInfo(String enrollFacePcIp) {
		this.enrollFacePcIp = enrollFacePcIp;
	}

	/** full constructor */
	public HjdjFaceInfo(String enrollFacePcIp, String enrollFaceId) {
		this.enrollFacePcIp = enrollFacePcIp;
		this.enrollFaceId = enrollFaceId;
	}

	
	public Integer getFaceIndex() {
		return faceIndex;
	}

	public void setFaceIndex(Integer faceIndex) {
		this.faceIndex = faceIndex;
	}

	public String getEnrollFacePcIp() {
		return enrollFacePcIp;
	}

	public void setEnrollFacePcIp(String enrollFacePcIp) {
		this.enrollFacePcIp = enrollFacePcIp;
	}

	public String getEnrollFaceId() {
		return enrollFaceId;
	}

	public void setEnrollFaceId(String enrollFaceId) {
		this.enrollFaceId = enrollFaceId;
	}

	// Property accessors

	

}
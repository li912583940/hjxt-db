package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * HjdjAcdShow entity. @author MyEclipse Persistence Tools
 */

public class HjdjAcdShow implements java.io.Serializable {

	// Fields

	private Long showId;
	private Integer showWindowsIndex;
	private Timestamp showTime;
	private Integer showFlag;
	private String showText;
	private String showVoc;

	// Constructors

	/** default constructor */
	public HjdjAcdShow() {
	}

	/** full constructor */
	public HjdjAcdShow(Integer showWindowsIndex, Timestamp showTime,
			Integer showFlag, String showText, String showVoc) {
		this.showWindowsIndex = showWindowsIndex;
		this.showTime = showTime;
		this.showFlag = showFlag;
		this.showText = showText;
		this.showVoc = showVoc;
	}

	// Property accessors

	public Long getShowId() {
		return this.showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Integer getShowWindowsIndex() {
		return this.showWindowsIndex;
	}

	public void setShowWindowsIndex(Integer showWindowsIndex) {
		this.showWindowsIndex = showWindowsIndex;
	}

	public Timestamp getShowTime() {
		return this.showTime;
	}

	public void setShowTime(Timestamp showTime) {
		this.showTime = showTime;
	}

	public Integer getShowFlag() {
		return this.showFlag;
	}

	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}

	public String getShowText() {
		return this.showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public String getShowVoc() {
		return this.showVoc;
	}

	public void setShowVoc(String showVoc) {
		this.showVoc = showVoc;
	}

}
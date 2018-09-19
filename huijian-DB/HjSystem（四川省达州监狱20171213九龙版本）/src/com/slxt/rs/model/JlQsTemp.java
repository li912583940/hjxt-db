package com.slxt.rs.model;

/**
 * JlQsTemp entity. @author MyEclipse Persistence Tools
 */

public class JlQsTemp implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String relCriNumb;
	private String unitCode;
	private String relName;
	private String relSex;
	private String relNation;
	private String relBirthday;
	private String relIdNumb;
	private String relAddr;
	private String relUnit;
	private String relTel;
	private String relPolity;
	private String relOccup;
	private String relSpecial;

	// Constructors

	/** default constructor */
	public JlQsTemp() {
	}

	/** full constructor */
	public JlQsTemp(String relCriNumb, String unitCode, String relName,
			String relSex, String relNation, String relBirthday,
			String relIdNumb, String relAddr, String relUnit, String relTel,
			String relPolity, String relOccup, String relSpecial) {
		this.relCriNumb = relCriNumb;
		this.unitCode = unitCode;
		this.relName = relName;
		this.relSex = relSex;
		this.relNation = relNation;
		this.relBirthday = relBirthday;
		this.relIdNumb = relIdNumb;
		this.relAddr = relAddr;
		this.relUnit = relUnit;
		this.relTel = relTel;
		this.relPolity = relPolity;
		this.relOccup = relOccup;
		this.relSpecial = relSpecial;
	}

	// Property accessors

	public Integer getWebId() {
		return this.webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getRelCriNumb() {
		return this.relCriNumb;
	}

	public void setRelCriNumb(String relCriNumb) {
		this.relCriNumb = relCriNumb;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getRelName() {
		return this.relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}

	public String getRelSex() {
		return this.relSex;
	}

	public void setRelSex(String relSex) {
		this.relSex = relSex;
	}

	public String getRelNation() {
		return this.relNation;
	}

	public void setRelNation(String relNation) {
		this.relNation = relNation;
	}

	public String getRelBirthday() {
		return this.relBirthday;
	}

	public void setRelBirthday(String relBirthday) {
		this.relBirthday = relBirthday;
	}

	public String getRelIdNumb() {
		return this.relIdNumb;
	}

	public void setRelIdNumb(String relIdNumb) {
		this.relIdNumb = relIdNumb;
	}

	public String getRelAddr() {
		return this.relAddr;
	}

	public void setRelAddr(String relAddr) {
		this.relAddr = relAddr;
	}

	public String getRelUnit() {
		return this.relUnit;
	}

	public void setRelUnit(String relUnit) {
		this.relUnit = relUnit;
	}

	public String getRelTel() {
		return this.relTel;
	}

	public void setRelTel(String relTel) {
		this.relTel = relTel;
	}

	public String getRelPolity() {
		return this.relPolity;
	}

	public void setRelPolity(String relPolity) {
		this.relPolity = relPolity;
	}

	public String getRelOccup() {
		return this.relOccup;
	}

	public void setRelOccup(String relOccup) {
		this.relOccup = relOccup;
	}

	public String getRelSpecial() {
		return this.relSpecial;
	}

	public void setRelSpecial(String relSpecial) {
		this.relSpecial = relSpecial;
	}

}
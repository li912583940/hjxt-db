package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlBakFr entity. @author MyEclipse Persistence Tools
 */

public class JlBakFr implements java.io.Serializable {

	// Fields

	private Integer webId;
	private String frNo;
	private String frName;
	private String frCard;
	private String jy;
	private String jq;
	private String jbNo;
	private Integer jbSetTime;
	private Integer jbSetType;
	private Integer qqJb;
	private Integer qqUse;
	private Integer qqLeft;
	private Integer qqYe;
	private String qqZh;
	private String qqMm;
	private Integer hjJb;
	private Integer hjUse;
	private Integer hjLeft;
	private Timestamp hjLastTime;
	private String monitorFlag;
	private Integer state;
	private Timestamp outTime;
	private Integer spState;
	private String spUserNo;
	private Timestamp spTime;
	private String spInfo;
	private String spMon;
	private String infoRjsj;
	private String infoJg;
	private String infoZm;
	private String infoXq;
	private String infoDq;
	private String infoZdzf;
	private String infoHome;
	private Byte[] zp;
	// Constructors

	public Byte[] getZp() {
		return zp;
	}

	public void setZp(Byte[] zp) {
		this.zp = zp;
	}

	/** default constructor */
	public JlBakFr() {
	}

	/** minimal constructor */
	public JlBakFr(String frNo, String jy, String jq, String jbNo,
			Integer qqJb, Integer qqUse, Integer qqLeft, Integer hjJb,
			Integer hjUse, Integer hjLeft, String monitorFlag, Integer state,
			Integer spState) {
		this.frNo = frNo;
		this.jy = jy;
		this.jq = jq;
		this.jbNo = jbNo;
		this.qqJb = qqJb;
		this.qqUse = qqUse;
		this.qqLeft = qqLeft;
		this.hjJb = hjJb;
		this.hjUse = hjUse;
		this.hjLeft = hjLeft;
		this.monitorFlag = monitorFlag;
		this.state = state;
		this.spState = spState;
	}

	/** full constructor */
	public JlBakFr(String frNo, String frName, String frCard, String jy,
			String jq, String jbNo, Integer jbSetTime, Integer jbSetType,
			Integer qqJb, Integer qqUse, Integer qqLeft, Integer qqYe,
			String qqZh, String qqMm, Integer hjJb, Integer hjUse,
			Integer hjLeft, Timestamp hjLastTime, String monitorFlag,
			Integer state, Timestamp outTime, Integer spState, String spUserNo,
			Timestamp spTime, String spInfo, String spMon, String infoRjsj,
			String infoJg, String infoZm, String infoXq, String infoDq,
			String infoZdzf, String infoHome) {
		this.frNo = frNo;
		this.frName = frName;
		this.frCard = frCard;
		this.jy = jy;
		this.jq = jq;
		this.jbNo = jbNo;
		this.jbSetTime = jbSetTime;
		this.jbSetType = jbSetType;
		this.qqJb = qqJb;
		this.qqUse = qqUse;
		this.qqLeft = qqLeft;
		this.qqYe = qqYe;
		this.qqZh = qqZh;
		this.qqMm = qqMm;
		this.hjJb = hjJb;
		this.hjUse = hjUse;
		this.hjLeft = hjLeft;
		this.hjLastTime = hjLastTime;
		this.monitorFlag = monitorFlag;
		this.state = state;
		this.outTime = outTime;
		this.spState = spState;
		this.spUserNo = spUserNo;
		this.spTime = spTime;
		this.spInfo = spInfo;
		this.spMon = spMon;
		this.infoRjsj = infoRjsj;
		this.infoJg = infoJg;
		this.infoZm = infoZm;
		this.infoXq = infoXq;
		this.infoDq = infoDq;
		this.infoZdzf = infoZdzf;
		this.infoHome = infoHome;
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

	public String getFrName() {
		return this.frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getFrCard() {
		return this.frCard;
	}

	public void setFrCard(String frCard) {
		this.frCard = frCard;
	}

	public String getJy() {
		return this.jy;
	}

	public void setJy(String jy) {
		this.jy = jy;
	}

	public String getJq() {
		return this.jq;
	}

	public void setJq(String jq) {
		this.jq = jq;
	}

	public String getJbNo() {
		return this.jbNo;
	}

	public void setJbNo(String jbNo) {
		this.jbNo = jbNo;
	}

	public Integer getJbSetTime() {
		return this.jbSetTime;
	}

	public void setJbSetTime(Integer jbSetTime) {
		this.jbSetTime = jbSetTime;
	}

	public Integer getJbSetType() {
		return this.jbSetType;
	}

	public void setJbSetType(Integer jbSetType) {
		this.jbSetType = jbSetType;
	}

	public Integer getQqJb() {
		return this.qqJb;
	}

	public void setQqJb(Integer qqJb) {
		this.qqJb = qqJb;
	}

	public Integer getQqUse() {
		return this.qqUse;
	}

	public void setQqUse(Integer qqUse) {
		this.qqUse = qqUse;
	}

	public Integer getQqLeft() {
		return this.qqLeft;
	}

	public void setQqLeft(Integer qqLeft) {
		this.qqLeft = qqLeft;
	}

	public Integer getQqYe() {
		return this.qqYe;
	}

	public void setQqYe(Integer qqYe) {
		this.qqYe = qqYe;
	}

	public String getQqZh() {
		return this.qqZh;
	}

	public void setQqZh(String qqZh) {
		this.qqZh = qqZh;
	}

	public String getQqMm() {
		return this.qqMm;
	}

	public void setQqMm(String qqMm) {
		this.qqMm = qqMm;
	}

	public Integer getHjJb() {
		return this.hjJb;
	}

	public void setHjJb(Integer hjJb) {
		this.hjJb = hjJb;
	}

	public Integer getHjUse() {
		return this.hjUse;
	}

	public void setHjUse(Integer hjUse) {
		this.hjUse = hjUse;
	}

	public Integer getHjLeft() {
		return this.hjLeft;
	}

	public void setHjLeft(Integer hjLeft) {
		this.hjLeft = hjLeft;
	}

	public Timestamp getHjLastTime() {
		return this.hjLastTime;
	}

	public void setHjLastTime(Timestamp hjLastTime) {
		this.hjLastTime = hjLastTime;
	}

	public String getMonitorFlag() {
		return this.monitorFlag;
	}

	public void setMonitorFlag(String monitorFlag) {
		this.monitorFlag = monitorFlag;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
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

	public String getInfoRjsj() {
		return this.infoRjsj;
	}

	public void setInfoRjsj(String infoRjsj) {
		this.infoRjsj = infoRjsj;
	}

	public String getInfoJg() {
		return this.infoJg;
	}

	public void setInfoJg(String infoJg) {
		this.infoJg = infoJg;
	}

	public String getInfoZm() {
		return this.infoZm;
	}

	public void setInfoZm(String infoZm) {
		this.infoZm = infoZm;
	}

	public String getInfoXq() {
		return this.infoXq;
	}

	public void setInfoXq(String infoXq) {
		this.infoXq = infoXq;
	}

	public String getInfoDq() {
		return this.infoDq;
	}

	public void setInfoDq(String infoDq) {
		this.infoDq = infoDq;
	}

	public String getInfoZdzf() {
		return this.infoZdzf;
	}

	public void setInfoZdzf(String infoZdzf) {
		this.infoZdzf = infoZdzf;
	}

	public String getInfoHome() {
		return this.infoHome;
	}

	public void setInfoHome(String infoHome) {
		this.infoHome = infoHome;
	}

}
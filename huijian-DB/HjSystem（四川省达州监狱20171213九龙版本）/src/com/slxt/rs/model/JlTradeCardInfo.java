package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlTradeCardInfo entity. @author MyEclipse Persistence Tools
 */

public class JlTradeCardInfo implements java.io.Serializable {

	// Fields

	private Integer webId;
	private Timestamp sj;
	private String cardNum;
	private String frNo;
	private String frName;
	private String qsName;
	private String opName;
	private String jqName;
	private Integer qsId;

	// Constructors

	/** default constructor */
	public JlTradeCardInfo() {
	}

	/** minimal constructor */
	public JlTradeCardInfo(Timestamp sj, String cardNum, String frNo,
			Integer qsId) {
		this.sj = sj;
		this.cardNum = cardNum;
		this.frNo = frNo;
		this.qsId = qsId;
	}

	/** full constructor */
	public JlTradeCardInfo(Timestamp sj, String cardNum, String frNo,
			String frName, String qsName, String opName, String jqName,
			Integer qsId) {
		this.sj = sj;
		this.cardNum = cardNum;
		this.frNo = frNo;
		this.frName = frName;
		this.qsName = qsName;
		this.opName = opName;
		this.jqName = jqName;
		this.qsId = qsId;
	}

	// Property accessors

	public Integer getWebId() {
		return this.webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public Timestamp getSj() {
		return this.sj;
	}

	public void setSj(Timestamp sj) {
		this.sj = sj;
	}

	public String getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
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

	public String getQsName() {
		return this.qsName;
	}

	public void setQsName(String qsName) {
		this.qsName = qsName;
	}

	public String getOpName() {
		return this.opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getJqName() {
		return this.jqName;
	}

	public void setJqName(String jqName) {
		this.jqName = jqName;
	}

	public Integer getQsId() {
		return this.qsId;
	}

	public void setQsId(Integer qsId) {
		this.qsId = qsId;
	}

}
package com.slxt.rs.model;

import java.sql.Timestamp;

/**
 * JlHjDj entity. @author MyEclipse Persistence Tools
 */

public class JlHjDj implements java.io.Serializable {

	// Fields

	private Long hjid;
	private String jy;
	private String jqNo;
	private String jqName;
	private String frNo;
	private String frName;
	private String qsInfo1;
	private String qsInfo2;
	private String qsInfo3;
	private String qsInfo4;
	private String qsInfo5;
	private String qsInfo6;
	private String qsInfo7;
	private String qsInfo8;
	private String qsInfo9;
	private Integer hjTime;
	private String hjInfo;
	private Integer hjType;
	private String monitorFlag;
	private Integer djType;
	private String djUser;
	private Timestamp djTime;
	private String yjNo;
	private String yjName;
	private String frInUser;
	private Timestamp frInTime;
	private String frOutUser;
	private Timestamp frOutTime;
	private String qsInUser;
	private Timestamp qsInTime;
	private String qsOutUser;
	private Timestamp qsOutTime;
	private Integer hjOrder;
	private Integer fpFlag;
	private Integer fpLineNo;
	private Timestamp fpTime;
	private Integer fpTzfrFlag;
	private Integer fpTzqsFlag;
	private Long hjIndex;
	private String tpInfo;
	private String spInfo;
	private Timestamp spTime;
	private String spUser;
	private Integer state;
	private String cancelInfo;
	private String callId;
	private Integer importFlag;
	private Timestamp importTime;
	private int infoWp;
	private String qsCard1;
	private String qsCard2;
	private String qsCard3;
	private String qsCard4;
	private String qsCard5;
	private String qsCard6;
	private String qsCard7;
	private String qsCard8;
	private String qsCard9;
	private byte[] qsZp1;
	private byte[] qsZp2;
	private byte[] qsZp3;
	private byte[] qsZp4;
	private byte[] qsZp5;
	private byte[] qsZp6;
	private byte[] qsZp7;
	private byte[] qsZp8;
	private byte[] qsZp9;
	private Timestamp wpDjTime;
	private String wpDjr;
	private Integer pageTzState;
	private Integer tpQsNum;
	private Integer qzSp;
	private String frDah;
	private String pageTzUserNo;
	private String pageTzUserName;
	private Timestamp pageTzTime;
	private Timestamp fpTimeFr;
	private Timestamp fpTimeQs;
	// Constructors

	

	/** default constructor */
	public JlHjDj() {
	}

	public String getQsCard1() {
		return qsCard1;
	}

	public void setQsCard1(String qsCard1) {
		this.qsCard1 = qsCard1;
	}

	public String getQsCard2() {
		return qsCard2;
	}

	public void setQsCard2(String qsCard2) {
		this.qsCard2 = qsCard2;
	}

	public String getQsCard3() {
		return qsCard3;
	}

	public void setQsCard3(String qsCard3) {
		this.qsCard3 = qsCard3;
	}

	public String getQsCard4() {
		return qsCard4;
	}

	public void setQsCard4(String qsCard4) {
		this.qsCard4 = qsCard4;
	}

	public String getQsCard5() {
		return qsCard5;
	}

	public void setQsCard5(String qsCard5) {
		this.qsCard5 = qsCard5;
	}

	public String getQsCard6() {
		return qsCard6;
	}

	public void setQsCard6(String qsCard6) {
		this.qsCard6 = qsCard6;
	}

	public String getQsCard7() {
		return qsCard7;
	}

	public void setQsCard7(String qsCard7) {
		this.qsCard7 = qsCard7;
	}

	public String getQsCard8() {
		return qsCard8;
	}

	public void setQsCard8(String qsCard8) {
		this.qsCard8 = qsCard8;
	}

	public String getQsCard9() {
		return qsCard9;
	}

	public void setQsCard9(String qsCard9) {
		this.qsCard9 = qsCard9;
	}

	public byte[] getQsZp1() {
		return qsZp1;
	}

	public void setQsZp1(byte[] qsZp1) {
		this.qsZp1 = qsZp1;
	}

	public byte[] getQsZp2() {
		return qsZp2;
	}

	public void setQsZp2(byte[] qsZp2) {
		this.qsZp2 = qsZp2;
	}

	public byte[] getQsZp3() {
		return qsZp3;
	}

	public void setQsZp3(byte[] qsZp3) {
		this.qsZp3 = qsZp3;
	}

	public byte[] getQsZp4() {
		return qsZp4;
	}

	public void setQsZp4(byte[] qsZp4) {
		this.qsZp4 = qsZp4;
	}

	public byte[] getQsZp5() {
		return qsZp5;
	}

	public void setQsZp5(byte[] qsZp5) {
		this.qsZp5 = qsZp5;
	}

	public byte[] getQsZp6() {
		return qsZp6;
	}

	public void setQsZp6(byte[] qsZp6) {
		this.qsZp6 = qsZp6;
	}

	public byte[] getQsZp7() {
		return qsZp7;
	}

	public void setQsZp7(byte[] qsZp7) {
		this.qsZp7 = qsZp7;
	}

	public byte[] getQsZp8() {
		return qsZp8;
	}

	public void setQsZp8(byte[] qsZp8) {
		this.qsZp8 = qsZp8;
	}

	public byte[] getQsZp9() {
		return qsZp9;
	}

	public void setQsZp9(byte[] qsZp9) {
		this.qsZp9 = qsZp9;
	}

	/** minimal constructor */
	public JlHjDj(String jy, String jqNo, String frNo, Integer hjTime,
			Integer hjType, Integer djType, String djUser, Timestamp djTime,
			Integer fpFlag, Integer fpTzfrFlag, Integer fpTzqsFlag,
			Integer state,String frDah,String pageTzUserNo,String pageTzUserName,Timestamp pageTzTime) {
			this.jy = jy;
			this.jqNo = jqNo;
			this.frNo = frNo;
			this.hjTime = hjTime;
			this.hjType = hjType;
			this.djType = djType;
			this.djUser = djUser;
			this.djTime = djTime;
			this.fpFlag = fpFlag;
			this.fpTzfrFlag = fpTzfrFlag;
			this.fpTzqsFlag = fpTzqsFlag;
			this.state = state;
			this.frDah = frDah;
			this.pageTzUserNo = pageTzUserNo;
			this.pageTzUserName = pageTzUserName;
			this.pageTzTime = pageTzTime;
	}

	/** full constructor */
	public JlHjDj(String jy, String jqNo, String jqName, String frNo,
			String frName, String qsInfo1, String qsInfo2, String qsInfo3,
			String qsInfo4, String qsInfo5, String qsInfo6, String qsInfo7,
			String qsInfo8, String qsInfo9, Integer hjTime, String hjInfo,
			Integer hjType, String monitorFlag, Integer djType, String djUser,
			Timestamp djTime, String yjNo, String yjName, String frInUser,
			Timestamp frInTime, String frOutUser, Timestamp frOutTime,
			String qsInUser, Timestamp qsInTime, String qsOutUser,
			Timestamp qsOutTime, Integer hjOrder, Integer fpFlag,
			Integer fpLineNo, Timestamp fpTime, Integer fpTzfrFlag,
			Integer fpTzqsFlag, Long hjIndex, String tpInfo, String spInfo,
			Timestamp spTime, String spUser, Integer state, String cancelInfo,
			String callId, Integer importFlag, Timestamp importTime,int infoWp,
			Integer tpQsNum,Integer qzSp,String frDah,String pageTzUserNo,String pageTzUserName,Timestamp pageTzTime,
			Timestamp fpTimeFr,Timestamp fpTimeQs) {
		this.jy = jy;
		this.jqNo = jqNo;
		this.jqName = jqName;
		this.frNo = frNo;
		this.frName = frName;
		this.qsInfo1 = qsInfo1;
		this.qsInfo2 = qsInfo2;
		this.qsInfo3 = qsInfo3;
		this.qsInfo4 = qsInfo4;
		this.qsInfo5 = qsInfo5;
		this.qsInfo6 = qsInfo6;
		this.qsInfo7 = qsInfo7;
		this.qsInfo8 = qsInfo8;
		this.qsInfo9 = qsInfo9;
		this.hjTime = hjTime;
		this.hjInfo = hjInfo;
		this.hjType = hjType;
		this.monitorFlag = monitorFlag;
		this.djType = djType;
		this.djUser = djUser;
		this.djTime = djTime;
		this.yjNo = yjNo;
		this.yjName = yjName;
		this.frInUser = frInUser;
		this.frInTime = frInTime;
		this.frOutUser = frOutUser;
		this.frOutTime = frOutTime;
		this.qsInUser = qsInUser;
		this.qsInTime = qsInTime;
		this.qsOutUser = qsOutUser;
		this.qsOutTime = qsOutTime;
		this.hjOrder = hjOrder;
		this.fpFlag = fpFlag;
		this.fpLineNo = fpLineNo;
		this.fpTime = fpTime;
		this.fpTzfrFlag = fpTzfrFlag;
		this.fpTzqsFlag = fpTzqsFlag;
		this.hjIndex = hjIndex;
		this.tpInfo = tpInfo;
		this.spInfo = spInfo;
		this.spTime = spTime;
		this.spUser = spUser;
		this.state = state;
		this.cancelInfo = cancelInfo;
		this.callId = callId;
		this.importFlag = importFlag;
		this.importTime = importTime;
		this.infoWp=infoWp;
		this.tpQsNum=tpQsNum;
		this.qzSp=qzSp;
		this.frDah=frDah;
		this.pageTzUserNo = pageTzUserNo;
		this.pageTzUserName = pageTzUserName;
		this.pageTzTime = pageTzTime;
		this.fpTimeFr = fpTimeFr;
		this.fpTimeQs = fpTimeQs;
	}

	// Property accessors

	public Long getHjid() {
		return this.hjid;
	}

	public void setHjid(Long hjid) {
		this.hjid = hjid;
	}

	public String getJy() {
		return this.jy;
	}

	public void setJy(String jy) {
		this.jy = jy;
	}

	public String getJqNo() {
		return this.jqNo;
	}

	public void setJqNo(String jqNo) {
		this.jqNo = jqNo;
	}

	public String getJqName() {
		return this.jqName;
	}

	public void setJqName(String jqName) {
		this.jqName = jqName;
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

	public String getQsInfo1() {
		return this.qsInfo1;
	}

	public void setQsInfo1(String qsInfo1) {
		this.qsInfo1 = qsInfo1;
	}

	public String getQsInfo2() {
		return this.qsInfo2;
	}

	public void setQsInfo2(String qsInfo2) {
		this.qsInfo2 = qsInfo2;
	}

	public String getQsInfo3() {
		return this.qsInfo3;
	}

	public void setQsInfo3(String qsInfo3) {
		this.qsInfo3 = qsInfo3;
	}

	public String getQsInfo4() {
		return this.qsInfo4;
	}

	public void setQsInfo4(String qsInfo4) {
		this.qsInfo4 = qsInfo4;
	}

	public String getQsInfo5() {
		return this.qsInfo5;
	}

	public void setQsInfo5(String qsInfo5) {
		this.qsInfo5 = qsInfo5;
	}

	public String getQsInfo6() {
		return this.qsInfo6;
	}

	public void setQsInfo6(String qsInfo6) {
		this.qsInfo6 = qsInfo6;
	}

	public String getQsInfo7() {
		return this.qsInfo7;
	}

	public void setQsInfo7(String qsInfo7) {
		this.qsInfo7 = qsInfo7;
	}

	public String getQsInfo8() {
		return this.qsInfo8;
	}

	public void setQsInfo8(String qsInfo8) {
		this.qsInfo8 = qsInfo8;
	}

	public String getQsInfo9() {
		return this.qsInfo9;
	}

	public void setQsInfo9(String qsInfo9) {
		this.qsInfo9 = qsInfo9;
	}

	public Integer getHjTime() {
		return this.hjTime;
	}

	public void setHjTime(Integer hjTime) {
		this.hjTime = hjTime;
	}

	public String getHjInfo() {
		return this.hjInfo;
	}

	public void setHjInfo(String hjInfo) {
		this.hjInfo = hjInfo;
	}

	public Integer getHjType() {
		return this.hjType;
	}

	public void setHjType(Integer hjType) {
		this.hjType = hjType;
	}

	public String getMonitorFlag() {
		return this.monitorFlag;
	}

	public void setMonitorFlag(String monitorFlag) {
		this.monitorFlag = monitorFlag;
	}

	public Integer getDjType() {
		return this.djType;
	}

	public void setDjType(Integer djType) {
		this.djType = djType;
	}

	public String getDjUser() {
		return this.djUser;
	}

	public void setDjUser(String djUser) {
		this.djUser = djUser;
	}

	public Timestamp getDjTime() {
		return this.djTime;
	}

	public void setDjTime(Timestamp djTime) {
		this.djTime = djTime;
	}

	public String getYjNo() {
		return this.yjNo;
	}

	public void setYjNo(String yjNo) {
		this.yjNo = yjNo;
	}

	public String getYjName() {
		return this.yjName;
	}

	public void setYjName(String yjName) {
		this.yjName = yjName;
	}

	public String getFrInUser() {
		return this.frInUser;
	}

	public void setFrInUser(String frInUser) {
		this.frInUser = frInUser;
	}

	public Timestamp getFrInTime() {
		return this.frInTime;
	}

	public void setFrInTime(Timestamp frInTime) {
		this.frInTime = frInTime;
	}

	public String getFrOutUser() {
		return this.frOutUser;
	}

	public void setFrOutUser(String frOutUser) {
		this.frOutUser = frOutUser;
	}

	public Timestamp getFrOutTime() {
		return this.frOutTime;
	}

	public void setFrOutTime(Timestamp frOutTime) {
		this.frOutTime = frOutTime;
	}

	public String getQsInUser() {
		return this.qsInUser;
	}

	public void setQsInUser(String qsInUser) {
		this.qsInUser = qsInUser;
	}

	public Timestamp getQsInTime() {
		return this.qsInTime;
	}

	public void setQsInTime(Timestamp qsInTime) {
		this.qsInTime = qsInTime;
	}

	public String getQsOutUser() {
		return this.qsOutUser;
	}

	public void setQsOutUser(String qsOutUser) {
		this.qsOutUser = qsOutUser;
	}

	public Timestamp getQsOutTime() {
		return this.qsOutTime;
	}

	public void setQsOutTime(Timestamp qsOutTime) {
		this.qsOutTime = qsOutTime;
	}

	public Integer getHjOrder() {
		return this.hjOrder;
	}

	public void setHjOrder(Integer hjOrder) {
		this.hjOrder = hjOrder;
	}

	public Integer getFpFlag() {
		return this.fpFlag;
	}

	public void setFpFlag(Integer fpFlag) {
		this.fpFlag = fpFlag;
	}

	public Integer getFpLineNo() {
		return this.fpLineNo;
	}

	public void setFpLineNo(Integer fpLineNo) {
		this.fpLineNo = fpLineNo;
	}

	public Timestamp getFpTime() {
		return this.fpTime;
	}

	public void setFpTime(Timestamp fpTime) {
		this.fpTime = fpTime;
	}

	public Integer getFpTzfrFlag() {
		return this.fpTzfrFlag;
	}

	public void setFpTzfrFlag(Integer fpTzfrFlag) {
		this.fpTzfrFlag = fpTzfrFlag;
	}

	public Integer getFpTzqsFlag() {
		return this.fpTzqsFlag;
	}

	public void setFpTzqsFlag(Integer fpTzqsFlag) {
		this.fpTzqsFlag = fpTzqsFlag;
	}

	public Long getHjIndex() {
		return this.hjIndex;
	}

	public void setHjIndex(Long hjIndex) {
		this.hjIndex = hjIndex;
	}

	public String getTpInfo() {
		return this.tpInfo;
	}

	public void setTpInfo(String tpInfo) {
		this.tpInfo = tpInfo;
	}

	public String getSpInfo() {
		return this.spInfo;
	}

	public void setSpInfo(String spInfo) {
		this.spInfo = spInfo;
	}

	public Timestamp getSpTime() {
		return this.spTime;
	}

	public void setSpTime(Timestamp spTime) {
		this.spTime = spTime;
	}

	public String getSpUser() {
		return this.spUser;
	}

	public void setSpUser(String spUser) {
		this.spUser = spUser;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCancelInfo() {
		return this.cancelInfo;
	}

	public void setCancelInfo(String cancelInfo) {
		this.cancelInfo = cancelInfo;
	}

	public String getCallId() {
		return this.callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public Integer getImportFlag() {
		return this.importFlag;
	}

	public void setImportFlag(Integer importFlag) {
		this.importFlag = importFlag;
	}

	public Timestamp getImportTime() {
		return this.importTime;
	}

	public void setImportTime(Timestamp importTime) {
		this.importTime = importTime;
	}

	public int getInfoWp() {
		return infoWp;
	}

	public void setInfoWp(int infoWp) {
		this.infoWp = infoWp;
	}

	public Timestamp getWpDjTime() {
		return wpDjTime;
	}

	public void setWpDjTime(Timestamp wpDjTime) {
		this.wpDjTime = wpDjTime;
	}

	public String getWpDjr() {
		return wpDjr;
	}

	public void setWpDjr(String wpDjr) {
		this.wpDjr = wpDjr;
	}

	public Integer getPageTzState() {
		return pageTzState;
	}

	public void setPageTzState(Integer pageTzState) {
		this.pageTzState = pageTzState;
	}

	public Integer getTpQsNum() {
		return tpQsNum;
	}

	public void setTpQsNum(Integer tpQsNum) {
		this.tpQsNum = tpQsNum;
	}

	public Integer getQzSp() {
		return qzSp;
	}

	public void setQzSp(Integer qzSp) {
		this.qzSp = qzSp;
	}
	public String getFrDah() {
		return frDah;
	}

	public void setFrDah(String frDah) {
		this.frDah = frDah;
	}

	public String getPageTzUserNo() {
		return pageTzUserNo;
	}

	public void setPageTzUserNo(String pageTzUserNo) {
		this.pageTzUserNo = pageTzUserNo;
	}

	public String getPageTzUserName() {
		return pageTzUserName;
	}

	public void setPageTzUserName(String pageTzUserName) {
		this.pageTzUserName = pageTzUserName;
	}

	public Timestamp getPageTzTime() {
		return pageTzTime;
	}

	public void setPageTzTime(Timestamp pageTzTime) {
		this.pageTzTime = pageTzTime;
	}

	public Timestamp getFpTimeFr() {
		return fpTimeFr;
	}

	public void setFpTimeFr(Timestamp fpTimeFr) {
		this.fpTimeFr = fpTimeFr;
	}

	public Timestamp getFpTimeQs() {
		return fpTimeQs;
	}

	public void setFpTimeQs(Timestamp fpTimeQs) {
		this.fpTimeQs = fpTimeQs;
	}

}
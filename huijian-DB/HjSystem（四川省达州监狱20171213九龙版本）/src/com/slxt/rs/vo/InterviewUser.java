package com.slxt.rs.vo;

public class InterviewUser {
	private String CardNo;//身份证号，必要字段
	private String UserName;//用户名称，必要字段
	private String Reason;//探视原因
	private String StartDate;//有效期开始，在有效期内，可以发卡并进入AB门系统。必要字段
	private String EndDate;//有效期结束，必要字段
	private String CardAddress;//人员地址
	private String CardType;//卡类型，必要字段
	private String Gender;//性别，必要字段
	private String NationNames;//民族
	private String ApprovedNo;//审批单号
	private String AccompanyBewrite;//陪同干警，陪同干警内容是：多个警用代号隔开，必要字段
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public String getCardAddress() {
		return CardAddress;
	}
	public void setCardAddress(String cardAddress) {
		CardAddress = cardAddress;
	}
	public String getCardType() {
		return CardType;
	}
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getNationNames() {
		return NationNames;
	}
	public void setNationNames(String nationNames) {
		NationNames = nationNames;
	}
	public String getApprovedNo() {
		return ApprovedNo;
	}
	public void setApprovedNo(String approvedNo) {
		ApprovedNo = approvedNo;
	}
	public String getAccompanyBewrite() {
		return AccompanyBewrite;
	}
	public void setAccompanyBewrite(String accompanyBewrite) {
		AccompanyBewrite = accompanyBewrite;
	}
	
	
	
}

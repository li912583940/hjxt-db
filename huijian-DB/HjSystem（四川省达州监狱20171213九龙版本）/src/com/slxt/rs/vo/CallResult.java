package com.slxt.rs.vo;

import java.util.List;

public class CallResult {
	private Integer acdNo;
	private Integer acdCount;
	private List<String> list;
	private List<String> repeatList;
	private String showId;
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public List<String> getRepeatList() {
		return repeatList;
	}
	public void setRepeatList(List<String> repeatList) {
		this.repeatList = repeatList;
	}
	public Integer getAcdNo() {
		return acdNo;
	}
	public void setAcdNo(Integer acdNo) {
		this.acdNo = acdNo;
	}
	public Integer getAcdCount() {
		return acdCount;
	}
	public void setAcdCount(Integer acdCount) {
		this.acdCount = acdCount;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	 
	 
}

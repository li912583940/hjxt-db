package com.slxt.rs.vo;

import java.util.ArrayList;
import java.util.List;

public class SfyzVO3 {
	private List<SfyzVO2> list=new ArrayList<SfyzVO2>();
	private String inCount;
	private String outCount;
	private String leftCount;
	private String state;
	public List<SfyzVO2> getList() {
		return list;
	}
	public void setList(List<SfyzVO2> list) {
		this.list = list;
	}
	public String getInCount() {
		return inCount;
	}
	public void setInCount(String inCount) {
		this.inCount = inCount;
	}
	public String getOutCount() {
		return outCount;
	}
	public void setOutCount(String outCount) {
		this.outCount = outCount;
	}
	public String getLeftCount() {
		return leftCount;
	}
	public void setLeftCount(String leftCount) {
		this.leftCount = leftCount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}	

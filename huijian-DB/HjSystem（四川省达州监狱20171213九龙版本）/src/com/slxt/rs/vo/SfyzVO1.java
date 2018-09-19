package com.slxt.rs.vo;

import java.util.ArrayList;
import java.util.List;

import com.slxt.rs.model.JlQs;

public class SfyzVO1 {
	private JlQs jlQs;
	private String jzBase64;
	private String zpBase64;
	private List<SfyzVO> list=new ArrayList<SfyzVO>();
	private String inCount;
	private String outCount;
	private String leftCount;
	private String state;
	
	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
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


	public String getJzBase64() {
		return jzBase64;
	}


	public void setJzBase64(String jzBase64) {
		this.jzBase64 = jzBase64;
	}


	public String getZpBase64() {
		return zpBase64;
	}


	public void setZpBase64(String zpBase64) {
		this.zpBase64 = zpBase64;
	}


	public JlQs getJlQs() {
		return jlQs;
	}


	public void setJlQs(JlQs jlQs) {
		this.jlQs = jlQs;
	}


	public List<SfyzVO> getList() {
		return list;
	}


	public void setList(List<SfyzVO> list) {
		this.list = list;
	} 
	
}

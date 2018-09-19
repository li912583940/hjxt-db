package com.slxt.rs.vo;

import java.util.List;

import com.slxt.rs.model.JlQs;

public class QueryQs {
	private List<FrQs> list;
	private int hjTime;
	private String enter;
	public List<FrQs> getList() {
		return list;
	}
	public void setList(List<FrQs> list) {
		this.list = list;
	}
	public int getHjTime() {
		return hjTime;
	}
	public void setHjTime(int hjTime) {
		this.hjTime = hjTime;
	}
	public String getEnter() {
		return enter;
	}
	public void setEnter(String enter) {
		this.enter = enter;
	}
	
}

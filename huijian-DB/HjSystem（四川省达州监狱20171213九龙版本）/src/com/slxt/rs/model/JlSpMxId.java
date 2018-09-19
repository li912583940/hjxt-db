package com.slxt.rs.model;

/**
 * JlSpMxId entity. @author MyEclipse Persistence Tools
 */

public class JlSpMxId implements java.io.Serializable {

	// Fields

	private String sqid;
	private Integer xh;

	// Constructors

	/** default constructor */
	public JlSpMxId() {
	}

	/** full constructor */
	public JlSpMxId(String sqid, Integer xh) {
		this.sqid = sqid;
		this.xh = xh;
	}

	// Property accessors

	public String getSqid() {
		return this.sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public Integer getXh() {
		return this.xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof JlSpMxId))
			return false;
		JlSpMxId castOther = (JlSpMxId) other;

		return ((this.getSqid() == castOther.getSqid()) || (this.getSqid() != null
				&& castOther.getSqid() != null && this.getSqid().equals(
				castOther.getSqid())))
				&& ((this.getXh() == castOther.getXh()) || (this.getXh() != null
						&& castOther.getXh() != null && this.getXh().equals(
						castOther.getXh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSqid() == null ? 0 : this.getSqid().hashCode());
		result = 37 * result + (getXh() == null ? 0 : this.getXh().hashCode());
		return result;
	}

}
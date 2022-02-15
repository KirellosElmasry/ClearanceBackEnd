package com.church.clearance.model;

import java.io.Serializable;

public class PreviousChildren implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String childName;
	
	private Integer childAge;
	
	private String baptism;
	
	private String refNo;
	
	private Integer userId;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public Integer getChildAge() {
		return childAge;
	}

	public void setChildAge(Integer childAge) {
		this.childAge = childAge;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBaptism() {
		return baptism;
	}

	public void setBaptism(String baptism) {
		this.baptism = baptism;
	}
	
	

}

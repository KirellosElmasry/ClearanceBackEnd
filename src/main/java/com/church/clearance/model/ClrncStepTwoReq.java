package com.church.clearance.model;

import java.io.Serializable;

public class ClrncStepTwoReq implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	private String refNo;
	
	private String isPreviousEngagement;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getIsPreviousEngagement() {
		return isPreviousEngagement;
	}

	public void setIsPreviousEngagement(String isPreviousEngagement) {
		this.isPreviousEngagement = isPreviousEngagement;
	}
	
	

}

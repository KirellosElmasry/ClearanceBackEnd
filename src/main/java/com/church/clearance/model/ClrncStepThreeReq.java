package com.church.clearance.model;

import java.io.Serializable;

public class ClrncStepThreeReq implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Integer userId;
	
	private String refNo;
	
	private String isPreviousMarrage;

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

	public String getIsPreviousMarrage() {
		return isPreviousMarrage;
	}

	public void setIsPreviousMarrage(String isPreviousMarrage) {
		this.isPreviousMarrage = isPreviousMarrage;
	}
	
	
	

}

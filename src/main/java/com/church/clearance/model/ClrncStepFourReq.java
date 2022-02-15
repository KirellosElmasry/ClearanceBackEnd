package com.church.clearance.model;

import java.io.Serializable;

public class ClrncStepFourReq implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Integer userId;
	
	private String refNo;
	
	private String isPreviousChild;

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

	public String getIsPreviousChild() {
		return isPreviousChild;
	}

	public void setIsPreviousChild(String isPreviousChild) {
		this.isPreviousChild = isPreviousChild;
	}
	
	


}

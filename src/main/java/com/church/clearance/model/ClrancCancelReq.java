package com.church.clearance.model;

import java.io.Serializable;

public class ClrancCancelReq implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 private Integer userId;
		
	 private String refNo;
		
	 private String originalClearance;
	 
	 private String engClearance;
	 
	 private String annulClearance;

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

	public String getOriginalClearance() {
		return originalClearance;
	}

	public void setOriginalClearance(String originalClearance) {
		this.originalClearance = originalClearance;
	}

	public String getEngClearance() {
		return engClearance;
	}

	public void setEngClearance(String engClearance) {
		this.engClearance = engClearance;
	}

	public String getAnnulClearance() {
		return annulClearance;
	}

	public void setAnnulClearance(String annulClearance) {
		this.annulClearance = annulClearance;
	}
	 
	 

}

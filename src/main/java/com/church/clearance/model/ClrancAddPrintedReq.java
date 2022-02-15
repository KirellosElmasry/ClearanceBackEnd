package com.church.clearance.model;

import java.io.Serializable;

public class ClrancAddPrintedReq implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Integer userId;
	
	private String refNo;
	
	private String printedClearance;

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

	public String getPrintedClearance() {
		return printedClearance;
	}

	public void setPrintedClearance(String printedClearance) {
		this.printedClearance = printedClearance;
	}
	
	

}

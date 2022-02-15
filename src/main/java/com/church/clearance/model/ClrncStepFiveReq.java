package com.church.clearance.model;

import java.io.Serializable;
import java.util.Date;

public class ClrncStepFiveReq implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Integer userId;
	
	private String refNo;
	
	private String socialStatus;
	
	private String sourceOfPermitMarriage;
	
	private Date dateOfPermitMarriage;
	
	private Integer numOfChild;

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

	public String getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(String socialStatus) {
		this.socialStatus = socialStatus;
	}

	public String getSourceOfPermitMarriage() {
		return sourceOfPermitMarriage;
	}

	public void setSourceOfPermitMarriage(String sourceOfPermitMarriage) {
		this.sourceOfPermitMarriage = sourceOfPermitMarriage;
	}

	public Date getDateOfPermitMarriage() {
		return dateOfPermitMarriage;
	}

	public void setDateOfPermitMarriage(Date dateOfPermitMarriage) {
		this.dateOfPermitMarriage = dateOfPermitMarriage;
	}

	public Integer getNumOfChild() {
		return numOfChild;
	}

	public void setNumOfChild(Integer numOfChild) {
		this.numOfChild = numOfChild;
	}
	
	

}

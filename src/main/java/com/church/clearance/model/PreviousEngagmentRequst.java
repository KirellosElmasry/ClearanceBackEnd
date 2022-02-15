package com.church.clearance.model;

import java.io.Serializable;
import java.util.Date;

public class PreviousEngagmentRequst implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Date engagmentDate;
	
	private String engagmentPlace;
	
	private String priestFather;
	
	private String status;
	
	private String engagmentFileAttachment;
	
	private String annulmentEngagmentAttachmnet;
	
	private String refNo;
	
	private Integer userId;

	public Date getEngagmentDate() {
		return engagmentDate;
	}

	public void setEngagmentDate(Date engagmentDate) {
		this.engagmentDate = engagmentDate;
	}

	public String getEngagmentPlace() {
		return engagmentPlace;
	}

	public void setEngagmentPlace(String engagmentPlace) {
		this.engagmentPlace = engagmentPlace;
	}

	public String getPriestFather() {
		return priestFather;
	}

	public void setPriestFather(String priestFather) {
		this.priestFather = priestFather;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEngagmentFileAttachment() {
		return engagmentFileAttachment;
	}

	public void setEngagmentFileAttachment(String engagmentFileAttachment) {
		this.engagmentFileAttachment = engagmentFileAttachment;
	}

	public String getAnnulmentEngagmentAttachmnet() {
		return annulmentEngagmentAttachmnet;
	}

	public void setAnnulmentEngagmentAttachmnet(String annulmentEngagmentAttachmnet) {
		this.annulmentEngagmentAttachmnet = annulmentEngagmentAttachmnet;
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
	
	

}

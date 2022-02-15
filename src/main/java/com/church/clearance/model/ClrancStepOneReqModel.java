package com.church.clearance.model;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.church.clearance.entities.PersonalData;


public class ClrancStepOneReqModel implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	private String address;
	
	private String job;
	
	private String jobAddress;
	
	private String militaryService;
	
	private String fromChurch;
	
	private String recognitionRegularityRate;
	
	private String intakeRate;
	
	private String fatherOfConfession;
	
	private String Status;
	
    private String gender;
	
	private Integer churchId;
	
	private Integer userId;
	
	private String emirateId;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	public String getMilitaryService() {
		return militaryService;
	}

	public void setMilitaryService(String militaryService) {
		this.militaryService = militaryService;
	}

	public String getFromChurch() {
		return fromChurch;
	}

	public void setFromChurch(String fromChurch) {
		this.fromChurch = fromChurch;
	}


	public String getRecognitionRegularityRate() {
		return recognitionRegularityRate;
	}

	public void setRecognitionRegularityRate(String recognitionRegularityRate) {
		this.recognitionRegularityRate = recognitionRegularityRate;
	}

	public String getIntakeRate() {
		return intakeRate;
	}

	public void setIntakeRate(String intakeRate) {
		this.intakeRate = intakeRate;
	}

	public String getFatherOfConfession() {
		return fatherOfConfession;
	}

	public void setFatherOfConfession(String fatherOfConfession) {
		this.fatherOfConfession = fatherOfConfession;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getChurchId() {
		return churchId;
	}

	public void setChurchId(Integer churchId) {
		this.churchId = churchId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmirateId() {
		return emirateId;
	}

	public void setEmirateId(String emirateId) {
		this.emirateId = emirateId;
	}
	
	
	

}

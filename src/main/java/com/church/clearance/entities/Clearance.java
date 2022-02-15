package com.church.clearance.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="clearance")
@NamedQuery(name="Clearance.findAll", query="SELECT c FROM Clearance c")
public class Clearance implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ref_no")
	private String refNo;
	
	@Column(name="address")
	private String address;
	
	@Column(name="job")
	private String job;
	
	@Column(name="job_address")
	private String jobAddress;
	
	@Column(name="military_service")
	private String militaryService;
	
	@Column(name="from_church")
	private String fromChurch;
	
	@Column(name="is_previous_engagement")
	private String isPreviousEngagement;
	
	@Column(name="is_previous_marriage")
	private String isPreviousMarriage;
	
	@Column(name="is_previous_travel_board")
	private String is_previousTravelBoard;
	
	@Column(name="recognition_regularity_rate")
	private String recognitionRegularityRate;
	
	@Column(name="intake_rate")
	private String intakeRate;
	
	@Column(name="father_of_confession")
	private String fatherOfConfession;
	
	@Column(name="is_have_childern")
	private String isHaveChildern;
	
	@Column(name="num_of_childern")
	private Integer numOfChildern;
	
	@Column(name="social_status")
	private String socialStatus;
	
	@Column(name="source_of_marriage_permit")
	private String sourceOfMarriagePermit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_of_marriage_permit")
	private Date dateOfMarriagePermit;
	
	@Column(name="Status")
	private String Status;
	
	@Column(name="printed_file_attachment")
	private String printedFileAttachment;
	
	@Column(name="original_file_attachment")
	private String originalFileAttachment;
	
	@Column(name="engagment_file_attachment")
	private String engagmentFileAttachment;
	
	@Column(name="annulment_engagment_attachmnet")
	private String annulmentEngagmentAttachmnet;
	
	@Column(name="gender")
	private String gender;
	
	@ManyToOne
	@JoinColumn(name="church_id")
	private Church church;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name="Emirate_id")
	private PersonalData personalData;
	
	@JsonIgnore
	@OneToMany(mappedBy="clearance")
	private List<PreviousMarriage> previousMarriages;
	
	@JsonIgnore
	@OneToMany(mappedBy="clearance")
	private List<PreviousEngagment> previousEngagment;
	
	@JsonIgnore
	@OneToMany(mappedBy="clearance")
	private List<Child> childs;

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

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

	public String getIsPreviousEngagement() {
		return isPreviousEngagement;
	}

	public void setIsPreviousEngagement(String isPreviousEngagement) {
		this.isPreviousEngagement = isPreviousEngagement;
	}

	public String getIsPreviousMarriage() {
		return isPreviousMarriage;
	}

	public void setIsPreviousMarriage(String isPreviousMarriage) {
		this.isPreviousMarriage = isPreviousMarriage;
	}

	public String getIs_previousTravelBoard() {
		return is_previousTravelBoard;
	}

	public void setIs_previousTravelBoard(String is_previousTravelBoard) {
		this.is_previousTravelBoard = is_previousTravelBoard;
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

	public String getIsHaveChildern() {
		return isHaveChildern;
	}

	public void setIsHaveChildern(String isHaveChildern) {
		this.isHaveChildern = isHaveChildern;
	}


	public Integer getNumOfChildern() {
		return numOfChildern;
	}

	public void setNumOfChildern(Integer numOfChildern) {
		this.numOfChildern = numOfChildern;
	}

	public String getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(String socialStatus) {
		this.socialStatus = socialStatus;
	}

	public String getSourceOfMarriagePermit() {
		return sourceOfMarriagePermit;
	}

	public void setSourceOfMarriagePermit(String sourceOfMarriagePermit) {
		this.sourceOfMarriagePermit = sourceOfMarriagePermit;
	}

	public Date getDateOfMarriagePermit() {
		return dateOfMarriagePermit;
	}

	public void setDateOfMarriagePermit(Date dateOfMarriagePermit) {
		this.dateOfMarriagePermit = dateOfMarriagePermit;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPrintedFileAttachment() {
		return printedFileAttachment;
	}

	public void setPrintedFileAttachment(String printedFileAttachment) {
		this.printedFileAttachment = printedFileAttachment;
	}

	public String getOriginalFileAttachment() {
		return originalFileAttachment;
	}

	public void setOriginalFileAttachment(String originalFileAttachment) {
		this.originalFileAttachment = originalFileAttachment;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	public List<PreviousMarriage> getPreviousMarriages() {
		return previousMarriages;
	}

	public void setPreviousMarriages(List<PreviousMarriage> previousMarriages) {
		this.previousMarriages = previousMarriages;
	}

	public List<PreviousEngagment> getPreviousEngagment() {
		return previousEngagment;
	}

	public void setPreviousEngagment(List<PreviousEngagment> previousEngagment) {
		this.previousEngagment = previousEngagment;
	}

	public List<Child> getChilds() {
		return childs;
	}

	public void setChilds(List<Child> childs) {
		this.childs = childs;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Church getChurch() {
		return church;
	}

	public void setChurch(Church church) {
		this.church = church;
	}
	
	

}

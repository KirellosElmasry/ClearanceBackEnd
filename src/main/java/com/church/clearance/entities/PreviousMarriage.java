package com.church.clearance.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="previous_marriage")
@NamedQuery(name="PreviousMarriage.findAll", query="SELECT p FROM PreviousMarriage p")
public class PreviousMarriage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="mar_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int marId;
	
	@Column(name="kind_of_marriage")
	private String kindOfMarriage;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="marriage_date")
	private Date marriageDate;
	
	@Column(name="marriage_place")
	private String marriagePlace;
	
	@Column(name="priest_father")
	private String priestFather;
	
	@Column(name="status")
	private String status;
	
	@Column(name="case_no")
	private String caseNo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_of_case")
	private Date dateOfCase;
	
	@Column(name="court")
	private String court;
	
	@ManyToOne
	@JoinColumn(name="ref_no")
	private Clearance clearance;

	public int getMarId() {
		return marId;
	}

	public void setMarId(int marId) {
		this.marId = marId;
	}

	public String getKindOfMarriage() {
		return kindOfMarriage;
	}

	public void setKindOfMarriage(String kindOfMarriage) {
		this.kindOfMarriage = kindOfMarriage;
	}

	public Date getMarriageDate() {
		return marriageDate;
	}

	public void setMarriageDate(Date marriageDate) {
		this.marriageDate = marriageDate;
	}

	public String getMarriagePlace() {
		return marriagePlace;
	}

	public void setMarriagePlace(String marriagePlace) {
		this.marriagePlace = marriagePlace;
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

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public Date getDateOfCase() {
		return dateOfCase;
	}

	public void setDateOfCase(Date dateOfCase) {
		this.dateOfCase = dateOfCase;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public Clearance getClearance() {
		return clearance;
	}

	public void setClearance(Clearance clearance) {
		this.clearance = clearance;
	}
	
	

}

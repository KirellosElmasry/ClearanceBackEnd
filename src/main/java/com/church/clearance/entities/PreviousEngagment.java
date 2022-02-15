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
@Table(name="previous_engagment")
@NamedQuery(name="PreviousEngagment.findAll", query="SELECT p FROM PreviousEngagment p")
public class PreviousEngagment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="eng_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int engId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="engagment_date")
	private Date engagmentDate;
	
	@Column(name="engagment_place")
	private String engagmentPlace;
	
	@Column(name="priest_father")
	private String priestFather;
	
	@Column(name="status")
	private String status;
	
	@Column(name="engagment_file_attachment")
	private String engagmentFileAttachment;
	
	@Column(name="annulment_engagment_attachmnet")
	private String annulmentEngagmentAttachmnet;
	
	@ManyToOne
	@JoinColumn(name="ref_no")
	private Clearance clearance;

	public int getEngId() {
		return engId;
	}

	public void setEngId(int engId) {
		this.engId = engId;
	}

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

	public Clearance getClearance() {
		return clearance;
	}

	public void setClearance(Clearance clearance) {
		this.clearance = clearance;
	}
	
	

}

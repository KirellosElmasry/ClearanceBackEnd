package com.church.clearance.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="personal_data")
@NamedQuery(name="PersonalData.findAll", query="SELECT p FROM PersonalData p")
public class PersonalData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Emirate_id")
	private String emirateId;
	
	@Column(name="name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="birthdate")
	private Date birthDate;
	
	@Column(name="birth_location")
	private String birthLocation;
	
	@Temporal(TemporalType.DATE)
	@Column(name="baptism")
	private Date baptism;
	
	@Column(name="baptism_place")
	private String baptismPlace;
	
	@Column(name="education")
	private String education;
	
	@Temporal(TemporalType.DATE)
	@Column(name="education_date")
	private Date educationDate;
	
	@Column(name="pic")
	private String pic;
	
	@JsonIgnore
	@OneToMany(mappedBy="personalData")
	private List<Clearance> clearances;
	
	@Column(name="status")
	private String status;

	public String getEmirateId() {
		return emirateId;
	}

	public void setEmirateId(String emirateId) {
		this.emirateId = emirateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthLocation() {
		return birthLocation;
	}

	public void setBirthLocation(String birthLocation) {
		this.birthLocation = birthLocation;
	}

	public Date getBaptism() {
		return baptism;
	}

	public void setBaptism(Date baptism) {
		this.baptism = baptism;
	}

	public String getBaptismPlace() {
		return baptismPlace;
	}

	public void setBaptismPlace(String baptismPlace) {
		this.baptismPlace = baptismPlace;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Date getEducationDate() {
		return educationDate;
	}

	public void setEducationDate(Date educationDate) {
		this.educationDate = educationDate;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public List<Clearance> getClearances() {
		return clearances;
	}

	public void setClearances(List<Clearance> clearances) {
		this.clearances = clearances;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PersonalData [emirateId=" + emirateId + ", name=" + name + ", birthDate=" + birthDate
				+ ", birthLocation=" + birthLocation + ", baptism=" + baptism + ", baptismPlace=" + baptismPlace
				+ ", education=" + education + ", educationDate=" + educationDate + ", pic=" + pic + ", clearances="
				+ clearances + ", status=" + status + "]";
	}
}

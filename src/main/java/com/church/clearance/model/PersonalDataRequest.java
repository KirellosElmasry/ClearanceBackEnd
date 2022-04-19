package com.church.clearance.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class PersonalDataRequest implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
    private String emirateId;
	
	private String name;
	
	private Date birthDate;
	
	private String birthLocation;
	
	private Date baptism;
	
	private String baptismPlace;
	
	private String education;
	
	private Date educationDate;
	
	private String pic;

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
	
	
	

}

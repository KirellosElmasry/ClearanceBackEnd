package com.church.clearance.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="churchs")
@NamedQuery(name="Church.findAll", query="SELECT c FROM Church c")
public class Church implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="church_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int churchId;
	
	@Column(name="church_name_en")
	private String churchNameEn;
	
	@Column(name="church_name_ar")
	private String churchNameAr;
	
	@Column(name="country")
	private String country;
	
	@JsonIgnore
	@OneToMany(mappedBy="church")
	private List<Users> users;

	
	public int getChurchId() {
		return churchId;
	}

	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}

	public String getChurchNameEn() {
		return churchNameEn;
	}

	public void setChurchNameEn(String churchNameEn) {
		this.churchNameEn = churchNameEn;
	}

	public String getChurchNameAr() {
		return churchNameAr;
	}

	public void setChurchNameAr(String churchNameAr) {
		this.churchNameAr = churchNameAr;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	

}

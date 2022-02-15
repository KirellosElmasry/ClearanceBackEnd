package com.church.clearance.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="child")
@NamedQuery(name="Child.findAll", query="SELECT c FROM Child c")
public class Child implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="child_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int childId;
	
	@Column(name="child_name")
	private String childName;
	
	@Column(name="child_age")
	private int childAge;
	
	@Column(name="baptism")
	private String baptism;
	
	@ManyToOne
	@JoinColumn(name="ref_no")
	private Clearance clearance;

	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}



	public int getChildAge() {
		return childAge;
	}

	public void setChildAge(int childAge) {
		this.childAge = childAge;
	}

	public String getBaptism() {
		return baptism;
	}

	public void setBaptism(String baptism) {
		this.baptism = baptism;
	}

	public Clearance getClearance() {
		return clearance;
	}

	public void setClearance(Clearance clearance) {
		this.clearance = clearance;
	}
	
	

}

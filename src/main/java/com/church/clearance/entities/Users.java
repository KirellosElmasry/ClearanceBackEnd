package com.church.clearance.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
@NamedQuery(name="Users.findAll", query="SELECT u FROM Users u")
public class Users implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="rol_id")
	private Roles roll;
	
	@ManyToOne
	@JoinColumn(name="church_id")
	private Church church;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Clearance> clearances;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRoll() {
		return roll;
	}

	public void setRoll(Roles roll) {
		this.roll = roll;
	}

	public Church getChurch() {
		return church;
	}

	public void setChurch(Church church) {
		this.church = church;
	}

	public List<Clearance> getClearances() {
		return clearances;
	}

	public void setClearances(List<Clearance> clearances) {
		this.clearances = clearances;
	}
	
	

}

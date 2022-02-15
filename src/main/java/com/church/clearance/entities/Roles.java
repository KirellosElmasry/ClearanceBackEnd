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
@Table(name="roles")
@NamedQuery(name="Roles.findAll", query="SELECT r FROM Roles r")
public class Roles implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="rol_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int rolId;
	
	@Column(name="rol_name")
	private String rolName;
	
	@JsonIgnore
	@OneToMany(mappedBy="roll")
	private List<Users> users;

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	

}

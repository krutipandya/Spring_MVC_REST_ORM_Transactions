package com.cmpe275.lab2.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="person")
public class Person {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO) //for auto-increment
	private int id;
	
	@Column(name="firstName")
	private String firstname;
	
	@Column(name="lastName")
	private String lastname;
	
	@Column
	private String email;
	
	@Column
	private String description;
	
	@Embedded
	private Address address;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="orgId")
	private Organization org;
	
	@Transient
	private List<Integer> friends;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	public List<Integer> getFriendsDetails() {
		return friends;
	}
	public void setFriendsDetails(List<Integer> friends) {
		this.friends = friends;
	}

}

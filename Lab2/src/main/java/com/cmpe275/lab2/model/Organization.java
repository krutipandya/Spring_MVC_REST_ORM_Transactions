package com.cmpe275.lab2.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cmpe275.lab2.model.Address;
@Entity
@Table(name="organization")
public class Organization {

		@Id
		@Column(name="orgId")
		@GeneratedValue(strategy=GenerationType.AUTO) //for autonumber
	    private int id;
		
		@Column
	    private String name;
	    
		@Column
		private String description;
	    
		@Embedded
		private Address address;
	    
		@Column
		private String email;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	}
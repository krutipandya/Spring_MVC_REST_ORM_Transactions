package com.cmpe275.lab2.service;
import com.cmpe275.lab2.model.Organization;

public interface OrganizationService {

		public void add(Organization org);
		public void edit(Organization org);
		public void delete(int id);
		public Organization getOrganization(int id);
		
}


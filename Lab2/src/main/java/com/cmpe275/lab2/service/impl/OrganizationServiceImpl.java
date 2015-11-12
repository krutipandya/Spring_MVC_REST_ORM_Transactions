package com.cmpe275.lab2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.lab2.dao.OrganizationDao;
import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.service.OrganizationService;
@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	private OrganizationDao orgDao;
	
	@Transactional
	public void add(Organization org) {
		orgDao.add(org);
	}


	@Transactional
	public void edit(Organization org) {
		orgDao.edit(org);
	}

	@Transactional
	public void delete(int id) {
		orgDao.delete(id);
	}

	
	@Transactional
	public Organization getOrganization(int id) {
		return orgDao.getOrganization(id);
	}
}

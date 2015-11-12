package com.cmpe275.lab2.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmpe275.lab2.dao.OrganizationDao;
import com.cmpe275.lab2.model.Organization;

@Repository
public class OrganizationDaoImpl implements OrganizationDao{

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Organization org) {
		session.getCurrentSession().save(org);
	}

	@Override
	public void edit(Organization org) {
		session.getCurrentSession().update(org);
	}

	@Override
	public void delete(int id) {
	Query query = session.getCurrentSession().createQuery("delete Organization where orgId = :ID");
		query.setParameter("ID", id);
		query.executeUpdate();
	}

	@Override
	public Organization getOrganization(int id) {
		return (Organization)session.getCurrentSession().get(Organization.class, id);
	}
}

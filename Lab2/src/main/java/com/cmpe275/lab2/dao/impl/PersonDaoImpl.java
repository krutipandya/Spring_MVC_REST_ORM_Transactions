package com.cmpe275.lab2.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmpe275.lab2.dao.PersonDao;
import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.model.Person;


@Repository
public class PersonDaoImpl implements PersonDao {
	
	@Autowired
	private SessionFactory session;
	
	
	@Override
	public void add(Person person) {
		session.getCurrentSession().save(person);
	}

	@Override
	public void edit(Person person) {
		session.getCurrentSession().update(person);
	}

	@Override
	public Person getPerson(int id) {
		return (Person)session.getCurrentSession().get(Person.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getPersonList(Organization org) {
		
		System.out.println(" org id "+org.getId());
		Criteria c = session.getCurrentSession().createCriteria(Person.class, "p");
		c.add(Restrictions.eq("p.org.id", org.getId()));
		List<Person> listPerson=(ArrayList<Person>)c.list();
		if(!listPerson.isEmpty() && listPerson.size()>0)
		{
			return listPerson;
		}
		else
		{
			return null;
		}
		
	}
	
	@Override
	public void delete(int id)
	{
		System.out.println(" id "+id);
		Query query = session.getCurrentSession().createQuery("delete Person where id = :ID");
		
		query.setParameter("ID", id);
		 
		query.executeUpdate();
	}
	
}


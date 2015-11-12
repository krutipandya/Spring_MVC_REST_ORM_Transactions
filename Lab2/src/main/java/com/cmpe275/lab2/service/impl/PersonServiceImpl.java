package com.cmpe275.lab2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.lab2.dao.PersonDao;
import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.model.Person;
import com.cmpe275.lab2.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDao personDao;
	
	@Transactional
	public void add(Person person) {
		personDao.add(person);
	}

	@Transactional
	public Person getPerson(int id) {
		return personDao.getPerson(id);
	}

	@Transactional
	public void edit(Person person) {
		personDao.edit(person);
	}

	@Transactional
	public void delete(int id) {
		personDao.delete(id);
	}

	@Transactional
	public List<Person> getPersonList(Organization org) {
		return personDao.getPersonList(org);
	}

}

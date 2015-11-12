package com.cmpe275.lab2.service;

import java.util.List;

import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.model.Person;

public interface PersonService {
	
	public void add(Person person);
	public void edit(Person person);
	public void delete(int id);
	public Person getPerson(int id);
	public List<Person> getPersonList(Organization org);
}

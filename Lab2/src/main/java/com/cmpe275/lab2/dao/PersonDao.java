package com.cmpe275.lab2.dao;

import java.util.List;

import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.model.Person;

public interface PersonDao {

	public void add(Person person);
	public void edit(Person person);
	public void delete(int id);
	public Person getPerson(int id);
	public List<Person> getPersonList(Organization org);
}

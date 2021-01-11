package com.peoplezone.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.peoplezone.models.Person;

public interface PersonService {
	
	List<Person> getAllPeople();
	List<Person> searchBySpecAll(String search, String page, String num, String sortBy, String ascending);
	Person getPerson(long id);
	void updatePerson(long id, Person newPerson);
	void deletePerson(long id);
	void createPerson(Person p);
}

package com.peoplezone.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.peoplezone.models.Person;
import com.peoplezone.repo.PersonRepo;
import com.peoplezone.search.PersonSpecification;
import com.peoplezone.search.SearchCriteria;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepo personRepo;
	


	@Override
	public List<Person> getAllPeople() {
		return personRepo.findAll();
	}

	

	
	public List<Person> searchBySpecSurname(String search){
		 PersonSpecification spec = new PersonSpecification(new SearchCriteria("surName", ":", search));
		 
		 List<Person> results = personRepo.findAll(spec);
		 return results;
	}
	
	public List<Person> searchBySpecFirstname(String search, String page, String num, String sortBy, boolean ascending){
		 PersonSpecification spec = new PersonSpecification(new SearchCriteria("firstName", ":", search));
		 
		 List<Person> results = personRepo.findAll(spec);
		 return results;
	}
	
	public List<Person> searchBySpecAll(String search, String page, String num, String sortBy, String ascending){
		 int age = 0;
		
		 try {
			 age = Integer.parseInt(search);
		 } catch (Exception e) {
			 
		 }
		 double d = 0;
		 try {
			 d = Double.parseDouble(search);
		 } catch (Exception e) {
			 
		 }
		 
		 int pageNum = 0;
		 try {
			 pageNum = Integer.parseInt(page);
		 } catch (Exception e) {
			 
		 }
		 
		 int numResults = 0;
		 try {
			 numResults = Integer.parseInt(num);
		 } catch (Exception e) {
			 
		 }
		 boolean asc = true;
		 if(ascending.equals("false"))
			 asc = false;
		 
		 char gender = 0;
		 try {
			 gender = search.charAt(0);
		 } catch (Exception e) {
			 
		 }
		 
		 
		 PersonSpecification spec = new PersonSpecification(new SearchCriteria("firstName", ":", search));
		 PersonSpecification spec2 = new PersonSpecification(new SearchCriteria("surName", ":", search));
		 PersonSpecification spec3 = new PersonSpecification(new SearchCriteria("age", ":", age));
		 PersonSpecification spec4 = new PersonSpecification(new SearchCriteria("description", ":", search));
		 PersonSpecification spec5 = new PersonSpecification(new SearchCriteria("highestEducationQualification", "=", search));
		 PersonSpecification spec6 = new PersonSpecification(new SearchCriteria("occupation", ":", search));
		 PersonSpecification spec7 = new PersonSpecification(new SearchCriteria("employer", ":", search));
		 PersonSpecification spec8 = new PersonSpecification(new SearchCriteria("college", ":", search));
		 PersonSpecification spec9 = new PersonSpecification(new SearchCriteria("school", ":", search));
		 PersonSpecification spec10 = new PersonSpecification(new SearchCriteria("eyecolor", "=", search));
		 PersonSpecification spec11 = new PersonSpecification(new SearchCriteria("weight", "=", d));
		 PersonSpecification spec12 = new PersonSpecification(new SearchCriteria("height", "=", d));
		 PersonSpecification spec17 = new PersonSpecification(new SearchCriteria("phoneNumber", "=", d));
		 PersonSpecification spec18 = new PersonSpecification(new SearchCriteria("gender", "=", gender));
		 PersonSpecification spec19 = new PersonSpecification(new SearchCriteria("emailAddress", "=", search));
		 PersonSpecification spec20 = new PersonSpecification(new SearchCriteria("websiteAddress", ":", search));
		 PersonSpecification spec21 = new PersonSpecification(new SearchCriteria("homeAddress", ":", search));;
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 } 
		 
		 Page<Person> results = personRepo.findAll(Specification.where(spec).or(spec2).or(spec3).or(spec6).or(spec7).or(spec8).or(spec9).or(spec11).or(spec12).or(spec19).or(spec21).or(spec20).or(spec4).or(spec5).or(spec10).or(spec17).or(spec18), firstPageWithFourElements);
		 List<Person> res = results.getContent();
		 
		 return res;
	}

	@Override
	public Person getPerson(long id) {
		Optional<Person> p = personRepo.findById(id);
		return p.get();
	}
	

	@Override
	public void updatePerson(long id, Person newPerson) {
		personRepo.save(newPerson);	
	}
	
	@Override
	public void deletePerson(long id) {
		personRepo.deleteById(id);	
	}




	@Override
	public void createPerson(Person p) {
		personRepo.save(p);
	}
	 
	
	
}

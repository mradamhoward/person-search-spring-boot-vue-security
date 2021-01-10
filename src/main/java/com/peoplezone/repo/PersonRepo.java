package com.peoplezone.repo;
import org.springframework.stereotype.Repository;

import com.peoplezone.models.Person;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
	List<Person> searchByFirstNameContainingOrSurNameContainingAllIgnoreCase(String firstName, String surName);
	
	Person findByFirstName(String firstName);
	
	
	
	List<Person> searchByFirstNameContainingAllIgnoreCase(String firstName, Pageable page);
	List<Person> searchBySurNameContainingAllIgnoreCase(String surName, Pageable page);
	List<Person> searchByAge(int age, Pageable page);
	List<Person> searchByDescriptionContainingAllIgnoreCase(String desc, Pageable page);
	List<Person> searchByHighestEducationQualificationContainingAllIgnoreCase(String edu, Pageable page);
	List<Person> searchByOccupationContainingAllIgnoreCase(String occ, Pageable page);
	List<Person> searchByEmployerContainingAllIgnoreCase(String emp, Pageable page);
	List<Person> searchByCollegeContainingAllIgnoreCase(String emp, Pageable page);
	List<Person> searchBySchoolContainingAllIgnoreCase(String emp, Pageable page);
	List<Person> searchByEyecolorContainingAllIgnoreCase(String eye, Pageable page);
	List<Person> searchByWeight(double weight, Pageable page);
	List<Person> searchByHeight(double height, Pageable page);
	List<Person> searchByDriversLicence(boolean emp, Pageable page);
	List<Person> searchByProvisionalLicence(boolean emp, Pageable page);
	List<Person> searchByPhoneNumber(long phone, Pageable page);
	List<Person> searchByGender(char emp, Pageable page);
	List<Person> searchByEmailAddressIgnoreCase(String emp, Pageable page);
	List<Person> searchByWebsiteAddressContainingAllIgnoreCase(String emp, Pageable page);
	List<Person> searchByHomeAddressContainingAllIgnoreCase(String emp, Pageable page);

	
	
	
}

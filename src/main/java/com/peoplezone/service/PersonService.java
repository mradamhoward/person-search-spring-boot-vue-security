package com.peoplezone.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.peoplezone.models.Person;

public interface PersonService {
	
	List<Person> searchForPersonFirstNameLastName(String toSearch);
	List<Person> getAllPeople();
	List<Person> searchByAllAttributes(String toSearch, int page, int quan, String sortBy, boolean ascending);
	List<Person> searchForFirstName(String toSearch, int page, int quan);
	List<Person> searchForSecondName(String toSearch, int page, int quan);
	List<Person> searchForAge(String toSearch, int page, int quan);
	List<Person> searchForDOB(String toSearch, int page, int quan);
	List<Person> searchForDesc(String toSearch, int page, int quan);
	List<Person> searchForEmail(String toSearch, int page, int quan);
	List<Person> searchForWebsite(String toSearch, int page, int quan);
	List<Person> searchForGender(String toSearch, int page, int quan);
	List<Person> searchForPhone(String toSearch, int page, int quan);
	List<Person> searchForHomeAddress(String toSearch, int page, int quan);
	List<Person> searchForEducation(String toSearch, int page, int quan);
	List<Person> searchForOccupation(String toSearch, int page, int quan);
	List<Person> searchForEmployer(String toSearch, int page, int quan);
	List<Person> searchForCollege(String toSearch, int page, int quan);
	List<Person> searchForSchool(String toSearch, int page, int quan);
	List<Person> searchForEyeColor(String toSearch, int page, int quan);
	List<Person> searchForWeight(String toSearch, int page, int quan);
	List<Person> searchForHeight(String toSearch, int page, int quan);
	List<Person> searchForPPS(String toSearch, int page, int quan);
	List<Person> searchForDriverLicence(String toSearch, int page, int quan);
	List<Person> searchForProvisional(String toSearch, int page, int quan);
	List<Person> searchForIBAN(String toSearch, int page, int quan);
	List<Person> searchBySpecSurname(String search);
	List<Person> searchBySpecFirstname(String search);
	List<Person> searchBySpecAll(String search);
	List<Person> searchBySpecAll(String search, String page, String num, String sortBy, String ascending);
	List<Person> searchForFirstName(String search, String page, String num, String sortBy, String ascending);
	List<Person> searchForSecondName(String search, String page, String num, String sortBy, String ascending);
	List<Person> searchForEmail(String search, String page, String num, String sortBy, String ascending);
	List<Person> searchForDesc(String search, String page, String num, String sortBy, String ascending);
	List<Person> searchForAge(String search, String page, String num, String sortBy, String ascending);
	List<Person> searchForPhone(String search, String page, String num, String sortBy, String ascending);
	List<Person> searchForHomeAddress(String search, String page, String num, String sortBy, String ascending);
	List<Person> searchForGender(String search, String page, String num, String sortBy, String ascending);
	List<Person> searchForWebsite(String search, String page, String num, String sortBy, String ascending);
	Person getPerson(long id);
}

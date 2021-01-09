package com.peoplezone.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peoplezone.models.Person;
import com.peoplezone.service.PersonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;

	
	@GetMapping("/search")
	public List<Person> searchSpecificAttribute(@RequestParam String toSearch, @RequestParam String attr,  @RequestParam String page,  @RequestParam String numResults,@RequestParam String sortBy,	@RequestParam String ascending) {
		System.out.println("tosearch " + toSearch);
		System.out.println("Attr " + attr);
		System.out.println("Page " + page);
		System.out.println("Sort by: " + sortBy);
		System.out.println("Ascending: " + ascending); 
		
		int page1 = 0;
		try {
			page1 = Integer.parseInt(page);
		} catch (Exception e) {
					
		}
		int num = 2;
		try {
			 num = Integer.parseInt(numResults);
		} catch (Exception e) {
					
		}
		
		boolean asc = true;
		
		if(ascending.equals("true")) {
			asc = true;
		} else {
			asc = false;
		}
		
		List<Person> peopleResults;

		int age = 0; 
		
		try {
			age = Integer.parseInt(toSearch);
		} catch (Exception e){
			
		}
		
		double doublesearch = 0; 
		
		try {
			doublesearch = Double.parseDouble(toSearch);
		} catch (Exception e){
			
		}
		
		long l = 0;
		
		try {
			l = Long.parseLong(toSearch);
		} catch (Exception e) {
			
		}
		
		char gender = 0;
		try {
			gender = toSearch.charAt(gender);
		} catch (Exception e) {
			
		}
		
		switch(attr) {
			//Search and sort working for all attributes
			case"all":{
				List<Person> pagep = personService.searchBySpecAll(toSearch, page, numResults, sortBy, ascending);
				return pagep;
			}
			//Search and sort working for firstName
			case "firstName":{
				peopleResults = personService.searchForFirstName(toSearch, page, numResults, sortBy, ascending);
				return peopleResults;
			}  
			//Search and sort working for surName
			case "surName":{
				peopleResults = personService.searchForSecondName(toSearch, page, numResults, sortBy, ascending);
				return peopleResults;
			}  
			case "age":{
				peopleResults = personService.searchForAge(toSearch, page, numResults, sortBy, ascending);
				return peopleResults;
			}  
			case "dob":{
				peopleResults = personService.searchForDOB(toSearch,page1, num);
				return peopleResults;
			}  
			case "desc":{
				peopleResults = personService.searchForDesc(toSearch, page, numResults, sortBy, ascending);
				return peopleResults;
			}  
			case "email":{
				peopleResults = personService.searchForEmail(toSearch, page, numResults, sortBy, ascending);
				return peopleResults;
			}  
			case "website":{
				peopleResults = personService.searchForWebsite(toSearch, page, numResults, sortBy, ascending);
				return peopleResults;
			}  
			case "gender":{
				peopleResults = personService.searchForGender(toSearch, page, numResults, sortBy, ascending);
				return peopleResults;
			}  
			case "phone":{
				peopleResults = personService.searchForPhone(toSearch, page, numResults, sortBy, ascending);
				return peopleResults;
			}  
			case "homeAddress":{
				peopleResults = personService.searchForHomeAddress(toSearch, page, numResults, sortBy, ascending);
				return peopleResults;
			}  
			case "edu":{
				peopleResults = personService.searchForEducation(toSearch,page1, num);
				return peopleResults;
			}  
			case "occupation":{
				peopleResults = personService.searchForOccupation(toSearch,page1, num);
				return peopleResults;
			} 
			case "employer":{
				peopleResults = personService.searchForEmployer(toSearch,page1, num);
				return peopleResults;
			}  
			case "college":{
				peopleResults = personService.searchForCollege(toSearch, page1, num);
				return peopleResults;
			}  
			case "school":{
				peopleResults = personService.searchForSchool(toSearch,page1, num);
				return peopleResults;
			}  
			case "eye":{
				peopleResults = personService.searchForEyeColor(toSearch,page1, num);
				return peopleResults;
			}  
			case "weight":{
				peopleResults = personService.searchForWeight(toSearch,page1, num);
				return peopleResults;
			}  
			case "height":{
				peopleResults = personService.searchForHeight(toSearch,page1, num);
				return peopleResults;
			}  
			case "pps":{
				peopleResults = personService.searchForPPS(toSearch,page1, num);
				return peopleResults;
			}  
			case "iban":{
				peopleResults = personService.searchForIBAN(toSearch,page1, num);
				return peopleResults;
			}  
		}
		
		return null;
	}
	
	@GetMapping("/all")
	public List<Person> getAllPeople(){
		List<Person> all = personService.getAllPeople();
		return all;
	}
	
}

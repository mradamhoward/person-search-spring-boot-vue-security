package com.peoplezone.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;

	
	@GetMapping("/search")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Person> searchSpecificAttribute(@RequestParam String q,  @RequestParam String page,  @RequestParam String numResults,@RequestParam String sortBy,	@RequestParam String ascending) {
//		System.out.println("tosearch " + toSearch);
//		System.out.println("Attr " + attr);
//		System.out.println("Page " + page);
//		System.out.println("Sort by: " + sortBy);
//		System.out.println("Ascending: " + ascending); 
	
		
		boolean asc = true;
		
		if(ascending.equals("true")) {
			asc = true;
		} else {
			asc = false;
		}
		
		List<Person> pagep = null;
		try {
			pagep = personService.searchBySpecAll(q, page, numResults, sortBy, ascending);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagep;
	}
	
	@GetMapping("/all")
	public List<Person> getAllPeople(){
		List<Person> all = personService.getAllPeople();
		return all;
	}
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/person")
	public Person getPerson(@RequestParam long id){
		Person p = null;
		System.out.println(id);
		try {
			p = personService.getPerson(id);
		} catch (Exception e) {
				
		}
		return p;
	}
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@PutMapping("/updatePerson")
	public void updatePerson(@RequestParam long id, @RequestBody Person person){
		try {
			personService.updatePerson(id, person);
		} catch (Exception e) {
				
		}
	}
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@DeleteMapping("/deletePerson")
	public void deletePerson(@RequestParam long id){
		System.out.println("Delete" + id);
		try {
			personService.deletePerson(id);
		} catch (Exception e) {
				
		}
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/exportPerson")
	public String exportPerson(long id) {
		String location;
		
		location = personService.createPDF(id);
		
		return location;
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@PostMapping("/createPerson")
	public void createPerson(@RequestBody Person p){
		System.out.println("Create" + p.toString());
		try {
			personService.createPerson(p);
		} catch (Exception e) {
				
		}
	}
	
	
}

package com.peoplezone.config;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.peoplezone.*;
import com.peoplezone.models.ERole;
import com.peoplezone.models.Person;
import com.peoplezone.models.Role;
import com.peoplezone.models.User;
import com.peoplezone.repo.PersonRepo;
import com.peoplezone.repo.UserRepository;
import java.util.Set;

@Component
public class AppConfig  implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private PersonRepo personRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	private final Person createPersonIfNotFound(String firstName, String lastName, int age, Date DOB, String desc, String edu, String occupation, String employer, String school, String college, String eyeColor
			, double weight, double height, String PPS, boolean drivers, boolean prov, String IBAN, long phone, char gender, String email, String home, String website) {
		Person person = personRepo.findByFirstName(firstName);
		if(person == null) {
			person = new Person();
			person.setFirstName(firstName);
			person.setSurName(lastName);
			person.setAge(age);
			Date dob = new Date();
			person.setDOB(dob);
			person.setDescription(desc);
			person.setHighestEducationQualification(edu);
			person.setOccupation(occupation);
			person.setEmployer(employer);
			person.setCollege(college);
			person.setSchool(school);
			person.setEyecolor(eyeColor);
			person.setWeight(weight);
			person.setHeight(height);
			person.setDriversLicence(drivers);
			person.setProvisionalLicence(prov);
			person.setPhoneNumber(phone);
			person.setGender(gender);
			person.setEmailAddress(email);
			person.setWebsiteAddress(website);
			person.setHomeAddress(home);
			
			personRepo.save(person);
		}
		
		return person;	
	}
	
	@Transactional
	public final void createAdminUserIfNotFound(String userName, String email, String password, Role role) {
		Optional<User> user = null;
		User us = null;
		try {
			user = userRepo.findByUsername(userName);
			us  = user.get();
		} catch (Exception e) {
			
		}
		
		
		if(us == null) {
			us = new User();
			us.setEmail(email);
			us.setPassword(password);
			us.setUsername(userName);
			Set<Role> setRole = new HashSet<Role>();
			setRole.add(role);
			us.setRoles(setRole);
		}
		userRepo.save(us);
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		/*
		createPersonIfNotFound("Adam", "Howard", 24, new Date(),"Backend Dev", "BSC", "Software Engineer", "McAfee", "Colaiste an Chraoibhin","CIT",  "green", 220, 175, "8591910M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"3 Bishop Street Cobh", "https://linkedin.com" );
		createPersonIfNotFound("Jack", "O'Brien", 22, new Date(),"Musician", "BSC(Hons)", "Solicitor", "Dell",  "Colmans", "UCC","blue", 164, 135, "8321311M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"Tallow", "https://youtube.com" );
		createPersonIfNotFound("Fintan", "Linehan", 27, new Date(),"Tech Support", "Certificate", "Tech Support Specialist", "Amazon",  "Colmans", "UL","brown", 200, 155, "85954340M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@alltreeservices.ie", 
				"Waterford", "https://facebook.com" );
		createPersonIfNotFound("Shane", "O Connel", 21, new Date(),"Business man", "MSc", "researcher", "Apple",  "Loreto", "DCU", "green", 320, 195, "8591910M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "sales@amazon.com", 
				"College Road", "https://mup.ie" );
		createPersonIfNotFound("Cian", "Howard", 24, new Date(),"Nice", "BSC", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "GMIT", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@ergo.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Dave", "Howard", 24, new Date(),"Friendly", "MSC", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "AIT", "red", 120, 167, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'F', "info@zone.ie", 
				"12 Marian Square", "https://google.com" );
		createPersonIfNotFound("Kenneth", "Howard", 24, new Date(),"Helpful", "MPharm", "Engineer", "Apple", "Colaiste an Chraoibhin", "MTU", "blue", 120, 185, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@ssp.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Gavin", "Howard", 24, new Date(),"Yurt", "MSC", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "CIT", "green", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@person.ie", 
				"16 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 1", "Howard", 28, new Date(),"Absolutely", "PhD", "Teacher", "Dell", "Colaiste an Chraoibhin", "UL", "blue", 120, 166, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'F', "hello@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 2", "Howard", 21, new Date(),"Great", "Certificate", "Lecturer", "IBM", "Colaiste an Chraoibhin", "LIT", "green", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "yurt@mup.ie", 
				"22 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 3", "Howard", 20, new Date(),"Best", "Higher Cert", "PT", "Nike", "Colaiste an Chraoibhin", "ITT", "blue", 127, 155, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "yes@mup.ie", 
				"1 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 4", "Howard", 26, new Date(),"Frontend Dev", "BBus", "Builder", "McKesson", "Colaiste an Chraoibhin", "TUD", "brown", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'F', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 5", "Howard", 55, new Date(),"Yes", "BSC", "Welder", "McAfee", "Colaiste an Chraoibhin", "UCD", "blue", 220, 155, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@google.com", 
				"9 Bishop Street", "https://google.com" );
		createPersonIfNotFound("Person 6", "Howard", 64, new Date(),"Software Dev", "PhD", "Lecturer", "Intel", "Colaiste an Chraoibhin", "UCC", "brown", 190, 195, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"1 Bishop Street", "https://google.com" );
		createPersonIfNotFound("Person 7", "Howard", 92, new Date(),"Backend Dev", "MSC", "AI Researcher", "Google", "Colaiste an Chraoibhin", "CIT", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@apple.com", 
				"Kilarney Road ", "https://google.com" );
		createPersonIfNotFound("Shane", "Howard", 24, new Date(),"Backend Dev", "BSC", "Software Engineer", "McAfee", "Colaiste an Chraoibhin","CIT",  "green", 220, 175, "8591910M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"3 Bishop Street Cobh", "https://linkedin.com" );
		createPersonIfNotFound("Mary", "O'Brien", 22, new Date(),"Musician", "BSC(Hons)", "Solicitor", "Dell",  "Colmans", "UCC","blue", 164, 135, "8321311M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"Tallow", "https://youtube.com" );
		createPersonIfNotFound("Sean", "Linehan", 27, new Date(),"Tech Support", "Certificate", "Tech Support Specialist", "Amazon",  "Colmans", "UL","brown", 200, 155, "85954340M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@alltreeservices.ie", 
				"Waterford", "https://facebook.com" );
		createPersonIfNotFound("Jack", "O Connel", 21, new Date(),"Business man", "MSc", "researcher", "Apple",  "Loreto", "DCU", "green", 320, 195, "8591910M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "sales@amazon.com", 
				"College Road", "https://mup.ie" );
		createPersonIfNotFound("Liam", "Howard", 24, new Date(),"Nice", "BSC", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "GMIT", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@ergo.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Steve", "Howard", 24, new Date(),"Friendly", "MSC", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "AIT", "red", 120, 167, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'F', "info@zone.ie", 
				"12 Marian Square", "https://google.com" );
		createPersonIfNotFound("Kenneth", "Howard", 24, new Date(),"Helpful", "MPharm", "Engineer", "Apple", "Colaiste an Chraoibhin", "MTU", "blue", 120, 185, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@ssp.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Gavin", "Howard", 24, new Date(),"Yurt", "MSC", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "CIT", "green", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@person.ie", 
				"16 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 8", "Howard", 28, new Date(),"Absolutely", "PhD", "Teacher", "Dell", "Colaiste an Chraoibhin", "UL", "blue", 120, 166, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'F', "hello@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 9", "Howard", 21, new Date(),"Great", "Certificate", "Lecturer", "IBM", "Colaiste an Chraoibhin", "LIT", "green", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "yurt@mup.ie", 
				"22 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 10", "Howard", 20, new Date(),"Best", "Higher Cert", "PT", "Nike", "Colaiste an Chraoibhin", "ITT", "blue", 127, 155, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "yes@mup.ie", 
				"1 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 22", "Howard", 26, new Date(),"Frontend Dev", "BBus", "Builder", "McKesson", "Colaiste an Chraoibhin", "TUD", "brown", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'F', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 11", "Howard", 55, new Date(),"Yes", "BSC", "Welder", "McAfee", "Colaiste an Chraoibhin", "UCD", "blue", 220, 155, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@google.com", 
				"9 Bishop Street", "https://google.com" );
		createPersonIfNotFound("Person 44", "Howard", 64, new Date(),"Software Dev", "PhD", "Lecturer", "Intel", "Colaiste an Chraoibhin", "UCC", "brown", 190, 195, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"1 Bishop Street", "https://google.com" );
		createPersonIfNotFound("Person 77", "Howard", 92, new Date(),"Backend Dev", "MSC", "AI Researcher", "Google", "Colaiste an Chraoibhin", "CIT", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@apple.com", 
				"Kilarney Road ", "https://google.com" );*/
		
		
	}
}

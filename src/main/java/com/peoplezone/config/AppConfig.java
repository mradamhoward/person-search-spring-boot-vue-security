package com.peoplezone.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.peoplezone.*;
import com.peoplezone.models.Person;
import com.peoplezone.repo.PersonRepo;

@Component
public class AppConfig  implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private PersonRepo personRepo;
	
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
			person.setPPSnumber(PPS);
			person.setDriversLicence(drivers);
			person.setProvisionalLicence(prov);
			person.setBankIBAN(IBAN);
			person.setPhoneNumber(phone);
			person.setGender(gender);
			person.setEmailAddress(email);
			person.setWebsiteAddress(website);
			person.setHomeAddress(home);
			
			personRepo.save(person);
		}
		
		return person;	
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		createPersonIfNotFound("Adam", "Howard", 24, new Date(),"Backend Dev", "BSC", "Software Engineer", "McAfee", "Colaiste an Chraoibhin","CIT",  "green", 220, 175, "8591910M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"3 Bishop Street Cobh", "https://linkedin.com" );
		createPersonIfNotFound("Jack", "O'Brien", 22, new Date(),"Musician", "BSC(Hons)", "Solicitor", "Dell",  "Colmans", "UCC","blue", 164, 135, "8321311M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"Tallow", "https://youtube.com" );
		createPersonIfNotFound("Fintan", "Linehan", 27, new Date(),"Tech Support", "Certificate", "Tech Support Specialist", "Amazon",  "Colmans", "UL","brown", 200, 155, "85954340M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@alltreeservices.ie", 
				"Waterford", "https://facebook.com" );
		createPersonIfNotFound("Shane", "O Connel", 21, new Date(),"Business man", "MSc", "researcher", "Apple",  "Loreto", "DCU", "green", 320, 195, "8591910M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "sales@amazon.com", 
				"College Road", "https://mup.ie" );
		createPersonIfNotFound("Cian", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Dave", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Kenneth", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Gavin", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 1", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 2", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 3", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 4", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 5", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 6", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		createPersonIfNotFound("Person 7", "Howard", 24, new Date(),"Backend Dev", "PhD", "Lecturer", "McAfee", "Colaiste an Chraoibhin", "UCC", "blue", 120, 165, "75446750M", false, true, "IE11BOFI90286469745676", 892490923, 'M', "info@mup.ie", 
				"11 Marian Square", "https://google.com" );
		
	}
}

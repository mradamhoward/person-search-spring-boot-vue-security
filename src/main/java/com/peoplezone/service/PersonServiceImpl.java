package com.peoplezone.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
		ArrayList<PersonSpecification> personSpecs = new ArrayList<PersonSpecification>();
		
		ArrayList<String> searchWords = new ArrayList<String>();
		
		for(String s : search.split(" ")) {
			searchWords.add(s);
		}
		
		int numWords = searchWords.size();
		
		System.out.println("num words");
		
		
		for(String s : searchWords) {
			System.out.println(s);
			
			int age = 0;
			
			 try {
				 age = Integer.parseInt(s);
			 } catch (Exception e) {
				 
			 }
			 double d = 0;
			 try {
				 d = Double.parseDouble(s);
			 } catch (Exception e) {
				 
			 }
			 
			
			 char gender = 0;
			 try {
				 gender = search.charAt(0);
			 } catch (Exception e) {
				 
			 }
			 
			 PersonSpecification spec = new PersonSpecification(new SearchCriteria("firstName", ":", s));
			 PersonSpecification spec2 = new PersonSpecification(new SearchCriteria("surName", ":", s));
			 PersonSpecification spec3 = new PersonSpecification(new SearchCriteria("age", ":", age));
			 PersonSpecification spec4 = new PersonSpecification(new SearchCriteria("description", ":", s));
			 PersonSpecification spec5 = new PersonSpecification(new SearchCriteria("highestEducationQualification", "=", s));
			 PersonSpecification spec6 = new PersonSpecification(new SearchCriteria("occupation", ":", s));
			 PersonSpecification spec7 = new PersonSpecification(new SearchCriteria("employer", ":", s));
			 PersonSpecification spec8 = new PersonSpecification(new SearchCriteria("college", ":", s));
			 PersonSpecification spec9 = new PersonSpecification(new SearchCriteria("school", ":", s));
			 PersonSpecification spec10 = new PersonSpecification(new SearchCriteria("eyecolor", "=", s));
			 PersonSpecification spec11 = new PersonSpecification(new SearchCriteria("weight", "=", d));
			 PersonSpecification spec12 = new PersonSpecification(new SearchCriteria("height", "=", d));
			 PersonSpecification spec17 = new PersonSpecification(new SearchCriteria("phoneNumber", "=", d));
			 //PersonSpecification spec18 = new PersonSpecification(new SearchCriteria("gender", "=", gender));
			 PersonSpecification spec19 = new PersonSpecification(new SearchCriteria("emailAddress", "=", s));
			 PersonSpecification spec20 = new PersonSpecification(new SearchCriteria("websiteAddress", ":", s));
			 PersonSpecification spec21 = new PersonSpecification(new SearchCriteria("homeAddress", ":", s));;
			 
			 personSpecs.add(spec);
			 personSpecs.add(spec2);
			 personSpecs.add(spec3);
			 //personSpecs.add(spec4);
			 //personSpecs.add(spec5);
			 personSpecs.add(spec6);
			 personSpecs.add(spec7);
			 personSpecs.add(spec8);
			 personSpecs.add(spec9);
			 personSpecs.add(spec10);
			 personSpecs.add(spec11);
			 personSpecs.add(spec12);
			 personSpecs.add(spec17);
			 //personSpecs.add(spec18);
			 personSpecs.add(spec19);
			 personSpecs.add(spec20);
			 personSpecs.add(spec21);
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
		
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
		
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 }
		 
		 ArrayList<Specification<Person>> specs = new ArrayList<Specification<Person>>();
		 
		 Specification<Person> specTotal = null;
		 
		 int numSpecs = 14;
		 
		 int iters = personSpecs.size() / numSpecs; 
		 
		 System.out.println(personSpecs.size());
		 
		 System.out.println("Iterations of personSpecs: " + iters);
		
		 for(int i = 0; i < iters; i++) {
			 specs.add(Specification.where(personSpecs.get(i)).or(personSpecs.get(i + 1)).or(personSpecs.get(i + 2)).or(personSpecs.get(i + 3)).or(personSpecs.get(i + 4)).or(personSpecs.get(i + 5)).or(personSpecs.get(i + 6)).or(personSpecs.get(i + 7)).or(personSpecs.get(i + 8)).or(personSpecs.get(i + 9)).or(personSpecs.get(i + 10)).or(personSpecs.get(i + 11)).or(personSpecs.get(i + 12)).or(personSpecs.get(i + 13)));
		 }
		 
		 System.out.println("Number of specifications to append to total: " + specs.size());
		 
		 int totalToAppend = 0;
		 totalToAppend = specs.size();
		 
		 System.out.print(totalToAppend);
		 
		 switch(totalToAppend) {
		 	case 1: {
		 		specTotal = specs.get(0); 
		 		System.out.println(1 + " case");
		 		break;
		 	}
		 	case 2: {
		 		specTotal = specs.get(0).or(specs.get(1));
		 		System.out.println("case 2");
		 		break;
		 	}
		 	case 3: {
		 		specTotal = specs.get(0).or(specs.get(1)).or(specs.get(2));  
		 		break;
		 	}
		 	case 4: {
		 		specTotal = specs.get(0).or(specs.get(1)).or(specs.get(2)).or(specs.get(3)); 
		 		break;
		 	}
		 	case 5: {
		 		specTotal = specs.get(0).or(specs.get(1)).or(specs.get(2)).or(specs.get(3)).or(specs.get(4)); 
		 		break;
		 	}
		 }
		 
		 //specTotal = Specification.where(spec).or(spec2).or(spec3).or(spec6).or(spec7).or(spec8).or(spec9).or(spec11).or(spec12).or(spec19).or(spec21).or(spec20).or(spec4).or(spec5).or(spec10).or(spec17);
		 
		 Page<Person> results = personRepo.findAll(specTotal, firstPageWithFourElements);
		
		
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

	@Override
	public String createPDF(long id) {
		Optional<Person> toExport = personRepo.findById(id);
		Person p = toExport.get();
		
		String documentName = "uploads/" + p.getFirstName() + p.getSurName() + ".pdf";
		
		 Document document = new Document();
	      try
	      {
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(documentName));
	         document.open();
	         document.addCreationDate();
	         document.addCreator("PeopleZone");
	         document.addTitle(p.getFirstName() + " " + p.getSurName() + " Details");
	         document.addAuthor("PeopleZone - Adam Howard");
	         
	         PdfPTable table = new PdfPTable(2);
	         
	         table.setWidthPercentage(100); //Width 100%
	         table.setSpacingBefore(10f); //Space before table
	         table.setSpacingAfter(10f); //Space after table
	  
	         //Set Column widths
	         float[] columnWidths = {1f, 4f};
	         table.setWidths(columnWidths);
	         
	         PdfPCell cell1 = new PdfPCell(new Paragraph("ID: "));
	         cell1.setPaddingLeft(10);
	      
	         cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell2 = new PdfPCell(new Paragraph(Long.toString(p.getId())));
	         cell2.setPaddingLeft(10);
	 
	         cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell3 = new PdfPCell(new Paragraph("First Name: "));
	         cell3.setPaddingLeft(10);

	         cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell4 = new PdfPCell(new Paragraph(p.getFirstName()));
	         cell4.setPaddingLeft(10);
	   
	         cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell5 = new PdfPCell(new Paragraph("Surname: "));
	         cell5.setPaddingLeft(10);
	      
	         cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell6 = new PdfPCell(new Paragraph(p.getSurName()));
	         cell6.setPaddingLeft(10);

	         cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell7 = new PdfPCell(new Paragraph("Age: "));
	         cell7.setPaddingLeft(10);
	   
	         cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell8 = new PdfPCell(new Paragraph(Integer.toString(p.getAge())));
	         cell8.setPaddingLeft(10);
	    
	         cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell9 = new PdfPCell(new Paragraph("Description: "));
	         cell9.setPaddingLeft(10);
	
	         cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell10 = new PdfPCell(new Paragraph(p.getDescription()));
	         cell10.setPaddingLeft(10);
	       
	         cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell11 = new PdfPCell(new Paragraph("Date of Birth: "));
	         cell11.setPaddingLeft(10);
	         cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         @SuppressWarnings("deprecation")
	         PdfPCell cell12 = new PdfPCell(new Paragraph(p.getDob().getDate() + "/" + (p .getDob().getMonth() + 1 ) + "/" + Integer.toString(p.getDob().getYear())));
	         cell12.setPaddingLeft(10);
	         cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell13 = new PdfPCell(new Paragraph("Qualification: "));
	         cell13.setPaddingLeft(10);
	         cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell14 = new PdfPCell(new Paragraph(p.getHighestEducationQualification()));
	         cell14.setPaddingLeft(10);
	         cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell15 = new PdfPCell(new Paragraph("Occupation: "));
	         cell15.setPaddingLeft(10);
	         cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell16 = new PdfPCell(new Paragraph(p.getOccupation()));
	         cell16.setPaddingLeft(10);
	         cell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell17 = new PdfPCell(new Paragraph("Employer: "));
	         cell17.setPaddingLeft(10);
	         cell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell18 = new PdfPCell(new Paragraph(p.getEmployer()));
	         cell18.setPaddingLeft(10);
	         cell18.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell19 = new PdfPCell(new Paragraph("College: "));
	         cell19.setPaddingLeft(10);
	         cell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell20 = new PdfPCell(new Paragraph(p.getCollege()));
	         cell20.setPaddingLeft(10);
	         cell20.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell21 = new PdfPCell(new Paragraph("School: "));
	         cell21.setPaddingLeft(10);
	         cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell22 = new PdfPCell(new Paragraph(p.getSchool()));
	         cell22.setPaddingLeft(10);
	         cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell23 = new PdfPCell(new Paragraph("Eye Color: "));
	         cell23.setPaddingLeft(10);
	         cell23.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell24 = new PdfPCell(new Paragraph(p.getEyecolor()));
	         cell24.setPaddingLeft(10);
	         cell24.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell25 = new PdfPCell(new Paragraph("Weight: "));
	         cell25.setPaddingLeft(10);
	         cell25.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell26 = new PdfPCell(new Paragraph(Double.toString(p.getWeight())));
	         cell26.setPaddingLeft(10);
	         cell26.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell27 = new PdfPCell(new Paragraph("Height: "));
	         cell27.setPaddingLeft(10);
	         cell27.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell28 = new PdfPCell(new Paragraph(Double.toString(p.getHeight())));
	         cell28.setPaddingLeft(10);
	         cell28.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell29 = new PdfPCell(new Paragraph("Drivers Licence: "));
	         cell29.setPaddingLeft(10);
	         cell29.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell30 = new PdfPCell(new Paragraph(Boolean.toString(p.isDriversLicence())));
	         cell30.setPaddingLeft(10);
	         cell30.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell31 = new PdfPCell(new Paragraph("Gender: "));
	         cell31.setPaddingLeft(10);
	         cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell32 = new PdfPCell(new Paragraph(p.getGender()));
	         cell32.setPaddingLeft(10);
	         cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell33 = new PdfPCell(new Paragraph("Phone Number: "));
	         cell33.setPaddingLeft(10);
	         cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell34 = new PdfPCell(new Paragraph(Long.toString(p.getPhoneNumber())));
	         cell34.setPaddingLeft(10);
	         cell34.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell35 = new PdfPCell(new Paragraph("Email: "));
	         cell35.setPaddingLeft(10);
	         cell35.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell36 = new PdfPCell(new Paragraph(p.getEmailAddress()));
	         cell36.setPaddingLeft(10);
	         cell36.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell37 = new PdfPCell(new Paragraph("Address: "));
	         cell37.setPaddingLeft(10);
	         cell37.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell38 = new PdfPCell(new Paragraph(p.getHomeAddress()));
	         cell38.setPaddingLeft(10);
	         cell38.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell39 = new PdfPCell(new Paragraph("Website: "));
	         cell39.setPaddingLeft(10);
	         cell39.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         PdfPCell cell40 = new PdfPCell(new Paragraph(p.getWebsiteAddress()));
	         cell40.setPaddingLeft(10);
	         cell40.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         table.addCell(cell1);
	         table.addCell(cell2);
	         table.addCell(cell3);
	         table.addCell(cell4);
	         table.addCell(cell5);
	         table.addCell(cell6);
	         table.addCell(cell7);
	         table.addCell(cell8);
	         table.addCell(cell9);
	         table.addCell(cell10);
	         table.addCell(cell11);
	         table.addCell(cell12);
	         table.addCell(cell13);
	         table.addCell(cell14);
	         table.addCell(cell15);
	         table.addCell(cell16);
	         table.addCell(cell17);
	         table.addCell(cell18);
	         table.addCell(cell19);
	         table.addCell(cell20);
	         table.addCell(cell21);
	         table.addCell(cell22);
	         table.addCell(cell23);
	         table.addCell(cell24);
	         table.addCell(cell25);
	         table.addCell(cell26);
	         table.addCell(cell27);
	         table.addCell(cell28);
	         table.addCell(cell29);
	         table.addCell(cell30);
	         table.addCell(cell31);
	         table.addCell(cell32);
	         table.addCell(cell33);
	         table.addCell(cell34);
	         table.addCell(cell35);
	         table.addCell(cell36);
	         table.addCell(cell37);
	         table.addCell(cell38);
	         table.addCell(cell39);
	         table.addCell(cell40);
	         
	         document.add(table);
	         document.close();
	         writer.close();
	      } catch (DocumentException e)
	      {
	         e.printStackTrace();
	      } catch (FileNotFoundException e)
	      {
	         e.printStackTrace();
	      }
	      String removeUploadsStr = documentName.replace("uploads", "files");
	      
	      return removeUploadsStr;
	}




	
	
}

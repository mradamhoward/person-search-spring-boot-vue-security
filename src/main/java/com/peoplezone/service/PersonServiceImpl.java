package com.peoplezone.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
	public List<Person> searchForPersonFirstNameLastName(String toSearch) {
		List<Person> results = personRepo.searchByFirstNameContainingOrSurNameContainingAllIgnoreCase(toSearch, toSearch);
		return results;
	}

	@Override
	public List<Person> getAllPeople() {
		return personRepo.findAll();
	}

	@Override
	public List<Person> searchByAllAttributes(String toSearch, int page, int quan, String sortBy, boolean ascending) {
		int ageToSearch = 0;
		try {
			ageToSearch = Integer.parseInt(toSearch);
		} catch (Exception e) {
			
		}
		double toSearchDouble = 0;
		try {
			toSearchDouble = Double.parseDouble(toSearch);
		} catch (Exception e) {
			
		}
		long phoneToSearch = 0;
		try {
			phoneToSearch = Long.parseLong(toSearch);
		} catch (Exception e) {	
			
		}
		
		System.out.println(toSearchDouble);
		
		List<Person> results;
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan, Sort.by(sortBy).descending());
		if(ascending) {
			firstPageWithTwoElements = PageRequest.of(page, quan, Sort.by(sortBy).ascending());
		}
		
		results = personRepo.searchByFirstNameContainingOrSurNameContainingOrAgeOrDescriptionContainingOrHighestEducationQualificationContainingOrOccupationContainingOrEmployerContainingOrCollegeContainingOrSchoolContainingOrEyecolorOrWeightOrHeightOrPPSnumberOrDriversLicenceOrProvisionalLicenceOrBankIBANOrPhoneNumberOrGenderOrEmailAddressOrWebsiteAddressContainingOrHomeAddressContainingAllIgnoreCase(toSearch, toSearch, ageToSearch, toSearch, toSearch, toSearch, toSearch, toSearch, toSearch, toSearch, toSearchDouble, toSearchDouble, toSearch, false, false, toSearch, phoneToSearch, 'G',toSearch, toSearch, toSearch, firstPageWithTwoElements);
		
		
		return results;
		
		
	}
	
	List<Person> removeDuplicatePersons(List<Person> toRemove){
		List<Person> result = toRemove;
		
		 List<Person> listWithoutDuplicates = new ArrayList<>(
			      new HashSet<Person>(result));
		 
		return listWithoutDuplicates;
	}

	@Override
	public List<Person> searchForFirstName(String search, String page, String num, String sortBy, String ascending) {
		// TODO Auto-generated method stub
		
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
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 } 
		return personRepo.searchByFirstNameContainingAllIgnoreCase(search, firstPageWithFourElements);
	}

	@Override
	public List<Person> searchForSecondName(String search, String page, String num, String sortBy, String ascending) {
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
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 }
		return personRepo.searchBySurNameContainingAllIgnoreCase(search, firstPageWithFourElements);
	}

	@Override
	public List<Person> searchForAge(String search, String page, String num, String sortBy, String ascending) {
		 int toAge = 0;
		 try {
			toAge = Integer.parseInt(search);
		 } catch (Exception e) {
			
		 }
		
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
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 }
		 
		 return personRepo.searchByAge(toAge,firstPageWithFourElements);
	}

	@Override
	public List<Person> searchForDOB(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Date dob;
		String DEFAULT_PATTERN = "dd-MM-yyyy";
		String yourDateString = toSearch;
		try {
			DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
			Date myDate = formatter.parse(yourDateString);
		} catch (Exception e) {
			
		}
		//return personRepo;
		return null;
	}

	@Override
	public List<Person> searchForDesc(String search, String page, String num, String sortBy, String ascending) {
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
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 }
		 return personRepo.searchByDescriptionContainingAllIgnoreCase(search,firstPageWithFourElements);
	}

	@Override
	public List<Person> searchForEmail(String search, String page, String num, String sortBy, String ascending) {
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
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 }
		return personRepo.searchByEmailAddressIgnoreCase(search, firstPageWithFourElements);
	}

	@Override
	public List<Person> searchForWebsite(String search, String page, String num, String sortBy, String ascending) {
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
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 }
		return personRepo.searchByWebsiteAddressContainingAllIgnoreCase(search,firstPageWithFourElements);
	}

	@Override
	public List<Person> searchForGender(String search, String page, String num, String sortBy, String ascending) {
		// TODO Auto-generated method stub
		char gender;
		if(search.equalsIgnoreCase("Male")) {
			gender = 'M';
		} else if (search.equalsIgnoreCase("Female")) {
			gender = 'F';
		} else {
			gender = '0';
		}
		
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
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 }

		return personRepo.searchByGender(gender,firstPageWithFourElements);
	}

	@Override
	public List<Person> searchForPhone(String search, String page, String num, String sortBy, String ascending) {
		// TODO Auto-generated method stub
		long number = 0;
		try {
			number = Long.parseLong(search);
		} catch (Exception e) {
			
		}
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
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 }
		return personRepo.searchByPhoneNumber(number,firstPageWithFourElements);
	}

	@Override
	public List<Person> searchForHomeAddress(String search, String page, String num, String sortBy, String ascending) {
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
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 }
		return personRepo.searchByHomeAddressContainingAllIgnoreCase(search,firstPageWithFourElements);
	}

	@Override
	public List<Person> searchForEducation(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByHighestEducationQualificationContainingAllIgnoreCase(toSearch,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForOccupation(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByOccupationContainingAllIgnoreCase(toSearch,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForEmployer(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByEmployerContainingAllIgnoreCase(toSearch, firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForCollege(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByCollegeContainingAllIgnoreCase(toSearch, firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForSchool(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchBySchoolContainingAllIgnoreCase(toSearch, firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForEyeColor(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByEyecolorContainingAllIgnoreCase(toSearch,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForWeight(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		int weight = 0;
		try {
			weight = Integer.parseInt(toSearch);
		} catch (Exception e) {
			
		}
		return personRepo.searchByWeight(weight,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForHeight(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		int height = 0;
		try {
			height = Integer.parseInt(toSearch);
		} catch (Exception e) {
			
		}
		return personRepo.searchByWeight(height,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForPPS(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByPPSnumberIgnoreCase(toSearch,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForDriverLicence(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		boolean driver = false;
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		//todo
		return personRepo.searchByDriversLicence(driver, firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForProvisional(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		boolean driver = false;
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		//todo
		return personRepo.searchByDriversLicence(driver, firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForIBAN(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByBankIBANAllIgnoreCase(toSearch,firstPageWithTwoElements);
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
		 PersonSpecification spec13 = new PersonSpecification(new SearchCriteria("PPSnumber", "=", search));
		 PersonSpecification spec16 = new PersonSpecification(new SearchCriteria("bankIBAN", "=", search));
		 PersonSpecification spec17 = new PersonSpecification(new SearchCriteria("phoneNumber", "=", search));
		 PersonSpecification spec18 = new PersonSpecification(new SearchCriteria("gender", "=", search));
		 PersonSpecification spec19 = new PersonSpecification(new SearchCriteria("emailAddress", "=", search));
		 PersonSpecification spec20 = new PersonSpecification(new SearchCriteria("websiteAddress", ":", search));
		 PersonSpecification spec21 = new PersonSpecification(new SearchCriteria("homeAddress", ":", search));
		 PersonSpecification spec22 = new PersonSpecification(new SearchCriteria("PPSnumber", ":", search));
		 
		 Pageable firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.DESC, sortBy));;
	
		 
		 if(asc) {
			 firstPageWithFourElements = PageRequest.of(pageNum, numResults, Sort.by(Sort.Direction.ASC, sortBy));
		 } 
		 
		 Page<Person> results = personRepo.findAll(Specification.where(spec).or(spec2).or(spec3).or(spec4).or(spec4).or(spec5).or(spec6).or(spec7).or(spec8).or(spec9).or(spec10).or(spec11).or(spec12).or(spec13).or(spec16).or(spec17).or(spec18).or(spec19).or(spec20).or(spec21).or(spec22), firstPageWithFourElements);
		 List<Person> res = results.getContent();
		 
		 return res;
	}

	@Override
	public List<Person> searchBySpecFirstname(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchBySpecAll(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchForFirstName(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchForSecondName(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchForAge(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchForDesc(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchForEmail(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchForWebsite(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchForGender(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchForPhone(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> searchForHomeAddress(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	
	
}

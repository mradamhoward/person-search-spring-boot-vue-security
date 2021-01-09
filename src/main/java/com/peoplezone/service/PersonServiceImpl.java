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
	public List<Person> searchForFirstName(String toSearch, int page, int quantity) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quantity);
		return personRepo.searchByFirstNameContainingAllIgnoreCase(toSearch, firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForSecondName(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchBySurNameContainingAllIgnoreCase(toSearch,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForAge(String toSearch, int page, int quan) {
		int toAge = 0;
		try {
			toAge = Integer.parseInt(toSearch);
		} catch (Exception e) {
			
		}
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByAge(toAge,firstPageWithTwoElements);
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
	public List<Person> searchForDesc(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByDescriptionContainingAllIgnoreCase(toSearch,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForEmail(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByEmailAddressIgnoreCase(toSearch,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForWebsite(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByWebsiteAddressContainingAllIgnoreCase(toSearch,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForGender(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		char gender;
		if(toSearch.equalsIgnoreCase("Male")) {
			gender = 'M';
		} else if (toSearch.equalsIgnoreCase("Female")) {
			gender = 'F';
		} else {
			gender = '0';
		}
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByGender(gender,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForPhone(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		long number = 0;
		try {
			number = Long.parseLong(toSearch);
		} catch (Exception e) {
			
		}
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByPhoneNumber(number,firstPageWithTwoElements);
	}

	@Override
	public List<Person> searchForHomeAddress(String toSearch, int page, int quan) {
		// TODO Auto-generated method stub
		Pageable firstPageWithTwoElements = PageRequest.of(page, quan);
		return personRepo.searchByHomeAddressContainingAllIgnoreCase(toSearch,firstPageWithTwoElements);
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
	
	 
	
	
}

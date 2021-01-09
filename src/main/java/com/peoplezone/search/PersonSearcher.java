package com.peoplezone.search;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.peoplezone.models.Person;
import com.peoplezone.repo.PersonRepo;

@Component
public class PersonSearcher {

    @Autowired
    private PersonRepo personRepo;

    /*
        Would be better taking a "Form"/Object with your search criteria.
     */
    
    //Called in controller searchSpecific under all case in switch attr
    public List<Person> search(String toSearch, Integer age, double toSearchDouble, long toSearchLong, char gender, Pageable pageable) {

        //Get "all"
        Specification<Person> personSpecification = Specification.not(null);

        //Create "Predicates" (like the where clauses).
        personSpecification = personSpecification.and(new MyPersonSpec("firstName", toSearch, "equal"));
        //personSpecification = personSpecification.and(new MyPersonSpec("surName", toSearch, "equal"));
        /*
        personSpecification = personSpecification.and(new MyPersonSpec("age", age, "equal"));
        personSpecification = personSpecification.and(new MyPersonSpec("description", toSearch, "like"));
        personSpecification = personSpecification.and(new MyPersonSpec("highestEducationQualification", toSearch, "like"));
        personSpecification = personSpecification.and(new MyPersonSpec("occupation", toSearch, "like"));
        personSpecification = personSpecification.and(new MyPersonSpec("employer", toSearch, "like"));
        personSpecification = personSpecification.and(new MyPersonSpec("college", toSearch, "like"));
        personSpecification = personSpecification.and(new MyPersonSpec("school", toSearch, "like"));
        personSpecification = personSpecification.and(new MyPersonSpec("eyecolor", toSearch, "equal"));
        personSpecification = personSpecification.and(new MyPersonSpec("weight", toSearchDouble, "equal"));
        personSpecification = personSpecification.and(new MyPersonSpec("height", toSearchDouble, "equal"));
        personSpecification = personSpecification.and(new MyPersonSpec("PPSnumber", toSearch, "equal"));
        personSpecification = personSpecification.and(new MyPersonSpec("bankIBAN", toSearch, "equal"));
        personSpecification = personSpecification.and(new MyPersonSpec("phoneNumber", toSearchLong, "equal"));
        personSpecification = personSpecification.and(new MyPersonSpec("gender", gender, "equal"));
        personSpecification = personSpecification.and(new MyPersonSpec("emailAddress", toSearch, "equal"));
        personSpecification = personSpecification.and(new MyPersonSpec("websiteAddress", toSearch, "like"));
        personSpecification = personSpecification.and(new MyPersonSpec("homeAddress", toSearch, "like"));
        */
       
        //Run query using Repo. Spring paging still works.
        
        Page<Person> filterdPersons = personRepo.findAll(personSpecification, pageable);
        List<Person> p = filterdPersons.getContent();
        return p;
    }

    private static class MyPersonSpec implements Specification<Person> {

        private final String field;
        private final Object value;
        private final String operation;

        private MyPersonSpec(String field, Object value, String operation) {
            this.field = field;
            this.value = value;
            this.operation = operation;
        }

        @Override
        public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            switch (operation) {
                case "like":
                    return criteriaBuilder.like(root.get(field), "%" + value.toString().toLowerCase() + "%");
                case "equal":
                    return criteriaBuilder.equal(root.get(field), value);
                case "gt":
                    return criteriaBuilder.greaterThan(root.get(field), (int) value);
                case "lt":
                    return criteriaBuilder.lessThan(root.get(field), (int) value);

                default:
                    throw new RuntimeException("Unexpected `op`.");
            }
        }
    }
}
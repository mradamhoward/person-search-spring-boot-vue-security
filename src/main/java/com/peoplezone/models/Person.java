package com.peoplezone.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private long id;
	
	private String firstName;
	
	private String surName;
	
	private int age;
	
    private Date dob;
	
	private String description;
	
	private String highestEducationQualification;
	
	private String occupation;
	
	private String employer;
	
	private String college;
	
	private String school;
	
	private String eyecolor;
	
	private double weight;
	
	private double height;
	
	private boolean driversLicence;
	
	private boolean provisionalLicence;

	private long phoneNumber;
	
	private char gender;
	
	private String emailAddress;
	
	private String websiteAddress;
	
	private String homeAddress;
	
	private String image;
	
}

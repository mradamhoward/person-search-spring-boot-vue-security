package com.peoplezone;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.peoplezone.service.FilesStorageService;

@EnableJpaRepositories()
@SpringBootApplication
public class PeopleZoneApplication implements CommandLineRunner{
	@Resource
	FilesStorageService storageService;
	  
	public static void main(String[] args) {
		SpringApplication.run(PeopleZoneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//storageService.deleteAll();
	    storageService.init();
	}

}

package com.peoplezone.config;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.peoplezone.dto.SocialProvider;
import com.peoplezone.models.Person;
import com.peoplezone.models.Product;
import com.peoplezone.models.Role;
import com.peoplezone.models.User;
import com.peoplezone.repo.PersonRepo;
import com.peoplezone.repo.ProductRepo;
import com.peoplezone.repo.RoleRepository;
import com.peoplezone.repo.UserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProductRepo prodRepo;
	
	@Autowired
	private PersonRepo personRepo;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}
		// Create initial roles
		Role userRole = createRoleIfNotFound(Role.ROLE_USER);
		Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);
		Role modRole = createRoleIfNotFound(Role.ROLE_MODERATOR);
		createUserIfNotFound("admin@javachinna.com", Set.of(userRole, adminRole, modRole));
		createUserIfNotFound("info@mup.ie", Set.of(userRole, adminRole, modRole));
		alreadySetup = true;
		
//		createProductIfNotFound("Airmax 90", "Nike", "Trainers");
//		createProductIfNotFound("Casual Trainers", "Tommy Hilfiger", "Trainers");
//		createProductIfNotFound("Hi tops", "Nike", "Hi tops");
//		
//		createPersonIfNotFound("Adam", "Howard");
//		createPersonIfNotFound("Fintan", "Linehan");
//		createPersonIfNotFound("Darren", "O'Gorman");
//		
	}
	
	@Transactional
	private final Person createPersonIfNotFound(String firstName, String lastName) {
		Person person = personRepo.findByFirstName(firstName);
		if(person == null) {
			person = new Person();
			person.setFirstName(firstName);
			person.setSurName(lastName);
			
			personRepo.save(person);
		}
		
		return person;	
	}
	
	@Transactional
	private final Product createProductIfNotFound(String title, String brand, String desc) {
		Product prod = prodRepo.findByTitle(title);
		if(prod == null){
			prod = new Product();
			prod.setTitle(title);
			prod.setBrand(brand);
			prod.setCategory("Shoes");
			prod.setDescription(desc);
			prod.setImgFile("Nike Img");
			prod.setPrice(100.50);
			prodRepo.save(prod);
		}
		
		return prod;	
	}

	@Transactional
	private final User createUserIfNotFound(final String email, Set<Role> roles) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setDisplayName("Admin");
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode("Zalman1729"));
			user.setRoles(roles);
			user.setProvider(SocialProvider.LOCAL.getProviderType());
			user.setEnabled(true);
			Date now = Calendar.getInstance().getTime();
			user.setCreatedDate(now);
			user.setModifiedDate(now);
			user = userRepository.save(user);
		}
		return user;
	}

	@Transactional
	private final Role createRoleIfNotFound(final String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = roleRepository.save(new Role(name));
		}
		return role;
	}
	
	/*
	 * @Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
	    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
	    factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
	        @Override
	        public void customize(Connector connector) {
	            connector.setProperty("relaxedQueryChars", "|{}[]");
	        }
	    });
	    return factory;
	}
	 */
	
}
package com.peoplezone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peoplezone.config.CurrentUser;
import com.peoplezone.dto.LocalUser;
import com.peoplezone.models.Person;
import com.peoplezone.models.Product;
import com.peoplezone.repo.ProductRepo;
import com.peoplezone.service.UserServiceImpl;
import com.peoplezone.util.GeneralUtils;
import com.peoplezone.service.ProductService;
import com.peoplezone.service.ProductServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepo productRepo;
	
	@CrossOrigin
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
		return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getContent() {
		List<Product> products = productRepo.searchByTitleContainingOrDescriptionContainingOrBrandContainingAllIgnoreCase("tom", "tom", "tom");
		return ResponseEntity.ok(products);
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getUserContent() {
		
		return ResponseEntity.ok("User content goes here");
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAdminContent() {
		return ResponseEntity.ok("Admin content goes here");
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> getModeratorContent() {
		return ResponseEntity.ok("Moderator content goes here");
	}
	

}
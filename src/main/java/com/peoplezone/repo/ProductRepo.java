package com.peoplezone.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peoplezone.models.Product;
import com.peoplezone.models.User;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	Product findById(long id);
	Product findByTitle(String title);
	
	List<Product> searchByTitleContainingOrDescriptionContainingOrBrandContainingAllIgnoreCase(String title, String description, String brand);
	
}

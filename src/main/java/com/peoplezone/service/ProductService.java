package com.peoplezone.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.peoplezone.models.Product;

import java.util.List;


public interface ProductService {
	
	Product getProductById(long id);
	
	List<Product> getAllProducts();
}

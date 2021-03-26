package com.peoplezone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peoplezone.models.Product;
import com.peoplezone.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {


	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public Product getProductById(long id) {
		Product product = productRepo.findById(id);
		
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}
	
}

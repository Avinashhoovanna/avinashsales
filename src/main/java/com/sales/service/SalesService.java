package com.sales.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sales.dto.ProductDTO;
import com.sales.model.Product;
import com.sales.repository.SalesRepository;

@Service
public class SalesService {
	@Autowired
	SalesRepository repository;

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public Product addProductforSale(ProductDTO productDTO) {
		productDTO.setOrderDescription("ReadyToSale");
		Product product = getModelMapper().map(productDTO, Product.class);
		Product productSave = repository.save(product);
		return productSave;
	}

	public Product getProductByName(String name) {
		Product product = repository.findByName(name);
		return product;
	}

	@Transactional
	public int updateProductByName(String name, String orderDescription) {
		int result = repository.updateProductByName(orderDescription, name);
		return result;
	}

	public List<Product> getAllProducts() {
		Iterable<Product> items = repository.findAll();
		return (List<Product>) items;
	}

	@Transactional
	public int cancelProductByName(String name) {
		String cancel = "canceled";
		int result = repository.cancelProductByName(cancel, name);
		return result;

	}
}

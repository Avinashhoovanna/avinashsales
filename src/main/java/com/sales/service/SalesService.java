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
		return repository.save(product);
	}

	public Product getProductByName(String name) {
		return repository.findByName(name);
	}

	@Transactional
	public int updateProductByName(String name, String orderDescription) {
		return repository.updateProductByName(orderDescription, name);
	}

	public List<Product> getAllProducts() {
		Iterable<Product> items = repository.findAll();
		return (List<Product>) items;
	}

	@Transactional
	public int cancelProductByName(String name) {
		String cancel = "canceled";
		return repository.cancelProductByName(cancel, name);
	}
}

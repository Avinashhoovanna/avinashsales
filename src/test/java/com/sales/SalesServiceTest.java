package com.sales;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.sales.dto.ProductDTO;
import com.sales.model.Product;
import com.sales.repository.SalesRepository;
import com.sales.service.SalesService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SalesServiceTest {
	
	@InjectMocks
	SalesService salesService;
	
	@Mock
	SalesRepository repository;
	
	public Product getProduct() {
		Product product = new Product();
		product.setId(1);
		product.setName("Nokia");
		product.setOrderDescription("ReadyToSale");
		product.setPrice(1000.00);
		return product;
	}
	
	public ProductDTO getProductDTO() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(1);
		productDTO.setName("Nokia");
		productDTO.setOrderDescription("ReadyToSale");
		productDTO.setPrice(1000.00);
		return productDTO;
	}
	
	@Test
	public void addProductforSaleTest() {
		when(repository.save(getProduct())).thenReturn(getProduct());
		Product product = salesService.addProductforSale(getProductDTO());
		assertNotNull(product);
	}
	
	@Test
	public void getProductByName() {
		when(repository.findByName("Nokia")).thenReturn(getProduct());
		Product product = salesService.getProductByName("Nokia");
		assertNotNull(product);
	}

}

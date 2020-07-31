package com.sales;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.sales.controller.SalesController;
import com.sales.dto.ProductDTO;
import com.sales.model.Product;
import com.sales.service.SalesService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SalesServiceControllerTest {

	@InjectMocks
	SalesController salesController;

	@Mock
	SalesService salesService;

	@Test
	public void getAllItemsTest() {
		when(salesService.getAllProducts()).thenReturn(new ArrayList<Product>());
		List<Product> products = salesController.getAllItems();
		assertNotNull(products);
	}

	@Test
	public void getItemByNameTest() {
		when(salesService.getProductByName("Nokia")).thenReturn(new Product());
		Product product = salesController.getItemByName("Nokia");
		assertNotNull(product);
	}

	@Test
	public void cancelProductByNameTest() {
		when(salesService.cancelProductByName("Nokia")).thenReturn(1);
		String actual = salesController.cancelProductByName("Nokia");
		String expected = "Order Cancelled succesfully......";
		assertEquals(expected, actual);
	}

	@Test
	public void addProductTest() {
		// when(salesService.addProductforSale(new ProductDTO())).thenReturn(new
		// Product());
		boolean actual = salesController.addProduct(new ProductDTO());
		assertTrue(actual);
	}

	@Test
	public void orderProductTest() {
		Product product = new Product();
		when(salesService.getProductByName("Nokia")).thenReturn(product);
		// when(salesService.updateProductByName("Nokia",
		// product.getOrderDescription())).thenReturn(true);
		String expected = "Placed Order Successfully...";
		String actual = salesController.orderProduct("Nokia");
		assertEquals(expected, actual);
	}

}

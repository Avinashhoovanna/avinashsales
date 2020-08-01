package com.sales.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sales.dto.ProductDTO;
import com.sales.model.Product;
import com.sales.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {
	@Autowired
	SalesService service;
	
	private static final Logger log = LogManager.getLogger(SalesController.class);
	
	//API used for ordering the product
	@PostMapping("/orderProduct/{name}")
	public String orderProduct(@PathVariable String name) {
		String message = "";
		try {
			log.info("Inside order product method.");
			if (null != name) {
				Product product = service.getProductByName(name);
				if (null != product) {
					product.setOrderDescription("Ordered");
					service.updateProductByName(name, product.getOrderDescription());
					message = "Placed Order Successfully...";
				}
			} else {
				message = "Not able to place Order";
			}
		} catch (Exception ex) {
			log.debug(ex.getMessage());
		}
		return message;
	}
	
	//API used for getting all products in table
	@GetMapping("/getAllProducts")
	public List<Product> getAllItems() {
		List<Product> allProducts = null;
		try {
			log.debug("Inside getAllItems method.");
			allProducts = service.getAllProducts();
		} catch (Exception ex) {
			log.debug(ex.getMessage());
		}
		return allProducts;
	}

	//API for getting product based on name
	@GetMapping("/getProductByName/{name}")
	public Product getItemByName(@PathVariable String name) {
		Product product = null;
		try {
			log.debug("Inside getItemByName method");
			product = service.getProductByName(name);
		} catch (Exception ex) {
			log.debug(ex.getMessage());
		}
		return product;
	}
	
	//API for canceling the product
	@DeleteMapping("/cancelProductByName/{name}")
	public String cancelProductByName(@PathVariable String name) {
		String message = "";
		try {
			log.debug("Inside cancelProductByName method");
			service.cancelProductByName(name);
			message = "Order Cancelled succesfully......";
		} catch (Exception e) {
			message = "Not able to process the Request";
			log.debug(e.getMessage());
		}
		return message;

	}

	// used by Inventory Microservice for adding product.
	@PostMapping("/addProductForSale")
	public boolean addProduct(@RequestBody ProductDTO productDTO) {
		boolean result = false;
		try {
			log.debug("Inside addProduct method called by Inventory Service");
			service.addProductforSale(productDTO);
			result = true;
		} catch (Exception ex) {
			result = false;
			log.debug(ex.getMessage());
		}
		return result;
	}

}

package com.sales.controller;

import java.util.List;
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

	@PostMapping("/orderProduct/{name}")
	public String orderProduct(@PathVariable String name) {
		String message = "";
		try {
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
			message = "Not able to process the Request..";
			ex.printStackTrace();
		}
		return message;
	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllItems() {
		List<Product> allProducts = null;
		try {
			allProducts = service.getAllProducts();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return allProducts;
	}

	@GetMapping("/getProductByName/{name}")
	public Product getItemByName(@PathVariable String name) {
		Product product = null;
		try {
			product = service.getProductByName(name);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return product;
	}

	@DeleteMapping("/cancelProductByName/{name}")
	public String cancelProductByName(@PathVariable String name) {
		String message = "";
		try {
			service.cancelProductByName(name);
			message = "Order Cancelled succesfully......";
		} catch (Exception e) {
			message = "Not able to process the Request";
			e.printStackTrace();
		}
		return message;

	}

	// used by Inventory Microservice..
	@PostMapping("/addProductForSale")
	public boolean addProduct(@RequestBody ProductDTO productDTO) {
		boolean result = false;
		try {
			service.addProductforSale(productDTO);
			result = true;
		} catch (Exception ex) {
			result = false;
			ex.printStackTrace();
		}
		return result;
	}

}

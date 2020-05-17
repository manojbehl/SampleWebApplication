package com.ibm.ignite.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.ignite.model.Product;
import com.ibm.ignite.service.ProductService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/products")
public class ProdcutController {
	
	@Autowired
	ProductService productService;

	 @GetMapping(value = { "", "/" })
	    public @NotNull Iterable<Product> getProducts() {
	        return productService.getAllProducts();
	    }

}

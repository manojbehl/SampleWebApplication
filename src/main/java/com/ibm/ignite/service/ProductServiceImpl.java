package com.ibm.ignite.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ibm.ignite.exception.ResourceNotFoundException;
import com.ibm.ignite.model.Product;
import com.ibm.ignite.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	 	private ProductRepository productRepository;

	    public ProductServiceImpl(ProductRepository productRepository) {
	        this.productRepository = productRepository;
	    }

	    @Override
	    public Iterable<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    @Override
	    public Product getProduct(long id) {
	        return productRepository
	          .findById(id)
	          .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	    }

	    @Override
	    public Product save(Product product) {
	        return productRepository.save(product);
	    }

}

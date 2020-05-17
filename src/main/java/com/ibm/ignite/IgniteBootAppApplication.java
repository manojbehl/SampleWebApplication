package com.ibm.ignite;

import java.util.Base64;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ibm.ignite.model.Product;
import com.ibm.ignite.model.User;
import com.ibm.ignite.service.ProductService;
import com.ibm.ignite.service.UserServiceImpl;

@SpringBootApplication
public class IgniteBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgniteBootAppApplication.class, args);
	}

	@Bean
    CommandLineRunner runner(ProductService productService, UserServiceImpl userService) {
		String str = new String("1111");
        return args -> {
            productService.save(new Product(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
            productService.save(new Product(2L, "Game Console", 200.00, "http://placehold.it/200x100"));
            productService.save(new Product(3L, "Sofa", 100.00, "http://placehold.it/200x100"));
            productService.save(new Product(4L, "Icecream", 5.00, "http://placehold.it/200x100"));
            productService.save(new Product(5L, "Beer", 3.00, "http://placehold.it/200x100"));
            productService.save(new Product(6L, "Phone", 500.00, "http://placehold.it/200x100"));
            productService.save(new Product(7L, "Watch", 30.00, "http://placehold.it/200x100"));
            
            userService.create(
            		new User(1L, 
            				"manoj.behl@gmail.com", "Manoj","Behl", 
            				new String( Base64.getEncoder().encode(str.getBytes())) ) 
            		);
        };
    }
	
	
}

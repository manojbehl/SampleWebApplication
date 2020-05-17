package com.ibm.ignite.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.ignite.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}

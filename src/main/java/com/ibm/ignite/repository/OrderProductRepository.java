package com.ibm.ignite.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.ignite.model.OrderProduct;
import com.ibm.ignite.model.OrderProductPK;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK>{

}

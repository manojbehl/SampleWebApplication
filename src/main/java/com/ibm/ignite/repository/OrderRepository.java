package com.ibm.ignite.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.ignite.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

	public Iterable<Order> findByUserId(long userId);
}

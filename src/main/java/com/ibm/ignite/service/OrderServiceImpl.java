package com.ibm.ignite.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ignite.model.Order;
import com.ibm.ignite.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
     
	@Override
    public Iterable<Order> getOrders(long userId) {
        return this.orderRepository.findByUserId(userId);
    }
	
    @Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());
        return this.orderRepository.save(order);
    }
 
    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }
}

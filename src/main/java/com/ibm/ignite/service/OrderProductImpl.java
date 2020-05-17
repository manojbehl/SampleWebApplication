package com.ibm.ignite.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ignite.model.OrderProduct;
import com.ibm.ignite.repository.OrderProductRepository;

@Service
@Transactional
public class OrderProductImpl implements OrderProductService{

	@Autowired
    OrderProductRepository orderProductRepository;

 
    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }



}

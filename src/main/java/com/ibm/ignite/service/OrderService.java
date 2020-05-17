package com.ibm.ignite.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ibm.ignite.model.Order;

public interface OrderService {

 	@NotNull Iterable<Order> getAllOrders();

    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
    
    Iterable<Order> getOrders(long userId);

}

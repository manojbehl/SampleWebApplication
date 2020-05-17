package com.ibm.ignite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.ignite.dto.OrderProductDto;
import com.ibm.ignite.exception.ResourceNotFoundException;
import com.ibm.ignite.model.Order;
import com.ibm.ignite.model.OrderProduct;
import com.ibm.ignite.model.OrderStatus;
import com.ibm.ignite.service.OrderProductService;
import com.ibm.ignite.service.OrderService;
import com.ibm.ignite.service.ProductService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	ProductService productService;
    @Autowired
	OrderService orderService;
    @Autowired
    OrderProductService orderProductService;
	
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }
    
    @GetMapping(path="/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> ordersByUserId(@PathVariable(name="userId", required=true)long userId) {
        return this.orderService.getOrders(userId);
    }
    
	@PostMapping
	public ResponseEntity<Order> create(@RequestBody OrderForm form) {
		List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order.setUserId(form.getUserId());
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto
              .getProduct()
              .getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(order.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);	
   }
	
    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
          .stream()
          .filter(op -> Objects.isNull(productService.getProduct(op
            .getProduct()
            .getId())))
          .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

	
	public static class OrderForm {

        private List<OrderProductDto> productOrders;
        
        private long userId;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }

		public long getUserId() {
			return userId;
		}

		public void setUserId(long userId) {
			this.userId = userId;
		}
    }
}

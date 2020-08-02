package com.demo.microservices.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.microservices.orderservice.model.OrderItem;

@FeignClient(name="order-item-service",url="localhost:8000")
public interface OrderItemProxy {
	
	@GetMapping(path="/getOrderItem")
	public OrderItem getOrderItem(@RequestParam("id") Long id);

}

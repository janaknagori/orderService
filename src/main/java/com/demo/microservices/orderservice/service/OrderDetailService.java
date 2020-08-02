package com.demo.microservices.orderservice.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.microservices.orderservice.model.OrderDetail;

public interface OrderDetailService extends JpaRepository<OrderDetail, Long>{
	List<OrderDetail>  findByCustomerEmailAndStatus(String email,boolean status);
	OrderDetail findByOrderIdAndStatus(Long orderCode,boolean status);
}

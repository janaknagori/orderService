package com.demo.microservices.orderservice.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.orderservice.Exception.OrderNotFoundException;
import com.demo.microservices.orderservice.model.OrderDetail;
import com.demo.microservices.orderservice.model.OrderItem;
import com.demo.microservices.orderservice.response.OrderResponse;
import com.demo.microservices.orderservice.service.OrderDetailService;
import com.demo.microservices.orderservice.service.OrderItemProxy;
import com.demo.microservices.orderservice.utility.OrderUtility;
import com.demo.microservices.orderservice.utility.Validation;

@RestController
public class OrderController {
	
	@Autowired
	private OrderItemProxy proxy;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	/**
	 * This will place orders
	 * 
	 * @param orderDetail
	 * @param orderItemIds
	 * @return OrderResponse
	 */
	@PostMapping(path="/placeOrder")
	public OrderResponse placeOrder(@RequestBody OrderDetail orderDetail, @RequestParam String orderItemIds) {
		List<String> errorList = Validation.validateOrderDetail(orderDetail);
		if(!CollectionUtils.isEmpty(errorList)) {
			return OrderUtility.getServiceResponse(errorList);
		}else {
			try {
				double totalAmt = 0;
				//Set<OrderItem> items = new HashSet<OrderItem>();
				String orderItems = "";
				if(orderItemIds.contains(",")) {
					String[] idArr = orderItemIds.split(",");
					for (String string : idArr) {
						OrderItem resposne = proxy.getOrderItem(Long.parseLong(string));
						//items.add(new OrderItem(resposne.getItemId(),resposne.getProductCode(),resposne.getProductName(),resposne.getProductPrice(),resposne.getQuantity()));
						orderItems += resposne.getItemId()+",";
						totalAmt += resposne.getProductPrice();
					}
				}else {
					OrderItem resposne = proxy.getOrderItem(Long.parseLong(orderItemIds));
					//items.add(new OrderItem(resposne.getItemId(),resposne.getProductCode(),resposne.getProductName(),resposne.getProductPrice(),resposne.getQuantity()));
					orderItems += resposne.getItemId();
				}
				if(orderItems.contains(",")) {
					orderItems = orderItems.substring(0, orderItems.length()-1);
				}
				orderDetail.setTotal(totalAmt);
				orderDetail.setOrderItems(orderItems);
				orderDetail.setStatus(true);
				orderDetailService.save(orderDetail);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return OrderUtility.getServiceResponse(orderDetail);
		}
	}
	
	
	/**
	 * This will fetch Orders By Email
	 * 
	 * @param customerEmail
	 * @return OrderResponse
	 */
	@GetMapping(path="/getOrderResponseOrderResponse")
	public OrderResponse getOrdersByEmail(@RequestParam String customerEmail){
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		try {
			orderDetails = orderDetailService.findByCustomerEmailAndStatus(customerEmail,true);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return OrderUtility.getServiceResponse(orderDetails);
	}
	
	/**
	 * This will fetch Orders By OrderId
	 * 
	 * @param orderId
	 * @return OrderResponse
	 */
	@GetMapping(path="/getOrdersByOrderId")
	public OrderResponse getOrdersByOrderId(@RequestParam String orderId){
		OrderDetail orderDetails = null;
		
		orderDetails = orderDetailService.findByOrderIdAndStatus(Long.parseLong(orderId),true);
		if(orderDetails == null) {
			throw new OrderNotFoundException("Order with order id : "+orderId+" is not found");
		}
		
		return OrderUtility.getServiceResponse(orderDetails);
	}

}

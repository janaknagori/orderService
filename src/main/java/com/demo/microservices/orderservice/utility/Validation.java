package com.demo.microservices.orderservice.utility;

import java.util.ArrayList;
import java.util.List;

import com.demo.microservices.orderservice.model.OrderDetail;

public class Validation {
	
	/**
	 * Validate OrderDetail fields
	 * 
	 * @param orderDetail
	 * @return List<String>
	 */
	public static List<String> validateOrderDetail(OrderDetail orderDetail) {
		List<String> messageList = new ArrayList<String>();
		if(orderDetail != null) {
			if(!OrderUtility.emailVaidate(orderDetail.getCustomerEmail())) {
				messageList.add("Email should have in proper format");
			}
			if(!OrderUtility.strSize(orderDetail.getCustomerName())) {
				messageList.add("Customer Name should have atleast 4 characters");
			}
			if(!OrderUtility.addressValid(orderDetail.getShippingAddress())) {
				messageList.add("Shipping Address should have atleast 50 characters");
			}
		}
		
		return messageList;
	}

}

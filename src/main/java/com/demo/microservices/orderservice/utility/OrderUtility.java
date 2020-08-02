package com.demo.microservices.orderservice.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.demo.microservices.orderservice.constant.AppConstant;
import com.demo.microservices.orderservice.response.OrderResponse;

public class OrderUtility {
	
	private static final String regex = "^(.+)@(.+)$";
	
	public static OrderResponse getServiceResponse(final Object res, final String errorMessage) {
		if (null != res) {
			return getSuccessServiceResponse(res);
		} else {
			return getFailureServiceResponse(errorMessage);
		}
	}

	public static OrderResponse getServiceResponse(final Object res) {
		return getServiceResponse(res, AppConstant.NO_RECORD_FOUND);
	}

	public static OrderResponse getSuccessServiceResponse(final Object result) {
		OrderResponse response = new OrderResponse();
		response.setStatus(AppConstant.SUCCESS);
		response.setResponseMessage(AppConstant.SUCCESS);
		response.setResObject(result);
		return response;
	}

	/**
	 * get Failure Service Response with message
	 * 
	 * @param message
	 * @return ServiceResponse
	 */
	public static OrderResponse getFailureServiceResponse(final String message) {
		OrderResponse response = new OrderResponse();
		response.setStatus(AppConstant.FAILED);
		response.setResponseMessage(message);
		return response;
	}

	/**
	 * get Failure Service Response with message and object
	 * 
	 * @param message
	 * @param result
	 * @return ServiceResponse
	 */
	public static OrderResponse getFailureServiceResponse(final String message, final Object result) {
		OrderResponse response = new OrderResponse();
		response.setStatus(AppConstant.FAILED);
		response.setResponseMessage(message);
		response.setResObject(result);
		return response;
	}
	
	/**
	 * Validate double value
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean hasPrice(Double value) {
		if (value != null && value.doubleValue() > 0)
			return true;
		return false;
	}
	
	/**
	 * Validate integer value
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean hasQty(Integer value) {
		if (value != null && value.intValue() > 0)
			return true;
		return false;
	}
	
	/**
	 * Validate string size
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean strSize(String value) {
		if (value != null && value.length() > 4)
			return true;
		return false;
	}
	
	/**
	 * Validate address field
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean addressValid(String value) {
		if (value != null && value.length() > 50)
			return true;
		return false;
	}
	
	/**
	 * Validate Email format
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean emailVaidate(String value) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		if (matcher.matches())
			return true;
		return false;
	}
	
}

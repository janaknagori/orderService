
package com.demo.microservices.orderservice.response;

public class OrderResponse {
	
private static final long serialVersionUID = 2175476829866293178L;
	
	private String status;
	private String responseMessage;
	private Object resObject;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getResObject() {
		return resObject;
	}
	public void setResObject(Object resObject) {
		this.resObject = resObject;
	}

}

package com.demo.microservices.orderservice.model;

public class OrderItem {
	
	private Long itemId;
	private String productCode;
	private String productName;
	private double productPrice;
	private int quantity;
	private boolean status;
	
	public OrderItem() {}
	
	public OrderItem(Long itemId, String productCode, String productName, double productPrice, int quantity) {
		super();
		this.itemId = itemId;
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}

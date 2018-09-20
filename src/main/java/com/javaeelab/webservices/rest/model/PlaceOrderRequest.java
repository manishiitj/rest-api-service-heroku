package com.javaeelab.webservices.rest.model;

import lombok.Data;

@Data
public class PlaceOrderRequest {
	public String itemId;
	public String customerId;
	public String sellerId;
}

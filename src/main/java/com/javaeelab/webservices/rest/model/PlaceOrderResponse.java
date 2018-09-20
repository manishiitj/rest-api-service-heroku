package com.javaeelab.webservices.rest.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlaceOrderResponse {
	public String date;
	public String status;
	public String orderId;
}

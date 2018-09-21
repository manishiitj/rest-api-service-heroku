package com.javaeelab.webservices.rest.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

@Data
@Builder
public class Order {
	private String orderid;
	private String itemid;
	private String sellerid;
	private String customerid;
	private String entityid;
	private String status;
	private int weight;
	private String nextcycle;
}
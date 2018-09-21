package com.javaeelab.webservices.rest.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

@Data
@Entity
@Table(name = "hackorders")
public class Order {
	@Id
	private String orderid;
	private String itemid;
	private String sellerid;
	private String customerid;
	private String entityid;
	private String status;
	private int weight;
	private int nextcycle;
}
package com.javaeelab.webservices.rest;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "label")
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}

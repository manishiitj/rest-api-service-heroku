package com.javaeelab.webservices.rest;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "label")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

}

package com.javaeelab.webservices.rest.model;

import lombok.Builder;

import java.util.List;

@Builder
public class Entity {

    public String entityId;

    public String nextCycle;

    public int capacityLeft;

    public List<String> orders;
}

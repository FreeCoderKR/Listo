package com.example.listo.dto;

import lombok.Data;

import java.security.acl.Owner;

@Data
public class RestaurantRegisterDto {
    private String name;
    private String location;
    private int capacity;
    private Long ownerId;
}

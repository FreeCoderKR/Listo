package com.example.listo.dto;

import lombok.Data;


@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private String location;
    private int capacity;
    private Long ownerId;
}

package com.example.listo.dto.restaurant;

import lombok.Data;


@Data
public class RestaurantReqDto {
    private Long ownerId;
    private String name;
    private String location;
    private String phone;
    private int capacity;
}

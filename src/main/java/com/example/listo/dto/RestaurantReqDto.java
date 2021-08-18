package com.example.listo.dto;

import lombok.Data;


@Data
public class RestaurantReqDto {
    private String name;
    private String location;
    private int capacity;
}

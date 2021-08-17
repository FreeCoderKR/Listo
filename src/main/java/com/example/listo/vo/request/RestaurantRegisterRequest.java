package com.example.listo.vo.request;

import lombok.Data;

@Data
public class RestaurantRegisterRequest {
    private String name;
    private String location;
    private int capacity;

}

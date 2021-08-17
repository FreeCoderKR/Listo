package com.example.listo.vo.response;

import lombok.Data;


@Data
public class RestaurantRegisterResponse {
    private String name;
    private String location;
    private int capacity;
}

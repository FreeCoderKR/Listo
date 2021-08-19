package com.example.listo.dto.restaurant;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RestaurantUpdateReqDto {
    private String name;
    private String location;
    private String phone;
    @Size(min = 2, message = "restaurant has to offer 2 people at least")
    private int capacity;
    private RestaurantStatus status;
}

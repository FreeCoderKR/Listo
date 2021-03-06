package com.example.listo.dto.restaurant;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class RestaurantReqDto {
    @NotNull
    private Long ownerId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String location;
    @NotEmpty
    private String phone;
    @NotNull
    @Size(min = 2, message = "restaurant has to offer 2 people at least")
    private int capacity;
}

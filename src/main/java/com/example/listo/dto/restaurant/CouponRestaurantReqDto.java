package com.example.listo.dto.restaurant;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CouponRestaurantReqDto {
    @NotNull
    private Long restaurantId;
}

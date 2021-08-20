package com.example.listo.dto.restaurant;

import com.example.listo.domain.restaurant.CouponType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CouponReqDto {
    @NotNull
    private Long restaurantId;
    @NotEmpty
    private String name;
    @NotEmpty
    private CouponType type;
    @NotNull
    private int value;
    @NotNull
    @Size(min = 1, message = "coupon need more than one day duration")
    private int durationDay;

}

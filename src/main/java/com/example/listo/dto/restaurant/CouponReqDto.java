package com.example.listo.dto.restaurant;

import com.example.listo.domain.restaurant.CouponType;
import lombok.Data;

@Data
public class CouponReqDto {
    private String name;
    private CouponType type;
    private int value;
    private int durationDay;

}

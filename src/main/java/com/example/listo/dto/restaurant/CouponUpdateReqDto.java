package com.example.listo.dto.restaurant;

import com.example.listo.domain.restaurant.CouponType;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CouponUpdateReqDto {
    private String name;
    private CouponType type;
    private int value;
    @Size(min = 1)
    private int durationDay;
}

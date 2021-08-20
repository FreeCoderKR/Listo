package com.example.listo.dto.restaurant;

import com.example.listo.domain.restaurant.CouponEntity;
import com.example.listo.domain.restaurant.CouponType;
import lombok.Data;

@Data
public class CouponOnlyResDto {
    private Long id;
    private String name;
    private CouponType type;
    private int value;
    private int durationDay;

    public CouponOnlyResDto() {
    }

    public CouponOnlyResDto(CouponEntity coupon) {
        this.id = coupon.getId();
        this.name = coupon.getName();
        this.type = coupon.getType();
        this.value = coupon.getValue();
        this.durationDay = coupon.getDurationDay();
    }
}

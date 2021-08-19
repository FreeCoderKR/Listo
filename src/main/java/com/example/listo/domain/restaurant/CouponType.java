package com.example.listo.domain.restaurant;

import lombok.Getter;

import javax.persistence.GeneratedValue;

@Getter
public enum CouponType {
    PRICE,FREE,RATE
}

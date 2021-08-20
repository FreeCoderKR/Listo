package com.example.listo.dto.user.guest;

import com.example.listo.domain.restaurant.CouponType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyCouponResDto {
    private Long id;
    private LocalDateTime expire;
    private String name;
    private CouponType type;
    private int value;


}

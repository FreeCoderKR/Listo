package com.example.listo.domain.restaurant;

import com.example.listo.common.BaseTimeEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "coupon")
public class CouponEntity extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "coupon_id")
    private Long id;
    @Column(name = "coupon_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private CouponType type;
    @Column(name = "coupon_value")
    private int value;
    @Column(name = "coupon_duration")
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;


}

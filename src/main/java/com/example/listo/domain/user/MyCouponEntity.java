package com.example.listo.domain.user;

import com.example.listo.domain.restaurant.CouponEntity;
import com.example.listo.domain.user.GuestEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "mycoupon")
public class MyCouponEntity {

    @Id @GeneratedValue
    @Column(name = "mycoupon_id")
    private Long id;

    private LocalDateTime expire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private GuestEntity guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private CouponEntity coupon;

    public void setGuest(GuestEntity guest) {
        guest.getMycoupons().add(this);
        this.guest = guest;
    }

    public MyCouponEntity() {
    }

    public MyCouponEntity(GuestEntity guest, CouponEntity coupon) {
        this.setGuest(guest);
        this.coupon = coupon;
        this.expire = LocalDateTime.now().plusDays(coupon.getDurationDay());
    }
}

package com.example.listo.domain.order;

import com.example.listo.common.BaseTimeEntity;
import com.example.listo.domain.restaurant.CouponType;
import com.example.listo.domain.restaurant.MyCouponEntity;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment")
public class PaymentEntity extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "total_payment")
    private int total;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private PaymentResult result;
    private PaymentType type;
    private LocalDateTime payedDate;

    @Column(name = "coupon_use")
    @ColumnDefault("false")
    private Boolean couponUse;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mycoupon_id")
    private MyCouponEntity coupon;

    public void setOrder(OrderEntity order) {
        order.setPayment(this);
        this.order = order;
    }

    public PaymentEntity(OrderEntity order, PaymentType type, MyCouponEntity mycoupon) {
        this.setOrder(order);
        this.type = type;
        this.coupon = mycoupon;
        this.couponUse=true;
        this.payedDate=LocalDateTime.now();
        if(mycoupon.getCoupon().getType().equals(CouponType.PRICE)){
            this.total= order.getTotalPrice(order.getOrderMenus())-mycoupon.getCoupon().getValue();
        }else if(mycoupon.getCoupon().getType().equals(CouponType.RATE)){
            this.total= (int)(100-mycoupon.getCoupon().getValue())*order.getTotalPrice(order.getOrderMenus())/100;
        }else{
            this.total=0;
        }
        this.result=PaymentResult.PAID;

    }

    public PaymentEntity(OrderEntity order, PaymentType type) {
        this.setOrder(order);
        this.type = type;
        this.couponUse=false;
        this.payedDate=LocalDateTime.now();
        this.total=order.getTotalPrice(order.getOrderMenus());
        this.result=PaymentResult.PAID;
    }

    public PaymentEntity() {
    }
}

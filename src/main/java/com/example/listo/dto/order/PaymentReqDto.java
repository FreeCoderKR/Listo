package com.example.listo.dto.order;

import com.example.listo.domain.order.PaymentType;
import lombok.Data;

@Data
public class PaymentReqDto {
    //orderId는 pathvariable로

    private Long myCouponId;
    private PaymentType paymentType;
    private Boolean couponUse;

}

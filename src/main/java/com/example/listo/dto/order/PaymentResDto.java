package com.example.listo.dto.order;

import com.example.listo.domain.order.PaymentType;
import lombok.Data;

@Data
public class PaymentResDto {
    private Long id;
    private PaymentType type;
    private Boolean couponUse;
    private int paymentTotal;
    private int discount;

}

package com.example.listo.service.order;

import com.example.listo.dto.order.*;

public interface OrderService {
    OrderResDto makeOrder(OrderReqDto reqDto);

    ReviewResDto writeReview(ReviewReqDto reqDto, Long orderId);

    PaymentResDto payOrder(PaymentReqDto reqDto, Long orderId);
}

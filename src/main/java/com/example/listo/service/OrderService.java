package com.example.listo.service;

import com.example.listo.dto.OrderReqDto;
import com.example.listo.dto.OrderResDto;
import com.example.listo.dto.ReviewReqDto;
import com.example.listo.dto.ReviewResDto;

public interface OrderService {
    OrderResDto makeOrder(OrderReqDto reqDto);

    ReviewResDto writeReview(ReviewReqDto reqDto, Long orderId);
}

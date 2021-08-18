package com.example.listo.service;

import com.example.listo.dto.OrderReqDto;
import com.example.listo.dto.OrderResDto;

public interface OrderService {
    OrderResDto makeOrder(OrderReqDto reqDto);
}

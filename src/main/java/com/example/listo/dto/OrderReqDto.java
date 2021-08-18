package com.example.listo.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class OrderReqDto {

    private Long guestId;
    private Long restaurantId;
    private List<OrderMenuReqDto> orderMenus;
}

package com.example.listo.dto;

import com.example.listo.domain.ReviewEntity;
import com.example.listo.vo.commonenum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResDto {
    private Long orderId;
    private int totalOrderPrice;
    private List<OrderMenuResDto> orderMenus;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private String orderPersonName;
    private String restaurantName;
    private String msg;

    private ReviewResDto review;

}

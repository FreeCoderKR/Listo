package com.example.listo.dto.order;

import com.example.listo.domain.order.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResDto {
    private Long orderId;
    private int totalOrderPrice;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private String orderPersonName;
    private String restaurantName;
    private String msg;

    private List<OrderMenuResDto> orderMenus;

    private ReviewResDto review;

}

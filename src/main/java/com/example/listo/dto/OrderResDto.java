package com.example.listo.dto;

import com.example.listo.vo.commonenum.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResDto {
    private Long orderId;
    private int totalOrderPrice;
    private int totalOrderQuantity;
    private List<OrderMenuResDto> orderMenus;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private String orderPersonName;
    private String orderPersonEmail;
    private String restaurantName;

}

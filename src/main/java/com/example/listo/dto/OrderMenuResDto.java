package com.example.listo.dto;

import com.example.listo.domain.OrderMenuEntity;
import lombok.Data;

@Data
public class OrderMenuResDto {
    private Long orderMenuId;
    private String name;
    private int price;
    private int count;



}

package com.example.listo.dto.restaurant;

import lombok.Data;

@Data
public class MenuReqDto {
    private String name;
    private int price;
    private int stock;
}

package com.example.listo.dto;

import lombok.Data;

@Data
public class MenuReqDto {
    private String name;
    private int price;
    private int stock;
}
package com.example.listo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantResDto {
    private Long id;
    private String name;
    private String location;
    private int capacity;
    private String ownerName;

    private List<MenuResDto> menus;
}

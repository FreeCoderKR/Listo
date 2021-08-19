package com.example.listo.dto.restaurant;

import com.example.listo.dto.user.owner.OwnerOnlyResDto;
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
    private OwnerOnlyResDto owner;
    private List<MenuResDto> menus;
    private RestaurantStatus status;
    private float avgPoint;
    private int rank;

}

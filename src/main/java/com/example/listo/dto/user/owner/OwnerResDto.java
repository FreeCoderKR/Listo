package com.example.listo.dto.user.owner;

import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.dto.restaurant.RestaurantOnlyResDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerResDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int businessNumber;
    private RestaurantOnlyResDto restaurant;
}

package com.example.listo.dto.user.owner;

import com.example.listo.domain.restaurant.RestaurantEntity;
import lombok.Data;

@Data
public class OwnerResDto {
    private Long id;
    private String name;
    private String email;
    private String phone;

    private RestaurantEntity restaurant;
}

package com.example.listo.dto.restaurant;

import com.example.listo.domain.restaurant.RestaurantEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantOnlyResDto {
    private Long id;
    private String name;
    private String location;
    private String phone;
    private int capacity;
    private RestaurantStatus status;

    public RestaurantOnlyResDto() {
    }

    public RestaurantOnlyResDto(RestaurantEntity restaurantEntity) {
        this.id = restaurantEntity.getId();
        this.name = restaurantEntity.getName();
        this.location = restaurantEntity.getLocation();
        this.phone = restaurantEntity.getPhone();
        this.capacity = restaurantEntity.getCapacity();
        this.status = restaurantEntity.getStatus();
    }
}

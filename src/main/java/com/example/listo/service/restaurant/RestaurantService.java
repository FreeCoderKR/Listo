package com.example.listo.service.restaurant;

import com.example.listo.dto.restaurant.*;

public interface RestaurantService {
    RestaurantOnlyResDto register(RestaurantReqDto reqDto);
    MenuResDto registerMenu(MenuReqDto reqDto, Long restaurantId);

    RestaurantResDto getRestaurant(Long restaurantId);
}

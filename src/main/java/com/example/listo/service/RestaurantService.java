package com.example.listo.service;

import com.example.listo.dto.MenuReqDto;
import com.example.listo.dto.MenuResDto;
import com.example.listo.dto.RestaurantReqDto;
import com.example.listo.dto.RestaurantResDto;

public interface RestaurantService {
    RestaurantResDto register(RestaurantReqDto reqDto, Long ownerId);
    MenuResDto registerMenu(MenuReqDto reqDto, Long restaurantId);

    RestaurantResDto getRestaurant(Long restaurantId);
}

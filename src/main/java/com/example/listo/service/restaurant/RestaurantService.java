package com.example.listo.service.restaurant;

import com.example.listo.dto.restaurant.*;

import java.util.List;

public interface RestaurantService {
    RestaurantOnlyResDto register(RestaurantReqDto reqDto);
    MenuResDto registerMenu(MenuReqDto reqDto, Long restaurantId);

    RestaurantResDto getRestaurant(Long restaurantId);

    List<RestaurantOnlyResDto> getAllRestaurant();
    //TODO
    RestaurantOnlyResDto updateRestaurant(RestaurantUpdateReqDto request);
}

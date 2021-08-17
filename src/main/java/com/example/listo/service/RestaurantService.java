package com.example.listo.service;

import com.example.listo.dto.RegisterDto;
import com.example.listo.dto.RestaurantRegisterDto;

public interface RestaurantService {
    RestaurantRegisterDto register(RestaurantRegisterDto registerDto);
}

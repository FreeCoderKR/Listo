package com.example.listo.service;

import com.example.listo.domain.OwnerEntity;
import com.example.listo.domain.RestaurantEntity;
import com.example.listo.dto.RestaurantRegisterDto;
import com.example.listo.repository.OwnerRepository;
import com.example.listo.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{
    private final OwnerRepository ownerRepository;
    private final RestaurantRepository restaurantRepository;
    @Override
    public RestaurantRegisterDto register(RestaurantRegisterDto registerDto) {
        OwnerEntity owner = ownerRepository.findById(registerDto.getOwnerId()).orElseThrow();
        ModelMapper modelMapper = new ModelMapper();
        RestaurantEntity restaurant = modelMapper.map(registerDto, RestaurantEntity.class);
        restaurant.setOwner(owner);
        restaurantRepository.save(restaurant);
        return  registerDto;


    }
}

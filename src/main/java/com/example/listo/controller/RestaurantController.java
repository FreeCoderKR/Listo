package com.example.listo.controller;

import com.example.listo.dto.RestaurantDto;
import com.example.listo.service.RestaurantService;
import com.example.listo.vo.request.RestaurantRegisterRequest;
import com.example.listo.vo.response.RestaurantRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    @PostMapping("/{ownerId}")
    public ResponseEntity<RestaurantRegisterResponse> register(@RequestBody RestaurantRegisterRequest request, @PathVariable("ownerId") Long ownerId){
        ModelMapper modelMapper = new ModelMapper();

        RestaurantDto restaurantDto = modelMapper.map(request, RestaurantDto.class);
        restaurantDto.setOwnerId(ownerId);
        RestaurantDto register = restaurantService.register(restaurantDto);

        return ResponseEntity.ok(modelMapper.map(register, RestaurantRegisterResponse.class));
    }
}

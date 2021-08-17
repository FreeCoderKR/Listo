package com.example.listo.controller;

import com.example.listo.dto.RestaurantRegisterDto;
import com.example.listo.service.RestaurantService;
import com.example.listo.vo.request.RestaurantRegisterRequest;
import com.example.listo.vo.response.RestaurantRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant/{ownerId}")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    @PostMapping
    public ResponseEntity<RestaurantRegisterResponse> register(@RequestBody RestaurantRegisterRequest request, @PathVariable("ownerId") Long ownerId){
        ModelMapper modelMapper = new ModelMapper();

        RestaurantRegisterDto restaurantRegisterDto = modelMapper.map(request, RestaurantRegisterDto.class);
        restaurantRegisterDto.setOwnerId(ownerId);
        RestaurantRegisterDto register = restaurantService.register(restaurantRegisterDto);

        return ResponseEntity.ok(modelMapper.map(register, RestaurantRegisterResponse.class));
    }
}

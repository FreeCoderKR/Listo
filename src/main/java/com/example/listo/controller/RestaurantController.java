package com.example.listo.controller;

import com.example.listo.dto.MenuReqDto;
import com.example.listo.dto.MenuResDto;
import com.example.listo.dto.RestaurantReqDto;
import com.example.listo.dto.RestaurantResDto;
import com.example.listo.service.RestaurantService;
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
    public ResponseEntity<RestaurantResDto> register(@RequestBody RestaurantReqDto request, @PathVariable("ownerId") Long ownerId){
        RestaurantResDto resDto = restaurantService.register(request, ownerId);
        return ResponseEntity.ok(resDto);
    }
    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<MenuResDto> registerMenu(@RequestBody MenuReqDto reqDto, @PathVariable("restaurantId") Long restaurantId){
        MenuResDto menuResDto = restaurantService.registerMenu(reqDto, restaurantId);
        return ResponseEntity.ok(menuResDto);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResDto> getRestaurantInfo(@PathVariable("restaurantId") Long restaurantId){
        RestaurantResDto restaurant = restaurantService.getRestaurant(restaurantId);
        return ResponseEntity.ok(restaurant);
    }
}

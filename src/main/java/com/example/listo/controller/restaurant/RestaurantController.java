package com.example.listo.controller.restaurant;

import com.example.listo.dto.restaurant.*;
import com.example.listo.service.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    /**
     * restaurant + menu + coupon
     * */
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantOnlyResDto> register(@RequestBody RestaurantReqDto request){
        RestaurantOnlyResDto resDto = restaurantService.register(request);
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

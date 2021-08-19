package com.example.listo.controller.restaurant;

import com.example.listo.dto.restaurant.*;
import com.example.listo.service.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResDto> getRestaurantInfo(@PathVariable("restaurantId") Long restaurantId){
        RestaurantResDto restaurant = restaurantService.getRestaurant(restaurantId);
        return ResponseEntity.ok(restaurant);
    }
    @PatchMapping("/{restaurantId}")
    public ResponseEntity<RestaurantOnlyResDto> updateRestaurantInfo(@PathVariable("restaurantId") Long restaurantId, @RequestBody RestaurantUpdateReqDto request){
        RestaurantOnlyResDto resDto = restaurantService.updateRestaurant(request);
        return ResponseEntity.ok(resDto);
    }


    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<MenuResDto> registerMenu(@RequestBody MenuReqDto reqDto, @PathVariable("restaurantId") Long restaurantId){
        MenuResDto menuResDto = restaurantService.registerMenu(reqDto, restaurantId);
        return ResponseEntity.ok(menuResDto);
    }

    /*
    * read All은 list만 불러오는 것이기에 only로 가져오자., readOne은 detail을 가져오는 것이기에 fullset을 가져오자.
    * */
    @GetMapping
    public ResponseEntity<List<RestaurantOnlyResDto>> getAllRestaurant(){
        List<RestaurantOnlyResDto> restaurants = restaurantService.getAllRestaurant();
        return ResponseEntity.ok(restaurants);
    }
}

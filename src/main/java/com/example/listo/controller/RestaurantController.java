package com.example.listo.controller;

import com.example.listo.dto.restaurant.*;
import com.example.listo.dto.user.guest.MyCouponReqDto;
import com.example.listo.service.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    /**
     * restaurant + menu + coupon
     * */
    private final RestaurantService restaurantService;

    /*
     * read All은 list만 불러오는 것이기에 only로 가져오자., readOne은 detail을 가져오는 것이기에 fullset을 가져오자.
     * */
    @GetMapping("/restaurant")
    public ResponseEntity<List<RestaurantOnlyResDto>> getAllRestaurant(){
        List<RestaurantOnlyResDto> restaurants = restaurantService.getAllRestaurant();
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantOnlyResDto> register(@RequestBody RestaurantReqDto request){
        RestaurantOnlyResDto resDto = restaurantService.register(request);
        return ResponseEntity.ok(resDto);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<RestaurantResDto> getRestaurantInfo(@PathVariable("restaurantId") Long restaurantId){
        RestaurantResDto restaurant = restaurantService.getRestaurant(restaurantId);
        return ResponseEntity.ok(restaurant);
    }
    @PatchMapping("/restaurant/{restaurantId}")
    public ResponseEntity<RestaurantOnlyResDto> updateRestaurantInfo(@PathVariable("restaurantId") Long restaurantId, @RequestBody RestaurantUpdateReqDto request){
        RestaurantOnlyResDto resDto = restaurantService.updateRestaurant(request, restaurantId);
        return ResponseEntity.ok(resDto);
    }


    @PostMapping("/restaurant/menu")
    public ResponseEntity<MenuResDto> registerMenu(@RequestBody MenuReqDto reqDto){
        MenuResDto menuResDto = restaurantService.registerMenu(reqDto);
        return ResponseEntity.ok(menuResDto);
    }
    @PatchMapping("/restaurant/menu/{menuId}")
    public ResponseEntity<MenuResDto> updateMenu(@RequestBody MenuReqDto reqDto, @PathVariable("menuId") Long menuId){
        MenuResDto menuResDto = restaurantService.updateMenu(reqDto, menuId);
        return ResponseEntity.ok(menuResDto);
    }
    @DeleteMapping("/restaurant/menu/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable("menuId") Long menuId){
        restaurantService.deleteMenu(menuId);
        return ResponseEntity.ok("Successfully Deleted");
    }

    //TODO
    @GetMapping("/restaurant/{restaurantId}/coupon")
    public ResponseEntity<List<CouponOnlyResDto>> getAllCoupon(@PathVariable("restaurantId") Long restaurantId){
        List<CouponOnlyResDto> couponResDtos = restaurantService.getAllCoupon(restaurantId);
        return ResponseEntity.ok(couponResDtos);
    }

    @GetMapping("/restaurant/coupon/{couponId}")
    public ResponseEntity<CouponResDto> getOneCoupon(@PathVariable("couponId") Long couponId){
        CouponResDto couponResDto = restaurantService.getOneCoupon(couponId);
        return ResponseEntity.ok(couponResDto);
    }

    @PostMapping("/restaurant/coupon")
    public ResponseEntity<CouponOnlyResDto> registerCoupon(@RequestBody CouponReqDto reqDto){
        CouponOnlyResDto couponResDto = restaurantService.registerCoupon(reqDto);
        return ResponseEntity.ok(couponResDto);
    }
    @PatchMapping("/restaurant/coupon/{couponId}")
    public ResponseEntity<CouponOnlyResDto> updateCoupon(@RequestBody CouponUpdateReqDto reqDto, @PathVariable("couponId") Long couponId){
        CouponOnlyResDto couponResDto = restaurantService.updateCoupon(reqDto, couponId);
        return ResponseEntity.ok(couponResDto);
    }
    @DeleteMapping("/restaurant/coupon/{couponId}")
    public ResponseEntity<String> deleteCoupon(@PathVariable("couponId") Long couponId){
        restaurantService.deleteCoupon(couponId);
        return ResponseEntity.ok("Successfully Deleted");
    }
    @PostMapping("/restaurant/coupon/{couponId}/{guestId}")
    public ResponseEntity<String> downloadCoupon(@PathVariable("couponId") Long couponId, @PathVariable("guestId") Long guestId){
        restaurantService.downloadMyCoupon(couponId, guestId);
        return ResponseEntity.ok("Successfully Downloaded");
    }




}

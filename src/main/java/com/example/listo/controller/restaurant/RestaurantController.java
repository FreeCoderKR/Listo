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
        RestaurantOnlyResDto resDto = restaurantService.updateRestaurant(request, restaurantId);
        return ResponseEntity.ok(resDto);
    }


    @PostMapping("/menu")
    public ResponseEntity<MenuResDto> registerMenu(@RequestBody MenuReqDto reqDto){
        MenuResDto menuResDto = restaurantService.registerMenu(reqDto);
        return ResponseEntity.ok(menuResDto);
    }
    @PatchMapping("/menu/{menuId}")
    public ResponseEntity<MenuResDto> updateMenu(@RequestBody MenuReqDto reqDto, @PathVariable("menuId") Long menuId){
        MenuResDto menuResDto = restaurantService.updateMenu(reqDto, menuId);
        return ResponseEntity.ok(menuResDto);
    }
    @DeleteMapping("/menu/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable("menuId") Long menuId){
        restaurantService.deleteMenu(menuId);
        return ResponseEntity.ok("Successfully Deleted");
    }

    @GetMapping("/coupon")
    public ResponseEntity<List<CouponOnlyResDto>> getAllCoupon(@RequestBody CouponRestaurantReqDto reqDto){
        List<CouponOnlyResDto> couponResDtos = restaurantService.getAllCoupon(reqDto.getRestaurantId());
        return ResponseEntity.ok(couponResDtos);
    }
    @GetMapping("/mycoupon")
    public ResponseEntity<List<MyCouponResDto>> getAllMyCoupon(@RequestBody MyCouponReqDto reqDto){
        List<MyCouponResDto> myCouponResDtos = restaurantService.getAllMyCoupon(reqDto.getGuestId());
        return ResponseEntity.ok(myCouponResDtos);
    }
    @PostMapping("/mycoupon/{couponId}")
    public ResponseEntity<String> downloadCoupon(@PathVariable("couponId") Long couponId, @RequestBody MyCouponReqDto reqDto){
        restaurantService.downloadMyCoupon(couponId, reqDto.getGuestId());
        return ResponseEntity.ok("Successfully Downloaded");
    }
    @GetMapping("/coupon/{couponId}")
    public ResponseEntity<CouponResDto> getOneCoupon(@PathVariable("couponId") Long couponId){
        CouponResDto couponResDto = restaurantService.getOneCoupon(couponId);
        return ResponseEntity.ok(couponResDto);
    }

    @PostMapping("/coupon")
    public ResponseEntity<CouponOnlyResDto> registerCoupon(@RequestBody CouponReqDto reqDto){
        CouponOnlyResDto couponResDto = restaurantService.registerCoupon(reqDto);
        return ResponseEntity.ok(couponResDto);
    }
    @PatchMapping("/coupon/{couponId}")
    public ResponseEntity<CouponOnlyResDto> updateCoupon(@RequestBody CouponUpdateReqDto reqDto, @PathVariable("couponId") Long couponId){
        CouponOnlyResDto couponResDto = restaurantService.updateCoupon(reqDto, couponId);
        return ResponseEntity.ok(couponResDto);
    }
    @DeleteMapping("/coupon/{couponId}")
    public ResponseEntity<String> deleteCoupon(@PathVariable("couponId") Long couponId){
        restaurantService.deleteCoupon(couponId);
        return ResponseEntity.ok("Successfully Deleted");
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

package com.example.listo.service.restaurant;

import com.example.listo.dto.restaurant.*;

import java.util.List;

public interface RestaurantService {
    RestaurantOnlyResDto register(RestaurantReqDto reqDto);


    RestaurantResDto getRestaurant(Long restaurantId);

    List<RestaurantOnlyResDto> getAllRestaurant();

    RestaurantOnlyResDto updateRestaurant(RestaurantUpdateReqDto request, Long restaurantId);
    MenuResDto registerMenu(MenuReqDto reqDto);
    MenuResDto updateMenu(MenuReqDto reqDto, Long menuId);

    void deleteMenu(Long menuId);

    List<CouponOnlyResDto> getAllCoupon(Long restaurantId);

    CouponResDto getOneCoupon(Long couponId);

    CouponOnlyResDto registerCoupon(CouponReqDto reqDto);

    CouponOnlyResDto updateCoupon(CouponUpdateReqDto reqDto, Long couponId);

    void deleteCoupon(Long couponId);

    List<MyCouponResDto> getAllMyCoupon(Long guestId);

    void downloadMyCoupon(Long couponId, Long guestId);
}

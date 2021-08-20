package com.example.listo.service.restaurant;

import com.example.listo.domain.restaurant.CouponEntity;
import com.example.listo.domain.restaurant.MenuEntity;
import com.example.listo.domain.user.MyCouponEntity;
import com.example.listo.domain.user.GuestEntity;
import com.example.listo.domain.user.OwnerEntity;
import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.dto.restaurant.*;
import com.example.listo.dto.user.guest.MyCouponResDto;
import com.example.listo.error.DuplicateDataException;
import com.example.listo.error.NoDataWithIdException;
import com.example.listo.repository.restaurant.CouponRepository;
import com.example.listo.repository.restaurant.MenuRepository;
import com.example.listo.repository.user.MyCouponRepository;
import com.example.listo.repository.user.GuestRepository;
import com.example.listo.repository.user.OwnerRepository;
import com.example.listo.repository.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService{
    private final OwnerRepository ownerRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final CouponRepository couponRepository;
    private final MyCouponRepository myCouponRepository;
    private final GuestRepository guestRepository;
    @Override
    public RestaurantOnlyResDto register(RestaurantReqDto reqDto) {
        ModelMapper modelMapper = new ModelMapper();
        OwnerEntity owner = ownerRepository.findById(reqDto.getOwnerId()).orElseThrow(()->new NoDataWithIdException());
        RestaurantEntity restaurant = new RestaurantEntity(reqDto.getName(), reqDto.getCapacity(), reqDto.getLocation(), reqDto.getPhone(), owner);
        RestaurantEntity savedRestaurant = restaurantRepository.save(restaurant);
        RestaurantOnlyResDto resDto = new RestaurantOnlyResDto(savedRestaurant);
        return resDto;
    }
    @Override
    public RestaurantResDto getRestaurant(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findByIdFetch(restaurantId)
                .orElseThrow(() -> new NoDataWithIdException());

        RestaurantResDto resDto = new RestaurantResDto();
        resDto.setId(restaurant.getId());
        resDto.setCapacity(restaurant.getCapacity());
        resDto.setLocation(restaurant.getLocation());
        resDto.setName(restaurant.getName());
        List<MenuEntity> menuEntities = restaurant.getMenus();
        List<MenuResDto> menuResDtos = new ArrayList<>();
        for (MenuEntity menuEntity : menuEntities) {
            MenuResDto menuResDto = new MenuResDto(menuEntity);
            menuResDtos.add(menuResDto);
        }
        resDto.setMenus(menuResDtos);
        return resDto;
    }

    @Override
    public List<RestaurantOnlyResDto> getAllRestaurant() {
        List<RestaurantEntity> all = restaurantRepository.findAll();
        return all.stream().map(restaurantEntity -> new RestaurantOnlyResDto(restaurantEntity)).collect(Collectors.toList());
    }

    @Override
    public RestaurantOnlyResDto updateRestaurant(RestaurantUpdateReqDto request, Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findByIdFetch(restaurantId)
                .orElseThrow(() -> new NoDataWithIdException());

        restaurant.setName(Optional.ofNullable(request.getName()).orElse(restaurant.getName()));
        restaurant.setLocation(Optional.ofNullable(request.getLocation()).orElse(restaurant.getLocation()));
        restaurant.setPhone(Optional.ofNullable(request.getPhone()).orElse(restaurant.getPhone()));
        restaurant.setStatus(Optional.ofNullable(request.getStatus()).orElse(restaurant.getStatus()));
        restaurant.setCapacity(Optional.ofNullable(request.getCapacity()).orElse(restaurant.getCapacity()));

        ModelMapper mapper = new ModelMapper();
        return mapper.map(restaurant, RestaurantOnlyResDto.class);
    }

    @Override
    public MenuResDto registerMenu(MenuReqDto reqDto) {
        RestaurantEntity restaurant = restaurantRepository.findById(reqDto.getRestaurantId())
                .orElseThrow(() -> new NoDataWithIdException());
        MenuEntity menuEntity = new MenuEntity(reqDto.getName(), reqDto.getPrice(), reqDto.getStock(), restaurant);
        MenuEntity save = menuRepository.save(menuEntity);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(save, MenuResDto.class);

    }

    @Override
    public MenuResDto updateMenu(MenuReqDto reqDto, Long menuId) {
        MenuEntity menuEntity = menuRepository.findById(menuId).orElseThrow(() -> new NoDataWithIdException());
        menuEntity.setName(Optional.ofNullable(reqDto.getName()).orElse(menuEntity.getName()));
        menuEntity.setPrice(Optional.ofNullable(reqDto.getPrice()).orElse(menuEntity.getPrice()));
        menuEntity.setStock(Optional.ofNullable(reqDto.getStock()).orElse(menuEntity.getStock()));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(menuEntity, MenuResDto.class);
    }

    @Override
    public void deleteMenu(Long menuId) {
        MenuEntity menuEntity = menuRepository.findById(menuId).orElseThrow(() -> new NoDataWithIdException());
        menuRepository.delete(menuEntity);
    }

    @Override
    public List<CouponOnlyResDto> getAllCoupon(Long restaurantId) {
        List<CouponEntity> couponEntities = couponRepository.findAllByRestaurantId(restaurantId);
        return couponEntities.stream().map(couponEntity -> new CouponOnlyResDto(couponEntity)).collect(Collectors.toList());
    }

    @Override
    public CouponResDto getOneCoupon(Long couponId) {
        CouponEntity coupon = couponRepository.findById(couponId).orElseThrow(() -> new NoDataWithIdException());
        RestaurantOnlyResDto restaurantOnlyResDto = new RestaurantOnlyResDto(coupon.getRestaurant());
        CouponResDto couponResDto = new ModelMapper().map(coupon, CouponResDto.class);
        couponResDto.setRestaurant(restaurantOnlyResDto);
        return couponResDto;
    }

    @Override
    public CouponOnlyResDto registerCoupon(CouponReqDto reqDto) {
        RestaurantEntity restaurant = restaurantRepository.findById(reqDto.getRestaurantId())
                .orElseThrow(() -> new NoDataWithIdException());
        CouponEntity couponEntity = new CouponEntity(reqDto.getName(), reqDto.getType(), reqDto.getValue(), reqDto.getDurationDay(), restaurant);
        CouponEntity save = couponRepository.save(couponEntity);
        return new ModelMapper().map(save, CouponOnlyResDto.class);
    }

    @Override
    public CouponOnlyResDto updateCoupon(CouponUpdateReqDto reqDto, Long couponId) {
        CouponEntity coupon = couponRepository.findById(couponId).orElseThrow(() -> new NoDataWithIdException());
        coupon.setName(Optional.ofNullable(reqDto.getName()).orElse(coupon.getName()));
        coupon.setType(Optional.ofNullable(reqDto.getType()).orElse(coupon.getType()));
        coupon.setDurationDay(Optional.ofNullable(reqDto.getDurationDay()).orElse(coupon.getDurationDay()));
        coupon.setValue(Optional.ofNullable(reqDto.getValue()).orElse(coupon.getValue()));
        return new ModelMapper().map(coupon, CouponOnlyResDto.class);

    }

    @Override
    public void deleteCoupon(Long couponId) {
        CouponEntity couponEntity = couponRepository.findById(couponId).orElseThrow(() -> new NoDataWithIdException());
        couponRepository.delete(couponEntity);

    }



    @Override
    public void downloadMyCoupon(Long couponId, Long guestId) {
        GuestEntity guestEntity = guestRepository.findById(guestId).orElseThrow(() -> new NoDataWithIdException());
        CouponEntity couponEntity = couponRepository.findById(couponId).orElseThrow(() -> new NoDataWithIdException());
        MyCouponEntity myCouponEntity = new MyCouponEntity(guestEntity, couponEntity);
        //중복 다운로드 체크
        duplicateCouponDownloadCheck(couponId, guestId);
        myCouponRepository.save(myCouponEntity);

    }

    private void duplicateCouponDownloadCheck(Long couponId, Long guestId) {
        MyCouponEntity myCouponEntity = myCouponRepository.findByGuestIdAndCouponId(guestId, couponId);
        if(myCouponEntity != null){
            throw new DuplicateDataException();
        }
    }
}

package com.example.listo.service;

import com.example.listo.domain.MenuEntity;
import com.example.listo.domain.OwnerEntity;
import com.example.listo.domain.ReserveEntity;
import com.example.listo.domain.RestaurantEntity;
import com.example.listo.dto.*;
import com.example.listo.repository.MenuRepository;
import com.example.listo.repository.OwnerRepository;
import com.example.listo.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService{
    private final OwnerRepository ownerRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    @Override
    public RestaurantResDto register(RestaurantReqDto reqDto, Long ownerId) {
        ModelMapper modelMapper = new ModelMapper();
        OwnerEntity owner = ownerRepository.findByIdFetch(ownerId).orElseThrow(()->new RuntimeException("no owner with given ownerId"));
        log.info(owner.getUser().getName());
        RestaurantEntity restaurant = new RestaurantEntity(reqDto.getName(), reqDto.getCapacity(), reqDto.getLocation(), owner);
        RestaurantEntity savedRestaurant = restaurantRepository.save(restaurant);
        RestaurantResDto resDto = modelMapper.map(savedRestaurant, RestaurantResDto.class);
        resDto.setOwnerName(owner.getUser().getName());
        return resDto;


    }

    @Override
    public MenuResDto registerMenu(MenuReqDto reqDto, Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("there is no restaurant matching that id"));
        MenuEntity menuEntity = new MenuEntity(reqDto.getName(), reqDto.getPrice(), reqDto.getStock(), restaurant);
        MenuEntity save = menuRepository.save(menuEntity);
        MenuResDto menuResDto = new MenuResDto();
        menuResDto.setMenuId(save.getId());
        menuResDto.setMenuName(save.getName());
        menuResDto.setPrice(save.getPrice());
        menuResDto.setStock(save.getStock());
        return menuResDto;

    }

    @Override
    public RestaurantResDto getRestaurant(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("there is no restaurant matching that id"));

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
}

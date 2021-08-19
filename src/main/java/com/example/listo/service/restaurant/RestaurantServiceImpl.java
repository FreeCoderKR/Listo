package com.example.listo.service.restaurant;

import com.example.listo.domain.restaurant.MenuEntity;
import com.example.listo.domain.user.OwnerEntity;
import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.dto.restaurant.*;
import com.example.listo.error.NoDataWithIdException;
import com.example.listo.repository.restaurant.MenuRepository;
import com.example.listo.repository.user.OwnerRepository;
import com.example.listo.repository.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService{
    private final OwnerRepository ownerRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    @Override
    public RestaurantOnlyResDto register(RestaurantReqDto reqDto) {
        ModelMapper modelMapper = new ModelMapper();
        OwnerEntity owner = ownerRepository.findById(reqDto.getOwnerId()).orElseThrow(()->new NoDataWithIdException());
        RestaurantEntity restaurant = new RestaurantEntity(reqDto.getName(), reqDto.getCapacity(), reqDto.getLocation(), reqDto.getPhone(), owner);
        RestaurantEntity savedRestaurant = restaurantRepository.save(restaurant);
        RestaurantOnlyResDto resDto = modelMapper.map(savedRestaurant, RestaurantOnlyResDto.class);
        return resDto;
    }

    @Override
    public MenuResDto registerMenu(MenuReqDto reqDto, Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoDataWithIdException());
        MenuEntity menuEntity = new MenuEntity(reqDto.getName(), reqDto.getPrice(), reqDto.getStock(), restaurant);
        MenuEntity save = menuRepository.save(menuEntity);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(save, MenuResDto.class);

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
}

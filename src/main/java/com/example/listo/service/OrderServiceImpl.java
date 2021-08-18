package com.example.listo.service;

import com.example.listo.domain.*;
import com.example.listo.dto.OrderReqDto;
import com.example.listo.dto.OrderResDto;
import com.example.listo.dto.ReservationResDto;
import com.example.listo.repository.GuestRepository;
import com.example.listo.repository.MenuRepository;
import com.example.listo.repository.OrderRepository;
import com.example.listo.repository.RestaurantRepository;
import com.example.listo.vo.commonenum.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final GuestRepository guestRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Override
    public OrderResDto makeOrder(OrderReqDto reqDto) {
        GuestEntity guestEntity = guestRepository.findByIdFetch(reqDto.getGuestId())
                .orElseThrow(()->new RuntimeException("There is no Guest with id"+Long.toString(reqDto.getGuestId())));
        RestaurantEntity restaurantEntity = restaurantRepository.findById(reqDto.getRestaurantId())
                .orElseThrow(()->new RuntimeException("There is no Restaurant with id"+Long.toString(reqDto.getRestaurantId())));
        try{
            List<OrderMenuEntity> orderMenus = reqDto.getOrderMenus().stream().map(oDto ->
                    new OrderMenuEntity(oDto.getCount(), menuRepository.findById(oDto.getMenuId())
                            .orElseThrow(() -> new RuntimeException("no menu id like that")))).collect(Collectors.toList());
            OrderEntity order = new OrderEntity(guestEntity, restaurantEntity, orderMenus);
            OrderEntity savedOrder = orderRepository.save(order);






        }catch (Exception e){
            ReservationResDto errorDto = new ReservationResDto();
            errorDto.setMsg(e.getMessage());
            errorDto.setStatus(ReservationStatus.REJECTED);
            return errorDto;
        }
    }
}

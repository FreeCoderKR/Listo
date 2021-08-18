package com.example.listo.service;

import com.example.listo.domain.*;
import com.example.listo.dto.*;
import com.example.listo.repository.*;
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
    private final ReviewRepository reviewRepository;



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

            OrderResDto orderResDto = new OrderResDto();
            orderResDto.setOrderId(savedOrder.getId());
            orderResDto.setOrderDate(savedOrder.getOrderDate());
            orderResDto.setStatus(savedOrder.getStatus());
            orderResDto.setRestaurantName(restaurantEntity.getName());
            orderResDto.setOrderPersonName(guestEntity.getUser().getName());
            return orderResDto;


        }catch (Exception e){
            OrderResDto errorDto = new OrderResDto();
            errorDto.setMsg(e.getMessage());
            return errorDto;
        }


    }
    @Override
    public ReviewResDto writeReview(ReviewReqDto reqDto, Long orderId) {
        OrderEntity orderEntity = orderRepository.findByIdFetch(orderId)
                .orElseThrow(()->new RuntimeException("There is no Order with id"+Long.toString(orderId)));
        ReviewEntity reviewEntity = new ReviewEntity(reqDto.getPoint(), reqDto.getComment(), orderEntity);
        ReviewEntity save = reviewRepository.save(reviewEntity);
        ReviewResDto reviewResDto = new ReviewResDto();
        reviewResDto.setId(save.getId());
        reviewResDto.setOrderId(orderId);
        reviewResDto.setPoint(save.getPoint());
        reviewResDto.setComment(save.getComment());
        reviewResDto.setGuestName(orderEntity.getGuest().getUser().getName());
        reviewResDto.setRestaurantName(orderEntity.getRestaurant().getName());
        return reviewResDto;


    }
}

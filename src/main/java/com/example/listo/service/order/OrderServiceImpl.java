package com.example.listo.service.order;

import com.example.listo.domain.order.OrderEntity;
import com.example.listo.domain.order.OrderMenuEntity;
import com.example.listo.domain.order.PaymentEntity;
import com.example.listo.domain.order.ReviewEntity;
import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.domain.user.GuestEntity;
import com.example.listo.domain.user.MyCouponEntity;
import com.example.listo.dto.order.*;
import com.example.listo.repository.order.OrderRepository;
import com.example.listo.repository.order.ReviewRepository;
import com.example.listo.repository.payment.PaymentRepository;
import com.example.listo.repository.restaurant.MenuRepository;
import com.example.listo.repository.restaurant.RestaurantRepository;
import com.example.listo.repository.user.GuestRepository;
import com.example.listo.repository.user.MyCouponRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final GuestRepository guestRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final ReviewRepository reviewRepository;
    private final MyCouponRepository myCouponRepository;
    private final PaymentRepository paymentRepository;



    @Override
    public OrderResDto makeOrder(OrderReqDto reqDto) {
        GuestEntity guestEntity = guestRepository.findById(reqDto.getGuestId())
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
            orderResDto.setOrderPersonName(guestEntity.getName());
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
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(save, ReviewResDto.class);
    }

    @Override
    public PaymentResDto payOrder(PaymentReqDto reqDto, Long orderId) {
        OrderEntity orderEntity = orderRepository.findByIdFetch(orderId)
                .orElseThrow(()->new RuntimeException("There is no Order with id"+Long.toString(orderId)));
        ModelMapper mapper = new ModelMapper();
        int orderPrice=orderEntity.getTotalPrice(orderEntity.getOrderMenus());
        if(reqDto.getCouponUse()){

            MyCouponEntity myCouponEntity = myCouponRepository.findById(reqDto.getMyCouponId())
                    .orElseThrow(() -> new RuntimeException("There is no Coupon with id" + Long.toString(reqDto.getMyCouponId())));
            PaymentEntity paymentEntity = new PaymentEntity(orderEntity, reqDto.getPaymentType(), myCouponEntity);
            PaymentEntity save = paymentRepository.save(paymentEntity);
            PaymentResDto resDto = mapper.map(save, PaymentResDto.class);
            resDto.setDiscount(resDto.getPaymentTotal()-orderPrice);
            return resDto;
        }else{
            PaymentEntity paymentEntity = new PaymentEntity(orderEntity, reqDto.getPaymentType());
            PaymentEntity save = paymentRepository.save(paymentEntity);
            PaymentResDto resDto = mapper.map(save, PaymentResDto.class);
            resDto.setDiscount(0);
            return resDto;
        }

    }
}

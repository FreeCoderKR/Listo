package com.example.listo.domain.order;

import com.example.listo.common.BaseTimeEntity;
import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.domain.user.GuestEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class OrderEntity extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private GuestEntity guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderMenuEntity> orderMenus = new ArrayList<>();

    private LocalDateTime orderDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewEntity review;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
    private PaymentEntity payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public void setOrderMenus(List<OrderMenuEntity> orderMenus) {
        for (OrderMenuEntity orderMenu : orderMenus) {
            orderMenu.setOrder(this);
        }
        this.orderMenus = orderMenus;
    }

    public OrderEntity() {
    }

    public OrderEntity(GuestEntity guest, RestaurantEntity restaurant, List<OrderMenuEntity> orderMenus){
        this.setGuest(guest);
        this.setRestaurant(restaurant);
        this.setOrderMenus(orderMenus);
        this.setStatus(OrderStatus.ORDERED);
        this.setOrderDate(LocalDateTime.now());
    }
    public int getTotalPrice(List<OrderMenuEntity> orderMenus){
        int totalPrice=0;
        for (OrderMenuEntity orderMenu : orderMenus) {
            totalPrice+=orderMenu.getMenu().getPrice()*orderMenu.getCount();
        }
        return totalPrice;
    }
}

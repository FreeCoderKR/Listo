package com.example.listo.domain.order;

import com.example.listo.common.BaseTimeEntity;
import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.domain.user.GuestEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "review")
public class ReviewEntity extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private int point;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private GuestEntity guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "review")
    private OrderEntity order;

    public void setOrder(OrderEntity order) {
        order.setReview(this);
        this.order = order;
    }

    public ReviewEntity(int point, String comment, OrderEntity order) {
        this.point = point;
        this.comment = comment;
        this.setOrder(order);
        this.restaurant= order.getRestaurant();
        this.guest=order.getGuest();
    }

    public ReviewEntity() {
    }
}

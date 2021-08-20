package com.example.listo.domain.restaurant;

import com.example.listo.common.BaseTimeEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "rest")
public class RestEntity extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "rest_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Enumerated(EnumType.STRING)
    private RestType type;

    private LocalDate restStart;
    private LocalDate restEnd;
    private int monthlyDate;

    public void setRestaurant(RestaurantEntity restaurant) {
        restaurant.getRests().add(this);
        this.restaurant = restaurant;
    }

    public RestEntity() {
    }

    public RestEntity(RestaurantEntity restaurant, LocalDate restStart, LocalDate restEnd) {
        //특별휴일, 기간설정
        this.setRestaurant(restaurant);
        this.type = RestType.CUSTOM;
        this.restStart = restStart;
        this.restEnd = restEnd;
    }

    public RestEntity(RestaurantEntity restaurant, int monthlyDate) {
        //정기휴일설정
        this.setRestaurant(restaurant);
        this.type = RestType.REGULAR;
        this.monthlyDate = monthlyDate;
    }
}

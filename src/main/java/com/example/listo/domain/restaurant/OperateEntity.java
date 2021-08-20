package com.example.listo.domain.restaurant;

import com.example.listo.common.BaseTimeEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class OperateEntity extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "operate_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Day day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    private int operateStart;
    private int operateEnd;

    private int breakStart;
    private int breakEnd;

    public void setRestaurant(RestaurantEntity restaurant) {
        restaurant.getOperates().add(this);
        this.restaurant = restaurant;
    }
    //put create
    public OperateEntity() {
    }

    public OperateEntity(RestaurantEntity restaurant, Day day, int operateStart, int operateEnd, int breakStart, int breakEnd) {
        this.day = day;
        this.setRestaurant(restaurant);
        this.operateStart = operateStart;
        this.operateEnd = operateEnd;
        this.breakStart = breakStart;
        this.breakEnd = breakEnd;
    }
}

package com.example.listo.domain;


import com.example.listo.vo.commonenum.ReservationStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reserve")
public class ReserveEntity {
    @Id
    @GeneratedValue
    @Column(name = "reserve_id")
    private Long id;

    @Column(name="reserve_time")
    private int time;

    @Column(name="reserve_count")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_id")
    private GuestEntity guest;

    public void setRestaurant(RestaurantEntity restaurant) {
        restaurant.getReserves().add(this);
        this.restaurant = restaurant;
    }

    public void setGuest(GuestEntity guest) {
        guest.getReserves().add(this);
        this.guest = guest;
    }

    protected ReserveEntity() {
    }

    public ReserveEntity(int time, int count, RestaurantEntity restaurant, GuestEntity guest) {
        this.time = time;
        this.count = count;
        restaurant.removeCapacity(count);
        this.setRestaurant(restaurant);
        this.setGuest(guest);
    }
}

package com.example.listo.domain;


import com.example.listo.vo.commonenum.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reserve")
public class ReserveEntity {
    @Id
    @GeneratedValue
    @Column(name = "reserve_id")
    @NonNull
    private Long id;

    @Column(name="reserve_date")
    @NonNull
    private LocalDate date;

    @Column(name="reserve_time")
    @NonNull
    private int time;

    @Column(name="reserve_count")
    private int count;

    @Column(name="reserve_status")
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @JsonIgnore
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

    public ReserveEntity(int time, LocalDate date, int count, RestaurantEntity restaurant, GuestEntity guest) {
        this.time = time;
        this.date = date;
        this.count = count;
        this.status=ReservationStatus.CONFIRMED;
        this.setRestaurant(restaurant);
        this.setGuest(guest);
    }
}

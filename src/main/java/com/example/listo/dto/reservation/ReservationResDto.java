package com.example.listo.dto.reservation;

import com.example.listo.domain.reservation.ReserveEntity;
import com.example.listo.domain.reservation.ReservationStatus;
import com.example.listo.dto.restaurant.RestaurantOnlyResDto;
import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResDto {
    private Long id;
    private LocalDate date;
    private int time;
    private int count;
    private ReservationStatus status;
    private RestaurantOnlyResDto restaurant;
    private GuestOnlyResDto guest;
    private String msg;
    public ReservationResDto(){

    }

    public ReservationResDto(ReserveEntity reserveEntity) {
        this.id=reserveEntity.getId();
        this.date=reserveEntity.getDate();
        this.time=reserveEntity.getTime();
        this.count=reserveEntity.getCount();
        this.status=reserveEntity.getStatus();
    }
}

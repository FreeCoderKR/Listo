package com.example.listo.dto.reservation;

import com.example.listo.domain.reservation.ReservationStatus;
import com.example.listo.domain.reservation.ReserveEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationOnlyResDto {
    private Long id;
    private LocalDate date;
    private int time;
    private int count;
    private ReservationStatus status;

    public ReservationOnlyResDto() {
    }
    public ReservationOnlyResDto(ReserveEntity reserve) {
        this.id = reserve.getId();
        this.date = reserve.getDate();
        this.time = reserve.getTime();
        this.count = reserve.getCount();
        this.status = reserve.getStatus();
    }
}

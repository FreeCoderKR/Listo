package com.example.listo.dto.reservation;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationOnlyResDto {
    private Long id;
    private LocalDate date;
    private int time;
    private int count;
}

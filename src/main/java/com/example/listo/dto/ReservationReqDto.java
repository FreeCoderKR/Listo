package com.example.listo.dto;

import com.example.listo.vo.commonenum.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationReqDto {
    private LocalDate date;
    private int time;
    private int count;
    private Long guestId;
    private Long restaurantId;


}

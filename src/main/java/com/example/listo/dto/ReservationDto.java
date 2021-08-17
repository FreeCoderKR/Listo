package com.example.listo.dto;

import com.example.listo.vo.commonenum.ReservationStatus;
import lombok.Data;

@Data
public class ReservationDto {
    private int time;
    private int count;
    private Long id;
    private String restaurantName;
    private ReservationStatus status;
    private String msg;
    private int availableCount;


}

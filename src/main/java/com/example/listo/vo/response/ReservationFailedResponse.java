package com.example.listo.vo.response;

import com.example.listo.vo.commonenum.ReservationStatus;
import lombok.Data;

@Data
public class ReservationFailedResponse {
    private int time;
    private int count;
    private String restaurantName;
    private String guestName;
    private ReservationStatus status;
    private String msg;
    private int availableCount;
}

package com.example.listo.vo.request;

import lombok.Data;

@Data
public class ReservationRegisterRequest {

    private int time;
    private int count;
    private Long restaurantId;
    private Long guestId;

}

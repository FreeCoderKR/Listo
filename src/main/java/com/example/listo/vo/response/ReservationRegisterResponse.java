package com.example.listo.vo.response;

import com.example.listo.vo.commonenum.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRegisterResponse {
    private Long id;
    private int time;
    private int count;
    private String restaurantName;
    private String guestName;
    private ReservationStatus status;

}

package com.example.listo.dto;

import com.example.listo.domain.GuestEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class GuestResDto {
    private Long guestId;
    private String guestName;
    private String guestEmail;
    private int guestGrade;
    private List<ReservationResDto> reservations;
    public GuestResDto(){

    }


}

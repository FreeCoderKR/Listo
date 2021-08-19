package com.example.listo.dto.user.guest;

import com.example.listo.dto.reservation.ReservationResDto;
import lombok.Data;

import java.util.List;

@Data
public class GuestResDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int grade;
    private List<ReservationResDto> reservations;



}

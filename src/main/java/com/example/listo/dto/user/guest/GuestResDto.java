package com.example.listo.dto.user.guest;

import com.example.listo.domain.user.Grade;
import com.example.listo.dto.reservation.ReservationOnlyResDto;
import com.example.listo.dto.reservation.ReservationResDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestResDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String nick;
    private Grade grade;
    private List<ReservationOnlyResDto> reservations;



}

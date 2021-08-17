package com.example.listo.dto;

import lombok.Data;

import java.util.List;

@Data
public class GuestDto {
    private Long id;
    private String name;
    private String email;
    private int grade;
    private List<ReservationDto> reservations;

}

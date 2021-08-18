package com.example.listo.controller;

import com.example.listo.dto.ReservationReqDto;
import com.example.listo.dto.ReservationResDto;
import com.example.listo.service.ReservationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResDto> register(@RequestBody ReservationReqDto request){

        return ResponseEntity.ok(reservationService.register(request)) ;
    }

}

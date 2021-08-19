package com.example.listo.controller.reservation;

import com.example.listo.dto.reservation.ReservationReqDto;
import com.example.listo.dto.reservation.ReservationResDto;
import com.example.listo.service.reservation.ReservationService;

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

package com.example.listo.controller;

import com.example.listo.dto.reservation.ReservationOnlyResDto;
import com.example.listo.dto.reservation.ReservationReqDto;
import com.example.listo.dto.reservation.ReservationResDto;
import com.example.listo.dto.reservation.ReservationUpdateReqDto;
import com.example.listo.service.reservation.ReservationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResDto> register(@RequestBody ReservationReqDto request){
        return ResponseEntity.ok(reservationService.register(request)) ;
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReservationOnlyResDto>> getAllReservationByRestaurant(@PathVariable Long restaurantId){
        List<ReservationOnlyResDto> reservationDtos=reservationService.getAllByRestaurant(restaurantId);
        return ResponseEntity.ok(reservationDtos) ;
    }
//    @GetMapping("/restaurant/{restaurantId}/available?date={date}")
//    public ResponseEntity<HashMap<Integer, Integer>> getAvailableTimeByRestaurant(
//            @PathVariable("restaurantId") Long restaurantId, @RequestParam(value = "date") LocalDate date){
//        HashMap<Integer, Integer>times =reservationService.getAvailableTime(restaurantId, date);
//        return ResponseEntity.ok(times) ;
//    }
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<ReservationOnlyResDto>> getAllReservationByGuest(@PathVariable Long guestId){
        List<ReservationOnlyResDto> reservationDtos=reservationService.getAllByGuest(guestId);
        return ResponseEntity.ok(reservationDtos) ;
    }
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResDto> getMyReservationDetail(@PathVariable Long reservationId){
        ReservationResDto resDto=reservationService.getMyReservationDetail(reservationId);
        return ResponseEntity.ok(resDto);
    }
    @PatchMapping("/{reservationId}")
    public ResponseEntity<ReservationResDto> updateMyReservationDetail(@RequestBody ReservationUpdateReqDto request, @PathVariable Long reservationId){
        ReservationResDto resDto=reservationService.updateMyReservation(request, reservationId);
        return ResponseEntity.ok(resDto);
    }
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> cancelMyReservationDetail(@PathVariable Long reservationId){
        reservationService.cancelMyReservation(reservationId);
        return ResponseEntity.ok("Canceled Successfully");

    }

}

package com.example.listo.controller;

import com.example.listo.dto.ReservationDto;
import com.example.listo.service.ReservationService;
import com.example.listo.vo.commonenum.ReservationStatus;
import com.example.listo.vo.request.ReservationRegisterRequest;
import com.example.listo.vo.response.ReservationFailedResponse;
import com.example.listo.vo.response.ReservationRegisterResponse;
import com.example.listo.vo.response.RestaurantRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public ResponseEntity<?> register(@RequestBody ReservationRegisterRequest request){
        ModelMapper modelMapper = new ModelMapper();
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setCount(request.getCount());
        reservationDto.setTime(request.getTime());
        ReservationDto resultDto = reservationService.register(reservationDto, request.getRestaurantId(), request.getGuestId());
        if(resultDto.getStatus()== ReservationStatus.CONFIRMED){
            ReservationRegisterResponse successResponse = modelMapper.map(resultDto, ReservationRegisterResponse.class);
            return ResponseEntity.ok(successResponse);
        }else{
            ReservationFailedResponse failedResponse = modelMapper.map(resultDto, ReservationFailedResponse.class);
            return ResponseEntity.ok(failedResponse);

        }


    }

}

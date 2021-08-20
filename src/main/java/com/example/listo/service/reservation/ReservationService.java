package com.example.listo.service.reservation;

import com.example.listo.dto.reservation.ReservationOnlyResDto;
import com.example.listo.dto.reservation.ReservationReqDto;
import com.example.listo.dto.reservation.ReservationResDto;
import com.example.listo.dto.reservation.ReservationUpdateReqDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ReservationService {
    ReservationResDto register(ReservationReqDto reservationReqDto);

    List<ReservationOnlyResDto> getAllByRestaurant(Long restaurantId);


    List<ReservationOnlyResDto> getAllByGuest(Long guestId);

    ReservationResDto getMyReservationDetail(Long restaurantId);

    ReservationResDto updateMyReservation(ReservationUpdateReqDto request, Long restaurantId);

    void cancelMyReservation(Long restaurantId);
}

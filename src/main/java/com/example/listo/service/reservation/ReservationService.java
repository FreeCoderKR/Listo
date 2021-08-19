package com.example.listo.service.reservation;

import com.example.listo.dto.reservation.ReservationReqDto;
import com.example.listo.dto.reservation.ReservationResDto;

public interface ReservationService {
    ReservationResDto register(ReservationReqDto reservationReqDto);
}

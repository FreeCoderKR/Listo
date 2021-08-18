package com.example.listo.service;

import com.example.listo.dto.ReservationReqDto;
import com.example.listo.dto.ReservationResDto;

public interface ReservationService {
    ReservationResDto register(ReservationReqDto reservationReqDto);
}

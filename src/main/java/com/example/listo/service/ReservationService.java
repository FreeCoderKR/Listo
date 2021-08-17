package com.example.listo.service;

import com.example.listo.dto.ReservationDto;

public interface ReservationService {
    ReservationDto register(ReservationDto reservationDto, Long restaurantId, Long guestId);
}

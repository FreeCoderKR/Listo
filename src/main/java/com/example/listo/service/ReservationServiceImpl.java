package com.example.listo.service;

import com.example.listo.domain.GuestEntity;
import com.example.listo.domain.ReserveEntity;
import com.example.listo.domain.RestaurantEntity;
import com.example.listo.dto.ReservationDto;
import com.example.listo.repository.GuestRepository;
import com.example.listo.repository.ReservationRepository;
import com.example.listo.repository.RestaurantRepository;
import com.example.listo.vo.commonenum.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final RestaurantRepository restaurantRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    @Override
    public ReservationDto register(ReservationDto reservationDto, Long restaurantId, Long guestId) {
        GuestEntity guestEntity = guestRepository.findByIdFetch(guestId)
                .orElseThrow(()->new RuntimeException("There is no Guest with id"+Long.toString(guestId)));
        RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new RuntimeException("There is no Restaurant with id"+Long.toString(restaurantId)));
        try{
            ReserveEntity reservationEntity = new ReserveEntity(reservationDto.getTime(), reservationDto.getCount(), restaurantEntity, guestEntity);
            ReserveEntity savedReservation = reservationRepository.save(reservationEntity);
            ReservationDto reservation = new ReservationDto();
            reservation.setId(savedReservation.getId());
            reservation.setTime(savedReservation.getTime());
            reservation.setCount(savedReservation.getCount());
            reservation.setStatus(ReservationStatus.CONFIRMED);
            reservation.setRestaurantName(restaurantEntity.getName());
            return reservation;

        }catch (Exception e){
            ReservationDto errorDto = new ReservationDto();
            errorDto.setCount(reservationDto.getCount());
            errorDto.setAvailableCount(restaurantEntity.getCapacity());
            errorDto.setMsg(e.getMessage());
            errorDto.setTime(reservationDto.getTime());
            errorDto.setStatus(ReservationStatus.REJECTED);
            return errorDto;
        }


    }
}

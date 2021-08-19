package com.example.listo.service.reservation;

import com.example.listo.domain.user.GuestEntity;
import com.example.listo.domain.reservation.ReserveEntity;
import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.dto.reservation.ReservationReqDto;
import com.example.listo.dto.reservation.ReservationResDto;
import com.example.listo.error.NoAvailableReservaitonException;
import com.example.listo.repository.user.GuestRepository;
import com.example.listo.repository.reservation.ReservationRepository;
import com.example.listo.repository.restaurant.RestaurantRepository;
import com.example.listo.domain.reservation.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final RestaurantRepository restaurantRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    @Override
    public ReservationResDto register(ReservationReqDto reqDto) {
        GuestEntity guestEntity = guestRepository.findById(reqDto.getGuestId())
                .orElseThrow(()->new RuntimeException("There is no Guest with id"+Long.toString(reqDto.getGuestId())));
        RestaurantEntity restaurantEntity = restaurantRepository.findById(reqDto.getRestaurantId())
                .orElseThrow(()->new RuntimeException("There is no Restaurant with id"+Long.toString(reqDto.getRestaurantId())));


        try{
            ReserveEntity newReservation = new ReserveEntity(reqDto.getTime(), reqDto.getDate(),
                    reqDto.getCount(), restaurantEntity, guestEntity);
            checkAvailability(newReservation, restaurantEntity);
            ReserveEntity savedReservation = reservationRepository.save(newReservation);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(savedReservation, ReservationResDto.class);

        }catch (Exception e){
            ReservationResDto errorDto = new ReservationResDto();
            errorDto.setMsg(e.getMessage());
            errorDto.setStatus(ReservationStatus.REJECTED);
            return errorDto;
        }


    }

    private void checkAvailability(ReserveEntity reservation, RestaurantEntity restaurant) {
        List<ReserveEntity> reservationsOfTheTime = reservationRepository.findAllByRestaurantIdAndDateAndTime(
                restaurant.getId(), reservation.getDate(), reservation.getTime());
        int result=restaurant.getCapacity();
        if(!reservationsOfTheTime.isEmpty()){
            for (ReserveEntity reserveEntity : reservationsOfTheTime) {
                result-=reserveEntity.getCount();
            }
        }
        if(result<reservation.getCount()){
            throw new NoAvailableReservaitonException("that time is not available");
        }


    }


}

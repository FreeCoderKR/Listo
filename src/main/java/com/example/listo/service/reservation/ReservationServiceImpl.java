package com.example.listo.service.reservation;

import com.example.listo.domain.user.GuestEntity;
import com.example.listo.domain.reservation.ReserveEntity;
import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.dto.reservation.ReservationOnlyResDto;
import com.example.listo.dto.reservation.ReservationReqDto;
import com.example.listo.dto.reservation.ReservationResDto;
import com.example.listo.dto.reservation.ReservationUpdateReqDto;
import com.example.listo.dto.restaurant.RestaurantOnlyResDto;
import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.error.NoAvailableReservaitonException;
import com.example.listo.error.NoDataWithIdException;
import com.example.listo.repository.user.GuestRepository;
import com.example.listo.repository.reservation.ReservationRepository;
import com.example.listo.repository.restaurant.RestaurantRepository;
import com.example.listo.domain.reservation.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<ReservationOnlyResDto> getAllByRestaurant(Long restaurantId) {
        return reservationRepository.findAllByRestaurantId(restaurantId).stream()
                .map(reserveEntity -> new ReservationOnlyResDto(reserveEntity)).collect(Collectors.toList());
    }
    @Override
    public List<ReservationOnlyResDto> getAllByGuest(Long guestId) {
        return reservationRepository.findAllByGuestId(guestId).stream()
                .map(reserveEntity -> new ReservationOnlyResDto(reserveEntity)).collect(Collectors.toList());
    }


    @Override
    public ReservationResDto getMyReservationDetail(Long reservationId) {
        ReserveEntity reserveEntity = reservationRepository.findByIdFetch(reservationId).orElseThrow(() -> new NoDataWithIdException());
        ReservationResDto resDto = new ReservationResDto(reserveEntity);
        return resDto;
    }

    @Override
    public ReservationResDto updateMyReservation(ReservationUpdateReqDto request, Long reservationId) {
        ReserveEntity reserveEntity = reservationRepository.findByIdFetch(reservationId).orElseThrow(() -> new NoDataWithIdException());
        reserveEntity.setDate(Optional.ofNullable(request.getDate()).orElse(reserveEntity.getDate()));
        reserveEntity.setCount(Optional.ofNullable(request.getCount()).orElse(reserveEntity.getCount()));
        reserveEntity.setTime(Optional.ofNullable(request.getTime()).orElse(reserveEntity.getTime()));
        checkAvailability(reserveEntity, reserveEntity.getRestaurant());
        ReservationResDto resDto = new ReservationResDto(reserveEntity);

        return resDto;
    }

    @Override
    public void cancelMyReservation(Long reservationId) {

    }
}

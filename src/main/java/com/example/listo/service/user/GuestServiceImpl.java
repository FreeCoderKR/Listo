package com.example.listo.service.user;

import com.example.listo.domain.reservation.ReserveEntity;
import com.example.listo.domain.user.GuestEntity;
import com.example.listo.dto.reservation.ReservationOnlyResDto;
import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.reservation.ReservationResDto;
import com.example.listo.dto.user.guest.GuestUpdateReqDto;
import com.example.listo.error.NoDataWithIdException;
import com.example.listo.repository.user.GuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService{
    private final GuestRepository guestRepository;
    @Override
    public GuestResDto findOneGuest(Long guestId) throws RuntimeException{
        GuestEntity guestEntity = guestRepository.findById(guestId).orElseThrow(() -> new NoDataWithIdException());
        ModelMapper mapper = new ModelMapper();
        GuestResDto guestResDto = mapper.map(guestEntity, GuestResDto.class);
        List<ReserveEntity> reserves = guestEntity.getReserves();
        for (ReserveEntity reserve : reserves) {
            ReservationOnlyResDto reservation = new ReservationOnlyResDto();
            reservation.setCount(reserve.getCount());
            reservation.setId(reservation.getId());
            reservation.setDate(reservation.getDate());
            reservation.setTime(reservation.getTime());
            guestResDto.getReservations().add(reservation);
        }
        return guestResDto;


    }

    @Override
    public GuestOnlyResDto updateGuest(Long guestId, GuestUpdateReqDto request) {
        GuestEntity guestEntity = guestRepository.findById(guestId).orElseThrow(() -> new NoDataWithIdException());
        guestEntity.setNick(request.getNickname());
        guestEntity.setPhone(request.getPhone());
        ModelMapper mapper = new ModelMapper();
        GuestOnlyResDto guestResDto = mapper.map(guestEntity, GuestOnlyResDto.class);
        return guestResDto;
    }

    @Override
    public void deleteGuest(Long guestId) {
        guestRepository.findById(guestId).orElseThrow(() -> new NoDataWithIdException());
        guestRepository.deleteById(guestId);
    }
}

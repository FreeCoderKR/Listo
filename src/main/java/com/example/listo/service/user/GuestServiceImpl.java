package com.example.listo.service.user;

import com.example.listo.domain.reservation.ReserveEntity;
import com.example.listo.domain.user.GuestEntity;
import com.example.listo.domain.user.MyCouponEntity;
import com.example.listo.dto.reservation.ReservationOnlyResDto;
import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.reservation.ReservationResDto;
import com.example.listo.dto.user.guest.GuestUpdateReqDto;
import com.example.listo.dto.user.guest.MyCouponResDto;
import com.example.listo.error.NoDataWithIdException;
import com.example.listo.repository.user.GuestRepository;
import com.example.listo.repository.user.MyCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService{
    private final GuestRepository guestRepository;
    private final MyCouponRepository myCouponRepository;
    @Override
    public GuestResDto findOneGuest(Long guestId) throws RuntimeException{
        GuestEntity guestEntity = guestRepository.findById(guestId).orElseThrow(() -> new NoDataWithIdException());
        ModelMapper mapper = new ModelMapper();
        GuestResDto guestResDto = mapper.map(guestEntity, GuestResDto.class);
        List<ReserveEntity> reserves = guestEntity.getReserves();
        List<MyCouponEntity> myCouponList = guestEntity.getMycoupons();
        for (MyCouponEntity myCouponEntity : myCouponList) {
            MyCouponResDto myCouponResDto = new MyCouponResDto();
            myCouponResDto.setId(myCouponEntity.getId());
            myCouponResDto.setExpire(myCouponEntity.getExpire());
            myCouponResDto.setName(myCouponEntity.getCoupon().getName());
            myCouponResDto.setType(myCouponEntity.getCoupon().getType());
            myCouponResDto.setValue(myCouponEntity.getCoupon().getValue());
            guestResDto.getCoupons().add(myCouponResDto);
        }
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
        guestEntity.setNick(Optional.ofNullable(request.getNickname()).orElse(guestEntity.getNick()));
        guestEntity.setPhone(Optional.ofNullable(request.getPhone()).orElse(guestEntity.getPhone()));
        ModelMapper mapper = new ModelMapper();
        GuestOnlyResDto guestResDto = mapper.map(guestEntity, GuestOnlyResDto.class);
        return guestResDto;
    }

    @Override
    public void deleteGuest(Long guestId) {
        GuestEntity guestEntity = guestRepository.findById(guestId).orElseThrow(() -> new NoDataWithIdException());
        guestRepository.delete(guestEntity);
    }
}

package com.example.listo.service.user;

import com.example.listo.domain.reservation.ReserveEntity;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.reservation.ReservationResDto;
import com.example.listo.repository.user.GuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService{
    private final GuestRepository guestRepository;
    @Override
    public GuestResDto findGuest(Long guestId) {
        return guestRepository.findById(guestId)
                .map(guestEntity->
                        {
                            GuestResDto guestResDto = new GuestResDto();
                            guestResDto.setId(guestEntity.getId());
                            guestResDto.setGrade(guestEntity.getGrade());
                            guestResDto.setName(guestEntity.getName());
                            guestResDto.setEmail(guestEntity.getEmail());
                            List<ReserveEntity> reserveEntities = guestEntity.getReserves();
                            List<ReservationResDto> reserveResDto = new ArrayList<>();
                            for (ReserveEntity reserveEntity : reserveEntities) {
                                ReservationResDto reservationResDto = new ReservationResDto(reserveEntity);
                                reserveResDto.add(reservationResDto);
                            }
                            guestResDto.setReservations(reserveResDto);
                            return guestResDto;
                        }
                        )
                .orElseThrow(() -> new RuntimeException("There is no Guest with id" + Long.toString(guestId)));
    }




}

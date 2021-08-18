package com.example.listo.service;

import com.example.listo.domain.GuestEntity;
import com.example.listo.domain.ReserveEntity;
import com.example.listo.dto.GuestResDto;
import com.example.listo.dto.ReservationResDto;
import com.example.listo.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService{
    private final GuestRepository guestRepository;
    @Override
    public GuestResDto findGuest(Long guestId) {
        return guestRepository.findByIdFetch(guestId)
                .map(guestEntity->
                        {
                            GuestResDto guestResDto = new GuestResDto();
                            guestResDto.setGuestId(guestEntity.getId());
                            guestResDto.setGuestGrade(guestEntity.getGrade());
                            guestResDto.setGuestName(guestEntity.getUser().getName());
                            guestResDto.setGuestEmail(guestEntity.getUser().getEmail());
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

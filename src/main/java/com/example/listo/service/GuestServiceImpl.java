package com.example.listo.service;

import com.example.listo.domain.GuestEntity;
import com.example.listo.dto.GuestDto;
import com.example.listo.dto.ReservationDto;
import com.example.listo.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService{
    private final GuestRepository guestRepository;
    @Override
    public GuestDto findGuest(Long guestId) {
        guestRepository.findByIdFetch(guestId)
                .map(guestEntity -> {
                    GuestDto guestDto = new GuestDto();
                    guestDto.setId(guestEntity.getId());
                    guestDto.setEmail(guestEntity.getUser().getEmail());
                    guestDto.setName(guestEntity.getUser().getName());
                    guestEntity.getReserves().stream().map(reserveEntity ->
                            //TODO
                                    guestDto.getReservations().add(new ReservationDto())
                            );



                })
                .orElseThrow(() -> new RuntimeException("There is no Guest with id" + Long.toString(guestId)));



    }
}

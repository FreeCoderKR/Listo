package com.example.listo.service.user;

import com.example.listo.domain.reservation.ReserveEntity;
import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.domain.user.GuestEntity;
import com.example.listo.domain.user.OwnerEntity;
import com.example.listo.dto.reservation.ReservationOnlyResDto;
import com.example.listo.dto.restaurant.RestaurantOnlyResDto;
import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.user.owner.OwnerOnlyResDto;
import com.example.listo.dto.user.owner.OwnerResDto;
import com.example.listo.dto.user.owner.OwnerUpdateReqDto;
import com.example.listo.error.NoDataWithIdException;
import com.example.listo.repository.user.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository ownerRepository;
    @Override
    public void deleteOwner(Long ownerId) {
        OwnerEntity ownerEntity = ownerRepository.findById(ownerId).orElseThrow(() -> new NoDataWithIdException());
        ownerRepository.delete(ownerEntity);
    }

    @Override
    public OwnerOnlyResDto updateOwner(Long ownerId, OwnerUpdateReqDto request) {
        OwnerEntity ownerEntity = ownerRepository.findById(ownerId).orElseThrow(() -> new NoDataWithIdException());
        ownerEntity.setBusinessNumber(Optional.ofNullable(request.getBusinessNumber()).orElse(ownerEntity.getBusinessNumber()));
        ownerEntity.setPhone(Optional.ofNullable(request.getPhone()).orElse(ownerEntity.getPhone()));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(ownerEntity, OwnerOnlyResDto.class);
    }

    @Override
    public OwnerResDto findOneOwner(Long ownerId) {
        OwnerEntity ownerEntity = ownerRepository.findByIdFetch(ownerId).orElseThrow(() -> new NoDataWithIdException());
        RestaurantEntity restaurantEntity = ownerEntity.getRestaurant();
        ModelMapper mapper = new ModelMapper();
        OwnerResDto ownerResDto = mapper.map(ownerEntity, OwnerResDto.class);
        if(restaurantEntity != null){
            ownerResDto.setRestaurant(mapper.map(restaurantEntity, RestaurantOnlyResDto.class));
        }
        return ownerResDto;
    }
}

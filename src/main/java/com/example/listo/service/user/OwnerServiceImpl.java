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

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository ownerRepository;
    @Override
    public void deleteOwner(Long ownerId) {
        ownerRepository.findById(ownerId).orElseThrow(() -> new NoDataWithIdException());
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public OwnerOnlyResDto updateOwner(Long ownerId, OwnerUpdateReqDto request) {
        OwnerEntity ownerEntity = ownerRepository.findById(ownerId).orElseThrow(() -> new NoDataWithIdException());
        ownerEntity.setBusinessNumber(request.getBusinessNumber());
        ownerEntity.setPhone(request.getPhone());
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

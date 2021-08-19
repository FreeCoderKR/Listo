package com.example.listo.service.user;

import com.example.listo.domain.user.*;
import com.example.listo.dto.user.guest.GuestOnlyResDto;
import com.example.listo.dto.user.guest.GuestResDto;
import com.example.listo.dto.user.owner.OwnerOnlyResDto;
import com.example.listo.dto.user.owner.OwnerResDto;
import com.example.listo.dto.user.register.GuestReqDto;
import com.example.listo.dto.user.register.OwnerReqDto;
import com.example.listo.dto.user.register.UserRegisterResDto;
import com.example.listo.error.DuplicateDataException;
import com.example.listo.repository.user.GuestRepository;
import com.example.listo.repository.user.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final GuestRepository guestRepository;
    private final OwnerRepository ownerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public OwnerOnlyResDto createOwner(OwnerReqDto request) {
        String encPassword = passwordEncoder.encode(request.getPassword());
        ModelMapper modelMapper = new ModelMapper();
        OwnerEntity newOwner = modelMapper.map(request, OwnerEntity.class);
        newOwner.setEncPassword(encPassword);
        newOwner.setRole(Role.OWNER);
        ownerDuplicateCheck(newOwner.getEmail());
        OwnerEntity save = ownerRepository.save(newOwner);
        return modelMapper.map(save, OwnerOnlyResDto.class);
    }

    @Override
    public GuestOnlyResDto createGuest(GuestReqDto request) {
        String encPassword = passwordEncoder.encode(request.getPassword());
        ModelMapper modelMapper = new ModelMapper();
        GuestEntity newGuest = modelMapper.map(request, GuestEntity.class);
        newGuest.setEncPassword(encPassword);
        newGuest.setRole(Role.GUEST);
        newGuest.setGrade(Grade.BRONZE);
        guestDuplicateCheck(newGuest.getEmail());
        GuestEntity save = guestRepository.save(newGuest);
        return modelMapper.map(save, GuestOnlyResDto.class);
    }

    private void guestDuplicateCheck(String email) {
        GuestEntity guest = guestRepository.findByEmail(email);
        if(guest!= null){
            throw new DuplicateDataException();
        }
    }
    private void ownerDuplicateCheck(String email) {
        OwnerEntity owner = ownerRepository.findByEmail(email);
        if(owner!= null){
            throw new DuplicateDataException();
        }
    }


}

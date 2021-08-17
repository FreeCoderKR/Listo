package com.example.listo.service;

import com.example.listo.domain.GuestEntity;
import com.example.listo.domain.OwnerEntity;
import com.example.listo.domain.UserEntity;
import com.example.listo.dto.RegisterDto;
import com.example.listo.repository.GuestRepository;
import com.example.listo.repository.OwnerRepository;
import com.example.listo.vo.commonenum.Role;
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
    public RegisterDto createUser(RegisterDto dto) {
        String encPassword = passwordEncoder.encode(dto.getPassword());
        ModelMapper modelMapper = new ModelMapper();
        UserEntity user = modelMapper.map(dto, UserEntity.class);
        user.setEncPassword(encPassword);
        Long id;
        if(dto.getRole().equals(Role.OWNER)){
            OwnerEntity owner = new OwnerEntity();
            owner.setUser(user);
            OwnerEntity save = ownerRepository.save(owner);
            id= save.getId();

        }else{
            GuestEntity guest = new GuestEntity();
            guest.setUser(user);
            GuestEntity save = guestRepository.save(guest);
            id= save.getId();

        }
        dto.setId(id);
        return dto;

    }
}

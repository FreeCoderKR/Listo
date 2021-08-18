package com.example.listo.service;

import com.example.listo.domain.GuestEntity;
import com.example.listo.domain.OwnerEntity;
import com.example.listo.domain.UserEntity;
import com.example.listo.dto.UserReqDto;
import com.example.listo.dto.UserResDto;
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
    public UserResDto createUser(UserReqDto dto) {
        String encPassword = passwordEncoder.encode(dto.getPassword());
        ModelMapper modelMapper = new ModelMapper();
        UserEntity user = modelMapper.map(dto, UserEntity.class);
        user.setEncPassword(encPassword);
        Long id;
        if(dto.getRole().equals(Role.OWNER)){
            OwnerEntity owner = new OwnerEntity(user);
            OwnerEntity save = ownerRepository.save(owner);
            id= save.getId();

        }else{
            GuestEntity guest = new GuestEntity(user);
            GuestEntity save = guestRepository.save(guest);
            id= save.getId();

        }
        UserResDto resDto = modelMapper.map(user, UserResDto.class);
        resDto.setRole(dto.getRole());
        resDto.setId(id);
        return resDto;

    }

}

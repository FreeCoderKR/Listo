package com.example.listo.controller;

import com.example.listo.dto.RegisterDto;
import com.example.listo.service.UserService;
import com.example.listo.vo.request.UserRegisterRequest;
import com.example.listo.vo.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody  UserRegisterRequest request){
        ModelMapper modelMapper = new ModelMapper();
        RegisterDto registerDto = modelMapper.map(request, RegisterDto.class);
        RegisterDto savedDto = userService.createUser(registerDto);
        return ResponseEntity.ok(modelMapper.map(savedDto, UserRegisterResponse.class));

    }
}

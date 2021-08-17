package com.example.listo.service;

import com.example.listo.dto.RegisterDto;

public interface UserService {
    RegisterDto createUser(RegisterDto dto);
}

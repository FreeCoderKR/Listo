package com.example.listo.service;

import com.example.listo.dto.UserReqDto;
import com.example.listo.dto.UserResDto;

public interface UserService {
    UserResDto createUser(UserReqDto dto);

}

package com.example.listo.dto.user.login;

import com.example.listo.domain.user.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserLoginResDto {
    private Long userId;
    private String msg;
    private String token;
    @Enumerated(EnumType.STRING)
    private Role role;

}

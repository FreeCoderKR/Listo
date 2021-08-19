package com.example.listo.dto.user.register;

import com.example.listo.domain.user.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserRegisterResDto {
    private Long id;
    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
}

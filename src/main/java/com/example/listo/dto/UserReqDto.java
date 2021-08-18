package com.example.listo.dto;

import com.example.listo.vo.commonenum.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserReqDto {
    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
}

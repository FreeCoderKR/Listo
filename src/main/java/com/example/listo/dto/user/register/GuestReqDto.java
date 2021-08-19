package com.example.listo.dto.user.register;

import com.example.listo.domain.user.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class GuestReqDto {
    private String email;
    private String password;
    private String name;
    private String phone;

    private String nickname;
}

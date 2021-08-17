package com.example.listo.vo.response;

import com.example.listo.vo.commonenum.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserRegisterResponse {
    private String id;
    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

}

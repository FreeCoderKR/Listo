package com.example.listo.dto;

import com.example.listo.vo.commonenum.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserResDto {
    private Long id;
    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
}

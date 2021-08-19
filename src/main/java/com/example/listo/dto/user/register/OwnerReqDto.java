package com.example.listo.dto.user.register;

import lombok.Data;

@Data
public class OwnerReqDto {
    private String email;
    private String password;
    private String name;
    private String phone;

    private int businessNumber;
}

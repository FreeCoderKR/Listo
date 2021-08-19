package com.example.listo.dto.user.login;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserLoginReqDto {
    @NonNull
    private String email;
    @NonNull
    private String password;
}

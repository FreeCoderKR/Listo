package com.example.listo.dto.user.register;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OwnerReqDto {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 4)
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    @NotNull
    private int businessNumber;
}

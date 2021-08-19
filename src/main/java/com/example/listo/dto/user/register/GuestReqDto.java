package com.example.listo.dto.user.register;

import com.example.listo.domain.user.Role;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class GuestReqDto {
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
    @NotEmpty
    private String nick;
}

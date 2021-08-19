package com.example.listo.dto.user.guest;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class GuestUpdateReqDto {
    private String phone;
    private String nickname;
}

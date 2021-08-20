package com.example.listo.dto.user.guest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MyCouponReqDto {
    @NotNull
    private Long guestId;
}

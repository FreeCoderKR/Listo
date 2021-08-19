package com.example.listo.dto.user.guest;

import lombok.Data;

@Data
public class GuestOnlyResDto {
//    여러 군데에서 guest를 부분으로 조회할때 사용하는 dto(reservation or coupon 내용x)
    private Long id;
    private String name;
    private String email;
    private String phone;
}

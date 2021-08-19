package com.example.listo.dto.user.guest;

import com.example.listo.domain.user.Grade;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestOnlyResDto {
//    여러 군데에서 guest를 부분으로 조회할때 사용하는 dto(reservation or coupon 내용x)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String nick;
    private Grade grade;
}

package com.example.listo.dto.user.owner;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerOnlyResDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int businessNumber;
}

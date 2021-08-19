package com.example.listo.dto.restaurant;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MenuReqDto {
    @NotEmpty
    private String name;
    @NotNull
    private int price;
    @NotNull
    @Size(min = 1, message = "stock has to be more than 0")
    private int stock;
}

package com.example.listo.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
public class RestaurantRegisterResponse {
    private Long id;
    private String name;
    private String location;
    private int capacity;


}

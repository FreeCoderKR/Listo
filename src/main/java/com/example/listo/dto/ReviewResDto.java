package com.example.listo.dto;

import lombok.Data;

@Data
public class ReviewResDto {

    private Long id;
    private Long orderId;
    private int point;
    private String comment;
    private String guestName;
    private String restaurantName;

}

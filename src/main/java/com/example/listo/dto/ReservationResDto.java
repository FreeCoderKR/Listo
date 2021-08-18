package com.example.listo.dto;

import com.example.listo.domain.ReserveEntity;
import com.example.listo.vo.commonenum.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResDto {
    private Long id;
    private LocalDate date;
    private int time;
    private int count;
    private ReservationStatus status;
    private String msg;
    public ReservationResDto(){

    }

    public ReservationResDto(ReserveEntity reserveEntity) {
        this.id=reserveEntity.getId();
        this.date=reserveEntity.getDate();
        this.time=reserveEntity.getTime();
        this.count=reserveEntity.getCount();
        this.status=reserveEntity.getStatus();
    }
}

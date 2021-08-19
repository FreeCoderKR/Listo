package com.example.listo.domain.reservation;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    REQUESTED, CONFIRMED, REJECTED, FINISHED
}

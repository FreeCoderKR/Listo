package com.example.listo.repository.reservation;


import com.example.listo.domain.reservation.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReserveEntity, Long> {

    List<ReserveEntity> findAllByRestaurantIdAndDateAndTime(Long restaurantId, LocalDate Date, int Time);
}

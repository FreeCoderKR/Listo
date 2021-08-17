package com.example.listo.repository;


import com.example.listo.domain.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReserveEntity, Long> {
}

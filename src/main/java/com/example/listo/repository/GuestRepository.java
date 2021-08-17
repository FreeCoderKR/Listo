package com.example.listo.repository;

import com.example.listo.domain.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<GuestEntity,Long> {
}

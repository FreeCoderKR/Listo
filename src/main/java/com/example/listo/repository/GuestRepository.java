package com.example.listo.repository;

import com.example.listo.domain.GuestEntity;
import com.example.listo.domain.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<GuestEntity,Long> {
    @Query("select g from GuestEntity g join fetch g.user where g.id = :id ")
    Optional<GuestEntity> findByIdFetch(Long id);
}

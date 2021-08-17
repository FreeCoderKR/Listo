package com.example.listo.repository;

import com.example.listo.domain.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity,Long> {
}

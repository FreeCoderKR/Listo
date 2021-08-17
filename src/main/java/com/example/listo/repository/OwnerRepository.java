package com.example.listo.repository;

import com.example.listo.domain.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OwnerRepository extends JpaRepository<OwnerEntity,Long> {
    @Query("select o from OwnerEntity o join fetch o.user where o.id = :id ")
    OwnerEntity findByIdFetch(Long id);
}

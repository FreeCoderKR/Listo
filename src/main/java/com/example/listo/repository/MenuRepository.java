package com.example.listo.repository;

import com.example.listo.domain.MenuEntity;
import com.example.listo.domain.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    @Query("select m from MenuEntity m join fetch m.restaurant where m.id = :id ")
    Optional<MenuEntity> findByIdFetch(Long id);
}

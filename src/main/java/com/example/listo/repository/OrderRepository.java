package com.example.listo.repository;

import com.example.listo.domain.GuestEntity;
import com.example.listo.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("select o from OrderEntity o join fetch o.guest join fetch o.restaurant join fetch o.review where o.id=:id")
    Optional<OrderEntity> findByIdFetch(Long id);
}

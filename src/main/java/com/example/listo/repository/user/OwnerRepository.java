package com.example.listo.repository.user;

import com.example.listo.domain.order.OrderEntity;
import com.example.listo.domain.user.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerEntity,Long> {
    @Query(value = "select o from OwnerEntity o left join fetch o.restaurant where o.id=:id")
    Optional<OwnerEntity> findByIdFetch(@Param("id") Long id);
    OwnerEntity findByEmail(String email);
}

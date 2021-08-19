package com.example.listo.repository.restaurant;

import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.domain.user.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
    @Query(value = "select r from RestaurantEntity r left join fetch r.owner where r.id=:id")
    Optional<RestaurantEntity> findByIdFetch(@Param("id") Long id);
}

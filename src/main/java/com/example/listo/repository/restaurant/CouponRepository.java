package com.example.listo.repository.restaurant;

import com.example.listo.domain.restaurant.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.remote.JMXPrincipal;
import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
    List<CouponEntity> findAllByRestaurantId(Long restaurantId);
}

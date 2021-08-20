package com.example.listo.repository.restaurant;

import com.example.listo.domain.restaurant.MyCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCouponRepository extends JpaRepository<MyCouponEntity, Long> {
}

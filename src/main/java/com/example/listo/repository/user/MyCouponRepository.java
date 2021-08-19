package com.example.listo.repository.user;

import com.example.listo.domain.user.MyCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCouponRepository extends JpaRepository<MyCouponEntity, Long> {
}

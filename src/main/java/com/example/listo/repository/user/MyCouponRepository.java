package com.example.listo.repository.user;

import com.example.listo.domain.user.MyCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyCouponRepository extends JpaRepository<MyCouponEntity, Long> {

    @Query(value = "select mc from MyCouponEntity mc left join fetch mc.coupon where mc.guest.id = :id")
    List<MyCouponEntity> findAllFetchByGuestId(@Param("id") Long guestId);

    MyCouponEntity findByGuestIdAndCouponId(Long guestId, Long couponId);
}

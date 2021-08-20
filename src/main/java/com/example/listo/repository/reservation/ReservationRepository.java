package com.example.listo.repository.reservation;


import com.example.listo.domain.reservation.ReserveEntity;
import com.example.listo.domain.user.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReserveEntity, Long> {

    List<ReserveEntity> findAllByRestaurantIdAndDateAndTime(Long restaurantId, LocalDate Date, int Time);

    List<ReserveEntity> findAllByRestaurantId(Long restaurantId);

    List<ReserveEntity> findAllByGuestId(Long guestId);

    @Query(value = "select r from ReserveEntity r left join fetch r.guest left join fetch r.restaurant where r.id=:id")
    Optional<ReserveEntity> findByIdFetch(@Param("id") Long id);

    List<ReserveEntity> findAllByRestaurantIdAndDate(Long restaurantId, LocalDate date);
}

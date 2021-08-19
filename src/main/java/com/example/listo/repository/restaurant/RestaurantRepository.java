package com.example.listo.repository.restaurant;

import com.example.listo.domain.restaurant.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
}

package com.example.listo.repository.user;

import com.example.listo.domain.user.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<GuestEntity,Long> {

}

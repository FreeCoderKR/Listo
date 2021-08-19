package com.example.listo.repository.user;

import com.example.listo.domain.user.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity,Long> {

}

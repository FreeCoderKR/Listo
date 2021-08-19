package com.example.listo.domain.user;


import com.example.listo.domain.restaurant.RestaurantEntity;
import com.example.listo.domain.user.UserEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "owner")
public class OwnerEntity extends UserEntity {

    @Id @GeneratedValue
    @Column(name = "owner_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    private int businessNumber;


}

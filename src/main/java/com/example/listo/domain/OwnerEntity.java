package com.example.listo.domain;


import com.example.listo.common.BaseTimeEntity;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@Table(name = "owner")
public class OwnerEntity extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "owner_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    public OwnerEntity(UserEntity user) {
        this.user = user;
    }
    public OwnerEntity(){

    }
}

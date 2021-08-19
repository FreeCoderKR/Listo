package com.example.listo.domain.user;

import com.example.listo.domain.reservation.ReserveEntity;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "guest")
public class GuestEntity extends UserEntity {

    @Id @GeneratedValue
    @Column(name = "guest_id")
    private Long id;

    @ColumnDefault("1")
    private int grade;
    @Column(name = "guest_nick")
    private String nick;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<ReserveEntity> reserves = new ArrayList<>();

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<MyCouponEntity> coupons = new ArrayList<>();



}

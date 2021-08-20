package com.example.listo.domain.user;

import com.example.listo.domain.reservation.ReserveEntity;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class GuestEntity extends UserEntity {

    @Id @GeneratedValue
    @Column(name = "guest_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "guest_grade")
    private Grade grade;
    @Column(name = "nick_name")
    private String nick;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<ReserveEntity> reserves = new ArrayList<>();
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<MyCouponEntity> mycoupons = new ArrayList<>();


}

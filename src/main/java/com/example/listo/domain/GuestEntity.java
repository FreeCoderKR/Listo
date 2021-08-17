package com.example.listo.domain;

import com.example.listo.common.BaseTimeEntity;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "guest")
public class GuestEntity extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "guest_id")
    private Long id;

    @ColumnDefault("1")
    private int grade;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guest")
    private List<ReserveEntity> reserves = new ArrayList<>();




}

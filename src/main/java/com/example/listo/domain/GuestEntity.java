package com.example.listo.domain;

import com.example.listo.common.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.aspectj.weaver.ast.Or;
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


    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<ReserveEntity> reserves = new ArrayList<>();

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;

    public GuestEntity(UserEntity user) {
        this.user = user;
    }
    public GuestEntity(){

    }
}

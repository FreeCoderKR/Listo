package com.example.listo.domain.user;

import com.example.listo.common.BaseTimeEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public abstract class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String encPassword;


    @Column(nullable = false)
    private String phone;
}

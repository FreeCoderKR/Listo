package com.example.listo.domain.user;

import com.example.listo.common.BaseTimeEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user", uniqueConstraints={
        @UniqueConstraint(
                columnNames={"role","email"}
        )
})
@DynamicUpdate
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
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String encPassword;

    @Column(nullable = false)
    private String phone;
}

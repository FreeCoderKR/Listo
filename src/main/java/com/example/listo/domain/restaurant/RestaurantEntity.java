package com.example.listo.domain.restaurant;

import com.example.listo.common.BaseTimeEntity;
import com.example.listo.domain.user.OwnerEntity;
import com.example.listo.dto.restaurant.RestaurantStatus;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "restaurant")
public class RestaurantEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "restaurant_name", nullable = false)
    private String name;

    @Column(name = "restaurant_capacity", nullable = false)
    private int capacity;

    @Column(name = "restaurant_phone", nullable = false)
    private String phone;

    @Column(name = "restaurant_location", nullable = false)
    private String location;
    @Column(name = "restaurant_grade", nullable = false)
    private int grade;
    @Column(name = "operating_status", nullable = false)
    private RestaurantStatus status;

    @OneToOne(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OwnerEntity owner;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<MenuEntity> menus = new ArrayList<>();




    public void setOwner(OwnerEntity owner) {
        owner.setRestaurant(this);
        this.owner = owner;
    }

    public RestaurantEntity(String name, int capacity, String location, String phone, OwnerEntity owner) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.phone=phone;
        this.status=RestaurantStatus.OPEN;
        this.grade=1;
        this.setOwner(owner);
    }
    public RestaurantEntity(){

    }
}

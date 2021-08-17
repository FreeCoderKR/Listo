package com.example.listo.domain;

import com.example.listo.common.BaseTimeEntity;
import com.example.listo.error.NoAvailableReservaitonException;
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

    @Column(name = "restaurant_capacity")
    @ColumnDefault("10")
    private int capacity;

    @Column(name = "restaurant_location")
    private String location;

    @Column(name = "restaurant_grade")
    @ColumnDefault("1")
    private int grade;

    @OneToOne(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OwnerEntity owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<ReserveEntity> reserves = new ArrayList<>();


    public void setOwner(OwnerEntity owner) {
        owner.setRestaurant(this);
        this.owner = owner;
    }

    public void addCapacity(int reserveCount){
        this.capacity+=reserveCount;

    }
    public void removeCapacity(int reserveCount){
        int tempCapacity = this.capacity-reserveCount;
        if(tempCapacity<0){
            throw new NoAvailableReservaitonException("Reservation Not Available. only"+this.capacity+" is available at the time");
        }
        this.capacity=tempCapacity;
    }
}

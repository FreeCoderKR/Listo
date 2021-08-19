package com.example.listo.domain.restaurant;

import com.example.listo.common.BaseTimeEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "menu")
public class MenuEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;
    @Column(name = "menu_name")
    private String name;
    @Column(name = "menu_price")
    private int price;
    @Column(name = "menu_stock")
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    public void setRestaurant(RestaurantEntity restaurant){
        restaurant.getMenus().add(this);
        this.restaurant=restaurant;
    }

    public MenuEntity() {
    }

    public MenuEntity(String name, int price, int stock, RestaurantEntity restaurant) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.setRestaurant(restaurant);
    }
}

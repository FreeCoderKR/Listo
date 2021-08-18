package com.example.listo.domain;

import com.example.listo.error.OutOfStockException;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_menu")
public class OrderMenuEntity {

    @Id @GeneratedValue
    @Column(name = "order_menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity order;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

    private int count;

    public OrderMenuEntity() {
    }

    public OrderMenuEntity(int count, MenuEntity menu){
        int result;
        result= menu.getStock()-count;
        if(result<0){
            throw new OutOfStockException("there is no stock available");
        }
        menu.setStock(result);
        this.setMenu(menu);
    }
    public void cancelOrderMenu(MenuEntity menu, int count){
        menu.setStock(menu.getStock()+count);
        this.setMenu(menu);
    }
}

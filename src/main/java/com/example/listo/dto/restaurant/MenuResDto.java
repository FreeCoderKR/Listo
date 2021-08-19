package com.example.listo.dto.restaurant;

import com.example.listo.domain.restaurant.MenuEntity;
import lombok.Data;

@Data
public class MenuResDto {
    private Long id;
    private String name;
    private int stock;
    private int price;

    public MenuResDto() {
    }

    public MenuResDto(MenuEntity menuEntity) {
        this.id=menuEntity.getId();
        this.name=menuEntity.getName();
        this.stock=menuEntity.getStock();
        this.price=menuEntity.getPrice();
    }
}

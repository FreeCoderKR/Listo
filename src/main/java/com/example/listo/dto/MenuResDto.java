package com.example.listo.dto;

import com.example.listo.domain.MenuEntity;
import lombok.Data;

@Data
public class MenuResDto {
    private Long menuId;
    private String menuName;
    private int stock;
    private int price;

    public MenuResDto() {
    }

    public MenuResDto(MenuEntity menuEntity) {
        this.menuId=menuEntity.getId();
        this.menuName=menuEntity.getName();
        this.stock=menuEntity.getStock();
        this.price=menuEntity.getPrice();
    }
}

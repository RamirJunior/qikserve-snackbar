package com.ramir.qikserve.snackbar.domain.models.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Item {

    private String id;
    private String name;
    private BigDecimal price;
    private List<Promotion> promotions;
    private Integer quantity;

    public Item(
            String id,
            String name,
            BigDecimal price,
            Integer quantity,
            List<Promotion> promotions
    ) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = promotions.get(0).applyPromotion(quantity, price);
    }
}

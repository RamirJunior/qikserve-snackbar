package com.ramir.qikserve.snackbar.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
public class Order {

    private UUID id;
    private List<Item> items;
    private BigDecimal requiredPayment;

    public Order(List<Item> items) {
        this.id = UUID.randomUUID();
        this.items = items;
        this.requiredPayment = calculateRequiredPayment(getItems());
    }

    private BigDecimal calculateRequiredPayment(List<Item> items) {
        return items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

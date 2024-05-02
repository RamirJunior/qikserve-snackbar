package com.ramir.qikserve.snackbar.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderResponse {

    private UUID id;
    private List<ItemResponse> items;
    private BigDecimal total;
    private BigDecimal totalDiscount;
    private BigDecimal requiredPayment;

    public OrderResponse() {
        this.id = UUID.randomUUID();
    }
}

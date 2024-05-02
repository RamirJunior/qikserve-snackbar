package com.ramir.qikserve.snackbar.api.dtos;

import com.ramir.qikserve.snackbar.domain.models.entities.Promotion;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ItemResponse {

    private String id;
    private String name;
    private BigDecimal price;
    private List<Promotion> promotions;
}

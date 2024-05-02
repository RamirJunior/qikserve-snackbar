package com.ramir.qikserve.snackbar.dto;

import com.ramir.qikserve.snackbar.model.Promotion;
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

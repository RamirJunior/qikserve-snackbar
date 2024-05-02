package com.ramir.qikserve.snackbar.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    private String id;
    private String name;
    private BigDecimal price;
}

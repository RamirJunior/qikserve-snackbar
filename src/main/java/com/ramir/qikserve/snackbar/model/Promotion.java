package com.ramir.qikserve.snackbar.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ramir.qikserve.snackbar.model.promotion.BuyXGetYFreePromotion;
import com.ramir.qikserve.snackbar.model.promotion.FlatPercentPromotion;
import com.ramir.qikserve.snackbar.model.promotion.QuantityBasedPriceOverridePromotion;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BuyXGetYFreePromotion.class, name = "BUY_X_GET_Y_FREE"),
        @JsonSubTypes.Type(value = QuantityBasedPriceOverridePromotion.class, name = "QTY_BASED_PRICE_OVERRIDE"),
        @JsonSubTypes.Type(value = FlatPercentPromotion.class, name = "FLAT_PERCENT")
})
public abstract class Promotion {

    private String id;
    private String type;
    private Integer requiredQty;
    private BigDecimal price;
    private Integer freeQty;
    private Double amount;

    public abstract BigDecimal applyPromotion(Integer itemQuantity, BigDecimal itemPrice);
}

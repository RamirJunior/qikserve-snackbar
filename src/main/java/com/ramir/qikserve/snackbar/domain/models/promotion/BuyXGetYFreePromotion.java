package com.ramir.qikserve.snackbar.domain.models.promotion;

import com.ramir.qikserve.snackbar.domain.models.entities.Promotion;

import java.math.BigDecimal;

public class BuyXGetYFreePromotion extends Promotion {

    private int freeQuantity;
    private int requiredQuantity;

    public BuyXGetYFreePromotion() {
    }

    public BuyXGetYFreePromotion(Promotion promotion) {
        this.freeQuantity = promotion.getFreeQty();
        this.requiredQuantity = promotion.getRequiredQty();
    }

    public BigDecimal applyPromotion(Integer quantityItem, BigDecimal price) {
        if (quantityItem >= requiredQuantity)
            return price.multiply(BigDecimal.valueOf(quantityItem - freeQuantity));

        return price.multiply(BigDecimal.valueOf(quantityItem));
    }
}

package com.ramir.qikserve.snackbar.domain.models.promotion;

import com.ramir.qikserve.snackbar.domain.models.entities.Promotion;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Setter
public class QuantityBasedPriceOverridePromotion extends Promotion {

    private BigDecimal overrideItemPrice;

    public QuantityBasedPriceOverridePromotion() {
    }

    public QuantityBasedPriceOverridePromotion(Promotion promotion) {
        setId(promotion.getId());
        setRequiredQty(promotion.getRequiredQty());
        this.overrideItemPrice = promotion.getPrice();
    }

    public BigDecimal applyPromotion(Integer itemQuantity, BigDecimal itemPrice) {
        return (itemQuantity > 1 && itemQuantity % 2 == 0)
                ? getPromotionalPrice(itemQuantity)
                : itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
    }

    private @NotNull BigDecimal getPromotionalPrice(Integer itemQuantity) {
        var quantityWithPromotion = itemQuantity / 2;
        return overrideItemPrice.multiply(BigDecimal.valueOf(quantityWithPromotion));
    }
}

package com.ramir.qikserve.snackbar.model.promotion;

import com.ramir.qikserve.snackbar.model.Promotion;
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
        if (itemQuantity > 1) {
            if (itemQuantity % 2 == 0) {
                return getPromotionalPrice(itemQuantity);
            } else {
                return itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
            }
        }
        return itemPrice;
    }

    private @NotNull BigDecimal getPromotionalPrice(Integer itemQuantity) {
        var quantityWithPromotion = itemQuantity / 2;
        return overrideItemPrice.multiply(BigDecimal.valueOf(quantityWithPromotion));
    }
}

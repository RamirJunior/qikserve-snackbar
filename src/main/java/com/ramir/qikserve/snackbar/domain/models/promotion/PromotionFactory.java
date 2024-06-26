package com.ramir.qikserve.snackbar.domain.models.promotion;

import com.ramir.qikserve.snackbar.domain.models.entities.Promotion;

import java.util.List;

import static com.ramir.qikserve.snackbar.domain.util.Constants.*;

public class PromotionFactory {

    public Promotion createPromotion(List<Promotion> promotions) {

        return switch (promotions.get(0).getType()) {
            case BUY_X_GET_Y_FREE -> new BuyXGetYFreePromotion(promotions.get(0));
            case FLAT_PERCENT -> new FlatPercentPromotion(promotions.get(0));
            case QTY_BASED_PRICE_OVERRIDE -> new QuantityBasedPriceOverridePromotion(promotions.get(0));
            default -> throw new IllegalStateException("Promotion not supported: " + promotions.get(0).getType());
        };
    }
}

package com.ramir.qikserve.snackbar.model.promotion;

import com.ramir.qikserve.snackbar.model.Promotion;

import java.math.BigDecimal;


public class FlatPercentPromotion extends Promotion {

    private double amount;

    public FlatPercentPromotion(){

    }

    public FlatPercentPromotion(Promotion promotion) {
        this.amount = promotion.getAmount();
    }

    public BigDecimal applyPromotion(Integer itemQuantity, BigDecimal itemPrice) {
        BigDecimal percentualDecimal = BigDecimal.valueOf(amount / 100);
        BigDecimal discount = itemPrice.multiply(percentualDecimal);
        return itemPrice.subtract(discount);
    }
}

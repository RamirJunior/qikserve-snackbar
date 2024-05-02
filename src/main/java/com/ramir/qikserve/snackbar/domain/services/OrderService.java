package com.ramir.qikserve.snackbar.domain.services;

import com.ramir.qikserve.snackbar.api.dtos.OrderRequest;
import com.ramir.qikserve.snackbar.domain.models.entities.Item;
import com.ramir.qikserve.snackbar.domain.models.entities.Order;
import com.ramir.qikserve.snackbar.domain.models.promotion.PromotionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final WiremockService productService;

    public Order createOrder(OrderRequest orderRequest) {

        var items = orderRequest.getItems().stream()
                .map(itemRequest -> {
                    var wiremockItem = productService.getProduct(itemRequest.getId());
                    var promotion = new PromotionFactory().createPromotion(wiremockItem.getPromotions());
                    return new Item(
                            wiremockItem.getId(),
                            wiremockItem.getName(),
                            wiremockItem.getPrice(),
                            itemRequest.getQuantity(),
                            List.of(promotion));
                })
                .toList();

        return new Order(items);
    }
}

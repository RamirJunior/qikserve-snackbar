package com.ramir.qikserve.snackbar.api.controllers;

import com.ramir.qikserve.snackbar.api.dtos.OrderRequest;
import com.ramir.qikserve.snackbar.domain.models.entities.Item;
import com.ramir.qikserve.snackbar.domain.models.promotion.PromotionFactory;
import com.ramir.qikserve.snackbar.domain.services.OrderService;
import com.ramir.qikserve.snackbar.domain.services.WiremockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final WiremockService wiremockService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest) {
        List<Item> items = orderRequest.getItems().stream()
                .map(itemRequest -> {
                    var wiremockItem = wiremockService.getProduct(itemRequest.getId());
                    var promotion = new PromotionFactory().createPromotion(wiremockItem.getPromotions());
                    return new Item(
                            wiremockItem.getId(),
                            wiremockItem.getName(),
                            wiremockItem.getPrice(),
                            itemRequest.getQuantity(),
                            List.of(promotion));
                })
                .toList();

        var response = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity getWiremockProducts(OrderRequest orderRequest) {
        var response = wiremockService.getProducts();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{productId}")
    public ResponseEntity getProduct(@PathVariable String productId) {
        var response = wiremockService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

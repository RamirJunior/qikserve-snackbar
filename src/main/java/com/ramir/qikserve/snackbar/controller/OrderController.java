package com.ramir.qikserve.snackbar.controller;

import com.ramir.qikserve.snackbar.dto.ItemRequest;
import com.ramir.qikserve.snackbar.dto.OrderRequest;
import com.ramir.qikserve.snackbar.model.Item;
import com.ramir.qikserve.snackbar.model.Promotion;
import com.ramir.qikserve.snackbar.model.promotion.PromotionFactory;
import com.ramir.qikserve.snackbar.service.OrderService;
import com.ramir.qikserve.snackbar.service.WiremockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final WiremockService wiremockService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest) {
        List<Item> items = new ArrayList<>();
        List<Promotion> promotionList = new ArrayList<>();
        PromotionFactory factory = new PromotionFactory();
        for (ItemRequest itemRequest : orderRequest.getItems()) {
            var wiremockItem = wiremockService.getProduct(itemRequest.getId());

            var promotion = factory.createPromotion(wiremockItem.getPromotions());
            promotionList.add(promotion);

            items.add(
                    new Item(
                            wiremockItem.getId(),
                            wiremockItem.getName(),
                            wiremockItem.getPrice(),
                            itemRequest.getQuantity(),
                            promotionList
                    )
            );

        }
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

package com.ramir.qikserve.snackbar.service;

import com.ramir.qikserve.snackbar.dto.ItemRequest;
import com.ramir.qikserve.snackbar.dto.OrderRequest;
import com.ramir.qikserve.snackbar.model.Item;
import com.ramir.qikserve.snackbar.model.Order;
import com.ramir.qikserve.snackbar.model.Promotion;
import com.ramir.qikserve.snackbar.model.promotion.PromotionFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private WiremockService productService;

    public OrderService(WiremockService productService) {
        this.productService = productService;
    }

    public Order createOrder(OrderRequest orderRequest) {
        List<ItemRequest> itemsRequestList = orderRequest.getItems();
        List<Promotion> promotionList = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        PromotionFactory factory = new PromotionFactory();

        for (ItemRequest itemRequest : itemsRequestList) {
            var wiremockItem = productService.getProduct(itemRequest.getId());

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
        return new Order(items);
    }
}

package com.ramir.qikserve.snackbar.api.controllers;

import com.ramir.qikserve.snackbar.api.dtos.ItemRequest;
import com.ramir.qikserve.snackbar.api.dtos.ItemResponse;
import com.ramir.qikserve.snackbar.api.dtos.OrderRequest;
import com.ramir.qikserve.snackbar.domain.models.entities.Item;
import com.ramir.qikserve.snackbar.domain.models.entities.Order;
import com.ramir.qikserve.snackbar.domain.models.entities.Promotion;
import com.ramir.qikserve.snackbar.domain.models.promotion.QuantityBasedPriceOverridePromotion;
import com.ramir.qikserve.snackbar.domain.services.OrderService;
import com.ramir.qikserve.snackbar.domain.services.WiremockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    @Mock
    private WiremockService wiremockService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateOrder() {
        OrderRequest orderRequest = new OrderRequest();
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId("Dwt5F7KAhi");
        itemRequest.setQuantity(2);
        orderRequest.setItems(Collections.singletonList(itemRequest));


        Item mockItem = new Item("Dwt5F7KAhi", "Amazing Pizza!", BigDecimal.valueOf(10.99), 2, Collections.emptyList());
        List<Promotion> promotionList = new ArrayList<>();
        promotionList.add(new QuantityBasedPriceOverridePromotion());
        ItemResponse mockItemResponse = new ItemResponse("Dwt5F7KAhi", "Amazing Pizza!", BigDecimal.valueOf(10.99), promotionList);
        when(wiremockService.getProduct("Dwt5F7KAhi")).thenReturn(mockItemResponse);

        List<Item> itemList = Collections.singletonList(mockItem);
        Order mockOrder = new Order(itemList);
        when(orderService.createOrder(orderRequest)).thenReturn(mockOrder);

        ResponseEntity responseEntity = orderController.createOrder(orderRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(itemList, responseEntity.getBody());
    }
}

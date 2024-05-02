package com.ramir.qikserve.snackbar.service;


import com.ramir.qikserve.snackbar.dto.ItemResponse;
import com.ramir.qikserve.snackbar.dto.ProductResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class WiremockService {

    public ItemResponse getProduct(String productId) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String url = "http://localhost:8081/products/" + productId;
        var response = restTemplate.getForObject(url, ItemResponse.class);
        return response;
    }

    public List<ProductResponse> getProducts() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String url = "http://localhost:8081/products";
        ProductResponse[] responseData = restTemplate.getForObject(url, ProductResponse[].class);
        return Arrays.asList(responseData);
    }
}

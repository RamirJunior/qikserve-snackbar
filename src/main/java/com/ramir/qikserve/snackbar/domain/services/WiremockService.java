package com.ramir.qikserve.snackbar.domain.services;


import com.ramir.qikserve.snackbar.api.dtos.ItemResponse;
import com.ramir.qikserve.snackbar.api.dtos.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.ramir.qikserve.snackbar.domain.util.Constants.BASE_URL;

@Service
public class WiremockService {

    public ItemResponse getProduct(String productId) {
        return new RestTemplate().getForObject(BASE_URL + "/" + productId, ItemResponse.class);
    }

    public List<ProductResponse> getProducts() {
        return Arrays.asList(
                Objects.requireNonNull(
                        new RestTemplate().getForEntity(BASE_URL, ProductResponse[].class).getBody()));
    }
}

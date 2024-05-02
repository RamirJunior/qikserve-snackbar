package com.ramir.qikserve.snackbar.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<ItemRequest> items;
}

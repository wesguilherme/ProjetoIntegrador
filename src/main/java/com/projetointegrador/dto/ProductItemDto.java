package com.projetointegrador.dto;


import lombok.Data;

@Data
public class ProductItemDto {

    private String productId;
    private Integer quantity;

    public ProductItemDto() {
    }

    public ProductItemDto(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

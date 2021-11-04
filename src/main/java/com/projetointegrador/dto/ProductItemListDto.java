package com.projetointegrador.dto;


import lombok.Data;

@Data
public class ProductItemListDto {

    private Long purchaseItemId;
    private String productId;
    private Integer quantity;

    public ProductItemListDto() {

    }

    public ProductItemListDto(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

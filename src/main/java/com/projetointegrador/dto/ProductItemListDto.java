package com.projetointegrador.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemListDto {

    private Long purchaseItemId;
    private String productId;
    private Integer quantity;

}

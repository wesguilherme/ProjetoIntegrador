package com.projetointegrador.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class PurchaseOrderListDto {

    @NotNull
    private List<ProductItemListDto> products;

    public static List<ProductItemDto> convert(List<ProductItemListDto> productItemListDto){
        List<ProductItemDto> productItemDto =  new ArrayList<>();

        for (ProductItemListDto item: productItemListDto) {
            ProductItemDto p= new ProductItemDto();
            p.setProductId(item.getProductId());
            p.setQuantity(item.getQuantity());
            productItemDto.add(p);
        }

        return productItemDto;
    }

}

package com.projetointegrador.dto;

import com.projetointegrador.entity.*;
import com.projetointegrador.service.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDto {

    @NotNull
    @NotBlank
    private String shippingId;

    private String warehouseCode;

    private Long buyerId;

    private String productId;

    public Shipping convert(ShippingDto shippingDto, WarehouseService warehouseService, BuyerService buyerService, ProductService productService) {
        Shipping shipping = new Shipping();
        shipping.setShippingId(shippingDto.getShippingId());

        Warehouse warehouse = warehouseService.getByCode(shippingDto.getWarehouseCode());
        shipping.setWarehouse(warehouse);

        Buyer buyer = buyerService.getByIdBuyer(shippingDto.getBuyerId());
        shipping.setBuyer(buyer);

        Product product = productService.getByIdProduct(shippingDto.getProductId());
        shipping.setProduct(product);

        return shipping;
    }

//    public static List<ShippingDto> convertDto(List<Shipping> shipping){
//        List<ShippingDto> shippingDtos = new ArrayList<>();
//
//        for (Shipping item : shipping) {
//            ShippingDto shippingDto = new ShippingDto();
//            shippingDto.setShippingId(item.getShippingId());
//            shippingDto.setWarehouseCode(item.getWarehouse().getWarehouseCode());
//            shippingDto.setBuyerId(item.getBuyer().getBuyerId());
//            shippingDto.setProductId(item.getProduct().getProductId());
//
//            shippingDtos.add(shippingDto);
//        }
//
//        return shippingDtos;
//    }
}

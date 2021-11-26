package com.projetointegrador.dto;

import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.service.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class ShippingDto {

    @NotNull
    @NotBlank
    private String shippingId;
    private String warehouseCode;
    private Long buyerId;
    private String productId;
    private String name;
    private String description;
    private String cep;

    public Shipping convert(ShippingDto shippingDto, WarehouseService warehouseService, BuyerService buyerService, ProductService productService, StatesService statesService) {
        Shipping shipping = new Shipping();
        shipping.setShippingId(shippingDto.getShippingId());

        Warehouse warehouse = warehouseService.getByCode(shippingDto.getWarehouseCode());
        shipping.setWarehouse(warehouse);

        Buyer buyer = buyerService.getByIdBuyer(shippingDto.getBuyerId());
        shipping.setBuyer(buyer);

        Product product = productService.getByIdProduct(shippingDto.getProductId());
        shipping.setProduct(product);

        States states = statesService.getStates(shippingDto.getCep());
        shipping.setStates(states);


        return shipping;
    }
}

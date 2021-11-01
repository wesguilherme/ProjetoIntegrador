package com.projetointegrador.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductSellerDto {

    @NotNull
    private Double volume;
    @NotNull
    private Double maximumTemperature;
    @NotNull
    private Double minimumTemperature;
    @NotNull
    private Long sellerId;
    @NotNull
    private String productId;
    @NotNull
    private BigDecimal price;

    public ProductSellerDto(@NotNull Double volume, @NotNull Double maximumTemperature, @NotNull Double minimumTemperature, @NotNull Long sellerId, @NotNull String productId, @NotNull BigDecimal price) {
        this.volume = volume;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.sellerId = sellerId;
        this.productId = productId;
        this.price = price;
    }
}

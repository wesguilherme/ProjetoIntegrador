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

    public ProductSellerDto(Double volume, Double maximumTemperature, Double minimumTemperature, Long sellerId, String productId) {

        this.volume = volume;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.sellerId = sellerId;
        this.productId = productId;
    }
}

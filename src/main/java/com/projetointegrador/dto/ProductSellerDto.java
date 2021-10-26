package com.projetointegrador.dto;

import lombok.Data;

@Data
public class ProductSellerDto {

    private Long productSellerId;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;
    private Long sellerId;
    private String productId;

    public ProductSellerDto(Long productSellerId, Double volume, Double maximumTemperature, Double minimumTemperature, Long sellerId, String productId) {
        this.productSellerId = productSellerId;
        this.volume = volume;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.sellerId = sellerId;
        this.productId = productId;
    }
}

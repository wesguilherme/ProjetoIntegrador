package com.projetointegrador.entity;

import lombok.Data;

@Data
public class ProductSeller {
    private String productSellerId;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;

    //@OneToMany
    private Seller seller;

    //@OneToMany
    private Product product;

    public ProductSeller(String productSellerId, Double volume, Double maximumTemperature, Double minimumTemperature, Seller seller, Product product) {
        this.productSellerId = productSellerId;
        this.volume = volume;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.seller = seller;
        this.product = product;
    }

    @Override
    public String toString() {
        return "{" +
                " \"productSellerId\": \"" + productSellerId + "\"" +
                ", \"volume\":" + volume +
                ", \"maximumTemperature\":" + maximumTemperature +
                ", \"minimumTemperature\":" + minimumTemperature +
                ", \"seller\":" + seller +
                ", \"product\":" + product +
                "}";
    }
}

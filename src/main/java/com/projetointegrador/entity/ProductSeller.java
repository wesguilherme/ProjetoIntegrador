package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSellerId;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Product product;

    public ProductSeller() {

    }

    public ProductSeller(Long productSellerId, Double volume, Double maximumTemperature, Double minimumTemperature, Seller seller, Product product) {
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

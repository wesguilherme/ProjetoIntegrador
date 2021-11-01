package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class ProductSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSellerId;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "sellerId")
    private Seller seller;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "productId")
    private Product product;

    private BigDecimal price;

    public ProductSeller() {

    }

    public ProductSeller(Long productSellerId, Double volume, Double maximumTemperature, Double minimumTemperature, Seller seller, Product product, BigDecimal price) {
        this.productSellerId = productSellerId;
        this.volume = volume;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.seller = seller;
        this.product = product;
        this.price = price;
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

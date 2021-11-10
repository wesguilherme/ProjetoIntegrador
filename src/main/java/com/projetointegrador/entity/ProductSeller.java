package com.projetointegrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSellerId;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sellerId")
    private Seller seller;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "productId")
    private Product product;

    private BigDecimal price;

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

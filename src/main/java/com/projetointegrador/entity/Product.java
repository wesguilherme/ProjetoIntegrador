package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    private String productId;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    public Product() {

    }

    public Product(String productId, String name, String description, ProductType productType) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "{" +
                " \"productId\": \"" + productId + "\"" +
                ", \"name\":\"" + name + "\"" +
                ", \"description\":\"" + description + "\"" +
                ", \"productType\":\"" + productType +
                "}";
    }
}

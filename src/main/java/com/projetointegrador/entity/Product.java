package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productCode;
    private String name;
    private String description;

    public Product() {

    }

    public Product(Long productId, String productCode, String name, String description) {
        this.productId = productId;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                " \"productId\": " + productId +
                " \"productCode\": \"" + productCode + "\"" +
                ", \"name\":\"" + name + "\"" +
                ", \"description\":\"" + description + "\"" +
                "}";
    }
}

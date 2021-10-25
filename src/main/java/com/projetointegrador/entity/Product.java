package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    private String productId;
    private String name;
    private String description;

    public Product() {

    }

    public Product(String productId, String name, String description) {
        this.productId = productId;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                " \"productId\": \"" + productId  + "\"" +
                ", \"name\":\"" + name + "\"" +
                ", \"description\":\"" + description + "\"" +
                "}";
    }
}

package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Product {

    @Id
    private String productId;

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String description;

    public Product() {

    }

    @Override
    public String toString() {
        return "{" +
                " \"productId\": \"" + productId + "\"" +
                ", \"name\":\"" + name + "\"" +
                ", \"description\":\"" + description + "\"" +
                "}";
    }
}

package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    private String productId;
    private String name;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeId")
    private Type type;

    public Product() {

    }

    public Product(String productId, String name, String description, Type type) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.type = type;
    }
}

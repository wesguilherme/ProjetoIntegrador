package com.projetointegrador.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Override
    public String toString() {
        return "{" +
                " \"productId\": \"" + productId + "\"" +
                ", \"name\":\"" + name + "\"" +
                ", \"description\":\"" + description + "\"" +
                "}";
    }
}

package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Warehouse {

    @Id
    private String warehouseCode;
    private String description;

    public Warehouse() {

    }

    public Warehouse(String warehouseCode, String description) {
        this.warehouseCode = warehouseCode;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                ", \"warehouseCode\":\"" + warehouseCode + "\"" +
                ", \"description\":\"" + description + "\"" +
                "}";
    }
}

package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Warehouse {

    @Id
    private String warehouseId;
    private String warehouseCode;
    private String description;

    public Warehouse() {

    }

    public Warehouse(String warehouseId, String warehouseCode, String description) {
        this.warehouseId = warehouseId;
        this.warehouseCode = warehouseCode;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                " \"warehouseId\": \"" + warehouseId + "\"" +
                ", \"warehouseCode\":\"" + warehouseCode + "\"" +
                ", \"description\":\"" + description + "\"" +
                "}";
    }
}

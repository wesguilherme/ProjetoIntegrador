package com.projetointegrador.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Warehouse {

    @Id
    @NotNull
    private String warehouseCode;

    @NotNull
    @NotBlank
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
                " \"warehouseCode\":\"" + warehouseCode + "\"" +
                ", \"description\":\"" + description + "\"" +
                "}";
    }
}

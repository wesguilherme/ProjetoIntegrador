package com.projetointegrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    @Id
    @NotNull
    private String warehouseCode;

    @NotNull
    @NotBlank
    private String description;

    @Override
    public String toString() {
        return "{" +
                " \"warehouseCode\":\"" + warehouseCode + "\"" +
                ", \"description\":\"" + description + "\"" +
                "}";
    }
}

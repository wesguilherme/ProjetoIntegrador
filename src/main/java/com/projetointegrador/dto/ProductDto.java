package com.projetointegrador.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    @NotNull
    @NotBlank
    private String productId;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    private Long typeId;

    public ProductDto(@NotNull @NotBlank String productId, @NotNull @NotBlank String name, @NotNull @NotBlank String description, @NotNull Long typeId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.typeId = typeId;
    }
}
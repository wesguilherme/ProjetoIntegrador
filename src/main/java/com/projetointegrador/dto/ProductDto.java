package com.projetointegrador.dto;

import com.projetointegrador.entity.Type;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    @NotNull @NotBlank
    private String productId;

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String description;

    private Long typeId;
}

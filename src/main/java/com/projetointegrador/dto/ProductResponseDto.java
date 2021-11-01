package com.projetointegrador.dto;

import com.projetointegrador.entity.Product;
import lombok.Data;

@Data
public class ProductResponseDto {

    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;
    private Product product;

    public ProductResponseDto ( ) {
    }


}


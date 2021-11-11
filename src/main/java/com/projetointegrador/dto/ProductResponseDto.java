package com.projetointegrador.dto;

import com.projetointegrador.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;
    private Product product;

}


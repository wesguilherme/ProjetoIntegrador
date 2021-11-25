package com.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingResponseDto {

    private String shippingId;
    private String warehouseCode;
    private String buyerId;
    private String productId;
    private String cep;
    private String nome;
    private Double valorFrete;
}

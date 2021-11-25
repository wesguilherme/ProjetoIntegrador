package com.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlackFridayResponseDto {

    private String productId;
    private BigDecimal discount;
    private LocalDate initialDate;
    private LocalDate finalDate;
}

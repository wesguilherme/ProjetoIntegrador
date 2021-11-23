package com.projetointegrador.dto;

import com.projetointegrador.entity.Buyer;
import com.projetointegrador.entity.Nivel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelBuyerDto {

    private Long buyerId;
    private BigDecimal totalCompras;
    private Nivel nivelAtual;
    private Nivel proximoNivel;

}

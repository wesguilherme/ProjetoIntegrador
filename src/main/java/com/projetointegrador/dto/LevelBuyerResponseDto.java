package com.projetointegrador.dto;
import com.projetointegrador.entity.Buyer;
import com.projetointegrador.entity.Nivel;
import com.projetointegrador.service.BuyerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelBuyerResponseDto {

    private String buyerName;
    private BigDecimal totalCompras;
    private Nivel nivelAtual;
    private Nivel proximoNivel;
    private String mensagem;


    public static LevelBuyerResponseDto convert(LevelBuyerDto levelBuyerDto, BuyerService buyerService) {
        Buyer buyer = buyerService.getByIdBuyer(levelBuyerDto.getBuyerId());
        BigDecimal valor = levelBuyerDto.getProximoNivel().getValorTotalDeCompra().subtract(levelBuyerDto.getTotalCompras());

        LevelBuyerResponseDto levelBuyerResponseDto = new LevelBuyerResponseDto(buyer.getName(),levelBuyerDto.getTotalCompras(),levelBuyerDto.getNivelAtual(),levelBuyerDto.getProximoNivel(),"Falta "+valor+" para atingir o próximo nível!" );

        return levelBuyerResponseDto;
    }
}


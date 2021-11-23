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

    /**
     *
     * @param levelBuyerDto é esperado um parâmetro do tipo levelBuyerDto para converter para levelBuyerResponseDto
     * @param buyerService é esperado um parâmetro do tipo buyerService
     * @return retorna um objeto do tipo levelBuyerResponseDto convertido
     * @author - Ana Carolina
     */
    public static LevelBuyerResponseDto convert(LevelBuyerDto levelBuyerDto, BuyerService buyerService) {
        Buyer buyer = buyerService.getByIdBuyer(levelBuyerDto.getBuyerId());
        BigDecimal valor = levelBuyerDto.getProximoNivel().getValorTotalDeCompra().subtract(levelBuyerDto.getTotalCompras());
        String mensagem = "";
        Nivel proximo = null;
        if(levelBuyerDto.getNivelAtual() == levelBuyerDto.getProximoNivel()){
            mensagem = "Cliente já está no nível máximo. Aguarde novas promoções!";
            proximo = null;
        }else{
            mensagem = "Falta "+valor+" para o cliente atingir o próximo nível!";
            proximo = levelBuyerDto.getProximoNivel();
        }

        LevelBuyerResponseDto levelBuyerResponseDto = new LevelBuyerResponseDto(
                buyer.getName(),
                levelBuyerDto.getTotalCompras(),
                levelBuyerDto.getNivelAtual(),
                proximo,
                mensagem
        );
        return levelBuyerResponseDto;
    }
}
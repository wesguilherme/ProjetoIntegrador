package com.projetointegrador.service;

import com.projetointegrador.dto.LevelBuyerDto;
import com.projetointegrador.entity.Nivel;
import com.projetointegrador.repository.NivelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NivelService {

    @Autowired
    private NivelPersistence nivelPersistence;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    public NivelService() {}

    public NivelService(NivelPersistence nivelPersistence) {
        this.nivelPersistence = nivelPersistence;
    }

    /**
     *
     * @param nivelPersistence é esperado o parâmetro nivelPersistence para injeção de depêndencia
     * @param purchaseOrderService é esperado o parâmetro purchaseOrderService para injeção de depêndencia
     * @author - Ana Carolina
     */
    public NivelService(NivelPersistence nivelPersistence, PurchaseOrderService purchaseOrderService) {
        this.nivelPersistence = nivelPersistence;
        this.purchaseOrderService = purchaseOrderService;
    }

    /**
     * @param nivel é esperado o objeto do tipo nivel
     * @return nivel cadastrado
     * @author - Ana Carolina
     */
    public Nivel insert(Nivel nivel) {
        return nivelPersistence.save(nivel);
    }

    /**
     * @param nivel é esperado o objeto do tipo nivel
     * @return nivel alterado
     * @author - Ana Carolina
     */
    public Nivel update(Nivel nivel, Long id) {
        Optional<Nivel> nivel1 = nivelPersistence.findById(id);
        if(nivel1.isPresent()){
            nivel1.get().setBeneficios(nivel.getBeneficios());
            nivel1.get().setPercentualDesconto(nivel.getPercentualDesconto());
            nivel1.get().setValorTotalDeCompra(nivel.getValorTotalDeCompra());
            return nivelPersistence.save(nivel1.get());
        }else{
            throw new RuntimeException("Não existe nivel cadastrado com o id passado!");
        }
    }

    /**
     * @return uma lista de niveis cadastrados
     * @author - Ana Carolina
     */
    public List<Nivel> getAllNivel() {
        return nivelPersistence.findAll();
    }

    /**
     *
     * @param totalDeCompras é esperado um parâmetro com o total de compras de um cliente
     * @return retorna o nível atual do cliente e o próximo nível que o cliente pode atingir
     * @author - Ana Carolina
     */
    private List<Nivel> getNiveis(BigDecimal totalDeCompras){
        List<Nivel> nivelList = nivelPersistence.findAll();
        List<Nivel> nivels = new ArrayList<>();
        for (int i = 0; i<(nivelList.size()-1);i++){
            if (i == 0 && totalDeCompras.compareTo(new BigDecimal(0))>=0 && totalDeCompras.compareTo(nivelList.get(i).getValorTotalDeCompra())==-1)   {
                nivels.add(nivelList.get(i));
                nivels.add(nivelList.get(i+1));
                return nivels;
            }else if(totalDeCompras.compareTo(nivelList.get(i).getValorTotalDeCompra())>=0 && totalDeCompras.compareTo(nivelList.get(i+1).getValorTotalDeCompra())==-1){
                nivels.add(nivelList.get(i));
                nivels.add(nivelList.get(i+1));
                return nivels;
            }else if(totalDeCompras.compareTo(nivelList.get(nivelList.size()-1).getValorTotalDeCompra())==1){
                nivels.add(nivelList.get(nivelList.size()-1));
                nivels.add(nivelList.get(nivelList.size()-1));
                return nivels;
            }
        }
        return null;
    }

    /**
     *
     * @param buyerId espera o id de um cliente
     * @return o nível atual do cliente, o próximo que ele pode atingir e o total de suas compras
     * @author - Ana Carolina
     */
    public LevelBuyerDto customerLevelHistory(Long buyerId){
        BigDecimal totalDeCompras = purchaseOrderService.getTotalPricePurchaseByBuyer(buyerId);

        List<Nivel> nivels = this.getNiveis(totalDeCompras);

        LevelBuyerDto levelBuyerDto = new LevelBuyerDto();
        levelBuyerDto.setBuyerId(buyerId);
        levelBuyerDto.setTotalCompras(totalDeCompras);
        levelBuyerDto.setNivelAtual(nivels.get(0));
        levelBuyerDto.setProximoNivel(nivels.get(1));

        return levelBuyerDto;
    }
}

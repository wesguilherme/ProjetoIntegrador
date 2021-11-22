package com.projetointegrador.service;

import com.projetointegrador.dto.LevelBuyerDto;
import com.projetointegrador.entity.Nivel;
import com.projetointegrador.repository.NivelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class NivelService {

    @Autowired
    private NivelPersistence nivelPersistence;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    public NivelService() {
    }

    public NivelService(NivelPersistence nivelPersistence) {
        this.nivelPersistence = nivelPersistence;
    }

    public NivelService(NivelPersistence nivelPersistence, PurchaseOrderService purchaseOrderService) {
        this.nivelPersistence = nivelPersistence;
        this.purchaseOrderService = purchaseOrderService;
    }

    /**
     * @param nivel é esperado o objeto do tipo nivel
     * @return nivel cadastrado
     */
    public Nivel insert(Nivel nivel) {
        return nivelPersistence.save(nivel);
    }

    /**
     * @return uma lista de niveis cadastrados
     */
    public List<Nivel> getAllNivel() {
        return nivelPersistence.findAll();
    }

    private Nivel getNivelAtual(BigDecimal totalDeCompras){
        List<Nivel> nivelList = nivelPersistence.findAll();
        BigDecimal valorNivel = new BigDecimal(0);
        Nivel nivel = new Nivel();
        for (int i = 0; i<nivelList.size();i++){
            if(totalDeCompras.compareTo(nivelList.get(i).getValorTotalDeCompra())==-1 || totalDeCompras.compareTo(nivelList.get(i).getValorTotalDeCompra())==0){
                nivel = nivelList.get(i);
                return nivel;
            }
        }
        return nivel;
    }

    private Nivel getNivelProximo(BigDecimal totalDeCompras){
        List<Nivel> nivelList = nivelPersistence.findAll();
        BigDecimal valorNivel = new BigDecimal(0);
        Nivel nivel = new Nivel();
        for (int i = 0; i<nivelList.size();i++){
            if(totalDeCompras.compareTo(nivelList.get(i).getValorTotalDeCompra())==-1 || totalDeCompras.compareTo(nivelList.get(i).getValorTotalDeCompra())==0){
                if(i == nivelList.size()){
                    nivel = nivelList.get(nivelList.size());
                    return nivel;
                }else{
                    nivel = nivelList.get(i+1);
                    return nivel;
                }
            }
        }
        return nivel;
    }

    public LevelBuyerDto customerLevelHistory(Long buyerId){
        BigDecimal totalDeCompras = purchaseOrderService.getTotalPricePurchaseByBuyer(buyerId);

        LevelBuyerDto levelBuyerDto = new LevelBuyerDto();
        levelBuyerDto.setBuyerId(buyerId);
        levelBuyerDto.setTotalCompras(totalDeCompras);
        levelBuyerDto.setNivelAtual(this.getNivelAtual(totalDeCompras));
        levelBuyerDto.setProximoNivel(this.getNivelProximo(totalDeCompras));

        return levelBuyerDto;
    }
}

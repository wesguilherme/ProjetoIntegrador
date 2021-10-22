package com.projetointegrador.service;

import com.projetointegrador.dao.SellerPersistence;
import com.projetointegrador.entity.Seller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SellerService {

    private SellerPersistence sellerPersistence;


    public SellerService(SellerPersistence sellerPersistence) {
        this.sellerPersistence = sellerPersistence;
    }

    /**
     *
     * @return
     * @author Grupo 5 - Tester Wesley
     */
    private Long codigoUnico() {
        return ThreadLocalRandom.current().nextLong(100, 9999);
    }

    /**
     *
     * @param seller
     * @author Grupo 5 - Tester Wesley
     */
    public void salva(Seller seller) {
        if(seller.getSellerId()==null || seller.getSellerId().isEmpty()) {
            seller.getSellerId();
            sellerPersistence.cadastrar(seller);
        }else{
            Seller sellerExists = sellerPersistence.get(String.valueOf(seller.getSellerId()));
            if(sellerExists == null){
                throw new RuntimeException("Algo de muito errado aconteceu");
            }
            sellerExists.setCpf(seller.getCpf());
            sellerExists.setName(seller.getName());
            sellerExists.setAddresses(seller.getAddresses());
            sellerPersistence.atualiza(sellerExists);
        }
    }

//    /**
//     *
//     * @return uma lista de sellers ordenada (em ordem crescente) pelo nome
//     */
//    public List<Seller> listagem(){
//        List<Seller> lista = sellerPersistence.lista();
//        lista.sort((Seller v1, Seller v2) -> v1.getName().compareToIgnoreCase(v2.getName()));
//        return lista;
//    }

    /**
     *
     * @param codigo
     * @return
     * @author Grupo 5 - Tester Wesley
     */
    public Seller obtem(String codigo) {
        return sellerPersistence.get(codigo);
    }

    /**
     *
     * @param codigo
     * @author Grupo 5 - Tester Wesley
     */
    public void deleta(String codigo) {
        sellerPersistence.exclui(codigo);
    }
}

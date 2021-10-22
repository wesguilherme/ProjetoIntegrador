package com.projetointegrador.service;

import com.projetointegrador.repository.SellerPersistence;
import com.projetointegrador.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SellerService {

    @Autowired
    private SellerPersistence sellerPersistence;

    public SellerService(){
    }

    public SellerService(SellerPersistence sellerPersistence) {
        this.sellerPersistence = sellerPersistence;
    }

    /**
     * @param cpf - é esperado o parametro cpf do vendedor
     * @return - retorna a verificaçao de duplicidade do código
     * @author Grupo 5 - Tester Wesley
     */
    private boolean codigoNaoUtilizado(String cpf) {
        Seller sellerExistente = sellerPersistence.findByCpf(cpf);
        if (sellerExistente == null) {
            return false;
        }
        return true;
    }

    public Seller cadastrar(Seller seller) throws IOException {
        if (codigoNaoUtilizado(seller.getCpf())) {
            return sellerPersistence.save(seller);
        } else {
            throw new RuntimeException("Cpf já utilizado");
        }
    }
}

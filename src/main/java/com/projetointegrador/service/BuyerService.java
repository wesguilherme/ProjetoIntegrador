package com.projetointegrador.service;

import com.projetointegrador.entity.Buyer;
import com.projetointegrador.repository.BuyerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private BuyerPersistence buyerPersistence;

    public BuyerService ( ) {
    }

    public BuyerService (BuyerPersistence buyerPersistence) {
        this.buyerPersistence = buyerPersistence;
    }

    private boolean utilizedCode(String cpf){
        Buyer existentBuyer = buyerPersistence.findByCpf(cpf);
        if (existentBuyer != null){
            return true;
        }
          return false;
    }

    public Buyer insert(Buyer buyer){
        if (!utilizedCode(buyer.getCpf())){
            return buyerPersistence.save(buyer);
        }else {
            throw new RuntimeException("Cpf já utilizado");
        }
    }
}

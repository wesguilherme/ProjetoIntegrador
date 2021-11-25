package com.projetointegrador.service;

import com.projetointegrador.entity.Buyer;
import com.projetointegrador.repository.BuyerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerPersistence buyerPersistence;

    public BuyerService() {}

    /**
     * @param buyerPersistence é esperado parâmetro do tipo buyerPersistence para injeção de dependência
     */
    public BuyerService (BuyerPersistence buyerPersistence) {
        this.buyerPersistence = buyerPersistence;
    }

    /**
     * @param cpf é esperado o parâmetro cpf do buyer
     * @return true caso o codigo seja utilizado ou false caso não seja
     */
    private boolean utilizedCode(String cpf) {
        Buyer existentBuyer = buyerPersistence.findByCpf(cpf);
        if (existentBuyer != null) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param buyer é esperado o objeto do tipo buyer
     * @return buyer cadastrado
     * @throws RuntimeException caso cpf ja esteja sendo utilizado
     */
    public Buyer insert(Buyer buyer) {
        if (!utilizedCode(buyer.getCpf())) {
            return buyerPersistence.save(buyer);
        } else {
            throw new RuntimeException("Cpf já utilizado");
        }
    }

    /**
     * @param id é esperado o parâmetro id do buyer
     * @return buyer caso ele esteja presente
     * @throws RuntimeException caso não exista um buyer cadastrado
     */
    public Buyer getByIdBuyer(Long id) {
        Optional<Buyer> val;

        val = buyerPersistence.findById(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            return null;
        }
    }
}

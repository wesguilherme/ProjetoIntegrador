package com.projetointegrador.service;

import com.projetointegrador.entity.Seller;
import com.projetointegrador.repository.SellerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerPersistence sellerPersistence;

    public SellerService() {
    }

    /**
     * @param sellerPersistence - é esperado um parâmetro do tipo sellerPersistence para injeção de dependência
     * @author - Grupo 5 - Tester Wesley
     */
    public SellerService(SellerPersistence sellerPersistence) {
        this.sellerPersistence = sellerPersistence;
    }

    /**
     * @param cpf - é esperado o parametro cpf do seller
     * @return - retorna a verificaçao de duplicidade do código
     * @author - Grupo 5 - Tester Wesley
     */
    private boolean utilizedCode(String cpf) {
        Seller existentSeller = sellerPersistence.findByCpf(cpf);
        if (existentSeller != null) {
            return true;
        }
        return false;
    }

    /**
     * @param seller - é esperado um objeto do tipo seller
     * @return - retorna seller cadastrado na lista
     * @author - Grupo 5 - Tester Wesley
     */
    public Seller insert(Seller seller) {
        if (!utilizedCode(seller.getCpf())) {
            return sellerPersistence.save(seller);
        } else {
            throw new RuntimeException("Cpf já utilizado");
        }
    }

    /**
     * @param sellerId - é esperado o parametro Id do seller
     * @return - retorna se o seller existe ou não através do Id
     * @author - Grupo 5 - Tester Wesley
     */
    public Seller getByIdSeller(Long sellerId) {
        Optional<Seller> val;

        val = sellerPersistence.findById(sellerId);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe resultado para essa busca!");
        }
    }
}

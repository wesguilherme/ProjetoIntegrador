package com.projetointegrador.repository;

import com.projetointegrador.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerPersistence extends JpaRepository<Seller, Long> {

    Seller findByCpf(String cpf);

//    Seller findBySellerId(Long sellerId);

}

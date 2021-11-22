package com.projetointegrador.repository;

import com.projetointegrador.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerPersistence extends JpaRepository<Buyer, Long> {

    Buyer findByCpf(String cpf);
}
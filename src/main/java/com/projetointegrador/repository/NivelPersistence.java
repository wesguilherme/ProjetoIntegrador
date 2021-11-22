package com.projetointegrador.repository;

import com.projetointegrador.entity.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface NivelPersistence extends JpaRepository<Nivel, Long> {

    Nivel findByValorTotalDeCompra(BigDecimal valorTotalDeCompra);
}

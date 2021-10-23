package com.projetointegrador.repository;

import com.projetointegrador.entity.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchStockPeristence extends JpaRepository<BatchStock, Long> {

}

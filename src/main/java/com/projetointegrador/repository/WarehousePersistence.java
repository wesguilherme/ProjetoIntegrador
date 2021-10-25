package com.projetointegrador.repository;

import com.projetointegrador.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehousePersistence extends JpaRepository<Warehouse, String> {

    Optional<Warehouse> findByWarehouseCode(String code);

}

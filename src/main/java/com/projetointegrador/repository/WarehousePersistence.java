package com.projetointegrador.repository;

import com.projetointegrador.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehousePersistence extends JpaRepository<Warehouse, String> {

    Warehouse findByWarehouseCode(String code);

}

package com.projetointegrador.service;

import com.projetointegrador.entity.Representative;
import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.repository.WarehousePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    private WarehousePersistence warehousePersistence;

    public WarehouseService() {
    }

    public WarehouseService(WarehousePersistence warehousePersistence) {
        this.warehousePersistence = warehousePersistence;
    }

    private boolean utilizedCode(String codigo) {
        Optional<Warehouse> existentWarehouse = warehousePersistence.findByWarehouseCode(codigo);
        if(existentWarehouse.isPresent()){
            return true;
        }
        return false;
    }

    public Warehouse insert(Warehouse warehouse) {
        if(!utilizedCode(warehouse.getWarehouseCode())) {
            return warehousePersistence.save(warehouse);
        }
        throw new RuntimeException("Código já utilizado");
    }

    public boolean validWarehouse(String code) {
        Optional<Warehouse> validWarehouse = warehousePersistence.findByWarehouseCode(code);
        if(validWarehouse.isPresent()){
            return true;
        }
        return false;
    }

    public Warehouse getByCode(String code){

        Optional<Warehouse> val;

        val = warehousePersistence.findByWarehouseCode(code);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe resultado para essa busca!");
        }
    }
}

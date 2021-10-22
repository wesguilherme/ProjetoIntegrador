package com.projetointegrador.service;

import com.projetointegrador.repository.WarehousePersistence;
import com.projetointegrador.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class WarehouseService {

    @Autowired
    private WarehousePersistence warehousePersistence;

    public WarehouseService() {
    }

    public WarehouseService(WarehousePersistence warehousePersistence) {
        this.warehousePersistence = warehousePersistence;
    }

    private boolean codigoNaoUtilizado(String codigo) {
        Warehouse warehouseExistente = warehousePersistence.findByWarehouseCode(codigo);
        if(warehouseExistente == null){
            return true;
        }
        return false;
    }

    public Warehouse cadastrar(Warehouse warehouse) {
        if(codigoNaoUtilizado(warehouse.getWarehouseCode())) {
            return warehousePersistence.save(warehouse);
        }else{
            throw new RuntimeException("Código já utilizado");
        }
    }
}

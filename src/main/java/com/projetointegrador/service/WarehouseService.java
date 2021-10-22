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

    private String codigoUnico() {
        return "MLB" + ThreadLocalRandom.current().nextInt(100, 999);
    }

    private boolean codigoNaoUtilizado(String codigo) {
        Warehouse warehouseExistente = warehousePersistence.findByWarehouseCode(codigo);
        if(warehouseExistente == null){
            return false;
        }
        return true;
    }

    public Warehouse cadastrar(Warehouse warehouse) {
        if(codigoNaoUtilizado(warehouse.getWarehouseCode())) {
            warehouse.setWarehouseCode(codigoUnico());
            return warehousePersistence.save(warehouse);
        }else{
            throw new RuntimeException("Código já utilizado");
        }
    }
}

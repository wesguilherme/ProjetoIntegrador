package com.projetointegrador.service;

import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.repository.WarehousePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    private WarehousePersistence warehousePersistence;

    public WarehouseService() {}

    /**
     * @param warehousePersistence - é esperado um parâmetro do tipo warehousePersistence para injeção de dependência
     * @author - Grupo 5 - Tester Ana
     */
    public WarehouseService(WarehousePersistence warehousePersistence) {
        this.warehousePersistence = warehousePersistence;
    }

    /**
     * @param code - é esperado o parametro code do warehouse
     * @return - retorna a verificaçao de duplicidade do código
     * @author - Grupo 5 - Tester Ana
     */
    private boolean utilizedCode(String code) {
        Optional<Warehouse> existentWarehouse = warehousePersistence.findByWarehouseCode(code);
        if (existentWarehouse.isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * @param warehouse - é esperado um objeto do tipo warehouse
     * @return - retorna warehouse cadastrado na lista
     * @author - Grupo 5 - Tester Ana
     */
    public Warehouse insert(Warehouse warehouse) {
        if (!utilizedCode(warehouse.getWarehouseCode())) {
            return warehousePersistence.save(warehouse);
        }
        throw new RuntimeException("Código já utilizado");
    }

    /**
     * @param code - é esperado o parametro code de warehouse
     * @return - retorna a verificação tru ou false sobre a validade de warehouse
     * @author - Grupo 5 - Tester Ana
     */
    public boolean validWarehouse(String code) {
        Optional<Warehouse> validWarehouse = warehousePersistence.findByWarehouseCode(code);
        if (validWarehouse.isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * @param code - é esperado o parametro code de warehouse
     * @return - retorna se o warehouse existe ou não através do code
     * @author - Grupo 5 - Tester Ana
     */
    public Warehouse getByCode(String code) {

        Optional<Warehouse> val;

        val = warehousePersistence.findByWarehouseCode(code);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe Warehouse com esse código!");
        }
    }
}

package com.projetointegrador.service;

import com.projetointegrador.entity.*;
import com.projetointegrador.repository.WarehousePersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WarehouseServiceTest {

    @Test
    void shouldInsertWarehouse ( ) {
        WarehousePersistence mock1 = mock(WarehousePersistence.class);

        Warehouse warehouse = new Warehouse("MLB-207", "Deposito de refrigerados");
        when(mock1.save(any(Warehouse.class))).thenReturn(warehouse);

        WarehouseService warehouseService = new WarehouseService(mock1);
        Warehouse warehouseCadastrado = warehouseService.insert(warehouse);
        assertNotNull(warehouseCadastrado.getWarehouseCode());
    }

    @Test
    void shouldNotInsertWarehouse ( ) {
        WarehousePersistence mock1 = mock(WarehousePersistence.class);
        String warehouse = "MLB-207";
        Warehouse warehouse1 = new Warehouse("MLB-207", "Código já utilizado");
        when(mock1.findByWarehouseCode(warehouse)).thenReturn(Optional.of(warehouse1));


        WarehouseService warehouseService = new WarehouseService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            warehouseService.insert(warehouse1);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void mustGetWarehouseByCode ( ) {
        WarehousePersistence mock = mock(WarehousePersistence.class);

        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        when(mock.findByWarehouseCode("MLB-410")).thenReturn(Optional.of(warehouse));

        WarehouseService warehouseService = new WarehouseService(mock);
        Warehouse warehouse1 = warehouseService.getByCode("MLB-410");
        assertNotNull(warehouse1.getWarehouseCode());
    }

    @Test
    void mustnotGetWarehouseByCode ( ) throws RuntimeException {
        WarehousePersistence mock = mock(WarehousePersistence.class);

        when(mock.findByWarehouseCode("MLB-410")).thenReturn(Optional.empty());

        WarehouseService warehouseService = new WarehouseService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            warehouseService.getByCode("MLB-410");
        });

        String expectedMessage = "Não existe Warehouse com esse código!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
}
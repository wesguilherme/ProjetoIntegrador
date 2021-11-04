package com.projetointegrador.service;

import com.projetointegrador.entity.Address;
import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.repository.WarehousePersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WarehouseServiceTest {

    private Object Warehouse;

    @Test
    void shouldInsertWarehouse ( ) {
        WarehousePersistence mock1 = mock(WarehousePersistence.class);
        WarehouseService mock = mock(WarehouseService.class);
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Warehouse warehouse = new Warehouse();

        when(mock.insert(any(Warehouse.class))).thenReturn(warehouse);

        WarehouseService warehouseService = new WarehouseService(mock1);
        warehouseService.insert(warehouse);
        assertNotNull(warehouse.getWarehouseCode());
    }

    @Test
    void shouldNotInsertWarehouse() {
        WarehousePersistence mock1 = mock(WarehousePersistence.class);
        String warehouse = "MLB-207";
        Address address = new Address("rua goias", "44","99999-000", "sp", "sp", "cs");
        Warehouse warehouse1 = new Warehouse("MLB-207","Código já utilizado");
        when(mock1.findByWarehouseCode(warehouse));


        WarehouseService warehouseService = new WarehouseService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
           warehouseService.insert(warehouse1);

        });

        String expectedMessage = "Não existe Warehouse com esse código!";
        String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
    }
}


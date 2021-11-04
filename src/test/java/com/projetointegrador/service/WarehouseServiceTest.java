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
        //assertNotNull(warehouseService.getWarehouseCode());
    }

    @Test
    void shouldNotInsertWarehouse ( ) {
        WarehousePersistence mock1 = mock(WarehousePersistence.class);
        String warehouse = "MLB-207";
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Warehouse warehouse1 = new Warehouse("MLB-207", "Código já utilizado");
        when(mock1.findByWarehouseCode(warehouse));


        WarehouseService warehouseService = new WarehouseService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            warehouseService.insert(warehouse1);

        });

        String expectedMessage = "Não existe Warehouse com esse código!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void mustGetWarehouseByCode ( ) {
        WarehousePersistence mock = mock(WarehousePersistence.class);

        Warehouse warehouse = new Warehouse("1L", "Não existe Warehouse com esse código!");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse2 = new Warehouse("MLB-410", "Teste de cadastro");

        Optional<Warehouse> section = Optional.of(new Warehouse("SEC-123", "Não existe Warehouse com esse código!"));

        when(mock.findByWarehouseCode("SEC-123")).thenReturn(section);

        WarehouseService warehouseService = new WarehouseService(mock);
        Warehouse warehouse1 = warehouseService.getByCode("SEC-123");
        assertNotNull(warehouse1.getWarehouseCode());

    }


    @Test
    void mustnotGetWarehouseByCode ( ) throws RuntimeException {
        WarehousePersistence mock = mock(WarehousePersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Representative representative = new Representative(1L, "111.222.333-44", "Rafael", address);
        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        Optional<Section> section = Optional.of(new Section("SEC-123", 200.0, 30.0, type, representative, warehouse));

        when(mock.findByWarehouseCode("SEC-123")).thenReturn(Optional.of(warehouse));

        SectionService sectionService = new SectionService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            sectionService.getSectionByCode(String.valueOf(section));
        });

        String expectedMessage = "Não existe um setor com esse código!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
}
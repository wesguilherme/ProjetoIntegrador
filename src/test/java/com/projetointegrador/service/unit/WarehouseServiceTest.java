package com.projetointegrador.service.unit;

import com.projetointegrador.dto.WarehouseResponseDto;
import com.projetointegrador.dto.WarehousesDto;
import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.repository.WarehousePersistence;
import com.projetointegrador.service.WarehouseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WarehouseServiceTest {

    @Test
    void shouldInsertWarehouse() {
        WarehousePersistence mock1 = mock(WarehousePersistence.class);

        Warehouse warehouse = new Warehouse("MLB-207", "Deposito de refrigerados");
        when(mock1.save(any(Warehouse.class))).thenReturn(warehouse);

        WarehouseService warehouseService = new WarehouseService(mock1);
        Warehouse warehouseCadastrado = warehouseService.insert(warehouse);
        assertNotNull(warehouseCadastrado.getWarehouseCode());
    }

    @Test
    void shouldNotInsertWarehouse() {
        WarehousePersistence mock1 = mock(WarehousePersistence.class);
        String warehouse = "MLB-207";
        Warehouse warehouse1 = new Warehouse("MLB-207", "Código já utilizado");
        when(mock1.findByWarehouseCode(warehouse)).thenReturn(Optional.of(warehouse1));


        WarehouseService warehouseService = new WarehouseService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            warehouseService.insert(warehouse1);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldValidWarehouse() {
        WarehousePersistence mock = mock(WarehousePersistence.class);

        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        when(mock.findByWarehouseCode("MLB-410")).thenReturn(Optional.of(warehouse));
        WarehouseService warehouseService = new WarehouseService(mock);
        Boolean verify = warehouseService.validWarehouse("MLB-410");
        assertTrue(verify);
    }

    @Test
    void shouldNotValidWarehouse() {
        WarehousePersistence mock = mock(WarehousePersistence.class);

        when(mock.findByWarehouseCode("MLB-410")).thenReturn(Optional.empty());
        WarehouseService warehouseService = new WarehouseService(mock);
        Boolean verify = warehouseService.validWarehouse("MLB-410");
        assertFalse(verify);
    }

    @Test
    void shouldGetWarehouseByCode() {
        WarehousePersistence mock = mock(WarehousePersistence.class);

        Warehouse warehouse = new Warehouse("MLB-410", "Teste de cadastro");

        when(mock.findByWarehouseCode("MLB-410")).thenReturn(Optional.of(warehouse));

        WarehouseService warehouseService = new WarehouseService(mock);
        Warehouse warehouse1 = warehouseService.getByCode("MLB-410");
        assertNotNull(warehouse1.getWarehouseCode());
    }

    @Test
    void shouldNotGetWarehouseByCode() throws RuntimeException {
        WarehousePersistence mock = mock(WarehousePersistence.class);

        when(mock.findByWarehouseCode("MLB-410")).thenReturn(Optional.empty());

        WarehouseService warehouseService = new WarehouseService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            warehouseService.getByCode("MLB-410");
        });

        String expectedMessage = "Não existe Warehouse com esse código!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldWarehouseListByProduct() {
        WarehousePersistence warehousePersistenceMock = mock(WarehousePersistence.class);

        List<WarehousePersistence.listWarehouseByProduct> listWarehouseByProduct = new ArrayList<>();
        WarehousePersistence.listWarehouseByProduct listWarehouseByProduct1 = new WarehousePersistence.listWarehouseByProduct() {
            @Override
            public String getWarehouse_code() {
                return "MLB-410";
            }

            @Override
            public String getProduct_id() {
                return "MLB-410";
            }

            @Override
            public Integer getTotalQuantidade() {
                return 10;
            }
        };
        listWarehouseByProduct.add(listWarehouseByProduct1);

        List<WarehousesDto> warehouseDtos = new ArrayList<>();
        WarehousesDto warehouseDto = new WarehousesDto("MLB-411",10);
        warehouseDtos.add(warehouseDto);

        List<WarehouseResponseDto> warehouseResponseDtos = new ArrayList<>();
        WarehouseResponseDto warehouseResponseDto = new WarehouseResponseDto("MLB-410",warehouseDtos);
        warehouseResponseDtos.add(warehouseResponseDto);

        when(warehousePersistenceMock.listWarehouseByProduct(anyString())).thenReturn(listWarehouseByProduct);

        WarehouseService warehouseService1 = new WarehouseService(warehousePersistenceMock);
        WarehouseResponseDto warehouseResponseDto1 = warehouseService1.warehouseListByProduct("MLB410");
        assertNotNull(warehouseResponseDto1);
    }
}

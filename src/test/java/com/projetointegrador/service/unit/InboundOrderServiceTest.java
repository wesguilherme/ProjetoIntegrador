package com.projetointegrador.service.unit;

import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.repository.InboundOrderPersistence;
import com.projetointegrador.repository.ProductPersistence;
import com.projetointegrador.repository.TypePersistence;
import com.projetointegrador.service.BatchStockService;
import com.projetointegrador.service.InboundOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InboundOrderServiceTest {

    @Test
    void shouldInsertInboundOrderService() throws NullPointerException{
        InboundOrderPersistence inboundOrderPersistenceMock = mock(InboundOrderPersistence.class);

        Type type = Type.builder().typeId(1L).initials("RF").environmentType("REFRIGERADOS").build();
        Warehouse warehouse = Warehouse.builder().warehouseCode("MLB-410").description("Teste de cadastro").build();
        Address address = Address.builder().street("rua goias").number("44").postalCode("99999-000").city("SP").state("SP").complement("casa").build();
        Representative representative = Representative.builder().representativeId(1L).cpf("111.222.333-44").name("Rafael").address(address).build();
        Section section = Section.builder().sectionCode("SEC-123").totalCapacity(200.0).usedSpace(30.0).type(type).representative(representative).warehouse(warehouse).build();
        InboundOrder inboundOrder = InboundOrder.builder().inboundOrderId(1L).orderNumber(45).orderDate(LocalDate.now()).section(section).build();

        when(inboundOrderPersistenceMock.save(any(InboundOrder.class))).thenReturn(inboundOrder);

        InboundOrderService inboundOrderService = new InboundOrderService(inboundOrderPersistenceMock);
        InboundOrder inboundOrder1 = inboundOrderService.insert(inboundOrder);
        assertNotNull (inboundOrder1.getInboundOrderId());
    }

    @Test
    void shouldProductList(){
        TypePersistence typePersistenceMock = mock(TypePersistence.class);
        ProductPersistence productPersistenceMock = mock(ProductPersistence.class);
        Type type = Type.builder().typeId(1L).initials("RF").environmentType("REFRIGERADOS").build();

        List<Product> productList = new ArrayList<>();
        Product product = new Product("MLB-125", "Uva", "Caixa de Uva", type);
        productList.add(product);

        when(typePersistenceMock.findByInitials(anyString())).thenReturn(type);
        when(productPersistenceMock.findByType(any(Type.class))).thenReturn(productList);

        InboundOrderService inboundOrderService = new InboundOrderService(typePersistenceMock, productPersistenceMock);
        List<Product> products = inboundOrderService.productList("RF");
        assertEquals("RF", products.get(0).getType().getInitials());
    }

    @Test
    void shouldUpdateInboundOrderService() throws NullPointerException{
        InboundOrderPersistence inboundOrderPersistenceMock = mock(InboundOrderPersistence.class);
        BatchStockPersistence batchStockPersistenceMock = mock(BatchStockPersistence.class);
        BatchStockService batchStockServiceMock = mock(BatchStockService.class);

        List<BatchStock> batchStockList = new ArrayList<>();
        InboundOrder inboundOrder1 = InboundOrder.builder().inboundOrderId(1L).orderNumber(45).orderDate(LocalDate.now()).build();
        BatchStock batchStock = BatchStock.builder().batchStockId(1L).batchStockNumber(1L).inboundOrder(inboundOrder1).dueDate(LocalDate.now()).currentQuantity(10).build();
        batchStockList.add(batchStock);

        Type type = Type.builder().typeId(1L).initials("RF").environmentType("REFRIGERADOS").build();
        Warehouse warehouse = Warehouse.builder().warehouseCode("MLB-410").description("Teste de cadastro").build();
        Address address = Address.builder().street("rua goias").number("44").postalCode("99999-000").city("SP").state("SP").complement("casa").build();
        Representative representative = Representative.builder().representativeId(1L).cpf("111.222.333-44").name("Rafael").address(address).build();
        Section section = Section.builder().sectionCode("SEC-123").totalCapacity(200.0).usedSpace(30.0).type(type).representative(representative).warehouse(warehouse).build();
        InboundOrder inboundOrder = InboundOrder.builder().inboundOrderId(1L).orderNumber(45).orderDate(LocalDate.now()).section(section).batchStock(batchStockList).build();

        when(batchStockServiceMock.getBatchStockNumber(anyLong())).thenReturn(Optional.ofNullable(batchStock));

        when(inboundOrderPersistenceMock.findById(anyLong())).thenReturn(Optional.ofNullable(inboundOrder));
        when(inboundOrderPersistenceMock.save(any(InboundOrder.class))).thenReturn(inboundOrder);

        InboundOrderService inboundOrderService = new InboundOrderService(inboundOrderPersistenceMock, batchStockServiceMock);
        InboundOrder inboundOrder2 = inboundOrderService.update(inboundOrder,1L);
        assertNotNull (inboundOrder2.getInboundOrderId());
    }

    @Test
    void shouldNotUpdateInboundOrderService() throws NullPointerException{
        InboundOrderPersistence inboundOrderPersistenceMock = mock(InboundOrderPersistence.class);

        when(inboundOrderPersistenceMock.findById(anyLong())).thenReturn(Optional.empty());
        InboundOrder inboundOrder = InboundOrder.builder().inboundOrderId(1L).orderNumber(45).orderDate(LocalDate.now()).build();

        InboundOrderService inboundOrderService = new InboundOrderService(inboundOrderPersistenceMock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            inboundOrderService.update(inboundOrder, 1L);
        });

        assertEquals("Não existe inboundOrder com esse id!", exception.getMessage());
    }

}
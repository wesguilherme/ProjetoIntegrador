package com.projetointegrador.service;

import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.repository.ProductSellerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.method.P;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatchStockServiceTest {



    @Test
    void shouldgetBatchStockByProductSeller() {
        BatchStockPersistence mock1 = mock(BatchStockPersistence.class);
        BatchStockService mock = mock(BatchStockService.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L, "111.222.333-44", "Rafael", address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);

        ProductSeller productSeller = new ProductSeller(1L, 10.0, 5.0, 1.0, seller, product, new BigDecimal("20"));

        BatchStock batchStock = new BatchStock(1L, LocalDate.now(),LocalDateTime.now(), LocalDate.now(), 50, 100, 34F, "16", 5L, productSeller);

        when(mock1.findByProductSeller(productSeller)).thenReturn((List<BatchStock>) batchStock);

        BatchStockService batchStockService = new BatchStockService(mock1);
        BatchStock batchStock1 = batchStockService.getBatchStockByProductSeller(productSeller);
        assertNotNull(batchStock1.getBatchStockId());
    }
}

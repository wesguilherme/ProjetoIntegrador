package com.projetointegrador.service;

import com.projetointegrador.entity.*;
import com.projetointegrador.repository.BatchStockPersistence;
import com.projetointegrador.repository.ProductSellerPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatchStockServiceTest {



    @Test
    void shouldgetBatchStockByProductSeller() {
        BatchStockPersistence mock1 = mock(BatchStockPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L, "111.222.333-44", "Rafael", address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);

        ProductSeller productSeller = new ProductSeller(1L, 10.0, 5.0, 1.0, seller, product, new BigDecimal("20"));

        List<BatchStock> batchStockList = new ArrayList<>();
        BatchStock batchStock = new BatchStock(1L, LocalDate.now(),LocalDateTime.now(), LocalDate.now(), 50, 100, 34F, "16", 5L, productSeller);
        batchStockList.add(batchStock);
        when(mock1.findByProductSeller(any(ProductSeller.class))).thenReturn(batchStockList);

        BatchStockService batchStockService = new BatchStockService(mock1);
        List<BatchStock> batchStock1 = batchStockService.getBatchStockByProductSeller(productSeller);
        assertNotNull(batchStock1.get(0).getBatchStockId());
    }

    @Test
    void shouldNotgetBatchStockByProductSeller() {
        BatchStockPersistence mock = mock(BatchStockPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);


        BatchStockService batchStockService = new BatchStockService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            batchStockService.getBatchStockByProductSeller(ProductSeller.builder().build());
        });

        assertEquals("Não existe batchStock para esse produto.", exception.getMessage());
    }

//    @Test
//    void shouldverifyProductInBatchStock() {
//        BatchStockPersistence mock = mock(BatchStockPersistence.class);
//
//        Type type = new Type(1L, "RF", "REFRIGERADOS");
//        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
//        Seller seller = new Seller(1L, "111.222.333-44", "Rafael", address);
//        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);
//
//        ProductSeller productSeller = new ProductSeller(1L, 10.0, 5.0, 1.0, seller, product, new BigDecimal("20"));
//
//        when(mock.findById(1L)).thenReturn(Optional.of(productSeller));
//
//        ProductSellerService productSellerService = new ProductSellerService(mock);
//        ProductSeller productSeller1 = productSellerService.getProductSeller(1L);
//        assertNotNull(productSeller1.getProductSellerId());
//
//        List<BatchStock> batchStockList = new ArrayList<>();
//        BatchStock batchStock = new BatchStock(1L, LocalDate.now(),LocalDateTime.now(), LocalDate.now(), 50, 100, 34F, "16", 5L, productSeller);
//        batchStockList.add(batchStock);
//        when(mock.findByProductSeller(any(ProductSeller.class))).thenReturn(batchStockList);
//
//        BatchStockService batchStockService = new BatchStockService(mock);
//        List<BatchStock> batchStock1 = batchStockService.getBatchStockByProductSeller(productSeller);
//        assertNotNull(batchStock1.get(0).getBatchStockId());
    }



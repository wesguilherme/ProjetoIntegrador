package com.projetointegrador.service;

import com.projetointegrador.dto.ProductSellerDto;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.ProductSellerPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductSellerServiceTest {

    @Test
    void shouldInsertProductSeller() {
        ProductSellerPersistence productSellerPersistencemock = mock(ProductSellerPersistence.class);
        ProductSellerService productSellerServicemock = mock(ProductSellerService.class);
        ProductService productServicemock = mock(ProductService.class);
        SellerService sellerServicemock = mock(SellerService.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L, "111.222.333-44", "Rafael", address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);

        ProductSeller productSeller = new ProductSeller(1L, 10.0, 5.0, 1.0, seller, product, new BigDecimal("20"));
        ProductSellerDto productSellerDto = new ProductSellerDto(10.0, 5.0, 1.0, 1L, "MLB-123", new BigDecimal("20"));


        when(sellerServicemock.getByIdSeller(1L)).thenReturn(seller);
        when(productServicemock.getByIdProduct("MLB-123")).thenReturn(product);
        when(productSellerServicemock.insert(any(ProductSellerDto.class))).thenReturn(productSeller);

        ProductSellerService productSellerService = new ProductSellerService(productSellerPersistencemock, sellerServicemock, productServicemock);
        productSellerService.insert(productSellerDto);
        assertNotNull(product.getProductId());
    }

    @Test
    void shouldNotInsertProductSeller() {
        ProductSellerPersistence productSellerPersistencemock = mock(ProductSellerPersistence.class);
        ProductSellerService productSellerServicemock = mock(ProductSellerService.class);
        ProductService productServicemock = mock(ProductService.class);
        SellerService sellerServicemock = mock(SellerService.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L, "111.222.333-44", "Rafael", address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);

        ProductSeller productSeller = new ProductSeller(1L, 10.0, 5.0, 1.0, seller, product, new BigDecimal("20"));
        ProductSellerDto productSellerDto = new ProductSellerDto(10.0, 5.0, 1.0, 1L, "MLB-123", new BigDecimal("20"));

        when(productSellerServicemock.insert(any(ProductSellerDto.class))).thenReturn(productSeller);

        ProductSellerService productSellerService = new ProductSellerService(productSellerPersistencemock, sellerServicemock, productServicemock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSellerService.insert(productSellerDto);
        });
        assertEquals("Vendedor ou produto não existe!", exception.getMessage());
    }

    @Test
    void shouldGetProductSeller() {
        ProductSellerPersistence mock = mock(ProductSellerPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L, "111.222.333-44", "Rafael", address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);

        ProductSeller productSeller = new ProductSeller(1L, 10.0, 5.0, 1.0, seller, product, new BigDecimal("20"));

        when(mock.findById(1L)).thenReturn(Optional.of(productSeller));

        ProductSellerService productSellerService = new ProductSellerService(mock);
        ProductSeller productSeller1 = productSellerService.getProductSeller(1L);
        assertNotNull(productSeller1.getProductSellerId());
    }

    @Test
    void shouldNotGetProductSeller() {
        ProductSellerPersistence mock = mock(ProductSellerPersistence.class);

        ProductSellerService productSellerService = new ProductSellerService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSellerService.getProductSeller(1L);
        });
        assertEquals("Não existe Seller para essa busca!", exception.getMessage());
    }

    @Test
    void shouldGetProductSellerByProduto() {
        ProductSellerPersistence mock = mock(ProductSellerPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Address address = new Address("rua goias", "44", "99999-000", "sp", "sp", "cs");
        Seller seller = new Seller(1L, "111.222.333-44", "Rafael", address);
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);

        ProductSeller productSeller = new ProductSeller(1L, 10.0, 5.0, 1.0, seller, product, new BigDecimal("20"));

        when(mock.findProductSellerByProduct(product)).thenReturn(Optional.of(productSeller));

        ProductSellerService productSellerService = new ProductSellerService(mock);
        ProductSeller productSeller1 = productSellerService.getProductSellerByProduto(product);
        assertNotNull(productSeller1.getProduct());
    }

    @Test
    void shouldNotGetProductSellerByProduto() {
        ProductSellerPersistence mock = mock(ProductSellerPersistence.class);

        Type type = new Type(1L, "RF", "REFRIGERADOS");
        Product product = new Product("MLB-123", "Uva", "Caixa de Uva", type);


        ProductSellerService productSellerService = new ProductSellerService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productSellerService.getProductSellerByProduto(product);
        });
        assertEquals("Não existe Seller para essa busca!", exception.getMessage());
    }

    @Test
    void shouldListProductSeller() {

    }
}

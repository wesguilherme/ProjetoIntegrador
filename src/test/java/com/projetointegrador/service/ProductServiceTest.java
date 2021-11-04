package com.projetointegrador.service;

import com.projetointegrador.dto.ProductDto;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.Type;
import com.projetointegrador.repository.ProductPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Test
    void shouldInsertProduct() throws NullPointerException {
        ProductPersistence mock1 = mock(ProductPersistence.class);
        ProductService mock = mock(ProductService.class);
        TypeService mock2 = mock(TypeService.class);

        Type type = new Type(1L, "FF", "Congelados");
        Product product = new Product("MLB-410", "ruffles", "felicidade", type);
        ProductDto productDto = new ProductDto("MLB-410", "ruffles", "felicidade", 1L);

        when(mock.insert(any(ProductDto.class))).thenReturn(product);

        ProductService productService = new ProductService(mock1, mock2);
        productService.insert(productDto);
        assertNotNull(product.getProductId());
    }

    @Test
    void shouldNotInsertProduct() {
        ProductPersistence mock1 = mock(ProductPersistence.class);
        ProductService mock = mock(ProductService.class);
        String productId = "MLB-410";
        Type type = new Type(1L, "FF", "Congelados");
        Product product = new Product("MLB-410", "ruffles", "felicidade", type);
        ProductDto productDto = new ProductDto("MLB-410", "ruffles", "felicidade", 1L);
        when(mock1.findByProductId(productId)).thenReturn(product);

        ProductService productService = new ProductService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.insert(productDto);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldgetByIdProduct() {
        ProductPersistence mock1 = mock(ProductPersistence.class);
        Type type = new Type(1L, "FF", "Congelados");
        Optional<Product> product = Optional.of(new Product("MLB-410", "ruffles", "felicidade", type));
        when(mock1.findById("MLB-410")).thenReturn(product);

        ProductService productService = new ProductService(mock1);
        Product product1 = productService.getByIdProduct("MLB-410");
        assertNotNull(product1.getProductId());
    }

    @Test
    void shouldNotgetByIdProduct() {
        ProductPersistence mock1 = mock(ProductPersistence.class);
        String productId = "MLB-410";
        Type type = new Type(1L, "FF", "Congelados");
        Product product = new Product("MLB-410", "ruffles", "felicidade", type);
        when(mock1.findByProductId(productId)).thenReturn(product);

        ProductService productService = new ProductService(mock1);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            productService.getByIdProduct(productId);
        });

        String expectedMessage = "Não existe product com esse id!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void mustConvert(){
//
//    }
}

package com.projetointegrador.service.unit;

import com.projetointegrador.dto.ProductItemListDto;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.PurchaseItem;
import com.projetointegrador.repository.PurchaseItemPersistence;
import com.projetointegrador.service.ProductService;
import com.projetointegrador.service.PurchaseItemService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PurchaseItemServiceTest {

    @Test
    void shouldUpdateForNullPurchaseItem(){
        PurchaseItemPersistence mock = mock(PurchaseItemPersistence.class);
        List<ProductItemListDto> products = new ArrayList<>();

        PurchaseItemService purchaseItemService = new PurchaseItemService(mock);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            purchaseItemService.update(products);
        });
        String expectedMessage = "A lista de produtos não pode ser vazia!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldUpdatePurchaseItemNoPresent(){
        PurchaseItemPersistence mock = mock(PurchaseItemPersistence.class);

        ProductItemListDto prod = ProductItemListDto.builder().purchaseItemId(1L).productId("MLB-123").quantity(10).build();
        List<ProductItemListDto> products = new ArrayList<>();
        products.add(prod);

        when(mock.findById(1L)).thenReturn(Optional.empty());

        PurchaseItemService purchaseItemService = new PurchaseItemService(mock);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            purchaseItemService.update(products);
        });

        String expectedMessage = "Não existe purchaseItem com esse id!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldUpdatePurchaseItemPresent(){
        PurchaseItemPersistence mock = mock(PurchaseItemPersistence.class);
        ProductService mockProd = mock(ProductService.class);

        ProductItemListDto prod = ProductItemListDto.builder().purchaseItemId(1L).productId("MLB-123").quantity(10).build();
        List<ProductItemListDto> products = new ArrayList<>();
        products.add(prod);

        Product product = Product.builder().productId("MLB-123").build();
        when(mockProd.getByIdProduct("MLB-123")).thenReturn(product);

        Product produto = Product.builder().productId("MLB-123").build();
        PurchaseItem purchaseItem = PurchaseItem.builder().product(produto).quantity(5).build();
        when(mock.findById(anyLong())).thenReturn(Optional.ofNullable(purchaseItem));

        PurchaseItemService purchaseItemService = new PurchaseItemService(mock,mockProd);
        purchaseItemService.update(products);

        assertEquals(10,purchaseItem.getQuantity());
    }
}

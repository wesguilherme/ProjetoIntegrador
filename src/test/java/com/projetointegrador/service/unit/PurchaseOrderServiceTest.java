package com.projetointegrador.service.unit;

import com.projetointegrador.dto.ProductItemDto;
import com.projetointegrador.dto.PurchaseOrderResponseDto;
import com.projetointegrador.dto.TotalPrice;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.ProductPersistence;
import com.projetointegrador.repository.ProductSellerPersistence;
import com.projetointegrador.repository.PurchaseOrderPersistence;
import com.projetointegrador.service.ProductSellerService;
import com.projetointegrador.service.ProductService;
import com.projetointegrador.service.PurchaseOrderService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PurchaseOrderServiceTest {

    @Test
    void shouldInsertPurchaseOrder(){
        PurchaseOrderPersistence purchaseOrderPersistenceMock = mock(PurchaseOrderPersistence.class);

        Buyer buyer = Buyer.builder().buyerId(1L).build();
        OrderStatus orderStatus = OrderStatus.builder().orderStatusId(1L).statusCode("cart").build();

        PurchaseItem purchaseItem1 = PurchaseItem.builder().purchaseItemId(1L).quantity(10).build();
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        purchaseItems.add(purchaseItem1);

        PurchaseOrder purchaseOrder = PurchaseOrder.builder().purchaseOrderId(1L).date(LocalDate.now()).buyer(buyer).orderStatus(orderStatus).purchaseItems(purchaseItems).build();

        when(purchaseOrderPersistenceMock.save(any(PurchaseOrder.class))).thenReturn(purchaseOrder);

        ProductItemDto prod = ProductItemDto.builder().productId("MLB-123").quantity(10).build();
        List<ProductItemDto> productsItemDtos = new ArrayList<>();
        productsItemDtos.add(prod);

        PurchaseOrderService purchaseOrderService = new PurchaseOrderService(purchaseOrderPersistenceMock);

        PurchaseOrder purchaseOrder1 = purchaseOrderService.insert(purchaseOrder);
        assertNotNull(purchaseOrder1.getPurchaseOrderId());
    }

    @Test
    void shouldGetTotalprice(){
        PurchaseOrderPersistence purchaseOrderPersistenceMock = mock(PurchaseOrderPersistence.class);
        ProductPersistence productPersistenceMock = mock(ProductPersistence.class);
        ProductSellerPersistence productSellerPersistenceMock = mock(ProductSellerPersistence.class);

        ProductSellerService productSellerService = new ProductSellerService(productSellerPersistenceMock);
        ProductService productService = new ProductService(productPersistenceMock);

        ProductItemDto productItemDto = ProductItemDto.builder().productId("MLB-123").quantity(8).build();

        List<ProductItemDto> productItemDtos = new ArrayList<>();
        productItemDtos.add(productItemDto);

        Type type = Type.builder().typeId(1L).environmentType("REFRIGERADOS").initials("RF").build();
        Product product = Product.builder().productId("MLB-123").description("Produto teste").name("Frango").type(type).build();

        when(productPersistenceMock.findById(anyString())).thenReturn(java.util.Optional.ofNullable(product));

        ProductSeller productSeller = ProductSeller.builder().price(new BigDecimal(10)).build();
        when(productSellerPersistenceMock.findProductSellerByProduct(product)).thenReturn(java.util.Optional.ofNullable(productSeller));

        PurchaseOrderService purchaseOrderService = new PurchaseOrderService(purchaseOrderPersistenceMock, productService, productSellerService);

        TotalPrice totalPrice = purchaseOrderService.getTotalprice(productItemDtos);
        assertEquals(new BigDecimal(80),totalPrice.getTotalprice());
    }

    @Test
    void ShoudListOrdersByOrderId(){
        PurchaseOrderPersistence purchaseOrderPersistenceMock = mock(PurchaseOrderPersistence.class);

        Buyer buyer = Buyer.builder().buyerId(1L).build();
        OrderStatus orderStatus = OrderStatus.builder().orderStatusId(1L).statusCode("cart").build();


        Type type = Type.builder().typeId(1L).environmentType("REFRIGERADOS").initials("RF").build();
        Product product = Product.builder().productId("MLB-123").description("Produto teste").name("Frango").type(type).build();

        PurchaseItem purchaseItem1 = PurchaseItem.builder().purchaseItemId(1L).quantity(10).product(product).build();
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        purchaseItems.add(purchaseItem1);

        PurchaseOrder purchaseOrder = PurchaseOrder.builder().purchaseOrderId(1L).date(LocalDate.now()).buyer(buyer).orderStatus(orderStatus).purchaseItems(purchaseItems).build();

        when(purchaseOrderPersistenceMock.findById(anyLong())).thenReturn(Optional.ofNullable(purchaseOrder));

        PurchaseOrderService purchaseOrderService = new PurchaseOrderService(purchaseOrderPersistenceMock);

        PurchaseOrderResponseDto purchaseOrderResponseDto = purchaseOrderService.listOrdersByOrderId(1L);

        assertNotNull(purchaseOrderResponseDto.getOrderStatus());

    }
}

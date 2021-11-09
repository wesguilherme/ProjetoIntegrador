package com.projetointegrador.service;

import com.projetointegrador.dto.ProductItemDto;
import com.projetointegrador.entity.Buyer;
import com.projetointegrador.entity.OrderStatus;
import com.projetointegrador.entity.PurchaseItem;
import com.projetointegrador.entity.PurchaseOrder;
import com.projetointegrador.repository.PurchaseOrderPersistence;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
}

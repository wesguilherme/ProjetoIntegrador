package com.projetointegrador.service;

import com.projetointegrador.dto.ProductItemDto;
import com.projetointegrador.dto.ProductItemListDto;
import com.projetointegrador.dto.PurchaseOrderDto;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.PurchaseItemPersistence;
import com.projetointegrador.repository.PurchaseOrderPersistence;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PurchaseOrderServiceTest {

    @Test
    void shouldInsertPurchaseOrder(){
        PurchaseOrderPersistence purchaseOrderPersistenceMock = mock(PurchaseOrderPersistence.class);
        PurchaseOrderService purchaseOrderServiceMock = mock(PurchaseOrderService.class);
        BuyerService buyerServiceMock = mock(BuyerService.class);
        OrderStatusService orderStatusServiceMock = mock(OrderStatusService.class);
        ProductService productServiceMock = mock(ProductService.class);
        ProductSellerService productSellerServiceMock = mock(ProductSellerService.class);

        ProductItemDto prod = ProductItemDto.builder().productId("MLB-123").quantity(10).build();
        List<ProductItemDto> productsItemDtos = new ArrayList<>();
        productsItemDtos.add(prod);

        Buyer buyer = Buyer.builder().buyerId(1L).build();
        when(buyerServiceMock.getByIdBuyer(anyLong())).thenReturn(buyer);

        OrderStatus orderStatus = OrderStatus.builder().orderStatusId(1L).statusCode("cart").build();
        when(orderStatusServiceMock.getByOrderStatus("cart")).thenReturn(orderStatus);

        PurchaseOrderDto purchaseOrderDto = PurchaseOrderDto.builder().products(productsItemDtos).date(LocalDate.now()).buyerId(1L).orderStatus(orderStatus).build();

        PurchaseItem purchaseItem1 = PurchaseItem.builder().purchaseItemId(1L).quantity(10).build();
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        purchaseItems.add(purchaseItem1);

        PurchaseOrder purchaseOrder = PurchaseOrder.builder().purchaseOrderId(1L).date(LocalDate.now()).buyer(buyer).orderStatus(orderStatus).purchaseItems(purchaseItems).build();

        Product product = Product.builder().productId("MLB-123").build();
        when(productServiceMock.getByIdProduct("MLB-123")).thenReturn(product);

        when(purchaseOrderServiceMock.insert(any(PurchaseOrderDto.class))).thenReturn(purchaseOrder);

        PurchaseOrderService purchaseOrderService = new PurchaseOrderService(purchaseOrderPersistenceMock,buyerServiceMock,orderStatusServiceMock,productServiceMock,productSellerServiceMock,purchaseOrder);
        PurchaseOrder purchaseOrder1 = purchaseOrderService.insert(purchaseOrderDto);

        assertNotNull(purchaseOrder1.getPurchaseOrderId());
    }
}

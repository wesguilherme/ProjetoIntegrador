package com.projetointegrador.service;

import com.projetointegrador.entity.OrderStatus;
import com.projetointegrador.repository.OrderStatusPersistence;
import com.projetointegrador.repository.ProductSellerPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderStatusServiceTest {

    @Test
    void mustInsertOrderStatus ( ) {
        OrderStatusPersistence mock1 = mock(OrderStatusPersistence.class);
        OrderStatusService mock = mock(OrderStatusService.class);
        OrderStatus orderStatus = new OrderStatus(1L, "ok");

        when(mock.insert(any(OrderStatus.class))).thenReturn(orderStatus);

        OrderStatusService orderStatusService = new OrderStatusService(mock1);
        orderStatusService.insert(orderStatus);
        assertNotNull(orderStatus.getOrderStatusId());
    }

    @Test
    void shouldGetByOrderStatus ( ) {
        OrderStatusPersistence mock1 = mock(OrderStatusPersistence.class);

        OrderStatus orderStatus = new OrderStatus(1L, "cart");

        when(mock1.findByStatusCode("cart")).thenReturn(Optional.of(orderStatus));

        OrderStatusService orderStatusService = new OrderStatusService(mock1);

        OrderStatus orderStatus1 = orderStatusService.getByOrderStatus("cart");
        assertNotNull(orderStatus1.getOrderStatusId());
    }

    @Test
    void shouldNotGetByOrderStatus ( ) {
        ProductSellerPersistence mock = mock(ProductSellerPersistence.class);

        ProductSellerService productSellerService = new ProductSellerService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            productSellerService.getProductSeller(1L);
        });
        assertEquals("Não existe Seller para essa busca!", exception.getMessage());
    }
}

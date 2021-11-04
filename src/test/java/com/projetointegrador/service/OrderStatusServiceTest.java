package com.projetointegrador.service;

import com.projetointegrador.entity.OrderStatus;
import com.projetointegrador.repository.OrderStatusPersistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderStatusServiceTest {

    @Test
    void mustInsertOrderStatus() {
        OrderStatusPersistence mock1 = mock(OrderStatusPersistence.class);
        OrderStatusService mock = mock(OrderStatusService.class);
        OrderStatus orderStatus = new OrderStatus(1L, "ok");

        when(mock.insert(any(OrderStatus.class))).thenReturn(orderStatus);

        OrderStatusService orderStatusService = new OrderStatusService(mock1);
        orderStatusService.insert(orderStatus);
        assertNotNull(orderStatus.getOrderStatusId());
    }
}

package com.projetointegrador.service;

import com.projetointegrador.entity.OrderStatus;
import com.projetointegrador.repository.OrderStatusPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusPersistence orderStatusPersistence;

    public OrderStatusService() {
    }

    public OrderStatusService(OrderStatusPersistence orderStatusPersistence) {
        this.orderStatusPersistence = orderStatusPersistence;
    }

    public OrderStatus insert(OrderStatus orderStatus){
        return orderStatusPersistence.save(orderStatus);
    }

    public OrderStatus getByOrderStatus(String statusCode) {
        Optional<OrderStatus> val;

        val = orderStatusPersistence.findByStatusCode(statusCode);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe order status!");
        }
    }
}

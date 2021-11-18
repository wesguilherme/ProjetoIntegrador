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

    /**
     * @param orderStatusPersistence é esperado o parâmetro orderStatusPersistence para injeção de depêndencia
     * @author - Grupo 5
     */
    public OrderStatusService(OrderStatusPersistence orderStatusPersistence) {
        this.orderStatusPersistence = orderStatusPersistence;
    }

    /**
     * @param orderStatus é esperado o parâmetro do tipo orderStatus
     * @return orderStatus cadastrado
     * @author - Grupo 5
     */
    public OrderStatus insert(OrderStatus orderStatus) {
        return orderStatusPersistence.save(orderStatus);
    }

    /**
     * @param statusCode é esperado o parâmetro do tipo statusCode
     * @return orderStatus se estiver presente
     * @throws RuntimeException caso não exista orderStatus
     * @author - Grupo 5
     */
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

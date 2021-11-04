package com.projetointegrador.repository;

import com.projetointegrador.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusPersistence extends JpaRepository<OrderStatus, Long> {

    Optional<OrderStatus> findByStatusCode(String statusCode);
}

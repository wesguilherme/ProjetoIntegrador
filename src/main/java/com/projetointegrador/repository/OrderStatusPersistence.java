package com.projetointegrador.repository;

import com.projetointegrador.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusPersistence extends JpaRepository<OrderStatus, Long> {

}

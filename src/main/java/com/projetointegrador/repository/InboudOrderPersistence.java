package com.projetointegrador.repository;

import com.projetointegrador.entity.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboudOrderPersistence extends JpaRepository<InboundOrder, Long> {
}

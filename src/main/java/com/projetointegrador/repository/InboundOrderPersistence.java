package com.projetointegrador.repository;


import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.entity.Type;
import com.projetointegrador.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InboundOrderPersistence extends JpaRepository<InboundOrder, Long> {

}
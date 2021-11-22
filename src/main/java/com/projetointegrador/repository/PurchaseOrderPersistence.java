package com.projetointegrador.repository;

import com.projetointegrador.entity.Buyer;
import com.projetointegrador.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderPersistence extends JpaRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findPurchaseOrderByBuyer(Buyer buyer);

}

package com.projetointegrador.repository;

import com.projetointegrador.entity.PurchaseItem;
import com.projetointegrador.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemPersistence extends JpaRepository<PurchaseItem, Long> {

}

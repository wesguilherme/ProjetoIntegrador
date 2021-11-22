package com.projetointegrador.repository;

import com.projetointegrador.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingPersistence extends JpaRepository<Shipping, String> {

    Shipping findByShippingId(String shippingId);
}

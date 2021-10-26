package com.projetointegrador.repository;

import com.projetointegrador.entity.ProductSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSellerPersistence extends JpaRepository<ProductSeller, Long> {

}

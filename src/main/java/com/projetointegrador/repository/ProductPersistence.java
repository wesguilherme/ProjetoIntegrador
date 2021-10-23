package com.projetointegrador.repository;

import com.projetointegrador.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPersistence extends JpaRepository<Product, String> {

    Product findByProductId(String id);

}

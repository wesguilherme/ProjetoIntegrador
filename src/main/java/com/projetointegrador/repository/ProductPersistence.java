package com.projetointegrador.repository;

import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPersistence extends JpaRepository<Product, String> {

    Product findByProductId(String id);

    List<Product> findByType(Type type);
}

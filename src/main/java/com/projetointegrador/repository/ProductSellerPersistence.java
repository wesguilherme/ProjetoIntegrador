package com.projetointegrador.repository;

import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.ProductSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSellerPersistence extends JpaRepository<ProductSeller, Long> {

    Optional<ProductSeller> findProductSellerByProduct(Product product);

}

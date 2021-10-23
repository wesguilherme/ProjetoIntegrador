package com.projetointegrador.service;

import com.projetointegrador.entity.ProductSeller;

import com.projetointegrador.repository.ProductSellerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSellerService {

    @Autowired
    private ProductSellerPersistence productSellerPersistence;

    public ProductSellerService() {
    }

    public ProductSellerService(ProductSellerPersistence productSellerPersistence) {
        this.productSellerPersistence = productSellerPersistence;
    }

    public ProductSeller cadastrar(ProductSeller productSeller) {
        return productSellerPersistence.save(productSeller);
    }
}


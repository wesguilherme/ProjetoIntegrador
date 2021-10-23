package com.projetointegrador.service;

import com.projetointegrador.repository.ProductPersistence;
import com.projetointegrador.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProductService {

    @Autowired
    private ProductPersistence productPersistence;

    public ProductService() {
    }

    private boolean codigoNaoUtilizado(String id) {
        Product productExistente = productPersistence.findByProductId(id);
        if(productExistente == null){
            return true;
        }
        return false;
    }

    public Product cadastrar(Product product) {
        if(codigoNaoUtilizado(product.getProductId())) {
            return productPersistence.save(product);
        }else{
            throw new RuntimeException("Código já utilizado");
        }
    }
}

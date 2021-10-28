package com.projetointegrador.service;

import com.projetointegrador.entity.Product;
import com.projetointegrador.repository.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductPersistence productPersistence;

    public ProductService() {
    }

    /**
     * @param productPersistence - é esperado um parâmetro do tipo productPersistence para injeção de dependência
     * @author - Grupo 5 - Tester Ana
     */
    public ProductService(ProductPersistence productPersistence) {
        this.productPersistence = productPersistence;
    }

    /**
     * @param id - é esperado o parametro id do product
     * @return - retorna a verificaçao de duplicidade do código
     * @author - Grupo 5 - Tester Ana
     */
    private boolean UtilizedCode(String id) {
        Product productExistente = productPersistence.findByProductId(id);
        if (productExistente != null) {
            return true;
        }
        return false;
    }

    /**
     * @param product - é esperado um objeto do tipo product
     * @return - retorna product cadastrado na lista
     * @author - Grupo 5 - Tester Ana
     */
    public Product insert(Product product) {
        if (!UtilizedCode(product.getProductId())) {
            return productPersistence.save(product);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    /**
     * @param id - é esperado o parametro id do product
     * @return - retorna se o product existe ou não através do id
     * @author - Grupo 5 - Tester Ana
     */
    public Product getByIdProduct(String id) {
        Optional<Product> val;

        val = productPersistence.findById(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe product com esse id!");
        }
    }
}

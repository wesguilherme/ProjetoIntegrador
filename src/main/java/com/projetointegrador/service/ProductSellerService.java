package com.projetointegrador.service;

import com.projetointegrador.dto.ProductSellerDto;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.entity.Seller;
import com.projetointegrador.repository.ProductSellerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSellerService {

    @Autowired
    private ProductSellerPersistence productSellerPersistence;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    public ProductSellerService() {
    }

    /**
     * @param productSellerPersistence - é esperado um parâmetro do tipo productSellerPersistence para injeção de dependência
     * @author - Grupo 5 - Tester Ana
     */
    public ProductSellerService(ProductSellerPersistence productSellerPersistence) {
        this.productSellerPersistence = productSellerPersistence;
    }

    /**
     * @param productSellerDto - é esperado um objeto do tipo productSellerDto
     * @return - retorna productSellerDto cadastrado na lista
     * @author - Grupo 5 - Tester Ana
     */
    public ProductSeller insert(ProductSellerDto productSellerDto) {
        ProductSeller productSeller = convert(productSellerDto);

        if (productSeller.getProduct() != null && productSeller.getSeller() != null) {
            return productSellerPersistence.save(productSeller);
        }

        throw new RuntimeException("Vendedor ou produto não existe!");
    }

    /**
     * @param productSellerDto - é esperado um objeto do tipo productSellerDto
     * @return - retorna o productSeller com os dados de product e seller
     * @author - Grupo 5 - Tester Ana
     */
    public ProductSeller convert(ProductSellerDto productSellerDto) {
        ProductSeller productSeller = new ProductSeller();
        productSeller.setProductSellerId(productSellerDto.getSellerId());
        productSeller.setVolume(productSellerDto.getVolume());
        productSeller.setMaximumTemperature(productSellerDto.getMaximumTemperature());
        productSeller.setMinimumTemperature(productSellerDto.getMinimumTemperature());

        Product p = productService.getByIdProduct(productSellerDto.getProductId());
        Seller s = sellerService.getByIdSeller(productSellerDto.getSellerId());

        productSeller.setProduct(p);
        productSeller.setSeller(s);

        return productSeller;
    }
}


package com.projetointegrador.service;

import com.projetointegrador.dto.ProductResponseDto;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.repository.ProductSellerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * @param productSellerPersistence é esperado um parâmetro do tipo productSellerPersistence para injeção de dependência
     * @author - Grupo 5
     */
    public ProductSellerService(ProductSellerPersistence productSellerPersistence) {
        this.productSellerPersistence = productSellerPersistence;
    }

    /**
     *
     * @param productSellerPersistence é esperado um parâmetro do tipo productSellerPersistence para injeção de dependência
     * @param sellerService é esperado um parâmetro do tipo sellerService para injeção de dependência
     * @param productService é esperado um parâmetro do tipo productService para injeção de dependência
     */
    public ProductSellerService(ProductSellerPersistence productSellerPersistence, SellerService sellerService, ProductService productService) {
        this.productSellerPersistence = productSellerPersistence;
        this.sellerService = sellerService;
        this.productService = productService;
    }

    /**
     *
     * @param sellerService é esperado um parâmetro do tipo sellerService para injeção de dependência
     * @param productService é esperado um parâmetro do tipo productService para injeção de dependência
     */
    public ProductSellerService(SellerService sellerService, ProductService productService) {
        this.sellerService = sellerService;
        this.productService = productService;
    }
    /**
     * @param productSeller - é esperado um objeto do tipo productSellerDto
     * @return - retorna productSeller cadastrado na lista
     * @author - Grupo 5
     * @throws RuntimeException caso vendedor ou produto não exista
     */
    public ProductSeller insert(ProductSeller productSeller) {
        if (productSeller.getProduct() != null && productSeller.getSeller() != null) {
            return productSellerPersistence.save(productSeller);
        }
        throw new RuntimeException("Vendedor ou produto não existe!");
    }

    /**
     * @param id é esperado o parâmetro do tipo id do produto
     * @return retorna a verificaçao da existencia do código
     * @author - Grupo 5
     * @throws RuntimeException caso não exta seller para essa busca
     */
    public ProductSeller getProductSeller(Long id) {
        Optional<ProductSeller> val;
        val = productSellerPersistence.findById(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe Seller para essa busca!");
        }
    }
    /**
     * @param product faz a validacao do productSeller
     * @return retorna a verificaçao de duplicidade do código
     * @author - Grupo 5
     * @throws RuntimeException caso não exta seller para essa busca
     */
    public ProductSeller getProductSellerByProduto(Product product) {
        Optional<ProductSeller> val;

        val = productSellerPersistence.findProductSellerByProduct(product);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe Seller para essa busca!");
        }
    }
    /**
     * @return retorna a lista de productResponseDto
     * @author - Grupo 5
     */
    public List<ProductResponseDto> listProduct() {
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        List<ProductSeller> productSeller = productSellerPersistence.findAll();
        for (ProductSeller item : productSeller) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setMaximumTemperature(item.getMaximumTemperature());
            productResponseDto.setMinimumTemperature(item.getMinimumTemperature());
            productResponseDto.setVolume(item.getVolume());
            productResponseDto.setProduct(item.getProduct());
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }
}


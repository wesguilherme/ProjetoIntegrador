package com.projetointegrador.controller;

import com.projetointegrador.dto.ProductSellerDto;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.service.ProductSellerService;
import com.projetointegrador.service.ProductService;
import com.projetointegrador.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/product-seller")
public class ProductSellerController {

    @Autowired
    private ProductSellerService productSellerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    /**
     * @param productSellerDto é esperado um objeto do tipo productSellerDto
     * @param uriBuilder é esperado um objeto do tipo uriBuilder
     * @return o productSeller cadastrado
     */
    @PostMapping(value = "/insert")
    public ResponseEntity<ProductSeller> insert(@RequestBody @Valid ProductSellerDto productSellerDto, UriComponentsBuilder uriBuilder) {
        ProductSeller productSellerCadastrado = productSellerService.insert(productSellerDto.convert(productSellerDto, productService, sellerService));

        URI uri = uriBuilder.path("/product-seller/search/{id}").buildAndExpand(productSellerCadastrado.getProductSellerId()).toUri();
        return ResponseEntity.created(uri).body(productSellerCadastrado);
    }
}
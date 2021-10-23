package com.projetointegrador.controller;

import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.service.ProductSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/productSeller")
public class ProductSellerController {

    @Autowired
    private ProductSellerService productSellerService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ProductSeller> cadastrar(@RequestBody ProductSeller productSeller, UriComponentsBuilder uriBuilder){
        ProductSeller productSellerCadastrado = productSellerService.cadastrar(productSeller);

        URI uri = uriBuilder.path("/productSeller/buscar/{id}").buildAndExpand(productSellerCadastrado.getProductSellerId()).toUri();
        return ResponseEntity.created(uri).body(productSellerCadastrado);
    }
}

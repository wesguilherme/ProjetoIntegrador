package com.projetointegrador.controller;

import com.projetointegrador.entity.Product;
import com.projetointegrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Product> cadastrar(@RequestBody Product product, UriComponentsBuilder uriBuilder){
        Product productCadastrado = productService.cadastrar(product);

        URI uri = uriBuilder.path("/warehouse/buscar/{id}").buildAndExpand(productCadastrado.getProductId()).toUri();
        return ResponseEntity.created(uri).body(productCadastrado);
    }
}

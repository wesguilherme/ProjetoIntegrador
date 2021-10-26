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
@RequestMapping(value = "/api/v1/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/product/insert")
    public ResponseEntity<Product> insert(@RequestBody Product product, UriComponentsBuilder uriBuilder) {
        Product productCadastrado = productService.insert(product);

        //ta certo warehouse?
        URI uri = uriBuilder.path("/warehouse/search/{id}").buildAndExpand(productCadastrado.getProductId()).toUri();
        return ResponseEntity.created(uri).body(productCadastrado);
    }
}

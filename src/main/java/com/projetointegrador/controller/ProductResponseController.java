package com.projetointegrador.controller;

import com.projetointegrador.dto.ProductResponseDto;
import com.projetointegrador.service.ProductSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class ProductResponseController {

    @Autowired
    private ProductSellerService productSellerService;

    @GetMapping(value = "fresh-products/list")
    public ResponseEntity<?> getProductSellerId() {
        List<ProductResponseDto> productResponseDto = productSellerService.listProduct();

        if(productResponseDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(productResponseDto);
    }
}


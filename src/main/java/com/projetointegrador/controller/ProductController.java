package com.projetointegrador.controller;

import com.projetointegrador.dto.ProductDto;
import com.projetointegrador.dto.PurchaseOrderDto;
import com.projetointegrador.dto.PurchaseOrderResponseDto;
import com.projetointegrador.dto.TotalPrice;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.PurchaseOrder;
import com.projetointegrador.service.BatchStockService;
import com.projetointegrador.service.ProductService;
import com.projetointegrador.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
<<<<<<< HEAD
import java.util.List;
import java.util.Optional;
=======
import java.util.Arrays;
import java.util.List;
>>>>>>> 1134395b0cc95bf66218bbb9f7a5acae6dc2f2bd

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private BatchStockService batchStockService;

    @PostMapping(value = "/insert")
    public ResponseEntity<?> insert(@RequestBody @Valid ProductDto productDto, UriComponentsBuilder uriBuilder) {
        Product productCadastrado = productService.insert(productDto);

        URI uri = uriBuilder.path("/product/search/{id}").buildAndExpand(productCadastrado.getProductId()).toUri();
        return ResponseEntity.created(uri).body(productCadastrado);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<TotalPrice> insert(@RequestBody @Valid PurchaseOrderDto purchaseOrderDto, UriComponentsBuilder uriBuilder){
        batchStockService.verifyProductInBatchStock(purchaseOrderDto.getProducts());
        TotalPrice totalPrice = purchaseOrderService.getTotalprice(purchaseOrderDto.getProducts());
        PurchaseOrder purchaseOrder = purchaseOrderService.insert(purchaseOrderDto);

        URI uri = uriBuilder.path("/product/search/{id}").buildAndExpand(purchaseOrder.getPurchaseOrderId()).toUri();
        return ResponseEntity.created(uri).body(totalPrice);
    }


    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<?> listOrdersByOrderId(@PathVariable("id") Long id){
        PurchaseOrderResponseDto purchaseOrderResponseDto = purchaseOrderService.listOrdersByOrderId(id);

        if (purchaseOrderResponseDto.getBuyerId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(purchaseOrderResponseDto);
    }
}

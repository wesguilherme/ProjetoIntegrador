package com.projetointegrador.controller;

import com.projetointegrador.dto.*;
import com.projetointegrador.entity.InboundOrder;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.Section;
import com.projetointegrador.entity.Shipping;
import com.projetointegrador.service.BuyerService;
import com.projetointegrador.service.ProductService;
import com.projetointegrador.service.ShippingService;
import com.projetointegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/Shipping")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/insert")
    public ResponseEntity<Shipping> insert(@RequestBody @Valid ShippingDto shippingDto, UriComponentsBuilder uriBuilder) throws IOException {
        Shipping shippingCadastrado = shippingService.insert(shippingDto.convert(shippingDto, warehouseService, buyerService, productService));

        URI uri = uriBuilder.path("/shipping/search/{id}").buildAndExpand(shippingCadastrado.getShippingId()).toUri();
        return ResponseEntity.created(uri).body(shippingCadastrado);
    }



//    @PutMapping(value = "/update/{id}")
//    public ResponseEntity<Shipping> update(@RequestBody @Valid ShippingDto shippingDto, @PathVariable("id") String id, UriComponentsBuilder uriBuilder){
//        Shipping shippingCadastrado = shippingService.update(shippingDto.convert(shippingDto, warehouseService, buyerService, productService), id);
//
//        URI uri = uriBuilder.path("/shipping/search/{id}").buildAndExpand(shippingCadastrado.getShippingId()).toUri();
//        return ResponseEntity.created(uri).body(shippingCadastrado);
//    }
}
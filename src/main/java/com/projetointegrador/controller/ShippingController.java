package com.projetointegrador.controller;

import com.projetointegrador.dto.*;
import com.projetointegrador.entity.*;
import com.projetointegrador.repository.ShippingPersistence;
import com.projetointegrador.repository.StatesPersistence;
import com.projetointegrador.service.*;
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

    @Autowired
    private StatesService statesService;


    @PostMapping(value = "/insert")
    public ResponseEntity<Shipping> insert(@RequestBody @Valid ShippingDto shippingDto, UriComponentsBuilder uriBuilder) throws IOException {
        Shipping shippingCadastrado = shippingService.insert(shippingDto.convert(shippingDto, warehouseService, buyerService, productService, statesService));

        URI uri = uriBuilder.path("/shipping/search/{id}").buildAndExpand(shippingCadastrado.getShippingId()).toUri();
        return ResponseEntity.created(uri).body(shippingCadastrado);
    }

    @GetMapping(value = "/{shippingId}")
    public ResponseEntity<List<ShippingResponseDto>> frete(){
        List<ShippingResponseDto> shippingResponseDto = shippingService.listShipping();
        return ResponseEntity.ok().body(shippingResponseDto);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Shipping> update(@RequestBody @Valid ShippingDto shippingDto, @PathVariable("id") String id, UriComponentsBuilder uriBuilder){
        Shipping shippingCadastrado = shippingService.update(shippingDto.convert(shippingDto,warehouseService,buyerService,productService,statesService), id);

        URI uri = uriBuilder.path("/shipping/search/{id}").buildAndExpand(shippingCadastrado.getShippingId()).toUri();
        return ResponseEntity.created(uri).body(shippingCadastrado);
    }


//ShippingDto.convertUpdate(shippingCadastrado.getShippingId())


//    @DeleteMapping(value = "/delete")
//    public void delete(@RequestBody @Valid Shipping shippingId) {
//        shippingService.remover(shippingId);
//        Shipping shippingCadastrado = shippingService.remover(shippingId);

//        URI uri = uriBuilder.path("/shipping/search/{id}").buildAndExpand(shippingCadastrado.getShippingId()).toUri();
//        return ResponseEntity.created(uri).body(shippingCadastrado);

//    }
}


package com.projetointegrador.controller;

import com.projetointegrador.dto.ShippingDto;
import com.projetointegrador.dto.ShippingResponseDto;
import com.projetointegrador.entity.Shipping;
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


    /**
     * @param shippingDto é esperado um objeto do tipo shippingDto
     * @param uriBuilder é esperado um objeto do tipo uriBuilder
     * @return um shipping cadastrado
     * @throws IOException
     * @author Rafael
     */
    @PostMapping(value = "/insert")
    public ResponseEntity<Shipping> insert(@RequestBody @Valid ShippingDto shippingDto, UriComponentsBuilder uriBuilder) throws IOException {
        Shipping shippingCadastrado = shippingService.insert(shippingDto.convert(shippingDto, warehouseService, buyerService, productService, statesService));

        URI uri = uriBuilder.path("/shipping/search/{id}").buildAndExpand(shippingCadastrado.getShippingId()).toUri();
        return ResponseEntity.created(uri).body(shippingCadastrado);
    }

    /**
     * @return lista de shippingResponseDto
     * @author Rafael
     */
    @GetMapping(value = "/{shippingId}")
    public ResponseEntity<List<ShippingResponseDto>> frete(){
        List<ShippingResponseDto> shippingResponseDto = shippingService.listShipping();
        return ResponseEntity.ok().body(shippingResponseDto);
    }


    // Metodo criado, mais seria necessário adicionar atributos e não daria tempo.

//    @PutMapping(value = "/update/{id}")
//    public ResponseEntity<Shipping> update(@RequestBody @Valid ShippingDto shippingDto, @PathVariable("id") String id, UriComponentsBuilder uriBuilder){
//        Shipping shippingCadastrado = shippingService.update(shippingDto.convert(shippingDto,warehouseService,buyerService,productService,statesService), id);
//
//        URI uri = uriBuilder.path("/shipping/search/{id}").buildAndExpand(shippingCadastrado.getShippingId()).toUri();
//        return ResponseEntity.created(uri).body(shippingCadastrado);
//    }
}


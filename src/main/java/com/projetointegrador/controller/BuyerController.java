package com.projetointegrador.controller;

import com.projetointegrador.entity.Buyer;
import com.projetointegrador.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @PostMapping(value = "/insert")
    public ResponseEntity<Buyer> insert(@RequestBody @Valid Buyer buyer, UriComponentsBuilder uriBuilder) throws IOException{
        Buyer buyerCadastrado = buyerService.insert(buyer);

        URI uri = uriBuilder.path("/buyer/search/{id}").buildAndExpand(buyerCadastrado.getCpf()).toUri();
        return ResponseEntity.created(uri).body(buyerCadastrado);
    }
}

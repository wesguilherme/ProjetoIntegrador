package com.projetointegrador.controller;

import com.projetointegrador.entity.Seller;
import com.projetointegrador.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Seller> cadastrar(@RequestBody Seller seller, UriComponentsBuilder uriBuilder) throws IOException {
        Seller sellerCadastrado = sellerService.cadastrar(seller);

        URI uri = uriBuilder.path("/seller/buscar/{id}").buildAndExpand(sellerCadastrado.getCpf()).toUri();
        return ResponseEntity.created(uri).body(sellerCadastrado);
    }
}

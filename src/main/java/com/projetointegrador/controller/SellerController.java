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

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * @param seller é esperado um objeto do tipo seller
     * @param uriBuilder é esperado um objeto do tipo uriBuilder
     * @return um seller cadastrado
     * @throws IOException
     */
    @PostMapping(value = "/insert")
    public ResponseEntity<Seller> insert(@RequestBody @Valid Seller seller, UriComponentsBuilder uriBuilder) throws IOException {
        Seller sellerCadastrado = sellerService.insert(seller);

        URI uri = uriBuilder.path("/seller/search/{id}").buildAndExpand(sellerCadastrado.getCpf()).toUri();
        return ResponseEntity.created(uri).body(sellerCadastrado);
    }
}

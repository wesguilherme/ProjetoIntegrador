package com.projetointegrador.controller;

import com.projetointegrador.entity.Representative;
import com.projetointegrador.service.RepresentativeService;
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
@RequestMapping(value = "/api/v1/")
public class RepresentativeController {

    @Autowired
    private RepresentativeService representativeService;

    @PostMapping(value = "/representative/insert")
    public ResponseEntity<Representative> insert(@RequestBody Representative representative, UriComponentsBuilder uriBuilder) throws IOException {
        Representative representativeCadastrado = representativeService.insert(representative);

        URI uri = uriBuilder.path("/representative/search/{id}").buildAndExpand(representativeCadastrado.getRepresentativeId()).toUri();
        return ResponseEntity.created(uri).body(representativeCadastrado);
    }
}

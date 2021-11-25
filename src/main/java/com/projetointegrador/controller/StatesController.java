package com.projetointegrador.controller;

import com.projetointegrador.entity.States;
import com.projetointegrador.service.StatesService;
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
@RequestMapping(value = "/api/v1/States")
public class StatesController {

    @Autowired
    private StatesService statesService;


    @PostMapping(value = "/insert")
    public ResponseEntity<States> insert(@RequestBody @Valid States states, UriComponentsBuilder uriBuilder) throws IOException {
        States statesCadastrado = statesService.insert(states);

        URI uri = uriBuilder.path("/states/search/{id}").buildAndExpand(statesCadastrado.getCep()).toUri();
        return ResponseEntity.created(uri).body(statesCadastrado);
    }
}

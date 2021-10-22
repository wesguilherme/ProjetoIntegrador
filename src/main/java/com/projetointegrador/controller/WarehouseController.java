package com.projetointegrador.controller;

import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Warehouse> cadastrar(@RequestBody Warehouse warehouse, UriComponentsBuilder uriBuilder){
        Warehouse warehouseCadastrado = warehouseService.cadastrar(warehouse);

        URI uri = uriBuilder.path("/warehouse/buscar/{id}").buildAndExpand(warehouseCadastrado.getWarehouseCode()).toUri();
        return ResponseEntity.created(uri).body(warehouseCadastrado);
    }
}

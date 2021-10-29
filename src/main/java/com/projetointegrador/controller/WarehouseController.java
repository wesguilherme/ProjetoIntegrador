package com.projetointegrador.controller;

import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping(value = "/warehouse/insert")
    public ResponseEntity<Warehouse> insert(@RequestBody Warehouse warehouse, UriComponentsBuilder uriBuilder){
        Warehouse warehouseCadastrado = warehouseService.insert(warehouse);

        URI uri = uriBuilder.path("/warehouse/search/{id}").buildAndExpand(warehouseCadastrado.getWarehouseCode()).toUri();
        return ResponseEntity.created(uri).body(warehouseCadastrado);
    }

    @GetMapping("/{code}")
    public boolean getWarehouseById(@PathVariable("code") String code) {
        return warehouseService.validWarehouse(code);
    }

    @GetMapping("/warehouse/lista")
    public ResponseEntity<List<String>> getWarehouseById() {
        List<String> nomes = Arrays.asList("Ana", "Wesley", "Rafael", "Alessandro");
        return ResponseEntity.ok(nomes);
    }
}

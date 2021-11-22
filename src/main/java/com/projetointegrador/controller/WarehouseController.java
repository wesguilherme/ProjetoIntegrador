package com.projetointegrador.controller;

import com.projetointegrador.dto.WarehouseResponseDto;
import com.projetointegrador.entity.Warehouse;
import com.projetointegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/warehouse/")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    /**
     * @param warehouse é esperado um objeto do tipo warehouse
     * @param uriBuilder é esperado um objeto do tipo uriBuilder
     * @return um warehouse cadastrado
     */
    @PostMapping(value = "/insert")
    public ResponseEntity<Warehouse> insert(@RequestBody Warehouse warehouse, UriComponentsBuilder uriBuilder){
        Warehouse warehouseCadastrado = warehouseService.insert(warehouse);

        URI uri = uriBuilder.path("/warehouse/search/{id}").buildAndExpand(warehouseCadastrado.getWarehouseCode()).toUri();
        return ResponseEntity.created(uri).body(warehouseCadastrado);
    }

    /**
     * @param productId é esperado um objeto do tipo productId
     * @return o produtos em warehouse atraves do productId
     */
    @GetMapping("/listWarehouseByProductId/{id}")
    public ResponseEntity<WarehouseResponseDto> getWarehouseByProductId(@PathVariable("id") String productId) {
        WarehouseResponseDto warehouses = warehouseService.warehouseListByProduct(productId);

        if (warehouses.getProductId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(warehouses);
    }
}

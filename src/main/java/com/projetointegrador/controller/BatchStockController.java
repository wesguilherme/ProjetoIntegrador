package com.projetointegrador.controller;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.service.BatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/")
public class BatchStockController {

    @Autowired
    private BatchStockService batchStockService;

    @GetMapping("completeListByBatchStock/")
    public List<BatchStockDto> completeListByBatchStock() {
        return batchStockService.completeListByBatchStock;
    }

    @GetMapping(value = "fresh-products/list")
    public ResponseEntity<?> getProductSellerId() {
        List<BatchStockDto> batchStockDto = batchStockService.listProduct();

        if (batchStockDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(batchStockDto);
    }
}
package com.projetointegrador.controller;

import com.projetointegrador.dto.BatchStockResponseDto;
import com.projetointegrador.service.BatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/")
public class BatchStockController {

    @Autowired
    private BatchStockService batchStockService;

//    @GetMapping("completeListByBatchStock/")
//    public List<BatchStockDto> completeListByBatchStock() {
//        return batchStockService.completeListByBatchStock;
//    }

    @GetMapping(value = "listById/{id}")
    public ResponseEntity<?> listBatchStockByProductId(@PathVariable String id) {
        BatchStockResponseDto batchStockResponseDto = batchStockService.listBatchStockByProductId(id);

        if (batchStockResponseDto.getProductId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(batchStockResponseDto);
    }

    @GetMapping(value = "list/{id}/{ordination}")
    public ResponseEntity<?> listBatchStockWithFilter(@PathVariable String id, @PathVariable String ordination) {
        BatchStockResponseDto batchStockResponseDto = batchStockService.listBatchStockWithFilter(id, ordination);

        if (batchStockResponseDto.getProductId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(batchStockResponseDto);
    }
}
package com.projetointegrador.controller;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.dto.BatchStockResponseDto;
import com.projetointegrador.dto.BatchStockResponseDtoDueDate;
import com.projetointegrador.service.BatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/batchStock/")
public class BatchStockController {

    @Autowired
    private BatchStockService batchStockService;

    @GetMapping(value = "listById/{id}")
    public ResponseEntity<BatchStockResponseDto> listBatchStockByProductId(@PathVariable String id) {
        BatchStockResponseDto batchStockResponseDto = batchStockService.listBatchStockByProductId(id);

        if (batchStockResponseDto.getProductId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(batchStockResponseDto);
    }

    @GetMapping(value = "list/{id}/{ordination}")
    public ResponseEntity<BatchStockResponseDto> listBatchStockWithFilter(@PathVariable String id, @PathVariable String ordination) {
        BatchStockResponseDto batchStockResponseDto = batchStockService.listBatchStockWithFilter(id, ordination);

        if (batchStockResponseDto.getProductId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(batchStockResponseDto);
    }

    @GetMapping("/due-date/{sectionCode}/{quantityOfDays}")
    public ResponseEntity<BatchStockResponseDtoDueDate> batchStockInSection(@PathVariable("sectionCode") String sectionCode, @PathVariable("quantityOfDays") Integer quantityOfDays) {
        BatchStockResponseDtoDueDate batchStock = BatchStockDto.convertByDueDate(batchStockService.batchStockInSection(sectionCode, quantityOfDays));
        return ResponseEntity.ok().body(batchStock);
    }

    @GetMapping(value = "/due-date/list")
    public ResponseEntity<BatchStockResponseDtoDueDate> batchStockListWithFilter(@RequestParam("quantityOfDays") Integer quantityOfDays, @RequestParam("typeId") Long typeId,
                                                                                 @PageableDefault(sort = "dueDate", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        BatchStockResponseDtoDueDate batchStockResponseDtoDueDate = BatchStockDto.convertByListWithFilter(batchStockService.batchStockListWithFilter(quantityOfDays, typeId, paginacao));
        return ResponseEntity.ok().body(batchStockResponseDtoDueDate);
    }
}
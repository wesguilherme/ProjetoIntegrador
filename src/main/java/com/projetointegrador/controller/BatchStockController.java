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

    /**
     * @param id é esperado o parâmetro do tipo id
     * @return uma lista de batchStockResponseDto cada produto
     * @author - Grupo 5
     */
    @GetMapping(value = "listById/{id}")
    public ResponseEntity<BatchStockResponseDto> listBatchStockByProductId(@PathVariable String id) {
        BatchStockResponseDto batchStockResponseDto = batchStockService.listBatchStockByProductId(id);

        if (batchStockResponseDto.getProductId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(batchStockResponseDto);
    }

    /**
     * @param id é esperado o parâmetro do tipo id
     * @param ordination é esperado o parâmetro do tipo ordination
     * @return uma lista de batchStockResponseDto por produto ordenado por
     * L - lote
     * C - quantidade atual
     * F - data de validade
     * @author - Grupo 5
     */
    @GetMapping(value = "list/{id}/{ordination}")
    public ResponseEntity<BatchStockResponseDto> listBatchStockWithFilter(@PathVariable String id, @PathVariable String ordination) {
        BatchStockResponseDto batchStockResponseDto = batchStockService.listBatchStockWithFilter(id, ordination);

        if (batchStockResponseDto.getProductId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(batchStockResponseDto);
    }

    /**
     * @param sectionCode é esperado o parâmetro de codigo da section
     * @param quantityOfDays é esperado o parâmetro de quantidade do batchStock
     * @return lista de batchStock ordenada pela data de validade
     * @author - Grupo 5
     */
    @GetMapping("/due-date/{sectionCode}/{quantityOfDays}")
    public ResponseEntity<BatchStockResponseDtoDueDate> batchStockInSection(@PathVariable("sectionCode") String sectionCode, @PathVariable("quantityOfDays") Integer quantityOfDays) {
        BatchStockResponseDtoDueDate batchStock = BatchStockDto.convertByDueDate(batchStockService.batchStockInSection(sectionCode, quantityOfDays));
        return ResponseEntity.ok().body(batchStock);
    }

    /**
     * @param quantityOfDays é esperado o parâmetro de quantidade de dias do batchStock
     * @param typeId é esperado o parâmetro de id do tipo de produto
     * @param paginacao é esperado o objeto do tipo pageable
     * @return lista de batchStockResponseDtoDueDate ordenada pela data de validade ASC ou DESC
     * @author - Grupo 5
     */
    @GetMapping(value = "/due-date/list")
    public ResponseEntity<BatchStockResponseDtoDueDate> batchStockListWithFilter(@RequestParam("quantityOfDays") Integer quantityOfDays, @RequestParam("typeId") Long typeId,
                                                                                 @PageableDefault(sort = "dueDate", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        BatchStockResponseDtoDueDate batchStockResponseDtoDueDate = BatchStockDto.convertByListWithFilter(batchStockService.batchStockListWithFilter(quantityOfDays, typeId, paginacao));
        return ResponseEntity.ok().body(batchStockResponseDtoDueDate);
    }
}
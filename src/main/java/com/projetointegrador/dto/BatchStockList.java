package com.projetointegrador.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BatchStockList {

    private Long batchStockNumber;
    private Integer currentQuantity;
    private LocalDate dueDate;

    public BatchStockList() {
    }

    public BatchStockList(Long batchStockNumber, Integer currentQuantity, LocalDate dueDate) {
        this.batchStockNumber = batchStockNumber;
        this.currentQuantity = currentQuantity;
        this.dueDate = dueDate;
    }
}

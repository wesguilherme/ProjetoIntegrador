package com.projetointegrador.dto;

import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BatchStockDto {

    private LocalDate dueDate;
    private LocalDateTime manufacturingTime;
    private LocalDate manufacturingDate;
    private Integer currentQuantity;
    private Integer initialQuantity;
    private Float minimumTemperature;
    private String currentTemperature;
    private Product product;

    public BatchStockDto(BatchStock batchStock) {
        this.dueDate = batchStock.getDueDate();
        this.manufacturingTime = batchStock.getManufacturingTime();
        this.manufacturingDate = batchStock.getManufacturingDate();
        this.currentQuantity = batchStock.getCurrentQuantity();
        this.initialQuantity = batchStock.getInitialQuantity();
        this.currentTemperature = batchStock.getCurrentTemperature();
        this.minimumTemperature = batchStock.getMinimumTemperature();
        this.product = product;
    }

    public static BatchStockDto converter(BatchStock batchStock){
        BatchStockDto batchStockDto = new BatchStockDto(batchStock);
        return batchStockDto;
    }
}

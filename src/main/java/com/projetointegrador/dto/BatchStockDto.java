package com.projetointegrador.dto;

import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BatchStockDto {

    private LocalDate dueDate;
    private LocalDateTime manufacturingTime;
    private LocalDate manufacturingDate;
    private Integer currentQuantity;
    private Integer initialQuantity;
    private Float minimumTemperature;
    private String currentTemperature;
    private List<Product> product;

    public BatchStockDto(BatchStock batchStock) {
        this.dueDate = batchStock.getDueDate();
        this.manufacturingTime = batchStock.getManufacturingTime();
        this.manufacturingDate = batchStock.getManufacturingDate();
        this.currentQuantity = batchStock.getCurrentQuantity();
        this.initialQuantity = batchStock.getInitialQuantity();
        this.currentTemperature = batchStock.getCurrentTemperature();
        this.minimumTemperature = batchStock.getMinimumTemperature();
        this.product = batchStock.getProduct();
    }

    public static List<BatchStockDto> converter(List<BatchStock> batchStock){
        return batchStock.stream().map(BatchStockDto::new).collect(Collectors.toList());
    }
}

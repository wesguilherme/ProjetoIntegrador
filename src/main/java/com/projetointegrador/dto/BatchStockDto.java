package com.projetointegrador.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BatchStockDto {

    private LocalDate dueDate;
    private LocalDateTime manufacturingTime;
    private LocalDate manufacturingDate;
    private Integer currentQuantity;
    private Integer initialQuantity;
    private Float minimumTemperature;
    private String currentTemperature;
    private Long productSellerId;

    public BatchStockDto() {

    }

    public BatchStockDto(LocalDate dueDate, LocalDateTime manufacturingTime, LocalDate manufacturingDate, Integer currentQuantity, Integer initialQuantity, Float minimumTemperature, String currentTemperature, Long productSellerId) {
        this.dueDate = dueDate;
        this.manufacturingTime = manufacturingTime;
        this.manufacturingDate = manufacturingDate;
        this.currentQuantity = currentQuantity;
        this.initialQuantity = initialQuantity;
        this.minimumTemperature = minimumTemperature;
        this.currentTemperature = currentTemperature;
        this.productSellerId = productSellerId;
    }
}

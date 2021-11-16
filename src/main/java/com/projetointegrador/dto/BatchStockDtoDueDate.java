package com.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockDtoDueDate {

    private Long batchStockNumber;
    private String productId;
    private String environmentType;
    private LocalDate dueDate;
    private Integer currentQuantity;
}

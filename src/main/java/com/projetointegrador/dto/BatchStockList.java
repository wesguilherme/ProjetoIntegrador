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
public class BatchStockList {

    private Long batchStockNumber;
    private Integer currentQuantity;
    private LocalDate dueDate;

}

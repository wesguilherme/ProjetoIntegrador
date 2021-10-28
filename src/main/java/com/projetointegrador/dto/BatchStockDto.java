package com.projetointegrador.dto;

import com.projetointegrador.entity.BatchStock;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public static List<BatchStockDto> convertBatchStock(List<BatchStock> batchStock){
        List<BatchStockDto> batchStockDto = new ArrayList<>();

        for (BatchStock item : batchStock) {
            BatchStockDto bat = new BatchStockDto();
            bat.setDueDate(item.getDueDate());
            bat.setCurrentQuantity(item.getCurrentQuantity());
            bat.setCurrentTemperature(item.getCurrentTemperature());
            bat.setManufacturingDate(item.getManufacturingDate());
            bat.setMinimumTemperature(item.getMinimumTemperature());
            bat.setInitialQuantity(item.getInitialQuantity());
            bat.setManufacturingTime(item.getManufacturingTime());

            bat.setProductSellerId(item.getProductSeller().getProductSellerId());
            batchStockDto.add(bat);
        }

        return batchStockDto;
    }
}

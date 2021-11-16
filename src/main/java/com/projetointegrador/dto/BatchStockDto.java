package com.projetointegrador.dto;

import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.repository.BatchStockPersistence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockDto {

    private LocalDate dueDate;
    private LocalDateTime manufacturingTime;
    private LocalDate manufacturingDate;
    private Integer currentQuantity;
    private Integer initialQuantity;
    private Float minimumTemperature;
    private String currentTemperature;
    private Long productSellerId;
    private Long batchStockNumber;


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
            bat.setBatchStockNumber(item.getBatchStockNumber());

            bat.setProductSellerId(item.getProductSeller().getProductSellerId());
            batchStockDto.add(bat);
        }

        return batchStockDto;
    }

    public static BatchStockResponseDtoDueDate convertByDueDate(List<BatchStockPersistence.BatchStockListByDays> batchStocks) {
        BatchStockResponseDtoDueDate batchStockResponseDtoDueDate = new BatchStockResponseDtoDueDate();
        List<BatchStockDtoDueDate> batchStockDtoDueDates = new ArrayList<>();

        for (BatchStockPersistence.BatchStockListByDays item : batchStocks) {
            BatchStockDtoDueDate batchStockDtoDueDate1 = new BatchStockDtoDueDate();
            batchStockDtoDueDate1.setBatchStockNumber(item.getBatch_stock_number());
            batchStockDtoDueDate1.setDueDate(item.getDue_date());
            batchStockDtoDueDate1.setEnvironmentType(item.getEnvironment_type());
            batchStockDtoDueDate1.setProductId(item.getProduct_id());
            batchStockDtoDueDate1.setCurrentQuantity(item.getCurrent_quantity());

            batchStockDtoDueDates.add(batchStockDtoDueDate1);
        }

        batchStockResponseDtoDueDate.setBatchStock(batchStockDtoDueDates);
        return batchStockResponseDtoDueDate;
    }

    public static BatchStockResponseDtoDueDate convertByListWithFilter(List<BatchStockPersistence.BatchStockListByFilter> batchStocks) {
        BatchStockResponseDtoDueDate batchStockResponseDtoDueDate = new BatchStockResponseDtoDueDate();
        List<BatchStockDtoDueDate> batchStockDtoDueDates = new ArrayList<>();

        for (BatchStockPersistence.BatchStockListByFilter item : batchStocks) {
            BatchStockDtoDueDate batchStockDtoDueDate1 = new BatchStockDtoDueDate();
            batchStockDtoDueDate1.setBatchStockNumber(item.getBatch_stock_number());
            batchStockDtoDueDate1.setDueDate(item.getDue_date());
            batchStockDtoDueDate1.setEnvironmentType(item.getEnvironment_type());
            batchStockDtoDueDate1.setProductId(item.getProduct_id());
            batchStockDtoDueDate1.setCurrentQuantity(item.getCurrent_quantity());

            batchStockDtoDueDates.add(batchStockDtoDueDate1);
        }

        batchStockResponseDtoDueDate.setBatchStock(batchStockDtoDueDates);
        return batchStockResponseDtoDueDate;
    }
}

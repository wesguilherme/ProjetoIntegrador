package com.projetointegrador.service;

import com.projetointegrador.dto.BatchStockDto;
import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.repository.BatchStockPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchStockService {

    @Autowired
    private BatchStockPersistence batchStockPersistence;

    @Autowired
    private ProductSellerService productSellerService;

    public BatchStockService() {
    }

    public BatchStockService(BatchStockPersistence batchStockPersistence) {
        this.batchStockPersistence = batchStockPersistence;
    }

    public List<BatchStock> listBatchStockByProductId(String id) {
        List<BatchStockDto> batchStockDto = new ArrayList<>();

        ProductSeller productSeller = productSellerService.;

        List<BatchStock> batchStock = batchStockPersistence.findAll();

        for (BatchStock item : batchStock) {
            BatchStockDto bat = new BatchStockDto();
            bat.setDueDate(item.getDueDate());
            bat.setCurrentQuantity(item.getCurrentQuantity());
            bat.setCurrentTemperature(item.getCurrentTemperature());
            bat.setManufacturingDate(item.getManufacturingDate());
            bat.setMinimumTemperature(item.getMinimumTemperature());
            bat.setInitialQuantity(item.getInitialQuantity());
            bat.setManufacturingTime(item.getManufacturingTime());
            bat.setBatchStockNumberDto(item.getBatchNumber());

            bat.setProductSellerId(item.getProductSeller().getProductSellerId());
            batchStockDto.add(bat);
        }

        return batchStock;
    }

    public List<BatchStockDto> listByBatchNumber(Long batchNumber) {
        List<BatchStockDto> newListBatch = batchStockDto.;

        List<BatchStockDto> batch = newListBatch.stream()
                .filter(item -> item.getBatchStockNumberDto().equals(batchNumber))
                .collect(Collectors.toList());
        return batch;
    }

    public List<BatchStockDto> listByCurrentQuantity(Long batchNumber) {
        List<BatchStockDto> newListBatch = batchStockDto.;

        List<BatchStockDto> batch = newListBatch.stream()
                .filter(item -> item.getBatchStockNumberDto().equals(batchNumber))
                .sorted((BatchStockDto a, BatchStockDto b) -> a.getCurrentQuantity().compareTo(b.getCurrentQuantity()))
                .collect(Collectors.toList());
        return batch;
    }

    public List<BatchStockDto> listByDueDate(Long batchNumber) {
        List<BatchStockDto> newListBatch = batchStockDto.;

        List<BatchStockDto> batch = newListBatch.stream()
                .filter(item -> item.getBatchStockNumberDto().equals(batchNumber))
                .collect(Collectors.toList());
        return batch;
    }
}

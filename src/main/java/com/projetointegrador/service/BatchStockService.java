package com.projetointegrador.service;

import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.repository.BatchStockPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class BatchStockService {

    @Autowired
    private BatchStockPersistence batchStockPersistence;

    public BatchStockService(BatchStockPersistence batchStockPersistence) {
        this.batchStockPersistence = batchStockPersistence;
    }

    public BatchStock getBatchStockByProductSeller(ProductSeller productSeller) {
        Optional<BatchStock> val;

        val = batchStockPersistence.findByProductSeller(productSeller);

        if (val.isPresent()){
            return val.get();
        }else {
            throw new RuntimeException("Não existe batchStock para esse produto.");
        }
    }

    public Boolean verifyProductInBatchStock(ProductSeller productSellerId, Integer quantity){

        BatchStock batchStock = getBatchStockByProductSeller(productSellerId);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = batchStock.getDueDate();

        int period = Period.between(startDate, endDate).getDays();

        if(quantity <= batchStock.getCurrentQuantity()){
            if (period >= 21){
                return true;
            }else {
                throw new RuntimeException("Validade do produto inferior a 3 semanas");
            }
        }else {
            throw new RuntimeException("Não existe produto no estoque");
        }
    }
}

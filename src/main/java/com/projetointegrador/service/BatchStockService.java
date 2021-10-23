package com.projetointegrador.service;

import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.repository.BatchStockPeristence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchStockService {

    @Autowired
    private BatchStockPeristence batchStockPeristence;

    public BatchStockService() {
    }

    public BatchStockService(BatchStockPeristence batchStockPeristence) {
        this.batchStockPeristence = batchStockPeristence;
    }

    public BatchStock cadastrar(BatchStock batchStock){
        return batchStockPeristence.save(batchStock);
    }
}

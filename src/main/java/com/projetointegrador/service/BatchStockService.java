package com.projetointegrador.service;

import com.projetointegrador.dto.ProductItemDto;
import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.repository.BatchStockPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class BatchStockService {

    @Autowired
    private BatchStockPersistence batchStockPersistence;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSellerService productSellerService;

    public BatchStockService(BatchStockPersistence batchStockPersistence) {
        this.batchStockPersistence = batchStockPersistence;
    }

    public BatchStock getBatchStockByProductSeller(ProductSeller productSeller) {
        BatchStock batchStock = batchStockPersistence.findByProductSeller(productSeller);

        if (batchStock.getBatchStockId() != null){
            return batchStock;
        }else {
            throw new RuntimeException("Não existe batchStock para esse produto.");
        }
    }

    public void verifyProductInBatchStock(List<ProductItemDto> productItemDto){

        for (ProductItemDto item : productItemDto) {

            Product product = productService.getByIdProduct(item.getProductId());
            ProductSeller productSeller = productSellerService.getProductSellerByProduto(product);

            BatchStock batchStock = getBatchStockByProductSeller(productSeller);

            LocalDate startDate = LocalDate.now();
            LocalDate endDate = batchStock.getDueDate();

            int period = Period.between(startDate, endDate).getDays();

            if(item.getQuantity() <= batchStock.getCurrentQuantity()){
                if (period >= 21){
                    String resp = "Validade do produto: " + item.getProductId() + " é inferior a 3 semanas";
                    throw new RuntimeException(resp);
                }
            }else {
                String resp = "Não existe estoque para este produto: " + item.getProductId();
                throw new RuntimeException(resp);
            }
        }
    }
}

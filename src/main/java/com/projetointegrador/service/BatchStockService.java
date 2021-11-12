package com.projetointegrador.service;

import com.projetointegrador.dto.BatchStockList;
import com.projetointegrador.dto.BatchStockResponseDto;
import com.projetointegrador.dto.ProductItemDto;
import com.projetointegrador.dto.SectionResponseDto;
import com.projetointegrador.entity.BatchStock;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.repository.BatchStockPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchStockService {

    @Autowired
    private BatchStockPersistence batchStockPersistence;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSellerService productSellerService;

    public BatchStockService() {}

    public BatchStockService(BatchStockPersistence batchStockPersistence) {
        this.batchStockPersistence = batchStockPersistence;
    }

    public BatchStockService(BatchStockPersistence batchStockPersistence, ProductService productService, ProductSellerService productSellerService) {
        this.batchStockPersistence = batchStockPersistence;
        this.productService = productService;
        this.productSellerService = productSellerService;
    }

    public BatchStockResponseDto listBatchStockByProductId(String id) {
        BatchStockResponseDto batchStockResponseDto = new BatchStockResponseDto();
        List<BatchStockList> batchStockList = new ArrayList<>();

        Product product = productService.getByIdProduct(id);

        ProductSeller productSeller = productSellerService.getProductSellerByProduto(product);

        List<BatchStock> batchStock = batchStockPersistence.findByProductSeller(productSeller);

        for (BatchStock item : batchStock) {
//            BatchStockResponseDto bat = new BatchStockResponseDto();
            SectionResponseDto sectionResponseDto = new SectionResponseDto();
            sectionResponseDto.setSectionCode(item.getInboundOrder().getSection().getSectionCode());
            sectionResponseDto.setWarehouseCode(item.getInboundOrder().getSection().getWarehouse().getWarehouseCode());

            batchStockResponseDto.setSectionResponseDto(sectionResponseDto);
            batchStockResponseDto.setProductId(item.getProductSeller().getProduct().getProductId());

            BatchStockList bs = new BatchStockList();
            bs.setBatchStockNumber(item.getBatchStockNumber());
            bs.setCurrentQuantity(item.getCurrentQuantity());
            bs.setDueDate(item.getDueDate());

            batchStockList.add(bs);
        }

        batchStockResponseDto.setBatchStockList(batchStockList);

        return batchStockResponseDto;
    }

    public BatchStockResponseDto listBatchStockWithFilter(String id, String ordination) {
        BatchStockResponseDto batchStockResponseDto = new BatchStockResponseDto();
        List<BatchStockList> batchStockList = new ArrayList<>();

        Product product = productService.getByIdProduct(id);

        ProductSeller productSeller = productSellerService.getProductSellerByProduto(product);

        List<BatchStock> batchStock = batchStockPersistence.findByProductSeller(productSeller);

        for (BatchStock item : batchStock) {
            BatchStockResponseDto bat = new BatchStockResponseDto();

            SectionResponseDto sectionResponseDto = new SectionResponseDto();
            sectionResponseDto.setSectionCode(item.getInboundOrder().getSection().getSectionCode());
            sectionResponseDto.setWarehouseCode(item.getInboundOrder().getSection().getWarehouse().getWarehouseCode());

            batchStockResponseDto.setSectionResponseDto(sectionResponseDto);
            batchStockResponseDto.setProductId(item.getProductSeller().getProduct().getProductId());

            BatchStockList bs = new BatchStockList();
            bs.setBatchStockNumber(item.getBatchStockNumber());
            bs.setCurrentQuantity(item.getCurrentQuantity());
            bs.setDueDate(item.getDueDate());

            batchStockList.add(bs);
        }

        if (ordination.equalsIgnoreCase("L")) {
            List<BatchStockList> batchSortedByBatchNumber = batchStockList.stream()
                    .sorted((BatchStockList a, BatchStockList b) -> a.getBatchStockNumber().compareTo(b.getBatchStockNumber()))
                    .collect(Collectors.toList());
            batchStockResponseDto.setBatchStockList(batchSortedByBatchNumber);
        } else if (ordination.equalsIgnoreCase("C")) {
            List<BatchStockList> batchSortedByCurrentQuantity = batchStockList.stream()
                    .sorted((BatchStockList a, BatchStockList b) -> a.getCurrentQuantity().compareTo(b.getCurrentQuantity()))
                    .collect(Collectors.toList());
            batchStockResponseDto.setBatchStockList(batchSortedByCurrentQuantity);
        } else if (ordination.equalsIgnoreCase("F")) {
            List<BatchStockList> batchSortedByDueDate = batchStockList.stream()
                    .sorted((BatchStockList a, BatchStockList b) -> a.getDueDate().compareTo(b.getDueDate()))
                    .collect(Collectors.toList());
            batchStockResponseDto.setBatchStockList(batchSortedByDueDate);
        }

        return batchStockResponseDto;
    }

    public List<BatchStock> getBatchStockByProductSeller(ProductSeller productSeller) {

        List<BatchStock> val = batchStockPersistence.findByProductSeller(productSeller);

        if (!val.isEmpty()) {
            return val;
        } else {
            throw new RuntimeException("Não existe batchStock para esse produto.");
        }
    }

    public void verifyProductInBatchStock(List<ProductItemDto> productItemDto) {

        for (ProductItemDto item : productItemDto) {

            Product product = productService.getByIdProduct(item.getProductId());
            ProductSeller productSeller = productSellerService.getProductSellerByProduto(product);

            List<BatchStock> batchStock = getBatchStockByProductSeller(productSeller);

            LocalDate startDate = LocalDate.now();
            LocalDate endDate = batchStock.get(0).getDueDate();

            int period = Period.between(startDate, endDate).getDays();

            if (item.getQuantity() <= batchStock.get(0).getCurrentQuantity()) {
                if (period >= 21) {
                    String resp = "Validade do produto: " + item.getProductId() + " é inferior a 3 semanas";
                    throw new RuntimeException(resp);
                }
            } else {
                String resp = "Não existe estoque para este produto: " + item.getProductId();
                throw new RuntimeException(resp);
            }
        }
    }

    public List<BatchStockPersistence.BatchStockListByDays> batchStockInSection(String sectionCode, Integer quantityOfDays) {
        List<BatchStockPersistence.BatchStockListByDays> batchStocks = batchStockPersistence.listbatchByDays(sectionCode, quantityOfDays);
        return batchStocks;
    }

    public List<BatchStockPersistence.BatchStockListByFilter> batchStockListWithFilter(Integer quantityOfDays, Long typeId, String classification) {
        List<BatchStockPersistence.BatchStockListByFilter> batchStocksFilter = batchStockPersistence.listbatchByFilter(quantityOfDays, typeId, classification);
        return batchStocksFilter;
    }
}
